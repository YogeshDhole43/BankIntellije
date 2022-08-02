import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

@Listeners (Listenerclass.class)

public class TestClass extends BaseClass
{
    @Test (dataProvider = "testdata")
    public void loginverify(String b,String c) throws InterruptedException {
        System.out.println("Test Start");
        Thread.sleep(3000);
        Loginpom lp = new Loginpom(driver);

        lp.enteruseid(b);

        lp.enterpass(c);

        lp.login();


        try {
            driver.switchTo().alert();
            Alert a = driver.switchTo().alert();
            String actual= a.getText();
            String expected = "User or Password is not valid";

            if(actual.equals(expected))
            {
                System.out.println("popup test Passed");
                a.accept();
                driver.switchTo().defaultContent();
            }else {
                System.out.println("popup test Failed");
            }
        }catch(NoAlertPresentException e)
        {
            System.out.println("Login Credentials valid");
            lp.out();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();


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

        String[][] data = new String[noOfRows - 1][noOfColumns];
        for (int i = 0; i < noOfRows - 1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
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

}
