package com.book.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 将获得的参数注入JavaBean类
 * @param
 * @return
 */
public class WebUtils  {

    public static <T> T copyParaToBean(Map map, T bean){

        try {
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public static Integer switchToInt(String valStr,Integer defaultValStr) throws Exception{
        // 这里为啥要这样写？
        if( valStr == null){
            return defaultValStr;
        }

        return Integer.parseInt(valStr);

    }

}
