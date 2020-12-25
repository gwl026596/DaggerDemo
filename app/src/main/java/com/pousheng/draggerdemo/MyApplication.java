package com.pousheng.draggerdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.pousheng.draggerdemo.di.componet.DaggerMyApplicationComponet;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class MyApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        DaggerMyApplicationComponet.create().inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
