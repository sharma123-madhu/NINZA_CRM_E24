package configurationannotation;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.crm.objectrepository.HomePage;

public class CreateCampaigntestextendsBaseclass {
	@Test
	public void createCampaignWithMandatoryFieldsTest() throws InterruptedException, IOException {

		String campName = ex.readDataFromExcelFile("campaign", 1, 2);
		String targetSize = ex.readDataFromExcelFile("campaign", 1, 3);

		int randomInt = jLib.getRandomNumber();
		String campaignName = campName + randomInt;

		// Create Campaign
		CampaignPage campaignPage = new CampaignPage(driver);
		campaignPage.createCampaign(campaignName, targetSize);
		campaignPage.getCreateCampaignSubmitBtn().click();

		// Validation
		HomePage hp = new HomePage(driver);
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		Assert.assertEquals(msg, "Campaign " + campaignName + " Successfully Added");
		hp.getcloseToastMsg().click();

	}

	@Test
	public void createCampaignWithStatusTest() throws EncryptedDocumentException, IOException {

		
		String campName = elib.readDataFromExcelFile("Campaign", 4, 2);
		String targetSize = elib.readDataFromExcelFile("Campaign", 4, 3);
		String status = eLib.readDataFromExcelFile("Campaign", 4, 4);

		int randomInt = jLib.getRandomNumber();
		String campaignName = campName + randomInt;

		// Create Campaign
		CampaignPage campaignPage = new CampaignPage(driver);
		campaignPage.createCampaign(campaignName, targetSize);
		campaignPage.getCampaignStatusTF().sendKeys(status);
		campaignPage.getCreateCampaignSubmitBtn().click();

		// Validation
		HomePage hp = new HomePage(driver);
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		Assert.assertTrue(msg.contains(campaignName));
		hp.getToastMsg().click();
	}

	@Test
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {

		String campName = eLib.readDataFromExcelFile("Campaign", 7, 2);
		String targetSize = eLib.readDataFromExcelFile("Campaign", 7, 3);

		int randomInt = jLib.getRandomNumber();
		String campaignName = campName + randomInt;

		// Create Campaign
		CampaignPage campaignPage = new CampaignPage(driver);
		campaignPage.createCampaign(campaignName, targetSize);
		wLib.enterInput(driver, campaignPage.getExpectedCloseDateTF(), jLib.getRequiredDate(30));
		campaignPage.getCreateCampaignSubmitBtn().click();

		// Validation
		HomePage hp = new HomePage(driver);
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		Assert.assertTrue(msg.contains(campaignName));
		hp.getToastMsg().click();
		System.out.println("asdfghj");
	}

}
 

}
