package com.android.flyweightpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.flyweightpattern.flyweight.Flyweight;
import com.android.flyweightpattern.flyweight.FlyweightFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_flyweight;
    private FlyweightFactory factory = new FlyweightFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_flyweight = (Button) findViewById(R.id.btn_flyweight);

        btn_flyweight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flyweight:
                Flyweight flyweight1 = factory.getFlyweight("a");
                Flyweight flyweight2 = factory.getFlyweight("b");
                Flyweight flyweight3 = factory.getFlyweight("a");
                Log.e("Shawn", "flyweight1==flyweight2 : " + (flyweight1 == flyweight2));
                Log.e("Shawn", "flyweight1==flyweight3 : " + (flyweight1 == flyweight3));
                break;
        }
    }
}
