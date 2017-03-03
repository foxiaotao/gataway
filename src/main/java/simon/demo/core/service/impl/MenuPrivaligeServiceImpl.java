package simon.demo.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simon.demo.core.bean.MenuPrivalige;
import simon.demo.core.bean.MenuPrivaligeExample.Criteria;
import simon.demo.core.bean.MenuPrivaligeExample;
import simon.demo.core.bean.Role;
import simon.demo.core.bean.User;
import simon.demo.core.dao.MenuPrivaligeMapper;
import simon.demo.core.service.MenuPrivaligeService;
import simon.demo.core.shiro.ShiroUser;

@Service
public class MenuPrivaligeServiceImpl implements MenuPrivaligeService {
    @Autowired
    MenuPrivaligeMapper menuPrivaligeMapper;

    public int countByExample(MenuPrivaligeExample example) throws Exception {
        return menuPrivaligeMapper.countByExample(example);
    }

    public int deleteByExample(MenuPrivaligeExample example) throws Exception {
        return menuPrivaligeMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Long id) throws Exception {
        return menuPrivaligeMapper.deleteByPrimaryKey(id);
    }

    public int insert(MenuPrivalige record) throws Exception {
        return menuPrivaligeMapper.insertSelective(record);//mapper.insertSelective
    }

    public int insertSelective(MenuPrivalige record) throws Exception {
        return menuPrivaligeMapper.insertSelective(record);
    }

    public List<MenuPrivalige> selectByExample(MenuPrivaligeExample example) throws Exception {
        return menuPrivaligeMapper.selectByExample(example);
    }
    
    public Map<String,Object> findByPage(MenuPrivalige record, int startPage, int rows) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        MenuPrivaligeExample example=new MenuPrivaligeExample();
        Criteria criteria = example.createCriteria();
        //����������ѯ�����д�������
        map.put("total", menuPrivaligeMapper.countAll());
        map.put("rows", menuPrivaligeMapper.selectByExampleWithPage(example,startPage,rows));
        return map;
    }

    public MenuPrivalige selectByPrimaryKey(Long id) throws Exception {
        return menuPrivaligeMapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(MenuPrivalige record, MenuPrivaligeExample example) throws Exception {
        return menuPrivaligeMapper.updateByExampleSelective(record,example);
    }

    public int updateByExample(MenuPrivalige record, MenuPrivaligeExample example) throws Exception {
        return menuPrivaligeMapper.updateByExample(record,example);
    }

    public int updateByPrimaryKeySelective(MenuPrivalige record) throws Exception {
        return menuPrivaligeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MenuPrivalige record) throws Exception {
        return menuPrivaligeMapper.updateByPrimaryKey(record);
    }

	public int batchInsert(List records) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
    public List<MenuPrivalige> selectAll() throws Exception {
    	return menuPrivaligeMapper.selectAll();
    }

	public List<MenuPrivalige> selectAllMenu(ShiroUser user) {
		return menuPrivaligeMapper.selectAllMenu(user);
	}
	
}