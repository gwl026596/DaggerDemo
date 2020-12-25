package com.pousheng.draggerdemo.di.componet;

import com.pousheng.draggerdemo.MyApplication;
import com.pousheng.draggerdemo.di.module.BaseModule;
import com.pousheng.draggerdemo.di.module.MainActivityContributeModule;
import com.pousheng.draggerdemo.di.module.ServiceApiModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        BaseModule.class,
        ServiceApiModule.class,
        MainActivityContributeModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
})
public interface MyApplicationComponet {
    void inject(MyApplication application);
}
