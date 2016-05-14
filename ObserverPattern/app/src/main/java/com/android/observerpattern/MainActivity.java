package com.android.observerpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.firstmodule.javaapi.DataObservable;
import com.android.secondmodule1.javaapi.DataObserver;
import com.android.secondmodule2.javaapi.javaapi.DataNotify;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_data_change_1).setOnClickListener(this);
        DataObservable.getInstance().addObserver(new DataObserver());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataObservable.getInstance().deleteObserver(new DataObserver());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_data_change_1){
            DataNotify.notifyDataChanged();
        }
    }
}
