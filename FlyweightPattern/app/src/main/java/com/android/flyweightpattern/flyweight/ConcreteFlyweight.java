package com.android.flyweightpattern.flyweight;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-10
 */
public class ConcreteFlyweight implements Flyweight{

    private String intrinsicState;

    public ConcreteFlyweight(String state) {
        intrinsicState = state;
    }

    @Override
    public void operation() {
        Log.e("Shawn", "ConcreteFlyweight----" + intrinsicState);
    }
}
