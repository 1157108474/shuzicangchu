package org.activiti.rest.editor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.activitiManage.service.ActdicService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/model")
public class ModuleController {

	private Logger logger = LoggerFactory.getLogger(ModuleController.class);

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ManagementService managementService;

	@Resource
	private ActdicService actdicService;
	@Resource
	private DictionaryService dictionaryService;


	@RequestMapping(value = "/create.htm")
	public void create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String id = request.getParameter("id");
		Dictionary dictionary = dictionaryService.getDic(Integer.parseInt(id));
		try {
			String name = dictionary.getName();
			String key = dictionary.getName();
			String description = dictionary.getName()+"审批流程";

			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			Model modelData = repositoryService.newModel();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

			ActDic actDic = new ActDic();
			actDic.setDicID(id);
			actDic.setModelID(modelData.getId());
			actdicService.insertActDic(actDic);
			response.sendRedirect("/manageModeler.html?modelId=" + modelData.getId());
			//request.setAttribute("modelId",modelData.getId());
			/*response.sendRedirect(request.getServerName()+"/"+
					request.getContextPath() + "/manageModeler.html?modelId=" + modelData.getId());*/
			//System.out.println("跳转页面");
			//response.sendRedirect("manageModeler.html?modelId=" + modelData.getId());
		} catch (Exception e) {
			logger.error("出错", e);
		}
		//return "system/activitiManage/manageModeler";
	}
	@RequestMapping(value = "/createAll.htm")
	public void createAll(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List< Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map1 = new HashMap<>();
		map1.put("dicId","32");map1.put("name","物资入库");
		Map<String,Object> map2 = new HashMap<>();
		map2.put("dicId","316");map2.put("name","物资退库");
		Map<String,Object> map3 = new HashMap<>();
		map3.put("dicId","768");map3.put("name","直接退库");
		Map<String,Object> map4 = new HashMap<>();
		map4.put("dicId","651");map4.put("name","物资领料");
		Map<String,Object> map5 = new HashMap<>();
		map5.put("dicId","37");map5.put("name","物资寄存出库");
		Map<String,Object> map6 = new HashMap<>();
		map6.put("dicId","468");map6.put("name","物资寄存入库");
		Map<String,Object> map7 = new HashMap<>();
		map7.put("dicId","467");map7.put("name","物资接收");
		Map<String,Object> map8 = new HashMap<>();
		map8.put("dicId","590");map8.put("name","库存盘点");
		Map<String,Object> map9 = new HashMap<>();
		map9.put("dicId","248");map9.put("name","移库移位");
		Map<String,Object> map10 = new HashMap<>();
		map10.put("dicId","162");map10.put("name","变更");
		Map<String,Object> map11 = new HashMap<>();
		map11.put("dicId","107");map11.put("name","物资入库");
		Map<String,Object> map12 = new HashMap<>();
		map12.put("dicId","486");map12.put("name","物资退货");
		Map<String,Object> map13 = new HashMap<>();
		map13.put("dicId","326");map13.put("name","赠送品入库");
		Map<String,Object> map14 = new HashMap<>();
		map14.put("dicId","446");map14.put("name","物资申请");
		Map<String,Object> map15 = new HashMap<>();
		map15.put("dicId","569");map15.put("name","期初建账");
		Map<String,Object> map16 = new HashMap<>();
		map15.put("dicId","507");map15.put("name","物资调拨");
	}
	public void createServer(String name,String key,String description,String dicId) throws UnsupportedEncodingException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			Model modelData = repositoryService.newModel();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

			ActDic actDic = new ActDic();
			actDic.setDicID(dicId);
			actDic.setModelID(modelData.getId());
			actdicService.insertActDic(actDic);
		} catch (Exception e) {
			logger.error("出错", e);
		}
	}
}