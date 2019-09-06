package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.GenericDemoService;

public class GenericDemoServiceImpl implements GenericDemoService {

    @Override
    public <T> T helloword(T t) {
        return null;
    }
}
