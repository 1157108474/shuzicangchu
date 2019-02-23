package com.example.admin.storage.activity.outstore;

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
import com.example.admin.storage.activity.intostore.IntoStoreOptActivity;
import com.example.admin.storage.activity.movestore.YKYWOptActivity;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.google.zxing.activity.CaptureActivity;

public class OutStoreOptActivity extends Activity implements View.OnClickListener {
    String scanResult;
    private TextView encode,describe,provider,apply_num,noout_num,canout_num,is_equipment,is_serial,storage;
    private TextView storage_location_tv,out_num_tv;
    private EditText out_num_et;
    private Button btn_save;
    private ProgressDialog progressDialog;
    private UserInfo userInfo;
    private Material mMaterial = new Material();
    private Material mCKMaterial = new Material();
    private final int SAVE_OK = 20;
    private final int SAVE_FAIL = 21;
    private final int SAVE_ERROR = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_outstore_opt);
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
//        findViewById(R.id.btn_scan).setOnClickListener(this);
        encode = findViewById(R.id.id_encode);
        describe = findViewById(R.id.id_describe);
        provider = findViewById(R.id.gongyingshang);
        apply_num = findViewById(R.id.id_apply_num);
        noout_num = findViewById(R.id.id_noout_num);
        canout_num = findViewById(R.id.id_canout_num);
        is_equipment = findViewById(R.id.id_is_equipment);
        is_serial = findViewById(R.id.id_is_serial);
//        unit = findViewById(R.id.id_unit);
        storage = findViewById(R.id.id_store_name_tv);
        storage_location_tv = findViewById(R.id.id_storage_location_tv);
        out_num_tv = findViewById(R.id.id_out_num_tv);
        out_num_et = findViewById(R.id.id_out_num_et);


        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("出库下架");

        String ckwuliaoinfo = getIntent().getStringExtra("ckmaterial");
        String wuliaoinfo = getIntent().getStringExtra("storematerial");
        Log.i("wuliaoinfo---",wuliaoinfo);
        JSONObject obj = JSONObject.parseObject(wuliaoinfo);
        mCKMaterial = JSONObject.parseObject(ckwuliaoinfo,Material.class);
        mMaterial = JSONObject.parseObject(wuliaoinfo,Material.class);
        encode.setText(mMaterial.getEncode());
        describe.setText(mMaterial.getDescribe());
        provider.setText(mMaterial.getProvider());

        apply_num.setText(mCKMaterial.getApplyoutNum());
        noout_num.setText(mCKMaterial.getApplyNooutNum());
        canout_num.setText(mMaterial.getStockNum());
        storage.setText(mMaterial.getStoreName());
        storage_location_tv.setText(mMaterial.getStoreLocation());
        is_equipment.setText(mMaterial.getIsEquipment()==1?"是":"否");
        is_serial.setText(mMaterial.getIsSerialInt()==0?"不启用":"启用");
    }

    public void saveOutStore(){
//        final String count = out_num_et.getText().toString();

        final String count = out_num_et.getText().toString();
        if(count.equals("")){
            Toast.makeText(OutStoreOptActivity.this,"出库数量为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Double.parseDouble(count)>Double.parseDouble(mMaterial.getStockNum())){
            Toast.makeText(OutStoreOptActivity.this,"输入数量不能超过可出库数量",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Double.parseDouble(count)>Double.parseDouble(mCKMaterial.getApplyNooutNum())){
            Toast.makeText(OutStoreOptActivity.this,"输入数量不能超过未出库数量",Toast.LENGTH_SHORT).show();
            return;
        }
//        final String location = storage_location_et.getText().toString();
        progressDialog.setMessage("正在保存...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.SAVE_OUTSTORE;//+"?sheetId="+mMaterial.getSheetId()+"&sheetDetailId="
//                        +mMaterial.getSheetDetailId()+"&detailCount="+count+"&isEquipment="+finalIsEquipment
//                        + "&enableSn="+finalIsSerial+"&materialId="+mMaterial.getMid()+"&creator="+userInfo.id;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&sheetId="+mCKMaterial.getSheetId()+"&sheetDetailId="+mCKMaterial.getSheetDetailId()+"&ztId="+mCKMaterial.getZtid()+
                            "&extendInt5="+mCKMaterial.getSheetDetailId()+"&materialCode="+mMaterial.getEncode()+"&extendint2="+mMaterial.getMid()+"&detailCount="+count+"&sncode="+mCKMaterial.getSncode();
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("responseData--",responseData);
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
                    out_num_et.setVisibility(View.GONE);
                    btn_save.setBackgroundColor(ContextCompat.getColor(OutStoreOptActivity.this, R.color.lightGray));
                    btn_save.setEnabled(false);

                    Toast.makeText(OutStoreOptActivity.this,"出库成功",Toast.LENGTH_LONG).show();

                    break;
                case SAVE_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreOptActivity.this, "出库失败！", Toast.LENGTH_LONG).show();

                    break;
                case SAVE_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreOptActivity.this, "出库出错！", Toast.LENGTH_LONG).show();
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
                saveOutStore();
                break;
//            case R.id.btn_scan:
//                startQrCode();
//                break;

            default:
                break;
        }
    }
}
