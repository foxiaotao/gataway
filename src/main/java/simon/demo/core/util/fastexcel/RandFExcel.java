package simon.demo.core.util.fastexcel;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class RandFExcel extends FastExcel {
	

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
    /**
	 * 功能描述：设置EXCEL表格基本样式
	 */
	private void setGeneralProperty(CellStyle style) {
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setAlignment(HorizontalAlignment.CENTER);
	}
}
