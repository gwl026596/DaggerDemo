package com.pousheng.draggerdemo.api.service;

import com.pousheng.draggerdemo.BuildConfig;
import com.pousheng.draggerdemo.api.interceptor.MyInterceptor;


import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static  RetrofitManager instance;
    private RetrofitManager(){

    }
    public static RetrofitManager getInstance(){
        if (instance==null){
           synchronized (RetrofitManager.class){
               if (instance==null){
                   instance=new RetrofitManager();
               }
           }
        }
        return instance;
    }

    public Retrofit createRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
                .connectTimeout(9, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new MyInterceptor())
                .build();
        ignoreSSLCheck(okHttpClient);
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private void ignoreSSLCheck(OkHttpClient okHttpClient) {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hv1 = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        String workerClassName = "okhttp3.OkHttpClient";
        try {
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(okHttpClient, hv1);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(okHttpClient, sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
