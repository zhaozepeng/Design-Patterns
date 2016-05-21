package com.android.factorymethodpattern.factory;

import android.util.Log;

import com.android.factorymethodpattern.toy.IToy;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class ConcreteToyCreator implements IToyCreator{
    private static final String TAG = "ConcreteToyCreator";

    @Override
    public <T extends IToy> IToy createToy(Class<T> clazz) {
        if (clazz == null){
            throw new IllegalArgumentException("argument must not be null");
        }
        try {
            IToy toy = clazz.newInstance();
            Log.e(TAG, "buy a/an " + toy.getName()+" for " + toy.price() + " yuan, and then ---");
            toy.play();
            return toy;
        } catch (Exception e) {
            throw new UnknownError(e.getMessage());
        }
    }
}
