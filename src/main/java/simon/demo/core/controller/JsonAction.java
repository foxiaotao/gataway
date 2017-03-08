package simon.demo.core.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.User;
import simon.demo.core.service.ProductService;
import simon.demo.core.service.UserService;

@Controller
@RequestMapping(value="/json")
public class JsonAction {
	
	Logger logger = Logger.getLogger(JsonAction.class);
	
	@Autowired
    ProductService productServiceImpl;

	//###################################  对象转json  ############################################
	
	@RequestMapping(value="jsonToObj.do")
	public ResponseEntity objectToJson(){
		List<Product> list = productServiceImpl.selectAll();
		// com.alibaba.fastjson.JSONArray
		System.out.println(JSONArray.toJSON(list));
		return new ResponseEntity<>(JSONArray.toJSON(list),HttpStatus.OK);
	}
	
	@RequestMapping(value="jsonToObj2.do")
	public ResponseEntity objectToJson2(){
		List<Product> list = productServiceImpl.selectAll();
		// com.alibaba.fastjson.JSONArray
		System.out.println(JSONArray.toJSON(list));
		
		return new ResponseEntity<>(JSONArray.toJSON(list),HttpStatus.OK);
	}
	
	
	//###################################  对象转json  ############################################
}