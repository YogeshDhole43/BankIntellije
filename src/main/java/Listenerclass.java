import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listenerclass extends Utility implements ITestListener
{
    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println(result.getMethod().getMethodName());
        try {
            captureScreenshot(result.getMethod().getMethodName()+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
