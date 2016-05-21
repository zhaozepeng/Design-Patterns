package com.android.factorymethodpattern.toy;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class MenToy implements IToy{
    @Override
    public String getName() {
        return "PS4";
    }

    @Override
    public float price() {
        return 2300;
    }

    @Override
    public void play() {
        Log.e("play", "a man is playing GTA5 on ps4");
    }
}
