package com.yaoli.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysPermission;
import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysUser;
import com.yaoli.common.obsoleted.SysLoginPwdInterceptor;
import com.yaoli.controller.RoleController;
import com.yaoli.controller.SewageController;
import com.yaoli.service.ISysPermissionService;
import com.yaoli.service.ISysRolePermissionService;
import com.yaoli.service.ISysRoleService;
import com.yaoli.service.ISysUserRoleService;
import com.yaoli.service.ISysUserService;
import com.yaoli.vo.SewageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-shiro.xml", "file:src/main/webapp/WEB-INF/application-dao.xml", "file:src/main/webapp/WEB-INF/application-springmvc.xml" })
public class TestMybatis {
	private static Logger logger = Logger.getLogger(TestMybatis.class);
	@Resource
	private ISysRoleService iRolesService = null;
	@Resource
	private RoleController roleController = null;

	@Resource
	private SysLoginPwdInterceptor sysLoginInterceptor = null;

	@Resource
	private ISysUserService iSysUserService = null;

	@Resource
	private ISysPermissionService iSysPermissionService;

	@Resource
	private ISysUserRoleService iSysUserRoleService;

	@Resource
	private ISysRolePermissionService iSysRolePermissionService;

	@Resource
	private ISysRoleService iSysRoleService;
	
	@Resource
	private SewageController sewageController;
	
	
	
	// @Test
	public void test1() {
		// ApplicationContext ac = new
		// ClassPathXmlApplicationContext("spring-mybatis.xml");
		// iRolesService = (IRolesService)ac.getBean("rolesService");
		SysRole user = iRolesService.getRoleById(1);
		logger.info(JSON.toJSONString(user));
	}
	
	
	@Test
	public void ajax() throws IOException, ParseException{
		SewageVO sewageVO = new SewageVO();
		//sewageVO.setSewageId(169);
		
		sewageController.ajaxGetSewageRunInfoSewageId(sewageVO, request, response);
		
	}

	

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();

		response = new MockHttpServletResponse();
	}

	//@Test
	public void testLogin() {
		try {
			// request.setParameter("userName", "admin");
			// logger.info(JSON.toJSON(iSysUserService.getUserByLoginName("yaoli")));
			// assertEquals(true, sysLoginInterceptor.preHandle(request, response, null));
/*			SysUser user = new SysUser();
			user.setId(1);
			
			// 得以用户所有的角色
			List<SysRole> userRolesList = iSysRoleService.getUserRolesByUserId(user.getId());
			
			//得到用户的所有权限
			List<SysPermission> sysPermissionList = iSysPermissionService.getPermissionsByUserId(user.getId());
			
			JSON.toJSON(userRolesList);
			JSON.toJSON(sysPermissionList);
			
			System.out.println();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
