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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileulity.ExcelFileUtility;
import com.ninza.crm.generic.fileulity.PropertyFileUtility;
import com.ninza.crm.generic.webdriveutility.Javautility;// 
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninzacrmbaseclass.BaseClass;

public class CreateCamapignTest extends BaseClass {
	@Test(groups ="Smoke" )
	public void createCamapignWithMandataoryFieldsTest() throws EncryptedDocumentException, IOException {
		
		// excel file
		// Step 1. create the object of excel file utility
		ExcelFileUtility ex = new ExcelFileUtility();
		String campaignName = ex.toReadTheDataFromExcel("Camp", 1, 1);
		String targetSize = ex.toReadTheDataFromExcel("Camp", 1, 2);
    
	
		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();

		// Create campaign with Mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);// 
		ccp.getCreateButton().click();

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();
		//System.out.println(msg);
		Assert.assertEquals(msg,"Campaign "+campaignName +" Successfully Added","Both the not equal");
//		if (msg.contains("Successfully Added")) {
//			System.out.println("Campign created successfully");
//		} else {
//			System.out.println("Campign is not created");
//		}
		hp.getCloseToastMsgBtn().click();
		
	}

	@Test(groups ="Regression" )
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
		
		// excel file
		// Step 1. create the object of excel file utility
		ExcelFileUtility ex = new ExcelFileUtility();
		String campaignName = ex.toReadTheDataFromExcel("Camp", 1, 1);
		String targetSize = ex.toReadTheDataFromExcel("Camp", 1, 2);

		 
		

		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();

		// Create campaign with Mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);

		// Get the date after 30 days
		Javautility ju = new Javautility();
		String expectedDate = ju.togetRequiredDate(30);
		ccp.getExpectedCloseDate().sendKeys(expectedDate);
		ccp.getCreateButton().click();

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();
	Assert.assertTrue(msg.contains("Successfully Added"));
//
//		if (msg.contains("Successfully Added")) {
//			System.out.println("Campign created successfully");
//		} else {
//			System.out.println("Campign is not created");
//		}
		hp.getCloseToastMsgBtn().click();
		

	}
}

