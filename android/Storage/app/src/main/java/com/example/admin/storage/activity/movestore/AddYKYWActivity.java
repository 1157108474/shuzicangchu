package com.example.admin.storage.activity.movestore;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.storage.R;
import com.example.admin.storage.activity.intostore.ReceivedListSelectActivity;
import com.example.admin.storage.adapters.BillAdapter;
import com.example.admin.storage.baseclass.Bill;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.CustomPopupWindow;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddYKYWActivity extends Activity implements View.OnClickListener{
    List<String> mData = new ArrayList<String>();
    Spinner spinner;
    ArrayAdapter adapter;
    private final int GET_OK = 20;
    private final int GET_FAIL = 21;
    private final int GET_ERROR = 22;
    ProgressDialog progressDialog = null;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ykyw);

        initView();
        initData();

    }
    private void initView() {

        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        TextView tv = findViewById(R.id.id_title_tv);
        tv.setText("选择库房");
        findViewById(R.id.id_btn_save).setOnClickListener(this);
        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mData);
    //设置下拉列表风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到spinner中去
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);//设置默认显示
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
            int arg2, long arg3) {
            // TODO Auto-generated method stub
            //result.setText("你的选择是："+((TextView)arg1).getText());


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

            }
        });

    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
        getListData();

    }
    private void getListData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Constant.GET_STORAGES;//+"?appFlag=1&userId="+userInfo.id+"&page=1&limit=100000&code="+ykywCode;
                Log.i("获取未完成移库移位单url----",url);
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&parentId=0&page=1&limit=100000";

                    String responseData = HTTP.queryPost(json,url);
                    Log.i("responseData------",responseData.toString());
                    if(responseData!=null && responseData!="null"){
                        JSONObject obj = new JSONObject(responseData);
                        if(obj.getInt("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("ykyw", obj.getString("data"));
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


    public void creatYKYW(){
        progressDialog.setMessage("正在添加...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = Constant.INSERT_YKYW;//+"?appFlag=1&userId="+userInfo.id+"&menuCode=sheet_YW";
                try {
                    Log.i("address----",address);
                    Message message = new Message();
                    String json = "appFlag=1&userId="+userInfo.id+"&menuCode=sheet_YW";
                    String responseData = HTTP.queryPost(json,address);
                    if(responseData!=null && responseData!= "null"){
                        JSONObject obj = new JSONObject(responseData);
                        if(obj.getInt("status")==1){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("ykyw", obj.getString("data"));
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
                    msg.what = Constant.NETWORK_ERROR;;
//                    msg.setData(b);
                    handler.sendMessage(msg);
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

                case GET_OK:
                    progressDialog.dismiss();
                    mData.clear();
                    String bills = msg.getData().getString("ykyw");
                    try {
                        com.alibaba.fastjson.JSONArray objArray = com.alibaba.fastjson.JSONArray.parseArray(bills);
//                        JSONArray objArray = JSONArray.fromObject(wuliaosData);
                        for(int i = 0; i < objArray.size(); i++){
                            Material ml = new Material();
                            com.alibaba.fastjson.JSONObject subObject =  objArray.getJSONObject(i);
//                            JSONObject obj = objArray.getJSONObject(i);
                            mData.add("");
                        }
                        adapter.notifyDataSetChanged();


                    } catch (com.alibaba.fastjson.JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(AddYKYWActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(AddYKYWActivity.this, "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(AddYKYWActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
                    break;

                case Constant.SUCCESS:
                    progressDialog.dismiss();
                    String data = msg.getData().getString("ykyw");
                    try {
                        JSONObject obj = new JSONObject(data);
                        String ykywcode = obj.getString("code");
                        Intent intent = new Intent(getApplicationContext(), YKYWDetailActivity.class);
                        Bundle bundle = new Bundle();
                        //传递参数
                        bundle.putInt("ykywid", obj.getInt("id"));
                        bundle.putString("ykyw", ykywcode);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "数据解析失败！", Toast.LENGTH_LONG).show();
                    }

                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "创建移库移位单失败！", Toast.LENGTH_LONG).show();
//
                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "创建移库移位单出错！", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_btn_save:

                break;

            default:
                break;
        }
    }


}
