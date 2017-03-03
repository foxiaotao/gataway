package simon.demo.core.util;

import java.util.ArrayList;
import java.util.List;

import simon.demo.core.bean.MenuPrivalige;
import simon.demo.core.bean.MenuTree;

public class TreeUtil {

	
	public static List<MenuTree> parseMenuTree(List<MenuPrivalige> mp){
		List<MenuTree> res = new ArrayList<MenuTree>();
		for (MenuPrivalige menuPrivalige : mp) {
			if(menuPrivalige.getParentid()==null){
				MenuTree item = new MenuTree();
				item.setId(menuPrivalige.getId().intValue());
				item.setName(menuPrivalige.getName());
				item.setUrl(menuPrivalige.getUrl());
				item.setLeaf(false);
				findChildren(item,mp);
				res.add(item);
			}
		}
		
		return res;
	}

	private static void findChildren(MenuTree item, List<MenuPrivalige> mp) {
		List<MenuTree> children = new ArrayList<MenuTree>();
		for (MenuPrivalige menuPrivalige : mp) {
			if(menuPrivalige.getParentid()!=null){
				if(menuPrivalige.getParentid().intValue()==item.getId()){
					MenuTree it = new MenuTree();
					it.setId(menuPrivalige.getId().intValue());
					it.setName(menuPrivalige.getName());
					it.setUrl(menuPrivalige.getUrl());
					it.setLeaf(true);
					children.add(it);
				}
				item.setChildren(children);
			}
		}
	}
}
