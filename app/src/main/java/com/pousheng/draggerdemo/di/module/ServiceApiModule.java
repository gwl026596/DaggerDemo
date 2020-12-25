package com.pousheng.draggerdemo.di.module;

import com.pousheng.draggerdemo.api.service.RetrofitManager;
import com.pousheng.draggerdemo.api.service.ServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceApiModule {
    @Singleton
    @Provides
    ServiceApi provideServiceApi(){
        return RetrofitManager.getInstance().createRetrofit().create(ServiceApi.class);
    }
}
