package com.zthc.ewms.system.activitiManage.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.activitiManage.controller.guard.ActivitiManageControllerGuard;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.activitiManage.service.ActdicService;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryEnums;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import drk.system.Log;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Controller
@RequestMapping("/system/activitiManage")
public class ActivitiManageController{

	@Resource(name="repositoryService")
	public RepositoryService repositoryService;

	@Resource(name = "dictionaryService")
	public DictionaryService dictionaryService;

	@Resource(name = "actdicService")
	public ActdicService actdicService;

	private final static Log log;
	
	static{
		log = Log.getLog("com.zthc.ewms.system.dictionary.controller.ActivitiManageController");
	}
	/**
	 * ��ѯģ���б�������ֵ�
	 */
	@RequestMapping(value = "modelList.htm")
	public String modelList(HttpServletRequest request,HttpServletResponse response) {
		int proDicPid = DictionaryEnums.DicType.ReceiptProcess.getCode();
		List<Dictionary> actDics = dictionaryService.getDicListByParentId(proDicPid);
		request.setAttribute("tree", actDics);
		return "system/activitiManage/manageActiviti";
	}
	@RequestMapping(value = "modelListTest.htm")
	public String modelListTest(HttpServletRequest request,HttpServletResponse response) {
		List<org.activiti.engine.repository.Model> list = repositoryService.createModelQuery().list();
		request.setAttribute("list", list);
		return "system/activitiManage/showActiviti";
	}

	/**
	 * ���������ֵ��ѯģ���б�
	 */
	@RequestMapping(value = "model.json")
	@ResponseBody
	public LayuiPage<org.activiti.engine.repository.Model> model(HttpServletRequest request, HttpServletResponse response) {
		String dicID = request.getParameter("id");
		int proDicPid = DictionaryEnums.DicType.ReceiptProcess.getCode();
		List<Dictionary> actDics = dictionaryService.getDicListByParentId(proDicPid);
		if(dicID == null){
			dicID = actDics.get(0).getId()+"";
		}
		ActDic actDic = actdicService.getActDicBydicID(dicID);
		LayuiPage<org.activiti.engine.repository.Model> ret = new LayuiPage<org.activiti.engine.repository.Model>();
		List<org.activiti.engine.repository.Model> models = new ArrayList<>();
		if(actDic != null){
			org.activiti.engine.repository.Model modelData = repositoryService.getModel(actDic.getModelID());
			models.add(modelData);
		}
		ret.setData(models);
		ret.setCount((long) models.size());
		return ret;
	}


