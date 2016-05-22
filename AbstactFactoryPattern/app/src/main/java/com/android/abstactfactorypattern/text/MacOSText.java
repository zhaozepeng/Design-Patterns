package com.android.abstactfactorypattern.text;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-22
 */
public class MacOSText implements IText{
    @Override
    public void show() {
        Log.e("show", "this is a MacOS text");
    }
}
