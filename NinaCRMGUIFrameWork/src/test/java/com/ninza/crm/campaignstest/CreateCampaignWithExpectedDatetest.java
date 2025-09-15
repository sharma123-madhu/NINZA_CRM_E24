package com.ninza.crm.campaignstest;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ninza.crm.generic.fileulity.ExcelFileUtility;
import com.ninza.crm.generic.webdriveutility.Javautility;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

public class CreateCampaignWithExpectedDatetest {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		//Read the data from JSON file
		// Step 1. Craete a java representation of physcial file
		FileReader fr=new FileReader("./src/main/resources/commondata.json");
     // step 2. Create the object JSonparser and use parse method to pass the object of physcial file
		
		JSONParser jp=new JSONParser();
		Object javaobject=jp.parse(fr);
		
		//step 3 Convert java object to json by downcasting
		
		 JSONObject  obj = (JSONObject)javaobject;
		 
		 //step 4 Read data using get()
		 String BROWSER=obj.get("browser").toString();
		 String URL = obj.get("url").toString();
		 String USERNAME=obj.get("username").toString();
		 String PASSWORD=obj.get("password").toString();
		 
		 // excelfile
		 //create the object of excel file utility
		   ExcelFileUtility ex=new ExcelFileUtility();
			   String CampName = ex.toReadTheDataFromExcel("Camp", 1, 1);
		   String Target= ex.toReadTheDataFromExcel("Camp", 1, 2);
		   

		 
			
			ChromeOptions settings =new ChromeOptions();
			
			Map<String,Object> prefs= new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", prefs);
			
			WebDriver driver=null;
			//Launch the browser
			if (BROWSER.equals("edge")) {
				 driver=new EdgeDriver();
				
			} else if (BROWSER.equals("chrome")) {
				driver=new ChromeDriver();
				
			} 
			

			 driver=new ChromeDriver(settings);
			 
			 

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
				ccp.getCampaignName().sendKeys(CampName);
				ccp.getTargetSize().clear();
			   ccp.getTargetSize().sendKeys(Target);
			   ccp.getCreateButton().click();
			   Thread.sleep(2000);
			   //get the date after 30 days
			   Javautility ju=new Javautility();
			  String expectedDate = ju.togetRequiredDate(30);
			  ccp.getExpectedCloseDate().sendKeys(expectedDate);
			  ccp.getCreateButton().click();
			
			
		
		
		//Verify the Successful message
//WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
	
			  WebElement toastmsg=cp.getSuccessMsg();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		
		String msg =toastmsg.getText();
		System.out.println(msg);
		
		}
	}


