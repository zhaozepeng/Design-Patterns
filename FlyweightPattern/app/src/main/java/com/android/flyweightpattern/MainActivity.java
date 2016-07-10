package com.android.flyweightpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.flyweightpattern.flyweight.Shape;
import com.android.flyweightpattern.flyweight.ShapeFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_flyweight;
    private ShapeFactory factory = new ShapeFactory();

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
                Shape shape1 = factory.getShape("红色");
                shape1.draw();
                Shape shape2 = factory.getShape("灰色");
                shape2.draw();
                Shape shape3 = factory.getShape("绿色");
                shape3.draw();
                Shape shape4 = factory.getShape("红色");
                shape4.draw();
                Shape shape5 = factory.getShape("灰色");
                shape5.draw();
                Shape shape6 = factory.getShape("灰色");
                shape6.draw();

                Log.e("Shawn", "一共绘制了"+factory.getSize()+"中颜色的圆形");
                break;
        }
    }
}
