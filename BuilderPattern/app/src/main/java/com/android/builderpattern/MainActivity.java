package com.android.builderpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.builderpattern.builder.Computer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_build = (Button) findViewById(R.id.btn_build);

        btn_build.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_build:
                Computer computer = new Computer.Builder()
                        .setCPU("inter-skylake-i7")
                        .setGPU("GTX-Titan")
                        .setMemoryType("ddr4-2133MHz")
                        .setMemorySize(16)
                        .setStorageType("ssd")
                        .setStorageSize(512)
                        .setScreenType("IPS")
                        .setScreenSize(28)
                        .setOSType("Ubuntu/Window10")
                        .create();
        }
    }
}
