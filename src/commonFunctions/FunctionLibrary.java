package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
	
		public static boolean verifylogin(String username,String password)
		{
			driver.findElement(By.cssSelector(config.getProperty("ObjUser"))).sendKeys("username");
			driver.findElement(By.cssSelector(config.getProperty("ObjPass"))).sendKeys("password");
			driver.findElement(By.cssSelector(config.getProperty("ObjLogin"))).click();
			String expected = "dashboard";
			String actual = driver.getCurrentUrl();
			if(actual.contains(expected))
			{
				Reporter.log("login success::"+expected+"   "+actual,true);
					return true;
			}
			else
			{
				String errormessage = driver.findElement(By.cssSelector(config.getProperty("eObjErrormessag "))).getText();
				Reporter.log(errormessage+"   "+expected+"  "+actual,true);
				return false;
			}
		}

	

}
