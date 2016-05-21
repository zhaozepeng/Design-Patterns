package com.android.factorymethodpattern.toy;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class WomenToy implements IToy{
    @Override
    public String getName() {
        return "plush toy";
    }

    @Override
    public float price() {
        return 200;
    }

    @Override
    public void play() {
        Log.e("play", "a woman is playing with a plush toy");
    }
}
