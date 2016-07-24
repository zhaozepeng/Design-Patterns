package com.android.chainofresponsibilitypattern.chain;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-24
 */
public class ConcreteRequest2 extends AbstractRequest{

    public ConcreteRequest2(Object object) {
        super(object);
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
