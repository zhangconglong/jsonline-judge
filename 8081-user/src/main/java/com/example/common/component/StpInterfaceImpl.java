package com.example.common.component;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: v1.0
 * @description：自定义权限验证接口扩展
 * @author: zhangconglong on 2021/11/27 20:39
 */
@Component	// 打开此注解，保证此类被springboot扫描，即可完成sa-token的自定义权限验证扩展 
public class StpInterfaceImpl implements StpInterface {

	/**
	 * 返回一个账号拥有的【权限】
	 * @param loginId 用户id
	 * @param loginType 用户角色
	 * @return
	 */
	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {
		// 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
		List<String> list = new ArrayList<String>();	
		list.add("101");
		list.add("user-add");
		list.add("user-delete");
		list.add("user-update");
		list.add("user-get");
		list.add("article-get");
		return list;
	}

	/**
	 * 返回一个账号所拥有的【角色】
	 * @param loginId
	 * @param loginType
	 * @return
	 */
	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		// 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
		List<String> list = new ArrayList<String>();	
		list.add("admin");
		return list;
	}
}