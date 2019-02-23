package com.example.admin.storage.activity.check;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

public class MaterialCheckActivity extends Activity implements View.OnClickListener {
    private int sheetId;
    String scanResult;
    private TextView encode,describe,provider,stocknum,unit,storage_location,storage;
    private TextView infect_num_tv;
    private EditText infect_num_et;
    private Button btn_save;
    private ProgressDialog progressDialog;
    private final int SAVE_OK = 20;
    private final int SAVE_FAIL = 21;
    private final int SAVE_ERROR = 22;
    private UserInfo userInfo;
    private Material mMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_material_check);
        initView();
    }
    public void initView(){
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        btn_save = findViewById(R.id.id_btn_modify);
        btn_save.setOnClickListener(this);
//        findViewById(R.id.id_btn_scan).setOnClickListener(this);
        encode = findViewById(R.id.id_encode);
        describe = findViewById(R.id.id_describe);
        provider = findViewById(R.id.gongyingshang);
        stocknum = findViewById(R.id.id_count);
        unit = findViewById(R.id.id_unit);
        storage = findViewById(R.id.id_storage);
        storage_location = findViewById(R.id.id_storage_location);


        infect_num_tv = findViewById(R.id.id_infect_num_tv);
        infect_num_et = findViewById(R.id.id_infect_num_et);


        TextView tv = (TextView)findViewById(R.id.id_title_tv);

        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("materialcheck");
        String materialStr = intent.getStringExtra("materialcheck");
        mMaterial = JSONObject.parseObject(materialStr,Material.class);
//        sheetId = mMaterial.getInt("sheetId");
        encode.setText(mMaterial.getEncode());
        tv.setText(mMaterial.getEncode());
        describe.setText(mMaterial.getDescribe());
        provider.setText(mMaterial.getProvider());
        stocknum.setText(mMaterial.getStockNum());
        unit.setText(mMaterial.getUnit());
        storage.setText(mMaterial.getStoreName());
        storage_location.setText(mMaterial.getStoreLocation());
//        ≥.setText(bundle.getString("encode"));

        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");

//        JSONObject jsonObject = JSONObject.parseObject(ustr);
        userInfo = JSONObject.parseObject(ustr,UserInfo.class);


    }
    private void initData() {

        Log.i("userInfo.code---",userInfo.code);
//        getListData();

    }
   private void updatePandian(){
        final String infectNum = infect_num_et.getText().toString();
        final String sysNum = stocknum.getText().toString();
        if(infectNum.equals("")){
            Toast.makeText(MaterialCheckActivity.this,"实际数量为空",Toast.LENGTH_LONG).show();
            return;
        }
       progressDialog.setMessage("正在保存...");
       progressDialog.show();
       new Thread(new Runnable() {
           @Override
           public void run() {
//                String details = formatData();
               //order为接收单号
               String address = Constant.UPDATE_PANDIAN;//+"?userId="+userInfo.id+"&id="+mMaterial.getMid()+"&detailCount="+infectNum+"&sysCount="+sysNum;
               try {
                   Message message = new Message();
//                   org.json.JSONObject obj = new org.json.JSONObject();
//                   obj.put("sheetId",mMaterial.getSheetId());
//                   obj.put("sheetDetailId",mMaterial.getSheetDetailId());
//                   obj.put("materialId",mMaterial.getMid());
//                   obj.put("detailCount",count);
//                   obj.put("isEquipment", finalIsEquipment);
//                   obj.put("enableSn", finalIsSerial);
//                   obj.put("creator",0);
//                    obj.put("orderNum",order);
                   String json = "appFlag=1&userId="+userInfo.id+"&id="+mMaterial.getMid()+"&detailCount="+infectNum+"&sysCount="+sysNum;
                   String responseData = HTTP.queryPost(json,address);
                   Log.i("盘点数量修改返回----",responseData);
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

                case SAVE_OK:
                    progressDialog.dismiss();
                    infect_num_et.setVisibility(View.GONE);
                    infect_num_tv.setText(infect_num_et.getText().toString());
                    btn_save.setVisibility(View.GONE);

                    Toast.makeText(MaterialCheckActivity.this,"修改成功",Toast.LENGTH_LONG).show();

                    break;
                case SAVE_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(MaterialCheckActivity.this, "修改失败！", Toast.LENGTH_LONG).show();

                    break;
                case SAVE_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(MaterialCheckActivity.this, "修改出错！", Toast.LENGTH_LONG).show();
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
            case R.id.id_btn_modify:
                updatePandian();
                break;

            default:
                break;
        }
    }
}
