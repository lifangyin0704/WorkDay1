package com.bwie.workday1;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 木子 on 2017/8/29.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }
}
