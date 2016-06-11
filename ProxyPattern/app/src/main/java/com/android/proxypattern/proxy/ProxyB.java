package com.android.proxypattern.proxy;

import java.lang.reflect.Method;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-11
 */
public class ProxyB extends ISubjectProxy{
    public ProxyB(Subject subject) {
        super(subject);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("operationA")){
            throw new UnsupportedOperationException("ProxyB can't invoke operationA");
        } else if (method.getName().equals("operationB")) {
            return method.invoke(subject, args);
        }
        return null;
    }
}
