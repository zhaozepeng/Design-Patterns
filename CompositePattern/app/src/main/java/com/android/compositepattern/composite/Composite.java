package com.android.compositepattern.composite;

import android.util.Log;

import java.util.ArrayList;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-26
 */
public class Composite implements Component{

    private ArrayList<Component> componentList = new ArrayList<>();

    @Override
    public void operation() {
        Log.e("shawn", "this is composite " + this + " -------start");
        for (Component component : componentList) {
            component.operation();
        }
        Log.e("shawn", "this is composite " + this + " -------end");
    }

    @Override
    public void add(Component child) {
        componentList.add(child);
    }

    @Override
    public void remove(Component child) {
        componentList.remove(child);
    }

    @Override
    public Component getChild(int position) {
        return componentList.get(position);
    }
}
