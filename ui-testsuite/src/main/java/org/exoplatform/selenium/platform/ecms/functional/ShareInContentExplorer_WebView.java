package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class ShareInContentExplorer_WebView extends PlatformBase {
		
		ManageLogInOut manageLoginOut;
		
		NavigationToolbar navTool;	
		SiteExplorerHome siteExplorerHome;
		AttachmentFileDatabase attFileData;
		HomePagePlatform homepage;
				
		@BeforeMethod
		public void setUpBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			initSeleniumTest();
			getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			manageLoginOut = new ManageLogInOut(driver);
			
			navTool = new NavigationToolbar(driver);
			siteExplorerHome  = new SiteExplorerHome(driver);
			attFileData = new AttachmentFileDatabase(); 	
			attFileData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
			homepage = new HomePagePlatform(driver);
						
			manageLoginOut.signInWithoutRefresh(DATA_USER1, DATA_PASS);

			info("End setUpBeforeMethod");
		}
	
		@AfterMethod
		public void afterMethod(){			
			manageLoginOut.signOut();
			
			driver.manage().deleteAllCookies();
			driver.quit();			
		}	
		
		

	/**
	*<li> Case ID:130808.</li>
	*<li> Test Case Name: Check Share button displayed in Action Bar while previewing content.</li>
	*<li> Pre-Condition: userA has permission to use Web View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckShareButtonDisplayedInActionBarWhilePreviewingContent() {
		info("Test 1: Check Share button displayed in Action Bar while previewing content");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Web View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		info("Add/Upload a file.File = " + fileName);

		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		Utils.pause(3000);
		homepage.goToDocuments();	
		siteExplorerHome.goToPathOfDrive("/", "Collaboration");
		siteExplorerHome.clickWebView();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
				
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Open file to preview.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is displayed in Action bar while previewing.*/
		info("Check the display of Share button.");
		doubleClickOnElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		
		/*Step number: 3
		*Step Name: Step 3:Check Share Dialog displayed.
		*Step Description: 
			- Click on Share button.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/ 
		
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);		
 	}

	/**
	*<li> Case ID:130812.</li>
	*<li> Test Case Name: Check ability to share the file in multiple spaces.</li>
	*<li> Pre-Condition: - SPACE1 and SPACE2 already existed.
						 - userA is member of SPACE1 and SPACE2.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckAbilityToShareTheFileInMultipleSpaces() {
		info("Test 2: Check ability to share the file in multiple spaces");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Web View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		String spaceName1 = "space1308121";
		String spaceName2 = "space1308122";
		ArrayList<String> spaceList = new ArrayList<>();
		spaceList.add(spaceName1);
		spaceList.add(spaceName2);	
								
		//Create 2 spaces with 2 members each space
		siteExplorerHome.initSpaceWithUsers(spaceName1, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		siteExplorerHome.initSpaceWithUsers(spaceName2, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);		
		
		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		homepage.goToDocuments();
		siteExplorerHome.goToPathOfDrive("/", "Collaboration");
		siteExplorerHome.clickWebView();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
				
		
		/*Step number: 2
		*Step Name: Step 2:Share file in multi
		-spaces
		*Step Description: 
			- Open the file to Preview.
			- Click on Share button.
			- Select SPACE1 and SPACE2.
			- Share.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to SPACE1 and SPACE2.*/

		info("Share file in multi-spaces");
		
		doubleClickOnElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		siteExplorerHome.shareDocumentToManySpaces(fileName, spaceList, "");
		
		manageLoginOut.signOut();		
		
		/*Step number: 3
		*Step Name: Step 3:Check file is shared in spaces
		*Step Description: 
			- Log in by other members of SPACE1 and SPACE2.
			- Check AS and Space Documents.
		*Input Data: 
			
		*Expected Outcome: 
			- Activity of File shared will be displayed in Space AS and Intranet AS.
			- A symlink of File is added in Space Documents > Shared folder.*/ 
		
		manageLoginOut.signIn(DATA_USER2,DATA_PASS);			
		Utils.pause(3000);
		
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName1);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName1);
		
		homepage.goToHomePage();
		
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName2);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName2);	
 	}
}