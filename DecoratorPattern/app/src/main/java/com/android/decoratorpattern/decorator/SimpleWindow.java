package com.android.decoratorpattern.decorator;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-09
 */
public class SimpleWindow implements IWindow {
    @Override
    public void draw() {
        Log.e("shawn", "drawing a window");
    }

    @Override
    public String getDescription() {
        return "a window";
    }
}
