package com.example.admin.storage.activity.storequery;

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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.activity.buyer.BuyerListSelectActivity;
import com.example.admin.storage.activity.check.CheckStoreActivity;
import com.example.admin.storage.activity.check.CheckStoreDetailActivity;
import com.example.admin.storage.adapters.CheckStoreAdapter;
import com.example.admin.storage.adapters.StoreQueryAdapter;
import com.example.admin.storage.baseclass.CheckBill;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class StoreQueryActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener{
    Button btnQrCode,btnAdd; // 扫码
    TextView tvResult; // 结果
    private PullToRefreshListView listView;
    private StoreQueryAdapter adapter;
    private List<Material> mData = new ArrayList<Material>();
    private ProgressDialog progressDialog;
    private UserInfo userInfo;

    LinearLayout nolayout ;
    EditText search_et;
    private int page = 1;
    private int pageSize = 20;
    private int totalPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_query);

        initView();
        initData();

    }
    private void initView() {
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        findViewById(R.id.id_scan_bt).setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
        search_et= findViewById(R.id.search_et);
        search_et.setHint("物料编码");
        nolayout = findViewById(R.id.nocontent);

        listView = (PullToRefreshListView) findViewById(R.id.material_listview);

        adapter = new StoreQueryAdapter(this,mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
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
        userInfo = JSONObject.parseObject(ustr,UserInfo.class);

        getListData();

    }
    private void getListData(){
        final String storelocationcode;
        final String materialcode;
        String text = search_et.getText().toString();
        if(text.contains("-")){
            storelocationcode=text;
            materialcode="";
        }else{
            storelocationcode="";
            materialcode=text;
        }
        progressDialog.setMessage("正在加载，请稍后...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.QUERY_ALL_M;//+"?userId="+userInfo.id+"&storelocationcode="+storelocationcode+"&materialcode="+materialcode+"&page="+page+"&limit="+pageSize;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&storelocationcode="+storelocationcode+"&materialcode="+materialcode+"&page="+page+"&limit="+pageSize;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("库存查询 单---",responseData.toString());
                    if(responseData!=null && responseData!= "null"){
                        JSONObject obj = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInteger("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("meteriallist", obj.getString("data"));
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
                page = 1;
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
//                Toast.makeText(StoreQueryActivity.this, "刷新完成", Toast.LENGTH_LONG).show();
                handler.sendEmptyMessage(0);
            }
        }, 200);
    }

    @Override
    public void onLoadingMore() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 200);
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
//                    Log.i("xialashuaxin--","00000000");
                    page = 1;
                    listView.setLoadMore(true);
                    mData.clear();
                    getListData();
                    break;
                case 1:
                    if(page == totalPage){
                        Toast.makeText(StoreQueryActivity.this, "没有更多数据", Toast.LENGTH_LONG).show();
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
                    String pandianData = msg.getData().getString("meteriallist");
                    int cout = msg.getData().getInt("count");
                    if(cout % pageSize > 0){
                        totalPage = cout / pageSize + 1;
                    }else{
                        totalPage = cout / pageSize;
                    }
                    if(pandianData == "null"){
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
                            JSONObject obj = JSONObject.parseObject(objArray.getJSONObject(i).toString());
//                            JSONObject obj = objArray.getJSONObject(i);
                            Material mMaterial = new Material();
                            mMaterial.setEncode(obj.getString("materialcode"));
                            mMaterial.setDescribe(obj.getString("description"));
                            mMaterial.setStoreName(obj.getString("housename"));
                            mMaterial.setStoreLocation(obj.getString("storelocationcode"));
                            mMaterial.setStockNum(Constant.formatCount(obj.getString("storecount")));
                            mMaterial.setUnit(obj.getString("detailunitname"));
//                            Log.i("gongyings----",String.valueOf(obj.get("gysname")));
                            mMaterial.setOwner(obj.get("gysname")==null?"":obj.getString("gysname"));
                            mData.add(mMaterial);
                        }
                        adapter.notifyDataSetChanged();
//                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(StoreQueryActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(StoreQueryActivity.this, "查询未完成接收单失败！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(StoreQueryActivity.this, "查询未完成接收单出错！", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };
}
