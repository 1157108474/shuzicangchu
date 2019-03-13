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
     * �����ϸҳ�� �����б�
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
     * ����ҳ�棬��ϸ�б�
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
     * �޸�����������ϸ
     *
     * @param obj
     * @return
     */
    @Transactional
    public int updateApplySheet(SheetDetail obj) {
        return this.dao.updateApplySheet(obj);
    }

    /**
     * ɾ����ϸ����
     **/
    @Transactional
    public void delDetails(SheetDetailCondition condition) {
        int i = this.dao.delDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    /**
     * ɾ����ϸ����
     **/
    @Transactional
    public void delRKDetails(SheetDetailCondition condition) {
        int i = this.dao.delRKDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    /**
     * ɾ����ϸ����
     **/
    @Transactional
    public void delZRDetails(SheetDetailCondition condition) {
        int i = this.dao.delZRDetails(condition);
        if (condition.getIds().length != i) {
            throw new RuntimeException();
        }
    }

    /**
     * ɾ����ϸ��ϸ����
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
     * ��������
     *
     * @param file
     * @param userId
     * @param departId
     * @param ztId
     */
    @Transactional
    public String[] importKCResult(MultipartFile file, Integer userId, Integer departId, Integer ztId) throws IOException {
        //MultipartFileת��Ϊ�ļ���
        InputStream inputStream = FileUtils.convertStream(file);
        // ���ݺ�׺�����ж�excel�İ汾
        Workbook workbook = FileUtils.readExcel(file.getOriginalFilename(), inputStream);
        if (workbook == null) {
            throw new IOException();
        }
        //����Excel
        return analysisExcels(workbook, userId, departId, ztId);
    }

    /**
     * ����excel
     *
     * @param workbook
     * @return
     */
    @Transactional
    public String[] analysisExcels(Workbook workbook, Integer userId, Integer departId, Integer ztId) {
        //��ȡ��һ��sheet
        Sheet sheet = workbook.getSheetAt(0);
        //��ȡ�������
        int rownum = sheet.getPhysicalNumberOfRows();
        SheetExcel sheetExcel;
        StringBuffer strBuilder = new StringBuffer();
        for (int i = 1; i < rownum; i++) {
            if (sheet.getRow(i) != null) {
                try {
                    //����
                    String materialCode = ExcelImport.getValue(sheet.getRow(i).getCell(1));
                    //�ⷿ����
                    String storehouseCode = ExcelImport.getValue(sheet.getRow(i).getCell(4));
                    if (StringUtils.isNotEmpty(materialCode) || StringUtils.isNotEmpty(storehouseCode)) {
                        //��ȡ���excel
                        sheetExcel = getSheetExcel(sheet.getRow(i), userId, ztId);
                        //�����������
                        saveDetailExcel(sheetExcel, userId, departId, ztId);
                    }

                } catch (IllegalStateException e) {
                    strBuilder.append("��").append(i + 1).append("��,").append(e.getMessage()).append(";");
                } catch (Exception e) {
                    log.errorPrintStacktrace(e);
                    log.error("����������롿 ������Ϣ����" + i + "��," + e.getMessage());
                    strBuilder.append("��").append(i + 1).append("��,").append(e.getMessage()).append(";");
                }
            }
        }
        if (StringUtils.isNotEmpty(strBuilder.toString())) {
            log.error("����������롿 ������Ϣ��" + strBuilder.toString());
            return new String[]{"0", strBuilder.toString()};
        } else {
            return new String[]{"1", "����ɹ�"};
        }
    }

    /**
     * �����������
     *
     * @param sheetExcel
     * @param userId
     * @param departId
     * @param ztId
     */
    @Transactional
    public void saveDetailExcel(SheetExcel sheetExcel, Integer userId, Integer departId, Integer ztId) throws SQLException {
        //���浥��
        SheetRK sheetRK = saveSheetExcel(sheetExcel, userId, departId, ztId);
        //������ϸ
        SheetRKDETAIL sheetRKDETAIL = saveSheetDetailExcel(sheetExcel, sheetRK.getId(), userId, ztId);
        //�༭��λ
        editorStorehouse(sheetExcel, sheetRKDETAIL.getId());
       /* //�������񷽷�
        sheetDao.auditSheet(sheetRK.getId(), "SHEETRK_COMMIT", userId);*/
        //��������Stock
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
     * ��ȡ���excel
     *
     * @return
     */
    @Transactional
    public SheetExcel getSheetExcel(Row row, Integer userId, Integer ztId) {
        SheetExcel sheetExcel = new SheetExcel();

        //�����֯��ҵ��ʵ��
        String inventoryName = ExcelImport.getValue(row.getCell(0));
        sheetExcel.setInventoryName(inventoryName);
        sheetExcel.setBusinessEntity(inventoryName);
        //����
        String materialCode = ExcelImport.getValue(row.getCell(1));

        AssertUtils.notEmpty(materialCode, "���ϱ��벻��Ϊ��");
        Material material = materialService.getMaterialByCode(materialCode);
        Assert.notNull(material, "���ϲ�����");
        sheetExcel.setMaterialId(material.getId());
        sheetExcel.setMaterialCode(material.getCode());
        sheetExcel.setMaterialName(material.getName());
        sheetExcel.setCateGoryId(material.getSparescateId());
        sheetExcel.setMaterialSpecification(material.getSpecifications());
        sheetExcel.setMaterialDescription(material.getDescription());
        sheetExcel.setMaterialUnit(material.getUnit());

        //�ⷿ����
        String storehouseCode = ExcelImport.getValue(row.getCell(4));

        AssertUtils.notEmpty(storehouseCode, "�ⷿ���벻��Ϊ��");
        WareHouse wareHouse = wareHouseService.getWareHouseByCodeAndStatus(storehouseCode, DictionaryEnums.Status.ENABLE.getCode());
        Assert.notNull(wareHouse, "�ⷿ������");
        sheetExcel.setStorehouseId(wareHouse.getId());
        sheetExcel.setStorehouseName(wareHouse.getName());
        //��λ
        String storeLocationCode = ExcelImport.getValue(row.getCell(5));
        WareHouse location=null;
        if (StringUtils.isNotEmpty(storeLocationCode)) {
            location = wareHouseService.getWareHouseByCodeAndStatus(storeLocationCode, DictionaryEnums.Status.ENABLE.getCode());
            Assert.notNull(wareHouse, "��λ������");
        } else {
            List<WareHouse> wareHouseList = wareHouseService.findByParentId(wareHouse.getId());
            //��ȡĬ�Ͽ�λ����
            String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");
            Boolean bool = false;
            for (WareHouse house : wareHouseList) {
                if (house.getName().equals(defaultStoreHouse)) {
                    location = wareHouse;
                    bool = true;
                    break;
                }
            }
            //û��Ĭ�Ͽ�λ������Ĭ�Ͽ�λ
            if (!bool) {
                location = this.sheetRKDETAILService.addWareHouse(defaultStoreHouse, wareHouse.getId(), ztId, userId);
            }
        }
        Assert.notNull(location, "��λ����Ϊ��");
        sheetExcel.setStoreLocationId(location.getId());
        sheetExcel.setStoreLocationCode(location.getCode());
        sheetExcel.setStoreLocationName(location.getName());
        sheetExcel.setStoreLocationCode(location.getCode());
        //����
        String numStr = ExcelImport.getValue(row.getCell(6));
        AssertUtils.notEmpty(numStr, "��������Ϊ��");
        Double num = Double.parseDouble(numStr);
        sheetExcel.setNum(num);
        //����
        String univalentStr = ExcelImport.getValue(row.getCell(7));
        AssertUtils.notEmpty(univalentStr, "���۲���Ϊ��");
        Double univalent = Double.parseDouble(univalentStr);
        sheetExcel.setUnivalent(univalent);
        //˰��
        String taxRateStr = ExcelImport.getValue(row.getCell(8));
        AssertUtils.notEmpty(taxRateStr, "˰�ʲ���Ϊ��");
        Double taxRate = Double.parseDouble(taxRateStr);
        sheetExcel.setTaxRate(taxRate);
        //����˰���
        Double noTaxPrice = num * univalent;
        sheetExcel.setNoTaxPrice(noTaxPrice);
        //��˰���
        Double taxPrice = num * univalent * (1 + taxRate);
        sheetExcel.setTaxPrice(taxPrice);
        //��������
        String allotNumber = ExcelImport.getValue(row.getCell(9));
        AssertUtils.notEmpty(allotNumber, "������������Ϊ��");
        sheetExcel.setAllotNumber(Double.parseDouble(allotNumber));
        //�ƻ�����
        String projectDivisionId = ExcelImport.getValue(row.getCell(10));
        if (StringUtils.isEmpty(projectDivisionId)) {
            sheetExcel.setProjectDivisionId(null);
        } else {
            sheetExcel.setProjectDivisionId(Integer.parseInt(projectDivisionId));
        }
        //�Ƿ����豸
        String ifEquipment = ExcelImport.getValue(row.getCell(11));
        AssertUtils.notEmpty(ifEquipment, "�Ƿ����豸����Ϊ��");
        sheetExcel.setIfEquipment(Integer.parseInt(ifEquipment));
        //�Ƿ��������к�
        String ifSerialNumber = ExcelImport.getValue(row.getCell(12));
        AssertUtils.notEmpty(ifSerialNumber, "�Ƿ��������кŲ���Ϊ��");
        sheetExcel.setIfSerialNumber(Integer.parseInt(ifSerialNumber));

        //��������
        String productionDate = ExcelImport.getValue(row.getCell(13));
        AssertUtils.notEmpty(productionDate, "�������ڲ���Ϊ��");
        sheetExcel.setProductionDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD, productionDate));
        //��������
        String shelfLifeDate = ExcelImport.getValue(row.getCell(14));
        if (StringUtils.isNotEmpty(shelfLifeDate)) {
            sheetExcel.setShelfLifeDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD, shelfLifeDate));
        }
        return sheetExcel;
    }

    /**
     * ���浥��
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
            throw new RuntimeException("���ݱ���ʧ�ܣ����ɵ��ݱ���ʧ��");
        }
        sheetRK.setCode(code);
        sheetRK.setGuid(UUID.randomUUID().toString());
        sheetRK.setName("�������뵥");
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
     * ������ϸ
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
        //����
        sheetRKDETAIL.setCategoryid(sheetExcel.getCateGoryId());
        sheetRKDETAIL.setMaterialid(sheetExcel.getMaterialId());
        sheetRKDETAIL.setMaterialcode(sheetExcel.getMaterialCode());
        sheetRKDETAIL.setMaterialname(sheetExcel.getMaterialName());
        sheetRKDETAIL.setMaterialspecification(sheetExcel.getMaterialSpecification());
        sheetRKDETAIL.setDescription(sheetExcel.getMaterialDescription());
        sheetRKDETAIL.setDetailunitname(sheetExcel.getMaterialUnit());
        //��λ���ƻ�����
        sheetRKDETAIL.setExtendstring1(sheetExcel.getStorehouseCode());
        sheetRKDETAIL.setPlandepartid(sheetExcel.getProjectDivisionId());
        //������
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
     * �༭��λ
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
        //�ⷿ����λ
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
