package com.android.abstactfactorypattern.button;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-22
 */
public class WindowsButton implements IButton {
    @Override
    public void show() {
        Log.e("show", "this is a Windows button");
    }
}
