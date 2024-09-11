package utilities;//package utilities;

import org.testng.annotations.DataProvider;


import java.io.IOException;
import java.util.Arrays;

public class DataProviders
{

    ////////////// Data provider 1
    @DataProvider(name = "loginData")
    public String[][] LoginData() throws IOException {
      String path="C:\\Users\\saida\\OneDrive\\Book 1.xlsx";

      ExcelUtiltityFile exlUtl=new ExcelUtiltityFile(path);

      int noOfRows=exlUtl.getNoOfRows("Sheet1");
      int noOfColms=exlUtl.getNoOfCells("Sheet1",1);

      String[][] logindata=new String[4][3];

      for (int i=1;i<5;i++)
      {
          for (int j=0;j<3;j++)
          {
              logindata[i-1][j] =exlUtl.getCellValue("Sheet1",i,j);
          }
      }
        System.out.print(Arrays.deepToString(logindata));
        return logindata;
    }

    /////// Data provider 2

    /////// Data provider 3

    /////// Data provider 4








}
