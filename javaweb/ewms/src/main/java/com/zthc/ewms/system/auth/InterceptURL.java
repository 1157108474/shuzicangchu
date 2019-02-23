package com.zthc.ewms.system.auth;

import java.util.ArrayList;
import java.util.List;

public class InterceptURL {

    /**
     * 存放登录前不需要拦截的Url
     *
     * @return
     */
    public static List<String> getLoginFrontUrl() {
        List<String> interceptUrls = new ArrayList<>();
        interceptUrls.add("/");//初始方法
        interceptUrls.add("/system/auth/login.json");//登录方法
        interceptUrls.add("/system/auth/logout.json");//注销方法
        interceptUrls.add("/login.jsp");//登录页
        interceptUrls.add("/login.html");//登录页
        interceptUrls.add("/system/auth/ssoLogin");//单点登录方法
        interceptUrls.add("/goError.jsp");//错误页面

        interceptUrls.add("/sheet/apply/listSqNum.json");//申领单接口
        interceptUrls.add("/sheet/ck/saveSheetCK.json");//APP接口(创建出库单（出库单单）)
        interceptUrls.add("/sheet/ck/listManageCK.json");//APP接口(查询制单中的出库单（出库单单）)
        interceptUrls.add("/sheet/ck/listSheetCK.json");//APP接口(查询出库单信息（已入库）)
        interceptUrls.add("/sheet/ck/listSQCKDetail.json");//APP接口(查询出库单信息（未入库）)
        interceptUrls.add("/sheet/ck/listCldetails.json");//APP接口(根据货位标签条形码获取货架上库存信息)
        interceptUrls.add("/system/ware/getWareHouseByCode.json");//APP接口(根据库位编码获取信息)
        interceptUrls.add("/system/material/getMaterialByCode.json");//APP接口(根据物料编码获取信息)

        interceptUrls.add("/system/ware/ifWareHouseByCode.json");//APP接口(根据库房编码查询是否有库位)

        interceptUrls.add("/sheet/rk/listManageRk.json");//APP接口 (查询未完成的入库单)
        interceptUrls.add("/sheet/rk/listRkDetails.json");//APP接口 查询入库单物料信息（已入库）
        interceptUrls.add("/sheet/rk/listDetails.json");//APP接口 查询入库单物料信息（未入库）
        interceptUrls.add("/sheet/rk/RK");//APP接口 (保存入库单据)
        interceptUrls.add("/sheet/rkDetail/addRKDetails");//APP接口 (保存入库明细)
        interceptUrls.add("/sheet/rk/listJSDList.json");//APP接口 (查询可入库单（接收单）)

        interceptUrls.add("/sheet/wzjs/listManageOrder.json");//APP接口 (查询未完成的接收单信息)
        interceptUrls.add("/sheet/wzjs/listDetails.json");//APP接口 查询接收单明细信息（未接收）
        interceptUrls.add("/sheet/wzjs/listJSDDetails.json");//APP接口 查询接收单明细信息（已接收）
        interceptUrls.add("/sheet/detail/addWZJSDetails");//APP接口 (保存接收明细)
        interceptUrls.add("/sheet/wzjs/WZJS");//APP接口 (保存接收单据)
        interceptUrls.add("/sheet/wzjs/listOrderGeneral.json");//APP接口 查询可接收的采购单（未接收的采购订单）
        interceptUrls.add("/services/AxisService");//axisWebService接口
        return interceptUrls;
    }
    /**
     * 存放登录后不需要拦截的Url
     *
     * @return
     */
    public static List<String> getLoginAfterUrl() {
        List<String> interceptUrls = new ArrayList<>();
        return interceptUrls;
    }
}
