package com.android.secondmodule2.eventbus;

import android.util.Log;

import com.android.firstmodule.DataBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-15
 */
public class EventObserver {
    private static final String TAG = "EventObserver";
    private static volatile EventObserver instance;

    private EventObserver() {

    }

    public static EventObserver getInstance() {
        if (instance == null) {
            synchronized (EventObserver.class) {
                if (instance == null) {
                    instance = new EventObserver();
                }
            }
        }
        return instance;
    }

    public void registerObserver() {
        EventBus.getDefault().register(this);
    }

    public void unRegisterObserver() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(DataBean bean) {
        Log.e(TAG, bean.temperature + "");
    }
}
