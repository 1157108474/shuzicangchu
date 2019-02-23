package com.example.admin.storage.activity.buyer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;


public class ReceiveOptActivity extends Activity implements View.OnClickListener {
    private EditText receive_num_et;
    private TextView receive_num_tv;
    private Button btn_save;
    private CheckBox isEquipment_cb,isSerial_cb;
    private final int SAVE_OK = 20;
    private final int SAVE_FAIL = 21;
    private final int SAVE_ERROR = 22;
    private Material mMaterial = new Material();
    private ProgressDialog progressDialog;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_receive_opt);
        initView();
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
    }
    public void initView(){
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        btn_save = (Button)findViewById(R.id.btn_accept);
        btn_save.setOnClickListener(this);
        findViewById(R.id.btn_connect).setOnClickListener(this);
        findViewById(R.id.btn_print).setOnClickListener(this);
        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("采购接收");
        receive_num_et = (EditText)findViewById(R.id.id_receipt_num) ;
//        receive_num_tv = (TextView)findViewById(R.id.id_tv_receipt_num);
        TextView caigoudingdan_tv = (TextView)findViewById(R.id.caigoudingdan);
        TextView encode_tv = (TextView)findViewById(R.id.id_encode);
        TextView describe_tv = (TextView)findViewById(R.id.id_describe);
        TextView provider_tv = (TextView)findViewById(R.id.gongyingshang);
        TextView unit_tv = (TextView)findViewById(R.id.id_unit);
        TextView type_tv = (TextView)findViewById(R.id.id_sales_type);
        TextView buy_num_tv = (TextView)findViewById(R.id.id_buy_num);
        TextView canreceive_num_tv = (TextView)findViewById(R.id.id_canreceive_num);
        isEquipment_cb = (CheckBox) findViewById(R.id.id_is_equipment_cb);
        isSerial_cb = (CheckBox)findViewById(R.id.id_is_serial_cb);

        isEquipment_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isSerial_cb.setEnabled(true);

                }else{
                    isSerial_cb.setChecked(false);
                    isSerial_cb.setEnabled(false);
                }
            }
        });

        String wuliaoinfo = getIntent().getStringExtra("materialreceive");
        JSONObject obj = JSONObject.parseObject(wuliaoinfo);
        mMaterial = JSONObject.parseObject(wuliaoinfo,Material.class);
        caigoudingdan_tv.setText(mMaterial.getBuyOrder());
        encode_tv.setText(mMaterial.getEncode());
        describe_tv.setText(mMaterial.getDescribe());
        provider_tv.setText(mMaterial.getProvider());
        unit_tv.setText(mMaterial.getUnit());
        type_tv.setText(mMaterial.getSalesType());
        buy_num_tv.setText(mMaterial.getBuyNum());
        canreceive_num_tv.setText(mMaterial.getCanReceiveNum());
        if(mMaterial.getIsEquipment()==0)
        {
            isSerial_cb.setEnabled(false);
            isEquipment_cb.setChecked(false);
        }else{
            isEquipment_cb.setChecked(true);
            isSerial_cb.setEnabled(true);
            if(mMaterial.getIsSerialInt()==0){
                isSerial_cb.setChecked(true);
            }
        }

    }
    public void saveReceive(){

        final String count = receive_num_et.getText().toString();
        if(Double.parseDouble(count)>Double.parseDouble(mMaterial.getCanReceiveNum())){
            Toast.makeText(ReceiveOptActivity.this,"接收数量不能超过可接收数量",Toast.LENGTH_SHORT).show();
            return;
        }
        int isSerial=0,isEquipment=0;

        if(isEquipment_cb.isChecked()){
            isEquipment=1;
            if(isSerial_cb.isChecked()){
                isSerial=1;
            }
        }
        final int finalIsEquipment = isEquipment;
        final int finalIsSerial = isSerial;

        progressDialog.setMessage("正在保存...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.SAVE_RECEIVE;//+"?sheetId="+mMaterial.getSheetId()+"&sheetDetailId="
//                        +mMaterial.getSheetDetailId()+"&detailCount="+count+"&isEquipment="+finalIsEquipment
//                        + "&enableSn="+finalIsSerial+"&materialId="+mMaterial.getMid()+"&creator="+userInfo.id;
                try {
                    Message message = new Message();
                    org.json.JSONObject obj = new org.json.JSONObject();
                    obj.put("sheetId",mMaterial.getSheetId());
                    obj.put("sheetDetailId",mMaterial.getSheetDetailId());
                    obj.put("materialId",mMaterial.getMid());
                    obj.put("detailCount",count);
                    obj.put("isEquipment", finalIsEquipment);
                    obj.put("enableSn", finalIsSerial);
                    obj.put("creator",0);
//                    obj.put("orderNum",order);
                    String json = "appFlag=1&sheetId="+mMaterial.getSheetId()+"&sheetDetailId="
                            +mMaterial.getSheetDetailId()+"&detailCount="+count+"&isEquipment="+finalIsEquipment
                            + "&enableSn="+finalIsSerial+"&materialId="+mMaterial.getMid()+"&userId="+userInfo.id;
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
                    receive_num_et.setVisibility(View.GONE);
                    btn_save.setBackgroundColor(ContextCompat.getColor(ReceiveOptActivity.this, R.color.lightGray));
                    btn_save.setEnabled(false);

                    Toast.makeText(ReceiveOptActivity.this,"接收成功",Toast.LENGTH_LONG).show();

                    break;
                case SAVE_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(ReceiveOptActivity.this, "接收失败！", Toast.LENGTH_LONG).show();

                    break;
                case SAVE_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(ReceiveOptActivity.this, "接收出错！", Toast.LENGTH_LONG).show();
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
            case R.id.btn_accept:
                if(receive_num_et.getText().toString().equals("")){
                    Toast.makeText(ReceiveOptActivity.this,"请输入接收数量",Toast.LENGTH_SHORT).show();

                }else{
                    saveReceive();
                }

                break;
            case R.id.btn_connect:
                Constant.alert(this,"暂不能连接打印机");
                break;

            case R.id.btn_print:
                Constant.alert(this,"暂不能连接打印机");
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);

                break;

            default:
                break;
        }
    }
}
