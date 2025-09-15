package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	
	WebDriver driver;
	public CreateProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(name="productId")
	private WebElement productId;
	
	@FindBy(name="productName")
	private WebElement productName;
	
	@FindBy(name="quantity")
	private WebElement quantity;
	
	
	@FindBy(name="price")
	private WebElement price;
	
	
	@FindBy(name="productCategory")
	private WebElement productCategory;
	
	
	@FindBy(name="vendorId")
	private WebElement vendorId;
	
	@FindBy(xpath ="//button[@type='submit']")
	private WebElement addProductButton;
	
	@FindBy(xpath = "//div[@class='Toastify__toast-body']")
	private WebElement toastMsg;
	
	public WebElement getProductName() {
		return productName;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getProductCategory() {
		return productCategory;
	}

	public WebElement getVendorId() {
		return vendorId;
	}

	public WebElement getAddproductButton() {
		return addProductButton;
	}

	public WebElement getToastMsg() {
		return toastMsg;
	}
	
	
	
	

}
