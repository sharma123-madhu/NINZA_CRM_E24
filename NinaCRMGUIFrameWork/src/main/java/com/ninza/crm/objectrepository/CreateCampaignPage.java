package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {
	WebDriver driver;
	public  CreateCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Campaign ID")
	private WebElement Campaign ;
	
	@FindBy(name="campaignName")
	private WebElement campaignName;
	
	
	@FindBy(name="targetSize")
	private WebElement targetSize;
	
	@FindBy(name="campaignStatus")
	private WebElement campaignStatus;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expectedCloseDate;
	
	@FindBy(name="targetAudience")
	private WebElement targetAudience;
	
	@FindBy(name="description")
	private WebElement description;
	
	public WebElement getCampaign() {
		return Campaign;
	}

	public WebElement getCampaignStatus() {
		return campaignStatus;
	}

	public WebElement getTargetAudience() {
		return targetAudience;
	}

	public WebElement getDescription() {
		return description;
	}
	@FindBy(xpath="//button[text()='Create Campaign']")
	private WebElement createButton;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCampaignName() {
		return campaignName;
	}

	public WebElement getTargetSize() {
		return targetSize;
	}

	public WebElement getExpectedCloseDate() {
		return expectedCloseDate;
	}

	public WebElement getCreateButton() {
		return createButton;
	}
	
	
	
	
}

