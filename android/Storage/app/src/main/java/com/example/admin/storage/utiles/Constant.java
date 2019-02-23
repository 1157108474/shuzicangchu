package com.example.admin.storage.utiles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

import com.example.admin.storage.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常量
 */
public class Constant {
    //接口
//    public static final String SERVER_BASE = "http://192.168.1.125:8080/";//李成
//    public static final String SERVER_BASE = "http://192.168.1.135:8080/";//纪元
//    public static final String SERVER_BASE = "http://192.168.1.108:8087/";//越要彬
//    public static final String SERVER_BASE = "http://192.168.1.103:8080/";//高温君
//    public static final String SERVER_BASE = "http://192.168.1.127:8081/";
    public static final String SERVER_BASE = "http://222.186.130.213:24040/";

    //登录
    public static final String LOGIN = SERVER_BASE + "system/auth/appLogin.json";
    //采购接收
    public static final String GET_NO_FINISHED_RECEIVE = SERVER_BASE + "sheet/wzjs/listManageOrder.json";
    //    public static final String GET_MOVESTORE_BY_ID = SERVER_BASE + "";
    public static final String INSERT_RECEIVE = SERVER_BASE + "sheet/wzjs/WZJS";
    public static final String QUERY_ALL_BUYORDER = SERVER_BASE + "sheet/wzjs/listOrderGeneral.json";
    public static final String QUERY_ALL_WULIAO_BUY = SERVER_BASE + "sheet/wzjs/listDetails.json";
    public static final String QUERY_ALL_WULIAO_RECEIVED= SERVER_BASE + "sheet/wzjs/listJSDDetails.json";
    public static final String SAVE_RECEIVE = SERVER_BASE + "sheet/detail/addWZJSDetails";
    //    public static final String QUERY_YKYW_DETAIL = SERVER_BASE + "sheet/ykyw/details";
//    public static final String GET_NO_FINISHED_MOVESTORE = SERVER_BASE + "";
    //入库上架
    public static final String GET_NO_FINISHED_INTOSTORE = SERVER_BASE + "sheet/rk/listManageRk.json";
    //    public static final String GET_MOVESTORE_BY_ID = SERVER_BASE + "";
    public static final String INSERT_INTOSTORE = SERVER_BASE + "sheet/rk/RK";//
    public static final String QUERY_RECEIPT = SERVER_BASE + "sheet/rk/listJSDList.json";//查询未入库的接收单
    public static final String QUERY_RECEIPT_WULIAOS = SERVER_BASE + "sheet/rk/listDetails.json";//查询所有物料
    public static final String SAVE_INTOSTORE = SERVER_BASE + "sheet/rkDetail/addRKDetails";//"sheet/ykyw/addDetails";//保存明细
    public static final String QUERY_INTO_DETAIL = SERVER_BASE + "sheet/rk/listRkDetails.json";
    //出库下架
    public static final String GET_NO_FINISHED_OUTSTORE = SERVER_BASE + "sheet/ck/listManageCK.json";
//    public static final String GET_MOVESTORE_BY_ID = SERVER_BASE + "";
    public static final String INSERT_OUTSTORE = SERVER_BASE + "sheet/ck/saveSheetCK.json";//创建出库单
    public static final String QUERY_APPLIES = SERVER_BASE + "sheet/apply/listSqNum.json";//查询申领单
    public static final String QUERY_OUTSTORE_WULIAOS = SERVER_BASE + "sheet/ck/listSQCKDetail.json";//查询所有物料
    public static final String QUERY_OUTSTORE_DETAIL = SERVER_BASE + "sheet/ck/listSheetCK.json";//查询所有物料
    public static final String SELECT_WULIAO = SERVER_BASE + "sheet/ck/listCldetails.json";
    public static final String SAVE_OUTSTORE = SERVER_BASE + "sheet/ck/saveDetail.json";//"sheet/ykyw/addDetails";//保存明细
    public static final String APART_OUTSTORE = SERVER_BASE + "sheet/ck/splitSheet.json";
    //移库移位
    public static final String GET_NO_FINISHED_MOVESTORE = SERVER_BASE + "sheet/ykyw/sheets";
    public static final String GET_STORAGES = SERVER_BASE + "system/ware/listLocation";
    public static final String INSERT_YKYW = SERVER_BASE + "sheet/YKYW";//创建移库移位单
    public static final String QUERY_ALL_WULIAO = SERVER_BASE + "sheet/ykyw/getAddDetails";//查询所有物料
    public static final String SAVE_YKYW = SERVER_BASE + "sheet/ykyw/addDetailForApp";//"sheet/ykyw/addDetails";//保存明细
    public static final String QUERY_YKYW_DETAIL = SERVER_BASE + "sheet/ykyw/details";



    //盘点
    public static final String QUERY_ALL_PANDIAN = SERVER_BASE + "sheet/pd/manageKcpd.json";
    public static final String QUERY_PANDIAN_DETAIL = SERVER_BASE + "sheet/pd/wzpdDetailApp.json";
    public static final String UPDATE_PANDIAN = SERVER_BASE + "sheet/pd/editDetail.json";
    public static final String ADD_PANYING = SERVER_BASE + "sheet/pd/addPYdata.json";

    //库存查询
    public static final String QUERY_ALL_M= SERVER_BASE + "sheet/query/queryKCDetailsList.json";

    // request参数
    public static final int REQ_QR_CODE = 11002; // // 打开扫描界面请求码
    public static final int REQ_PERM_CAMERA = 11003; // 打开摄像头

    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";
    //参数传递
    public static final String RECEIVE_FRAG_ORDER = "order";
    public static final String RECEIVE_FRAG_ORDER_ID = "orderid";
    public static final String BUY_FRAG_ORDER_ID = "buy_order_id";
    public static final String BUY_FRAG_ORDER_CODE = "buy_order_code";
    public static final String FRAG_ORDER_CODE = "orderCode";
    public static final String FRAG_ORDER_ID = "orderId";
    public static final String JS_CODE = "jieshouCode";
    public static final String APPLY_ID = "applyId";
    public static final String APPLY_CODE = "applyCode";
    public static final int NETWORK_ERROR = 999;
    public static final int FAIL = 998;
    public static final int SUCCESS = 100;

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    public static void alert(Context context, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(message)
                .setTitle("提示")
                .setCancelable(false)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public static String formatCount(String count){
        NumberFormat nf = new DecimalFormat("#.##");
        double a = Double.parseDouble(count);
        return nf.format(a);

    }
}
