package com.example.admin.storage.activity.buyer;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.storage.R;
import com.example.admin.storage.utiles.Constant;

public class ReceiveDetailActivity extends FragmentActivity implements View.OnClickListener{
    private LinearLayout mTabLayout;
    private LinearLayout mTabReceive;
    private LinearLayout mTabReceived;
    private Fragment mFragReceive;
    private Fragment mFragReceived;
    private String wzjsnumber;
    private int wzjsid,buyorderid;
    private Bundle fragbundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_receive_list);
        initViews();
        selectTab(0);
    }
    private void initViews() {
        mTabLayout = (LinearLayout)findViewById(R.id.box);
        View tabvew = View.inflate(this,R.layout.subtab_two,null);
        mTabLayout.addView(tabvew);
        //返回键
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        // 获取传递过来的信息。
//        int iId = getIntent().getIntExtra("receiptId",0);
        wzjsnumber = getIntent().getStringExtra("receiptOrder");
//        buyorderid = getIntent().getIntExtra("buyOrderid",0);
        wzjsid = getIntent().getIntExtra("receiptOrderid",0);
        String buyCode = getIntent().getStringExtra("buyOrderCode");
        TextView textView = (TextView) findViewById(R.id.id_title_tv);
        textView.setText(wzjsnumber);
        //activity向fragment传值的hundle 赋值
        fragbundle.putString(Constant.RECEIVE_FRAG_ORDER, wzjsnumber);
        fragbundle.putInt(Constant.RECEIVE_FRAG_ORDER_ID, wzjsid);
//        fragbundle.putInt(Constant.BUY_FRAG_ORDER_ID, buyorderid);
        fragbundle.putString(Constant.BUY_FRAG_ORDER_CODE, buyCode);
        //初始化Tab的布局文件
        mTabReceive = (LinearLayout) findViewById(R.id.id_tab_receive);
        mTabReceived = (LinearLayout) findViewById(R.id.id_tab_received);
        mTabReceive.setOnClickListener(this);
        mTabReceived.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_tab_receive:
                selectTab(0);//当点击的是微信的Tab就选中微信的Tab
                break;
            case R.id.id_tab_received:
                selectTab(1);
                Log.i("111","1111");
                break;
             default:
                    break;
        }
    }
    @SuppressLint("ResourceAsColor")
    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        mFragReceive = null;
        mFragReceived = null;
        switch (i) {
            //当选中点击的是微信的Tab时
            case 0:
                //设置微信的ImageButton为绿色
                mTabReceived.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTabReceive.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                //如果微信对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFragReceive == null) {
                    mFragReceive = new ReceiveFragment();
                    //activity向fragment传值
                    mFragReceive.setArguments(fragbundle);

                    transaction.add(R.id.id_content, mFragReceive);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFragReceive);
                }

                break;

            case 1:
                Log.i("tab1","tab1-----");
                mTabReceive.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTabReceived.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                if (mFragReceived == null) {
                    mFragReceived = new ReceivedFragment();
                    //activity向fragment传值
                    mFragReceived.setArguments(fragbundle);
                    transaction.add(R.id.id_content, mFragReceived);
                } else {
                    transaction.show(mFragReceived);
                }
                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }
    @SuppressLint("ResourceAsColor")
    private void hideFragments(FragmentTransaction transaction) {
        Log.i("hideFragments","hideFragments");
        if (mFragReceive != null) {

            transaction.hide(mFragReceive);
        }

        if (mFragReceived != null) {

            transaction.hide(mFragReceived);
        }
    }
}
