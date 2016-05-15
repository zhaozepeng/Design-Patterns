package com.android.secondmodule2.multiobserver;

import android.util.Log;

import com.android.firstmodule.DataBean;
import com.android.firstmodule.javaapi.DataObservable;
import com.android.firstmodule.multiobserver.IMultiDataObservable;
import com.android.firstmodule.multiobserver.MultiDataObserver;
import com.android.firstmodule.multiobserver.listener.IDataListenerOne;
import com.android.firstmodule.multiobserver.listener.IDataListenerTwo;

import java.util.ArrayList;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-15
 */
public class DataCommunicate {
    private static final String TAG = "DataCommunicate";

    private static IDataListenerTwo listenerTwo = null;

    public static void registerDataListenerTwo() {
        IMultiDataObservable dataObservable = MultiDataObserver.getInstance();
        listenerTwo = new IDataListenerTwo() {
            @Override
            public void OnDataChanged(DataBean data) {
                Log.e(TAG, data.temperature + "");
            }
        };
        dataObservable.addObserver(listenerTwo);
    }

    public static void notifyDataListenerOne() {
        IMultiDataObservable dataObservable = MultiDataObserver.getInstance();
        ArrayList<IDataListenerOne> lists = dataObservable.findObserver(IDataListenerOne.class);
        DataBean bean = new DataBean();
        bean.temperature = (int) (Math.random() * 40);
        for (IDataListenerOne listener : lists) {
            listener.OnDataChanged(bean);
        }
    }

    public static void unRegisterDataListenerTwo() {
        IMultiDataObservable dataObservable = MultiDataObserver.getInstance();
        dataObservable.deleteObserver(listenerTwo);
    }
}
