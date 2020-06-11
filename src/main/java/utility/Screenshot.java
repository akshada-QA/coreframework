package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.core.coreframework.BaseTest;

public class Screenshot extends BaseTest{
	
	public static String getScreenshot(String screenshotName) throws IOException {
	        
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);

			org.openqa.selenium.io.FileHandler.copy(source, finalDestination);
			return destination;

}
}