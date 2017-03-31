package simon.demo.core.util.fastexcel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 快速简单操作Excel的工具
 *
 * @author peiyu
 */
/**
 * @author Administrator
 *
 */
public abstract class FastExcel implements Closeable {

    private static final Logger     LOG    = LoggerFactory.getLogger(FastExcel.class);
    
    private  Workbook workbook;
    /**
     * 时日类型的数据默认格式化方式
     */
    private	DateFormat		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  int      inportStartRow = 0;
    private  String   sheetName = "Sheet1";
    private  String   excelFilePath;//导出路径（传统 磁盘的方式 out了）

    private  String   outFilePath;//导出路径（传统 磁盘的方式 out了）
    protected Sheet sheet;
    protected CellStyle[] cellStyles;
    

//++++++++++++++++++++++++++++++++++++++++++====导入====+++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++====导入====+++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++====导入====+++++++++++++++++++++++++++++++++++++++++++
    
    /**
     * 设置时间数据格式
     * @param format 格式
     */
    public FastExcel setDateFormat(String format) {
        this.dateFormat = new SimpleDateFormat(format);
        return this;
    }
    
    /**
     * 设置需要读取的sheet名字，不设置默认的名字是Sheet1，也就是excel默认给的名字，所以如果文件没有自已修改，这个方法也就不用调了
     * @param sheetName 需要读取的Sheet名字
     */
    public FastExcel setSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }
    
    /**
     * 开始读取的行数，这里指的是标题内容行的行数，不是数据开始的那行
     * @param startRow 开始行数
     */
    public FastExcel setInportStartRow(int startRow) {
        if (startRow < 1) {
            throw new RuntimeException("最小为1");
        }
        this.inportStartRow = --startRow;
        return this;
    }
    /**
     * 传入excel路径获取excel流（传统方式）
     * @param excelFilePath
     * @throws IOException
     */
    public FastExcel setExcelFilePath(String excelFilePath) throws InvalidFormatException, IOException{
        this.excelFilePath = excelFilePath;
        this.workbook = createWorkbookByFilePath();
    	return this;
    };
    
    /**
     * 创建工作簿（excelFilePath的形式）
     * @throws IOException
     * @throws InvalidFormatException
     */
    private Workbook createWorkbookByFilePath() throws IOException, InvalidFormatException {
        Workbook workbook;
        File file = new File(this.excelFilePath);
        if (!file.exists()) {
            LOG.warn("文件:{} 不存在！创建此文件！", this.excelFilePath);
            if (!file.createNewFile()) {
                throw new IOException("文件创建失败");
            }
            workbook = new XSSFWorkbook();
        } else {
            workbook = WorkbookFactory.create(file);
        }
        return workbook;
    }
    
    /**
     * 通过数据流操作excel，仅用于读取数据(web 上传)
     * @param inputStream excel数据流
     * @throws IOException            IO流异常
     * @throws InvalidFormatException 非法的格式异常
     */
    public FastExcel setExcelInputStream(InputStream inputStream) throws IOException, InvalidFormatException {
        this.workbook = WorkbookFactory.create(inputStream);
        return this;
    }

    /** 导入
     * 解析读取excel文件
     * 通过clazz对象字段的注解 与 excel 中列数据匹配
     * @param clazz 对应的映射类型
     * @param <T>   泛型
     * @return 读取结果
     */
    public <T> List<T> excel2ObjByAnnotation(Class<T> clazz) {
        List<T> resultList = null;
        try {
        	int num = workbook.getNumberOfSheets();	
        	
        	for(int sheeti = 0; sheeti < num; sheeti++){
    			sheet = workbook.getSheetAt(sheeti);
    			if (null != sheet) {
                    resultList = new ArrayList<T>(sheet.getLastRowNum() - 1);
                    Row row = sheet.getRow(this.inportStartRow);

                    Map<String, Field> fieldMap = new HashMap<String, Field>();
                    Map<String, String> titleMap = new HashMap<String, String>();

                    Field[] fields = clazz.getDeclaredFields();
                    //这里开始处理映射类型里的注解
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(MapperCell.class)) {
                            MapperCell mapperCell = field.getAnnotation(MapperCell.class);
                            fieldMap.put(mapperCell.cellName(), field);
                        }
                    }

                    for (Cell title : row) {
                        CellReference cellRef = new CellReference(title);
                        titleMap.put(cellRef.getCellRefParts()[2], title.getRichStringCellValue().getString());
                    }

                    for (int i = this.inportStartRow + 1; i <= sheet.getLastRowNum(); i++) {
                        T t = clazz.newInstance();
                        Row dataRow = sheet.getRow(i);
                        for (Cell data : dataRow) {
                            CellReference cellRef = new CellReference(data);
                            String cellTag = cellRef.getCellRefParts()[2];
                            String name = titleMap.get(cellTag);
                            Field field = fieldMap.get(name);
                            if (null != field) {
                                field.setAccessible(true);
                                getCellValue(data, t, field);
                            }
                        }
                        resultList.add(t);
                    }
                } else {
                    throw new RuntimeException("the sheet indexOf :" + num + " is not exist");
                }
        	}
        } catch (InstantiationException e) {
            LOG.error("初始化异常", e);
        } catch (IllegalAccessException e) {
            LOG.error("初始化异常", e);
        } catch (ParseException e) {
            LOG.error("时间格式化异常:{}", e);
        } catch (Exception e) {
            LOG.error("其他异常", e);
        }
        return resultList;
    }


    @SuppressWarnings("deprecation")
	private void getCellValue(Cell cell, Object o, Field field) throws IllegalAccessException, ParseException {
//        LOG.debug("cell:{}, field:{}, type:{}", cell.getCellTypeEnum(), field.getName(), field.getType().getName());
        switch (cell.getCellTypeEnum()) {
            case BLANK:
                break;
            case BOOLEAN:
                field.setBoolean(o, cell.getBooleanCellValue());
                break;
            case ERROR:
                field.setByte(o, cell.getErrorCellValue());
                break;
            case FORMULA:
                field.set(o, cell.getCellFormula());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    if (field.getType().getName().equals(Date.class.getName())) {
                        field.set(o, cell.getDateCellValue());
                    } else {
                        field.set(o, dateFormat.format(cell.getDateCellValue()));
                    }
                } else {
                    if (field.getType().isAssignableFrom(Integer.class) || field.getType().getName().equals("int")) {
                        field.setInt(o, (int) cell.getNumericCellValue());
                    } else if (field.getType().isAssignableFrom(Short.class) || field.getType().getName().equals("short")) {
                        field.setShort(o, (short) cell.getNumericCellValue());
                    } else if (field.getType().isAssignableFrom(Float.class) || field.getType().getName().equals("float")) {
                        field.setFloat(o, (float) cell.getNumericCellValue());
                    } else if (field.getType().isAssignableFrom(Byte.class) || field.getType().getName().equals("byte")) {
                        field.setByte(o, (byte) cell.getNumericCellValue());
                    } else if (field.getType().isAssignableFrom(Double.class) || field.getType().getName().equals("double")) {
                        field.setDouble(o, cell.getNumericCellValue());
                    } else if (field.getType().isAssignableFrom(String.class)) {
                        String s = String.valueOf(cell.getNumericCellValue());
                        if (s.contains("E")) {
                            s = s.trim();
                            BigDecimal bigDecimal = new BigDecimal(s);
                            s = bigDecimal.toPlainString();
                        }
                        //防止整数判定为浮点数
                        if (s.endsWith(".0"))
                            s = s.substring(0, s.indexOf(".0"));
                        field.set(o, s);
                    } else {
                        field.set(o, cell.getNumericCellValue());
                    }
                }
                break;
            case STRING:
                if (field.getType().getName().equals(Date.class.getName())) {
                    field.set(o, dateFormat.parse(cell.getRichStringCellValue().getString()));
                } else {
                    field.set(o, cell.getRichStringCellValue().getString());
                }
                break;
            default:
                field.set(o, cell.getStringCellValue());
                break;
        }
    }

