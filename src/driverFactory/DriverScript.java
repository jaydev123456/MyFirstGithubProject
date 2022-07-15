package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath = "D:\\Selenium_8oclock\\MavenProject\\TestInput\\LoginData.xlsx";
String outPutpath = "D:\\Selenium_8oclock\\DDT_FrameWork\\TestOutput\\DataDrivenResults.xlsx";
@Test
public void validatelogin() throws Throwable
{
	boolean res = false;
	//access excel methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in a sheet
	int rc = xl.rowCount("Login");
	//count no of cell in row
	int cc = xl.cellcount("Login");
	Reporter.log("no of rows are::"+rc+"no of cells in row are::"+cc,true);
	for (int i=1;i<rc;i++)
	{
		driver.get(config.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//read cell data
		String user = xl.getCelldata("Login", i, 0);
		String pass = xl.getCelldata("Login", i, 1);
		res = FunctionLibrary.verifylogin(user, pass);
		if(res)
		{
			//write as login success into results cell
			xl.setCellData("Login", i, 2, "Login success", outPutpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "pass", outPutpath);
		}
		else
		{
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./ScreenShot/iteration/"+i+"LoginPage.png"));
		//write as login fail into results cell
			xl.setCellData("Login", i, 2, "Login fail", outPutpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "fail", outPutpath);
		}
	}
}
}
