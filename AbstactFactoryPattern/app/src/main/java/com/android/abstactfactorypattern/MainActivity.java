package com.android.abstactfactorypattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.abstactfactorypattern.factory.MacOSFactory;
import com.android.abstactfactorypattern.factory.UnixFactory;
import com.android.abstactfactorypattern.factory.WindowsFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_Windows;
    private Button btn_Unix;
    private Button btn_MacOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_Windows = (Button) findViewById(R.id.btn_Windows);
        btn_Unix = (Button) findViewById(R.id.btn_Unix);
        btn_MacOS = (Button) findViewById(R.id.btn_MacOS);

        btn_Windows.setOnClickListener(this);
        btn_Unix.setOnClickListener(this);
        btn_MacOS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Windows:
                WindowsFactory windowsFactory = new WindowsFactory();
                windowsFactory.createButton().show();
                windowsFactory.createText().show();
                break;
            case R.id.btn_Unix:
                UnixFactory unixFactory = new UnixFactory();
                unixFactory.createButton().show();
                unixFactory.createText().show();
                break;
            case R.id.btn_MacOS:
                MacOSFactory macOSFactory = new MacOSFactory();
                macOSFactory.createButton().show();
                macOSFactory.createText().show();
                break;
        }
    }
}
