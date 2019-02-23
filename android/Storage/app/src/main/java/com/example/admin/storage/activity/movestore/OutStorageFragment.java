package com.example.admin.storage.activity.movestore;


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


import com.example.admin.storage.R;
import com.example.admin.storage.activity.intostore.IntoStoreOptActivity;
import com.example.admin.storage.adapters.MaterialNoIntoStoreAdapter;
import com.example.admin.storage.adapters.MaterialYKYWOutStoreAdapter;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class OutStorageFragment extends Fragment implements PullToRefreshListView.OnRefreshListener, View.OnClickListener{
    public String LOGTag = "OutStorageFragment------";
    private String receiveOrder;
    private int orderid;
    private EditText eWuliao;
    private PullToRefreshListView listView;
    private MaterialYKYWOutStoreAdapter adapter;
    private List<Material> mData = new ArrayList<Material>();
    String wuliaosData;
    ProgressDialog progressDialog = null;
    LinearLayout noContent;
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
        noContent = (LinearLayout)view.findViewById(R.id.nocontent);
        eWuliao= (EditText) view.findViewById(R.id.et_wuliao);
        listView = (PullToRefreshListView) view.findViewById(R.id.material_listview);

        adapter = new MaterialYKYWOutStoreAdapter(getActivity(),mData);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(listView.isMoved()){
                    return;
                }
                Intent intent = new Intent(getActivity(),YKYWOptActivity.class);
//                Material ml = mData.get(i);
////                String json = JSON.toJSONString(ret, true);
//                JSONObject json = JSONObject.fromObject(ml);//将java对象转换为json对象
//
//                String str = json.toString();//将json对象转换为字符串
                JSONArray objArray = null;
                try {
                    objArray = new JSONArray(wuliaosData);
                    String str = objArray.get(i-1).toString();
                    intent.putExtra("materialykyw", str);
                    intent.putExtra("sheetId", orderid);

                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                JSONArray objArray = JSONArray.fromObject(wuliaosData);

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
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
//                    Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_LONG).show();
                    listView.setLoadMore(true);
                    page = 1;
                    mData.clear();
                    getListData("");
                    break;
                case 1:
                    if(page == totalPage){
                        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_LONG).show();
                        listView.onRefreshComplete();
                        listView.setLoadMore(false);

                    }else {
                        page++;
                        getListData("");
                    }
                    break;
                case Constant.SUCCESS:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    wuliaosData = msg.getData().getString("wuliaos");
                    int cout = msg.getData().getInt("count");
                    if(cout % pageSize > 0){
                        totalPage = cout / pageSize + 1;
                    }else{
                        totalPage = cout / pageSize;
                    }
                    if(wuliaosData=="null"){
                        noContent.setVisibility(View.VISIBLE);
                        break;
                    }
                    try {
                        JSONArray objArray = new JSONArray(wuliaosData);
                        if(objArray.length()==0){
                            noContent.setVisibility(View.VISIBLE);
                            break;
                        }
//                        JSONArray objArray = JSONArray.fromObject(wuliaosData);
                        for(int i = 0; i < objArray.length(); i++){
                            Material ml = new Material();
                            JSONObject obj = new JSONObject(String.valueOf(objArray.getJSONObject(i)));
//                            JSONObject obj = objArray.getJSONObject(i);
                            ml.setMid(obj.getInt("id"));
                            ml.setEncode(obj.getString("materialCode"));
                            ml.setDescribe(obj.getString("description"));
                            ml.setProvider(obj.getString("providerDepName"));
                            ml.setStockNum(Constant.formatCount(obj.getString("isCount")));
                            Log.i(LOGTag+"enableSn-----",String.valueOf(obj.getString("enableSn")));
                            ml.setIsSerialInt(String.valueOf(obj.getString("enableSn"))=="null"?0:obj.getInt("enableSn"));

                            ml.setSerial(obj.getString("snCode")=="null"?"":obj.getString("snCode"));

                            ml.setUnit(obj.getString("detailUnitName"));
                            ml.setStoreName(obj.getString("houseName"));
                            ml.setStoreLocation(obj.getString("storeLocationCode"));
                            mData.add(ml);
                        }
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
//                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),"数据解析失败",Toast.LENGTH_LONG).show();
                    }
                    break;
                case Constant.FAIL:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    noContent.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "查询移库移位单失败！", Toast.LENGTH_LONG).show();

                    break;
                case Constant.NETWORK_ERROR:
                    progressDialog.dismiss();
                    listView.onRefreshComplete();
                    noContent.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "查询移库移位单出错！", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };
    public void getListData(final String materialCode){
        progressDialog.setMessage("正在加载，请稍等......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = Constant.QUERY_ALL_WULIAO;//+"?appFlag=1&userId=" +userInfo.id+"&menuCode=sheet_YW&materialCode="+materialCode+"&page="+page+"&limit="+pageSize;
                try {
                    Message message = new Message();
                    String json = "appFlag=1&userId=" +userInfo.id+"&menuCode=sheet_YW&materialCode="+materialCode+"&page="+page+"&limit="+pageSize;
                    String responseData = HTTP.queryPost(json,address);
                    if(responseData!=null){
                        Log.i(LOGTag+"responseData----",responseData);
                        JSONObject obj = new JSONObject(responseData);
//                        JSONObject obj = JSONObject.fromObject(responseData);
                        if(obj.getInt("code")==0){
                            Bundle bundle = new Bundle();
                            //传递参数
                            bundle.putString("wuliaos", obj.getString("data"));
                            bundle.putInt("count", obj.getInt("count"));
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
        if (requestCode == Constant.REQ_QR_CODE && resultCode == getActivity().RESULT_OK) {
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
    public void onStart() {
        super.onStart();
        if (isAdded()) {//判断Fragment已经依附Activity
            receiveOrder = getArguments().getString(Constant.RECEIVE_FRAG_ORDER);
            orderid = getArguments().getInt(Constant.RECEIVE_FRAG_ORDER_ID,0);
            Log.i("ReceiveFrag",receiveOrder);
            mData.clear();
            getListData("");
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_search){
            String materialCode = eWuliao.getText().toString();
            mData.clear();
            getListData(materialCode);
        }else  if (id == R.id.btn_scan){
            startQrCode();
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


}
