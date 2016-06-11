package com.android.proxypattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.proxypattern.proxy.ISubjectProxy;
import com.android.proxypattern.proxy.ProxyA;
import com.android.proxypattern.proxy.ProxyB;
import com.android.proxypattern.proxy.RealSubject;
import com.android.proxypattern.proxy.Subject;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button proxy_a;
    private Button proxy_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        proxy_a = (Button) findViewById(R.id.proxy_a);
        proxy_b = (Button) findViewById(R.id.proxy_b);

        proxy_a.setOnClickListener(this);
        proxy_b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Subject subject = new RealSubject();
        ISubjectProxy proxy = null;
        switch (v.getId()) {
            case R.id.proxy_a:
                proxy = new ProxyA(subject);
                break;
            case R.id.proxy_b:
                proxy = new ProxyB(subject);
                break;
        }
        Subject sub = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), proxy);
        try {
            Log.e("Shawn", sub.operationA());
        }catch (UnsupportedOperationException e){
            Log.e("Shawn", e.getMessage());
        }
        try {
            Log.e("Shawn", sub.operationB());
        }catch (UnsupportedOperationException e){
            Log.e("Shawn", e.getMessage());
        }
    }
}
