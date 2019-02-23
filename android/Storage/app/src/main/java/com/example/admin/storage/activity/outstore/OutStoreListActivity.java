package com.example.admin.storage.activity.outstore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;

public class OutStoreListActivity extends FragmentActivity implements View.OnClickListener{
    private LinearLayout mTabLayout;
    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private TextView tab1_tv,tab2_tv;
    private Fragment mFragNoOutStore;
    private Fragment mFragHasOutStore;
    private Bundle fragbundle = new Bundle();
    private ProgressDialog progressDialog;
    private UserInfo userInfo;
    private int ckId,applyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_receive_list);
        initViews();
        initData();
        selectTab(0);
    }
    private void initViews() {
        progressDialog = new ProgressDialog(this);
        mTabLayout = (LinearLayout)findViewById(R.id.box);
        View tabvew = View.inflate(this,R.layout.subtab_two,null);
        mTabLayout.addView(tabvew);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        Button btn_chai = findViewById(R.id.btn_add);
        btn_chai.setText("拆单");
        btn_chai.setVisibility(View.VISIBLE);
        btn_chai.setOnClickListener(this);
        // 获取传递过来的信息。
        ckId = getIntent().getIntExtra("ckId",0);
        String ckCode = getIntent().getStringExtra("ckCode");
         applyId = getIntent().getIntExtra("applyId",0);
        String applyCode = getIntent().getStringExtra("applyCode");
        TextView textView = (TextView) findViewById(R.id.id_title_tv);
        textView.setText(ckCode);

        fragbundle.putString(Constant.FRAG_ORDER_CODE, ckCode);
        fragbundle.putInt(Constant.FRAG_ORDER_ID, ckId);
        fragbundle.putInt(Constant.APPLY_ID, applyId);
        fragbundle.putString(Constant.APPLY_CODE, applyCode);
        //初始化Tab的布局文件
        mTab1 = (LinearLayout) findViewById(R.id.id_tab_receive);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab_received);
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);

        tab1_tv = (TextView)findViewById(R.id.id_tab1);
        tab1_tv.setText("出库列表");
        tab2_tv= (TextView)findViewById(R.id.id_tab2);
        tab2_tv.setText("已出库列表");
    }
    private void initData(){
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
    }
    private void split(){
//        final String materialCode = eWuliao.getText().toString();
        progressDialog.setMessage("正在拆单......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.APART_OUTSTORE;//+"?creator="+userInfo.id+"&ordernum="+buyOrder+"&materialCode="+materialCode+"&page="+page;
                try {
                    Message message = new Message();
//                    applyCode = "WZTH201709280060";//debug by shang
                    String json = "appFlag=1&userId="+userInfo.id+"&sheetId="+ckId+"&typeName=申领出库"+"&extendint1="+applyId+"&useddepartid="+"";
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("获得的未出库列表shuj-------",responseData);
                    if(responseData!=null){
                        JSONObject obj = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInteger("status")==1){

                            message.what = Constant.SUCCESS;
                        }else{
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("message", obj.getString("message"));

                            message.setData(bundle);
                            message.what = Constant.FAIL;
                        }
//                        responseData
                    }else{
                        message.what = Constant.NETWORK_ERROR;
                    }
                    handler.sendMessage(message);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = Constant.NETWORK_ERROR;
//                    msg.setData(b);
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case Constant.SUCCESS:
                    progressDialog.dismiss();
//                    String wuliaos = msg.getData().getString("message");
                    selectTab(0);
                    Constant.alert(OutStoreListActivity.this, "拆单成功，返回查看所拆新单");
                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    String message = msg.getData().getString("message");
                    Toast.makeText(OutStoreListActivity.this, message, Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreListActivity.this, "拆单出错！", Toast.LENGTH_LONG).show();
                    break;


                default:
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.btn_add:
                split();
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
        mFragNoOutStore = null;
        mFragHasOutStore = null;
        switch (i) {
            //当选中点击的是微信的Tab时
            case 0:
                //设置微信的ImageButton为绿色
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                //如果微信对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFragNoOutStore == null) {
                    mFragNoOutStore = new NoOutStorageFragment();
                    mFragNoOutStore.setArguments(fragbundle);

                    transaction.add(R.id.id_content, mFragNoOutStore);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFragNoOutStore);
                }

                break;

            case 1:
                Log.i("tab1","tab1-----");
                mTab1.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unselecte_bg));
                mTab2.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_selected_bg));
                if (mFragHasOutStore == null) {
                    mFragHasOutStore = new HasOutStoreFragment();
                    mFragHasOutStore.setArguments(fragbundle);
                    transaction.add(R.id.id_content, mFragHasOutStore);
                } else {
                    transaction.show(mFragHasOutStore);
                }
                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }
    @SuppressLint("ResourceAsColor")
    private void hideFragments(FragmentTransaction transaction) {
        Log.i("hideFragments","hideFragments");
        if (mFragNoOutStore != null) {

            transaction.hide(mFragNoOutStore);
        }

        if (mFragHasOutStore != null) {

            transaction.hide(mFragHasOutStore);
        }
    }
}
