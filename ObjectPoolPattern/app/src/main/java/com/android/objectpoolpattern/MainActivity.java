package com.android.objectpoolpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_get = (Button) findViewById(R.id.btn_get);

        btn_get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Reusable reusable = ReusablePool.getInstance().requireReusable();
                            Log.e("CORRECT", "get a Reusable object " + reusable);
                            Thread.sleep(5000);
                            ReusablePool.getInstance().releaseReusable(reusable);
                        }catch (Exception e){
                            Log.e("ERROR", e.getMessage());
                        }
                    }
                }).start();
                break;
        }
    }
}
