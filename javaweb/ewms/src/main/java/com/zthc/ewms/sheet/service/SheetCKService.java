package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.base.util.DateUtils;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.sheet.entity.ck.dto.RenewalCostDTO;
import com.zthc.ewms.sheet.entity.enums.RenewalCostEnum;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAIL;
import com.zthc.ewms.sheet.entity.guard.SheetStock;
import com.zthc.ewms.sheet.entity.query.VCgjhEntity;
import com.zthc.ewms.sheet.service.guard.SheetCKServiceGuard;
import com.zthc.ewms.system.activitiListener.service.ActivitiService;
import com.zthc.ewms.system.applyDep.entity.ApplyDep;
import com.zthc.ewms.system.applyDep.service.ApplyDepService;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.provider.entity.Provider;
import com.zthc.ewms.system.provider.service.ProviderService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SheetCKService extends SheetCKServiceGuard {

	@Resource(name = "organizationService")
	public OrganizationService organizationService;
	@Resource(name = "userService")
	public UserService userService;
	@Resource(name = "dictionaryService")
	public DictionaryService dictionaryService;
	@Resource(name = "applyDepService")
	public ApplyDepService applyDepService;

	@Resource(name = "wareHouseService")
	public WareHouseService wareHouseService;
	@Resource(name = "providerService")
	public ProviderService providerService;

	@Resource(name = "sheetService")
	public SheetService sheetService;
	@Resource(name = "vCgjhEntityService")
	public VCgjhEntityService vCgjhEntityService;
	@Resource(name = "sheetRKDETAILService")
	public SheetRKDETAILService sheetRKDETAILService;
	@Resource(name = "sheetCGService")
	public SheetCGService sheetCGService;
	@Resource(name = "sheetDetailService")
	public SheetDetailService detailService;
	@Autowired
	private ActivitiService activitiService;

	private final static Log xmllog = Log.getLog(" com.zthc.ewms.sheet.sheetCK.xmllog");

	/**
	 * �����б��ѯ����
	 *
	 * @param className
	 * @param key
	 * @param param
	 * @param condition
	 * @param           <T>
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public <T> LayuiPage<T> publicDetails(String className, String key, Map<String, Object> param,
			Condition condition) {
		return this.dao.publicDetails(className, key, param, condition);
	}

	@Transactional
	public void saveSheetCkDetail(List<SheetCKDETAIL> detailList) {
		for (SheetCKDETAIL detail : detailList) {
			// TODO ����һ�����ⵥֻ����һ���ⷿ
			/*
			 * List<SheetCKDETAIL> listSheetCKdetail =
			 * this.dao.listSheetCKdetail(detail.getSheetId()); for (SheetCKDETAIL ckdetail
			 * : listSheetCKdetail) {
			 * if(!detail.getStoreId().equals(ckdetail.getStoreId())){ throw new
			 * RuntimeException("ÿ�����ⵥֻ����ѡ��һ���ⷿ��"); } }
			 */
			this.dao.saveSheetCkDetail(detail);
		}
	}

	@Transactional
	public JSONObject isTrue(Object id) {
		JSONObject ret = new JSONObject();

		long count = this.dao.getDetailCount(id);
		if (count <= 0) {
			ret.put("code", false);
			ret.put("message", "�������ϸ��");
		} else {
			ret.put("code", true);
			ret.put("message", "");
		}
		return ret;
	}

	@Transactional
	public void updateSheetCK(Integer id, String memo, String extendString1, Integer userId) {
		if (this.dao.updateSheetCK(id, memo, extendString1, userId) != 1) {
			throw new RuntimeException("");
		}
	}

	/**
	 * �������ӿ�
	 *
	 * @return
	 */
	public String pushSLCKXML(SheetCK sheet) {
//        int syncResult = 0;
//        String str = "CUXYXINVISS";
//        String str2 = "10000";
//        String str3 = "";
//        String str4 = "";
//        String str5 = "";
//        String str6 = "";
//        String str7 = "ͬ�����ϳ���ӿ�,�ӿ�����:invokews";
//        int num2 = 0;
////        int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='ͬ�����ϳ�����Ϣ'").ID;
//        String str8 = "�̶�ֵ:"+str+";batchNum:"+str2+";requestData��"+str3;
//
//        xmllog.message("SLCKXML");
//        SheetCK sheet = this.getSheetCKOne(id);
		xmllog.message("sheetId:" + sheet.getId() + ";;;;sheetCode:" + sheet.getCode());
		List<SheetCKDETAIL> detailList = this.detailService.sheetDetails(sheet.getId(), "SheetCKDETAIL");
//        try {
//            cux_fnd_ws_server_prgClient client = new cux_fnd_ws_server_prgClient();
//            XmlDeclaration newChild = document.CreateXmlDeclaration("1.0", "utf-8", null);

		Document document = DocumentHelper.createDocument();

		Element element = document.addElement("WSINTERFACE");
		Element node = element.addElement("HEADER");

		// ��ֹ���ݻ���,900000001��ʼ����ˮ��
		node.addElement("HEADER_ID").addText(StringUtils.getHeaderId(sheet.getId()));

		node.addElement("DOC_NUM").addText(sheet.getCode() == null ? "" : sheet.getCode());
		node.addElement("TRANS_DATE").addText(DateUtils.getTime());
		// node.addElement("TRANS_DATE").addText("2018-06-10 12:30:21");

		Organization organization = this.organizationService.getOrganizationOne(sheet.getZtid());
		node.addElement("ORGANIZATION_ID")
				.addText(organization.getExtendint1() == null ? "" : organization.getExtendint1().toString());

		organization = this.organizationService.getOrganizationOne(sheet.getDepartid());
		node.addElement("DEPT_ID")
				.addText(organization.getExtendint1() == null ? "" : organization.getExtendint1().toString());
		// ��ȡ������
		User person = this.userService.getUserOne(sheet.getCreator());
		node.addElement("MAKER_ID").addText(person.getExtendint1() == null ? "" : person.getExtendint1().toString());
		node.addElement("MAKER_NAME").addText(sheet.getName() == null ? "" : person.getName());
		// ע�������Ƿ���ȷ
		node.addElement("MAKER_DATE").addText(sheet.getCreatedate() == null ? ""
				: DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, sheet.getCreatedate()));

		if (sheet.getUpdator() != null) {
			person = this.userService.getUserOne(sheet.getUpdator());
			String updateByIdStr = "";
			if (person != null) {
				updateByIdStr = person.getExtendint1() == null ? "" : person.getExtendint1().toString();
			}
			node.addElement("UPDATE_BY_ID").addText(updateByIdStr);
			node.addElement("UPDATE_DATE").addText(sheet.getUpdatedate() == null ? ""
					: DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, sheet.getUpdatedate()));
		} else {
			node.addElement("UPDATE_BY_ID").addText("");
			node.addElement("UPDATE_DATE").addText("");
		}
		// 20180915 null
		node.addElement("NOTES").addText(sheet.getMemo() == null ? "" : sheet.getMemo());

		Dictionary dictionary = this.dictionaryService.getDictionaryOne(sheet.getFundssource());
		node.addElement("FIN_SOURCE").addText(dictionary.getName() == null ? "" : dictionary.getName());
		// 20180915 null
		node.addElement("USE").addText(sheet.getExtendstring1() == null ? "" : sheet.getExtendstring1());

		ApplyDep dep = this.applyDepService.getOne(sheet.getApplydepartid());
		node.addElement("ALIAS_NAME").addText(dep.getName() == null ? "" : dep.getName());
		node.addElement("ALIAS_ID").addText(dep.getErpId() == null ? "" : dep.getErpId());

		node.addElement("BATCH_NAME").addText(DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()));
		node.addElement("TRANS_TYPE").addText("105");
		node.addElement("PROJECT_CODE").addText("");
		node.addElement("CONTRACT_CODE").addText("");

		node.addElement("USING_DEPARTMENT").addText("");
		node.addElement("APPLY_EMP_NUM").addText("");
		node.addElement("APPROVER_NUM").addText("");

		WareHouse warehouse;
		SheetStock stock;
		VCgjhEntity plan = null;
		for (SheetCKDETAIL detail : detailList) {
			Element node2 = node.addElement("LINE");
			// ��ֹ���ݻ���,900000001��ʼ����ˮ��
			node2.addElement("LINE_ID").addText(StringUtils.getHeaderId(detail.getId()));

			node2.addElement("ITEM_NO").addText(detail.getMaterialCode() == null ? "" : detail.getMaterialCode());
			node2.addElement("ITEM_DESC").addText(detail.getDescription() == null ? "" : detail.getDescription());
			node2.addElement("TRANS_UOM").addText(detail.getDetailUnitName() == null ? "" : detail.getDetailUnitName());
//          node2.addElement("TRANS_QUANTITY").addText(decimal.Zero - detail.DetailCount);
			node2.addElement("TRANS_QUANTITY")
					.addText(detail.getDetailCount() == null ? "" : detail.getDetailCount().toString());
			// node2.addElement("UOM").addText(detail.getDetailUnitName());
//          node2.addElement("QUANTITY").addText((decimal.Zero - detail.DetailCount).ToString());

			node2.addElement("QUANTITY").addText("-" + detail.getDetailCount().toString());

			// 20180915 null
			node2.addElement("NOTES").addText(sheet.getMemo() == null ? "" : sheet.getMemo());
			node2.addElement("UOM").addText(detail.getDetailUnitName() == null ? "" : detail.getDetailUnitName());
			// ��ȡ�ⷿ
			warehouse = this.wareHouseService.getWareHouseOne(detail.getStoreId());

			node2.addElement("SUBINV_CODE").addText((warehouse == null) ? "" : warehouse.getCode());

			Boolean isExistWarehouse = this.wareHouseService.isExistWarehouse(warehouse.getId());
			if (isExistWarehouse) {
				// ��ȡ��λ
				warehouse = this.wareHouseService.getWareHouse(detail.getStoreLocationId());
				if (warehouse == null) {
					throw new RuntimeException("��ȡ��λʧ��");
				}
				if (warehouse.getErpId() != null) {
					node2.addElement("LOCATOR_ID")
							.addText(warehouse.getErpId() == null ? "" : warehouse.getErpId().toString());
					node2.addElement("LOCATOR_CODE").addText(warehouse.getCode() == null ? "" : warehouse.getCode());
				}
			}

			node2.addElement("ASSET_NUMBER").addText("");

			stock = this.sheetService.getSheetStockOne(detail.getExtendint2());
			Provider provider = this.providerService.getOne(detail.getProviderDepId());

			int num6 = DictionaryEnums.JsType.IsJs.getCode();
			String extendInt2Str = provider.getExtendInt2() == null ? "" : provider.getExtendInt2().toString();

			node2.addElement("OWNING_ORGANIZATION_ID").addText((stock.getOwnerType() == num6) ? extendInt2Str : "");
			node2.addElement("OWNING_ORGANIZATION_NAME").addText(
					(stock.getOwnerType() == num6) ? (provider.getName() + "_" + provider.getExtendString1()) : "");
			// 20180915 null
			node2.addElement("SERIAL_NUMBER_CONTROL_CODE")
					.addText(detail.getEnablesn() != null && (detail.getEnablesn() == 1) ? "5" : "1");
			// 20180915 null ExtendInt5 --> �ɹ��ƻ�Id
			if (detail.getExtendInt5() != null) {
				plan = this.vCgjhEntityService.getOne(detail.getExtendInt5());
			}

			node2.addElement("CONSIGNED_FLAG").addText((stock.getOwnerType() == num6) ? "Y" : "N");
			node2.addElement("SOURCE_LINE_ID").addText("");
			node2.addElement("SOURCE_DOC_NUM").addText("");
			node2.addElement("SOURCE_HEADER_ID").addText("");
			node2.addElement("SCHEDULT_LN_ID")
					.addText((plan == null || plan.getErpDetailId() == null) ? "" : plan.getErpDetailId().toString());
			node2.addElement("SCHEDULT_HD_ID")
					.addText((plan == null || plan.getErpId() == null) ? "" : plan.getErpId().toString());

			node2.addElement("SOURCE_LINGTUI_ID").addText("");
			node2.addElement("SOURCE_LINGTUI_DOC_NUM").addText("");
			node2.addElement("SOURCE_LINGTUI_LN_ID").addText("");
			node2.addElement("USING_ADDRESS").addText("");

		}

		return document.getRootElement().asXML();
