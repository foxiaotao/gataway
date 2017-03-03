package simon.demo.core.shiro;

import java.io.Serializable;
import java.util.List;

import simon.demo.core.bean.Role;

public class ShiroUser implements Serializable {

		private Long id;
		
		private String username;

		private String name;

		private List<Role> roleList;

		private List<String> permissionList;

		
		
		public ShiroUser(Long id,String username, String name) {
			super();
			this.id = id;
			this.username = username;
			this.name = name;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Role> getRoleList() {
			return roleList;
		}

		public void setRoleList(List<Role> roleList) {
			this.roleList = roleList;
		}

		public List<String> getPermissionList() {
			return permissionList;
		}

		public void setPermissionList(List<String> permissionList) {
			this.permissionList = permissionList;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((permissionList == null) ? 0 : permissionList.hashCode());
			result = prime * result + ((roleList == null) ? 0 : roleList.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShiroUser other = (ShiroUser) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (permissionList == null) {
				if (other.permissionList != null)
					return false;
			} else if (!permissionList.equals(other.permissionList))
				return false;
			if (roleList == null) {
				if (other.roleList != null)
					return false;
			} else if (!roleList.equals(other.roleList))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ShiroUser [username=" + username + ", name=" + name + ", roleList=" + roleList + ", permissionList="
					+ permissionList + "]";
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}



	}