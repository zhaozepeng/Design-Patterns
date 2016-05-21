package com.android.factorymethodpattern.multiFactory;

import android.util.Log;

import com.android.factorymethodpattern.toy.IToy;
import com.android.factorymethodpattern.toy.MenToy;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class MenToyCreator implements IToyCreator  {
    private static final String TAG = "MenToyCreator";

    @Override
    public IToy createToy() {
        IToy toy = new MenToy();
        Log.e(TAG, "buy a/an " + toy.getName()+" for " + toy.price() + " yuan, and then ---");
        toy.play();
        return toy;
    }
}