	/*
     * ɾ��ģ��
     */
	@RequestMapping(value = "modelDel.htm")
	public String  modelDel(HttpServletRequest request,HttpServletResponse response) {
		String modelId = request.getParameter("id");
		repositoryService.deleteModel(modelId);
		System.out.println("ɾ���ɹ���");
		return "redirect:/system/activitiManage/modelListTest.htm";
	}
	/*
     * ����ģ��
     */
	@RequestMapping(value = "modelDeploy.htm")
	public void  modelDeploy(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String modelId = request.getParameter("id");
		String msg ="";
		try {
			org.activiti.engine.repository.Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;
			String starId =null;// = getStarId(bpmnStr, "<startEvent id=\"","\" name=\"");
			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			Collection<FlowElement> even = model.getMainProcess().getFlowElements();
			for (FlowElement flowElement : even){
				if (flowElement instanceof StartEvent) {
					StartEvent startEvent = (StartEvent)flowElement;
					starId=startEvent.getId();
				}
			}
			//��Ӽ���


			/*String bpmnBytesStr = bpmnStr.replaceAll("</userTask>","<extensionElements>  \n" +
					" <activiti:taskListener event=\"all\" class=\"com.zthc.ewms.system.activitiListener.listener.TaskListenerImpl\"></activiti:taskListener>  \n" +
					" </extensionElements>  \n" +
					" </userTask>");*/
			//����������
			//String[] s = bpmnBytesStr.split("<sequenceFlow");
			//String bpmnBytesStr2="";
			//bpmnBytesStr2+=s[0];
			Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();

			for(FlowElement flowElement : flowElements){
				if (flowElement instanceof SequenceFlow) {
					String sourceRef = ((SequenceFlow) flowElement).getSourceRef();
					if(!sourceRef.equals(starId)){
						((SequenceFlow) flowElement).setConditionExpression("${message=='"+flowElement.getId()+"'}");
					}
				}
			}
			/*for(int i=1;i<s.length;i++) {
				String ss = s[i];
				*//*String name = getInsideString(s[i],"name=\"","\" sourceRef");
				if(name.isEmpty()){
					bpmnBytesStr2+=("<sequenceFlow "+ss);
				}else {
					String ss2 = ss.replaceAll("</sequenceFlow>", Matcher.quoteReplacement("<conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${message=='"+name+"'}]]></conditionExpression></sequenceFlow>"));
					bpmnBytesStr2+=("<sequenceFlow "+ss2);
				}*//*
				if(!ss.contains(starId)){
					String id = getInsideString(s[i],"id=\"","\" name=");
					String ss2 = ss.replaceAll("</sequenceFlow>", Matcher.quoteReplacement("<conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${message=='"+id+"'}]]></conditionExpression></sequenceFlow>"));
					bpmnBytesStr2+=("<sequenceFlow "+ss2);
				}else{
					bpmnBytesStr2+=("<sequenceFlow "+ss);
				}
			}*/
			//System.out.println(bpmnBytesStr2);
			//System.out.println( new String(bpmnBytes,"utf-8"));
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);
			String bpmnStr = new String(bpmnBytes,"utf-8");
			String processName = modelData.getName() + ".bpmn20.xml";//modelData.getName()
				Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, bpmnStr).deploy();
			msg =  "���̲���ɹ�";
			//�޸�act_dic�Ĳ���id
			actdicService.updateActDicBymodelId(modelId,deployment.getId());
			//�޸ĵ��ݹ��������̲���ID
			// System.out.println( "����ɹ�������ID=" + deployment.getId()+deployment.getName());
		} catch (Exception e) {
				msg =  "���̲���ʧ��";
			// System.out.println("����ģ�Ͳ�������ʧ�ܣ�modelId:"+ modelId);
		}
		out.println(msg);
		out.flush();
		out.close();
	}
	public  String  getStarId(String  str, String strStart, String strEnd ) {
		//String str2 = str.substring(0,str.indexOf("</startEvent>"));
		String strS = str.substring(str.indexOf("<startEvent") + "<startEvent".length(),str.indexOf("</startEvent>"));
		if(strS.indexOf(strStart) < 0 ){
			return "";
		}
		else if(strS.indexOf(strEnd) < 0){
			return " ";
		}
		else {
			return strS.substring(strS.indexOf(strStart) + strStart.length(), strS.indexOf(strEnd));
		}

	}
	public  String  getInsideString(String  str, String strStart, String strEnd ) {
		String str2 = str.substring(0,str.indexOf("</sequenceFlow>"));
		if(str2.indexOf(strStart) < 0 ){
			return "";
		}
		else if(str2.indexOf(strEnd) < 0){
			return "";
		}
		else {
			return str2.substring(str.indexOf(strStart) + strStart.length(), str.indexOf(strEnd));
		}

	}
	/**
	 * ����model����Ϊָ������
	 *
	 * @param modelId ģ��ID
	 * @param type    �����ļ�����(bpmn\json)
	 */
	@RequestMapping(value = "export/{modelId}/{type}")
	public void export(@PathVariable("modelId") String modelId,
					   @PathVariable("type") String type,
					   HttpServletResponse response) {
		try {
			org.activiti.engine.repository.Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());

			JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);

			// �����쳣
			if (bpmnModel.getMainProcess() == null) {
				response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
				response.getOutputStream().println("no main process, can't export for type: " + type);
				response.flushBuffer();
				return;
			}

			String filename = "";
			byte[] exportBytes = null;

			String mainProcessId = bpmnModel.getMainProcess().getId();

			if (type.equals("bpmn")) {

				BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
				exportBytes = xmlConverter.convertToXML(bpmnModel);

				filename = mainProcessId + ".bpmn20.xml";
			} else if (type.equals("json")) {

				exportBytes = modelEditorSource;
				filename = mainProcessId + ".json";

			}

			ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
			IOUtils.copy(in, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.flushBuffer();
		} catch (Exception e) {
			System.out.println("����model��xml�ļ�ʧ��");
			//log.error("����model��xml�ļ�ʧ�ܣ�modelId={}, type={}", modelId, type, e);
		}
	}
	@ResponseBody
	@RequestMapping("/addModelisTrue.json")
	public void addModelisTrue(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		boolean obj = false;
		ActDic actDic = actdicService.getActDicBydicID(id);
		if(actDic==null){
			obj= true;
		}
		out.println(obj);
		out.flush();
		out.close();
	}
	
}	
	
	
