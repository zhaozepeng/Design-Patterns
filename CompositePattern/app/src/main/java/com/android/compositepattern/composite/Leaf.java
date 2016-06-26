package com.android.compositepattern.composite;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-26
 */
public class Leaf implements Component {
    @Override
    public void operation() {
        Log.e("shawn", "this if leaf " + this);
    }

    @Override
    public void add(Component child) {
        throw new UnsupportedOperationException("leaf can't add child");
    }

    @Override
    public void remove(Component child) {
        throw new UnsupportedOperationException("leaf can't remove child");
    }

    @Override
    public Component getChild(int position) {
        throw new UnsupportedOperationException("leaf doesn't have any child");
    }
}
