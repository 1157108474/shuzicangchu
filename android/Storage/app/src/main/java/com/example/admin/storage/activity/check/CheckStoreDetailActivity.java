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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.activity.intostore.IntoStoreListActivity;
import com.example.admin.storage.adapters.IntostoreAdapter;
import com.example.admin.storage.adapters.MaterialCheckAdapter;
import com.example.admin.storage.baseclass.CheckBill;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.Receipt;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.CustomPopupWindow;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;


import java.util.ArrayList;
import java.util.List;

public class CheckStoreDetailActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener{
    Button btnQrCode; // 扫码
    TextView tvResult; // 结果
    private PullToRefreshListView listView;
    private MaterialCheckAdapter adapter;
    private List<Material> mData = new ArrayList<Material>();
    private int sheetId;
    private String storage;
    private LinearLayout nolayout ;
    private EditText search_et;
    private TextView checked_num_tv,nocheck_num_tv;
    private ProgressDialog progressDialog;
    private UserInfo userInfo;
    private int page = 1;
    private int pageSize = 20;
    private int totalPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detail);

        initView();
        initData();

    }
    private void initView() {

        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);

        findViewById(R.id.id_search_bt).setOnClickListener(this);
        findViewById(R.id.id_scan_bt).setOnClickListener(this);

        search_et = findViewById(R.id.search_et);
        nolayout = findViewById(R.id.nocontent);
        checked_num_tv = (TextView)findViewById(R.id.id_checked_num) ;
        nocheck_num_tv = (TextView)findViewById(R.id.id_no_check_num) ;
        findViewById(R.id.btn_add).setOnClickListener(this);

        listView = (PullToRefreshListView) findViewById(R.id.receipt_listview);

        adapter = new MaterialCheckAdapter(this,mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.i("buyerReceiveActivity",listView.isMoved()+"itemclick---");
                if(listView.isMoved()){
                    return;
                }
                Material ml = new Material();
                Log.i("itemi==",String.valueOf(i));
                ml = mData.get(i-1);
//                Toast.makeText(CheckStoreDetailActivity.this, "Click item" + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CheckStoreDetailActivity.this,MaterialCheckActivity.class);
                String mstr = JSONObject.toJSONString(ml);

                intent.putExtra("materialcheck", mstr);
//                intent.putExtras(bundle);
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
        sheetId = getIntent().getIntExtra("sheetid",0);
        storage = getIntent().getStringExtra("storage");
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");

//        JSONObject jsonObject = JSONObject.parseObject(ustr);
        userInfo = JSONObject.parseObject(ustr,UserInfo.class);
//        userInfo = Settings.getInstance().getUserInfo();
//        getListData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mData.clear();
        page = 1;
        getListData();
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
                String address = Constant.QUERY_PANDIAN_DETAIL;//+"?sheetId="+sheetId+"&page="+page+"&limit="+pageSize;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&sheetId="+sheetId+"&page="+page+"&limit="+pageSize;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("pandian 单---",responseData.toString());
                    if(responseData!=null && responseData!= "null" && responseData!= "[]"){
                        JSONObject obj = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInteger("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("pandianData", responseData);
//                            bundle.putInt("count", obj);
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
            case R.id.btn_add:
                Intent it = new Intent(CheckStoreDetailActivity.this,AddMaterialCheckActivity.class);
                it.putExtra("sheetId",sheetId);
                startActivity(it);
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
        public String toString() {
            return super.toString();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mData.clear();
                    page = 1;
                    listView.setLoadMore(true);
                    getListData();
                    break;
                case 1:
                    if(page == totalPage){
                        Toast.makeText(CheckStoreDetailActivity.this, "没有更多数据", Toast.LENGTH_LONG).show();
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
                    String pandianData = msg.getData().getString("pandianData");

                    try {
                        JSONObject jsonObject = JSONObject.parseObject(pandianData);
                        int yipanNum = jsonObject.getInteger("ypCount");
                        Log.i("已盘点---",String.valueOf(yipanNum));
                        checked_num_tv.setText(String.valueOf(yipanNum));
                        int total = jsonObject.getInteger("detailCount");
                        int nopanNum = total - yipanNum;
                        nocheck_num_tv.setText(String.valueOf(nopanNum));
                        if(total % pageSize > 0){
                            totalPage = total / pageSize + 1;
                        }else{
                            totalPage = total / pageSize;
                        }


                        String pandians = jsonObject.getString("data");
                        if(pandians == "null" || pandians==null){
                            nolayout.setVisibility(View.VISIBLE);
                            break;
                        }
                        JSONArray objArray = JSONArray.parseArray(pandians);
                        if(objArray.size() == 0){
                            nolayout.setVisibility(View.VISIBLE);
                            break;
                        }
                        for(int i = 0; i < objArray.size(); i++){
                            JSONObject obj = objArray.getJSONObject(i);
                            Log.i("status--",String.valueOf(obj.getInteger("stockStatus")));
                            if(obj.getInteger("stockStatus")==0){
                                Material mMaterial = new Material();
                                mMaterial.setMid(obj.getInteger("id"));
                                mMaterial.setEncode(obj.getString("materialCode"));
                                mMaterial.setDescribe(obj.getString("description"));
                                mMaterial.setProvider("");
                                mMaterial.setStockNum(Constant.formatCount(obj.getString("sysCount")));
                                mMaterial.setUnit(obj.getString("detailUnitName"));
                                mMaterial.setStoreName(storage);
                                mMaterial.setStoreLocation(obj.getString("storeLocationCode"));
                                mData.add(mMaterial);
                            }

                        }
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(CheckStoreDetailActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(CheckStoreDetailActivity.this, "查询盘点明细失败！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(CheckStoreDetailActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };
}
