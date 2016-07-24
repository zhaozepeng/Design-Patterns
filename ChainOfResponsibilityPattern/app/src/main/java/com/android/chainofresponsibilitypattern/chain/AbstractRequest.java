package com.android.chainofresponsibilitypattern.chain;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-24
 */
public abstract class AbstractRequest {

    private Object object;

    public AbstractRequest(Object object) {
        this.object = object;
    }

    public Object getContent() {
        return object;
    }

    public abstract int getLevel();
}
