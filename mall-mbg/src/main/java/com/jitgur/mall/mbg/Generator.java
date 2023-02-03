package com.jitgur.mall.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * mbg生成器
 * Created by jitgur on 20230203
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        //        MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //        当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        //        读取MBG配置文件
        InputStream in = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(in);
        in.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator mbg = new MyBatisGenerator(configuration, callback, warnings);
        //        执行生成代码
        mbg.generate(null);

        //        输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}
