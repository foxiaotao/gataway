package simon.demo.core.test;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
	
//	@Autowired  
//	HttpServletRequest request; //这里可以获取到request
	
	@Autowired  
	ProductServiceImpl productServiceImpl;
	
	@Test
	public void indextest(){
//		User user = userMapper.selectByPrimaryKey(Long.valueOf(1));
//		System.out.println(user);
	}
	
	@Test
	public void testProduct() throws Exception{
//		Map<String, Object> findByPage = productServiceImpl.findByPage(null, 1, 10);
		int a =0;
	}
	
}
