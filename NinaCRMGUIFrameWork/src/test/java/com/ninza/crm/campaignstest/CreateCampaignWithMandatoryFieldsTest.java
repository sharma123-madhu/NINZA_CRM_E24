package com.ninza.crm.campaignstest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileulity.ExcelFileUtility;
import com.ninza.crm.generic.fileulity.PropertyFileUtility;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;


public class CreateCampaignWithMandatoryFieldsTest{
	
	@DataProvider
	public Object[][]login() throws EncryptedDocumentException, IOException{
		ExcelFileUtility ex=new ExcelFileUtility();
		int lastRow = ex.ToGetTheRowCount("campaign");
		
		Object[][] obj=new Object[lastRow][2];
		for (int i = 0; i < lastRow; i++) {
			obj[i][0]=ex.toReadTheDataFromExcel("campaign", i+1, 1);
			obj[i][1]=ex.toReadTheDataFromExcel("campaign", i+1,2);
			
		}
		return obj;
	}
	
	@Test(dataProvider="login")
	public void createCampaignWithMandatoryTest(String campignName,String targetSize) throws IOException {
		
// properties file
		// create the object of propertyfileutility
	PropertyFileUtility pf=new PropertyFileUtility();
	
	//get the value
	String BROWSER = pf.togetFromPropertiesFile("browser");
	  String URL = pf.togetFromPropertiesFile("url");
	   String USERNAME = pf.togetFromPropertiesFile("username");
	   String PASSWORD = pf.togetFromPropertiesFile("password");
	   
	   //create the file excelfile utility
	// ExcelFileUtility ex=new ExcelFileUtility();
	   
	//   String CampName = ex.toReadTheDataFromExcel("Camp", 1, 1);
	//   String Target= ex.toReadTheDataFromExcel("Camp", 1, 2);
	   

		ChromeOptions settings =new ChromeOptions();
		
		Map<String,Object> prefs= new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		WebDriver driver=null;
		//Launch the browser
		if (BROWSER.equals("edge")) {
			 driver=new EdgeDriver();
			
		} else if (BROWSER.equals("chrome")) {
			driver=new ChromeDriver(settings);
			
		} 
		

		//navigate and login into the application
		LoginPage lp=new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PASSWORD);
	
		
        // click on campaign link in homepage
		HomePage hp=new HomePage(driver);
		hp.getCampaignLink().click();
		
		//click on create campaign 
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getCreateCampaign().click();
		
		
		
		//create campaign with mandatory fields
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(campignName);
		ccp.getTargetSize().clear();
	   ccp.getTargetSize().sendKeys(targetSize);
	   ccp.getCreateButton().click();
		
		//verify the succesfull message
	    WebElement toastmsg=cp.getSuccessMsg();
	
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg =toastmsg.getText();
		
		if(msg.contains("Successfully Added")) {
			System.out.println("Campaign create successfully");
		}
		else {
			System.out.println("Campaign is not created ");
		}

	}
}


