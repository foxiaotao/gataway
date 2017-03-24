package simon.demo.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import simon.demo.core.bean.ExchangeRate;
import simon.demo.core.service.POIService;
import simon.demo.core.util.ExcelUtil;

@Service
public class POIServiceImpl implements POIService {

	/**
	 * @功能: 处理表格excel数据到数据库
	 * @作者: 大贲·孙涛
	 * @时间: 2016-6-29 上午10:14:32
	 * @版本: v1.0
	 * @param inputStream
	 * @throws IOException
	 * @throws Exception
	 */
	public void actionExcel(InputStream inputStream) {
		//初始化表头映射，因为映射的标题是符合标题行目前不支持，所以直接使用顺序映射
		LinkedHashMap<String, String> pm = new LinkedHashMap<String, String>();
		pm.put("yearMonth", "月份");
		pm.put("multIplier", "中间价");
		pm.put("currency", "目标币种"); 
		//封装excel的数据
		//获取数据
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
//		int allNum = 0;
		int num = workbook.getNumberOfSheets();	
		List<ExchangeRate> rates = null;
		//迭代excel中的每一个sheet
		for(int i = 0; i < num; i++){
			HSSFSheet sheet = workbook.getSheetAt(i);
			//封装到bean
			ExcelUtil<ExchangeRate> test = new ExcelUtil<ExchangeRate>(pm, sheet, ExchangeRate.class);
			rates = test.getEntitiesHasNoHeader(1);//从第几行起 取数据
			if(rates.size()>0){
				Date dd = new Date(System.currentTimeMillis());
				for (ExchangeRate exchangeRate : rates) {
					//入库 insert
					System.out.println(exchangeRate);
				}
			}
		}
		
	}
}
