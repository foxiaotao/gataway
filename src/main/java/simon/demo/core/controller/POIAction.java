package simon.demo.core.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.ReturnBean;
import simon.demo.core.service.POIService;
import simon.demo.core.service.ProductService;
import simon.demo.core.util.ExcelByModelUtil;
import simon.demo.core.util.excel.ADDRFHistoryExcelExportor;
import simon.demo.core.util.excel.AbstractExcelExportor;

import com.alibaba.fastjson.JSONArray;


@Controller
@RequestMapping(value="/poi")
public class POIAction {
	
	Logger logger = Logger.getLogger(POIAction.class);
	
	@Autowired  
	HttpServletRequest request; //这里可以获取到request
	
	
	@Autowired
    ProductService productServiceImpl;

	@Autowired
    POIService poiServiceImpl;
	
	@RequestMapping(value="/inp.do")
    public String inportIndex() throws Exception {
        return "poi/inportXls";
    }
	
	
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
    
    @RequestMapping(value="exportModelMap.do")
    public ResponseEntity<byte[]> exportModelMap(){
    	ServletRequest request = ((WebSubject)SecurityUtils.getSubject()).getServletRequest();   
    	HttpSession httpSession = ((HttpServletRequest)request).getSession();   
    	logger.debug("httpSession.getServletContext():"+httpSession.getServletContext());  
    	String basePath = httpSession.getServletContext().getRealPath("/WEB-INF");
    	String filePath = basePath+"/excelModel/ZZSScottareModel.xls";
    	
    	//data
    	//封装整个excel所需数据,key为sheet名字,String
		Map<String,Map<Integer,Map<Integer,String>>> fieldData = new HashMap<String, Map<Integer,Map<Integer,String>>>();
		//封装一个sheet的数据,key为数据row行序号,int
		Map<Integer,Map<Integer,String>> rowMap = null;
		//封装一行的数据,key为cell列序号,int
		//Map<Integer,String> cellMap = null;
		
		//sheet1 部分
		rowMap = new HashMap<Integer, Map<Integer,String>>();	//实例化sheet1-rowMap
		//sheet1
		//rowMap:key=0,value:第0行的数据
		rowMap.put(0, ExcelByModelUtil.fillCellMapData(new Integer[]{0},new String[]{"附件1 值 税 纳 增  税  申 报 表2"}));
		//rowMap:key=3,value:第3行的数据
		rowMap.put(3, ExcelByModelUtil.fillCellMapData(new Integer[]{0,17,31},new String[]{"税款所属时间：自2016 ","填表日期： 2016 年 12  月  30  日","金额单位：10000.00元 至角分"}));
		//rowMap:key=4,value:第3行的数据
		rowMap.put(4, ExcelByModelUtil.fillCellMapData(new Integer[]{0,5,27},new String[]{"税款所属时间：自2015 ","填表日期： 2015 年 12  月  30  日","金额单位：10000.01元 至角分"}));
		
		fieldData.put("首页", rowMap);//sheet1数据加入map中
		
		//sheet2部分
		rowMap = new HashMap<Integer, Map<Integer,String>>();	//实例化sheet2-rowMap
		//rowMap:key=3,value:第3行的数据
		rowMap.put(3, ExcelByModelUtil.fillCellMapData(new Integer[]{0,16,31},new String[]{"税款所属时间：自2015 ","填表日期： 2015 年 12  月  30  日","金额单位：10000.01元 至角分"}));
		//rowMap:key=4,value:第3行的数据
		rowMap.put(4, ExcelByModelUtil.fillCellMapData(new Integer[]{0,5,27},new String[]{"纳税人识别号2","业务系统的标识2","所属行业：2"}));
		//sheet2
		fieldData.put("Sheet2", rowMap);
    	//data
    	
    	try {
			File f = new File(filePath);
			if(!f.exists()){
				logger.error("文件路径不存在");
				return null;
			}
		} catch (Exception e1) {
			logger.error("文件路径不存在");
			return null;
		}
    	ExcelByModelUtil excel = new ExcelByModelUtil();
    	excel.setModelPath(filePath).writeDataByMap(fieldData);
    	
    	HttpHeaders header = new HttpHeaders();
    	try {
			header.setContentDispositionFormData("attachment", new String("增值税.xls".getBytes("UTF-8"),"ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
    	header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	return new ResponseEntity<byte[]>(excel.toByteArray(),header,HttpStatus.CREATED);
    }
    @RequestMapping(value="exportModelList.do")
    public ResponseEntity<byte[]> exportModelList(){
    	ServletRequest request = ((WebSubject)SecurityUtils.getSubject()).getServletRequest();   
    	HttpSession httpSession = ((HttpServletRequest)request).getSession();   
    	logger.debug("httpSession.getServletContext():"+httpSession.getServletContext());  
    	String basePath = httpSession.getServletContext().getRealPath("/WEB-INF");
    	String filePath = basePath+"/excelModel/ZZSScottareModelLsit.xls";
    	
    	//data
    	//封装整个excel所需数据,key为sheet名字,String
    	Map<String,List<String>> fieldData = new HashMap<String, List<String>>();
    	//封装一个sheet的数据
    	List<String> cellData = new ArrayList<String>();
    	cellData.add("附件1 值 税 纳 增  税  申 报 表3");
    	cellData.add("税款所属时间：自2016 ");
    	cellData.add("填表日期： 2016 年 12  月  30  日");
    	cellData.add("金额单位：10000.00元 至角分");
    	cellData.add("税款所属时间：自2015 ");
    	cellData.add("填表日期： 2015 年 12  月  30  日");
    	cellData.add("金额单位：10000.01元 至角分");
    	fieldData.put("首页", cellData);
    	
    	//sheet2
    	cellData = new ArrayList<String>();
    	cellData.add("sheet2附件1 值 税 纳 增  税  申 报 表3");
    	cellData.add("sheet2税款所属时间：自2016 ");
    	cellData.add("sheet2填表日期： 2016 年 12  月  30  日");
    	cellData.add("sheet2金额单位：10000.00元 至角分");
    	cellData.add("sheet2税款所属时间：自2015 ");
    	cellData.add("sheet2填表日期： 2015 年 12  月  30  日");
    	cellData.add("sheet2金额单位：10000.01元 至角分");
    	fieldData.put("Sheet2", cellData);
    	//data
    	
    	try {
    		File f = new File(filePath);
    		if(!f.exists()){
    			logger.error("文件路径不存在");
    			return null;
    		}
    	} catch (Exception e1) {
    		logger.error("文件路径不存在");
    		return null;
    	}
    	ExcelByModelUtil excel = new ExcelByModelUtil();
    	excel.setModelPath(filePath).setRowCellCount(54, 40).writeDataByList(fieldData);
    	
    	HttpHeaders header = new HttpHeaders();
    	try {
    		header.setContentDispositionFormData("attachment", new String("增值税list.xls".getBytes("UTF-8"),"ISO8859-1"));
    	} catch (UnsupportedEncodingException e) {
    		logger.error(e.getMessage());
    	}
    	header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	return new ResponseEntity<byte[]>(excel.toByteArray(),header,HttpStatus.CREATED);
    }
    
    /**非 shiro环境
     * @param resquest
     * @param response
     * @return
     */
    @RequestMapping(value="inportXls.do")
    public ResponseEntity inportXls1(HttpServletRequest resquest,HttpServletResponse response){
    	try {  
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) resquest;  
		    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		    String xlsName;
		    String[] name;
		    for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {   
		    	// 上传文件 
		    	MultipartFile mf = entity.getValue();  
		    	if(mf.getSize() > 4097152){
		    		//(MaxUploadSizeExceededException e) {
		    		return new ResponseEntity(new ReturnBean(false,"上传失败,文件大小超过2M限制"), HttpStatus.OK);
		    	}
		    	xlsName = mf.getOriginalFilename();
		    	//文件不能为空
		    	Assert.notNull(xlsName);
		    	
	    		name = xlsName.split("\\.");
    			//验证文件格式
    			if("xls".equals(name[1])){
    				//处理文件
    				poiServiceImpl.actionExcel(mf.getInputStream());
    				return new ResponseEntity(new ReturnBean(true,"上传成功"), HttpStatus.OK);
    			}else{
    				return new ResponseEntity("上传失败,文件大小超过2M限制", HttpStatus.OK);
    			}
		  	}  
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity(new ReturnBean(false,"上传成功"), HttpStatus.OK);
		} 
    	
		return null;
    }
    /**shiro环境
     * @param resquest
     * @param response
     * @return
     */
    @RequestMapping(value="inportXlsShiro.do")
    public ResponseEntity inportXlsShiro(ShiroHttpServletRequest shiroRequest,HttpServletResponse response){
    	try {  
    	    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();  
    	    MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart((HttpServletRequest) shiroRequest.getRequest());  
    		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
    		String xlsName;
    		String[] name;
    		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {   
    			// 上传文件 
    			MultipartFile mf = entity.getValue();  
    			if(mf.getSize() > 4097152){
    				//(MaxUploadSizeExceededException e) {
    				return new ResponseEntity(new ReturnBean(false,"上传失败,文件大小超过2M限制"), HttpStatus.OK);
    			}
    			xlsName = mf.getOriginalFilename();
    			//文件不能为空
    			Assert.notNull(xlsName);
    			
    			name = xlsName.split("\\.");
    			//验证文件格式
    			if("xls".equals(name[1])){
    				//处理文件
    				poiServiceImpl.actionExcel(mf.getInputStream());
    				return new ResponseEntity(new ReturnBean(true,"上传成功"), HttpStatus.OK);
    			}else{
    				return new ResponseEntity("上传失败,文件大小超过2M限制", HttpStatus.OK);
    			}
    		}  
    	} catch (IOException e) {
    		e.printStackTrace();
    		return new ResponseEntity(new ReturnBean(false,"上传成功"), HttpStatus.OK);
    	} 
    	
    	return null;
    }
}