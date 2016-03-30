package com.yaoli.shiro.test;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysRole;
import com.yaoli.common.obsoleted.SysLoginJDBCRealm;
import com.yaoli.common.obsoleted.SysLoginPwdInterceptor;
import com.yaoli.controller.RoleController;
import com.yaoli.service.ISysRoleService;
import com.yaoli.service.ISysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-spring.xml", "file:src/main/webapp/WEB-INF/application-dao.xml", "file:src/main/webapp/WEB-INF/application-springmvc.xml" })
public class TestSysLoginJDBCRealm {
	private static Logger logger = Logger.getLogger(TestSysLoginJDBCRealm.class);
	@Resource
	private ISysRoleService iRolesService = null;
	@Resource
	private RoleController roleController = null;
	
	@Resource 
	private SysLoginPwdInterceptor sysLoginInterceptor = null; 
	
	@Resource
	private ISysUserService iSysUserService = null;
	
	@Resource 
	private SysLoginJDBCRealm sysLoginJDBCRealm = null; 
	
	
	
	// @Test
	public void test1() {
		SysRole user = iRolesService.getRoleById(1);
		logger.info(JSON.toJSONString(user));
	}

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();

		response = new MockHttpServletResponse();
		
		//sysLoginJDBCRealm = new SysLoginJDBCRealm();
	}

	@Test
	public void testGetAuthenticationToken() {
		try {
			//sysLoginInterceptor.preHandle(request, response, null);
			
			AuthenticationToken token = new UsernamePasswordToken("yaoli","yaoli2");
			
			//切记 切记，junit中的所有 实例化对象全部交给框架
			//SimpleAuthenticationInfo info = (SimpleAuthenticationInfo)sysLoginJDBCRealm.getAuthenticationToken(token);
			
			//JSON.toJSONString(info);
			//assertEquals(true, sysLoginInterceptor.preHandle(request, response, null));
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}