package com.yaoli.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Array;
import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysRoleMenu;
import com.yaoli.service.ISysRoleService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.RoleForm;
import com.yaoli.vo.SysUserVO;


@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private ISysRoleService irolesService;
	
	@RequiresPermissions("getallrolessystem:admin:view")
	@RequestMapping("/role.do")
	public String showRole(HttpServletRequest request,Model model){
		int id = Integer.parseInt(request.getParameter("id"));
		SysRole role = irolesService.getRoleById(id);
		model.addAttribute("role", role);
		return "/showrole";
	}
	
	@RequestMapping("/get.do")
	public String getR(HttpServletRequest request,Model model){
		System.out.println("ayoli");
		return "/showrole";
	}
	
	@RequestMapping("/getallroles.do")
	public void getAllRoles(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		/*以下代码只是看request.getParameter中有哪些参数*/
/*		for(Iterator iter = request.getParameterMap().entrySet().iterator();iter.hasNext();){ 
	        Map.Entry element = (Map.Entry)iter.next(); 
	        Object strKey = element.getKey();
	        Object strObj = element.getValue();
	        System.out.println(strKey+"  "+strObj);
		}*/
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		List<SysRole> list= irolesService.getRolesByPaging(map);
		int count = irolesService.getTotalCount();
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);
		
		
		
		
		
/*		sysPagingUtil.setPageNumber(pageNum);//page 当前页
		
		sysPagingUtil.setPageSize(String.valueOf(count/Integer.valueOf(pageSize)));//total 一共多少页
		
		sysPagingUtil.setTotal(String.valueOf(count));//records 一共多少条记录
*/		
		//sysPagingUtil.setPageNumber(pageNum);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoshowrole.do")
	public String gotoShowRoles(){
		return "/role/showroles";
	}
	
	@RequestMapping("/gotoaddnewrole.do")
	public String gotoAddnewRole(){
		return "/role/addnewrole";
	}
	
	@RequestMapping("/addnewrole.do")
	public void addNewRole(@RequestBody RoleForm roleForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysRole sysRole = new SysRole();
		sysRole.setName(roleForm.getName());
		int roleCount = irolesService.insert(sysRole);
		
		if(roleCount != 1){
			throw new Exception("插入新角色失败");
		}
		
		//根据用户名获取用户id，用户查询
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		
		//循环插入信息，这里应该用mybatis内置的功能，今后优化
		for (int i = 0; i < roleForm.getMeunIds().size(); i++) {
			sysRoleMenu.setMenuid(roleForm.getMeunIds().get(i));
			sysRoleMenu.setRoleid(sysRole.getId());
			int count2 = irolesService.insertRoleMenu(sysRoleMenu);
			if(count2 != 1){
				throw new Exception("插入新角色失败");
			}
		}
		
		JsonMessage json = new JsonMessage();
		json.setKey("pass");
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(json));
	}
	
	@RequestMapping("/deleteselectedusers.do")
	public void deleteRoles(@RequestBody RoleForm roleForm,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Integer> delRoleIdList = roleForm.getDelRoleIds();
		
		//已经绑定用户的角色id，即那些不能删除的角色
		List<Integer> hasBindingUserRoles = new ArrayList<Integer>();
		
		
		//规定 如果 某个角色已经绑定了某个用户 那么这个角色删除不了
		for (int i = 0; i < delRoleIdList.size(); i++) {
			//if(delRoleIdList.get(i)
			if (irolesService.getRoleBindingUserCount(delRoleIdList.get(i)) > 0) {
				hasBindingUserRoles.add(delRoleIdList.get(i));
				//表明该id不能删除
			}else{
				//可以删除该角色
				int count = irolesService.deleteByPrimaryKey(delRoleIdList.get(i));
				//也要删除 角色和菜单的关联 sys rolemenu表
				int countRoleMenu = irolesService.deleteRoleMenuByRoleId(delRoleIdList.get(i));
				if(count != 1 && countRoleMenu == 0){
					throw new Exception("删除角色失败");
				}
			}
		}
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		jsonMessage.setData(hasBindingUserRoles);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
}
