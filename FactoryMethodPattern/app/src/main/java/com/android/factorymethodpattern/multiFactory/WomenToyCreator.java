package com.android.factorymethodpattern.multiFactory;

import android.util.Log;

import com.android.factorymethodpattern.toy.IToy;
import com.android.factorymethodpattern.toy.WomenToy;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class WomenToyCreator implements IToyCreator  {
    private static final String TAG = "WomenToyCreator";

    @Override
    public IToy createToy() {
        IToy toy = new WomenToy();
        Log.e(TAG, "buy a/an " + toy.getName()+" for " + toy.price() + " yuan, and then ---");
        toy.play();
        return toy;
    }
}
