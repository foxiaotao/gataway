package simon.demo.core.bean;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
	private Integer id;
	private String iconCss;
	private String name;
	private String url;
	private boolean leaf;
	private String parentId;
	private List<MenuTree> children = new ArrayList<MenuTree>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIconCss() {
		return iconCss;
	}
	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	
	
}
