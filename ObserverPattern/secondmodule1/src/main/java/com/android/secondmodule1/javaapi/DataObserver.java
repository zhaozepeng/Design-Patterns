package com.android.secondmodule1.javaapi;

import android.util.Log;

import com.android.firstmodule.javaapi.DataBean;
import com.android.firstmodule.javaapi.DataObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-14
 */
public class DataObserver implements Observer {
    private static final String TAG = "DataObserver";

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof DataObservable){
            if (data instanceof DataBean){
                Log.e(TAG, ((DataBean)data).temperature+"");
            }
        }
    }
}
