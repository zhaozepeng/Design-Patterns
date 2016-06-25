package com.android.prototypepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.prototypepattern.prototype.ConcretePrototype;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_prototype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_prototype = (Button) findViewById(R.id.btn_prototype);

        btn_prototype.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prototype:
                ConcretePrototype src = new ConcretePrototype();
                src.setString("src");
                src.getStringList().add("src 1");
                src.getStringList().add("src 2");
                ConcretePrototype des = src.clone();
                des.setString("des");
                des.getStringList().add("des 1");
                des.getStringList().add("des 2");
                Log.e("shawn", "src.string = " + src.getString() +"   des.string = " + des.getString());
                StringBuilder builder = new StringBuilder();
                for (String temp : src.getStringList()) {
                    builder.append(temp).append("  ");
                }
                Log.e("shawn", "src.stringList = " + builder.toString());
                builder = new StringBuilder();
                for (String temp : des.getStringList()) {
                    builder.append(temp).append("  ");
                }
                Log.e("shawn", "des.stringList = " + builder.toString());
                break;
        }
    }
}
