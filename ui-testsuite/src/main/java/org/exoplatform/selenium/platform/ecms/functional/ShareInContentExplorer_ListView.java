package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class ShareInContentExplorer_ListView extends PlatformBase {
		
		ManageLogInOut manageLoginOut;
		
		SiteExplorerHome siteExplorerHome;
		AttachmentFileDatabase attFileData;
		HomePagePlatform homepage;
		
		@BeforeMethod
		public void setUpBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			initSeleniumTest();
			getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			manageLoginOut = new ManageLogInOut(driver);
			
			siteExplorerHome  = new SiteExplorerHome(driver);
			attFileData = new AttachmentFileDatabase(); 	
			attFileData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
			homepage = new HomePagePlatform(driver);
			
			info("End setUpBeforeMethod");
		}
	
		@AfterMethod
		public void afterMethod(){
			switchToParentWindow();
			manageLoginOut.signOut();
			
			driver.manage().deleteAllCookies();
			driver.quit();			
		}			

	/**
	*<li> Case ID:130804.</li>
	*<li> Test Case Name: Check Share button displayed in Action bar.</li>
	*<li> Pre-Condition: userA has permission to use List View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckShareButtonDisplayedInActionBar() {
		info("Test 1: Check Share button displayed in Action bar");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select List View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		info("Add/Upload a file.");

		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		Utils.pause(3000);
		homepage.goToDocuments();	
		siteExplorerHome.clickListView();
		siteExplorerHome.uploadFile("TestData/" + fileName);
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Go to parent folder of this file.
			- Tick on checkbox to select this file.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is displayed in Action bar.*/

		info("Check the display of Share button.");
		check(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
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
	*<li> Case ID:130806.</li>
	*<li> Test Case Name: Check Share button displayed in Action Bar while previewing content.</li>
	*<li> Pre-Condition: userA has permission to use List View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckShareButtonDisplayedInActionBarWhilePreviewingContent() {
		info("Test 2: Check Share button displayed in Action Bar while previewing content");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select List View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		info("Add/Upload a file.");

		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		Utils.pause(3000);
		homepage.goToDocuments();	
		siteExplorerHome.clickListView();
		siteExplorerHome.uploadFile("TestData/" + fileName);
		Utils.pause(3000);
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Open this file to preview.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is displayed in Action bar while previewing.*/

		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
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
	*<li> Case ID:130811.</li>
	*<li> Test Case Name: Check ability to share the file in multiple spaces.</li>
	*<li> Pre-Condition: - SPACE1 and SPACE2 already existed.
	- userA is member of SPACE1 and SPACE2.
	- userA has permission to use List View.</li>
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
			- Select List View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		
		String spaceName1 = "space1308111";
		String spaceName2 = "space1308112";
		ArrayList<String> spaceList = new ArrayList<>();
		spaceList.add(spaceName1);
		spaceList.add(spaceName2);	
								
		//Create 2 spaces with 2 members each space
		siteExplorerHome.initSpaceWithUsers(spaceName1, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		siteExplorerHome.initSpaceWithUsers(spaceName2, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);		
		
		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		homepage.goToDocuments();
		siteExplorerHome.goToPathOfDrive("/", "Content Management");
		Utils.pause(3000);
		siteExplorerHome.clickListView();
		siteExplorerHome.uploadFile("TestData/" + fileName);
		String tmpFileName=fileName.substring(0,fileName.lastIndexOf("."));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", tmpFileName));		
		
		/*Step number: 2
		*Step Name: Step 2:Share file in multi
		-spaces
		*Step Description: 
			- Go to parent folder of this file.
			- Tick on checkbox to select this file.
			- Click on Share button.
			- Select SPACE1 and SPACE2.
			- Share.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to SPACE1 and SPACE2.*/

		info("Share file in multi-spaces");
				
		check(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", fileName));	
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
		info("Check space1");
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName1);
		
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName1);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName1);
		
		info("Check space2");
		homepage.goToHomePage();		
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName2);
		
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName2);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName2);			
 	}

	/**
	*<li> Case ID:130815.</li>
	*<li> Test Case Name: Check Share button NOT displayed in Action Bar while a folder is ticked and opened.</li>
	*<li> Pre-Condition: userA has permission to use List View.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckShareButtonNOTDisplayedInActionBarWhileAFolderIsTickedAndOpened() {
		info("Test 4: Check Share button NOT displayed in Action Bar while a folder is ticked and opened");
		/*Step Number: 1
		*Step Name: Step 1:Add folder.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select List View.
			- Add Folder: FolderA.
		*Input Data: 
			
		*Expected Outcome: 
			- The folder is added.*/

		String folderName = "Folder130815-"+getRandomNumber();
		info("Add folder.");
		manageLoginOut.signIn(DATA_USER1,DATA_PASS);
		
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickListView();
		Utils.pause(3000);	

		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderName,"");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", folderName));			
				
		
		/*Step number: 2
		*Step Name: Step 2:Check Share button NOT displayed.
		*Step Description: 
			- Go to parent folder of FolderA.
			- Tick on checkbox to select FolderA.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is NOT displayed in Action bar.*/ 
		info("Check Share button NOT displayed.");
		check(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", folderName));	
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
 	}

	/**
	*<li> Case ID:130818.</li>
	*<li> Test Case Name: Check Share button NOT displayed in Action Bar while more than one node is ticked.</li>
	*<li> Pre-Condition: userA has permission to use List View.</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test05_CheckShareButtonNOTDisplayedInActionBarWhileMoreThanOneNodeIsTicked() throws Exception {
		info("Test 5: Check Share button NOT displayed in Action Bar while more than one node is ticked");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload multi
		-files.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select List View.
			- Add/upload multi
			-files.
		*Input Data: 
			
		*Expected Outcome: 
			- The files are added/uploaded.*/

		String fileName1 = attFileData.getAttachFileByArrayTypeRandom(1);
		String fileName2 = attFileData.getAttachFileByArrayTypeRandom(2);	
		
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add(fileName1);
		fileList.add(fileName2);
		
		String folderName = "Folder130818-"+getRandomNumber();
		
		info("Add/Upload a file");
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickListView();
		siteExplorerHome.goToAddNewFolder();
		Utils.pause(3000);	
		siteExplorerHome.createFolder(folderName,"");
		siteExplorerHome.uploadFileToFolder(folderName, fileList);
		Utils.pause(3000);			
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Go to parent folder of the above files.
			- Tick on checkboxes to select multi-files.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is NOT displayed in Action bar.*/ 
		
		homepage.goToDocuments();		
		siteExplorerHome.goToPathOfDrive("/", "Personal Documents");
		info("Check the display of Share button");
		check(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", folderName), 2000, 2,1);	
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
 	}
	
}