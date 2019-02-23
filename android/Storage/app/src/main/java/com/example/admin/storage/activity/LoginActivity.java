package com.example.admin.storage.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.EditTextClearTools;
import com.example.admin.storage.R;
import com.example.admin.storage.utiles.HTTP;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserPassword;
    private CheckBox checkBox;
    private Button btnLogin;
    private String userName;
    private String userPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_logo_activty);
        init();
    }


    private void init(){
        progressDialog = new ProgressDialog(this);
        etUserName = (EditText) findViewById(R.id.et_userName);
        etUserPassword = (EditText) findViewById(R.id.et_password);
        ImageView unameClear = (ImageView) findViewById(R.id.iv_unameClear);
        ImageView pwdClear = (ImageView) findViewById(R.id.iv_pwdClear);
        Button loginBtn = (Button)findViewById(R.id.btn_login);
        checkBox = (CheckBox)findViewById(R.id.cb_checkbox);
        EditTextClearTools.addClearListener(etUserName,unameClear);
        EditTextClearTools.addClearListener(etUserPassword,pwdClear);

        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        String js = sp.getString("userinfo","");
        if(!js.equals("")){
            int rememb = sp.getInt("rememb",0);
            if(rememb==1){
                checkBox.setChecked(true);
                if(!js.equals("")){
                    try {
                        JSONObject obj = new JSONObject(js);
                        etUserName.setText(obj.getString("code"));
                        etUserPassword.setText(sp.getString("userpsw",""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String name = etUserName.getText().toString();
                Log.i("name---",name);
                if(name.equals("")){
                    alert("","  请输入用户名");
                    Toast.makeText(getApplication(),"请输入用户名",Toast.LENGTH_SHORT);
                    return;
                }
                 String pwd = etUserPassword.getText().toString();
                if(pwd.equals("")){
                    alert("","  请输入密码");
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT);
                    return;
                }
//                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
                login();
//                UserInfo userInfo = new UserInfo();
//                userInfo.setUserName("ceshi");
//                userInfo.setUid(3284);
//                userInfo.setPassword("123");
//                userInfo.mUnit = mUnitSpinner.getSelectedItem().toString();
//                userInfo.mDepartment = mDepartmentSpinner.getSelectedItem().toString();
//                userInfo.mUserId = mUserIdEditor.getText().toString();
//                userInfo.mUserName = mUserNameEditor.getText().toString();
//                userInfo.mUserPhone = mPhoneEditor.getText().toString();
//
//                Settings.getInstance().setUserInfo(userInfo);
//                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
//                //启动
//                startActivity(intent);

            }
        });
//        //debug by shang
//        MaterialModel mm = new MaterialModel();
//        mm.setSheetId(89);
//        mm.setSheetDetailId(888);
//        mm.setCategoryId(5656);
//        mm.setMaterialId(45);
//        String str = mm.toString();
//        Log.i("login---",str);
//        String jsonString = JSONObject.toJSONString(mm);
//        Log.i("login--jsonString--",jsonString.toString());
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        Log.i("login--jsonObject--",jsonObject.toString());
//
//        System.out.println(jsonObject);

    }
    public void login(){
        final String name = etUserName.getText().toString();
        Log.i("name---",name);
//        if(name.equals("")){
//            Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT);
//            return;
//        }
        final String pwd = etUserPassword.getText().toString();
//        if(pwd.equals("")){
//            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT);
//            return;
//        }
        Log.i("pwd---",pwd);
        progressDialog.setMessage("正在登录...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.LOGIN;//+"?code="+name+"&passWord="+pwd;
                Log.i("login address----",address);
                try {
                    Message message = new Message();
                    String json = "appFlag=1&code="+name+"&passWord="+pwd;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("登录---",responseData.toString());
                    if(responseData!=null && responseData!= "null"){
                        org.json.JSONObject obj = new org.json.JSONObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInt("status")==1){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("userinfo",obj.getString("data") );
                            bundle.putString("userpower",obj.getString("message") );
                            message.setData(bundle);
                            message.what = Constant.SUCCESS;
                        }else{

                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("message", obj.getString("message"));
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
                    int rememb = 0;
//                    Log.i("checkBox.isChecked----",String.valueOf(checkBox.isChecked()));
                    if(checkBox.isChecked()==true){
                        rememb = 1;
                    }
                    String userinfo = msg.getData().getString("userinfo");
                    String userpower = msg.getData().getString("userpower");
                    try {

                        org.json.JSONObject obj = new org.json.JSONObject(String.valueOf(userinfo));
                        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
//                        String js = sp.getString("userinfo","");
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userinfo", userinfo);
//                        editor.putString("userpower", "wzjs_sheet,receipt_details,sheet_CK,KCPDZD,sheet_YW,querys");
                        editor.putString("userpower", userpower);
                        editor.putString("userpsw", etUserPassword.getText().toString());
                        editor.putInt("rememb", rememb);
                        editor.commit();
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                        finish();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this,"数据解析失败",Toast.LENGTH_SHORT).show();
                    }


                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    String message = msg.getData().getString("message");
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录出错！", Toast.LENGTH_SHORT).show();
                    break;


                default:
                    break;
            }
        }
    };
    public void alert(String title,String message){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        dialog.show();
    }

}
