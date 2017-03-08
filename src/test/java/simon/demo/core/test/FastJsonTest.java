package simon.demo.core.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.User;
import simon.demo.core.dao.UserMapper;
import simon.demo.core.service.UserService;
import simon.demo.core.service.impl.ProductServiceImpl;

public class FastJsonTest extends JunitSpringTest{

	@Autowired  
	ProductServiceImpl productServiceImpl;
	
	
	/**对象-json
	 * @throws Exception
	 */
	@Test
	public void obj2Json() throws Exception{
		List<Product> selectAll = productServiceImpl.selectAll();
		
		//属性为空时 怎么转
		selectAll.get(0).setAttr(null);
		System.out.println(JSON.toJSON(selectAll));
		//直接过滤掉了
		System.out.println(selectAll.size());
	}
	@Test
	public void obj2JsonDate() throws Exception{
		List<Product> selectAll = productServiceImpl.selectAll();
		
		//属性为空时 怎么转
		selectAll.get(0).setUpdateTime(new Date());
		System.out.println(JSON.toJSON(selectAll.get(0)));
		//{"attr":"2","id":"1","product":"1","status":"1","unit":"2","updateTime":1488942252298}
		
		//需要转指定时间格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		System.out.println(JSON.toJSONString(selectAll.get(0), SerializerFeature.WriteDateUseDateFormat));
		
		System.out.println(selectAll.size());
	}
	
	
	/**json-对象
	 * @throws Exception
	 */
	@Test
	public void json2Obj() throws Exception{
		
		//json2obj
		String json = "{\"attr\":\"2\",\"updateTime\":\"2017-03-08 12:01:03\",\"id\":\"1\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"}";
		String jsonArray = "[{\"attr\":\"2\",\"updateTime\":\"20170308120103\",\"id\":\"1\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"},{\"attr\":\"2\",\"id\":\"2\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"}]";
		System.out.println(json);
//		JSON.DEFFAULT_DATE_FORMAT = "yyyyMMddhh:MM:dd";
		Product product = JSON.parseObject(json,Product.class);
		
		List<Product> parseObject = JSON.parseArray(jsonArray,Product.class);
		
		//obj2json
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		System.out.println(JSON.toJSONString(parseObject, SerializerFeature.WriteDateUseDateFormat));
		
		
		
		System.out.println(0);
//		result:
//		{"attr":"2","updateTime":"2017-03-08 12:01:03","id":"1","product":"1","status":"1","unit":"2"}
//		[{"attr":"2","id":"1","product":"1","status":"1","unit":"2","updateTime":"2017-03-08"},{"attr":"2","id":"2","product":"1","status":"1","unit":"2"}]
	}
	
}
