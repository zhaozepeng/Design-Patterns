package com.android.factorymethodpattern.multiFactory;

import android.util.Log;

import com.android.factorymethodpattern.toy.ChildrenToy;
import com.android.factorymethodpattern.toy.IToy;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public class ChildrenToyCreator implements IToyCreator {
    private static final String TAG = "ChildrenToyCreator";

    @Override
    public IToy createToy() {
        IToy toy = new ChildrenToy();
        Log.e(TAG, "buy a/an " + toy.getName()+" for " + toy.price() + " yuan, and then ---");
        toy.play();
        return toy;
    }
}
