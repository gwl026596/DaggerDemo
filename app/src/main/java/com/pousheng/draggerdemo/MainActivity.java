package com.pousheng.draggerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pousheng.draggerdemo.api.response.BaseModel;
import com.pousheng.draggerdemo.api.response.Lcee;
import com.pousheng.draggerdemo.api.response.YySportResource;
import com.pousheng.draggerdemo.databinding.ActivityMain2Binding;
import com.pousheng.draggerdemo.model.ExpdetailExpModel;
import com.pousheng.draggerdemo.viewmodel.MainViewmodel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    MainViewmodel mainViewmodel;
    private TextView tvExp;
    private ActivityMain2Binding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        initView();
        initData();
        dataBinding.tvAdd.setOnClickListener(v -> {
            int newExp = new Integer(dataBinding.getExpdetailExpModel().getExp())+1;
            dataBinding.getExpdetailExpModel().setExp(String.valueOf(newExp));
            Log.d("还是",dataBinding.getExpdetailExpModel().getExp());
        });
    }

    private void initView() {
        tvExp = findViewById(R.id.tvExp);
    }

    private void initData() {
        //mainViewmodel= new ViewModelProvider(this).get(MainViewmodel.class);
        mainViewmodel.getExpdetailExp("20000326198").observe(this, new Observer<Lcee<YySportResource<BaseModel<ExpdetailExpModel>>>>() {
            @Override
            public void onChanged(Lcee<YySportResource<BaseModel<ExpdetailExpModel>>> yySportResourceLcee) {
             updateView(yySportResourceLcee);
            }
        });
    }

    private void updateView(Lcee<YySportResource<BaseModel<ExpdetailExpModel>>> yySportResourceLcee) {
      switch (yySportResourceLcee.status){
          case Content:
              dataBinding.setExpdetailExpModel(yySportResourceLcee.data.getResult().getResult());
              //tvExp.setText(yySportResourceLcee.data.getResult().getResult().getExp()+"");
              break;
      }
    }
}