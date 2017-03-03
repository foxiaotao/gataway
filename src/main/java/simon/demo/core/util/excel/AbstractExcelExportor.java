package simon.demo.core.util.excel;


import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import simon.demo.core.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * excel导出
 */
public abstract class AbstractExcelExportor {

    private final Logger logger = Logger.getLogger(getClass());

    // properties
    private String[] columnNames;

    protected Workbook workbook = new SXSSFWorkbook(5000);
    
    protected Sheet sheet;
    protected CellStyle[] cellStyles;

    public AbstractExcelExportor createSheet(String sheetName) {
        Assert.notNull(sheetName, "sheet名称不能为空!");
        sheet = workbook.createSheet(sheetName);
        return this;
    }

    public AbstractExcelExportor createSheet() {
        sheet = workbook.createSheet();
        return this;
    }

    public AbstractExcelExportor setColumnNames(String[] columnNames) {
        Assert.notNull(columnNames, "columnNames不能为空!");
        Assert.notEmpty(columnNames, "columnNames不能为空!");
        this.columnNames = columnNames;
        return this;
    }

    public AbstractExcelExportor createHeader(String[] headers) {
        Assert.notNull(headers, "Excel头部不能为空!");
        Assert.notEmpty(headers, "Excel头部不能为空!");
        Row row = sheet.createRow(0); // 头
        cellStyles = new CellStyle[headers.length];
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cellStyles[i] = workbook.createCellStyle();
            cell.setCellValue(headers[i]);
            formatHeadCell(cell, 0, i);
        }
        return this;
    }

    public AbstractExcelExportor createContent(List<?> datas) {
        if(datas != null && !datas.isEmpty()) {
            for (int i = 0; i < datas.size(); i++) {
                Object dataObj = datas.get(i);
                Row row = sheet.createRow(i + 1);
                
                for (int j = 0; j < columnNames.length; j++) {
                    Cell cell = row.createCell(j);
                    Method method = ReflectionUtils.findMethod(dataObj.getClass(), "get" + StringUtil.firstWordToUpperCase(columnNames[j]));
                    Object value = ReflectionUtils.invokeMethod(method, dataObj);
                    
                    if(value == null) {
                        cell.setCellValue("");
                    }else {
                        if(value instanceof String) {
                        	cell.setCellValue((String)value);
                        }else if(value instanceof Integer) {
                            cell.setCellValue((double)value);
                        }
                    }
                    formatContentCell(cell, i, j,value);
                }
            }
        }
        return this;
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally{
//        	try {
//				workbook.close();
//			} catch (IOException e) {
//				logger.error(e.getMessage());
//			}
        }
        return out.toByteArray();
    }

    protected abstract void formatContentCell(Cell cell, int rowIndex, int colIndex, Object value);

    protected abstract void formatHeadCell(Cell cell, int rowIndex, int colIndex);
}
