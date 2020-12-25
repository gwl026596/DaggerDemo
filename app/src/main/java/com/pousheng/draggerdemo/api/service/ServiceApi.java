package com.pousheng.draggerdemo.api.service;

import com.pousheng.draggerdemo.api.response.BaseModel;
import com.pousheng.draggerdemo.api.response.YySportResource;
import com.pousheng.draggerdemo.model.ExpdetailExpModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi {

    //查询会员信息以及积分兑换规则
    @GET("exp/getexpdetailExp/{memberId}")
    Call<YySportResource<BaseModel<ExpdetailExpModel>>> getExpdetailExp(@Path("memberId") String memberId);

}
