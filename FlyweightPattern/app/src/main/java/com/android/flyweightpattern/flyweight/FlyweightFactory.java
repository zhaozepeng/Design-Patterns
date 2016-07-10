package com.android.flyweightpattern.flyweight;

import java.util.HashMap;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-10
 */
public class FlyweightFactory {

    private HashMap<String, Flyweight> mFlyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = mFlyweights.get(key);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(key);
            mFlyweights.put(key, flyweight);
        }
        return flyweight;
    }
}
