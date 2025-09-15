package com.ninza.crm.objectrepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="username")
	WebElement username; 
	
	@FindBy(id="inputPassword")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbutton;

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginbutton() {
		return loginbutton;
	}
	
	public void loginIntoApp(String url,String un,String pwd) {
		//Maximize the window
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.get(url);
				getUsername().sendKeys(un);
				getPassword().sendKeys(pwd);
				getLoginbutton().click();
				
	}

}
