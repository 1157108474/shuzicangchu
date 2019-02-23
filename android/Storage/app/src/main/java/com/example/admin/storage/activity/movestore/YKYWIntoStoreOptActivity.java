package com.example.admin.storage.activity.movestore;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.storage.R;
import com.example.admin.storage.utiles.Constant;
import com.google.zxing.activity.CaptureActivity;

public class YKYWIntoStoreOptActivity extends Activity implements View.OnClickListener {
    String scanResult;
    private TextView encode,describe,provider,stockNum,serial,is_serial,unit,pre_storage_location,pre_storage;
    private TextView move_num_tv,target_storage_location_tv;
    private EditText target_storage_location_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ykyw_intostore_opt);
        initView();
    }
    public void initView(){
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        findViewById(R.id.id_btn_save).setOnClickListener(this);
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

        target_storage_location_tv = findViewById(R.id.id_target_storage_location_tv);
        target_storage_location_et = findViewById(R.id.id_target_storage_location_et);


        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("移入操作");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("materialykyw");
        encode.setText(bundle.getString("encode"));
//        tv.setText(bundle.getString("encode"));
        describe.setText(bundle.getString("describe"));
        provider.setText(bundle.getString("provider"));
        stockNum.setText(bundle.getString("stockNum"));
        unit.setText(bundle.getString("unit"));
        is_serial.setText(bundle.getString("isserial"));
        serial.setText(bundle.getString("serial"));
        pre_storage.setText(bundle.getString("storage"));
        pre_storage_location.setText(bundle.getString("storagelocation"));

        move_num_tv.setText(bundle.getString("needIntoNum"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_btn_save:

                break;
            case R.id.id_btn_scan:
                startQrCode();
                break;

            default:
                break;
        }
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