//            xmllog.message("����ǰ:"+data);
//            document = DocumentHelper.createDocument();
//            Element invokews = document.addElement("cux:invokews");
//            invokews.addElement("pIfaceCode").addText("CUXYXINVISS");
//            invokews.addElement("pBatchNumber").addText("10000");
//            invokews.addElement("pRequestData").addText(new BASE64Encoder().encodeBuffer(data.getBytes()));
//            ERPWebService erp =new ERPWebServiceImpl();
//            str8 = erp.pushXML(document.asXML());
//
////          client.invokews(str, str2, str3, ref str4, ref str5, ref str6);
//            syncResult = (str4 == "S000A000") ? 1 : 0;
////          Inf_TaskLogService.WriteLog(str7, num2, iD, str8, syncResult, str5);
//        } catch (Exception exception) {
//            syncResult = -1;
////            Inf_TaskLogService.WriteLog(str7, num2, iD, str8, 2, exception.Message);
//        }
//        return syncResult;
	}

	/**
	 * �첽���³ɱ�
	 *
	 * @param sheetCK
	 * @param userId
	 * @throws Exception
	 */
	@Transactional
	public void asynRenewalCost(SheetCK sheetCK, Integer userId) throws Exception {
		xmllog.error("�����³ɱ��� ���뷽��;sheetId:" + sheetCK.getId() + ";userId:" + userId);

		// ��ȡ�����µ���ϸ
		List<SheetCKDETAIL> sheetCKDETAILList = this.dao.listSheetCKdetail(sheetCK.getId());
		if (StringUtils.isEmpty(sheetCKDETAILList)) {
			throw new RuntimeException("�õ���û����ϸ");
		}
		List<RenewalCostDTO> renewalCostDTOList = new ArrayList<>();
		// ������ϸͬ�����³ɱ��ӿ�
		for (SheetCKDETAIL sheetCKDETAIL : sheetCKDETAILList) {
			RenewalCostDTO renewalCostDTO = this.activitiService.syncToRenewalCost(sheetCKDETAIL.getId(), userId);
			renewalCostDTOList.add(renewalCostDTO);
		}
		this.renewalCost(sheetCK.getId(), RenewalCostEnum.SUCCESS.getRenewalCost(), renewalCostDTOList);
	}

	/**
	 * ���³ɱ�
	 *
	 * @param sheetId
	 * @param renewalCost
	 * @param renewalCostDTOList
	 */
	@Transactional
	public void renewalCost(Integer sheetId, Integer renewalCost, List<RenewalCostDTO> renewalCostDTOList) {
		this.dao.renewalCost(sheetId, renewalCost);
		// ������ϸ�ɱ�
		for (RenewalCostDTO renewalCostDTO : renewalCostDTOList) {
			this.dao.renewalCostDetail(renewalCostDTO.getSheetCKDETAILId(), renewalCostDTO.getNoTaxPrice(),
					renewalCostDTO.getNotAxSum());
		}
	}

	/**
	 * ���³ɱ���ʱ����
	 *
	 * @return
	 */
	@Transactional
	public void renewalCostTask() {
		List<SheetCK> sheetCKS = this.dao.querySheetCKRenewalCost();
		for (SheetCK sheetCK : sheetCKS) {
			try {
				asynRenewalCost(sheetCK, 0);
			} catch (Exception e) {
				e.printStackTrace();
				xmllog.error("���³ɱ�ʧ�ܣ�sheetId:" + sheetCK.getId());
			}
		}
	}

	@Transactional
	public List<SheetCKDETAIL> printCKDetails(Integer id) {
		return this.dao.printCKDetails(id);
	}

}
