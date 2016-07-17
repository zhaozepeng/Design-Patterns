package com.android.facadepattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.facadepattern.facade.Facade;
import com.android.facadepattern.facade.IFacade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String args[]) {
        IFacade facade = new Facade();
        facade.operationA();
        facade.operationB();
        facade.operationC();
    }
}
