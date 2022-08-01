import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utility
{

    public static String screenshotsSubFolderName;
    public static String logindata(int i , int j) throws EncryptedDocumentException, IOException
    {
        String Path = "G:\\AutomationProjects Intellije\\src\\main\\resources\\e_banking.xlsx";
        FileInputStream f = new FileInputStream(Path);

        Workbook wb = WorkbookFactory.create(f);
        Sheet sh = wb.getSheet("Sheet1");
        Row r= sh.getRow(0);
        int rows = sh.getLastRowNum();
        int col = r.getLastCellNum();
        Cell cell = sh.getRow(i).getCell(j);
        CellType type = cell.getCellType();
        String type1 = type.toString();
        if(type1=="STRING")
        {
            String value =cell.getStringCellValue();
            return value ;
        }else
        {if(type1=="NUMERIC")
        {
            double val = cell.getNumericCellValue();
            long valu = (long)val;
            String value = String.valueOf(valu);
            return value;
        }else
        {
            boolean val = cell.getBooleanCellValue();
            String value = String.valueOf(val);
            return value;
        }
        }

    }


    @DataProvider(name="testdata")
    public Object[][] getData() throws Exception {
        File excelFile = new File("G:\\AutomationProjects Intellije\\src\\main\\resources\\e_banking.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[noOfRows-1][noOfColumns];
        for (int i = 0; i < noOfRows-1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                data[i][j] =  df.formatCellValue(sheet.getRow(i+1).getCell(j));
            }
        }
        workbook.close();
        fis.close();
        //To print the data use following for each loop.
 		/* for (String[] dataArr : data) {
 			System.out.println(Arrays.toString(dataArr));
 		}*/
        return data;
    }
    public void captureScreenshot(String fileName) {
        if(screenshotsSubFolderName == null) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotsSubFolderName = myDateObj.format(myFormatObj);
        }
        TakesScreenshot driver = null;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./Screen/"+ screenshotsSubFolderName+"/"+fileName);
        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved successfully");
    }



}
