package com.alibaba.dubbo.demo;

public interface GenericDemoService {

    <T> T helloword(T t);

}
