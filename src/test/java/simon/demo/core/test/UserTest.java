package simon.demo.core.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.User;
import simon.demo.core.dao.UserMapper;
import simon.demo.core.service.UserService;
import simon.demo.core.service.impl.ProductServiceImpl;

public class UserTest extends JunitSpringTest{

//	@Autowired
//    UserService userServiceImpl;
//	@Autowired  
//    @Qualifier("userMapper") 
//    private UserMapper userMapper;
	
	@Autowired  
	ProductServiceImpl productServiceImpl;
	
	@Test
	public void indextest(){
//		User user = userMapper.selectByPrimaryKey(Long.valueOf(1));
//		System.out.println(user);
	}
	
	@Test
	public void testProduct() throws Exception{
		List<Product> selectAll = productServiceImpl.selectAll();
		System.out.println(selectAll.size());
	}
	
}
