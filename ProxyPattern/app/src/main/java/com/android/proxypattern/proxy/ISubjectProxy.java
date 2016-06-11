package com.android.proxypattern.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-11
 */
public abstract class ISubjectProxy implements InvocationHandler {

    protected Subject subject;

    public ISubjectProxy(Subject subject) {
        this.subject = subject;
    }

}
