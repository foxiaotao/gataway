package simon.demo.core.util.simonexcel;

import org.apache.poi.ss.usermodel.Cell;

import simon.demo.core.util.fastexcel.RandFFutrueBean;

public class XxxExcelUtilMapping<T> extends ExcelUtilMapping<T> {


	public XxxExcelUtilMapping(Class<T> c) {
		super(c);
	}

	@Override
    protected void formatContentCell(Cell cell, int rowIndex, int colIndex,Object value) {
//    	setGeneralProperty(cellStyles[colIndex]);
    	if(value == null) {
            cell.setCellValue("");
        }else {
            // 自适应宽度
            int cellLength = value.toString().getBytes().length;
            // excel有列宽限制的 255字符
            if (cellLength > 125) {
            	cellLength = 125;
            } else if (cellLength < 10) {
            	cellLength = 10;
            }
            sheet.setColumnWidth(colIndex, cellLength * 2 * 256);
        }
    	cell.setCellStyle(cellStyles[colIndex]);
    }

    @Override
    protected void formatHeadCell(Cell cell, int rowIndex, int colIndex) {
    	sheet.setColumnWidth(colIndex, 10 * 2 * 256);
    	setGeneralProperty(cellStyles[colIndex]);
    	cell.setCellStyle(cellStyles[colIndex]);
    }

}
