package com.jitgur.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.jitgur.mall.admin.bo.AdminUserDetails;
import com.jitgur.mall.admin.dao.UmsAdminResourceRelationDao;
import com.jitgur.mall.admin.dao.UmsAdminRoleRelationDao;
import com.jitgur.mall.admin.dto.UmsAdminParam;
import com.jitgur.mall.admin.dto.UpdateAdminPasswordParam;
import com.jitgur.mall.admin.service.UmsAdminCacheService;
import com.jitgur.mall.admin.service.UmsAdminService;
import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.common.util.RequestUtil;
import com.jitgur.mall.mbg.mapper.UmsAdminLoginLogMapper;
import com.jitgur.mall.mbg.mapper.UmsAdminMapper;
import com.jitgur.mall.mbg.mapper.UmsAdminRoleRelationMapper;
import com.jitgur.mall.mbg.model.*;
import com.jitgur.mall.security.util.JwtTokenUtil;
import com.jitgur.mall.security.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminResourceRelationDao adminResourceRelationDao;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;


    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }


    @Override
    public List<UmsAdmin> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        return adminMapper.selectByExample(example);
    }


    @Override
    public UmsAdmin getUserByUsername(String username) {
        UmsAdmin admin = getCacheService().getAdmin(username);
        if (admin != null) {
            return admin;
        }
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            getCacheService().setAdmin(admin);
        }
        return admin;
    }


    @Override
    public UmsAdmin getUserByAdminId(Long adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }


    @Override
    public int delete(Long id) {
        UmsAdmin admin = getUserByAdminId(id);
        if (admin == null) {
            return 0;
        }
        return adminMapper.deleteByPrimaryKey(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = getUserByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("当前用户不存在");
        }
        List<UmsResource> resourceList = adminResourceRelationDao.getResourceList(admin.getId());
        return new AdminUserDetails(admin, resourceList);
    }


    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminResourceRelationDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            getCacheService().setResourceList(adminId, resourceList);
        }
        return resourceList;
    }


    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        // 删除原来的关系
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(example);

        // 添加新的关系
        if (CollUtil.isNotEmpty(roleIds)) {
            List<UmsAdminRoleRelation> relationList = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation adminRoleRelation = new UmsAdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(roleId);
                relationList.add(adminRoleRelation);
            }
            adminRoleRelationDao.insertList(relationList);
        }
        getCacheService().delResourceList(adminId);
        return count;
    }


    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);

    }


    @Override
    public int update(Long adminId, UmsAdmin umsAdmin) {
        UmsAdmin admin = getUserByAdminId(adminId);
        // 密码相同，则不改变
        if (passwordEncoder.matches(umsAdmin.getPassword(), admin.getPassword())) {
            umsAdmin.setPassword(null);
        }

        // 密码加密保存
        if (!StringUtil.isEmpty(umsAdmin.getPassword())) {
            umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        }
        umsAdmin.setId(adminId);
        int count = adminMapper.updateByPrimaryKeySelective(umsAdmin);
        getCacheService().delAdmin(adminId);
        return count;
    }


    @Override
    public int updatePassword(UpdateAdminPasswordParam updateParam) {
        if (updateParam.getNewPassword().equals(updateParam.getOldPassword())) {
            Asserts.fail("新旧密码不能相同");
        }
        UmsAdmin admin = getUserByUsername(updateParam.getUsername());
        if (passwordEncoder.matches(updateParam.getOldPassword(), admin.getPassword())) {
            Asserts.fail("密码不正确");
        }
        admin.setPassword(passwordEncoder.encode(updateParam.getNewPassword()));
        adminMapper.updateByPrimaryKey(admin);
        getCacheService().delAdmin(admin.getId());
        return 1;
    }


    @Override
    public UmsAdmin registry(UmsAdminParam adminParam) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtil.copyProperties(adminParam, admin);

        // 密码长度
        if (admin.getPassword().length() < 8) {
            Asserts.fail("密码长度不能小于8位");
        }

        // 用户是否存在
        UmsAdmin admin1 = getUserByUsername(admin.getUsername());
        if (admin1 != null) {
            Asserts.fail("用户名已存在");
        }

        // 密码加密保存
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        adminMapper.insert(admin);
        return admin;
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("请输入正确的密码");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("对不起，您的账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateAdminLastLoginTime(username);
            insertAdminLoginLog(username);
        } catch (ArithmeticException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    /**
     * 添加用户登录日志
     */
    private void insertAdminLoginLog(String username) {
        UmsAdmin admin = getUserByUsername(username);
        if (admin == null) {
            return;
        }
        UmsAdminLoginLog log = new UmsAdminLoginLog();
        log.setAdminId(admin.getId());
        log.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(log);
    }


    /**
     * 更改用户登录时间
     */
    private void updateAdminLastLoginTime(String username) {
        UmsAdmin admin = new UmsAdmin();
        admin.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(admin, example);
    }

}
