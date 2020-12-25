package com.pousheng.draggerdemo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pousheng.draggerdemo.api.response.BaseModel;
import com.pousheng.draggerdemo.api.response.Lcee;
import com.pousheng.draggerdemo.api.response.YySportResource;
import com.pousheng.draggerdemo.api.service.ServiceApi;
import com.pousheng.draggerdemo.model.ExpdetailExpModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MainRepository {

    ServiceApi serviceApi;

    @Inject
    public  MainRepository(ServiceApi serviceApi){
     this.serviceApi=serviceApi;
    }

    public LiveData<Lcee<YySportResource<BaseModel<ExpdetailExpModel>>>> getExpdetailExp(String memberId){
        MutableLiveData<Lcee<YySportResource<BaseModel<ExpdetailExpModel>>>> data =new MutableLiveData();
        data.setValue(Lcee.loading());
        serviceApi.getExpdetailExp(memberId).enqueue(new Callback<YySportResource<BaseModel<ExpdetailExpModel>>>() {
            @Override
            public void onResponse(Call<YySportResource<BaseModel<ExpdetailExpModel>>> call, Response<YySportResource<BaseModel<ExpdetailExpModel>>> response) {
              if (response.body()!=null){
                  data.setValue(Lcee.content(response.body()));
              }else {
                  data.setValue(Lcee.empty());
              }
            }

            @Override
            public void onFailure(Call<YySportResource<BaseModel<ExpdetailExpModel>>> call, Throwable t) {
                data.setValue(Lcee.error(t));
            }
        });

        return data;
    };

}
