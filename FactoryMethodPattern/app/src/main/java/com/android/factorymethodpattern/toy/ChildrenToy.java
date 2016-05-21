package com.android.factorymethodpattern.toy;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class ChildrenToy implements IToy{
    @Override
    public String getName() {
        return "toy car";
    }

    @Override
    public float price() {
        return 10.5f;
    }

    @Override
    public void play() {
        Log.e("play", "a child is playing a toy car");
    }
}
