package com.example.admin.storage.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.storage.R;

import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //声明四个Tab的布局文件
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    //声明四个Tab的ImageButton
    private ImageButton mWeixinImg;
    private ImageButton mSettingImg;

    //声明四个Tab分别对应的Fragment
    private Fragment mFragWeinxin;
    private Fragment mFragSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        initViews();//初始化控件
        initEvents();//初始化事件
        selectTab(0);//默认选中第一个Tab
    }

    private void initEvents() {
        //初始化四个Tab的点击事件
        mTabWeixin.setOnClickListener(this);
//        mTabFrd.setOnClickListener(this);
//        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
    }

    private void initViews() {
        //初始化四个Tab的布局文件
        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
//        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
//        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        //初始化四个ImageButton
        mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
//        mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
//        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton) findViewById(R.id.id_tab_setting_img);
    }

    //处理Tab的点击事件
    @Override
    public void onClick(View v) {
        //先将四个ImageButton置为灰色
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                selectTab(0);//当点击的是微信的Tab就选中微信的Tab
                break;
//            case R.id.id_tab_frd:
//                selectTab(1);
//                break;
//            case R.id.id_tab_address:
//                selectTab(2);
//                break;
            case R.id.id_tab_setting:
                selectTab(1);
                break;
        }

    }

    //进行选中Tab的处理
    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            //当选中点击的是微信的Tab时
            case 0:
                //设置微信的ImageButton为绿色
                mWeixinImg.setImageResource(R.mipmap.tab_work_pressed);
                //如果微信对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFragWeinxin == null) {
                    mFragWeinxin = new WorkFragment();
                    transaction.add(R.id.id_content, mFragWeinxin);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFragWeinxin);
                }
                break;
//            case 1:
//                mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
//                if (mFragFrd == null) {
//                    mFragFrd = new FrdFragment();
//                    transaction.add(R.id.id_content, mFragFrd);
//                } else {
//                    transaction.show(mFragFrd);
//                }
//                break;
//            case 2:
//                mAddressImg.setImageResource(R.mipmap.tab_address_pressed);
//                if (mFragAddress == null) {
//                    mFragAddress = new AddressFragment();
//                    transaction.add(R.id.id_content, mFragAddress);
//                } else {
//                    transaction.show(mFragAddress);
//                }
//                break;
            case 1:
                mSettingImg.setImageResource(R.mipmap.tab_settings_pressed);
                if (mFragSetting == null) {
                    mFragSetting = new SettingFragment();
                    transaction.add(R.id.id_content, mFragSetting);
                } else {
                    transaction.show(mFragSetting);
                }
                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFragWeinxin != null) {
            transaction.hide(mFragWeinxin);
        }
//        if (mFragFrd != null) {
//            transaction.hide(mFragFrd);
//        }
//        if (mFragAddress != null) {
//            transaction.hide(mFragAddress);
//        }
        if (mFragSetting != null) {
            transaction.hide(mFragSetting);
        }
    }

    //将四个ImageButton置为灰色
    private void resetImgs() {
        mWeixinImg.setImageResource(R.mipmap.tab_work_normal);
//        mFrdImg.setImageResource(R.mipmap.tab_find_frd_normal);
//        mAddressImg.setImageResource(R.mipmap.tab_address_normal);
        mSettingImg.setImageResource(R.mipmap.tab_settings_normal);
    }
    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
