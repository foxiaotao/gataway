package simon.demo.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simon.demo.core.bean.Role;
import simon.demo.core.bean.RoleExample.Criteria;
import simon.demo.core.bean.RoleExample;
import simon.demo.core.dao.RoleMapper;
import simon.demo.core.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    public int countByExample(RoleExample example) throws Exception {
        return roleMapper.countByExample(example);
    }

    public int deleteByExample(RoleExample example) throws Exception {
        return roleMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Long rid) throws Exception {
        return roleMapper.deleteByPrimaryKey(rid);
    }

    public int insert(Role record) throws Exception {
        return roleMapper.insertSelective(record);//mapper.insertSelective
    }

    public int insertSelective(Role record) throws Exception {
        return roleMapper.insertSelective(record);
    }

    public List<Role> selectByExample(RoleExample example) throws Exception {
        return roleMapper.selectByExample(example);
    }
    
    public List<Role> selectAll() throws Exception {
    	return roleMapper.selectAll();
    }

    public Map<String,Object> findByPage(Role record, int startPage, int rows) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        RoleExample example=new RoleExample();
        Criteria criteria = example.createCriteria();
        //����������ѯ�����д�������
        map.put("total", roleMapper.countAll());
        map.put("rows", roleMapper.selectByExampleWithPage(example,startPage,rows));
        return map;
    }

    public Role selectByPrimaryKey(Long rid) throws Exception {
        return roleMapper.selectByPrimaryKey(rid);
    }

    public int updateByExampleSelective(Role record, RoleExample example) throws Exception {
        return roleMapper.updateByExampleSelective(record,example);
    }

    public int updateByExample(Role record, RoleExample example) throws Exception {
        return roleMapper.updateByExample(record,example);
    }

    public int updateByPrimaryKeySelective(Role record) throws Exception {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Role record) throws Exception {
        return roleMapper.updateByPrimaryKey(record);
    }

	public int batchInsert(List records) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}