//TODO 导出
//++++++++++++++++++++++++++++++++++++++++++====导出====+++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++====导出====+++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++====导出====+++++++++++++++++++++++++++++++++++++++++++
    
    
    /**
     * 如果文件 需要保存在 磁盘  调用
     * @param outFilePath
     * @return
     */
    public FastExcel setOutFilePath(String outFilePath){
    	this.outFilePath = outFilePath;
    	return this;
    }
    
    
    /**
     * 将数据写入excel文件,导出
     *
     * @param list 数据列表
     * @param <T>  泛型
     * @return 写入结果
     */
    public <T> boolean createExcel(List<T> list) {
    	workbook = new HSSFWorkbook();
        boolean result = false;
        if (null != list && !list.isEmpty()) {
            T test = list.get(0);
            Map<String, Field> fieldMap = new HashMap<String, Field>();
            Map<Integer, String> titleMap = new TreeMap<Integer, String>();
            Field[] fields = test.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
            	Field field = fields[i];
            	if (field.isAnnotationPresent(MapperCell.class)) {
                    MapperCell mapperCell = field.getAnnotation(MapperCell.class);
                    fieldMap.put(mapperCell.cellName(), field);
                    if(titleMap.get(mapperCell.order())!=null){
                    	//说明order 排序有重复，忽略MapperCell.order 属性
                    	titleMap.put(i, mapperCell.cellName());
                    }else{
                    	titleMap.put(mapperCell.order(), mapperCell.cellName());
                    }
                }
			}
            try {
                sheet = workbook.createSheet(this.sheetName==null?"Sheet1":this.sheetName);
                Collection<String> values = titleMap.values();
                String[] s = new String[values.size()];
                values.toArray(s);
                
                //保存样式数组 每一列 所有样式相同
                cellStyles = new CellStyle[s.length];
                //生成标题行
                Row titleRow = sheet.createRow(0);
                for (int i = 0; i < s.length; i++) {
                    Cell cell = titleRow.createCell(i);
                    cell.setCellValue(s[i]);
                    cellStyles[i] = workbook.createCellStyle();
                    formatHeadCell(cell, 0, i);
                }
                //生成数据行
                for (int i = 0, length = list.size(); i < length; i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < s.length; j++) {
                        Cell cell = row.createCell(j);
                        for (Map.Entry<String, Field> data : fieldMap.entrySet()) {
                            if (data.getKey().equals(s[j])) {
                                Field field = data.getValue();
                                field.setAccessible(true);
                                cell.setCellValue(field.get(list.get(i)).toString());
                                formatContentCell(cell, i, j, field.get(list.get(i)).toString());
                                break;
                            }
                        }
                    }
                }
                result = true;
            }catch (Exception e) {
                LOG.error("其他异常", e);
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    
    /**
     * 将生成的excel 保存在磁盘 outFilePath 磁盘路径
     */
    public void createFileInDisk(){
    	FileOutputStream fileOutputStream = null;
    	File file = new File(this.outFilePath);
        try {
			if (!file.exists()) {
			    if (!file.createNewFile()) {
			        throw new IOException("文件创建失败");
			    }
			}
			fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 转成字节数组（springmvc 文件下载的形式 使用）
     * @return
     */
    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
        } finally{
//        	try {
//				workbook.close();
//			} catch (IOException e) {
//				logger.error(e.getMessage());
//			}
        }
        return out.toByteArray();
    }
    /**
     * 获取指定单元格的值
     *
     * @param rowNumber  行数，从1开始
     * @param cellNumber 列数，从1开始
     * @return 该单元格的值
     */
    public String getCellValue(int rowNumber, int cellNumber) {
        String result;
        checkRowAndCell(rowNumber, cellNumber);
        Sheet sheet = this.workbook.getSheet(this.sheetName);
        Row row = sheet.getRow(--rowNumber);
        Cell cell = row.getCell(--cellNumber);
        switch (cell.getCellTypeEnum()) {
            case BLANK:
                result = cell.getStringCellValue();
                break;
            case BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                result = String.valueOf(cell.getErrorCellValue());
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = dateFormat.format(cell.getDateCellValue());
                } else {
                    result = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            default:
                result = cell.getStringCellValue();
                break;
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        this.workbook.close();
    }

    private void checkRowAndCell(int rowNumber, int cellNumber) {
        if (rowNumber < 1) {
            throw new RuntimeException("rowNumber less than 1");
        }
        if (cellNumber < 1) {
            throw new RuntimeException("cellNumber less than 1");
        }
    }
    
    protected abstract void formatContentCell(Cell cell, int rowIndex, int colIndex, Object value);

    protected abstract void formatHeadCell(Cell cell, int rowIndex, int colIndex);
}
