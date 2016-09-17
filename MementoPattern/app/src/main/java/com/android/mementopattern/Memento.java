package com.android.mementopattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-17
 */
public class Memento {
    private int mState = 0;

    public void setState(int state) {
        this.mState = state;
    }

    public int getState() {
        return mState;
    }
}
