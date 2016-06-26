package com.android.compositepattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.compositepattern.composite.Component;
import com.android.compositepattern.composite.Composite;
import com.android.compositepattern.composite.Leaf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_component = (Button) findViewById(R.id.btn_component);

        btn_component.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_component:
                Component root = new Composite();

                Component leaf1 = new Leaf();
                Composite branch = new Composite();
                root.add(leaf1);
                root.add(branch);

                Component leaf2 = new Leaf();
                branch.add(leaf2);

                root.operation();
                break;
        }
    }
}
