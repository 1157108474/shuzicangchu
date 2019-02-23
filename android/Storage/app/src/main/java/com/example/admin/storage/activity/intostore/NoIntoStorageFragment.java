package com.example.admin.storage.activity.intostore;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.adapters.MaterialNoIntoStoreAdapter;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class NoIntoStorageFragment extends Fragment implements PullToRefreshListView.OnRefreshListener,View.OnClickListener{
    private String intoCode,jsCode;
    private int intoId;
    private EditText eWuliao;
    private PullToRefreshListView listView;
    private MaterialNoIntoStoreAdapter adapter;
    private List<Material> mData = new ArrayList<Material>();
    ProgressDialog progressDialog = null;
    private final int GET_OK = 20;
    private final int GET_FAIL = 21;
    private final int GET_ERROR = 22;
    private LinearLayout noContent;
    private UserInfo userInfo;
    private int page = 1;
    private int pageSize = 20;
    private int totalPage = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receive, container, false);
        view.findViewById(R.id.btn_search).setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        eWuliao= (EditText) view.findViewById(R.id.et_wuliao);
        noContent = (LinearLayout) view.findViewById(R.id.nocontent);

        listView = (PullToRefreshListView) view.findViewById(R.id.material_listview);

        adapter = new MaterialNoIntoStoreAdapter(getActivity(),mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(listView.isMoved()){
                    return;
                }
                Material ml = mData.get(i-1);
                String jsonStr = JSONObject.toJSONString(ml);
                Intent intent = new Intent(getActivity(),IntoStoreOptActivity.class);
                Log.i("intoId=====",String.valueOf(intoId));
                intent.putExtra("rkid",intoId);
                intent.putExtra("rkmaterial",jsonStr);
                startActivity(intent);
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return true;
            }


        });
        SharedPreferences sp = this.getActivity().getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
        return view;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_search){
            String wuliaoCode = eWuliao.getText().toString();
            if(wuliaoCode.equals("")){
                Toast.makeText(getActivity(),"请输入或扫描物料编码",Toast.LENGTH_SHORT).show();
                return;
            }
            page=1;
            mData.clear();
            getListData();
        }else if(id == R.id.btn_scan){
            startQrCode();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("NoIntofragment---","--onResume");
        page=1;
        mData.clear();
        getListData();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("NoIntofragment---","--onstart");
        if (isAdded()) {//判断Fragment已经依附Activity
            intoCode = getArguments().getString(Constant.FRAG_ORDER_CODE);//入库单编号
            intoId = getArguments().getInt(Constant.FRAG_ORDER_ID);//入库单id
            jsCode = getArguments().getString(Constant.JS_CODE);
//            Log.i("intoCode--",intoCode);

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
    public void getListData(){
        final String materialCode = eWuliao.getText().toString();
        progressDialog.setMessage("正在加载，请稍等......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String details = formatData();
                //order为接收单号
                String address = Constant.QUERY_RECEIPT_WULIAOS;//+"?creator="+userInfo.id+"&ordernum="+buyOrder+"&materialCode="+materialCode+"&page="+page;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&jsCode="+jsCode+"&materialCode="+materialCode+"&page="+page+"&limit="+pageSize;
                    String responseData = HTTP.queryPost(json,address);
                    Log.i("获得的未入库列表shuj-------",responseData);
                    if(responseData!=null){
                        JSONObject obj = JSONObject.parseObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInteger("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("wuliaos", obj.getString("data"));
                            bundle.putInt("count", obj.getInteger("count"));
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
                        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_LONG).show();
                        listView.onRefreshComplete();
                        listView.setLoadMore(false);

                    }else {
                        page++;
                        getListData();
                    }
                    break;
                case GET_OK:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    String wuliaos = msg.getData().getString("wuliaos");
                    int cout = msg.getData().getInt("count");
                    if(cout % pageSize > 0){
                        totalPage = cout / pageSize + 1;
                    }else{
                        totalPage = cout / pageSize;
                    }

                    Log.i("获得的入库wuliao列表---",wuliaos);
                    try {
                        JSONArray objArray = JSONArray.parseArray(wuliaos);
                        if(objArray.size()==0){
                            noContent.setVisibility(View.VISIBLE);
                            break;
                        }
                        for(int i = 0; i < objArray.size(); i++){
                            Material ml = new Material();
                            JSONObject obj =  objArray.getJSONObject(i);
//                            JSONObject obj = objArray.getJSONObject(i);
                            ml.setMid(obj.getInteger("materialId"));
//                            ml.setSheetId(intoId);
                            ml.setSheetId(obj.getInteger("sheetId"));
                            ml.setSheetDetailId(obj.getInteger("id"));
                            ml.setProvider(obj.getString("providerName"));
                            ml.setIsEquipment(obj.getInteger("isEquipment"));
                            ml.setIsSerialInt(obj.getInteger("enableSn"));

                            ml.setEncode(obj.getString("materialCode"));
                            ml.setDescribe(obj.getString("description"));
                            double noruku = obj.getDouble("isCount");//已入库数量
                            double jieshou = obj.getDouble("jsCount");
                            double kerkNum = jieshou - noruku;
                            ml.setCanPutawayNum(Constant.formatCount(String.valueOf(kerkNum)));
                            ml.setReceivedNum(Constant.formatCount(String.valueOf(jieshou)));
                            ml.setSalesType(obj.getString("jsType"));
                            ml.setUnit(obj.getString("detailUnitName"));//debug
                            ml.setStoreName(obj.getString("storeName"));

                            mData.add(ml);
                        }
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
//                        listView.onRefreshComplete();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(getActivity(), "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    Toast.makeText(getActivity(), "查询出错！", Toast.LENGTH_LONG).show();
                    break;


                default:
                    break;
            }
        }
    };

}
