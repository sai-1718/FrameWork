package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v128.filesystem.model.File;

import javax.swing.text.Style;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtiltityFile
{

    public   FileInputStream fis;
    public   FileOutputStream fos;
    public   XSSFWorkbook wb;
    public   XSSFSheet sh;
    public   XSSFRow row;
    public   XSSFCell cell;
    public   Style Style;
    public    String path;

    public  ExcelUtiltityFile (String path)
    {
        this.path=path;
    }

    public    int getNoOfRows(String sheetName ) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetName);
        int NoOfRows = sh.getLastRowNum();
        fis.close();
        wb.close();

        return NoOfRows;

    }

    public    int getNoOfCells(String sheetName,int rowNo ) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rowNo);
        int NoOfCells=row.getLastCellNum();
        fis.close();
        wb.close();

        return NoOfCells;

    }

    public    String getCellValue(String sheetName,int rowNo ,int cellNo) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rowNo);
        cell = row.getCell(cellNo);

        String cellValue;
        try
        {
            //String cellValue=cell.getStringCellValue();   // by getStringCellValue()
            //String cellValue=cell.toString();            // by toString
            DataFormatter formatter=new DataFormatter();   // by data formatter
            cellValue=formatter.formatCellValue(cell);
        }
        catch (Exception e)
        {
             cellValue="";
        }
        fis.close();
        wb.close();

        return cellValue;
    }


    public    void setCellData(String sheetName,int rowNo ,int cellNo,String data) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rowNo);

        cell=row.createCell(cellNo);
        cell.setCellValue(data);

        fos=new FileOutputStream(path);
        wb.write(fos);

        fos.close();
        fis.close();
        wb.close();

    }






}
