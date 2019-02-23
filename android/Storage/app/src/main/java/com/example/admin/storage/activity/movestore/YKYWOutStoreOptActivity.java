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

public class YKYWOutStoreOptActivity extends Activity implements View.OnClickListener {
    String scanResult;
    private TextView encode,describe,provider,stockNum,serial,is_serial,unit,pre_storage_location,pre_storage;
    private TextView move_num_tv;
    private EditText move_num_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ykyw_outstore_opt);
        initView();
    }
    public void initView(){
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        findViewById(R.id.id_btn_save).setOnClickListener(this);
//        findViewById(R.id.btn_scan).setOnClickListener(this);
        encode = findViewById(R.id.id_encode);
        describe = findViewById(R.id.id_describe);
        provider = findViewById(R.id.gongyingshang);
        stockNum = findViewById(R.id.id_count);
        unit = findViewById(R.id.id_unit);
        serial = findViewById(R.id.id_serial);
        is_serial = findViewById(R.id.id_is_serial);

        pre_storage_location = findViewById(R.id.id_old_storage_location);
        pre_storage = findViewById(R.id.id_pre_storage);

        move_num_tv = findViewById(R.id.id_move_num_tv);
        move_num_et = findViewById(R.id.id_move_num_et);


        TextView tv = (TextView)findViewById(R.id.id_title_tv);
        tv.setText("移出操作");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("materialykyw");
        encode.setText(bundle.getString("encode"));
//        tv.setText(bundle.getString("encode"));
        describe.setText(bundle.getString("describe"));
        provider.setText(bundle.getString("provider"));
        stockNum.setText(bundle.getString("stocknum"));
        unit.setText(bundle.getString("unit"));
        is_serial.setText(bundle.getString("isserial"));
        serial.setText(bundle.getString("serial"));
        pre_storage.setText(bundle.getString("storage"));
        pre_storage_location.setText(bundle.getString("storagelocation"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
