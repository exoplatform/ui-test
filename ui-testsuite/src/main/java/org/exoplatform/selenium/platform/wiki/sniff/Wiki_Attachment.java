package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Wiki_Attachment extends Wiki_TestConfig {
	/**
	 *<li> Case ID:122812.</li>
	 *<li> Test Case Name: Upload Attachment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:122816.</li>
	 *<li> Test Case Name: Download attachment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:122873.</li>
	 *<li> Test Case Name: Delete attachment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_UploadDownloadDeleteAttachment() {
		info("Test 1: Upload Attachment");
        String rand = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+rand;
		String content =  txData.getContentByArrayTypeRandom(1)+ rand;
		String link = fData.getAttachFileByArrayTypeRandom(1);
		
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);

		/*Step Number: 1
		 *Step Name: Step 1: Upload Attachment in Page
		 *Step Description: 
			- Open Add/Edit page form 
			- Upload attachments
		 *Input Data: 

		 *Expected Outcome: 
			- Attachment(s) is uploaded successful and listed in Attachment table with properties*/ 
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(title, content);
		sourceEditor.attachFile("TestData/"+link);
		wikiMg.saveAddPage();
		
		waitForAndGetElement(wHome.ELEMENT_PAGE_ATTACHFILE,3000,1);
		
		click(wHome.ELEMENT_PAGE_ATTACHFILE);
		driver.navigate().refresh();
		click(wHome.ELEMENT_PAGE_DOWNLOADATTACHFILE);

		click(wHome.ELEMENT_PAGE_DELETEATTACHFILE);
		waitForElementNotPresent(wHome.ELEMENT_PAGE_DOWNLOADATTACHFILE);
		
	}
}