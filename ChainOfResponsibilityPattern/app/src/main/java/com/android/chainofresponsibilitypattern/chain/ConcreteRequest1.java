package com.android.chainofresponsibilitypattern.chain;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-24
 */
public class ConcreteRequest1 extends AbstractRequest{

    public ConcreteRequest1(Object object) {
        super(object);
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
