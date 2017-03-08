package simon.demo.core.test;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.RestResponse;
import simon.demo.core.bean.RestResponse2;
import simon.demo.core.service.impl.ProductServiceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonTest {//extends JunitSpringTest{

	@Autowired  
	ProductServiceImpl productServiceImpl;
	
	
	
	/**json-对象
	 * @throws Exception
	 */
	@Test
	public void json() throws Exception{
		String json = "{\"msg\":\"true\",\"result\":[{\"attr\":\"0.0\",\"id\":\"100.0\",\"product\":\"0\",\"status\":\"297.086\",\"updateTime\":\"2017-01-03\"},{\"attr\":\"0.0\",\"id\":\"100.0\",\"product\":\"0\",\"status\":\"162.678\"}],\"total\":60,\"success\":true}";
		RestResponse obj = new GsonBuilder().create().fromJson(json, RestResponse.class);
		System.out.println(obj);
	}
	/** 可能  json对象 有的key的value为空
	 * 	list对象设置Object 类型，经过gson转换之后java类型为 LinkedTreeMap，成功之后再通过gson 转成list对象
	 * @throws Exception
	 */
	@Test
	public void json2() throws Exception{
		String json = "{\"msg\":\"true\",\"result\":[{\"attr\":\"0.0\",\"id\":\"100.0\",\"product\":\"0\",\"status\":\"297.086\",\"updateTime\":\"20170103\"},{\"attr\":\"0.0\",\"id\":\"100.0\",\"product\":\"0\",\"status\":\"162.678\"}],\"total\":60,\"success\":true}";
		RestResponse2 obj = new GsonBuilder().create().fromJson(json, RestResponse2.class);
		if("true".equals(obj.getSuccess().toString()) && (double)obj.getTotal()>0){
			List<Product> list =  new GsonBuilder().setDateFormat("yyyyMMdd").create().fromJson(obj.getResult().toString(), new TypeToken<List<Product>>(){}.getType());
			System.out.println(list);
			if(list!=null && list.size()>0){
				System.out.println(list.get(0).getUpdateTime());
				System.out.println(list.get(0).getAttr());
			}
		}
		System.out.println(obj);
		
		//对象转json
		String obj2Json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(obj);
		System.out.println(json);
		System.out.println(obj2Json);
	}
	/** 可能  json对象 有的key的value为空
	 * 	list对象设置Object 类型，经过gson转换之后java类型为 LinkedTreeMap，成功之后再通过gson 转成list对象
	 * @throws Exception
	 */
	@Test
	public void json3() throws Exception{
		String json = "{\"msg\":\"\",\"result\":\"\",\"total\":\"1\",\"success\":\"true\"}";
		RestResponse2 obj = new GsonBuilder().create().fromJson(json, RestResponse2.class);
		if("true".equals(obj.getSuccess().toString()) && Integer.parseInt(obj.getTotal().toString())>0){
			List<Product> list =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(obj.getResult().toString(), new TypeToken<List<Product>>(){}.getType());
			System.out.println(list);
			if(list!=null && list.size()>0){
				System.out.println(list.get(0).getUpdateTime());
				System.out.println(list.get(0).getAttr());
			}
		}
		
		System.out.println(obj);
		
		
	}
	
}
