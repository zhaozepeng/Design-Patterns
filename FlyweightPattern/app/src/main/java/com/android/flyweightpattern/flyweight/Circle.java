package com.android.flyweightpattern.flyweight;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-10
 */
public class Circle implements Shape{
    String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        Log.e("Shawn", "画了一个" + color +"的圆形");
    }
}
