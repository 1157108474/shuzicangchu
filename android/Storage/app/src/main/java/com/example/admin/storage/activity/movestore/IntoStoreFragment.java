package com.example.admin.storage.activity.movestore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.storage.R;
import com.example.admin.storage.adapters.MaterialHasIntoStoreAdapter;
import com.example.admin.storage.adapters.MaterialYKYWIntoStoreAdapter;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.utiles.Constant;
import com.google.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class IntoStoreFragment extends Fragment implements View.OnClickListener {
    private String receiveOrder;
    private EditText eWuliao;
    private ListView listView;
    private MaterialYKYWIntoStoreAdapter adapter;
    private List<Material> mData = new ArrayList<Material>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_received, container, false);
        view.findViewById(R.id.btn_search).setOnClickListener(this);
        view.findViewById(R.id.btn_scan).setOnClickListener(this);
        eWuliao= (EditText) view.findViewById(R.id.et_wuliao);

        listView = (ListView) view.findViewById(R.id.material_listview);

//        for(int i =0;i<4;i++){
//            Material ml = new Material();
//            ml.setEncode("TSNG2356789");
//            ml.setDescribe("sdfsd啊但是是根深蒂固山豆根山豆根似的和规范发嘀咕嘀咕的风格的");
//            ml.setProvider("我是供应商");
//            ml.setPutawayNum("3");
//            ml.setIsSerial(1);
//            ml.setStoreName("塑料库");
//            ml.setStoreLocation("C04-16-08-56");
//            ml.setUnit("件");
//            ml.setNeedIntoNum("3");
//            mData.add(ml);
//        }
        adapter = new MaterialYKYWIntoStoreAdapter(getActivity(),mData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),YKYWIntoStoreOptActivity.class);
                Material ml = mData.get(i);

//                //用Bundle携带Opt数据
                Bundle bundle=new Bundle();
                bundle.putString("encode",ml.getEncode());
                bundle.putString("decribe",ml.getDescribe());
                bundle.putString("provider",ml.getProvider());
                bundle.putString("stockNum",ml.getStockNum());
                bundle.putString("needIntoNum",ml.getNeedIntoNum());
                bundle.putString("unit",ml.getUnit());
                bundle.putString("isserial",ml.getIsSerialInt()==0?"不启用":"启用");
                bundle.putString("serial",ml.getSerial());
                bundle.putString("strage",ml.getStoreName());
                bundle.putString("storagelocation",ml.getStoreLocation());
//                bundle.putString("ykyworder","YKYW33333333" );
                //把数据捆
                intent.putExtra("materialykyw", bundle);

                startActivity(intent);
            }

        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (isAdded()) {//判断Fragment已经依附Activity
            receiveOrder = getArguments().getString(Constant.RECEIVE_FRAG_ORDER);
            Log.i("ReceiveFrag",receiveOrder);
        }
    }
    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            eWuliao.setText(scanResult);
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
                    Toast.makeText(getActivity(), "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_search){

        }else if(id==R.id.btn_scan){
            startQrCode();
        }
    }
}
