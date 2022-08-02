import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseClass
{
    public WebDriver driver;
    @BeforeClass
    public void launchbroswer()
    {
        String key = "webdriver.gecko.driver";
        String value = "G:\\AutomationProjects Intellije\\src\\main\\resources\\Drivers\\geckodriver.exe";
        String url = "https://www.demo.guru99.com/V4/";
        System.setProperty(key,value);
        driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    @AfterClass
    public void closebrowser()
    {
        driver.quit();
    }
}

