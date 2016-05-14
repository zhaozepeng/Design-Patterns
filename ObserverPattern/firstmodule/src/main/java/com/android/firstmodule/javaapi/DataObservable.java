package com.android.firstmodule.javaapi;

import java.util.Observable;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-14
 */
public class DataObservable extends Observable{

    private static volatile DataObservable instance;
    private DataObservable() {}

    public static DataObservable getInstance() {
        if (instance == null){
            synchronized (DataObservable.class){
                if (instance == null){
                    instance = new DataObservable();
                }
            }
        }
        return instance;
    }

    public void notifyDataChanged(DataBean data){
        setChanged();
        notifyObservers(data);
    }
}
