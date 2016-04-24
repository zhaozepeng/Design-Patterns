/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.android.singleton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-04-22
 */
public class SingletonPatternTest extends Activity{

    private static final String TAG = "SingletonPatternTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, SingleEnum.INSTANCE.getName());
        Log.e(TAG, Singleton.getInstance().getName());
    }
}
