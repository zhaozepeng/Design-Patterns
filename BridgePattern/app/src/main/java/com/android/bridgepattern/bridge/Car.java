package com.android.bridgepattern.bridge;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-10
 */
public abstract class Car {
    private ITire tire;

    public Car(ITire tire) {
        this.tire = tire;
    }

    public ITire getTire() {
        return tire;
    }

    public abstract void run();
}
