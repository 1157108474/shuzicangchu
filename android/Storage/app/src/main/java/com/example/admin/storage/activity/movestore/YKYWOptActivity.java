package com.example.admin.storage.activity.movestore;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.admin.storage.R;
import com.example.admin.storage.activity.intostore.IntoStoreOptActivity;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.MaterialModel;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class YKYWOptActivity extends Activity implements View.OnClickListener{

    String scanResult;
    private TextView encode,describe,provider,stockNum,serial,is_serial,unit,pre_storage_location,pre_storage;
    private TextView move_num_tv,target_storage_location_tv;
    private EditText target_storage_location_et,move_num_et;
    private Button btn_save;
    String materialykyw;
    ProgressDialog progressDialog = null;
    private UserInfo userInfo;
    private int sheetId;
    JSONObject materialObj=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ykyw_intostore_opt);
        initView();
        initData();
    }
    public void initView(){
        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(true);//设置可否用back键关闭对话框
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        btn_save = findViewById(R.id.id_btn_save);
        btn_save.setOnClickListener(this);
        findViewById(R.id.id_btn_scan).setOnClickListener(this);
        encode = findViewById(R.id.id_encode);
        describe = findViewById(R.id.id_describe);
        provider = findViewById(R.id.gongyingshang);
        stockNum = findViewById(R.id.id_count);
        unit = findViewById(R.id.id_unit);
        serial = findViewById(R.id.id_serial);
        is_serial = findViewById(R.id.id_is_serial);
        pre_storage_location = findViewById(R.id.id_old_storage_location);
        pre_storage = findViewById(R.id.id_pre_storage);
        move_num_tv = findViewById(R.id.id_move_num);
        move_num_et = findViewById(R.id.id_move_num_et);

        target_storage_location_tv = findViewById(R.id.id_target_storage_location_tv);
        target_storage_location_et = findViewById(R.id.id_target_storage_location_et);


        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("移入操作");

        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("materialykyw");
        materialykyw = intent.getStringExtra("materialykyw");
        sheetId = intent.getIntExtra("sheetId",0);
Log.i("materialykyw-----",materialykyw);
//        JSONObject obj = null;
        try {
            materialObj = new JSONObject(materialykyw);
            encode.setText(materialObj.getString("materialCode"));
//        tv.setText(bundle.getString("encode"));
            describe.setText(materialObj.getString("description"));
            provider.setText(materialObj.getString("providerDepName"));
            stockNum.setText(materialObj.getString("isCount"));
            unit.setText(materialObj.getString("detailUnitName"));
            if(materialObj.getInt("enableSn") == 0){
                is_serial.setText("不启用");
            }else{
                is_serial.setText("启用");
            }

            serial.setText(materialObj.getString("snCode"));
            pre_storage.setText(materialObj.getString("houseName"));
            pre_storage_location.setText(materialObj.getString("storeLocationCode"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        JSONObject obj = JSONObject.fromObject(materialykyw);



//        move_num_tv.setText(obj.getString("isCount"));
    }
    public void initData(){
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
    }
//    public JSONObject formatData(){

//        JSONObject obj = null;
//        Log.i("materialykyw  000------",materialykyw);
//        try {
//            obj = new JSONObject(materialykyw);
//            JSONObject subObj = new JSONObject();
//            subObj.put("sheetId",obj.getInt("sheetId"));
//            subObj.put("sheetDetailId",obj.getInt("sheetDetailId"));
//            subObj.put("detailCount",Integer.parseInt(move_num_et.getText().toString()));
//            subObj.put("storeLocationCode",target_storage_location_et.getText().toString());
//            subObj.put("sheetId",obj.getInt("sheetId"));
//            return subObj;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }

//    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_btn_save:
                saveYKYW();
                break;
            case R.id.id_btn_scan:
                startQrCode();
                break;

            default:
                break;
        }
    }
    private void saveYKYW(){
        if (target_storage_location_et.getText().toString().equals("")) {
            Toast.makeText(YKYWOptActivity.this, "请录入目标库位！", Toast.LENGTH_SHORT).show();
            return ;
        }
        if (move_num_et.getText().toString().equals("")) {
            Toast.makeText(YKYWOptActivity.this, "请录入移动数量！", Toast.LENGTH_SHORT).show();
            return ;
        }
        final String count = move_num_et.getText().toString();
        if(Double.parseDouble(count)>Double.parseDouble(stockNum.getText().toString())){
            Toast.makeText(YKYWOptActivity.this,"输入数量不能超过库存数量",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("正在保存，请稍等......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = Constant.SAVE_YKYW;//+"?appFlag=1&userId="+userInfo.id+"&details="+ jsonArray.toString();
                try {
                    Message message = new Message();
//                    JSONObject obj = new JSONObject(materialykyw);
                    String json = "appFlag=1&userId="+userInfo.id+"&sheetId="+sheetId+"&sheetDetailId="+materialObj.getInt("id")+
                            "&detailCount="+count+"&storeLocationCode="+target_storage_location_et.getText().toString();
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("ykywopt---responseDa--",responseData);

                    // 定义okhttp
//                    OkHttpClient okHttpClient_post_kv = new OkHttpClient();
//                    // 定义请求体
//                    // 执行okhttp
//                    Log.i("details---",jsonArray.toString());
//
////                    RequestBody body = FormBody.create(MediaType.parse("application/json; charset=GBK"), jsonObject.toString());
//                    RequestBody body = new FormBody.Builder()
//                            .add("appFlag","1")//添加参数体
//                            .add("userId","3284")
//                            .add("details",jsonArray.toString())
//                            .build();
//                    Request request = new Request.Builder()
//                            .post(body) //请求参数
//                            .url(address)
//                            .build();
//                    Response response = okHttpClient_post_kv.newCall(request).execute();
//                    String responseData = response.body().string();
                    Log.i("添加明细----",responseData);
                    if(responseData!=null){
                        JSONObject obj1 = new JSONObject(responseData);
//                        JSONObject obj1 = JSONObject.fromObject(responseData);
                        if(obj1.getInt("status")==1){

                            message.what = Constant.SUCCESS;
                        }else{
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("message", obj1.getString("message"));
//                            bundle.putInt("count", obj1.getInt("count"));
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
                    progressDialog.dismiss();
                    Message msg = new Message();
                    msg.what = Constant.NETWORK_ERROR;
//                    msg.setData(b);

                }
            }
        }).start();
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case Constant.SUCCESS:
                    progressDialog.dismiss();
                    target_storage_location_et.setVisibility(View.GONE);
                    target_storage_location_tv.setVisibility(View.VISIBLE);
                    move_num_et.setVisibility(View.GONE);
                    move_num_tv.setVisibility(View.VISIBLE);
                    btn_save.setVisibility(View.GONE);

                    Toast.makeText(YKYWOptActivity.this, "保存成功！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    String buysData = msg.getData().getString("message");
                    Toast.makeText(YKYWOptActivity.this, buysData, Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(YKYWOptActivity.this, "保存出错！", Toast.LENGTH_LONG).show();

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
            //将扫描出的信息显示出来
            target_storage_location_et.setText(scanResult);
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
