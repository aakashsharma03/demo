package  com.unifier.automation.dmautomation.excelReader;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public Excel_Reader (String path)
	{
		this.path = path;
		try {
			
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("deprecation")
	public String [][] getDataFromSheet(String sheetName, String excelName)
	{
		String datasets [][]=null;
		sheet = workbook.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum() + 1;
		int totalColumn = sheet.getRow(0).getLastCellNum();
		datasets = new String [totalRow - 1][totalColumn];
		
		try 
		{
			for (int i=1;i<totalRow;i++)
			{
				
				XSSFRow rows = sheet.getRow(i);
				for ( int j = 0;j<totalColumn; j++)
				{
					XSSFCell cell = rows.getCell(j);
					if (cell.getCellType()==Cell.CELL_TYPE_STRING)
						datasets[i-1][j]=cell.getStringCellValue();
					else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
						String celltext = String.valueOf(cell.getNumericCellValue());
						datasets[i-1][j]=celltext;
						}
					else
						datasets[i-1][j]=String.valueOf(cell.getBooleanCellValue());
				
				}
			}
		
			return datasets;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasets;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public String getCellData (String sheetName , String colName, int rowNum)
	{ 
		try {
			int colNum = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i=0;i<row.getLastCellNum();i++)
			{
				if (row.getCell(i).getStringCellValue().equals(colName))
				{
					colNum=i;
					break;
				}
			}
			row = sheet.getRow(rowNum-1);
			XSSFCell cell = row.getCell(colNum);
			if(cell.getCellType()==Cell.CELL_TYPE_STRING) 
				{
					return cell.getStringCellValue();
				} 
			else if (cell.getCellType()==Cell.CELL_TYPE_BLANK)
				{
					return "";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	}
