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
import simon.demo.core.util.fastexcel.MapperCell;
import simon.demo.core.util.fastexcel.RandFFutrueBean;

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
		pm.put("ad_contract_id", "订单ID");
		pm.put("contract_name", "订单名称");
		pm.put("ad_cast_id", "投放ID"); 
		pm.put("cast_name", "订单名称"); 
		pm.put("ad_creative_id", "素材ID"); 
		pm.put("creative_name", "素材名称"); 
		pm.put("province_name", "省份"); 
		pm.put("city_name", "城市"); 
		pm.put("show1", "AD曝光1+UV"); 
		pm.put("show2", "AD曝光2+UV"); 
		pm.put("show3", "AD曝光3+UV"); 
		pm.put("show4", "AD曝光4+UV"); 
		pm.put("show5", "AD曝光5+UV"); 
		pm.put("show6", "AD曝光6+以上UV"); 
		pm.put("click_yt", "优土点击(次)"); 
		pm.put("click_admaster", "AD点击(次)"); 
		pm.put("click1", "AD点击1+UV"); 
		pm.put("click2", "AD点击2+UV"); 
		pm.put("click3", "AD点击3+UV"); 
		pm.put("click4", "AD点击4+UV"); 
		pm.put("click5", "AD点击5+UV"); 
		pm.put("click6", "AD点击6+以上UV"); 
		
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
		List<RandFFutrueBean> rates = null;
		//迭代excel中的每一个sheet
		for(int i = 0; i < num; i++){
			HSSFSheet sheet = workbook.getSheetAt(i);
			//封装到bean
			ExcelUtil<RandFFutrueBean> test = new ExcelUtil<RandFFutrueBean>(pm, sheet, RandFFutrueBean.class);
			long starttime = System.currentTimeMillis();
			rates = test.getEntitiesHasNoHeader(1);//从第几行起 取数据
			System.out.println("时间差异："+(System.currentTimeMillis()-starttime));
			if(rates.size()>0){
				Date dd = new Date(System.currentTimeMillis());
				for (RandFFutrueBean exchangeRate : rates) {
					//入库 insert
//					System.out.println(exchangeRate);
				}
			}
		}
		
	}
}
