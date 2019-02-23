package com.zthc.ewms.system.activitiManage.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.activitiManage.entity.TestRole;
import com.zthc.ewms.system.activitiManage.entity.TestUser;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.service.RoleService;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.service.UserRoleService;
import com.zthc.ewms.system.user.service.UserService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONArray;

@Controller
@RequestMapping("/activitipart")
public class ActivitiPartController {

	@Resource
	private RoleService roleService;

	@Resource
	private UserRoleService userRoleService;

	@Resource(name = "dictionaryService")
	public DictionaryService dictionaryService;

	@ResponseBody
	@RequestMapping("/getRole.htm")
	public void getRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//List<Role> roles = roleService.listRoles();
		List<Role> roles = roleService.listRoleNames();
		Object obj=JSONArray.fromObject(roles.toArray());
		out.println(obj);
		out.flush();
		out.close();
	}

	@ResponseBody
	@RequestMapping("/gotoRole.htm")
	public void gotoRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int page = Integer.parseInt(request.getParameter("page"));
		int size =  Integer.parseInt(request.getParameter("size"));
		RoleCondition condition = new RoleCondition();
		condition.setPageNum(page);
		condition.setPageTotal(size);
		//LayuiPage<Role> result = roleService.listRole(new Role(),condition);
		LayuiPage<Role> result = roleService.listRoleName(new Role(),condition);
		/*List<Role> roles = roleService.listRoles();
		int startNo = (page- 1) * size;
		List<Role> result=null;
		if(startNo+size<roles.size()){
			result = roles.subList(startNo, startNo + size);
		}else{
			result = roles.subList(startNo, roles.size());
		}*/
		Object roleObj=JSONArray.fromObject(result.getData().toArray());
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("status",1);
		ret.put("count",result.getCount());
		ret.put("list",roleObj);
		Object obj = JSONArray.fromObject(ret);
		out.println(obj);
		out.flush();
		out.close();
	}

	@ResponseBody
	@RequestMapping("/getUserByRole.htm")
	public void getUserByRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int page = Integer.parseInt(request.getParameter("page"));
		int size =  Integer.parseInt(request.getParameter("size"));
		String roleId = request.getParameter("roleId");
		Role role = roleService.getRoleOne(roleId);
		UserCondition condition = new UserCondition();
		condition.setPageNum(page);
		condition.setPageTotal(size);
		LayuiPage<User> result = userRoleService.listRoleUserName(role,condition);
		/*List<User> users = userRoleService.listRoleUsers(role,null);
		int startNo = (page - 1) * size;
		List<User> result=null;
		if(startNo+size<users.size()){
			result = users.subList(startNo, startNo + size);
		}else{
			result = users.subList(startNo, users.size());
		}*/
		Object usersObj=JSONArray.fromObject(result.getData().toArray());
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("status",1);
		ret.put("count",result.getCount());
		ret.put("list",usersObj);
		Object obj = JSONArray.fromObject(ret);
		out.println(obj);
		out.flush();
		out.close();
	}

	@ResponseBody
	@RequestMapping("/getPermission.htm")
	public void getPermission(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<Dictionary> dictionarys = dictionaryService.getDicListByParentId(44);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"parent"});
		Object obj=JSONArray.fromObject(dictionarys.toArray(),jsonConfig);
		out.println(obj);
		out.flush();
		out.close();
	}
}
