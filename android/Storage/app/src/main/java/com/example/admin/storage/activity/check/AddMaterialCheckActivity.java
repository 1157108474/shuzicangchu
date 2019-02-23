package com.example.admin.storage.activity.check;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.activity.intostore.ReceivedListSelectActivity;
import com.example.admin.storage.baseclass.Receipt;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddMaterialCheckActivity extends Activity implements View.OnClickListener {
    String scanResult;
    private EditText describe,serial,stocknum,unit,storage_location,storage;
    private EditText infect_num_tv;
    private EditText encode,infect_num_et;
    private ProgressDialog progressDialog;
    private final int SAVE_OK = 20;
    private final int SAVE_FAIL = 21;
    private final int SAVE_ERROR = 22;
    private final int GET_OK = 30;
    private final int GET_FAIL = 31;
    private final int GET_ERROR = 32;
    private UserInfo userInfo;
    private int sheetId;
    ArrayList<String> mItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_material_check);
        initView();
        initData();

    }
    public void initView(){
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        findViewById(R.id.id_btn_save).setOnClickListener(this);
        findViewById(R.id.id_btn_scan).setOnClickListener(this);
        encode = (EditText) findViewById(R.id.id_encode_et);
        describe = (EditText)findViewById(R.id.id_describe);
        serial = (EditText)findViewById(R.id.id_serial_et);
//        stocknum = findViewById(R.id.id_count);
        unit = (EditText)findViewById(R.id.id_unit);
        storage =(EditText) findViewById(R.id.id_storage);
        storage_location = (EditText)findViewById(R.id.id_storage_location);
//        infect_num_tv = findViewById(R.id.id_infect_num_tv);
        infect_num_et =(EditText) findViewById(R.id.id_infect_num_et);

        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("添加盘盈数据");
        spinner = (Spinner) findViewById(R.id.spinner);
        // 建立数据源
//        String[] mItems = getStringArray(R.array.languages);
        // 建立Adapter并且绑定数据源

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

//                String[] languages = getResources().getStringArray(R.array.languages);
//                Toast.makeText(MainActivity.this, "你点击的是:"+languages[pos], 2000).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


    }
    private void initData(){
        mItems.add("请选择库房");
        sheetId = getIntent().getIntExtra("sheetId",0);
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = JSONObject.parseObject(ustr,UserInfo.class);
        getStorages();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_btn_save:
                addPanying();
                break;
            case R.id.id_btn_scan:
                startQrCode();
                break;

            default:
                break;
        }
    }
    private void getStorages(){
        progressDialog.setMessage("正在加载...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.GET_STORAGES;//+"?userId="+userInfo.id+"&sheetId="+sheetId+"&tagCode="+code+
                //"&detailUnitName="+unitStr+"&description="+describestr+"&sysCount="+infectNum+"&storeLocationCode="+storeLocStr+"&snCode="+serialStr;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId=1010&parentId=0&page=1&limit=10000";
                    String responseData = HTTP.queryPost(json,address);

                    Log.i("添加盘盈数据----",responseData);
                    if(responseData!=null){
                        JSONObject objres = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(objres.getInteger("code")==0){

                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("storages", objres.getString("data"));
//                            bundle.putInt("count", objres.getInt("count"));
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
                    msg.what = SAVE_ERROR;
//                    msg.setData(b);
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    private void addPanying(){

        final String infectNum = infect_num_et.getText().toString();
        final String code = encode.getText().toString();
        final String describestr = describe.getText().toString();
        final String unitStr = unit.getText().toString();
//        final String storeStr = storage.getText().toString();
        final String storeLocStr = storage_location.getText().toString();
        final String serialStr = serial.getText().toString();
        if(infectNum.equals("")||code.equals("")||describestr.equals("")||unitStr.equals("")||storeLocStr.equals("")){
            Constant.alert(this,"请填写完整数据");
            return;
        }
        final String storeStr = (String) spinner.getSelectedItem();
        if(storeStr.contains("选择库房")){
            Constant.alert(AddMaterialCheckActivity.this,"请选择库房");
            return;
        }
        progressDialog.setMessage("正在保存...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.ADD_PANYING;//+"?userId="+userInfo.id+"&sheetId="+sheetId+"&tagCode="+code+
                        //"&detailUnitName="+unitStr+"&description="+describestr+"&sysCount="+infectNum+"&storeLocationCode="+storeLocStr+"&snCode="+serialStr;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&sheetId="+sheetId+"&tagCode="+code+
                            "&detailUnitName="+unitStr+"&description="+describestr+"&sysCount="+infectNum+"&storeLocationCode="+storeLocStr+"&snCode="+serialStr;
                    String responseData = HTTP.queryPost(json,address);

//                    OkHttpClient okHttpClient_post_kv = new OkHttpClient();
//
////                    RequestBody body = FormBody.create(MediaType.parse("application/json; charset=GBK"), jsonObject.toString());
//                    RequestBody body = new FormBody.Builder()
//                            .add("sheetId",String.valueOf(sheetId))//添加参数体
//                            .add("userId",String.valueOf(userInfo.id))
//                            .add("tagCode",code)//添加参数体
//                            .add("detailUnitName",unitStr)
//                            .add("description",describestr)//添加参数体
//                            .add("sysCount", infectNum)
//                            .add("storeLocationCode", storeLocStr)//添加参数体
//                            .add("snCode",serialStr)
//                            .build();
//                    Request request = new Request.Builder()
//                            .post(body) //请求参数
//                            .url(address)
//                            .build();
//                    Response response = okHttpClient_post_kv.newCall(request).execute();
//                    String responseData = response.body().string();
                    Log.i("添加盘盈数据----",responseData);
                    if(responseData!=null){
                        JSONObject objres = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(objres.getInteger("status")==1){

                            message.what = SAVE_OK;
                        }else{
                            message.what = SAVE_FAIL;
                        }
//                        responseData
                    }else{
                        message.what = SAVE_ERROR;
                    }
                    handler.sendMessage(message);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = SAVE_ERROR;
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
                case GET_OK:
                    progressDialog.dismiss();

                    String buysData = msg.getData().getString("storages");


//                    Log.i("获取到的采购订单列表-----",buysData);
                    try {
                        JSONArray objArray = new JSONArray(buysData);
                       if(objArray.length()==0){
                           mItems.add("无");
                           return;
                       }
                        for(int i = 0; i < objArray.length(); i++){
                            org.json.JSONObject obj = new org.json.JSONObject(String.valueOf(objArray.getJSONObject(i)));
                            Log.i("name---",obj.getString("name"));
                            mItems.add(obj.getString("name"));
                        }
                        adapter=new ArrayAdapter<String>(AddMaterialCheckActivity.this,android.R.layout.simple_spinner_item, mItems);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //绑定 Adapter到控件
                        spinner.setAdapter(adapter);
//                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(AddMaterialCheckActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(AddMaterialCheckActivity.this, "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(AddMaterialCheckActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
                    break;
                case SAVE_OK:
                    progressDialog.dismiss();
                    encode.setText("");
                    describe.setText("");
                    serial.setText("");
                    unit.setText("");
                    storage.setText("");
                    storage_location.setText("");
                    infect_num_et.setText("");
                    Toast.makeText(AddMaterialCheckActivity.this,"添加成功",Toast.LENGTH_LONG).show();

                    break;
                case SAVE_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(AddMaterialCheckActivity.this, "添加失败！", Toast.LENGTH_LONG).show();

                    break;
                case SAVE_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(AddMaterialCheckActivity.this, "添加出错！", Toast.LENGTH_LONG).show();
                    break;


                default:
                    break;
            }
        }
    };
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
            //将扫描出的信息应该只有物料编码，然后根据物料编码从后台查询数据填入到本页面
            encode.setText(scanResult);
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
}
