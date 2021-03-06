package simon.demo.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import simon.demo.core.bean.User;
import simon.demo.core.bean.UserExample.Criteria;
import simon.demo.core.bean.UserExample;
import simon.demo.core.dao.UserMapper;
import simon.demo.core.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public int countByExample(UserExample example) throws Exception {
        return userMapper.countByExample(example);
    }

    public int deleteByExample(UserExample example) throws Exception {
        return userMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Long id) throws Exception {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) throws Exception {
        return userMapper.insertSelective(record);//mapper.insertSelective
    }

    public int insertSelective(User record) throws Exception {
        return userMapper.insertSelective(record);
    }

    public List<User> selectByExample(UserExample example) throws Exception {
    	List<User> list1 = userMapper.selectByExample(example);
    	System.out.println(list1.size());
    	List<User> list2 = userMapper.selectByExample(example);
    	System.out.println(list2.size());
        return userMapper.selectByExample(example);
    }
//    public List<User> selectByExample(UserExample example) throws Exception {
//    	return userMapper.selectByExample(example);
//    }

    public Map<String,Object> findByPage(User record, int startPage, int rows) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        UserExample example=new UserExample();
        Criteria criteria = example.createCriteria();
        //具体条件查询请自行处理！！！
        map.put("total", userMapper.countAll());
        map.put("rows", userMapper.selectByExampleWithPage(example,startPage,rows));
        return map;
    }

    @Transactional
    public User selectByPrimaryKey(Long id){
    	User user2 = userMapper.selectByPrimaryKey(id);
        return user2;
    }

    public int updateByExampleSelective(User record, UserExample example) throws Exception {
        return userMapper.updateByExampleSelective(record,example);
    }

    public int updateByExample(User record, UserExample example) throws Exception {
        return userMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(User record) throws Exception {
    	int type = userMapper.updateByPrimaryKeySelective(record);
//    	Assert.isNull(record, "不能为空");
    	System.out.println(11);
        return type;
    }

    public int updateByPrimaryKey(User record) throws Exception {
        return userMapper.updateByPrimaryKey(record);
    }


	public String test() {
		// TODO Auto-generated method stub
		List<User> user = userMapper.selectUserById(2l);
		System.out.println(user.get(0));
		return null;
	}

	public int deleteByPrimaryKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int batchInsert(List records) throws Exception {
		return userMapper.batchInsert2(records);
	}
}