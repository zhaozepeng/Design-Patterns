package com.android.bridgepattern.bridge;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-10
 */
public class RainyTire implements ITire{
    @Override
    public String run() {
        return "run on the rainy road.";
    }
}
