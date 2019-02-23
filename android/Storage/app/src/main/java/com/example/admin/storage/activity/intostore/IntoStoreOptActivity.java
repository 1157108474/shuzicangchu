package com.example.admin.storage.activity.intostore;

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

import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.activity.buyer.ReceiveOptActivity;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.google.zxing.activity.CaptureActivity;

public class IntoStoreOptActivity extends Activity implements View.OnClickListener {
    String scanResult;
    private TextView encode,describe,provider,accept_num,canputaway_num,is_equipment,is_serial,unit,pre_storage_location,storage;
    private TextView storage_location_tv,into_num_tv;
    private EditText into_num_et,storage_location_et;
    private Button btn_save;
    private ProgressDialog progressDialog;
    private UserInfo userInfo;
    private Material mMaterial = new Material();
    private final int SAVE_OK = 20;
    private final int SAVE_FAIL = 21;
    private final int SAVE_ERROR = 22;
    int intoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intostore_opt);
        initView();
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
    }
    public void initView(){
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        btn_save = findViewById(R.id.id_btn_save);
        btn_save.setOnClickListener(this);
        findViewById(R.id.id_btn_scan).setOnClickListener(this);
        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("入库上架");

        encode = findViewById(R.id.id_encode);
        describe = findViewById(R.id.id_describe);
        provider = findViewById(R.id.gongyingshang);
        accept_num = findViewById(R.id.id_accept_num);
        canputaway_num = findViewById(R.id.id_canputaway_num);
        is_equipment = findViewById(R.id.id_is_equipment);
        is_serial = findViewById(R.id.id_is_serial);
        unit = findViewById(R.id.id_unit);
        pre_storage_location = findViewById(R.id.id_pre_storage_location);
        storage = findViewById(R.id.id_store_name);
        storage_location_tv = findViewById(R.id.id_storage_location_tv);
        into_num_tv = findViewById(R.id.id_into_num_tv);
        into_num_et = findViewById(R.id.id_into_num_et);
        storage_location_et = findViewById(R.id.id_storage_location_et);

        intoId = getIntent().getIntExtra("rkid",0);
        String wuliaoinfo = getIntent().getStringExtra("rkmaterial");
        Log.i("wuliaoinfo---",wuliaoinfo);
        JSONObject obj = JSONObject.parseObject(wuliaoinfo);
        mMaterial = JSONObject.parseObject(wuliaoinfo,Material.class);
        encode.setText(mMaterial.getEncode());
        describe.setText(mMaterial.getDescribe());
        provider.setText(mMaterial.getProvider());
        unit.setText(mMaterial.getUnit());
        accept_num.setText(mMaterial.getReceivedNum());
        canputaway_num.setText(mMaterial.getCanPutawayNum());
        is_equipment.setText(mMaterial.getIsEquipment()==1?"是":"否");
        is_serial.setText(mMaterial.getIsSerialInt()==0?"不启用":"启用");
        storage.setText(mMaterial.getStoreName());

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
            scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            storage_location_et.setText(scanResult);
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
    public void saveInto(){
       if(into_num_et.getText().toString().equals("") || storage_location_et.getText().toString().equals("")){
           Toast.makeText(IntoStoreOptActivity.this,"入库数量或库房为空",Toast.LENGTH_SHORT).show();
            return;
       }

//        final String count = into_num_et.getText().toString();
        final String count = into_num_et.getText().toString();
        if(Double.parseDouble(count)>Double.parseDouble(mMaterial.getCanPutawayNum())){
            Toast.makeText(IntoStoreOptActivity.this,"输入数量不能超过可入库数量",Toast.LENGTH_SHORT).show();
            return;
        }
        final String location = storage_location_et.getText().toString();
        progressDialog.setMessage("正在保存...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.SAVE_INTOSTORE;//+"?sheetId="+mMaterial.getSheetId()+"&sheetDetailId="
//                        +mMaterial.getSheetDetailId()+"&detailCount="+count+"&isEquipment="+finalIsEquipment
//                        + "&enableSn="+finalIsSerial+"&materialId="+mMaterial.getMid()+"&creator="+userInfo.id;
                try {
                    Message message = new Message();
                    org.json.JSONObject obj = new org.json.JSONObject();
                    obj.put("sheetId",mMaterial.getSheetId());
                    obj.put("sheetDetailId",mMaterial.getSheetDetailId());
                    obj.put("materialId",mMaterial.getMid());
                    obj.put("detailCount",count);
                    obj.put("creator",0);
//                    obj.put("orderNum",order);
                    String json = "appFlag=1&userId="+userInfo.id+"&sid="+mMaterial.getSheetId()+"&detailId="+mMaterial.getSheetDetailId()+"&rkId="+intoId+"&detailCount="+count+"&storeLocationCode="+location;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("responseData--",responseData);
                    if(responseData!=null){
                        JSONObject objres = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(objres.getInteger("status")==1){

                            message.what = SAVE_OK;
                        }else{
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("message", objres.getString("message"));
                            message.setData(bundle);
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

                case SAVE_OK:
                    progressDialog.dismiss();
                    into_num_et.setVisibility(View.GONE);
                    btn_save.setBackgroundColor(ContextCompat.getColor(IntoStoreOptActivity.this, R.color.lightGray));
                    btn_save.setEnabled(false);

                    Toast.makeText(IntoStoreOptActivity.this,"入库成功",Toast.LENGTH_LONG).show();

                    break;
                case SAVE_FAIL:
                    progressDialog.dismiss();
                    String message = msg.getData().getString("message");
                    Toast.makeText(IntoStoreOptActivity.this, message, Toast.LENGTH_LONG).show();

                    break;
                case SAVE_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(IntoStoreOptActivity.this, "入库出错！", Toast.LENGTH_LONG).show();
                    break;


                default:
                    break;
            }
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_btn_save:

                saveInto();
                break;
            case R.id.id_btn_scan:
                startQrCode();
                break;

            default:
                break;
        }
    }
}
