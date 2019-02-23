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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.storage.R;
import com.example.admin.storage.adapters.BillAdapter;
import com.example.admin.storage.baseclass.Bill;
import com.example.admin.storage.baseclass.Material;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.Constant;
import com.example.admin.storage.utiles.HTTP;
import com.example.admin.storage.views.CustomPopupWindow;
import com.example.admin.storage.views.PullToRefreshListView;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MoveStoreActivity extends Activity implements View.OnClickListener, PullToRefreshListView.OnRefreshListener, CustomPopupWindow.OnItemClickListener {
    Button btnQrCode; // 扫码
    TextView tvResult; // 结果
    private ListView listView;
    private CustomPopupWindow mPop;
    //    private ArrayAdapter<List<String>> adapter0;
    private BillAdapter adapter;
    private List<Bill> mData = new ArrayList<Bill>();

    LinearLayout nolayout;
    EditText search_et;
//    private int page;
    private final int GET_OK = 20;
    private final int GET_FAIL = 21;
    private final int GET_ERROR = 22;
    ProgressDialog progressDialog = null;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_unfinished);

        initView();
        initData();

    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.id_back_btn).setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);
        TextView title = findViewById(R.id.id_title_tv);
        title.setText("移库移位单");
        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setVisibility(View.VISIBLE);
        btn_add.setOnClickListener(this);
        findViewById(R.id.id_search_bt).setOnClickListener(this);

//        findViewById(R.id.id_scan_bt).setOnClickListener(this);
        search_et = findViewById(R.id.search_et);
        nolayout = findViewById(R.id.nocontent);
        listView = (ListView) findViewById(R.id.id_listview_bill);
        adapter = new BillAdapter(this, mData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(MoveStoreActivity.this, "Click item" + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoveStoreActivity.this, YKYWDetailActivity.class);
                //用Bundle携带数据
                Bundle bundle = new Bundle();
                Bill mB = mData.get(i);

                //传递参数
                bundle.putString("ykyw", mB.getOrderNumber());
                bundle.putInt("ykywid", mB.getBid());
//                bundle.putInt("receiptId",1 );
                intent.putExtras(bundle);
                startActivity(intent);
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
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
//        userInfo = Settings.getInstance().getUserInfo();
//        getListData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mData.clear();
        getListData();
    }

    private void getListData() {
        final String ykywCode = search_et.getText().toString();
        progressDialog.setMessage("正在加载，请稍等......");//设置显示内容
        progressDialog.show();//将ProgessDialog显示出来
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Constant.GET_NO_FINISHED_MOVESTORE;//+"?appFlag=1&userId="+userInfo.id+"&page=1&limit=100000&code="+ykywCode;
                Log.i("获取未完成移库移位单url----",url);
                try {
                    Message message = new Message();

                    String json = "appFlag=1&userId="+userInfo.id+"&page=1&limit=100000&code="+ykywCode;

                    String responseData = HTTP.queryPost(json,url);
                    Log.i("ykyw responseData------",responseData.toString());
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

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_take_photo:
                mPop.dismiss();

                search_et.setCursorVisible(true);      //设置输入框中的光标不可见
                search_et.setFocusable(true);           //无焦点
                search_et.requestFocus();
                this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(search_et, 0);
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//                search_et.setFocusableInTouchMode(false);     //触摸时也得不到焦点
                Toast.makeText(getApplicationContext(), "拍照", Toast.LENGTH_LONG).show();
//                mPop.dismiss();
                break;
            case R.id.id_btn_select:
                mPop.dismiss();
//                Toast.makeText(getApplicationContext(),"从相册中选择", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_btn_cancelo:
                mPop.dismiss();
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
                if (mData.size() == 0) {
                    Toast.makeText(this, "没有未完成的单据", Toast.LENGTH_LONG);
                    return;
                }
//                if (search_et.getText().equals("")) {
//                    Toast.makeText(this, "请输入单号或扫描录入单号", Toast.LENGTH_LONG);
//                    return;
//                }
                mData.clear();
                getListData();
                break;
//            case R.id.id_scan_bt:
//                startQrCode();
//                break;
            case R.id.btn_add:
                creatYKYW();
//                Intent intent = new Intent(this, AddYKYWActivity.class);
//                startActivity(intent);
                break;

            default:
                break;
        }
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
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    Toast.makeText(getApplicationContext(), "刷新完成", Toast.LENGTH_LONG).show();
                    mData.clear();
                    getListData();
//
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "刷新完成", Toast.LENGTH_LONG).show();
//                    mData.clear();
                    getListData();
//                    listView.setLoadMore(false);
//                    getListData();
                    break;
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
                            Bill mBill = new Bill();
                            mBill.setBid(subObject.getInteger("id"));
                            mBill.setOrderNumber(subObject.getString("code"));
                            mBill.setCreator(subObject.getString("personName"));
                            mBill.setCreateTime(Constant.getDateToString(subObject.getLong("createDate"),"yyyy-MM-dd HH:mm:ss"));
                            mData.add(mBill);
                        }
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);

                    } catch (com.alibaba.fastjson.JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MoveStoreActivity.this,"数据解析失败",Toast.LENGTH_LONG).show();
                    }


                    break;
                case GET_FAIL:
                    progressDialog.dismiss();
                    Toast.makeText(MoveStoreActivity.this, "查询失败！", Toast.LENGTH_LONG).show();

                    break;
                case GET_ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(MoveStoreActivity.this, "查询出错！", Toast.LENGTH_LONG).show();
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

    class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            //这里是开始线程之前执行的,是在UI线程
//            mProgressBar.setMax(100);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String url = Constant.GET_NO_FINISHED_MOVESTORE + "?appFlag=1&userId=3284&page=1&limit=1000000";

            String responseData = HTTP.query(url);
            System.out.print("responseData------"+responseData);
            return responseData;
        }

        @Override
        protected void onPostExecute(String liststr) {
            super.onPostExecute(liststr);
//            mData.clear();
            try {
                Log.i("onPostExecute---","liststr------"+liststr);
                JSONObject object = new JSONObject(liststr);
                if(object.getInt("code") == 0){
                    JSONArray array = object.getJSONArray("data");    //得到为json的数组
                    for (int i = 0; i < array.length(); i++) {
                        System.out.println("---------------");
                        JSONObject subObject = array.getJSONObject(i);
                        Bill mBill = new Bill();
                        mBill.setBid(subObject.getInt("id"));
                        mBill.setOrderNumber(subObject.getString("code"));
                        mBill.setCreator(subObject.getString("creator"));
                        mBill.setCreateTime(Constant.getDateToString(subObject.getLong("createDate"),"yyyy-MM-dd HH:mm:ss"));
                        mData.add(mBill);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (mData.size() > 0) {
                nolayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

            } else {
                nolayout.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        }
    }

    ;
}
