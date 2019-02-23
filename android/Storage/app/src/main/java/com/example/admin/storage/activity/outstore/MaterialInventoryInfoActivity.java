package com.example.admin.storage.activity.outstore;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.storage.R;
import com.example.admin.storage.adapters.MaterialInventoryInfoAdapter;
import com.example.admin.storage.baseclass.Bill;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MaterialInventoryInfoActivity extends Activity implements View.OnClickListener,PullToRefreshListView.OnRefreshListener{

    TextView tvResult,applyNum_tv,nooutNum_tv; // 结果
    private PullToRefreshListView listView;
//    private CustomPopupWindow mPop;
    //    private ArrayAdapter<List<String>> adapter0;
    private MaterialInventoryInfoAdapter adapter;
    private List<Material> mData = new ArrayList<Material>();
    LinearLayout nolayout ;
    EditText search_et;
    private final int GET_OK = 20;
    private final int GET_FAIL = 21;
    private final int GET_ERROR = 22;
    ProgressDialog progressDialog = null;
    private Material mMaterial;
    private String materialCode;
    private int page = 1;
    private int pageSize = 20;
    private int totalPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_info);

        initView();
        initData();

    }
    private void initView() {

        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
//        findViewById(R.id.search_et).setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
        findViewById(R.id.id_scan_bt).setOnClickListener(this);

        search_et = findViewById(R.id.search_et);
        search_et.setHint("库位编码");
        nolayout = findViewById(R.id.nocontent);
        applyNum_tv = findViewById(R.id.id_apply_num);
        nooutNum_tv = findViewById(R.id.id_noout_num);
        listView = (PullToRefreshListView) findViewById(R.id.receipt_listview);

        adapter = new MaterialInventoryInfoAdapter(this,mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.i("buyerReceiveActivity",listView.isMoved()+"itemclick---");
                if(listView.isMoved()){
                    return;
                }
//                Toast.makeText(MaterialInventoryInfoActivity.this, "Click item" + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MaterialInventoryInfoActivity.this,OutStoreOptActivity.class);
                Material ml = mData.get(i-1);
                String ckmStr = com.alibaba.fastjson.JSONObject.toJSONString(mMaterial);
                String mStr = com.alibaba.fastjson.JSONObject.toJSONString(ml);

                intent.putExtra("ckmaterial",ckmStr);
                intent.putExtra("storematerial",mStr);
                startActivity(intent);
                finish();
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return true;
            }


        });
    }




    private void initData() {
        String json = getIntent().getStringExtra("ckmaterial");
        mMaterial = com.alibaba.fastjson.JSONObject.parseObject(json,Material.class);
        applyNum_tv.setText(mMaterial.getApplyoutNum());
        nooutNum_tv.setText(mMaterial.getApplyNooutNum());
        getListData();

    }
    private void getListData(){
        final String locationCode = search_et.getText().toString();
        progressDialog.setMessage("正在加载，请稍等......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Constant.SELECT_WULIAO;//+"?appFlag=1&userId="+userInfo.id+"&page=1&limit=100000&code="+ykywCode;
//                Log.i("获取未完成移库移位单url----",url);
                try {
                    Message message = new Message();
                    String json = "appFlag=1&storelocationcode="+locationCode+"&materialCode="+mMaterial.getEncode()+"&page="+page+"&limit="+pageSize;;
                    String responseData = HTTP.queryPost(json,url);
                    Log.i("物料库位信息------",responseData.toString());
                    if(responseData!=null && responseData!="null"){
                        JSONObject obj = new JSONObject(responseData);
                        if(obj.getInt("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("wuliaos", obj.getString("data"));
                            bundle.putInt("count", obj.getInt("count"));
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
            search_et.setText(scanResult);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_btn:
                finish();
                break;
            case R.id.id_search_bt:
                if(mData.size()==0){
                    return;
                }
                String code = search_et.getText().toString();
//                if(code.equals("")){
//                    Toast.makeText(this,"请输入或扫描录入库位编码",Toast.LENGTH_LONG);
//                    return;
//                }
                mData.clear();
                getListData();
                break;
            case R.id.id_scan_bt:
                startQrCode();
                break;

            case R.id.btn_add:
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);
//                nolayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                for(int i =0;i<4;i++){
                    Material mMaterial = new Material();
                    mMaterial.setEncode("WZLL201804201401");
                    mMaterial.setDescribe("这里是物料描述");
                    mMaterial.setProvider("这里是供应商");
                    mMaterial.setUnit("件");
                    mMaterial.setStoreName("材料库");
                    mMaterial.setStoreLocation("这里是库位");
                    mMaterial.setStockNum("3.6");

                    mData.add(mMaterial);
                }

                adapter.notifyDataSetChanged();
                listView.onRefreshComplete();

                break;

            default:
                break;
        }
    }

    @Override
    public void onDownPullRefresh() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }

    @Override
    public void onLoadingMore() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 1000);
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
//                    Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_LONG).show();
                    page=1;
                    listView.setLoadMore(true);
                    mData.clear();
                    getListData();
                    break;
                case 1:
                    if(page == totalPage){
                        Toast.makeText(MaterialInventoryInfoActivity.this, "没有更多数据", Toast.LENGTH_LONG).show();
                        listView.onRefreshComplete();
                        listView.setLoadMore(false);

                    }else {
                        page++;
                        getListData();
                    }
                    break;
                case GET_OK:
                    progressDialog.dismiss();

                    String applesData = msg.getData().getString("wuliaos");
                    int cout = msg.getData().getInt("count");
                    if(cout % pageSize > 0){
                        totalPage = cout / pageSize + 1;
                    }else{
                        totalPage = cout / pageSize;
                    }
                    if(applesData==null){
                        nolayout.setVisibility(View.VISIBLE);
                        break;
                    }
                    Log.i("获取到的申领单列表-----",applesData);
                    try {
                        JSONArray objArray = new JSONArray(applesData);
                        if(objArray.length()==0){
                            nolayout.setVisibility(View.VISIBLE);
                            break;
                        }
                        for(int i = 0; i < objArray.length(); i++){
                            Material ml = new Material();
//                            JSONObject obj = new JSONObject(String.valueOf(objArray.getJSONObject(i)));
                            JSONObject obj = objArray.getJSONObject(i);
                            ml.setMid(obj.getInt("id"));
                            ml.setEncode(obj.getString("materialCode"));
                            ml.setDescribe(obj.getString("description"));
                            ml.setProvider(obj.getString("providername"));
                            ml.setStockNum(Constant.formatCount(obj.getString("unsecount")));
                            ml.setStoreName(obj.getString("storelocationname"));
                            ml.setStoreLocation(obj.getString("storelocationcode"));
                            ml.setUnit(obj.getString("detailUnitName"));//debug
                            mData.add(ml);
                        }
                        adapter.notifyDataSetChanged();
//                        listView.setAdapter(adapter);
                        listView.onRefreshComplete();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        listView.onRefreshComplete();
                        Toast.makeText(MaterialInventoryInfoActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(MaterialInventoryInfoActivity.this, "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(MaterialInventoryInfoActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };
}
