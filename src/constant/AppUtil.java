package constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver;
public static Properties config;
@BeforeTest
public void setUp() throws Throwable
{
config = new Properties();
config.load(new FileInputStream("D:\\Selenium_8oclock\\DDT_FrameWork\\PropertyFiles\\login.properties"));
if(config.getProperty("Browser").equalsIgnoreCase("chrome"));
{
	driver = new ChromeDriver();
	}
 if(config.getProperty("Browser").equalsIgnoreCase("Firefox"));
{
	driver = new FirefoxDriver();
	}


	System.out.println(("Browser value is not not matching"));
}


@AfterTest
public void teardown()
{
	driver.close();
	}
}


