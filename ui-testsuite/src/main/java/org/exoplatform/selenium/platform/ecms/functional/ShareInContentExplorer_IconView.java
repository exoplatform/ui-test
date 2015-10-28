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
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class ShareInContentExplorer_IconView extends PlatformBase {
		
		ManageLogInOut manageLoginOut;
		
		NavigationToolbar navTool;	
		SiteExplorerHome siteExplorerHome;
		AttachmentFileDatabase attFileData;
		HomePagePlatform homepage;
		SpaceManagement spaceManage;

		SpaceHomePage spaceHome;
		SpaceSettingManagement spaceSetting;		
			
		
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
			
			spaceManage = new SpaceManagement(driver);		
			spaceHome = new SpaceHomePage(driver);
			spaceSetting = new SpaceSettingManagement(driver);
			
			info("End setUpBeforeMethod");
		}


		@AfterMethod
		public void afterMethod(){		
			manageLoginOut.signOut();
			
			driver.manage().deleteAllCookies();
			driver.quit();				
		}	
		

	/**
	*<li> Case ID:130802.</li>
	*<li> Test Case Name: Check Share button displayed in Contextual Menu.</li>
	*<li> Pre-Condition: userA has permission to use Icon View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckShareButtonDisplayedInContextualMenu() {
		info("Test 1: Check Share button displayed in Contextual Menu");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Icon View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		info("Add/Upload a file.");

		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		homepage.goToDocuments();
		siteExplorerHome.clickIconView();
		siteExplorerHome.uploadFile("TestData/" + fileName);
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Go to parent folder of the above file.
			- Right-click on the file.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is displayed in contextual menu.*/

		info("Check the display of Share button.");
		rightClickOnElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		
		/*Step number: 3
		*Step Name: Step 3:Check Share Dialog displayed.
		*Step Description: 
			- Click on Share menu.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/ 
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);
 	}

	/**
	*<li> Case ID:130807.</li>
	*<li> Test Case Name: Check Share button displayed in Action Bar while previewing content.</li>
	*<li> Pre-Condition: userA has permission to use Icon View</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckShareButtonDisplayedInActionBarWhilePreviewingContent() {
		info("Test 2: Check Share button displayed in Action Bar while previewing content");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Exploere.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Icon View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		info("Add/Upload a file.");
		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		homepage.goToDocuments();
		siteExplorerHome.clickIconView();
		siteExplorerHome.uploadFile("TestData/" + fileName, false);
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Open the file to preview.
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
	*<li> Case ID:130810.</li>
	*<li> Test Case Name: Check ability to share the file in multiple spaces.</li>
	*<li> Pre-Condition: - SPACE1 and SPACE2 already existed.
	- userA is member of SPACE1 and SPACE2.
	- userA has permission to user Icon View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckAbilityToShareTheFileInMultipleSpaces() {
		info("Test 3: Check ability to share the file in multiple spaces");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Icon View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		String spaceName1 = "space1308101";
		String spaceName2 = "space1308102";
		ArrayList<String> spaceList = new ArrayList<>();
		spaceList.add(spaceName1);
		spaceList.add(spaceName2);		
		
		info("Add/Upload a file.");
						
		//Create 2 spaces with 2 members each space
		siteExplorerHome.initSpaceWithUsers(spaceName1, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		siteExplorerHome.initSpaceWithUsers(spaceName2, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);		
		
		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		homepage.goToDocuments();
		siteExplorerHome.goToPathOfDrive("/", "Content Management");
		siteExplorerHome.clickIconView();
		siteExplorerHome.uploadFile("TestData/" + fileName);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		
		/*Step number: 2
		*Step Name: Step 2:Share file in multi-spaces
		*Step Description: 
			- Go to parent folder of this file.
			- Right-click on this file.
			- Click on Share.
			- Select SPACE1 and SPACE2.
			- Share file.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to SPACE1 and SPACE2.*/
		
		info("Share file in multi-spaces");
				
		rightClickOnElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		click(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
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
		
		info("Check spaces1");		
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName1);
	
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName1);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName1);
		
		info("Check spaces2");
		homepage.goToHomePage();
		
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName2);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName2);			
 	}

	/**
	*<li> Case ID:130816.</li>
	*<li> Test Case Name: Check Share button NOT displayed in Contextual Menu while right-click on a folder.</li>
	*<li> Pre-Condition: userA has permission to use Icon View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckShareButtonNOTDisplayedInContextualMenuWhileRightclickOnAFolder() {
		info("Test 4: Check Share button NOT displayed in Contextual Menu while right-click on a folder");
		/*Step Number: 1
		*Step Name: Step 1:Add folder.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Icon View.
			- Add Folder: FolderA.
		*Input Data: 
			
		*Expected Outcome: 
			- The folder is added.*/

		String folderName = "Folder130816-"+getRandomNumber();
		info("Add folder.");

		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickIconView();
		Utils.pause(3000);	

		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderName,"");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", folderName));			
		
		/*Step number: 2
		*Step Name: Step 2:Check Share button NOT displayed.
		*Step Description: 
			- Go to parent folder of FolderA.
			- Right-click on FolderA.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is NOT displayed in Contextual menu.*/ 

		info("Check Share button NOT displayed.");
		rightClickOnElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", folderName));
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);	
 	}
	
}