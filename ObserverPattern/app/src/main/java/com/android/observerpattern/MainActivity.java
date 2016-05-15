package com.android.observerpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.firstmodule.javaapi.DataObservable;
import com.android.secondmodule1.eventbus.EventNotifier;
import com.android.secondmodule1.javaapi.DataObserver;
import com.android.secondmodule2.eventbus.EventObserver;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_data_change_1).setOnClickListener(this);
        findViewById(R.id.btn_data_change_2).setOnClickListener(this);
        findViewById(R.id.btn_data_change_3).setOnClickListener(this);

        DataObservable.getInstance().addObserver(new DataObserver());

        com.android.secondmodule2.multiobserver.DataCommunicate.registerDataListenerTwo();
        com.android.secondmodule1.multiobserver.DataCommunicate.registerDataListenerOne();

        EventObserver.getInstance().registerObserver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataObservable.getInstance().deleteObserver(new DataObserver());

        com.android.secondmodule2.multiobserver.DataCommunicate.unRegisterDataListenerTwo();
        com.android.secondmodule1.multiobserver.DataCommunicate.unRegisterDataListenerOne();

        EventObserver.getInstance().unRegisterObserver();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_data_change_1){
            com.android.secondmodule2.javaapi.DataNotify.notifyDataChanged();
        }else if (v.getId() == R.id.btn_data_change_2){
            com.android.secondmodule1.multiobserver.DataCommunicate.notifyDataListenerTwo();
            com.android.secondmodule2.multiobserver.DataCommunicate.notifyDataListenerOne();
        }else if (v.getId() == R.id.btn_data_change_3) {
            EventNotifier.getInstance().sendEvent();
        }
    }
}
