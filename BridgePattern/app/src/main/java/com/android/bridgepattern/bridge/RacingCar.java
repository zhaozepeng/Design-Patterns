package com.android.bridgepattern.bridge;

import android.util.Log;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-10
 */
public class RacingCar extends Car{
    public RacingCar(ITire tire) {
        super(tire);
    }

    @Override
    public void run() {
        Log.e("shawn", "racing car " + getTire().run());
    }
}
