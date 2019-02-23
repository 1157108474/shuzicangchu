package com.example.admin.storage.utiles;

import android.content.Context;
import android.util.Log;


import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HTTP {


    private Context context;

    /**
     * 服务器名.
     */
    private String hostName;

    /**
     * 端口号
     */
    private int serverPort;


    public HTTP(Context context) {

        this.hostName = "192.168.80.5";
        this.serverPort = 8080;
        this.context = context;
    }


    public static String queryPost(String json,String url) {
        String address = url;//"http://"+hostName+":"+serverPort+"/"+url;
        Log.i("http",address+"?"+json);
        String responseData = null;
        try {
            OkHttpClient client = new OkHttpClient();
//            .Builder()
//                    .readTimeout(100,TimeUnit.SECONDS)//设置读取超时时间
//                    .writeTimeout(100,TimeUnit.SECONDS)//设置写的超时时间
//                    .connectTimeout(100,TimeUnit.SECONDS)//设置连接超时时间
//                    .build()
//            MultipartBody.Builder requestBody = new MultipartBody.Builder(json).setType(MultipartBody.FORM);
//
            RequestBody requestBody = FormBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=GBK"), json);//
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(address)
                    .build();
            Response response = client.newCall(request).execute();
            responseData = response.body().string();

            return responseData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
//    public static String postAsynHttp( String json,String url) throws IOException{
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(100,TimeUnit.SECONDS)//设置读取超时时间
//                .writeTimeout(100,TimeUnit.SECONDS)//设置写的超时时间
//                .connectTimeout(100,TimeUnit.SECONDS)//设置连接超时时间
//                .build();
////        String boundary = "xx--------------------------------------------------------------xx";
//        MultipartBody.Builder builder = new MultipartBody.Builder(boundary).setType(MultipartBody.FORM);
//
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(builder.build())
//                .build();
//        Call call = client.newCall(request);
//        Response response = call.execute();
//        String res = response.body().string();
//
//        if(rw.isFailed()){
//            throw  new IOException("保存失败！");
//        }
//    }
    public static String query(String url){
        String address = url;//"http://"+hostName+":"+serverPort+"/"+url;
        Log.i("http",address);
        String responseData = null;
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(address)
                    .build();
            Response response = client.newCall(request).execute();
            responseData = response.body().string();

            return responseData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
