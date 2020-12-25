package com.pousheng.draggerdemo.di.module;

import com.pousheng.draggerdemo.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityContributeModule {
   @ContributesAndroidInjector
    abstract MainActivity ContributeMainActivity();
}
