package com.android.chainofresponsibilitypattern.chain;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-24
 */
public class Client {
    public static void main(String[] args) {
        AbstractHandler handler1 = new ConcreteHandler1();
        AbstractHandler handler2 = new ConcreteHandler2();
        handler1.nextHandler = handler2;

        AbstractRequest request1 = new ConcreteRequest1("request1");
        AbstractRequest request2 = new ConcreteRequest2("request2");

        handler1.handleRequest(request1);
        handler1.handleRequest(request2);
    }
}
