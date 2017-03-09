/**
 * 系统数据导出Excel 生成器
 * @version 1.0
 */
package simon.demo.core.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;


/**
 * 导出excel 增值税交税申报表
 * @作者: 大贲.孙涛
 * @日期: 2016-6-2 上午11:00:27
 * @版本: v1.0
 */
public class ExcelByModelUtil {
	Logger logger = Logger.getLogger(this.getClass());
	
	protected HSSFWorkbook workBook;
	protected String modelPath = ""; //Excel模板路径

	/**第一层key(String):sheet名称,value(Map<Integer,Map<Integer,String>>):该sheet所有数据
	 * 第二层key(Integer):编辑行的行号,value(Map<Integer,String>):该行所有列所需数据
	 * 第三层key(Integer):一行中编辑列的序号,value(String):cell的值
	 */
	protected Map<String,Map<Integer,Map<Integer,String>>> fieldData = null; //excel数据内容,一个sheet的内容,外层map行数据,内层map,一行的每一列数据,key为列的序号

	
	//list 形式
	protected Map<String,List<String>> sheetData;
	private int allRowCount = 0;
	private int allCellCount = 0;
	
	/**设置模板路径
	 * @param modelPath
	 * @return
	 */
	public ExcelByModelUtil setModelPath(String modelPath){
		Assert.notNull(modelPath, "路径不能为空!");
		this.modelPath = modelPath;
		return this;
	}
	
	/**
	 * @param rowCount  总行数
	 * @param cellCount  总列数
	 * @return
	 */
	public ExcelByModelUtil setRowCellCount(int rowCount,int cellCount){
		this.allRowCount = rowCount;
		this.allCellCount = cellCount;
		return this;
	}
	

	/**
	 * 根据map 写数据，map<sheet,map<row,map<cell,value>>>
	 * @return HSSFWorkbook
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public ExcelByModelUtil writeDataByMap(Map<String,Map<Integer,Map<Integer,String>>> fieldData) {
		//拿到模板工作簿(模板只能xls文件,不能是xlsx文件,如果要支持office2007以上需要poi-ooxxml.jar包,使用XSSFWorkbook类)
		
		this.fieldData = fieldData;
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(modelPath);
			workBook = new HSSFWorkbook(fis);
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			String sheetName = null;//sheet的名称
			int rowIndex = 0;//编辑行号
			int cellIndex = 0;//编辑列号
			String cellValue = null;//编辑列的值
			//写入数据
			for (Map.Entry<String, Map<Integer,Map<Integer,String>>> sheetMap : fieldData.entrySet()) {
				//sheet
				sheetName = sheetMap.getKey();
				sheet = workBook.getSheet(sheetName);//得到指定sheet
				if(sheet!=null){
					for (Map.Entry<Integer, Map<Integer,String>> rowMap : sheetMap.getValue().entrySet()) {
						//得到行对象
						rowIndex = rowMap.getKey();
						row = sheet.getRow(rowIndex);
						if(row==null){
							//如果这个不存在,说明这行是空对象,那么创建对象
							row = sheet.createRow(rowIndex);
						}
						for (Map.Entry<Integer, String> cellMap : rowMap.getValue().entrySet()) {
							cellIndex = cellMap.getKey();//单元格列序号
							cellValue = cellMap.getValue();//单元格值
							//获取单元格
							cell = row.getCell(cellIndex);
							if(cell==null){
								//如果获取为空,说明这列是空对象,那么创建对象
								//log.error("excel POI操作" + sheetName + "第" + rowIndex + "行,第" + cellIndex + "列为空单元格,传入参数错误");
								cell = row.createCell(cellIndex);
							}
							cell.setCellValue(cellValue);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return this;
	}
	/**
	 * 替换模板中的#_#  顺序一行一行顺序替换
	 * @return HSSFWorkbook
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public ExcelByModelUtil writeDataByList(Map<String,List<String>> mapdata) {
		//拿到模板工作簿(模板只能xls文件,不能是xlsx文件,如果要支持office2007以上需要poi-ooxxml.jar包,使用XSSFWorkbook类)
		
		this.sheetData = mapdata;
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(modelPath);
			workBook = new HSSFWorkbook(fis);
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			String sheetName = null;//sheet的名称
			String cellValue = null;//编辑列的值
			//写入数据
			for (Map.Entry<String, List<String>> sheetData : mapdata.entrySet()) {
				//sheet
				sheetName = sheetData.getKey();
				sheet = workBook.getSheet(sheetName);//得到指定sheet
				//all rowl count
				int rowNum=0;	
				//all cell count
				int columnNum = 0;
				if(sheet!=null){
					rowNum=Math.max(allRowCount, sheet.getLastRowNum());
					HSSFRow hssfRow = sheet.getRow(0);
					if(hssfRow!=null){
						columnNum=Math.max(allCellCount, hssfRow.getLastCellNum());
					}
					//写数据
					//从上次替换处开始继续遍历
					int tem_j = 0;
					int tem_k = 0;
					for (int i = 0; sheetData.getValue()!=null && i < sheetData.getValue().size(); i++) {
						cellValue = sheetData.getValue().get(i);
						boolean flag = true;
						for (int j = tem_j; flag && j <= rowNum; j++) {
							row = sheet.getRow(j);
							for (int k = tem_k;flag && row!=null && k <= columnNum; k++) {
								cell = row.getCell(k);
								//HSSFCell.CELL_TYPE_STRING == cell.getCellType() String类型，需要替换的内容是#——#，确定是String类型
								if(cell!=null && HSSFCell.CELL_TYPE_STRING == cell.getCellType() && "#_#".equals(cell.getStringCellValue())){
									cell.setCellValue(cellValue);
									flag = false;//跳出 j ，k 循环，并记录j,k的值
									tem_j = j;
									tem_k = k;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return this;
	}

    /**
     * @return
     */
    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workBook.write(out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally{
        }
        return out.toByteArray();
    }
	
    
	/**封装一行row多有cell的数据到map中
	 * @param cellNos:一个cell所有需要写值的cellNo
	 * @param cellValues:每个cell对应的value
	 * @return	Map<Integer,String> cellMap:一个封装了cell数据的map
	 */
	public static Map<Integer,String> fillCellMapData(Integer[] cellNos,String[] cellValues){
		Map<Integer, String> cellMapData = new HashMap<Integer, String>();	//实例化cellMap,新的对象,封装第n行信息
		if(cellNos.length>0 && cellValues.length== cellNos.length){
			for (int i = 0; i < cellNos.length; i++) {
				cellMapData.put(cellNos[i], cellValues[i]);
			}
		}
		return cellMapData;
	}
}
