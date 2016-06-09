package com.android.decoratorpattern.decorator;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-09
 */
public class VerticalScrollBarDecorator extends DecoratorWindow{

    public VerticalScrollBarDecorator(IWindow window) {
        super(window);
    }

    @Override
    public void draw() {
        super.draw();
        Log.e("shawn", "then drawing the vertical scroll bar");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with vertical scroll bar";
    }
}
