package com.zthc.ewms.webservice.provide;

import com.zthc.ewms.base.util.DateUtils;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.dao.SheetDao;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.sheet.service.SheetService;
import com.zthc.ewms.sheet.service.VCgjhEntityService;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.formTemplateManage.entity.FormTemplate;
import com.zthc.ewms.system.formTemplateManage.service.FormTemplateService;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.service.MenuService;
import com.zthc.ewms.system.provider.service.ProviderService;
import com.zthc.ewms.system.spare.service.SparepartscateService;
import com.zthc.ewms.system.taskLog.service.TaskLogService;
import com.zthc.ewms.system.useDep.entity.OfficesScope;
import com.zthc.ewms.system.useDep.entity.UseDep;
import com.zthc.ewms.system.useDep.service.UseDepService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import com.zthc.ewms.webservice.entity.ERPResultModel;
import drk.system.Log;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class EwmsAxisService {

    @Resource(name = "taskLogService")
    private TaskLogService taskLogService;

    @Resource(name = "providerService")
    private ProviderService providerService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sheetService")
    private SheetService sheetService;

    @Resource(name = "sheetDetailService")
    private SheetDetailService sheetDetailService;



    @Resource(name = "wareHouseService")
    private WareHouseService wareHouseService;

    @Resource(name = "organizationService")
    private OrganizationService organizationService;

    @Resource(name = "dictionaryService")
    private DictionaryService dictionaryService;

    @Resource(name = "applyDepService")
    private ApplyDepService applyDepService;

    @Resource(name = "useDepService")
    private UseDepService useDepService;

    @Resource(name = "vCgjhEntityService")
    private VCgjhEntityService vCgjhEntityService;

    @Resource(name = "menuService")
    private MenuService menuService;


    @Resource(name = "formTemplateService")
    private FormTemplateService formTemplateService;

    @Resource(name = "sparepartscateService")
    public SparepartscateService sparepartscateService;

    @Resource(name="sheetDao")
    public SheetDao dao;

    private final static Log log;
    private Document document = null;
    List<Element> list = new ArrayList<>();

    static {
        log = Log.getLog("EwmsWebServiceImpl");
    }


    Boolean ValidateUser(String UserName, String UserPwd, String errorMsg) {
        Boolean flag = true;
        errorMsg = "";
        String LoginName = "yxwms";
        String LoginPwd = "yxwms";
        if (UserName != LoginName) {
            //errorMsg = "用户名不正确";
            flag = false;
        }
        if (UserPwd != LoginPwd) {
            //errorMsg = "密码不正确";
            flag = false;
        }
        return flag;
    }
    //多单据(单据下多物料)，多响应
    public String planTransformApply(String username, String userpwd, String param) {
        List<ERPResultModel> list = new ArrayList<ERPResultModel>();
        Sheet sheet = new Sheet();
        String errorMsg = "";
        String str2 = "同步计划转领料接口,接口名称:PlanTransformApply";
        int num = 0;
        String str3 = "username:" + username + ";userpwd:" + userpwd + ";param：" + param;
        boolean flag = true;
        try {
            if (!"yxwms".equals(username)) {
                errorMsg = "用户名不正确";
                flag = false;
            }
            if (!"yxwms".equals(userpwd)) {
                errorMsg = "密码不正确";
                flag = false;
            }
            if (flag) {
                if (!StringUtils.isEmpty(param)) {
                    log.debug("传入参数param： "+param);
                    FormTemplate entity = this.formTemplateService.getFromTemBydicID("" + DictionaryEnums.TypeToUrlId.getIdByType("WZLLD")).get(0);
                    String xml = null;
                    try {
                        xml = new String(new BASE64Decoder().decodeBuffer(param),"utf-8");
                        log.debug("xml_data: "+xml);
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.debug("xml进行base64解密错误");
                        return "xml解密失败";
                    }

                    List<Sheet> list3 = new ArrayList<Sheet>();
                    List<SheetDetail> list4 = new ArrayList<SheetDetail>();
                    List<String> list5 = new ArrayList<String>();
                    //转换字符串
                    Document document = null;
                    try {
                        document = DocumentHelper.parseText(xml);
                    } catch (DocumentException e) {
                        return "xml解析失败";
                    }
                    //获取根元素WSINTERFACE
                    Element root = document.getRootElement();
                    //访问指定名称的节点，如访问名称为“SPECIALITYCODE”的全部节点
                    List<Element> nodes = root.elements("HEADER");
                    Organization organization = null;
                    Sheet item = null;
                    ApplyDep dep = null;
                    SheetDetail detail = null;
                    //循环header，即多单据循环
                    for (Element node : nodes) {
                        String innerText = node.element("ORGANIZATION_ID").getText();
                        organization = this.organizationService.getOrganizationBy(innerText);
                        String code = node.element("SheetCode").getText();
                        Integer extendInt1 = Integer.valueOf(node.element("ERPID").getText());
                        if (!this.sheetService.existsFish(code, extendInt1, organization.getId())) {
                            String str6 = "";
                            XMLSerializer serializer = new XMLSerializer();
                            //list5.add( JsonConvert.SerializeXmlNode(node));
                            list5.add(serializer.read(node.asXML()).toString());

                            item = new Sheet();
                            item.setKindId(DictionaryEnums.TypeToUrlId.getIdByType("WZLLD"));
                            //item.setStatus(this.dictionaryService.getDictionaryByCode("YQR").getId());
                            //设置单据状态，需修改
                            //item.setStatus(this.dictionaryService.getDictionaryByCode("YQR").getId());
                            item.setStatus(DictionaryEnums.Status.OVER.getCode());
                            item.setName(entity.getFormTemName());
                            item.setRouteId(Integer.parseInt(entity.getProcessDefinitionKey()));
                            //item.setExtendInt1(Integer.parseInt(node.element("ERPID").getText()));
                            item.setExtendInt1(extendInt1);
                            //item.setCode(node.element("SheetCode").getText());
                            item.setCode(code);


                            if (organization != null) {
                                //item.setZtId(organization.getZtId());
                                item.setZtId(organization.getId());
                                //item.setDepartId(organization.getZtId());
                                item.setDepartId(organization.getId());
                                item.setExtendString2(organization.getName());
                            } else {
                                str6 = str6 + "数据库中不存在此库存组织(ID:" + innerText + ");";
                            }

                            String str7 = node.element("ApplyDepCode").getText();
                            dep = this.applyDepService.getApplyDepCode(str7);
                            if (dep != null) {
                                item.setApplyDepartId(dep.getId());
                            } else {
                                str6 = str6 + "数据库中不存在此申请单位(CODE:" + str7 + ");";
                            }
                            String str8 = node.element("UseDepCode").getText();
                            UseDep dep2 = this.useDepService.getUseDepCode(str8);
                            if (dep2 != null) {
                                item.setUsedDepartId(dep2.getId());
                            } else {
                                str6 = str6 + "数据库中不存在此使用单位(CODE:" + str8 + ");";
                            }
                            item.setCreateDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, node.element("CreateDate").getText()));
                            item.setMemo(node.element("Remark").getText());
                            item.setExtendString1(node.element("USE").getText());
                            //单据下多物料处理，即多line处理
                            //item.setOfficesId(dep2.getId());先注释掉,fundssource(生产629或基建630)
                            item.setFundsSource(629);

                            /*String str9 = node.element("LINE").element("MaterialCode").getText().substring(0, 2);
                            int num4 = this.sparepartscateService.getSparepartscate(str9).getId();
                            List<OfficesScope> table = this.useDepService.listOfficesScopes(num4, item.getZtId());
                            if ((table != null) && (table.size() > 0)) {
                                item.setOfficesId(table.get(0).getOfficesId());
                            } else {
                                str6 = str6 + "该物料分类尚未分配科室";
                            }*/
                            list3.add(item);
                            List<Element> node2s = node.elements("LINE");
                            for (Element node2 : node2s) {
                                detail = new SheetDetail();
                                detail.setGuid(java.util.UUID.randomUUID().toString());
                                detail.setExtendInt7(Integer.parseInt(node2.element("ERPDetailID").getText()));
                                detail.setMaterialCode(node2.element("MaterialCode").getText());
                                detail.setExtendString1(node2.element("MaterialUnit").getText());
                                detail.setDescription(node2.element("MaterialDescription").getText());
                                detail.setDetailCount(Double.parseDouble(node2.element("ApplyCount").getText()));
                                detail.setSnCode(node2.element("PlanCode").getText());
                                detail.setSheetDetailId(Integer.parseInt(node2.element("PlanID").getText()));
                                detail.setExtendInt8(Integer.parseInt(node2.element("PlanDetailID").getText()));
                                detail.setCreateDate(new Date());
                                detail.setExtendString3(str6);
                                detail.setExtendString4(item.getExtendInt1().toString());
                                detail.setDetailUnitName(node2.element("BaseUnit").getText());
                                list4.add(detail);
                            }
                        }
                    }
                    //List<IGrouping<Integer, Sheet>> list6 = (from x in list3 group x by x.ExtendInt1).ToList<IGrouping<Integer, Sheet>>();
                    //几个header
                    List list6 = list3;
                    int size = list6.size();
                    for (int i = 0; i < size; i++) {
                        sheet = (Sheet) list6.get(i);
                        List<SheetDetail> details = new ArrayList<SheetDetail>();
                        for (SheetDetail sheetDetail : list4) {
                            if (sheetDetail.getExtendString4() == sheet.getExtendInt1().toString()) {
                                details.add(sheetDetail);
                            }
                        }
                        int k = 0;
                        String inf = "";
                        for (SheetDetail detail1 : details) {
                            //判断物料是否分配科室使用单位
                            String str9 = detail1.getMaterialCode().substring(0, 2);
                            int num4 = this.sparepartscateService.getSparepartscate(str9).getId();
                            List<OfficesScope> table = this.useDepService.listOfficesScopes(num4, sheet.getDepartId());
                            if ((table != null) && (table.size() > 0)) {
                                sheet.setOfficesId(table.get(0).getOfficesId());
                            } else {
                                detail1.setExtendString3(detail1.getExtendString3()+"该物料分类尚未分配科室(CODE:"+str9+");");
                            }
                            if (!StringUtils.isEmpty(detail1.getExtendString3())) {
                                k++;
                            }
                            inf += detail1.getExtendString3();
                        }

                        //判断正常的话，k==0,
                        if (k == 0) {
                            //保存单据逻辑放在这里
                            //缺少流程任务id的设置和启动流程、设置url，menucode=XZLLD，usercode怎么确定.目前直接置状态为完成单据。
                            this.dao.saveSheet(sheet, null);
                            //存在sheet的话
                            if ((sheet != null) && !this.sheetService.checkNotExit(sheet.getCode())) {
                                int num7 = 0;//sheet的id
                                String menuCode = entity.getFormTemMenu();
                                Menu menu = this.menuService.getMenuCode(menuCode);
                                List<SheetDetail> enumerator3 = null;
                                enumerator3 = details;
                                for (SheetDetail sheetDetail : enumerator3) {
                                    sheetDetail.setSheetId(num7);
                                }
                                String str11 = this.sheetDetailService.saveEwmsSheetDetails(details);
                                if (str11 == "OK") {
                                    //保存成功响应此header信息
                                    ERPResultModel model = new ERPResultModel(sheet.getCode(), sheet.getExtendInt1().toString(), "", sheet.getCode(), true);
                                    list.add(model);
                                    //enumerator3 = details;
                                    /*for (SheetDetail sheetDetail : enumerator3) {
                                        String str12 = "";
                                        for (String s : list5) {
                                            str12 += "\"ERPDetailID\":\"" + sheetDetail.getExtendInt7() + "\"";
                                        }
                                    }*/
                                }else {
                                    this.sheetService.delSheet(sheet.getExtendInt1());
                                    int rowNum = Integer.parseInt(str11.split(";")[0]);
                                    //失败的话响应此header失败信息
                                    ERPResultModel model2 = new ERPResultModel(sheet.getExtendInt1().toString(), sheet.getCode(), false);
                                    char[] chArray2 = new char[]{';'};
                                    model2.setErrorText(str11.split(";")[1]);
                                    list.add(model2);

                                    /*String str13 = "";
                                    for (String s : list5) {
                                        str13 += "\"ERPDetailID\":\"" + details.get(rowNum).getExtendInt7() + "\"";
                                    }
                                    char[] chArray3 = new char[]{';'};*/
                                }

                            } else {
                                ERPResultModel model3 = new ERPResultModel(sheet.getCode(), sheet.getExtendInt1().toString(), "插入单据信息失败,ERPID:" + sheet.getExtendInt1() + ";", sheet.getCode(), false);
                                list.add(model3);
                                /*String str14 = "";
                                for (String s : list5) {
                                    str14 += "\"ERPID\":\"" + sheet.getExtendInt1() + "\"";
                                }*/
                            }
                        }else{
                            //单据内容不正确的话响应数据
                            ERPResultModel model4 = new ERPResultModel(sheet.getCode(), sheet.getExtendInt1().toString(), inf, sheet.getCode(), false);
                            list.add(model4);
                        }
                    }
                } else {
                    ERPResultModel model5 = new ERPResultModel("", "", "同步数据为空", "", false);
                    list.add(model5);
                }
            } else {
                ERPResultModel model6 = new ERPResultModel("", "", "用户名密码错误!", "", false);
                list.add(model6);
            }
        } catch (Exception exception) {
            this.sheetService.delSheet(sheet.getExtendInt1());
            ERPResultModel model7 = new ERPResultModel("", sheet.getExtendInt1().toString(), exception.getMessage(), sheet.getCode(), false);
            list.add(model7);
        }
        Document result = DocumentHelper.createDocument();
        Element codes = result.addElement("CODES");
        for (ERPResultModel model : list) {
            Element code = codes.addElement("CODE");
            code.addElement("STATUS").addText("" + model.getStatus());
            code.addElement("ErrorText").addText("" + model.getErrorText());
            code.addElement("ERPID").addText("" + model.getErpID());
            code.addElement("SheetCode").addText("" + model.getSheetCode());
        }

        String data = result.asXML();
        /*try {
            //验证用户名密码方式不对
            if (true) {
                String xml = new String(new BASE64Decoder().decodeBuffer(param),"utf-8");
                System.out.println(xml);
                document = DocumentHelper.parseText(xml);

                Element ele = document.getRootElement();
                List<Element> eles = ele.elements("HEADER");
                List<String> erps = new ArrayList<String>();
                List<String> sheets = new ArrayList<String>();
                for (Element e:eles){
                    erps.add(e.element("ERPID").getText());
                    sheets.add(e.element("SheetCode").getText());
                }
                for(int i=0; i<erps.size(); i++) {
                    Element code = codes.addElement("CODE");
                    code.addElement("STATUS").addText("" + 1);
                    code.addElement("ErrorText").addText("");
                    code.addElement("ERPID").addText(erps.get(i));
                    code.addElement("SheetCode").addText(sheets.get(i));
                }
                data = result.asXML();
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/

        return new BASE64Encoder().encodeBuffer(data.getBytes());
    }


    public String planTransformAllocation(String username, String userpwd, String param) {
        List<ERPResultModel> list = new ArrayList<ERPResultModel>();
        Sheet sheet = new Sheet();
        String errorMsg = "";
        String str2 = "同步计划转调拨接口,接口名称:PlanTransformAllocation";
        int num = 0;
        boolean flag = true;
//        int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步计划转调拨接口'").ID;
        String str3 = "username:" + username + ";userpwd:" + userpwd + ";param：" + param;
        try {
            if (!"yxwms".equals(username)) {
                errorMsg = "用户名不正确";
                flag = false;
            }
            if (!"yxwms".equals(userpwd)) {
                errorMsg = "密码不正确";
                flag = false;
            }
            if (flag) {
                if (!StringUtils.isEmpty(param)) {
                    List<Dictionary> allDic = this.dictionaryService.listDics();
                    FormTemplate entity = this.formTemplateService.getFromTemBydicID("" + DictionaryEnums.TypeToUrlId.getIdByType("WZDBD")).get(0);

                    String xml = null;
                    try {
                        xml = new BASE64Decoder().decodeBuffer(param).toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "xml解密失败";
                    }

                    List<Sheet> list3 = new ArrayList<Sheet>();
                    List<SheetDetail> list4 = new ArrayList<SheetDetail>();
                    List<String> list5 = new ArrayList<String>();

                    //转换字符串
                    Document document = null;
                    try {
                        document = DocumentHelper.parseText(xml);
                    } catch (DocumentException e) {
                        return "xml解析失败";
                    }
                    //获取根元素
                    Element root = document.getRootElement();
                    //访问指定名称的节点，如访问名称为“SPECIALITYCODE”的全部节点
                    List<Element> nodes = root.elements("WSINTERFACE");
                    XMLSerializer serializer;
                    for (Element node : nodes) {
                        String str5 = "";
                        serializer = new XMLSerializer();
                        /*list5.add( JsonConvert.SerializeXmlNode(node));*/
                        list5.add(serializer.read(node.asXML()).toString());
                        Sheet item = new Sheet();
                        item.setKindId(DictionaryEnums.TypeToUrlId.getIdByType("WZDBD"));
                        item.setStatus(this.dictionaryService.getDictionaryByCode("YQR").getId());
                        item.setName(entity.getFormTemName());
                        item.setRouteId(Integer.parseInt(entity.getProcessDefinitionKey()));
                        item.setExtendInt4(Integer.parseInt(node.element("ERPID").getText()));
                        item.setCode(node.element("Code").getText());
                        item.setCreateDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, node.element("CreateDate").getText()));

                        String num3 = node.element("ORGANIZATION_ID").getText();
                        Organization organization = this.organizationService.getOrganizationBy(num3);
                        if (organization != null) {
                            item.setExtendInt1(organization.getId());
                        } else {
                            str5 = str5 + "数据库中不存在此库存组织(ID:" + num3 + ");";
                        }
                        String num4 = node.element("ORGANIZATITRANSFER_ORGANIZATION_IDON_ID").getText();
                        Organization organization2 = this.organizationService.getOrganizationBy(num4);
                        if (organization2 != null) {
                            item.setExtendInt2(organization2.getId());
                        } else {
                            str5 = str5 + "数据库中不存在此库存组织(ID:" + num4 + ");";
                        }
                        String innerText = node.element("DepartId").getText();

                        if (!StringUtils.isEmpty(innerText)) {
                            Organization organization3 = this.organizationService.getOrganizationBy(innerText);
                            if (organization3 != null) {
                                item.setDepartId(organization3.getId());
                            } else {
                                str5 = str5 + "数据库中不存在此制单部门(ID:" + innerText + ");";
                            }
                        }
                        String creator = node.element("Creator").getText();
                        User person = this.userService.getUserOne(Integer.parseInt(creator));
                        //User person = EWMS.Service.Base_PersonService.Instance.GetEntity_Fish(" and ExtendInt1=" +  node.element("Creator").getText());
                        if (person != null) {
                            item.setCreator(person.getId());
                        } else {
                            str5 = str5 + "数据库中不存在此制单人(ID:" + creator + ");";
                        }
                        item.setCreateDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, node.element("CreateDate").getText()));

                        String updator = node.element("Updator").getText();
                        if (!StringUtils.isEmpty(updator)) {
                            User person2 = this.userService.getUserOne(Integer.parseInt(updator));
                            //User person2 = EWMS.Service.Base_PersonService.Instance.GetEntity_Fish(" and ExtendInt1=" + node.SelectSingleNode("Updator").InnerText);
                            if (person2 != null) {
                                item.setUpdater(person2.getId());
                            } else {
                                str5 = str5 + "数据库中不存在此更新人(ID:" + updator + ");";
                            }
                        }
                        item.setCreateDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, !StringUtils.isEmpty(node.element("UpdateDate").getText()) ? new Date().toString() : node.element("UpdateDate").getText()));
                        item.setMemo(node.element("Memo").getText());
                        list3.add(item);
                        List<Element> node2s = node.elements("LINE");
                        for (Element node2 : node2s) {

                            SheetDetail detail = new SheetDetail();
                            /*int num5 = Convert.ToInt32(WZ_SheetService.Instance.GetDataTable_Fish("select WZSHEETDETAIL_SEQUENCE.nextval from dual").Rows[0][0].ToString());
                            detail.setId(num5);*/
                            detail.setGuid(java.util.UUID.randomUUID().toString());
                            detail.setExtendInt3(Integer.parseInt(node2.element("ERPDetailID").getText()));

                            String str8 = node2.element("MaterialCode").getText();
                            if (this.sparepartscateService.getSparepartscate(str8) != null) {
                                detail.setMaterialCode(str8);
                            } else {
                                errorMsg = errorMsg + "数据库中不存在此物料(Code:" + str8 + ");";
                            }
                            detail.setDetailUnitName(node2.element("DetailUnitName").getText());
                            detail.setExtendString1(node2.element("MaterialCode").getText());
                            detail.setMaterialName(node2.element("MaterialName").getText());
                            detail.setDetailCount(Double.parseDouble(node2.element("DetailCount").getText()));
                            detail.setNoTaxPrice(Double.parseDouble(node2.element("NotaxPrice").getText()));
                            detail.setNoTaxSum(Double.parseDouble(node2.element("NoTaxSum").getText()));
                            detail.setExtendInt1(Integer.parseInt(node2.element("PlanID").getText()));
                            detail.setExtendInt2(Integer.parseInt(node2.element("PlanDetailID").getText()));
                            detail.setExtendString2(node2.element("PlanCode").getText());
                            String str9 = node2.element("SUBINV_CODE").getText();
                            WareHouse warehouse = this.wareHouseService.getWareHouseCode(str9);
                            if (warehouse != null) {
                                detail.setStoreId(warehouse.getId());
                            } else {
                                str5 = str5 + "数据库中不存在此库房,(编码：" + str9 + ");";
                            }
                            detail.setExtendString3(str5);
                            detail.setExtendInt4(item.getExtendInt4());
                            list4.add(detail);

                        }
                    }
                    List list6 = list3;
                    int size = list6.size();
                    //List<IGrouping<int, Sheet>> list6 = (from x in list3 group x by x.ExtendInt4).ToList<IGrouping<int, WZ_Sheet>>();
                    for (int i = 0; i < list6.size(); i++) {
                        sheet = (Sheet) list6.get(i);
                        List<SheetDetail> details = new ArrayList<SheetDetail>();
                        for (SheetDetail sheetDetail : list4) {
                            if (sheetDetail.getExtendString4() == sheet.getExtendInt4().toString()) {
                                details.add(sheetDetail);
                            }
                        }
                        int k = 0;
                        for (SheetDetail detail1 : details) {
                            if (!StringUtils.isEmpty(detail1.getExtendString3())) {
                                k++;
                            }
                        }
                        if (k == 0) {
                            if ((sheet != null) && !this.sheetService.checkNotExit(sheet.getCode())) {
                                int num7 = 0;
                                String menuCode = entity.getFormTemMenu();
                                Menu menu = this.menuService.getMenuCode(menuCode);
                                //num7 = WZ_SheetService.Instance.InsertOtherSheet(sheet, sheet.Creator, allDic.FirstOrDefault<Base_Dictionary>(m => (m.Code == "WZDBD")).Code, menu);
                                if (num7 > 0) {
                                    List<SheetDetail> enumerator3 = null;
                                    enumerator3 = details;
                                    for (SheetDetail sheetDetail : enumerator3) {
                                        sheetDetail.setSheetId(num7);
                                    }
                                    /*List<SheetDetail>.Enumerator enumerator3;
                                    using (enumerator3 = details.GetEnumerator())
                                    {
                                        while (enumerator3.MoveNext())
                                        {
                                            enumerator3.Current.SheetId = num7;
                                        }
                                    }*/
                                    String str11 = this.sheetDetailService.saveEwmsSheetDetails(details);

                                    //String str11 = DbHelperOra.ExecuteSqlTran2(WZ_SheetDetailService.Instance.GetInsertSqlList(details));
                                    if (str11 == "OK") {
                                        ERPResultModel model = new ERPResultModel(sheet.getCode(), sheet.getExtendInt4().toString(), "", sheet.getCode(), true);
                                        list.add(model);
                                        enumerator3 = details;
                                        for (SheetDetail sheetDetail : enumerator3) {
                                            String str13 = "";
                                            for (String s : list5) {
                                                str13 += "\"ERPDetailID\":\"" + sheetDetail.getExtendInt3() + "\"";
                                            }
                                            //EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str12, 1, "");

                                        }
                                    }
                                    this.sheetService.delSheet(sheet.getExtendInt4());
                                    //WZ_SheetService.Instance.DeleteByWhere(" and ExtendInt4=" + sheet.getExtendInt4());
                                    int rowNum = Integer.parseInt(str11.split(";")[0]);
                                    ERPResultModel model2 = new ERPResultModel(sheet.getExtendInt4().toString(), sheet.getCode(), false);
                                    model2.setErrorText(str11.split(";")[1]);
                                    list.add(model2);
                                    String str13 = "";
                                    for (String s : list5) {
                                        str13 += "\"ERPDetailID\":\"" + details.get(rowNum).getExtendInt3() + "\"";
                                    }
                                    char[] chArray3 = new char[]{';'};
                                    //EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str13, 2, str11.Split(chArray3)[1]);
                                } else {
                                    ERPResultModel model3 = new ERPResultModel(sheet.getCode(), sheet.getExtendInt4().toString(), "插入单据信息失败,ERPID:" + sheet.getExtendInt4() + ";", sheet.getCode(), false);
                                    list.add(model3);
                                    String str14 = "";
                                    for (String s : list5) {
                                        str14 += "\"ERPID\":\"" + sheet.getExtendInt1() + "\"";
                                    }
                                    //EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str14, 2, "插入单据信息失败,ERPID:" + sheet.ExtendInt4 + ";");
                                }
                            }
                        } else {
                            //String errorText =String.Join(";", (from x in details select x.ExtendString3).ToList<String>());
                            ERPResultModel model4 = new ERPResultModel("", sheet.getExtendInt4().toString(), "", sheet.getCode(), false);
                            list.add(model4);
                            String str15 = "";
                            for (String s : list5) {
                                str15 += "\"ERPID\":\"" + sheet.getExtendInt4() + "\"";
                            }
                            //EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str15, 2, String.Join(";", (from x in details select x.ExtendString3).ToList<string>()));
                        }
                    }
                } else {
//                    EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str3, 2, "同步数据为空");
                    ERPResultModel model1 = new ERPResultModel("", "", "同步数据为空", "", false);
                    list.add(model1);
                }
            } else {
                // EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str3, 2, errorMsg);
                ERPResultModel model6 = new ERPResultModel("", "", errorMsg, "", false);
                list.add(model6);
            }
        } catch (Exception exception) {
            this.sheetService.delSheet(sheet.getExtendInt1());
            //EWMS.Service.Inf_TaskLogService.WriteLog(str2, num, iD, str3, 2, exception.Message);
            ERPResultModel model7 = new ERPResultModel("", sheet.getExtendInt4().toString(), exception.getMessage(), sheet.getCode(), false);

            list.add(model7);
        }
        Document result = DocumentHelper.createDocument();
        Element codes = result.addElement("CODES");
        for (ERPResultModel model : list) {
            Element code = codes.addElement("CODE");
            code.addElement("STATUS").addText("" + model.getStatus());
            code.addElement("ErrorText").addText("" + model.getErrorText());
            code.addElement("ERPID").addText("" + model.getErpID());
            code.addElement("SheetCode").addText("" + model.getSheetCode());
        }
        String data = result.asXML();
        return new BASE64Encoder().encodeBuffer(data.getBytes());
    }


}
