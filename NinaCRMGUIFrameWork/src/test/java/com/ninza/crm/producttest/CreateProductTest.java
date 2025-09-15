package com.ninza.crm.producttest;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileulity.ExcelFileUtility;
import com.ninza.crm.generic.fileulity.PropertyFileUtility;
import com.ninza.crm.generic.webdriveutility.Javautility;
import com.ninza.crm.generic.webdriveutility.WebDriveUtility;
import com.ninza.crm.objectrepository.CreateProductPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.ProductPage;
import com.ninzacrmbaseclass.BaseClass;

public class CreateProductTest extends BaseClass {

	@Test(groups = {"Smoke","Regression"})
	public void createProductWithMendatoryFieldTest()throws IOException, InterruptedException {

		
				
				// generate random number
			
			
			int ranNum = ju.getRandomNumber();
			
			// read the data from excel;
			
			String productName = ex.toReadTheDataFromExcel("Product", 1, 1)+ranNum;
			String qty = ex.toReadTheDataFromExcel("Product", 1,2);
			String price = ex.toReadTheDataFromExcel("Product", 1, 3);
			
			
			
					// click product link in homepage
					HomePage hp=new HomePage(driver);
					hp.getProductsLink().click();
					
					// click on add product button
					ProductPage pp=new ProductPage(driver);
					pp.getCreatePage().click();
					
					//Create product
					
					CreateProductPage cpp=new CreateProductPage(driver);
					cpp.getProductName().sendKeys(productName);
					WebElement quantity = cpp.getQuantity();
					Thread.sleep(2000);
					quantity.clear();
					quantity.sendKeys(qty);
					
					WebElement pricepp=cpp.getPrice();
					pricepp.clear();
					pricepp.sendKeys(price);
					
					WebDriveUtility wdu=new WebDriveUtility();
				    wdu.dropDownByindex(cpp.getProductCategory(),3);
					wdu.dropDownByindex(cpp.getVendorId(),2);
					Thread.sleep(2000);
					cpp.getAddproductButton().click();
					
					Thread.sleep(2000);
					
					//verify the successfulmsg
					WebElement toastmsg=cpp.getToastMsg();
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
					wait.until(ExpectedConditions.visibilityOf(toastmsg));
					
					String msg =toastmsg.getText();
					System.out.println(msg);
					//Assert.assertEquals(msg,"product "+productName +" Successfully Added","Both the  equal");
					Assert.assertTrue(msg.contains("Successfully Added"));
//					if(msg.contains("Sucessfully Added")) {
//						System.out.println("product create successfully");
//					}else
//						
//					 {
//						System.out.println("product is not created ");
//					}
//		
				hp.getCloseToastMsgBtn().click();
	}
	}


