package com.jzbwlkj.application.retrofit;

import com.jzbwlkj.application.bean.CommonBean;
import com.jzbwlkj.application.bean.NumberBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
 * Created by admin on 2017/3/27.
 */

public interface Api {

    @FormUrlEncoded
    @POST(Config.ADD_NUMBER)
    Observable<CommonBean> addNumber(@Field("name") String name);

    @FormUrlEncoded
    @POST(Config.GET_NUMBER)
    Observable<NumberBean> getNumbers(@Field("name") String name);

    @FormUrlEncoded
    @POST(Config.DEL_NUMBER)
    Observable<CommonBean> delNumbers(@Field("id") String id);

}
