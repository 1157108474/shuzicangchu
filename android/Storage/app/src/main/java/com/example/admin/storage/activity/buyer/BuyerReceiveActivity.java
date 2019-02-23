package com.example.admin.storage.activity.buyer;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.storage.adapters.BillAdapter;
import com.example.admin.storage.adapters.ReceiptAdapter;
import com.example.admin.storage.baseclass.Bill;
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
import com.example.admin.storage.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuyerReceiveActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener,CustomPopupWindow.OnItemClickListener{
    Button btnQrCode; // 扫码
    TextView tvResult; // 结果
    private ListView listView;
    private CustomPopupWindow mPop;
    //    private ArrayAdapter<List<String>> adapter0;
    private BillAdapter adapter;
    private List<Bill> mData = new ArrayList<Bill>();
    private String wuliaosData ;
    public LinearLayout nolayout ;
    public EditText search_et;
    private ProgressDialog progressDialog;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_unfinished);

        initView();
        initData();

    }
    private void initView() {
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        TextView title = findViewById(R.id.id_title_tv);
        title.setText("接收单");
//        findViewById(R.id.search_et).setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setVisibility(View.VISIBLE);
        btn_add.setOnClickListener(this);
        search_et = findViewById(R.id.search_et);
//        search_et.setCursorVisible(false);      //设置输入框中的光标不可见
//        search_et.setFocusable(false);           //无焦点
//        search_et.setFocusableInTouchMode(false);     //触摸时也得不到焦点
//        search_et.setOnClickListener(this);
        nolayout = findViewById(R.id.nocontent);

        listView = (ListView) findViewById(R.id.id_listview_bill);

        adapter = new BillAdapter(this,mData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                if(listView.isMoved()){
//                    return;
//                }
//                Toast.makeText(BuyerReceiveActivity.this, "Click item" + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BuyerReceiveActivity.this,ReceiveDetailActivity.class);
                //用Bundle携带数据
                Bundle bundle=new Bundle();
                Bill mBill = new Bill();
                mBill = mData.get(i);
                bundle.putString("receiptOrder",mBill.getOrderNumber());
                bundle.putInt("receiptOrderid",mBill.getBid());
                bundle.putString("buyOrderCode",mBill.getExtendstring1());
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
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
//        userInfo = Settings.getInstance().getUserInfo();
//        getListData("");

    }
    public void getListData(final String order){
        progressDialog.setMessage("正在加载，请稍后...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.GET_NO_FINISHED_RECEIVE;//+"?appFlag=1&creator="+userInfo.id+"&code="+order;
                try {
                    Message message = new Message();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("creator",userInfo.id);
                    jsonObject.put("code",order);
                    String json = "appFlag=1&creator="+userInfo.id+"&code="+order;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("获取未完成的接收单---",responseData.toString());
                    if(responseData!=null && responseData!= "null"){
                        JSONObject obj = new JSONObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInt("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("wuliaos", obj.getString("data"));
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

    @Override
    public void setOnItemClick(View v) {
        switch(v.getId()){
            case R.id.id_btn_take_photo:
                mPop.dismiss();


                search_et.setCursorVisible(true);      //设置输入框中的光标不可见
                search_et.setFocusable(true);           //无焦点
                search_et.requestFocus();
                this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(search_et, 0);
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//                search_et.setFocusableInTouchMode(false);     //触摸时也得不到焦点
                Toast.makeText(getApplicationContext(), "拍照", Toast.LENGTH_LONG).show();

                break;
            case R.id.id_btn_select:
                mPop.dismiss();
//                Toast.makeText(getApplicationContext(),"从相册中选择", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_btn_cancelo:
                mPop.dismiss();
                break;
        }
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
        getListData("");
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
//                    Toast.makeText(this,"请输入单号",Toast.LENGTH_LONG);
//                    return;
//                }
                mData.clear();
                getListData(search_et.getText().toString());
                break;
            case R.id.id_scan_bt:
                startQrCode();
                break;

            case R.id.btn_add:
                Intent intent = new Intent(this,BuyerListSelectActivity.class);
                startActivity(intent);
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
                    Toast.makeText(getApplicationContext(), "刷新完成", Toast.LENGTH_LONG).show();
                    mData.clear();
                    getListData("");
//
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "刷新完成", Toast.LENGTH_LONG).show();
//                    mData.clear();
                    getListData("");
//                    listView.setLoadMore(false);
//                    getListData();
                    break;
                case Constant.SUCCESS:
                    progressDialog.dismiss();
                    wuliaosData = msg.getData().getString("wuliaos");
                    try {
                        JSONArray objArray = new JSONArray(wuliaosData);
//                        JSONArray objArray = JSONArray.fromObject(wuliaosData);
                        for(int i = 0; i < objArray.length(); i++){
                            Bill ml = new Bill();
                            JSONObject obj = new JSONObject(String.valueOf(objArray.getJSONObject(i)));
//                            JSONObject obj = objArray.getJSONObject(i);
                            ml.setBid(obj.getInt("id"));
                            ml.setExtendstring1(obj.getString("ordernum"));
                            ml.setOrderNumber(obj.getString("code"));
                            ml.setCreator(obj.getString("personname"));
                            ml.setCreateTime(Constant.getDateToString(obj.getLong("createdate"),"yyyy-MM-dd HH:mm:ss"));

                            mData.add(ml);
                        }
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(BuyerReceiveActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(BuyerReceiveActivity.this, "查询未完成接收单失败！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(BuyerReceiveActivity.this, "查询未完成接收单出错！", Toast.LENGTH_LONG).show();
                    break;


                default:
                    break;
            }
        }
    };
}
