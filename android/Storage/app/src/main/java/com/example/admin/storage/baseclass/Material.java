package com.example.admin.storage.baseclass;

/**
 * Created by admin on 2018/4/17.
 */

public class Material {
    /**
     * 物料id
     */
    private int mid;
    private String ztid;
    /**
     * 单据id
     */
    private int sheetDetailId;
    /**
     * 单据id
     */
    private int sheetId;
    private String sncode;

    private String uuid;
    /**
     * 采购订单
     */
    private String buyOrder;

    /**
     * 物料编码
     */
    private String encode;

    /**
     * 物料描述
      */
    private String describe;

    /**
     * 供应商
     */
    private String provider;

    /**
     * 采购数量
     */
    private String buyNum;

    /**
     * 可接收数量
     */
    private String canReceiveNum;

    /**
     * 已接收数量/入库中的接收数量
     */
    private String receivedNum;

    /**
     * 可入库数量
     */
    private String canPutawayNum;

    /**
     * 已入库数量
     */
    private String putawayNum;

    /**
     * 库存数量
     */
    private String stockNum;

    /**
     * 申请出库数量
     */
    private String applyoutNum;

    /**
     * 申请出库未出库数量
     */
    private String applyNooutNum;

    /**
     * 申请出库已出库数量
     */
    private String applyHasoutNum;

    /**
     * 移库移位待入库数量
     */
    private String needIntoNum;
    /**
     * 单位
     */
    private String unit;

    /**
     * 是否是设备
     */
    private int isEquipment;

//    /**
//     * 启用序列号
//     */
//    private String isSerial;
    /**
     * 启用序列号int
     */
    private int isSerialInt;
    /**
     * 序列号
     */
    private String serial;

    /**
     * 寄售类型
     */
    private String salesType;

    /**
     * 库房
     */
    private String storeName;
    /**
     * 库位
     */
    private String storeLocation;

    /**
     * 目标库房
     */
    private String targetStorage;
    /**
     * 目标库位
     */
    private String targetStoreLocation;


    /**
     * 拥有方
     */
    private String owner;



    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getCanReceiveNum() {
        return canReceiveNum;
    }

    public void setCanReceiveNum(String canReceiveNum) {
        this.canReceiveNum = canReceiveNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(String receivedNum) {
        this.receivedNum = receivedNum;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPutawayNum() {
        return putawayNum;
    }

    public void setPutawayNum(String putawayNum) {
        this.putawayNum = putawayNum;
    }

    public String getCanPutawayNum() {
        return canPutawayNum;
    }

    public void setCanPutawayNum(String canPutawayNum) {
        this.canPutawayNum = canPutawayNum;
    }



    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getApplyoutNum() {
        return applyoutNum;
    }

    public void setApplyoutNum(String applyoutNum) {
        this.applyoutNum = applyoutNum;
    }

    public String getApplyNooutNum() {
        return applyNooutNum;
    }

    public void setApplyNooutNum(String applyNooutNum) {
        this.applyNooutNum = applyNooutNum;
    }

    public String getApplyHasoutNum() {
        return applyHasoutNum;
    }

    public void setApplyHasoutNum(String applyHasoutNum) {
        this.applyHasoutNum = applyHasoutNum;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNeedIntoNum() {
        return needIntoNum;
    }

    public void setNeedIntoNum(String needIntoNum) {
        this.needIntoNum = needIntoNum;
    }

    public String getTargetStorage() {
        return targetStorage;
    }

    public void setTargetStorage(String targetStorage) {
        this.targetStorage = targetStorage;
    }

    public String getTargetStoreLocation() {
        return targetStoreLocation;
    }

    public void setTargetStoreLocation(String targetStoreLocation) {
        this.targetStoreLocation = targetStoreLocation;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }



    public String getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(String buyOrder) {
        this.buyOrder = buyOrder;
    }

    public int getIsEquipment() {
        return isEquipment;
    }

    public void setIsEquipment(int isEquipment) {
        this.isEquipment = isEquipment;
    }

    public int getIsSerialInt() {
        return isSerialInt;
    }

    public void setIsSerialInt(int isSerialInt) {
        this.isSerialInt = isSerialInt;
    }

    public int getSheetDetailId() {
        return sheetDetailId;
    }

    public void setSheetDetailId(int sheetDetailId) {
        this.sheetDetailId = sheetDetailId;
    }

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public String getZtid() {
        return ztid;
    }

    public void setZtid(String ztid) {
        this.ztid = ztid;
    }

    public String getSncode() {
        return sncode;
    }

    public void setSncode(String sncode) {
        this.sncode = sncode;
    }

//    public String getIsEquipmentStr() {
//        return isEquipmentStr;
//    }
//
//    public void setIsEquipmentStr(int isEquipmentStr) {
//        if(isEquipmentStr==1){
//            this.isEquipmentStr = "是";
//        }else{
//            this.isEquipmentStr = "否";
//        }
//    }
}
