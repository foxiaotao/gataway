package simon.demo.core.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import simon.demo.core.bean.Product;
import simon.demo.core.service.ProductService;

@Controller
@RequestMapping(value="/tProduct")
public class ProductActionTest {
	
	@RequestMapping(value="/index.do")
    public String index(String id) throws Exception {
    	return "product/Product";
    }
	@RequestMapping(value="/model.do")
	public String index2(String id) throws Exception {
		return "product/taxType";
	}
	@RequestMapping(value="/model2.do")
	public String index22(String id) throws Exception {
		return "product/taxType2";
	}
	@RequestMapping(value="/excelLook.do")
	public String excelLook(String id) throws Exception {
		return "file/file";
	}
	
	
	@RequestMapping(value="/fileLook.do")
	public void excel(String id,HttpServletResponse response) throws Exception {
		 response.reset();  
		 response.setContentType("application/vnd.ms-excel");  
		 InputStream ips = new FileInputStream("/Users/simon/Desktop/1111.xls");  //<---你的excel文件  
		 OutputStream ops = response.getOutputStream();  
		 int data = -1;  
		 while((data = ips.read()) != -1) {  
		   
		 ops.write(data);  
		 } 
	}
	
    @Autowired
    ProductService productServiceImpl;

    @RequestMapping(value="/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(String ids) throws Exception {
    	Map<String,Object> map = new HashMap<String, Object>();
    	//批量删除  先。。
    	String idsArr[] = ids.split(",");
    	for (int i = 0; i < idsArr.length; i++) {
    		productServiceImpl.deleteByPrimaryKey(Integer.valueOf(idsArr[i]));
		}
        map.put("success", "提示：删除成功！");
    	return map;
    }

    @RequestMapping(value="/insert.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insert(Product record) throws Exception {
    	Map<String,Object> map = new HashMap<String, Object>();
        productServiceImpl.insert(record);
    	map.put("success", "提示：添加成功！");
    	return map;
    }

//    @RequestMapping(value="/findByPage.do")
//    @ResponseBody
//    public Map<String,Object> findByPage(Product record, int rows, int page) throws Exception {
//        int startPage=rows*(page-1);
////        return productServiceImpl.findByPage(record,startPage,rows);
//    }
//    @RequestMapping(value="/findDetail.do")
//    @ResponseBody
//    public Map<String,Object> findDetail(Product record, int rows, int page) throws Exception {
//    	int startPage=rows*(page-1);
//    	Map<String, Object> findByPage = productServiceImpl.findByPage(record,startPage,rows);
//    	return findByPage;
//    }

    @RequestMapping(value="/update.do" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(Product record) throws Exception {
    	Map<String,Object> map = new HashMap<String, Object>();
        productServiceImpl.updateByPrimaryKeySelective(record);
        map.put("success", "提示：编辑成功！");
    	return map;
    }
}