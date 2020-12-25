package com.pousheng.draggerdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pousheng.draggerdemo.api.response.BaseModel;
import com.pousheng.draggerdemo.api.response.Lcee;
import com.pousheng.draggerdemo.api.response.YySportResource;
import com.pousheng.draggerdemo.model.ExpdetailExpModel;
import com.pousheng.draggerdemo.repository.MainRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainViewmodel extends ViewModel {
    MainRepository mainRepository;

    @Inject
    public MainViewmodel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public LiveData<Lcee<YySportResource<BaseModel<ExpdetailExpModel>>>> getExpdetailExp(String memberId) {
        return mainRepository.getExpdetailExp(memberId);
    }
}
