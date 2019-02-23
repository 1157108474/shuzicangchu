package com.example.admin.storage.activity.outstore;

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

import com.example.admin.storage.R;
import com.example.admin.storage.activity.buyer.BuyerReceiveActivity;
import com.example.admin.storage.adapters.BillAdapter;
import com.example.admin.storage.baseclass.Bill;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OutStoreActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener{
    Button btnQrCode,btnAdd; // 扫码
    TextView tvResult; // 结果
    private ListView listView;
//    private CustomPopupWindow mPop;
    //    private ArrayAdapter<List<String>> adapter0;
    private BillAdapter adapter;
    private List<Bill> mData = new ArrayList<Bill>();
    private final int GET_OK = 20;
    private final int GET_FAIL = 21;
    private final int GET_ERROR = 22;
    ProgressDialog progressDialog = null;
    private UserInfo userInfo;

    LinearLayout nolayout ;
    EditText search_et;
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
        title.setText("出库单");
        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setVisibility(View.VISIBLE);
        btn_add.setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
//        findViewById(R.id.id_scan_bt).setOnClickListener(this);
        search_et = findViewById(R.id.search_et);

        nolayout = findViewById(R.id.nocontent);

        listView = (ListView) findViewById(R.id.id_listview_bill);

        adapter = new BillAdapter(this,mData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bill bill = mData.get(i);
                int st = bill.getStatus();
                Intent intent = new Intent(OutStoreActivity.this,OutStoreListActivity.class);

                intent.putExtra("ckId",bill.getBid());
                intent.putExtra("ckCode",bill.getOrderNumber());
                intent.putExtra("applyId",bill.getExtendint1());
                intent.putExtra("applyCode",bill.getExtendstring1());
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

//        getListData();

    }
    private void getListData(){
        final String ykywCode = search_et.getText().toString();
        progressDialog.setMessage("正在加载，请稍等......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Constant.GET_NO_FINISHED_OUTSTORE;//+"?appFlag=1&userId="+userInfo.id+"&page=1&limit=100000&code="+ykywCode;
                Log.i("获取未完成出库单url----",url);
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&page=1&limit=100000&code="+ykywCode;
                    String responseData = HTTP.queryPost(json,url);
                    Log.i("responseData------",responseData.toString());
                    if(responseData!=null && responseData!="null"){
                        JSONObject obj = new JSONObject(responseData);
                        if(obj.getInt("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("ckbills", obj.getString("data"));
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

    @Override
    protected void onResume() {
        super.onResume();
        mData.clear();
        getListData();
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
                Intent intent = new Intent(this,ApplicationListSelectActivity.class);
                startActivity(intent);
//                nolayout.setVisibility(View.GONE);
//                listView.setVisibility(View.VISIBLE);
//                for(int i =0;i<4;i++){
//                    Bill mBill = new Bill();
//                    mBill.setOrderNumber("WZLL201804201401");
//                    mBill.setUseUnit("使用单位");
//                    mBill.setCreateTime("2018-04-20 14:01:56");
//                    mData.add(mBill);
//                }

//                adapter.notifyDataSetChanged();

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
                    getListData();
//
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "刷新完成", Toast.LENGTH_LONG).show();

                    getListData();

                    break;
                case GET_OK:
                    progressDialog.dismiss();
                    String wuliaosData = msg.getData().getString("ckbills");
                    try {
                        JSONArray objArray = new JSONArray(wuliaosData);
//                        JSONArray objArray = JSONArray.fromObject(wuliaosData);
                        for(int i = 0; i < objArray.length(); i++){

                            JSONObject obj = new JSONObject(String.valueOf(objArray.getJSONObject(i)));

                            if (obj.get("extendint1") != null) {
                                Bill ml = new Bill();
                                ml.setBid(obj.getInt("id"));
                                ml.setExtendstring1(obj.getString("slCode"));//申领单号
                                ml.setOrderNumber(obj.getString("code"));
                                ml.setCreator(obj.getString("personName"));
                                ml.setCreateTime(Constant.getDateToString(obj.getLong("createDate"),"yyyy-MM-dd HH:mm:ss"));
                                ml.setExtendint1(obj.getInt("extendint1"));
                                mData.add(ml);

                            }

                        }
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(OutStoreActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreActivity.this, "查询未完成接收单失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreActivity.this, "查询未完成接收单出错！", Toast.LENGTH_LONG).show();
                    break;



                default:
                    break;
            }
        }
    };
}
