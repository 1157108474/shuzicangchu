package com.example.admin.storage.activity.movestore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.storage.R;
import com.example.admin.storage.utiles.Constant;

public class YKYWDetailActivity extends FragmentActivity implements View.OnClickListener {

    private TextView titleView;
    private String title, order;
    private int orderid;
    private LinearLayout mTabLayout;
    private LinearLayout mTab1, mTab2;
    private TextView tab1_tv, tab2_tv;
    private Fragment mFragYkyw, mFragFinish;
    private Bundle fragbundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_receive_list);
        initViews();
        selectTab(0);
    }

    private void initViews() {
        mTabLayout = (LinearLayout) findViewById(R.id.box);
        View tabvew = View.inflate(this, R.layout.subtab_two, null);
        mTabLayout.addView(tabvew);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        // 获取传递过来的信息。
//        int iId = getIntent().getIntExtra("receiptId",0);
        order = getIntent().getStringExtra("ykyw");
        orderid = getIntent().getIntExtra("ykywid",0);
        Log.i("传递的创建的移库移位单信息---",order);
        titleView = (TextView) findViewById(R.id.id_title_tv);
//        title = "移出【" + order + "】";
        titleView.setText(order);
//        order = "YKYW900232345";
        fragbundle.putString(Constant.RECEIVE_FRAG_ORDER, order);
        fragbundle.putInt(Constant.RECEIVE_FRAG_ORDER_ID, orderid);
        //初始化Tab的布局文件
        TextView tv1 = findViewById(R.id.id_tab1);
        tv1.setText("移库移位列表");
        TextView tv2 = findViewById(R.id.id_tab2);
        tv2.setText("已完成列表");
        mTab1 = (LinearLayout) findViewById(R.id.id_tab_receive);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab_received);
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);

//        tab1_tv = (TextView)findViewById(R.id.id_tab1);
//        tab1_tv.setText("入库列表");
//        tab2_tv= (TextView)findViewById(R.id.id_tab2);
//        tab2_tv.setText("已入库列表");
    }

    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        mFragYkyw = null;
        mFragFinish = null;
        switch (i) {
            //当选中点击的是微信的Tab时
            case 0:
//                title = "移出【" + order + "】";
//                titleView.setText(title);
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                //如果第一个Fragment没有实例化，则进行实例化，并显示出来
                if (mFragYkyw == null) {
                    mFragYkyw = new OutStorageFragment();
                    mFragYkyw.setArguments(fragbundle);

                    transaction.add(R.id.id_content, mFragYkyw);
                } else {
                    //如果Fragment已经实例化，则直接显示出来
                    transaction.show(mFragYkyw);
                }

                break;

            case 1:
                Log.i("tab1", "tab1-----");
//                title = "移入【" + order + "】";
//                titleView.setText(title);
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
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
        Log.i("hideFragments", "hideFragments");
        if (mFragYkyw != null) {

            transaction.hide(mFragYkyw);
        }

        if (mFragFinish != null) {

            transaction.hide(mFragFinish);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_tab_receive:
                selectTab(0);
                break;
            case R.id.id_tab_received:
                selectTab(1);
                break;

            default:
                break;
        }
    }
}
