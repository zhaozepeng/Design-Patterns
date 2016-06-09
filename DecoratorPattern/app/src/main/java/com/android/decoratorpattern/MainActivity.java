package com.android.decoratorpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.decoratorpattern.decorator.HorizontalScrollBarDecorator;
import com.android.decoratorpattern.decorator.IWindow;
import com.android.decoratorpattern.decorator.SimpleWindow;
import com.android.decoratorpattern.decorator.VerticalScrollBarDecorator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_horizontal_window;
    private Button btn_vertical_window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_horizontal_window = (Button) findViewById(R.id.btn_horizontal_window);
        btn_vertical_window = (Button) findViewById(R.id.btn_vertical_window);

        btn_horizontal_window.setOnClickListener(this);
        btn_vertical_window.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_horizontal_window:
                IWindow horizontalWindow = new HorizontalScrollBarDecorator(new SimpleWindow());
                horizontalWindow.draw();
                Log.e("shawn", "window description : " + horizontalWindow.getDescription());
                break;
            case R.id.btn_vertical_window:
                IWindow verticalWindow = new VerticalScrollBarDecorator(new SimpleWindow());
                verticalWindow.draw();
                Log.e("shawn", "window description : " + verticalWindow.getDescription());
                break;
        }
    }
}
