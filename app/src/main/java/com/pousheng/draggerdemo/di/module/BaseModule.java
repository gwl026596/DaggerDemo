package com.pousheng.draggerdemo.di.module;

import android.content.Context;

import com.pousheng.draggerdemo.MyApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseModule {

    @Provides
    Context provideContext(){
        return MyApplication.context;
    }
}
