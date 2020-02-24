package  TestCases;
import CommonUtil.*;
import org.openqa.selenium.JavascriptExecutor;


import ExcelUtil.ExcelApiTest4;
import Pages.*;
import HTMLReport.*;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


 
public class TC01_Add_Skills
{

	public static int iRow = 0;
	
	WebDriver driver;
	String error;
	
	Reporter1 R1;
	
	
	
	public static String TestScriptName = "TC01_Add_Skills";
	public static String TestName;
	
	/*public void TC01_Add_Skills(WebDriver driver,Reporter1 R1 ) throws Exception {
		this.driver = driver;
		this.R1 = R1;
	}*/

		
		
		
		// Draws a red border around the found element. Does not set it back anyhow.
		public WebElement findElement(By by) throws Exception {
			WebElement elem = driver.findElement(by);
		
			// draw a border around the found element
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elem);
			}
			Thread.sleep(10);
		
			return elem;
		}
		
		


@Parameters({"Browser"})
@BeforeTest
public void CreateHTML(String browser) throws Exception {
	
	if(browser.equalsIgnoreCase("Chrome")){
		driver = TestBrowser.OpenChromeBrowser();
		
	
	}
	
	else if(browser.equalsIgnoreCase("IE")){
		driver = TestBrowser.OpenIEBrowser();
	
	}
	
	else if(browser.equalsIgnoreCase("FireFox")){
		driver = TestBrowser.OpenFirefoxBrowser();
	
	}
	else
	{
		driver = TestBrowser.OpenChromeBrowser();
	}
	
	
}





@DataProvider(name = "TC01_Add_Skills")
public static Object[][] Authentication1() throws Exception {

	ExcelApiTest4 eat = new ExcelApiTest4();
	Object[][] testObjArray = eat.getTableArray("C://HTML Report//OrangeHRM6//TC01_QuaSkills81.xlsx", "Sheet1");
	System.out.println(testObjArray.length);
	return (testObjArray);
}




@Test(dataProvider = "TC01_Add_Skills")
public void Skills_call_allmethods(String TestURL,String UserName, String Password,
		String SkillName,String SkillDesc) throws Exception {
	
	if (iRow==0)
	{
		iRow++;
		
		String str= "TC01_Add_Skills".concat("_Iterration_").concat(String.valueOf(iRow)) ;
		R1= new Reporter1(driver,str);	
		
	
	Login l1 = new Login();
	l1.Login(driver, R1);
	l1.OrangeHRMlogin(TestURL,UserName, Password);
	


	Skills sk=new Skills();
	sk.Skills(driver, R1);

	sk.AddSkills(SkillName, SkillDesc);

	
	}
	
	else
	{

		iRow++;
		
		String str= "TC01_Add_Skills".concat("_Iterration_").concat(String.valueOf(iRow)) ;
		R1= new Reporter1(driver,str);	
		
		Skills sk=new Skills();
		sk.Skills(driver, R1);
		sk.AddSkills(SkillName, SkillDesc);

	}

}



@AfterTest
public void CloseHtml() throws Exception {
	
	
	HomePage HM= new HomePage();
	HM.HomePage(driver, R1);
	HM.Logout();
	
	TestBrowser.CloseBrowser(driver);

	System.gc();

}




}

