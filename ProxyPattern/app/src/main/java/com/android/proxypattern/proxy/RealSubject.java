package com.android.proxypattern.proxy;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-11
 */
public class RealSubject implements Subject{
    @Override
    public String operationA() {
        return "this is operationA";
    }

    @Override
    public String operationB() {
        return "this is operationB";
    }
}
