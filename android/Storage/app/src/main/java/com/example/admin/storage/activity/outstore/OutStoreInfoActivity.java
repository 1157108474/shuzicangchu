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
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.google.zxing.activity.CaptureActivity;

public class OutStoreInfoActivity extends Activity implements View.OnClickListener {
    String scanResult;
    private TextView bill,applynumber,use_unit,apply_unit,funds_source,usage,superior;
    private LinearLayout noContent;
    private ProgressDialog progressDialog;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_outstore_info);
        initView();
    }
    public void initView(){
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        findViewById(R.id.id_btn_submit).setOnClickListener(this);
        findViewById(R.id.id_btn_split).setOnClickListener(this);
//        findViewById(R.id.btn_scan).setOnClickListener(this);
        bill = findViewById(R.id.id_order_number);
        applynumber = findViewById(R.id.id_apply_number);
        use_unit = findViewById(R.id.id_use_unit);
        apply_unit = findViewById(R.id.id_apply_unit);
        funds_source = findViewById(R.id.id_funds_source);
        usage = findViewById(R.id.id_use);
        superior = findViewById(R.id.id_superior);


        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("出库单详情");
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
    }
    private void split(){
//        final String materialCode = eWuliao.getText().toString();
        progressDialog.setMessage("正在拆单......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.QUERY_OUTSTORE_WULIAOS;//+"?creator="+userInfo.id+"&ordernum="+buyOrder+"&materialCode="+materialCode+"&page="+page;
                try {
                    Message message = new Message();
//                    applyCode = "WZTH201709280060";//debug by shang
                    String json = "appFlag=1&userId="+userInfo.id+"&sheetId="+""+"&typeName="+""+"&extendint1="+""+"&useddepartid="+"";
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("获得的未出库列表shuj-------",responseData);
                    if(responseData!=null){
                        JSONObject obj = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInteger("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("wuliaos", obj.getString("data"));
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
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case Constant.SUCCESS:
                    progressDialog.dismiss();
                    String wuliaos = msg.getData().getString("wuliaos");

                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreInfoActivity.this, "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(OutStoreInfoActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
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
            case R.id.id_btn_submit:

                break;
            case R.id.id_btn_split:
                split();
                break;

            default:
                break;
        }
    }
}
