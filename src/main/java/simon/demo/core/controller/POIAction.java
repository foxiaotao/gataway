package simon.demo.core.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.User;
import simon.demo.core.service.ProductService;
import simon.demo.core.service.UserService;
import simon.demo.core.util.excel.ADDRFHistoryExcelExportor;
import simon.demo.core.util.excel.AbstractExcelExportor;

@Controller
@RequestMapping(value="/poi")
public class POIAction {
	
	Logger logger = Logger.getLogger(POIAction.class);
	
	@Autowired
    ProductService productServiceImpl;

	@RequestMapping(value="/exportMap.do")
	@ResponseBody
    public Map<String,Object> exportMap() throws Exception {
    	logger.error("【导出log】-responseBody-map");
        List<Product> selectAll = productServiceImpl.selectAll();
        Map<String,Object> map = new HashMap<String,Object>(1);
        map.put("result", selectAll);
        return map;
    }
	
	
	
    @RequestMapping(value="/exportRe.do")
    public ResponseEntity exportRe() throws Exception {
    	logger.error("【导出log】");
        List<Product> selectAll = productServiceImpl.selectAll();
        return new ResponseEntity<>(JSONArray.toJSON(selectAll), HttpStatus.OK);
    }


    @RequestMapping(value="/export.do")
    public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	logger.error("【导出log】");
    	OutputStream os = null;  
    	Workbook wb = null;  
    	List<Product> selectAll;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("产品sheet");
			//设置title
//    		Row rowTitle = sheet.createRow(0);
//    		String titleMap[] = {"id号","商家名称","价格","属性","状态"};
//    		String fieldMap[] = {"id","product","unit","attr","status"};
			
			selectAll = productServiceImpl.selectAll();
			
			//Row
			Row row = null;
			Cell cellId = null;
			Cell cellProduct = null;
			Cell cellUnit = null;
			Cell cellAttr = null;
			Cell cellStatus = null;
			for (int i = 0; i < selectAll.size(); i++) {
				row = sheet.createRow(i);
				//id号
				cellId = row.createCell(0);
				cellId.setCellValue(selectAll.get(i).getId());
				//商家名称
				cellProduct = row.createCell(1);
				cellProduct.setCellValue(selectAll.get(i).getProduct());
				//价格
				cellUnit = row.createCell(2);
				cellUnit.setCellValue(selectAll.get(i).getUnit());
				//属性
				cellAttr = row.createCell(3);
				cellAttr.setCellValue(selectAll.get(i).getAttr());
				//状态
				cellStatus = row.createCell(4);
				cellStatus.setCellValue(selectAll.get(i).getStatus());
			}
			
			//设置response
			String fileName="产品管理.xls";  
            response.setContentType("application/vnd.ms-excel");  
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));  
            os = response.getOutputStream();  
            wb.write(os); 
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally{
			os.flush();  
            try {
				os.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}  
		}
        return ;
    }
    
    
	@RequestMapping(value = "exportA.do")
    public ResponseEntity<byte[]> exportAdMasterFreq(HttpServletRequest request, HttpServletResponse response){
    	//配置：org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
    	logger.error("【导出log】 by ResponseEntity");
    	ByteArrayOutputStream out = null;
    	Workbook wb = null;  
    	List<Product> selectAll;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("产品sheet");
			//设置title
//    		Row rowTitle = sheet.createRow(0);
//    		String titleMap[] = {"id号","商家名称","价格","属性","状态"};
//    		String fieldMap[] = {"id","product","unit","attr","status"};
			
			selectAll = productServiceImpl.selectAll();
			
			//Row
			Row row = null;
			Cell cellId = null;
			Cell cellProduct = null;
			Cell cellUnit = null;
			Cell cellAttr = null;
			Cell cellStatus = null;
			for (int i = 0; i < selectAll.size(); i++) {
				row = sheet.createRow(i);
				//id号
				cellId = row.createCell(0);
				cellId.setCellValue(selectAll.get(i).getId());
				//商家名称
				cellProduct = row.createCell(1);
				cellProduct.setCellValue(selectAll.get(i).getProduct());
				//价格
				cellUnit = row.createCell(2);
				cellUnit.setCellValue(selectAll.get(i).getUnit());
				//属性
				cellAttr = row.createCell(3);
				cellAttr.setCellValue(selectAll.get(i).getAttr());
				//状态
				cellStatus = row.createCell(4);
				cellStatus.setCellValue(selectAll.get(i).getStatus());
			}
			
			String fileName="产品管理2.xls";  
			HttpHeaders header = new HttpHeaders();
			try {
				header.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			//二进制数据
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			out = new ByteArrayOutputStream();
			wb.write(out);
			return new ResponseEntity<byte[]>(out.toByteArray(),header,HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
    }
    @RequestMapping(value = "exportGen.do")
    public ResponseEntity<byte[]> exportGenrater(HttpServletRequest request, HttpServletResponse response){
    	//配置：org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
    	logger.error("【导出log】 by exportGen");
    	Workbook wb = null;  
    	List<Product> selectAll;
    	try {
    		wb = new HSSFWorkbook();
    		Sheet sheet = wb.createSheet("产品sheet");
    		//设置title
//    		Row rowTitle = sheet.createRow(0);
    		String titleMap[] = {"id号","商家名称","价格","属性","状态"};
    		String fieldMap[] = {"id","product","unit","attr","status"};
    		
    		selectAll = productServiceImpl.selectAll();
    		
    		AbstractExcelExportor exporter = new ADDRFHistoryExcelExportor();
    		exporter.createSheet("产品").createHeader(titleMap).setColumnNames(fieldMap).createContent(selectAll);
    		
    		String fileName="产品管理2.xls";  
    		HttpHeaders header = new HttpHeaders();
    		try {
    			header.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
    		} catch (Exception e) {
    			logger.error(e.getMessage());
    		}
    		//二进制数据
    		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    		return new ResponseEntity<byte[]>(exporter.toByteArray(),header,HttpStatus.CREATED);
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    	}
    }
}