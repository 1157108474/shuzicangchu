package com.example.admin.storage.baseclass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by jy on 2018/04/24.
 */
public class Settings {

    private static Settings sInstance;

    private UserInfo mUserInfo;

//    private Settings(){
//
////        mUserInfo = (UserInfo) Utils.readObject(getUsreInfoPath());
//    }

    public static Settings getInstance(){
        if(sInstance == null){
            sInstance = new Settings();
        }
        return sInstance;
    }

    public UserInfo getUserInfo(){
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo){
        mUserInfo = userInfo;

    }
    public void saveObject() {


    }
}
