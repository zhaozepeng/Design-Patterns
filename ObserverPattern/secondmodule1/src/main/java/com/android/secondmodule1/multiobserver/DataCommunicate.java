package com.android.secondmodule1.multiobserver;

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

    private static IDataListenerOne listenerOne = null;

    public static void registerDataListenerOne() {
        IMultiDataObservable dataObservable = MultiDataObserver.getInstance();
        listenerOne = new IDataListenerOne() {
            @Override
            public void OnDataChanged(DataBean data) {
                Log.e(TAG, data.temperature + "");
            }
        };
        dataObservable.addObserver(listenerOne);
    }

    public static void notifyDataListenerTwo() {
        IMultiDataObservable dataObservable = MultiDataObserver.getInstance();
        ArrayList<IDataListenerTwo> lists = dataObservable.findObserver(IDataListenerTwo.class);
        DataBean bean = new DataBean();
        bean.temperature = (int) (Math.random() * 40);
        for (IDataListenerTwo listener : lists) {
            listener.OnDataChanged(bean);
        }
    }

    public static void unRegisterDataListenerOne() {
        IMultiDataObservable dataObservable = MultiDataObserver.getInstance();
        dataObservable.deleteObserver(listenerOne);
    }
}
