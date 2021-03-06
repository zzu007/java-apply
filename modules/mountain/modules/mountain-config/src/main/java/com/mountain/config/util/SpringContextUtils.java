package com.mountain.config.util;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * spring上下文工具类
 */
public class SpringContextUtils {

    private static ApplicationContext applicationContext; // Spring应用上下文环境

    public static void setApplicationContext(final ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(final String name) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(final String name, final Class<T> requiredType) {
        if (applicationContext == null) {
            return null;
        }
        return (T) applicationContext.getBean(name, requiredType);
    }


    public static boolean containsBean(final String name) {
        if (applicationContext == null) {
            return false;
        }
        return applicationContext.containsBean(name);
    }


    public static Class<?> getType(final String name) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getType(name);
    }


    @SuppressWarnings("unchecked")
    public static <T> Set<T> getBeanSetOfType(final Class<T> type) {
        if (applicationContext == null) {
            return Collections.emptySet();
        }
        Map<?, ?> map = applicationContext.getBeansOfType(type);
        Set<T> set = new HashSet<T>(map.size());
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            set.add((T) entry.getValue());
        }
        return set;
    }


    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getBeanMapOfType(final Class<T> type) {
        if (applicationContext == null) {
            return Collections.emptyMap();
        }
        return applicationContext.getBeansOfType(type);
    }


    public static String[] getBeanNamesOfType(final Class<?> type) {
        if (applicationContext == null) {
            return new String[0];
        }
        return applicationContext.getBeanNamesForType(type);
    }



    public static void configureBeanByName(Object bean) {
        applicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    public static void configureBeanByType(Object bean) {
        applicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
    }
}