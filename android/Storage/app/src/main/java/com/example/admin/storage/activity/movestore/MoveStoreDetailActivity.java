package com.example.admin.storage.activity.movestore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.storage.R;
import com.example.admin.storage.utiles.Constant;

public class MoveStoreDetailActivity extends FragmentActivity implements View.OnClickListener{
    private TextView titleView;
    private String title,order;
    private LinearLayout mTabLayout;
    private LinearLayout mTab1,mTab2,mTab3;
    private TextView tab1_tv,tab2_tv,tab3_tv;
    private Fragment mFragOutStore,mFragIntoStore,mFragFinish;
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
        View tabvew = View.inflate(this,R.layout.subtab_three,null);
        mTabLayout.addView(tabvew);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        // 获取传递过来的信息。
//        int iId = getIntent().getIntExtra("receiptId",0);
        order = getIntent().getStringExtra("ykyw");
        titleView = (TextView) findViewById(R.id.id_title_tv);
        title = "移出【"+order+"】";
        titleView.setText(order);

        fragbundle.putString(Constant.RECEIVE_FRAG_ORDER, order);
        //初始化Tab的布局文件
        mTab1 = (LinearLayout) findViewById(R.id.id_tab1);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab2);
        mTab3 = (LinearLayout) findViewById(R.id.id_tab3);
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);

//        tab1_tv = (TextView)findViewById(R.id.id_tab1);
//        tab1_tv.setText("入库列表");
//        tab2_tv= (TextView)findViewById(R.id.id_tab2);
//        tab2_tv.setText("已入库列表");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_tab1:
                selectTab(0);
                break;
            case R.id.id_tab2:
                selectTab(1);
                break;
            case R.id.id_tab3:
                selectTab(2);
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
        mFragOutStore = null;
        mFragIntoStore = null;
        switch (i) {
            //当选中点击的是微信的Tab时
            case 0:
                title = "移出【"+order+"】";
                titleView.setText(title);
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab3.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                //如果第一个Fragment没有实例化，则进行实例化，并显示出来
                if (mFragOutStore == null) {
                    mFragOutStore = new OutStorageFragment();
                    mFragOutStore.setArguments(fragbundle);

                    transaction.add(R.id.id_content, mFragOutStore);
                } else {
                    //如果Fragment已经实例化，则直接显示出来
                    transaction.show(mFragOutStore);
                }

                break;

            case 1:
                Log.i("tab1","tab1-----");
                title = "移入【"+order+"】";
                titleView.setText(title);
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab3.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                if (mFragIntoStore == null) {
                    mFragIntoStore = new IntoStoreFragment();
                    mFragIntoStore.setArguments(fragbundle);
                    transaction.add(R.id.id_content, mFragIntoStore);
                } else {
                    transaction.show(mFragIntoStore);
                }
                break;
            case 2:
                title = "【"+order+"】";
                titleView.setText(title);
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));

                mTab3.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                //如果第一个Fragment没有实例化，则进行实例化，并显示出来
                if (mFragFinish == null) {
                    mFragFinish = new FinishedFragment();
                    mFragFinish.setArguments(fragbundle);

                    transaction.add(R.id.id_content, mFragFinish);
                } else {
                    //如果Fragment已经实例化，则直接显示出来
                    transaction.show(mFragFinish);
                }

                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }
    @SuppressLint("ResourceAsColor")
    private void hideFragments(FragmentTransaction transaction) {
        Log.i("hideFragments","hideFragments");
        if (mFragOutStore != null) {

            transaction.hide(mFragOutStore);
        }
        if (mFragIntoStore != null) {

            transaction.hide(mFragIntoStore);
        }
        if (mFragFinish != null) {

            transaction.hide(mFragFinish);
        }
    }
}
