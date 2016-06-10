package com.android.bridgepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.bridgepattern.bridge.Car;
import com.android.bridgepattern.bridge.RacingCar;
import com.android.bridgepattern.bridge.RainyTire;
import com.android.bridgepattern.bridge.SandyTire;
import com.android.bridgepattern.bridge.SedanCar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sedanCar_with_rainyTire;
    private Button btn_sedanCar_with_sandyTire;
    private Button btn_racingCar_with_rainyTire;
    private Button btn_racingCar_with_sandyTire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_sedanCar_with_rainyTire = (Button) findViewById(R.id.btn_sedanCar_with_rainyTire);
        btn_sedanCar_with_sandyTire = (Button) findViewById(R.id.btn_sedanCar_with_sandyTire);
        btn_racingCar_with_rainyTire = (Button) findViewById(R.id.btn_racingCar_with_rainyTire);
        btn_racingCar_with_sandyTire = (Button) findViewById(R.id.btn_racingCar_with_sandyTire);

        btn_sedanCar_with_rainyTire.setOnClickListener(this);
        btn_sedanCar_with_sandyTire.setOnClickListener(this);
        btn_racingCar_with_rainyTire.setOnClickListener(this);
        btn_racingCar_with_sandyTire.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Car car = null;
        switch (v.getId()) {
            case R.id.btn_sedanCar_with_rainyTire:
                car = new SedanCar(new RainyTire());
                break;
            case R.id.btn_sedanCar_with_sandyTire:
                car = new SedanCar(new SandyTire());
                break;
            case R.id.btn_racingCar_with_rainyTire:
                car = new RacingCar(new RainyTire());
                break;
            case R.id.btn_racingCar_with_sandyTire:
                car = new RacingCar(new SandyTire());
                break;
        }
        car.run();
    }
}
