package com.android.secondmodule2.javaapi.javaapi;

import com.android.firstmodule.javaapi.DataBean;
import com.android.firstmodule.javaapi.DataObservable;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-14
 */
public class DataNotify {
    public static void notifyDataChanged(){
        DataBean bean = new DataBean();
        bean.temperature = (int) (Math.random() * 40);
        DataObservable.getInstance().notifyDataChanged(bean);
    }
}
