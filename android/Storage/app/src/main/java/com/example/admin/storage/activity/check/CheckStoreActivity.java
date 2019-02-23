package com.example.admin.storage.activity.check;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.activity.buyer.BuyerListSelectActivity;
import com.example.admin.storage.activity.buyer.BuyerReceiveActivity;
import com.example.admin.storage.adapters.CheckStoreAdapter;
import com.example.admin.storage.baseclass.Bill;
import com.example.admin.storage.baseclass.CheckBill;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CheckStoreActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener{
    Button btnQrCode,btnAdd; // 扫码
    TextView tvResult; // 结果
    private PullToRefreshListView listView;
//    private CustomPopupWindow mPop;
    //    private ArrayAdapter<List<String>> adapter0;
    private CheckStoreAdapter adapter;
    private List<CheckBill> mData = new ArrayList<CheckBill>();
    private ProgressDialog progressDialog;

    LinearLayout nolayout ;
    EditText search_et;
    private UserInfo userInfo;
    private int page = 1;
    private int pageSize = 20;
    private int totalPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        initView();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mData.clear();
        getListData();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("库存盘点");

        findViewById(R.id.id_back_btn).setOnClickListener(this);


        nolayout = findViewById(R.id.nocontent);
        listView = (PullToRefreshListView) findViewById(R.id.check_listview);
        adapter = new CheckStoreAdapter(this,mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);
        listView.setLoadMore(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
                if(listView.isMoved()){
                    return;
                }
//                Toast.makeText(CheckStoreActivity.this,"itme--"+i,Toast.LENGTH_SHORT).show();
                CheckBill cb = mData.get(i-1);
                Intent intent = new Intent(CheckStoreActivity.this,CheckStoreDetailActivity.class);
                //用Bundle携带数据
                Bundle bundle=new Bundle();
                //传递name参数为tinyphp
                bundle.putInt("sheetid",cb.getId());
                bundle.putString("storage",cb.getStorage());
//                bundle.putInt("receiptId",1 );
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return true;
            }


        });
    }
    private void initData() {
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");

//        JSONObject jsonObject = JSONObject.parseObject(ustr);
        userInfo = JSONObject.parseObject(ustr,UserInfo.class);
        Log.i("userInfo.code---",userInfo.code);
//        getListData();

    }
    private void getListData(){
//        MyAsyncTask myAsyncTask = new MyAsyncTask();
//        myAsyncTask.execute("www.baidu.com/xxx.jpg");
        progressDialog.setMessage("正在加载，请稍后...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.QUERY_ALL_PANDIAN;//+"?appFlag=1userId="+userInfo.id+"&page="+page+"&limit="+pageSize;
                try {
                    Message message = new Message();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userId",userInfo.id);
//                    jsonObject.put("code",order);
//                    Log.i("获取未完成接收单order----",order);
                    String json = "appFlag=1&userId="+userInfo.id+"&page="+page+"&limit="+pageSize;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("pandian 单---",responseData.toString());
                    if(responseData!=null && responseData!= "null"){
                        JSONObject obj = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInteger("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("pandians", obj.getString("data"));
                            bundle.putInt("count", obj.getInteger("count"));
                            message.setData(bundle);
                            message.what = Constant.SUCCESS;
                        }else{
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


    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            search_et.setText(scanResult);
//            Toast.makeText(this, scanResult, Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_search_bt:
                if(mData.size()==0){
                    return;
                }
//                if(search_et.getText().equals("")){
//                    Toast.makeText(this,"请输入单号或扫描录入单号",Toast.LENGTH_LONG);
//                    return;
//                }
                mData.clear();
                getListData();
                break;
            case R.id.id_scan_bt:
                startQrCode();
                break;


            default:
                break;
        }
    }

    @Override
    public void onDownPullRefresh() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }

    @Override
    public void onLoadingMore() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 1000);
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mData.clear();
                    page = 1;
                    listView.setLoadMore(true);
                    getListData();
//
                    break;
                case 1:
                    if(page == totalPage){
                        Toast.makeText(CheckStoreActivity.this, "没有更多数据", Toast.LENGTH_LONG).show();
                        listView.onRefreshComplete();
                        listView.setLoadMore(false);

                    }else {
                        page++;
                        getListData();
                    }
                    break;
                case Constant.SUCCESS:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    String pandianData = msg.getData().getString("pandians");
                    int cout = msg.getData().getInt("count");
                    if(cout % pageSize > 0){
                        totalPage = cout / pageSize + 1;
                    }else{
                        totalPage = cout / pageSize;
                    }
                    if(pandianData.equals("null")||pandianData==null){
                        nolayout.setVisibility(View.VISIBLE);
                        break;
                    }
                    try {
                        JSONArray objArray = new JSONArray(pandianData);
                        if(objArray.length() == 0){
                            nolayout.setVisibility(View.VISIBLE);
                            break;
                        }
                        for(int i = 0; i < objArray.length(); i++){
                            CheckBill ml = new CheckBill();
                            org.json.JSONObject obj = new org.json.JSONObject(String.valueOf(objArray.getJSONObject(i)));
//                            JSONObject obj = objArray.getJSONObject(i);
                            ml.setId(obj.getInt("id"));
                            ml.setOrderNumber(obj.getString("code"));
                            ml.setStorage(obj.getString("houseName"));
                            ml.setTotalNum(Constant.formatCount(obj.getString("sumDetailCount")));
                            ml.setAlreadyNum(Constant.formatCount(obj.getString("extendInt3")));
                            ml.setCreateTime(Constant.getDateToString(obj.getLong("createDate"),"yyyy-MM-dd HH:mm:ss"));

                            mData.add(ml);
                        }
                        adapter.notifyDataSetChanged();

//                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(CheckStoreActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(CheckStoreActivity.this, "查询未完成接收单失败！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(CheckStoreActivity.this, "查询未完成接收单出错！", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };
}
