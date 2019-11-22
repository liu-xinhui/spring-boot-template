package com.step.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.atteo.evo.inflector.English;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 代码生成器
 */
public class Generator {
    /**
     * 数据库参数配置，下面示例为mysql8.0数据库驱动，8以下请修改驱动名称
     */
    private static final String dbUrl = "jdbc:mysql://118.25.44.86:3306/template?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String dbDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String dbUsername = "dev";
    private static final String dbPassword = "hold123456";

    /**
     * 模块名称
     */
    private static final String targetModuleName = "template-main";
    /**
     * 包名
     */
    private static final String parentPackage = "com.step.template.main";

    /**
     * 读取控制台内容
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/" + targetModuleName + "/src/main/java");
        gc.setAuthor("");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setSwagger2(true);
        String entityName = "%s";
        gc.setEntityName(entityName);
        gc.setControllerName(entityName + "Controller");
        gc.setServiceName(entityName + "Service");
        gc.setMapperName(entityName + "Mapper");
        gc.setBaseResultMap(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(dbDriverName);
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(parentPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }

            @Override
            public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
                objectMap.put("svoPackage", parentPackage + ".svo");
                objectMap.put("superSvoClass", parentPackage + ".base.BaseSvo");
                TableInfo tableInfo = (TableInfo) objectMap.get("table");
                objectMap.put("restUrl", English.plural(tableInfo.getEntityPath()));
                tableInfo.getImportPackages().remove("java.io.Serializable");
                //公共字段
                List<TableField> commonFields = new ArrayList<>();
                List<TableField> newFields = new ArrayList<>();
                List<TableField> fields = tableInfo.getFields();
                StrategyConfig strategy = new StrategyConfig();
                strategy.setSuperEntityColumns("createBy", "createTime", "updateBy", "updateTime");
                for (TableField field : fields) {
                    if (strategy.includeSuperEntityColumns(field.getName())) {
                        commonFields.add(field);
                    } else {
                        newFields.add(field);
                    }
                }
                if (commonFields.size() == 4) {
                    tableInfo.setFields(newFields);
                    objectMap.put("superEntityClassPackage", parentPackage + ".base.BaseLogEntity");
                    objectMap.put("superEntityClass", "BaseLogEntity");
                } else {
                    objectMap.put("superEntityClassPackage", parentPackage + ".base.BaseEntity");
                    objectMap.put("superEntityClass", "BaseEntity");
                }
                return objectMap;
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + targetModuleName + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        focList.add(new FileOutConfig("/templates/svo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String parentPackagePath = parentPackage.replace(".", "/");
                String svoName = tableInfo.getEntityName() + "Svo" + StringPool.DOT_JAVA;
                return gc.getOutputDir() + "/" + parentPackagePath + "/svo/" + svoName;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null).setServiceImpl(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.no_change);
        //strategy.setSuperEntityClass("com.step.test.base.BaseEntity");
        strategy.setEntityLombokModel(true);
        //strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setSuperServiceClass(parentPackage + ".base.BaseService");
        strategy.setInclude(scanner("表名,如user;注意会覆盖已有文件"));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setLogicDeleteFieldName("deleted");
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
