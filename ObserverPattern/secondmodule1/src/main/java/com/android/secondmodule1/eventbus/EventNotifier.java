package com.android.secondmodule1.eventbus;

import com.android.firstmodule.DataBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-15
 */
public class EventNotifier {

    private static volatile EventNotifier instance;

    private EventNotifier() {
    }

    public static EventNotifier getInstance() {
        if (instance == null) {
            synchronized (EventNotifier.class) {
                if (instance == null) {
                    instance = new EventNotifier();
                }
            }
        }
        return instance;
    }

    public void sendEvent() {
        DataBean bean = new DataBean();
        bean.temperature = (int) (Math.random() * 40);
        EventBus.getDefault().post(bean);
    }
}
