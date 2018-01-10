package com.spring.test;

import com.spring.service.EmptyService;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.lang.reflect.Method;

public class MethorParamTest {

    public static void main(String args []) throws Exception {
        MethorParamTest methorParamTest = new MethorParamTest();
        Method method = methorParamTest.getClass().getMethod("tt", String.class, Long.class);

        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        MethodParameter mp1 = new MethodParameter(method, 0);
        MethodParameter mp2 = new MethodParameter(method, 1);
        MethodParameter rtp = new MethodParameter(method, -1);
        mp1.initParameterNameDiscovery(parameterNameDiscoverer);
        System.out.println(mp1.getParameterType()+" "+mp1.getParameterName());
        System.out.println(mp2.getParameterType());
        System.out.println(rtp.getParameterType());

        EmptyService emptyService = new EmptyService();
        Method m = emptyService.getClass().getMethod("dodo",String.class);
        String[] actualParams = parameterNameDiscoverer.getParameterNames(m);
        int len = actualParams==null?0:actualParams.length;
        System.out.println(len+"    "+actualParams[0]);
    }

    public Integer tt(String aa,Long bb) {
        return  0;
    }
}
