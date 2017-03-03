package simon.demo.core.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import simon.demo.core.bean.Role;
import simon.demo.core.bean.User;
import simon.demo.core.bean.UserExample;
import simon.demo.core.dao.UserMapper;

/**
 * Created by star on 5/15/14.
 */
public class ShiroRealm extends AuthorizingRealm {

	private final static Log logger = LogFactory.getLog(ShiroRealm.class);
	@Autowired
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		logger.info("Authorization::::" + shiroUser.getUsername());
		try {
			if (shiroUser != null) {
				List<Role> roleList = shiroUser.getRoleList();
				List<String> permissionList = shiroUser.getPermissionList();
				if (roleList != null && !roleList.isEmpty()) {
					SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
					StringBuffer roleIdSbf = new StringBuffer(); // 保存角色Id
					for (Role role : roleList) {
						info.addRole(role.getName());
						roleIdSbf.append(role.getRid()).append(",");
						logger.debug("添加角色::" + role.getName() + " TO 用户:::"
								+ shiroUser.getName());
					}

					if (permissionList != null && !permissionList.isEmpty()) {
						for (String permissionStr : permissionList) {
							info.addStringPermission(permissionStr);
							logger.debug("添加权限::" + permissionStr + " TO 用户:::"
									+ shiroUser.getName());
						}
					}

					return info;
				}
			}
		} catch (Exception e) {
			logger.error("查询角色出错::::::");
			logger.error(e);
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		try {
			UserExample example = new UserExample();
			example.createCriteria().andUsernameEqualTo(username);
			List<User> users = userMapper.selectByExample(example);
			User user = users.get(0);
			if (user != null) {
//				if (0 == user.getId() || "".equals(user.getUsername())
//						|| "".equals(user.getName())) {
//					throw new DisabledAccountException();
//				}

				ShiroUser shiroUser = new ShiroUser(user.getId(),user.getUsername(),
						user.getName());
//				ShiroUser shiroUser = new ShiroUser(user.getUsername(),
//						user.getName(), Integer.parseInt(logininfo[1]),
//						user.getLjpym(), user.getLjjc(), user.getLjqc(),
//						user.getDeptname(), user.getPostId(),
//						user.getPostName());

				// 添加角色列表
//				List<Role> roleList = roleDao.getRoleByAccId(shiroUser
//						.getAccId());
//				shiroUser.setRoleList(roleList);
//				String roleIds = "";
//				StringBuffer roleIdSbf = new StringBuffer(""); // 保存角色Id
//				for (Role role : roleList) {
//					roleIdSbf.append(role.getId()).append(",");
//				}
//				if (roleIdSbf.indexOf(",") > -1) {
//					roleIds = roleIdSbf
//							.substring(0, roleIdSbf.lastIndexOf(","));
//				}
//
//				// 添加权限列表
//				List<String> permissionKeyList = new ArrayList<String>();
//				Map<String, String> queryMap = new HashMap<String, String>();
//				queryMap.put("roleIds", roleIds);
//				List<Permission> permissionList = roleDao
//						.getPermissionByRoleIds(queryMap);
//				if (permissionList != null && !permissionList.isEmpty()) {
//					for (Permission permission : permissionList) {
//						permissionKeyList.add(permission.getKey());
//					}
//				}
//				shiroUser.setPermissionList(permissionKeyList);
//				logger.info("权限列表::" + shiroUser.getPermissionList());

				return new SimpleAuthenticationInfo(shiroUser,
						user.getPassword(), getName());
			} else {

				throw new Exception("not found");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}


}
