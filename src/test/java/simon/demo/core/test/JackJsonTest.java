package simon.demo.core.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import simon.demo.core.bean.Product;
import simon.demo.core.service.impl.ProductServiceImpl;
import simon.demo.core.util.JackJsonUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackJsonTest extends JunitSpringTest{
	private static ObjectMapper jackjsonMapper = new ObjectMapper();
	@Autowired  
	ProductServiceImpl productServiceImpl;
	
	
	/**对象-json
	 * @throws Exception
	 */
	@Test
	public void obj2Json() throws Exception{
		List<Product> selectAll = productServiceImpl.selectAll();
		String jackjson = JackJsonUtil.objectTOJackjson(selectAll);
		System.out.println(jackjson);
		
	}
	/** date
	 * @throws Exception
	 */
	@Test
	public void obj2Json2() throws Exception{
		List<Product> selectAll = productServiceImpl.selectAll();
		
		selectAll.get(0).setUpdateTime(new Date());
		System.out.println(jackjsonMapper.writeValueAsString(selectAll));
		//"updateTime":1488945381232
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		jackjsonMapper.setDateFormat(df);
		System.out.println(jackjsonMapper.writeValueAsString(selectAll));
		//"updateTime":"2017-03-08"
		
	}
	
	
	/**json-对象
	 * @throws Exception
	 */
	@Test
	public void json2Obj() throws Exception{
		//json2Obj
		String json = "{\"attr\":\"2\",\"updateTime\":\"2017-03-08 12:01:03\",\"id\":\"1\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"}";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		jackjsonMapper.setDateFormat(df);
		jackjsonMapper.readValue(json, Product.class);
		System.out.println(json);
		System.out.println(0);
		
		
		
	}
	/**json-对象 list
	 * @throws Exception
	 */
	@Test
	public void json2List() throws Exception{
		
		//json2Obj
		
		String json = "[{\"attr\":\"2\",\"updateTime\":\"2017-03-08 12:01:03\",\"id\":\"1\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"},{\"attr\":\"2\",\"id\":\"2\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"}]";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		jackjsonMapper.setDateFormat(df);
		JavaType javaType = jackjsonMapper.getTypeFactory().constructParametricType(List.class, Product.class);
		List<Product> readValue = jackjsonMapper.readValue(json, javaType);
		System.out.println(json);
		
		//obj2Json
		System.out.println(jackjsonMapper.writeValueAsString(readValue));
		//"updateTime":1488945381232
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		jackjsonMapper.setDateFormat(df2);
		System.out.println(jackjsonMapper.writeValueAsString(readValue));
		//"updateTime":"2017-03-08"
		
		System.out.println(0);
//		result:
//			[{"attr":"2","updateTime":"2017-03-08 12:01:03","id":"1","product":"1","status":"1","unit":"2"},{"attr":"2","id":"2","product":"1","status":"1","unit":"2"}]
//			[{"id":"1","product":"1","unit":"2","attr":"2","status":"1","updateTime":"2017-03-08 12:01:03"},{"id":"2","product":"1","unit":"2","attr":"2","status":"1","updateTime":null}]
//			[{"id":"1","product":"1","unit":"2","attr":"2","status":"1","updateTime":"2017-03-08"},{"id":"2","product":"1","unit":"2","attr":"2","status":"1","updateTime":null}]
	}
	/**json-对象 list  json多属性
	 * @throws Exception
	 */
	@Test
	public void json2List2() throws Exception{
		String json = "[{\"attr\":\"2\",\"updateTime\":\"2017-03-08 12:01:03\",\"id\":\"1\",\"id2\":\"1\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"},{\"attr\":\"2\",\"id\":\"2\",\"product\":\"1\",\"status\":\"1\",\"unit\":\"2\"}]";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jackjsonMapper.setDateFormat(df).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略json多余的属性;
		JavaType javaType = jackjsonMapper.getTypeFactory().constructParametricType(List.class, Product.class);
		List<Product> readValue = jackjsonMapper.readValue(json, javaType);
		System.out.println(json);
		System.out.println(0);
	}
	
}
