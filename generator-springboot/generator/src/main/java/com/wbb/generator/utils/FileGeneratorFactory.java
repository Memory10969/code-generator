package com.wbb.generator.utils;

import com.wbb.generator.utils.generator.*;

/**
 * @Author:William
 * @Date:2023/1/1 23:03
 */
public class FileGeneratorFactory {

    public static BaseGenerator getInstance(String type, GeneratorConfig config) throws Exception {
        if("entity".equals(type)){
            return new EntityGenerator(config);
        }else if("dao".equals(type)){
            return new DaoGenerator(config);
        }else if("service".equals(type)){
            return new ServiceGenerator(config);
        }else if("serviceImpl".equals(type)){
            return new ServiceImplGenerator(config);
        }
        else if("controller".equals(type)){
            return new ControllerGenerator(config);
        }
        else if("front".equals(type)){
            return new FrontGenerator(config);
        }
        return null;
    }
}
