package com.ninzacrmbaseclass;

import org.testng.annotations.Test;

import com.ninza.crm.generic.fileulity.ExcelFileUtility;
import com.ninza.crm.generic.fileulity.PropertyFileUtility;
import com.ninza.crm.generic.webdriveutility.Javautility;
import com.ninza.crm.generic.webdriveutility.WebDriveUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	
	 
	public WebDriver driver=null;
	 public  PropertyFileUtility pf=new PropertyFileUtility();
	public  ExcelFileUtility ex=new ExcelFileUtility();
  public Javautility ju=new Javautility();
  public  WebDriveUtility wlib=new WebDriveUtility();

  
  
  @BeforeMethod(groups = {"Smoke","Regression"})
  public void beforeMethod() throws IOException {
        System.out.println("Login");
  String URL = pf.togetFromPropertiesFile("url");
	String USERNAME = pf.togetFromPropertiesFile("username");
	String PASSWORD = pf.togetFromPropertiesFile("password");
//	
//	  String URL=System.getProperty("URL");
//	  String USERNAME=System.getProperty("username");
//	  String PASSWORD=System.getProperty("password");
//			  
			  
			  
			  
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PASSWORD);

  }

  @AfterMethod(groups = {"Smoke","Regression"})
  public void afterMethod() {
	  System.out.println("Logout");
	  HomePage hp = new HomePage(driver);
		hp.logout();
  }
//@Parameters("Browser")
  @BeforeClass(groups = {"Smoke","Regression"})
  public void beforeClass() throws IOException {
	  System.out.println("Launch the browser");
	String BROWSER = pf.togetFromPropertiesFile("browser");
	 //String Browser=System.getProperty("Browser");
	  
	  ChromeOptions settings =new ChromeOptions();
		Map<String,Object> prefs= new HashMap<>();
	prefs.put("profile.password_manager_leak_detection", false);
	settings.setExperimentalOption("prefs", prefs);
	
	
	
//	WebDriverManager.chromedriver().setup();
//	WebDriverManager.edgedriver().setup();
	// launch the browser
			if (BROWSER.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			} else if (BROWSER.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (BROWSER.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}
			 driver=new ChromeDriver(settings);

  }

  @AfterClass(groups = {"Smoke","Regression"})
  public void afterClass() {
	  System.out.println("Close the browser");
	  driver.quit();
  }

  @BeforeTest(groups = {"Smoke","Regression"})
  public void beforeTest() {
	  System.out.println("Pre-conditions for parallel executions");
  }

  @AfterTest(groups = {"Smoke","Regression"})
  public void afterTest() {
	  System.out.println("Post-conditions for parallel executions");
  }

  @BeforeSuite(groups = {"Smoke","Regression"})
  public void beforeSuite() {
	  System.out.println("Connect to Database");
  }

  @AfterSuite(groups = {"Smoke","Regression"})
  public void afterSuite() {
	  System.out.println("Disconnect to Database");
  }

}
