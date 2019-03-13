package com.zthc.ewms.sheet.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.AssertUtils;
import com.zthc.ewms.base.util.DateUtils;
import com.zthc.ewms.base.util.ExcelImport;
import com.zthc.ewms.base.util.FileUtils;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.SheetCGDao;
import com.zthc.ewms.sheet.dao.SheetDao;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.zthc.ewms.sheet.entity.guard.SheetExcel;
import com.zthc.ewms.sheet.entity.guard.SheetRK;
import com.zthc.ewms.sheet.entity.guard.SheetRKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetRkSonDetail;
import com.zthc.ewms.sheet.entity.guard.SheetStock;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.sheet.service.guard.SheetDetailServiceGuard;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.service.MaterialService;
import com.zthc.ewms.system.provider.service.ProviderService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;

import drk.system.AppConfig;
import drk.system.Log;


@Service
public class SheetDetailService extends SheetDetailServiceGuard {


    @Autowired
    ProviderService providerService;
    @Autowired
    MaterialService materialService;
    @Autowired
    WareHouseService wareHouseService;
    @Autowired
    SheetRKService sheetRKService;
    @Autowired
    SheetRKDETAILService sheetRKDETAILService;
    @Autowired
    public SheetDao sheetDao;
    @Autowired
    public SheetCGDao sheetCGDao;


    private final static Log log = Log.getLog(SheetDetailService.class.getName());

    public static String TYPE = "ZR";

    /**
     * 添加明细页面 数据列表
     *
     * @param obj
     * @param condition
     * @param ztId
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> LayuiPage<T> addDetailList(SheetDetail obj, SheetDetailCondition condition, Integer ztId, String className) {

        return this.dao.addDetailList(obj, condition, ztId, className);
    }

    /**
     * 新增页面，明细列表
     *
     * @param sheetId
     * @param condition
     * @param <T>
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> LayuiPage<T> sheetDetails(String sheetId, SheetDetailCondition condition, String className) {
        LayuiPage<T> ret = null;
        if (StringUtils.isEmpty(sheetId)) {
            ret = new LayuiPage<>();
            ret.setCount(0L);
            ret.setData(null);
        } else {
            ret = this.dao.sheetDetails(Integer.parseInt(sheetId), condition, className);
        }
        return ret;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> List<T> sheetDetails(Integer sheetId, String className) {
        return this.dao.sheetDetails(sheetId, className);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<OrderDetails> getOrderDetailOne(String orderNum) {
        return this.dao.getOrderDetailOne(orderNum);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> LayuiPage<T> sheetSubDetails(String sheetId, String materialCode, Integer storeId, SheetDetailCondition condition, String className) {
        LayuiPage<T> ret = null;
        if (StringUtils.isEmpty(sheetId)) {
            ret = new LayuiPage<>();
            ret.setCount(0L);
            ret.setData(null);
        } else {
            ret = this.dao.sheetSubDetails(Integer.parseInt(sheetId), materialCode, storeId, condition, className);
        }
        return ret;
    }

    /**
     * 修改物料申领明细
     *
     * @param obj
     * @return
     */
    @Transactional
    public int updateApplySheet(SheetDetail obj) {
        return this.dao.updateApplySheet(obj);
    }

