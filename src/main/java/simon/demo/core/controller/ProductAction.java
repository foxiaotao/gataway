package simon.demo.core.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import simon.demo.core.bean.Product;
import simon.demo.core.service.ProductService;

@Controller
@RequestMapping(value="/Product")
public class ProductAction {
	
	@RequestMapping(value="/index.do")
    public String index(String id) throws Exception {
    	return "product";
    }
	@RequestMapping(value="/index2.do")
	public String index2(String id) throws Exception {
		return "model";
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
        map.put("msg", "删除成功");
    	return map;
    }

    @RequestMapping(value="/insert.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insert(Product record) throws Exception {
    	Map<String,Object> map = new HashMap<String, Object>();
        productServiceImpl.insert(record);
    	map.put("success", "提示：添加成功！");
        map.put("msg", "添加成功");
    	return map;
    }

    @RequestMapping(value="/findByPage.do")
    @ResponseBody
    public Map<String,Object> findByPage(Product record, int rows, int page) throws Exception {
        int startPage=rows*(page-1);
        return productServiceImpl.findByPage(record,startPage,rows);
    }

    @RequestMapping(value="/update.do" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(Product record) throws Exception {
    	Map<String,Object> map = new HashMap<String, Object>();
        productServiceImpl.updateByPrimaryKeySelective(record);
        map.put("success", "提示：编辑成功！");
        map.put("msg", "编辑成功");
    	return map;
    }
}