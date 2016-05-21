package com.android.factorymethodpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.factorymethodpattern.factory.ConcreteToyCreator;
import com.android.factorymethodpattern.factory.IToyCreator;
import com.android.factorymethodpattern.multiFactory.ChildrenToyCreator;
import com.android.factorymethodpattern.multiFactory.MenToyCreator;
import com.android.factorymethodpattern.multiFactory.WomenToyCreator;
import com.android.factorymethodpattern.toy.ChildrenToy;
import com.android.factorymethodpattern.toy.MenToy;
import com.android.factorymethodpattern.toy.WomenToy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_child;
    private Button btn_men;
    private Button btn_women;

    private IToyCreator creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creator = new ConcreteToyCreator();
        initView();
    }

    private void initView() {
        btn_child = (Button) findViewById(R.id.btn_child);
        btn_men = (Button) findViewById(R.id.btn_men);
        btn_women = (Button) findViewById(R.id.btn_women);

        btn_child.setOnClickListener(this);
        btn_men.setOnClickListener(this);
        btn_women.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        com.android.factorymethodpattern.multiFactory.IToyCreator toyCreator;
        switch (v.getId()) {
            case R.id.btn_child:
                toyCreator = new ChildrenToyCreator();
                toyCreator.createToy();
                creator.createToy(ChildrenToy.class);
                break;
            case R.id.btn_men:
                toyCreator = new MenToyCreator();
                toyCreator.createToy();
                creator.createToy(MenToy.class);
                break;
            case R.id.btn_women:
                toyCreator = new WomenToyCreator();
                toyCreator.createToy();
                creator.createToy(WomenToy.class);
                break;
        }
    }
}