    /**
     * 删除明细方法
     **/
    @Transactional
    public void delDetails(SheetDetailCondition condition) {
        int i = this.dao.delDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    /**
     * 删除明细方法
     **/
    @Transactional
    public void delRKDetails(SheetDetailCondition condition) {
        int i = this.dao.delRKDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    /**
     * 删除明细方法
     **/
    @Transactional
    public void delZRDetails(SheetDetailCondition condition) {
        int i = this.dao.delZRDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    /**
     * 删除明细明细方法
     **/
    @Transactional
    public void delSheetDetail(Integer sheetId) {
        this.dao.delSheetDetail(sheetId);
    }

    @Transactional
    public <T> List<T> printDetails(Integer id, String where, String className) {
        return this.dao.printDetails(id, where, className);
    }

    @Transactional
    public List<OrderDetails> printOrderDetail(String orderNum) {
        return this.dao.printOrderDetails(orderNum);
    }

    /**
     * 导入库存结果
     *
     * @param file
     * @param userId
     * @param departId
     * @param ztId
     */
    @Transactional
    public String[] importKCResult(MultipartFile file, Integer userId, Integer departId, Integer ztId) throws IOException {
        //MultipartFile转换为文件流
        InputStream inputStream = FileUtils.convertStream(file);
        // 根据后缀名称判断excel的版本
        Workbook workbook = FileUtils.readExcel(file.getOriginalFilename(), inputStream);
        if (workbook == null) {
            throw new IOException();
        }
        //解析Excel
        return analysisExcels(workbook, userId, departId, ztId);
    }

    /**
     * 解析excel
     *
     * @param workbook
     * @return
     */
    @Transactional
    public String[] analysisExcels(Workbook workbook, Integer userId, Integer departId, Integer ztId) {
        //获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        SheetExcel sheetExcel;
        StringBuffer strBuilder = new StringBuffer();
        for (int i = 1; i < rownum; i++) {
            if (sheet.getRow(i) != null) {
                try {
                    //物料
                    String materialCode = ExcelImport.getValue(sheet.getRow(i).getCell(1));
                    //库房编码
                    String storehouseCode = ExcelImport.getValue(sheet.getRow(i).getCell(4));
                    if (StringUtils.isNotEmpty(materialCode) || StringUtils.isNotEmpty(storehouseCode)) {
                        //获取库存excel
                        sheetExcel = getSheetExcel(sheet.getRow(i), userId, ztId);
                        //保存解析数据
                        saveDetailExcel(sheetExcel, userId, departId, ztId);
                    }

                } catch (IllegalStateException e) {
                    strBuilder.append("第").append(i + 1).append("行,").append(e.getMessage()).append(";");
                } catch (Exception e) {
                    log.errorPrintStacktrace(e);
                    log.error("【库存量导入】 错误信息：第" + i + "行," + e.getMessage());
                    strBuilder.append("第").append(i + 1).append("行,").append(e.getMessage()).append(";");
                }
            }
        }
        if (StringUtils.isNotEmpty(strBuilder.toString())) {
            log.error("【库存量导入】 错误信息：" + strBuilder.toString());
            return new String[]{"0", strBuilder.toString()};
        } else {
            return new String[]{"1", "导入成功"};
        }
    }

    /**
     * 保存解析数据
     *
     * @param sheetExcel
     * @param userId
     * @param departId
     * @param ztId
     */
    @Transactional
    public void saveDetailExcel(SheetExcel sheetExcel, Integer userId, Integer departId, Integer ztId) throws SQLException {
        //保存单据
        SheetRK sheetRK = saveSheetExcel(sheetExcel, userId, departId, ztId);
        //保存明细
        SheetRKDETAIL sheetRKDETAIL = saveSheetDetailExcel(sheetExcel, sheetRK.getId(), userId, ztId);
        //编辑库位
        editorStorehouse(sheetExcel, sheetRKDETAIL.getId());
       /* //调用事务方法
        sheetDao.auditSheet(sheetRK.getId(), "SHEETRK_COMMIT", userId);*/
        //保存物资Stock
        saveSheetStock(sheetExcel, sheetRKDETAIL);
    }

    /**
     * @param sheetExcel
     * @param detail
     */
    @Transactional
    public void saveSheetStock(SheetExcel sheetExcel, SheetRKDETAIL detail) {
        SheetStock stock = new SheetStock();
        stock.setGuid(UUID.randomUUID().toString());
        stock.setTagCode(detail.getTagcode());
        stock.setSheetId(detail.getSheetid());
        stock.setSheetDetailId(detail.getId());
        stock.setCategoryId(detail.getCategoryid());
        stock.setMaterialId(detail.getMaterialid());
        stock.setMaterialCode(detail.getMaterialcode());
        stock.setMaterialName(detail.getMaterialname());
        stock.setMaterialBrand(detail.getMaterialbrand());
        stock.setMaterialModel(detail.getMaterialmodel());
        stock.setMaterialSpecification(detail.getMaterialspecification());
        stock.setDescription(detail.getDescription());
        stock.setStoreCount(sheetExcel.getAllotNumber());
        stock.setDetailUnit(detail.getDetailunit());
        stock.setNoTaxPrice(detail.getNotaxprice());
        stock.setNoTaxPrice(detail.getNotaxprice());
        stock.setCurrencyUnit(detail.getCurrencyunit());
        stock.setCurrencyUnit(detail.getCurrencyunit());
        stock.setStoreLocationId(sheetExcel.getStoreLocationId());
        stock.setStoreLocationCode(sheetExcel.getStoreLocationCode());
        stock.setStoreLocationName(sheetExcel.getStoreLocationName());
        stock.setProviderDepId(0);
        stock.setStoreId(sheetExcel.getStorehouseId());
        stock.setPlanDepartID(detail.getPlandepartid());
        stock.setStatus(detail.getStatus());
        stock.setMemo(detail.getMemo());
        stock.setCreator(detail.getCreator());
        stock.setCreateDate(detail.getCreatedate());
        stock.setExpirationTime(detail.getExpirationtime());
        stock.setZTID(detail.getZtid());
        stock.setIsEquipment(detail.getIsequipment());
        stock.setOwnerType(detail.getOwnertype());
        stock.setEnableSn(detail.getEnablesn());

        stock.setDetailUnitName(detail.getDetailunitname());

        dao.saveSheetStock(stock);

    }

    /**
     * 获取库存excel
     *
     * @return
     */
    @Transactional
    public SheetExcel getSheetExcel(Row row, Integer userId, Integer ztId) {
        SheetExcel sheetExcel = new SheetExcel();

        //库存组织、业务实体
        String inventoryName = ExcelImport.getValue(row.getCell(0));
        sheetExcel.setInventoryName(inventoryName);
        sheetExcel.setBusinessEntity(inventoryName);
        //物料
        String materialCode = ExcelImport.getValue(row.getCell(1));

        AssertUtils.notEmpty(materialCode, "物料编码不能为空");
        Material material = materialService.getMaterialByCode(materialCode);
        Assert.notNull(material, "物料不存在");
        sheetExcel.setMaterialId(material.getId());
        sheetExcel.setMaterialCode(material.getCode());
        sheetExcel.setMaterialName(material.getName());
        sheetExcel.setCateGoryId(material.getSparescateId());
        sheetExcel.setMaterialSpecification(material.getSpecifications());
        sheetExcel.setMaterialDescription(material.getDescription());
        sheetExcel.setMaterialUnit(material.getUnit());

        //库房编码
        String storehouseCode = ExcelImport.getValue(row.getCell(4));

        AssertUtils.notEmpty(storehouseCode, "库房编码不能为空");
        WareHouse wareHouse = wareHouseService.getWareHouseByCodeAndStatus(storehouseCode, DictionaryEnums.Status.ENABLE.getCode());
        Assert.notNull(wareHouse, "库房不存在");
        sheetExcel.setStorehouseId(wareHouse.getId());
        sheetExcel.setStorehouseName(wareHouse.getName());
        //库位
        String storeLocationCode = ExcelImport.getValue(row.getCell(5));
        WareHouse location=null;
        if (StringUtils.isNotEmpty(storeLocationCode)) {
            location = wareHouseService.getWareHouseByCodeAndStatus(storeLocationCode, DictionaryEnums.Status.ENABLE.getCode());
            Assert.notNull(wareHouse, "库位不存在");
        } else {
            List<WareHouse> wareHouseList = wareHouseService.findByParentId(wareHouse.getId());
            //获取默认库位名称
            String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");
            Boolean bool = false;
            for (WareHouse house : wareHouseList) {
                if (house.getName().equals(defaultStoreHouse)) {
                    location = wareHouse;
                    bool = true;
                    break;
                }
            }
            //没有默认库位，新增默认库位
            if (!bool) {
                location = this.sheetRKDETAILService.addWareHouse(defaultStoreHouse, wareHouse.getId(), ztId, userId);
            }
        }
        Assert.notNull(location, "库位不能为空");
        sheetExcel.setStoreLocationId(location.getId());
        sheetExcel.setStoreLocationCode(location.getCode());
        sheetExcel.setStoreLocationName(location.getName());
        sheetExcel.setStoreLocationCode(location.getCode());
        //数量
        String numStr = ExcelImport.getValue(row.getCell(6));
        AssertUtils.notEmpty(numStr, "数量不能为空");
        Double num = Double.parseDouble(numStr);
        sheetExcel.setNum(num);
        //单价
        String univalentStr = ExcelImport.getValue(row.getCell(7));
        AssertUtils.notEmpty(univalentStr, "单价不能为空");
        Double univalent = Double.parseDouble(univalentStr);
        sheetExcel.setUnivalent(univalent);
        //税率
        String taxRateStr = ExcelImport.getValue(row.getCell(8));
        AssertUtils.notEmpty(taxRateStr, "税率不能为空");
        Double taxRate = Double.parseDouble(taxRateStr);
        sheetExcel.setTaxRate(taxRate);
        //不含税金额
        Double noTaxPrice = num * univalent;
        sheetExcel.setNoTaxPrice(noTaxPrice);
        //含税金额
        Double taxPrice = num * univalent * (1 + taxRate);
        sheetExcel.setTaxPrice(taxPrice);
        //分配数量
        String allotNumber = ExcelImport.getValue(row.getCell(9));
        AssertUtils.notEmpty(allotNumber, "分配数量不能为空");
        sheetExcel.setAllotNumber(Double.parseDouble(allotNumber));
        //计划部门
        String projectDivisionId = ExcelImport.getValue(row.getCell(10));
        if (StringUtils.isEmpty(projectDivisionId)) {
            sheetExcel.setProjectDivisionId(null);
        } else {
            sheetExcel.setProjectDivisionId(Integer.parseInt(projectDivisionId));
        }
        //是否是设备
        String ifEquipment = ExcelImport.getValue(row.getCell(11));
        AssertUtils.notEmpty(ifEquipment, "是否是设备不能为空");
        sheetExcel.setIfEquipment(Integer.parseInt(ifEquipment));
        //是否启用序列号
        String ifSerialNumber = ExcelImport.getValue(row.getCell(12));
        AssertUtils.notEmpty(ifSerialNumber, "是否启用序列号不能为空");
        sheetExcel.setIfSerialNumber(Integer.parseInt(ifSerialNumber));

        //生产日期
        String productionDate = ExcelImport.getValue(row.getCell(13));
        AssertUtils.notEmpty(productionDate, "生产日期不能为空");
        sheetExcel.setProductionDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD, productionDate));
        //保质期限
        String shelfLifeDate = ExcelImport.getValue(row.getCell(14));
        if (StringUtils.isNotEmpty(shelfLifeDate)) {
            sheetExcel.setShelfLifeDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD, shelfLifeDate));
        }
        return sheetExcel;
    }

    /**
     * 保存单据
     *
     * @param sheetExcel
     * @param userId
     * @param departId
     * @param ztId
     */
    @Transactional
    public SheetRK saveSheetExcel(SheetExcel sheetExcel, Integer userId, Integer departId, Integer ztId) throws SQLException {
        SheetRK sheetRK = new SheetRK();
        Date now = new Date();
        String code = sheetRKService.getCode(TYPE, 0f);
        if (code == null) {
            throw new RuntimeException("单据保存失败：生成单据编码失败");
        }
        sheetRK.setCode(code);
        sheetRK.setGuid(UUID.randomUUID().toString());
        sheetRK.setName("物资杂入单");
        sheetRK.setStatus(41);
        sheetRK.setZtId(ztId);
        sheetRK.setDepartId(departId);
        sheetRK.setCreator(userId);
        sheetRK.setCreateDate(now);
        sheetRK.setSubmitTime(now);
        sheetRK.setExtendString2(sheetExcel.getInventoryName());
        sheetRK.setExtendString4(sheetExcel.getBusinessEntity());
        sheetRKService.saveSheet(sheetRK);

        return sheetRK;
    }

    /**
     * 保存明细
     *
     * @param sheetExcel
     * @param sheetId
     */
    @Transactional
    public SheetRKDETAIL saveSheetDetailExcel(SheetExcel sheetExcel, Integer sheetId, Integer userId, Integer ztId) {
        Date now = new Date();
        SheetRKDETAIL sheetRKDETAIL = new SheetRKDETAIL();
        sheetRKDETAIL.setGuid(UUID.randomUUID().toString());
        sheetRKDETAIL.setCreator(userId);
        sheetRKDETAIL.setCreatedate(now);
        sheetRKDETAIL.setZtid(ztId);
        sheetRKDETAIL.setSheetid(sheetId);
        sheetRKDETAIL.setOwnertype(610);
        //物料
        sheetRKDETAIL.setCategoryid(sheetExcel.getCateGoryId());
        sheetRKDETAIL.setMaterialid(sheetExcel.getMaterialId());
        sheetRKDETAIL.setMaterialcode(sheetExcel.getMaterialCode());
        sheetRKDETAIL.setMaterialname(sheetExcel.getMaterialName());
        sheetRKDETAIL.setMaterialspecification(sheetExcel.getMaterialSpecification());
        sheetRKDETAIL.setDescription(sheetExcel.getMaterialDescription());
        sheetRKDETAIL.setDetailunitname(sheetExcel.getMaterialUnit());
        //库位、计划部门
        sheetRKDETAIL.setExtendstring1(sheetExcel.getStorehouseCode());
        sheetRKDETAIL.setPlandepartid(sheetExcel.getProjectDivisionId());
        //金额相关
        sheetRKDETAIL.setNotaxprice(sheetExcel.getUnivalent());
        sheetRKDETAIL.setTaxRate(sheetExcel.getTaxRate());
        sheetRKDETAIL.setNotaxsum(sheetExcel.getNoTaxPrice());
        sheetRKDETAIL.setTaxsum(sheetExcel.getTaxPrice());
        sheetRKDETAIL.setExtendfloat1(sheetExcel.getTaxPrice());
        sheetRKDETAIL.setDetailcount(sheetExcel.getNum());

        sheetRKDETAIL.setIsequipment(sheetExcel.getIfEquipment());
        sheetRKDETAIL.setEnablesn(sheetExcel.getIfSerialNumber());
        sheetRKDETAIL.setExtenddate2(sheetExcel.getProductionDate());
        sheetRKDETAIL.setExpirationtime(sheetExcel.getShelfLifeDate());

        SheetDetail sheetDetail = new SheetDetail();
        sheetDetail.setGuid(sheetRKDETAIL.getGuid());
        sheetDetail.setSheetId(sheetRKDETAIL.getSheetid());
        sheetDetail.setCategoryId(sheetRKDETAIL.getCategoryid());
        sheetDetail.setMaterialId(sheetRKDETAIL.getMaterialid());
        sheetDetail.setMaterialCode(sheetRKDETAIL.getMaterialcode());
        sheetDetail.setMaterialName(sheetRKDETAIL.getMaterialname());
        sheetDetail.setProviderDepId(sheetRKDETAIL.getProviderdepid());
        sheetDetail.setCreator(sheetRKDETAIL.getCreator());
        sheetDetail.setCreateDate(sheetRKDETAIL.getCreatedate());
        sheetDetail.setZtId(sheetRKDETAIL.getZtid());
        sheetDetail.setExtendFloat1(sheetRKDETAIL.getExtendfloat1());
        sheetDetail.setExtendString1(sheetRKDETAIL.getExtendstring1());
        sheetDetail.setNoTaxPrice(sheetRKDETAIL.getNotaxprice());
        sheetDetail.setTaxRate(sheetRKDETAIL.getTaxRate());
        sheetDetail.setNoTaxSum(sheetRKDETAIL.getNotaxsum());
        sheetDetail.setMaterialSpecification(sheetRKDETAIL.getMaterialspecification());
        sheetDetail.setDescription(sheetRKDETAIL.getDescription());
        sheetDetail.setExpirationTime(sheetRKDETAIL.getExpirationtime());
        sheetDetail.setTaxPrice(sheetRKDETAIL.getTaxprice());
        sheetDetail.setTaxSum(sheetRKDETAIL.getTaxsum());
        sheetDetail.setPlanDepartId(sheetRKDETAIL.getPlandepartid());
        sheetDetail.setDetailCount(sheetRKDETAIL.getDetailcount());
        sheetDetail.setIsEquipment(sheetRKDETAIL.getIsequipment());
        sheetDetail.setOwnerType(sheetRKDETAIL.getOwnertype());
        sheetDetail.setDetailUnitName(sheetRKDETAIL.getDetailunitname());
        this.saveSheetDetail(sheetDetail, null);

        sheetRKDETAIL.setSheetdetailid(sheetDetail.getId());

        sheetRKDETAILService.saveSheetDetail(sheetRKDETAIL);
        return sheetRKDETAIL;
    }

    /**
     * 编辑库位
     *
     * @param sheetExcel
     */
    @Transactional
    public void editorStorehouse(SheetExcel sheetExcel, Integer detailId) {
        Date now = new Date();
        SheetRkSonDetail sheetRkSonDetail = new SheetRkSonDetail();
        sheetRkSonDetail.setGuid(UUID.randomUUID().toString());
        sheetRkSonDetail.setDetailId(detailId);
        sheetRkSonDetail.setSubStock(0);
        sheetRkSonDetail.setSubDetailCount(sheetExcel.getAllotNumber());
        //库房、库位
        sheetRkSonDetail.setStoreID(sheetExcel.getStorehouseId());
        sheetRkSonDetail.setStoreLocationId(sheetExcel.getStoreLocationId());
        sheetRkSonDetail.setStoreLocationCode(sheetExcel.getStoreLocationCode());
        sheetRkSonDetail.setStoreLocationName(sheetExcel.getStoreLocationName());

        sheetRkSonDetail.setAddTime(now);
        sheetRkSonDetail.setUnitName(sheetExcel.getMaterialUnit());

        sheetRKDETAILService.addSonDetail(sheetRkSonDetail);
    }
    @Transactional
	public void editSheetDetails(List<SheetDetail> detailList) {
		for (SheetDetail sheetDetail : detailList) {
			sheetCGDao.editSheetDetails(sheetDetail);
		}
	}
}
