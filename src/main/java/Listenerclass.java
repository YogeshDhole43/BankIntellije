import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listenerclass extends Utility implements ITestListener
{
    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println(result.getMethod().getMethodName());
        captureScreenshot(result.getMethod().getMethodName()+".jpg");
    }

}
