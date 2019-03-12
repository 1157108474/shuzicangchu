package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.DateUtils;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.SheetDetailDao;
import com.zthc.ewms.sheet.dao.SheetPDDao;
import com.zthc.ewms.sheet.entity.apply.Apply;
import com.zthc.ewms.sheet.entity.apply.ApplyPrint;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.entity.ck.ManageCK;
import com.zthc.ewms.sheet.entity.ck.dto.RenewalCostDTO;
import com.zthc.ewms.sheet.entity.db.Db;
import com.zthc.ewms.sheet.entity.db.DbDetail;
import com.zthc.ewms.sheet.entity.db.Dbd;
import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import com.zthc.ewms.sheet.entity.file.AttachFile;
import com.zthc.ewms.sheet.entity.guard.*;
import com.zthc.ewms.sheet.entity.order.Order;
import com.zthc.ewms.sheet.entity.order.OrderDetails;
import com.zthc.ewms.sheet.entity.order.OrderPrint;
import com.zthc.ewms.sheet.entity.pd.WzpcManage;
import com.zthc.ewms.sheet.entity.query.VCgjhEntity;
import com.zthc.ewms.sheet.entity.rk.*;
import com.zthc.ewms.sheet.entity.th.Th;
import com.zthc.ewms.sheet.entity.th.ThDetail;
import com.zthc.ewms.sheet.entity.th.ThPrint;
import com.zthc.ewms.sheet.entity.tk.TK;
import com.zthc.ewms.sheet.entity.tk.TKDetail;
import com.zthc.ewms.sheet.entity.tk.TKPrint;
import com.zthc.ewms.sheet.entity.ykyw.Ykyw;
import com.zthc.ewms.sheet.entity.ykyw.YkywDetail;
import com.zthc.ewms.sheet.entity.zc.ZC;
import com.zthc.ewms.sheet.entity.zr.SheetZRD;
import com.zthc.ewms.sheet.entity.zr.ZrDetails;
import com.zthc.ewms.sheet.service.guard.SheetServiceGuard;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.service.MaterialService;
import com.zthc.ewms.system.provider.entity.Provider;
import com.zthc.ewms.system.provider.service.ProviderService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.AppConfig;
import drk.system.Log;
import net.sf.json.JSONObject;
import org.activiti.engine.RuntimeService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class SheetService extends SheetServiceGuard {

    @Resource(name = "formTemplateService")
    public FormTemplateService formTemplateService;

    @Resource(name = "vCgjhEntityService")
    private VCgjhEntityService vCgjhEntityService;

    @Autowired
    private SheetDetailService sheetDetailService;
    @Resource(name = "sheetDetailDao")
    public SheetDetailDao sheetDetailDao;
    @Resource(name = "sheetPDDao")
    public SheetPDDao sheetPDDao;

    @Autowired
    private RuntimeService runtimeService;
    @Resource(name = "dictionaryService")
    private DictionaryService dictionaryService;


    @Resource(name = "sheetDetailService")
    public SheetDetailService detailService;
    @Resource(name = "organizationService")
    public OrganizationService orgService;
    @Resource(name = "userService")
    public UserService userService;
    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;

    @Resource(name = "sheetApplyService")
    public SheetApplyService sheetApplyService;
    @Resource(name = "materialService")
    public MaterialService materialService;

    @Resource(name = "sheetCKService")
    public SheetCKService sheetCKService;
    @Resource(name = "vCgjhEntityService")
    public VCgjhEntityService cgjhEntityService;
    @Resource(name = "applyDepService")
    private ApplyDepService applyDepService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private OrganizationService organizationService;
    private final static Log xmllog = Log.getLog(" com.zthc.ewms.sheet.xmllog");

    //增改
    @Transactional
    public void saveSheet(Sheet obj, String menuCode, String type, String userCode, SheetCondition condition) {
        List<FormTemplate> formTemplates = this.formTemplateService.getFromTemByMenuCode(menuCode);
        if (formTemplates == null || formTemplates.size() < 0) {
            throw new RuntimeException("单据保存失败：查不到单据前缀");
        }
        String code = this.getCode(formTemplates.get(0).getFormTemPre(), 0F);
        if (code == null) {
            throw new RuntimeException("单据保存失败：生成单据编码失败");
        } else {
            obj.setCode(code);
            Map<String, Object> map = processManageService.startProcessInstance(menuCode, userCode);
            if ("启动成功".equals(map.get("success"))) {
                obj.setRouteId(Integer.parseInt(map.get("processInstanceId").toString()));
                obj.setTaskId(map.get("taskId").toString());
                this.dao.saveSheet(obj, condition);
                obj.setUrl(DictionaryEnums.TypeToUrlId.getUrlByType(type) + obj.getId());
            } else {
                throw new RuntimeException("单据保存失败：启动工作流失败--" + map.get("success") + "");
            }
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkNotExit(String code) {
        return this.dao.checkNotExit(code);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsFish(String code, Integer extendInt1, Integer ztId) {
        return this.dao.existsFish(code, extendInt1, ztId);
    }

    @Transactional
    public void deleteSheet(Integer extendInt1) {
        this.dao.deleteSheet(extendInt1);
    }

    //增改
    @Transactional
    public void saveSheetCK(SheetCK obj, String menuCode, String type, String userCode, SheetCondition condition) {
        String code = this.getCode(type, 0F);
        if (code == null) {
            throw new RuntimeException("单据保存失败：生成单据编码失败");
        } else {
            obj.setCode(code);
            Map<String, Object> map = processManageService.startProcessInstance(menuCode, userCode);
            if ("启动成功".equals(map.get("success"))) {
                obj.setRouteid(Integer.parseInt(map.get("processInstanceId").toString()));
                obj.setTaskId(map.get("taskId").toString());
                this.dao.saveSheetCK(obj, condition);
                obj.setUrl(DictionaryEnums.TypeToUrlId.getUrlByType(type) + obj.getId());
            } else {
                throw new RuntimeException("单据保存失败：启动工作流失败--" + map.get("success") + "");
            }
        }
    }

    /**
     * 各类单据管理页面，获取单据列表数据
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> LayuiPage<T> sheetList(Sheet obj, SheetCondition condition, String className, Integer currentUserId,
                                      String beginStr, String endStr, boolean appFlag) {

        Date begin = null;
        Date end = null;
        DateTimeFormatter builder = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        if (!StringUtils.isEmpty(beginStr)) {
            begin = builder.parseDateTime(beginStr + " 00:00:00").toDate();
        }
        if (!StringUtils.isEmpty(endStr)) {
            end = builder.parseDateTime(endStr + " 23:59:59").toDate();
        }
        return this.dao.sheetList(obj, condition, className, currentUserId, begin, end, appFlag);
    }


    //查
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Ykyw getYkywSheetOne(Integer id) {
        return this.dao.getYkywSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WzpcManage getWzpcSheetOne(Integer id) {
        return this.dao.getWzpcSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetCKDETAIL getCkDetailOne(Integer id) {
        return this.dao.getCkDetailOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetStock getStockOne(Integer id) {
        return this.dao.getStockOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Db getDbSheetOne(Integer id) {
        return this.dao.getDbSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetRKD getRkSheetOne(Integer id) {
        return this.dao.getRkSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetZRD getZrSheetOne(Integer id) {
        return this.dao.getZrSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DbrkPrint getDbrkPrintOne(Integer id) {
        return this.dao.getDbrkPrintOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ManageCK getCkSheetOne(Integer id) {
        return this.dao.getCkSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetCK getSheetCkOne(Integer id) {
        return this.dao.getSheetCkOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetCKD getSheetCKDOne(Integer id) {
        return this.dao.getSheetCKDOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ManageApply getApplySheetOne(Integer id) {
        return this.dao.getApplySheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Th getThSheetOne(Integer id) {
        return this.dao.getThSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ZC getZCSheetOne(Integer id) {
        return this.dao.getZCSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ThPrint getThPrintSheetOne(Integer id) {
        return this.dao.getThPrintSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public TK getTKSheetOne(Integer id) {
        return this.dao.getTKSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public TKPrint getTKPrintSheetOne(Integer id) {
        return this.dao.getTKPrintSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ApplyPrint getApplyPrintOne(Integer id) {
        return this.dao.getApplyPrintOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OrderPrint getOrderPrintSheetOne(Integer id) {
        return this.dao.getOrderPrintSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Order getOrderOne(String id) {
        return this.dao.getOrderOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OrderDetails getOrderInfoOne(Integer id) {
        return this.dao.getOrderInfoOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetRKDETAIL getRkDetailById(Integer id) {
        return this.dao.getRkDetailById(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetCG getOrderOne(Integer id) {
        return this.dao.getOrderOneById(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RkDetails getRkDetails(Integer id) {
        return this.dao.getRkDetails(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RkSubDetail getRkSonDetail(Integer id) {
        return this.dao.getRkSonDetail(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RkDetail getRkDetail(Integer id) {
        return this.dao.getRkDetail(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public JsrkDetails getJsrkDetails(Integer id) {
        return this.dao.getJsrkDetails(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RkSubDetail getRkSubDetail(Integer id) {
        return this.dao.getRkSubDetail(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ZrDetails getZrDetails(Integer id) {
        return this.dao.getZrDetails(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetJCRK getJCRKSheetOne(Integer id) {
        return this.dao.getJCRKSheetOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetJCCK getJCCKSheetOne(Integer id) {
        return this.dao.getJCCKSheetOne(id);
    }

    @Transactional
    public void updateSheet(Integer id, String memo, String extendString1, Integer userId) {
        if (this.dao.updateSheet(id, memo, extendString1, userId) != 1) {
            throw new RuntimeException("");
        }
    }

    @Transactional
    public Integer editPDSheet(Integer id, Integer extendInt1, String extendString1, Integer extendInt2, String memo, Integer userId) {
        return this.dao.editPDSheet(id, extendInt1, extendString1, extendInt2, memo, userId);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SheetDetail> getDetailOne(Integer id) {
        return this.dao.getDetailOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SheetDetail> getDetaiList(Integer id, Integer masterId) {
        return this.dao.getDetailList(id, masterId);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetDetail getDetailById(Integer id) {
        return this.dao.getDetailOneById(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SheetRKDETAIL getSheetRKDETAILOneById(Integer id) {
        return this.dao.getSheetRKDETAILOneById(id);
    }

    /**
     * 生成单据编码
     */
    @Transactional
    public String getCode(String prefix, float flag) {
        try {
            return this.dao.getCode(prefix, flag);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Sheet> sheetListByProId(int proId) {
        return this.dao.sheetListByProId(proId);
    }

    /**
     * 获取单据附件列表
     *
     * @param sheetId
     * @param condition
     * @return
     */
    public LayuiPage<AttachFile> sheetFileList(Integer sheetId, SheetCondition condition) {
        return this.dao.sheetFileList(sheetId, condition);
    }

    /**
     * 保存单据附件
     *
     * @param file
     */
    public void saveFile(AttachFile file) {
        this.dao.saveFile(file);
    }

    /**
     * 检查单据附件名称是否重复
     *
     * @param name
     * @return
     */
    public int checkFile(String name) {
        return this.dao.checkFile(name);
    }

    /**
     * 删除附件
     *
     * @param id
     */
    public void deleteFile(Integer id) {
        this.dao.deleteFile(id);
    }


    /**
     * 删除单据
     *
     * @param id
     */
    @Transactional
    public void deleteSheet(Integer id, String type) {

        Sheet sheet = this.dao.getSheetOne(id);

        runtimeService.deleteProcessInstance(sheet.getRouteId() + "", "");//删除流程
        if ("KCPD".equals(type)) {
            this.sheetPDDao.delDetailsBySheetId(id);
        } else {
            this.sheetDetailDao.delDetailsBySheetId(id);
        }
        this.dao.delSheet(id);
    }


    /**
     * 单据办结 业务处理
     *
     * @param sheetId   单据id
     * @param sheetType 单据类型
     * @param userId    提交人ID
     * @return
     */
    @Transactional
    public String auditSheet(Integer sheetId, String sheetType, Integer userId) {
        try {
            return this.dao.auditSheet(sheetId, sheetType, userId);
        } catch (SQLException e) {
            return null;
        }
    }

    @Transactional
    public LayuiPage<Dbd> listDbdSheets(Integer ztId, String type, String code, SheetCondition condition) {
        return this.dao.listDbdSheets(ztId, type, code, condition);
    }

    public Map<String, Object> getSheetOneAll(String taskId) {
        return dao.getSheetOneByPi(taskId);
    }

    @Transactional
    public JSONObject isTrue(Object id) {
        JSONObject ret = new JSONObject();

        long count = dao.getDetailCount(id);
        if (count <= 0) {
            ret.put("code", false);
            ret.put("message", "请添加明细！");
        } else {
            ret.put("code", true);
            ret.put("message", "");
        }
        return ret;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String postYWXML(Integer id) {
        xmllog.message("YWXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Ykyw sheet = this.getYkywSheetOne(id);
        xmllog.message("sheetId:" + id + ";;;;sheetCode:" + sheet.getCode());
        List<YkywDetail> details = this.detailService.sheetDetails(id, "YkywDetail");
        Document document = DocumentHelper.createDocument();

        Element rootElement = document.addElement("WSINTERFACE");
        Element headerElement = rootElement.addElement("HEADER");

        //防止数据混乱,900000001开始的流水号
        headerElement.addElement("HEADER_ID").addText(StringUtils.getHeaderId(sheet.getId()));
        headerElement.addElement("DOC_NUM").addText(sheet.getCode());
        headerElement.addElement("TRANS_DATE").addText(simpleDate.format(new Date()));
        //headerElement.addElement("TRANS_DATE").addText("2018-06-06 14:20:21");

        Organization org = orgService.getOrganizationOne(sheet.getDepartId());
        if(org.getParentId() != 0){
        	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
        	headerElement.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
        	headerElement.addElement("TRANSFER_ORGANIZATION_ID").addText(ogtion.getExtendint1() + "");
        }else{
        	headerElement.addElement("ORGANIZATION_ID").addText(org.getExtendint1().toString());
        	headerElement.addElement("TRANSFER_ORGANIZATION_ID").addText(org.getExtendint1() + "");
        }

        headerElement.addElement("DEPT_ID").addText(org.getExtendint1() + "");
        User user = userService.getUserOne(sheet.getCreator());
        if(user.getExtendint1()!=null){
        	headerElement.addElement("MAKER_ID").addText(user.getExtendint1().toString());
        }else{
        	headerElement.addElement("MAKER_ID").addText(sheet.getCreator().toString());
        }
        headerElement.addElement("MAKER_NAME").addText(user.getName());
        headerElement.addElement("MAKER_DATE").addText(simpleDate.format(new Date(sheet.getCreateDate().getTime())));

        Element updaterElement = headerElement.addElement("UPDATE_BY_ID");
        Element updateDateElement = headerElement.addElement("UPDATE_DATE");
        if (sheet.getUpdater() != null) {
            user = userService.getUserOne(sheet.getUpdater());
            updaterElement.addText(user.getExtendint1() + "");
            updateDateElement.addText(simpleDate.format(sheet.getUpdateDate()));
        }

        headerElement.addElement("NOTES").addText(sheet.getMemo() == null ? "" : sheet.getMemo());

        headerElement.addElement("BATCH_NAME").addText(new DateTime().toString("yyyyMMddHHmmss"));
        //21:组织间调拨出库 2:子库存调拨 305:货位调拨 18:组织间调拨入库
        headerElement.addElement("TRANS_TYPE").addText(details.get(0).getExtendInt4() == details.get(0).getStoreId()
                ? "305" : "2");

        WareHouse wareHouse;
        String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");
        for (YkywDetail detail : details) {
            Element lineElement = headerElement.addElement("LINE");
            //防止数据混乱,900000001开始的流水号
            lineElement.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));

            lineElement.addElement("ITEM_NO").addText(detail.getMaterialCode());
            lineElement.addElement("ITEM_DESC").addText(detail.getDescription());

            lineElement.addElement("UOM").addText(detail.getDetailUnitName());

            lineElement.addElement("QUANTITY").addText(detail.getDetailCount() + "");
            wareHouse = wareHouseService.getWareHouse(detail.getExtendInt4());
            lineElement.addElement("SUBINV_CODE").addText(wareHouse.getCode());

            wareHouse = wareHouseService.getWareHouse(detail.getExtendInt5());
            //不为默认库位
            if(!wareHouse.getName().equals(defaultStoreHouse)){
                lineElement.addElement("LOCATOR_ID").addText(wareHouse.getErpId() + "");
                lineElement.addElement("LOCATOR_CODE").addText(wareHouse.getCode());
            }

            wareHouse = wareHouseService.getWareHouse(detail.getStoreId());
            lineElement.addElement("TRANSFER_SUBINV_CODE").addText(wareHouse.getCode());

            wareHouse = wareHouseService.getWareHouse(detail.getStoreLocationId());
            //不为默认库位
            if(!wareHouse.getName().equals(defaultStoreHouse)){
                lineElement.addElement("TRANSFER_LOCATOR_ID").addText(wareHouse.getErpId() + "");
                lineElement.addElement("TRANSFER_LOCATOR_CODE").addText(wareHouse.getCode());
            }

            lineElement.addElement("BASE_UNIT_PRICE").addText(detail.getNoTaxPrice() + "");
            lineElement.addElement("BASE_AMOUNT").addText(detail.getNoTaxSum() == null ? "" : detail.getNoTaxSum() + "");

            lineElement.addElement("SOURCE_LINE_ID").addText("");

        }
        return document.getRootElement().asXML();

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String postDBDXML(Integer id, SheetCK sheetCk) {
        xmllog.message("DBXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Db sheet = this.getDbSheetOne(id);
        xmllog.message("sheetId:" + id + ";sheetCode:" + sheet.getCode());
//        List<DbDetail> details = this.detailService.sheetDetails(id, "DbDetail");
        List<SheetCKDETAIL> detailsCk = this.detailService.sheetDetails(sheet.getId(), "SheetCKDETAIL");

        Document document = DocumentHelper.createDocument();

        Element rootElement = document.addElement("WSINTERFACE");
        Element headerElement = rootElement.addElement("HEADER");

        //防止数据混乱,900000001开始的流水号
        headerElement.addElement("HEADER_ID").addText(StringUtils.getHeaderId(sheet.getId()));

        headerElement.addElement("DOC_NUM").addText(sheet.getCode());
        headerElement.addElement("TRANS_DATE").addText(simpleDate.format(new Date()));
        Organization org = orgService.getOrganizationOne(sheet.getExtendInt1());
        if(org.getParentId() != 0){
        	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
        	headerElement.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
        }else{
        	headerElement.addElement("ORGANIZATION_ID").addText(org.getExtendint1().toString());
        }
        org = orgService.getOrganizationOne(sheet.getExtendInt2());
        if(org.getParentId() != 0){
        	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
        	headerElement.addElement("TRANSFER_ORGANIZATION_ID").addText(ogtion.getExtendint1() + "");
        }else{
        	headerElement.addElement("TRANSFER_ORGANIZATION_ID").addText(org.getExtendint1() + "");
        }
        org = orgService.getOrganizationOne(sheet.getDepartId());
        headerElement.addElement("DEPT_ID").addText(org.getExtendint1() + "");
        User user = userService.getUserOne(sheet.getCreator());
        if(user.getExtendint1()!=null){
        	headerElement.addElement("MAKER_ID").addText(user.getExtendint1().toString());
        }else{
        	headerElement.addElement("MAKER_ID").addText(sheet.getCreator().toString());
        }
        headerElement.addElement("MAKER_NAME").addText(user.getName());
        headerElement.addElement("MAKER_DATE").addText(simpleDate.format(sheet.getCreateDate()));

        Element updaterElement = headerElement.addElement("UPDATE_BY_ID");
        Element updateDateElement = headerElement.addElement("UPDATE_DATE");
        if (sheet.getUpdater() != null) {
            user = userService.getUserOne(sheet.getUpdater());
            updaterElement.addText(user.getExtendint1() + "");
            updateDateElement.addText(simpleDate.format(sheet.getUpdateDate()));
        }

        headerElement.addElement("NOTES").addText(sheet.getMemo() == null ? "" : sheet.getMemo());

        headerElement.addElement("BATCH_NAME").addText(new DateTime().toString("yyyyMMddHHmmss"));
        //21:组织间调拨出库 2:子库存调拨 305:货位调拨 18:组织间调拨入库
        headerElement.addElement("TRANS_TYPE").addText("3");

        WareHouse wareHouse;
        SheetDetail detail;

        for (SheetCKDETAIL detailCk : detailsCk) {
            detail = sheetDetailDao.getSheetDetailOne(detailCk.getSheetDetailId());
            Element lineElement = headerElement.addElement("LINE");
            //防止数据混乱,900000001开始的流水号
            lineElement.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));

            lineElement.addElement("ITEM_NO").addText(detail.getMaterialCode());
            lineElement.addElement("ITEM_DESC").addText(detail.getDescription());

            lineElement.addElement("UOM").addText(detail.getDetailUnitName());

            lineElement.addElement("QUANTITY").addText(detail.getDetailCount() + "");
            wareHouse = wareHouseService.getWareHouse(detailCk.getStoreId());
            lineElement.addElement("SUBINV_CODE").addText(wareHouse.getCode());

            wareHouse = wareHouseService.getWareHouse(detailCk.getStoreLocationId());
            lineElement.addElement("LOCATOR_ID").addText(wareHouse.getErpId() + "");
            lineElement.addElement("LOCATOR_CODE").addText(wareHouse.getCode());

            wareHouse = wareHouseService.getWareHouse(detail.getStoreId());
            lineElement.addElement("TRANSFER_SUBINV_CODE").addText(wareHouse.getCode());

            wareHouse = wareHouseService.getWareHouse(detail.getStoreLocationId());
            lineElement.addElement("TRANSFER_LOCATOR_ID").addText(wareHouse.getId() + "");
            lineElement.addElement("TRANSFER_LOCATOR_CODE").addText(wareHouse.getCode());

            lineElement.addElement("BASE_UNIT_PRICE").addText(detail.getNoTaxPrice() + "");
            lineElement.addElement("BASE_AMOUNT").addText(detail.getNoTaxSum() + "");

            lineElement.addElement("SOURCE_LINE_ID").addText("");

        }
        return document.getRootElement().asXML();

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String postLLOccupyCountXML(Integer id) {
        xmllog.message("LLOccupyCountXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ManageApply sheet = this.sheetApplyService.getApplyOne(id);
        xmllog.message("sheetId:" + id + ";;;;sheetCode:" + sheet.getCode());
        List<Apply> details = this.detailService.sheetDetails(id, "Apply");
        Document document = DocumentHelper.createDocument();

        Element rootElement = document.addElement("WSINTERFACE");
        WareHouse wareHouse;
        Material material;

        for (Apply detail : details) {
            Element headerElement = rootElement.addElement("HEADER");

            //防止数据混乱,900000001开始的流水号
            headerElement.addElement("HEADER_ID").addText(StringUtils.getHeaderId(sheet.getId()));

            headerElement.addElement("DOC_NUM").addText(sheet.getCode());
            //headerElement.addElement("DOC_NUM").addText(sheet.getCode());
            //防止数据混乱,900000001开始的流水号
            headerElement.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));
            Organization org = orgService.getOrganizationOne(detail.getZtId());
            if(org.getParentId() != 0){
            	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
            	headerElement.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
            }else{
            	headerElement.addElement("ORGANIZATION_ID").addText(org.getExtendint1().toString());
            }
            headerElement.addElement("ITEM_NO").addText(detail.getMaterialCode());
            //TODO
            //headerElement.addElement("QUANTITY").addText(detail.getExtentInt5());
            headerElement.addElement("QUANTITY").addText(detail.getDetailCount() + "");
            headerElement.addElement("UOM").addText(detail.getDetailUnitName());

            wareHouse = null;
            //20180916
            if (detail.getStoreId() != null) {
                wareHouse = wareHouseService.getWareHouse(detail.getStoreId());
            }
            /*headerElement.addElement("SUBINV_CODE").addText(wareHouse==null?"":wareHouse.getCode());
            if(detail.getStoreLocationId()!=null) {
                wareHouse = wareHouseService.getWareHouse(detail.getStoreLocationId());
            }
            headerElement.addElement("LOCATOR_ID").addText(wareHouse==null?"":wareHouse.getErpId()+"");
            headerElement.addElement("LOCATOR_Code").addText(wareHouse==null?"":wareHouse.getCode());*/

            headerElement.addElement("SUBINV_CODE").addText(wareHouse.getCode() + "");
            headerElement.addElement("LOCATOR_ID").addText("");
            headerElement.addElement("LOCATOR_Code").addText("");

            headerElement.addElement("INTERFACE_CODE").addText("CUXYXINVISS");
            //TODO
            headerElement.addElement("SOURCE_HEADER_ID").addText("" + (sheet.getExtendInt1() != null && sheet.getExtendInt1() > 0 ? sheet.getExtendInt1() : sheet.getId()));
            headerElement.addElement("SOURCE_LINE_ID").addText("" + (detail.getExtendInt7() != null && detail.getExtendInt7() > 0 ? detail.getExtendInt7() : detail.getId()));

            headerElement.addElement("SOURCE_DOC_NUM").addText(sheet.getCode());
            headerElement.addElement("TRANS_DATE").addText(simpleDate.format(new Date()));
            material = this.materialService.getMaterialByCode(detail.getMaterialCode());
            headerElement.addElement("SERIAL_NUMBER_CONTROL_CODE").addText(material != null && material.getEnableSN() != null && material.getEnableSN() == 1 ? "5" : "1");
            headerElement.addElement("TRANS_TYPE").addText("1");

        }
        return document.getRootElement().asXML();

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String postDBOccupyCountXML(Integer id) {
        xmllog.message("DBOccupyCountXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Db sheet = this.getDbSheetOne(id);
        xmllog.message("sheetId:" + id + ";;;;sheetCode:" + sheet.getCode());
        List<DbDetail> details = this.detailService.sheetDetails(id, "DbDetail");
        Document document = DocumentHelper.createDocument();

        Element rootElement = document.addElement("WSINTERFACE");
        WareHouse wareHouse;
        Material material;
        for (DbDetail detail : details) {
            Element headerElement = rootElement.addElement("HEADER");

            //防止数据混乱,900000001开始的流水号
            headerElement.addElement("HEADER_ID").addText(StringUtils.getHeaderId(id));
            headerElement.addElement("DOC_NUM").addText(sheet.getCode());
            //防止数据混乱,900000001开始的流水号
            headerElement.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));
            Organization org = orgService.getOrganizationOne(detail.getZTID());
            if(org.getParentId() != 0){
            	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
            	headerElement.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
            }else{
            	headerElement.addElement("ORGANIZATION_ID").addText(org.getExtendint1().toString());
            }
            headerElement.addElement("ITEM_NO").addText(detail.getMaterialCode());
            //TODO
//            headerElement.addElement("QUANTITY").addText(detail.getExtendInt5()+"");
            headerElement.addElement("QUANTITY").addText(detail.getDetailCount() + "");
            headerElement.addElement("UOM").addText(detail.getDetailUnitName());
            wareHouse = null;
            if (detail.getStoreId() != null) {
                wareHouse = wareHouseService.getWareHouse(detail.getStoreId());
            }
            headerElement.addElement("SUBINV_CODE").addText(wareHouse == null ? "" : wareHouse.getCode());
            if (detail.getStoreLocationId() != null) {
                wareHouse = wareHouseService.getWareHouse(detail.getStoreLocationId());
            }
            headerElement.addElement("LOCATOR_ID").addText(wareHouse == null ? "" : wareHouse.getErpId() + "");
            headerElement.addElement("LOCATOR_Code").addText(wareHouse == null ? "" : wareHouse.getCode());
            headerElement.addElement("INTERFACE_CODE").addText("CUXYXINVTRX");
            //TODO
            headerElement.addElement("SOURCE_HEADER_ID").addText("" + (sheet.getExtendInt4() != null && sheet.getExtendInt4() > 0 ? sheet.getExtendInt4() : sheet.getId()));
            headerElement.addElement("SOURCE_LINE_ID").addText("" + (detail.getExtendInt3() != null && detail.getExtendInt3() > 0 ? detail.getExtendInt3() : detail.getId()));

            headerElement.addElement("SOURCE_DOC_NUM").addText(sheet.getCode());
            headerElement.addElement("TRANS_DATE").addText(simpleDate.format(new Date()));
            material = this.materialService.getMaterial(detail.getMaterialId());
            headerElement.addElement("SERIAL_NUMBER_CONTROL_CODE").addText(material.getEnableSN() != null && material.getEnableSN() == 1 ? "5" : "1");
            headerElement.addElement("TRANS_TYPE").addText("1");
        }
        return document.getRootElement().asXML();

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String postTKXML(Integer id) {
        xmllog.message("TKXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TK sheet = this.getTKSheetOne(id);
        xmllog.message("sheetId:" + id + ";sheetCode:" + sheet.getCode());
        List<TKDetail> details = this.detailService.sheetDetails(id, "TKDetail");
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("WSINTERFACE");
        Element headerElement = rootElement.addElement("HEADER");

        //防止数据混乱,900000001开始的流水号
        headerElement.addElement("HEADER_ID").addText(StringUtils.getHeaderId(id));
        headerElement.addElement("DOC_NUM").addText(sheet.getCode());
        headerElement.addElement("TRANS_DATE").addText(simpleDate.format(new Date()));
        //headerElement.addElement("TRANS_DATE").addText("2018-06-03 15:23:22");
        Organization org = orgService.getOrganizationOne(sheet.getZtId());
        if(org.getParentId() != 0){
        	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
        	headerElement.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
        }else{
        	headerElement.addElement("ORGANIZATION_ID").addText(org.getExtendint1().toString());
        }

//        Element transferElement = headerElement.addElement("TRANSFER_ORGANIZATION_ID");
//        transferElement.addText(org.getExtendint1()+"");
        //Element deptElement = headerElement.addElement("DEPT_ID");
        org = orgService.getOrganizationOne(sheet.getDepartId());
        headerElement.addElement("DEPT_ID").addText(org.getExtendint1() + "");
        User user = userService.getUserOne(sheet.getCreator());
        if(user.getExtendint1()!=null){
        	headerElement.addElement("MAKER_ID").addText(user.getExtendint1().toString());
        }else{
        	headerElement.addElement("MAKER_ID").addText(sheet.getCreator().toString());
        }
        headerElement.addElement("MAKER_NAME").addText(user.getName());
        headerElement.addElement("MAKER_DATE").addText(simpleDate.format(sheet.getCreateDate()));

        Element updaterElement = headerElement.addElement("UPDATE_BY_ID");
        Element updateDateElement = headerElement.addElement("UPDATE_DATE");
        if (sheet.getUpdater() != null) {
            user = userService.getUserOne(sheet.getUpdater());
            updaterElement.addText(user.getExtendint1() + "");
            updateDateElement.addText(simpleDate.format(sheet.getUpdateDate()));
        }
        //20180916  null
        headerElement.addElement("NOTES").addText(sheet.getMemo() == null ? "" : sheet.getMemo());
        Dictionary dic = this.dictionaryService.getDictionaryOne(sheet.getFundsSource());
        headerElement.addElement("FIN_SOURCE").addText(dic.getName());
        headerElement.addElement("USE").addText(sheet.getExtendString1() == null ? "" : sheet.getExtendString1());

        SheetCK ckSheet = sheetCKService.getSheetCKOne(sheet.getExtendInt1());
        ApplyDep dep = applyDepService.getOne(ckSheet.getApplydepartid());
        headerElement.addElement("ALIAS_NAME").addText(dep.getName());
        headerElement.addElement("ALIAS_ID").addText(dep.getErpId());

        headerElement.addElement("BATCH_NAME").addText(new DateTime().toString("yyyyMMddHHmmss"));
        WareHouse wareHouse = wareHouseService.getWareHouse(details.get(0).getStoreId());
        headerElement.addElement("TRANS_TYPE").addText("寄售库".equals(wareHouse.getName()) ? "125" : "106");
        headerElement.addElement("PROJECT_CODE");
        headerElement.addElement("CONTRACT_CODE");

        SheetCKDETAIL ckDetail;
        VCgjhEntity plan;
        SheetStock stock;
        Provider provider;
        Dictionary dic_js = dictionaryService.getDictionaryByName("寄售");
        Integer jsId = null;
        if (dic != null) {
            jsId = dic_js.getId();
        }
        Boolean isJs;
        for (TKDetail detail : details) {
            ckDetail = this.getCkDetailOne(detail.getSheetDetailId());
            //20180916
            plan = null;
            if (ckDetail.getExtendInt5() != null) {
                plan = cgjhEntityService.getOne(ckDetail.getExtendInt5());
            }
            stock = getStockOne(ckDetail.getExtendint2());
            provider = providerService.getOne(sheet.getProviderDepId());
            Element lineElement = headerElement.addElement("LINE");
            //防止数据混乱,900000001开始的流水号
            lineElement.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));
            lineElement.addElement("ITEM_NO").addText(detail.getMaterialCode());
            lineElement.addElement("ITEM_DESC").addText(detail.getDescription());
            lineElement.addElement("TRANS_UOM").addText(detail.getDetailUnitName());
            lineElement.addElement("TRANS_QUANTITY").addText(detail.getDetailCount() + "");
            lineElement.addElement("UOM").addText(detail.getDetailUnitName());
            lineElement.addElement("QUANTITY").addText(detail.getDetailCount() + "");

            lineElement.addElement("NOTES").addText(detail.getMemo() == null ? "" : detail.getMemo());
            wareHouse = wareHouseService.getWareHouse(detail.getStoreId());
            lineElement.addElement("SUBINV_CODE").addText(wareHouse == null ? "" : wareHouse.getCode());

            Boolean isExistWarehouse = this.wareHouseService.isExistWarehouse(wareHouse.getId());
            if (isExistWarehouse) {
                //获取库位
                wareHouse = wareHouseService.getWareHouse(detail.getStoreLocationId());
                lineElement.addElement("LOCATOR_ID").addText(wareHouse.getErpId() + "");
                lineElement.addElement("LOCATOR_CODE").addText(wareHouse.getCode());
            }
            lineElement.addElement("ASSET_NUMBER").addText("");

            isJs = stock.getOwnerType() == jsId;
            lineElement.addElement("OWNING_ORGANIZATION_ID").addText(isJs ? provider.getExtendInt2() + "" : "");
            lineElement.addElement("OWNING_ORGANIZATION_NAME").addText(isJs ? provider.getName() + "_" + provider.getExtendString1() : "");
            //20180916
            lineElement.addElement("SERIAL_NUMBER_CONTROL_CODE").addText(detail.getEnableSn() != null && detail.getEnableSn() == 1 ? "5" : "1");

            lineElement.addElement("CONSIGNED_FLAG").addText(isJs ? "Y" : "N");
            //防止数据混乱,900000001开始的流水号
            lineElement.addElement("SOURCE_LINE_ID").addText(StringUtils.getHeaderId(ckDetail.getId()));
            lineElement.addElement("SOURCE_DOC_NUM").addText(ckSheet.getCode());
            lineElement.addElement("SOURCE_HEADER_ID").addText(ckSheet.getId() + "");

            lineElement.addElement("SCHEDULT_LN_ID").addText(plan == null ? "" : plan.getErpDetailId() + "");
            lineElement.addElement("SCHEDULT_HD_ID").addText(plan == null ? "" : plan.getErpId() + "");

        }
        return document.getRootElement().asXML();

    }

    public String postTHXML(Integer id) {
        xmllog.message("THXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Th sheet = this.getThSheetOne(id);
        xmllog.message("sheetId:" + id + ";sheetCode:" + sheet.getCode());

        List<ThDetail> details = this.detailService.sheetDetails(id, "ThDetail");
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("WSINTERFACE");
//        Element headerElement = rootElement.addElement("HEADER");
        Element headerElement = rootElement.addElement("REC_INFO");


        //防止数据混乱,900000001开始的流水号
        headerElement.addElement("HEADER_ID").addText(StringUtils.getHeaderId(id));
        headerElement.addElement("DOC_NUM").addText(sheet.getCode());

        Organization org = orgService.getOrganizationOne(sheet.getZtId());
        if(org.getParentId() != 0){
        	Organization ogtion = organizationService.getOrganizationOne(org.getParentId());
        	headerElement.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
        }else{
        	headerElement.addElement("ORGANIZATION_ID").addText(org.getExtendint1().toString());
        }

        Provider provider = providerService.getProviderOne(sheet.getExtendString1());
        headerElement.addElement("VENDOR_ID").addText(provider == null ? "" : provider.getErpId() + "");
        headerElement.addElement("VENDOR_NUMBER").addText(provider == null ? "" : provider.getCode());
        headerElement.addElement("VENDOR_NAME").addText(provider == null ? "" : provider.getName());


        /*headerElement.addElement("DEPT_ID").addText(sheet.getDepartId().toString());*/
        org = orgService.getOrganizationOne(sheet.getDepartId());
        headerElement.addElement("DEPT_ID").addText(org.getExtendint1().toString());

        User user = userService.getUserOne(sheet.getCreator());
        if(user.getExtendint1()!=null){
        	headerElement.addElement("MAKER_ID").addText(user.getExtendint1().toString());
        }else{
        	headerElement.addElement("MAKER_ID").addText(sheet.getCreator().toString());
        }
        headerElement.addElement("MAKER_NAME").addText(user.getName());
        headerElement.addElement("MAKER_DATE").addText(simpleDate.format(sheet.getCreateDate()));
        //headerElement.addElement("MAKER_DATE").addText("2018-06-11 10:29:20");

        Element updaterElement = headerElement.addElement("UPDATE_BY_ID");
        Element updateDateElement = headerElement.addElement("UPDATE_DATE");
        if (sheet.getUpdater() != null) {
            user = userService.getUserOne(sheet.getUpdater());
            updaterElement.addText(user.getExtendint1() + "");
            updateDateElement.addText(simpleDate.format(sheet.getUpdateDate()));
        } else {
            updaterElement.addText("");
            updateDateElement.addText("");
        }
        headerElement.addElement("ORG_ID").addText("21324");
        headerElement.addElement("RECEIPT_DATE").addText(simpleDate.format(new Date()));
        //headerElement.addElement("RECEIPT_DATE").addText("2018-06-11 10:29:20");
        headerElement.addElement("BATCH_NAME").addText(new DateTime().toString("yyyyMMddHHmmss"));
        headerElement.addElement("PROCESS_TYPE").addText("ONLINE");
        headerElement.addElement("TYPE").addText("RETURN");


        WareHouse wareHouse;
        SheetRKDETAIL rkDetail;
        RkSubDetail rkSubDetail;

        SheetStock stock;
        Order orderInfo;

        for (ThDetail detail : details) {

            rkSubDetail = this.getRkSubDetail(detail.getExtendInt8());
            rkDetail = this.getSheetRKDETAILOneById(rkSubDetail.getDetailId());
            orderInfo = this.getOrderOne(rkDetail.getExtendint1() + "");

//            plan = cgjhEntityService.getOne(ckDetail.getExtendInt5());
//            stock = service.getStockOne(ckDetail.getExtendint2());
//            provider = providerService.getOne(sheet.getProviderDepId());
            Element lineElement = headerElement.addElement("REC_LINE_INFO");

            //防止数据混乱,900000001开始的流水号
            lineElement.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));
            lineElement.addElement("UOM").addText(detail.getDetailUnitName());
            lineElement.addElement("QUANTITY").addText(-detail.getDetailCount() + "");

            lineElement.addElement("ITEM_NO").addText(detail.getMaterialCode());
            lineElement.addElement("ITEM_DESC").addText(detail.getDescription());
            wareHouse = this.wareHouseService.getWareHouse(detail.getStoreId());
            lineElement.addElement("SUBINV_CODE").addText(wareHouse == null ? "" : wareHouse.getCode());
//            ret = this.detailService.sheetDetails(sheetId, condition, "YkywDetail");
            Boolean isExistWarehouse = this.wareHouseService.isExistWarehouse(wareHouse.getId());
            if (isExistWarehouse){
                //获取库位
                wareHouse = wareHouseService.getWareHouse(detail.getStoreLocationId());
                lineElement.addElement("LOCATOR_ID").addText(wareHouse.getErpId() + "");
                lineElement.addElement("LOCATOR_CODE").addText(wareHouse.getCode());
            }

            lineElement.addElement("ASSET_NUMBER");
            lineElement.addElement("UNIT_PRICE").addText(detail.getNoTaxPrice() + "");
            lineElement.addElement("AMOUNT").addText(detail.getNoTaxSum() + "");
            lineElement.addElement("BASE_UNIT_PRICE").addText(detail.getNoTaxPrice() + "");

            lineElement.addElement("BASE_AMOUNT").addText(-detail.getNoTaxSum() + "");
            lineElement.addElement("PO_LINE_LOCATION_ID").addText(orderInfo == null ? "" : orderInfo.getFyid() + "");
            lineElement.addElement("PO_NUMBER").addText(orderInfo == null ? "" : orderInfo.getOrdernum());
            lineElement.addElement("PO_HEADER_ID").addText(orderInfo == null ? "" : orderInfo.getOrderId() + "");
            lineElement.addElement("PO_LINE_NUM").addText(orderInfo == null ? "" : orderInfo.getErpRowNum() + "");
            lineElement.addElement("PO_LINE_ID").addText(orderInfo == null ? "" : orderInfo.getOrderRowId() + "");
            lineElement.addElement("PO_RELEASE_NUM").addText(orderInfo == null ? "" : orderInfo.getIssuecode() + "");
            //lineElement.addElement("PO_RELEASE_ID").addText(orderInfo == null ? "" : orderInfo.getIssueid() + "");
            if (orderInfo != null && orderInfo.getIssueid() != 0) {
                lineElement.addElement("PO_RELEASE_ID").addText(orderInfo.getIssueid() + "");
            } else {
                lineElement.addElement("PO_RELEASE_ID").addText("");
            }
            lineElement.addElement("SOURCE_LINE_ID").addText(StringUtils.getHeaderId(detail.getExtendInt8()));
            lineElement.addElement("SERIAL_NUMBER_CONTROL_CODE").addText(new Integer(1).equals(detail.getEnableSn()) ? "5" : "1");
        }
        return document.getRootElement().asXML();
    }



    /**
     * 入库接口拼接XML
     *
     * @param id
     * @return
     */
    public String postRKXML(Integer id) {
        xmllog.message("RKXML");
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SheetRK rkData = this.dao.getRKOne(id);
        List<SheetRKDETAIL> detailList = this.dao.getRkDetailBySheetId(id);
        List<SheetRkSonDetail> subDetailList = this.dao.getSonDetailBySheetId(id);

        xmllog.message("sheetId:" + id + ";sheetCode:" + rkData.getCode());

        Document dom = DocumentHelper.createDocument();
        Element element = dom.addElement("WSINTERFACE");
        Element node = element.addElement("REC_INFO");
        //防止数据混乱,900000001开始的流水号
        node.addElement("HEADER_ID").addText(StringUtils.getHeaderId(rkData.getId()));
        node.addElement("DOC_NUM").addText(rkData.getCode());

        //直接添加库存组织id
        /*node.addElement("ORGANIZATION_ID").addText(rkData.getZtId().toString());*/
        Organization organization = organizationService.getOrganizationOne(rkData.getZtId());
        String store = detailList.get(0).getExtendstring1();
        if(organization.getParentId() != 0){
        	Organization ogtion = organizationService.getOrganizationOne(organization.getParentId());
        	node.addElement("ORGANIZATION_ID").addText(ogtion.getExtendint1().toString());
        }else{
        	node.addElement("ORGANIZATION_ID").addText(organization.getExtendint1().toString());
        }
        

        Provider provider = providerService.getProviderOne(rkData.getExtendString1());
        String vendor_id = provider == null ? "" : provider.getErpId().toString();
        String vendor_number = provider == null ? "" : provider.getCode();
        String vendor_name = provider == null ? "" : provider.getName();
        node.addElement("VENDOR_ID").addText(vendor_id);
        node.addElement("VENDOR_NUMBER").addText(vendor_number);
        node.addElement("VENDOR_NAME").addText(vendor_name);

        /*node.addElement("DEPT_ID").addText(rkData.getDepartId().toString());*/
        organization = organizationService.getOrganizationOne(rkData.getDepartId());
        node.addElement("DEPT_ID").addText(organization.getExtendint1().toString());

        User user = userService.getUserOne(rkData.getCreator());
        if(user.getExtendint1()!=null){
        	node.addElement("MAKER_ID").addText(user.getExtendint1().toString());
        }else{
        	node.addElement("MAKER_ID").addText(rkData.getCreator().toString());
        }
        //node.addElement("MAKER_ID").addText(user.getCode());
        node.addElement("MAKER_NAME").addText(user.getName());
        //node.addElement("MAKER_NAME").addText(user.getCode());

        node.addElement("MAKER_DATE").addText(simpleDate.format(new Date(rkData.getCreateDate().getTime())));
        //node.addElement("MAKER_DATE").addText("2018-06-01 10:30:21");
        if (rkData.getUpdator() != null) {
            user = userService.getUserOne(rkData.getUpdator());
            if (user != null) {
                node.addElement("UPDATE_BY_ID").addText(user.getExtendint1().toString());
                node.addElement("UPDATE_DATE").addText(simpleDate.format(new Date(rkData.getUpdateDate().getTime())));
            }
        } else {
            node.addElement("UPDATE_BY_ID").addText("");
            node.addElement("UPDATE_DATE").addText("");
        }

        node.addElement("ORG_ID").addText("21324");

        node.addElement("RECEIPT_DATE").addText(simpleDate.format(new Date(rkData.getCreateDate().getTime())));
        //node.addElement("RECEIPT_DATE").addText("2018-12-12 18:20:23");
        //node.addElement("RECEIPT_DATE").addText(simpleDate.format(new Date()));

        node.addElement("BATCH_NAME").addText(DateUtils.parseDateToStr("yyyyMMddHHmmss", new Date()));
        node.addElement("PROCESS_TYPE").addText("ONLINE");
        node.addElement("TYPE").addText("RECEIVE");
        SheetCG info;
        SheetDetail sheetDetail;
        WareHouse wareHouse = null;
        for (int i = 0; i < subDetailList.size(); i++) {
            for (int j = 0; j < detailList.size(); j++) {
                if (subDetailList.get(i).getDetailId().equals(detailList.get(j).getId())) {
                    info = this.getOrderOne(detailList.get(j).getExtendint1());
                    sheetDetail = this.getDetailById(detailList.get(j).getSheetdetailid());
                    Element node2 = node.addElement("REC_LINE_INFO");

                    //防止数据混乱,900000001开始的流水号
                    node2.addElement("LINE_ID").addText(StringUtils.getHeaderId(subDetailList.get(i).getId()));

                    node2.addElement("UOM").addText(detailList.get(j).getDetailunitname());
                    node2.addElement("QUANTITY").addText(subDetailList.get(i).getSubDetailCount() + "");
                    node2.addElement("ITEM_NO").addText(detailList.get(j).getMaterialcode());
                    //node2.addElement("ITEM_DESC").addText(detailList.get(j).getDescription());
                    node2.addElement("ITEM_DESC").addText("miaoshu");
                    //获取库房
                    wareHouse = wareHouseService.getWareHouseOne(subDetailList.get(i).getStoreID());
                    String wareHouseCode = wareHouse.getCode() == null ? "0" : wareHouse.getCode();
                    node2.addElement("SUBINV_CODE").addText(wareHouseCode);

                    Boolean isExistWarehouse = this.wareHouseService.isExistWarehouse(wareHouse.getId());
                    if (isExistWarehouse){
                        //获取库位
                        wareHouse = wareHouseService.getWareHouse(subDetailList.get(i).getStoreLocationId());
                        if (wareHouse == null) {
                            throw new RuntimeException("获取库位失败");
                        }
                        node2.addElement("LOCATOR_ID").addText(wareHouse.getErpId().toString());
                        node2.addElement("LOCATOR_CODE").addText(wareHouse.getCode());
                    }
                    node2.addElement("ASSET_NUMBER").addText("");
                    node2.addElement("UNIT_PRICE").addText(detailList.get(j).getNotaxprice().toString());
                    Double amount = detailList.get(j).getNotaxprice() * subDetailList.get(i).getSubDetailCount();

                    node2.addElement("AMOUNT").addText(amount.toString());
                    node2.addElement("BASE_UNIT_PRICE").addText(detailList.get(j).getNotaxprice().toString());

                    node2.addElement("BASE_AMOUNT").addText(amount.toString());
                    node2.addElement("PO_LINE_LOCATION_ID").addText(info.getFyid().toString());

                    node2.addElement("PO_NUMBER").addText(info.getOrdernum());
                    node2.addElement("PO_HEADER_ID").addText(info.getOrderid().toString());
                    node2.addElement("PO_LINE_NUM").addText(info.getErprownum());
                    node2.addElement("PO_LINE_ID").addText(info.getOrderrowid().toString());

                    node2.addElement("PO_RELEASE_NUM").addText(info.getIssuecode().toString());
                    node2.addElement("PO_RELEASE_ID").addText(info.getIssueid() == 0 ? "" : info.getIssueid().toString());
                    node2.addElement("SOURCE_LINE_ID").addText("");

                    if (detailList.get(j).getEnablesn() == 1) {
                        node2.addElement("SERIAL_NUMBER_CONTROL_CODE").addText("5");
                    } else {
                        node2.addElement("SERIAL_NUMBER_CONTROL_CODE").addText("1");
                    }
                }
            }
        }
        return dom.getRootElement().asXML();
    }

    /**
     * 异步更新成本
     *
     * @param sheetTK
     * @param userId
     * @throws Exception
     */
    @Transactional
    public void asynRenewalCost(TK sheetTK, Integer userId) throws Exception {
        xmllog.error("【退库更新成本】 进入方法;sheetId:" + sheetTK.getId() + ";userId:" + userId);

        //获取单据下的明细
        List<SheetDetail> sheetDetails = this.dao.listSheetTKDetail(sheetTK.getId());
        if (StringUtils.isEmpty(sheetDetails)){
            throw new RuntimeException("该单据没有明细");
        }
        List<RenewalCostDTO> renewalCostDTOList = new ArrayList<>();
        //根据明细同步更新成本接口
        for (SheetDetail sheetDetail :sheetDetails){
            RenewalCostDTO renewalCostDTO = this.activitiService.syncToRenewalCost(sheetDetail.getId(),userId);
            renewalCostDTOList.add(renewalCostDTO);
        }
        this.renewalCost(sheetTK.getId(), RenewalCostEnum.SUCCESS.getRenewalCost(),renewalCostDTOList);
    }

    /**
     * 移库移位异步更新成本
     *
     * @param ykyw
     * @param userId
     * @throws Exception
     */
    @Transactional
    public void asynRenewalCostYkyw(Ykyw ykyw, Integer userId) throws Exception {
        xmllog.error("【移库移位更新成本】 进入方法;sheetId:" + ykyw.getId() + ";userId:" + userId);

        //获取单据下的明细
        List<YkywDetail> ykywDetails = this.dao.listYkywDetail(ykyw.getId());
        if (StringUtils.isEmpty(ykywDetails)){
            throw new RuntimeException("该单据没有明细");
        }
        List<RenewalCostDTO> renewalCostDTOList = new ArrayList<>();
        //根据明细同步更新成本接口
        for (YkywDetail sheetDetail :ykywDetails){
            RenewalCostDTO renewalCostDTO = this.activitiService.syncToRenewalCost(sheetDetail.getId(),userId);
            renewalCostDTOList.add(renewalCostDTO);
        }
        this.renewalCost(ykyw.getId(), RenewalCostEnum.SUCCESS.getRenewalCost(),renewalCostDTOList);
    }



    /**
     * 更新成本
     *
     * @param sheetId
     * @param renewalCost
     * @param renewalCostDTOList
     */
    @Transactional
    public void renewalCost(Integer sheetId, Integer renewalCost, List<RenewalCostDTO> renewalCostDTOList) {
        this.dao.renewalCost(sheetId, renewalCost);
        //更新明细成本
        for(RenewalCostDTO renewalCostDTO :renewalCostDTOList){
            this.dao.renewalCostDetail(renewalCostDTO.getSheetCKDETAILId(),renewalCostDTO.getNoTaxPrice(),renewalCostDTO.getNotAxSum());
        }
    }

    /**
     * 更新成本定时任务
     *
     * @return
     */
    @Transactional
    public void renewalCostTask(){
        //退库定时任务
        List<TK> sheetTKS = (List<TK>) this.dao.findRenewalCost("TK");
        for (TK sheetTK : sheetTKS) {
            try {
                asynRenewalCost(sheetTK,0);
            } catch (Exception e) {
                e.printStackTrace();
                xmllog.error("【退库】更新成本失败，sheetId:" + sheetTK.getId());
            }
        }
        //移库移位定时任务
        List<Ykyw> ykywList = (List<Ykyw>) this.dao.findRenewalCost("Ykyw");
        for (Ykyw ykyw: ykywList) {
            try {
                asynRenewalCostYkyw(ykyw,0);
            } catch (Exception e) {
                e.printStackTrace();
                xmllog.error("【移库移位】更新成本失败，sheetId:" + ykyw.getId());
            }
        }
    }

}


