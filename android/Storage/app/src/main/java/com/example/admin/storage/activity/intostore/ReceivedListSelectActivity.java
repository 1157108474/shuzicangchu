package com.example.admin.storage.activity.intostore;

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

import com.example.admin.storage.R;
import com.example.admin.storage.activity.buyer.BuyerListSelectActivity;
import com.example.admin.storage.activity.buyer.ReceiveDetailActivity;
import com.example.admin.storage.adapters.IntostoreAdapter;
import com.example.admin.storage.baseclass.Receipt;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.CustomPopupWindow;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReceivedListSelectActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener{
    Button btnQrCode; // 扫码
    TextView tvResult; // 结果
    private PullToRefreshListView listView;
    private CustomPopupWindow mPop;
    private IntostoreAdapter adapter;
    private List<Receipt> mData = new ArrayList<Receipt>();
    private String selectedJieshou;
    private final int GET_OK = 20;
    private final int GET_FAIL = 21;
    private final int GET_ERROR = 22;
    private final int CREATE_OK = 10;
    private final int CREATE_FAIL = 11;
    private final int CREATE_ERROR = 12;
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
        setContentView(R.layout.activity_buyer_receive);

        initView();
        initData();

    }
    private void initView() {
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
//        findViewById(R.id.search_et).setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
        TextView title = findViewById(R.id.id_title_tv);
        title.setText("选择接收单");
//        Button btn_add = findViewById(R.id.btn_add);
//        btn_add.setVisibility(View.VISIBLE);
//        btn_add.setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
        search_et = findViewById(R.id.search_et);
        nolayout = findViewById(R.id.nocontent);

        listView = (PullToRefreshListView) findViewById(R.id.receipt_listview);

        adapter = new IntostoreAdapter(this,mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.i("buyerReceiveActivity",listView.isMoved()+"itemclick---");
                if(listView.isMoved()){
                    return;
                }
                Receipt rt = mData.get(i-1);
                selectedJieshou = rt.getJieshouOrder();
                createIntoStore(rt.getRid());
//                Toast.makeText(ReceivedListSelectActivity.this, "Click item" + i, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ReceivedListSelectActivity.this,IntoStoreListActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("receiptOrder","接收单号");
//                intent.putExtras(bundle);
//                startActivity(intent);
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
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
//        userInfo = Settings.getInstance().getUserInfo();
//        getListData();

    }
    public void createIntoStore(final String orderid){

        progressDialog.setMessage("正在创建入库单...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.INSERT_INTOSTORE;//+"?userId="+userInfo.id+"&orderId="+order+"&menuCode=wzjs_sheet";
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&orderId="+orderid+"&appFlag=1&menuCode=receipt_details";
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("创建入库单返回数据------",responseData);
                    if(responseData!=null && !responseData.equals("null")){
                        JSONObject obj1 = new JSONObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj1.getInt("status")==1){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("WZRK", obj1.getString("data"));
                            message.setData(bundle);
                            message.what = CREATE_OK;
                        }else{
                            message.what = CREATE_FAIL;
                        }
//                        responseData
                    }else{
                        message.what = CREATE_ERROR;
                    }
                    handler.sendMessage(message);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = CREATE_ERROR;
//                    msg.setData(b);
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    private void getListData(){
        final String order = search_et.getText().toString();//接收单号
        progressDialog.setMessage("正在查询接收单...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.QUERY_RECEIPT;//+"?creator="+userInfo.id+"&ordernum="+order+"&page="+page+"&limit="+pageSize;
                try {
                    Message message = new Message();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userId",userInfo.id);
                    jsonObject.put("ordernum",order);
                    String json = "appFlag=1&userId="+userInfo.id+"&code="+order+"&page="+page+"&limit="+pageSize;
//                    Log.i("cha jie shou dan----",address+json);
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("接收单列表---",responseData);

                    if(responseData!=null && responseData!="null"){
                        JSONObject obj = new JSONObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInt("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("receipts", obj.getString("data"));
                            bundle.putInt("count", obj.getInt("count"));
                            message.setData(bundle);
                            message.what = GET_OK;
                        }else{
                            message.what = GET_FAIL;
                        }
//                        responseData
                    }else{
                        message.what = GET_ERROR;
                    }
                    handler.sendMessage(message);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = GET_ERROR;
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
    protected void onResume() {
        super.onResume();
        mData.clear();
        getListData();
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
                        Toast.makeText(ReceivedListSelectActivity.this, "没有更多数据", Toast.LENGTH_LONG).show();
                        listView.onRefreshComplete();
                        listView.setLoadMore(false);

                    }else {
                        page++;
                        getListData();
                    }
                    break;
                case GET_OK:
                    progressDialog.dismiss();

                    String buysData = msg.getData().getString("receipts");

                    int cout = msg.getData().getInt("count");
                    if(cout % pageSize > 0){
                        totalPage = cout / pageSize + 1;
                    }else{
                        totalPage = cout / pageSize;
                    }
//                    Log.i("获取到的采购订单列表-----",buysData);
                    try {
                        JSONArray objArray = new JSONArray(buysData);
                        if(objArray.length()==0){
                            nolayout.setVisibility(View.VISIBLE);
                            return;
                        }
                        for(int i = 0; i < objArray.length(); i++){
                            Receipt ml = new Receipt();
                            JSONObject obj = new JSONObject(String.valueOf(objArray.getJSONObject(i)));
//                            JSONObject obj = objArray.getJSONObject(i);

                            ml.setRid(obj.getString("id"));
                            ml.setOrder(obj.getString("ordernum"));
                            ml.setJieshouOrder(obj.getString("code"));
                            ml.setJieshouTime(Constant.getDateToString(obj.getLong("createdate"),"yyyy-MM-dd HH:mm:ss"));
                            ml.setProvider(obj.getString("extendstring1"));
//                            ml.setOrderType(obj.getString("ordertype"));
//                            ml.setFafanghao(obj.getString("issuecode"));//debug

                            mData.add(ml);
                        }
                        adapter.notifyDataSetChanged();
//                        listView.setAdapter(adapter);
                        listView.onRefreshComplete();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        listView.onRefreshComplete();
                        Toast.makeText(ReceivedListSelectActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(ReceivedListSelectActivity.this, "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(ReceivedListSelectActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
                    break;
                case CREATE_OK:
                    progressDialog.dismiss();
                    String receivebill = msg.getData().getString("WZRK");
                    Log.i("receivebill=-------",receivebill);
                    try {
                        JSONObject obj = new JSONObject(receivebill);
                        Intent intent = new Intent(ReceivedListSelectActivity.this,IntoStoreListActivity.class);
//
                        intent.putExtra("jsCode",selectedJieshou);
                        intent.putExtra("intoCode",obj.getString("code"));
                        intent.putExtra("intoId",obj.getInt("id"));
//                        intent.putExtra("buyOrder",obj.getString("orderNum"));
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ReceivedListSelectActivity.this, "数据解析出错！", Toast.LENGTH_LONG).show();

                    }


                    break;
                case CREATE_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(ReceivedListSelectActivity.this, "添加入库单失败！", Toast.LENGTH_LONG).show();

                    break;
                case CREATE_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(ReceivedListSelectActivity.this, "添加入库单出错！", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };
}
