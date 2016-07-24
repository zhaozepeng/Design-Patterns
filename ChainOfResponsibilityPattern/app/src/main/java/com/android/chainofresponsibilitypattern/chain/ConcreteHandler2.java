package com.android.chainofresponsibilitypattern.chain;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-24
 */
public class ConcreteHandler2 extends AbstractHandler{
    @Override
    protected int getHandlerLevel() {
        return 2;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.print("ConcreteHandler2 handle this request : " + request.getContent() + "\n");
    }
}
