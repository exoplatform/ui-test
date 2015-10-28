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


	public class ShareInContentExplorer_AdminView extends PlatformBase{
		
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
			manageLoginOut.signOut();
			
			driver.manage().deleteAllCookies();
			driver.quit();			
		}			

	/**
	*<li> Case ID:130803.</li>
	*<li> Test Case Name: Check Share button displayed in Action bar.</li>
	*<li> Pre-Condition: userA has permission to user Admin View.</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test01_CheckShareButtonDisplayedInActionBar() throws Exception {
		info("Test 1: Check Share button displayed in Action bar");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Admin View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/
		
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add(fileName);
		
		String folderName = "Folder130803"+getRandomNumber();
		
		info("Add/Upload a file.File = " + fileName);
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickAdminView();
		Utils.pause(3000);	
		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderName,"");
		siteExplorerHome.uploadFileToFolder(folderName, fileList);
		Utils.pause(3000);	
			
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Go to parent folder of above file.
			- Tick on checkbox to select this file.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is displayed in Action bar.*/
		
		info("Check the display of Share button");
				
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", fileName));
	
		//Select file
		check(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", fileName));	
		//check share button
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		
		/*Step number: 3
		*Step Name: Step 3:Check Share Dialog displayed.
		*Step Description: 
			- Click on Share button.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/ 
		info("Check Share Dialog displayed");
		//click Share button
		click(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		//Check Share Dialog
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);
		info(" END Test 1: Check Share button displayed in Action bar");
 	}

	/**
	*<li> Case ID:130805.</li>
	*<li> Test Case Name: Check Share button displayed in Action Bar while previewing content.</li>
	*<li> Pre-Condition: userA has permission to user Admin View.</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test02_CheckShareButtonDisplayedInActionBarWhilePreviewingContent() throws Exception {
		info("Test 2: Check Share button displayed in Action Bar while previewing content");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Admin View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add(fileName);
		
		String folderName = "Folder130805-"+getRandomNumber();
		
		info("Add/Upload a file - folderName = " + folderName);
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickAdminView();
		Utils.pause(3000);	
		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderName,"");
		siteExplorerHome.uploadFileToFolder(folderName, fileList);
		Utils.pause(3000);	
		
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Open above file to preview.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is displayed in Action bar while previewing.*/

		info("Check the display of Share button");
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE.replace("${fileName}", fileName));
	
		//Select file
		click(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE.replace("${fileName}", fileName));	
		//check share button
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		/*Step number: 3
		*Step Name: Step 3:Check Share Dialog displayed.
		*Step Description: 
			- Click on Share button.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/ 
		
		info("Check Share Dialog displayed");
		//click Share button
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		//Check Share Dialog
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);
		info(" END Test 2: Check Share button displayed in Action Bar while previewing content");		
 	}

	/**
	*<li> Case ID:130809.</li>
	*<li> Test Case Name: Check ability to share the file in multiple spaces.</li>
	*<li> Pre-Condition: - SPACE1 and SPACE2 already existed.
	- userA is members of SPACE1 and SPACE2.
	- userA should have permission to user "Admin View".</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 	
	*/
	@Test
	public  void test03_CheckAbilityToShareTheFileInMultipleSpaces() throws Exception {
		info("Test 3: Check ability to share the file in multiple spaces");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Admin View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		String spaceName1 = "space1308091";
		String spaceName2 = "space1308092";
		ArrayList<String> spaceList = new ArrayList<>();
		spaceList.add(spaceName1);
		spaceList.add(spaceName2);
		
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add(fileName);
		
		String folderName = "Folder130809-"+getRandomNumber();
						
		info("Init spaces, Folder Name =" + folderName);	
		//Create 2 spaces with 2 members each space
		siteExplorerHome.initSpaceWithUsers(spaceName1, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		siteExplorerHome.initSpaceWithUsers(spaceName2, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		// End init spaces
		
		info("Add/Upload a file");	
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		homepage.goToDocuments();	
		siteExplorerHome.goToPathOfDrive("/", "Collaboration");
		siteExplorerHome.clickAdminView();
		Utils.pause(3000);	
		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderName,"");
		siteExplorerHome.uploadFileToFolder(folderName, fileList);
		Utils.pause(3000);	
		
		/*Step number: 2
		*Step Name: Step 2:Share file in multi-spaces
		*Step Description: 
			- Go to parent folder of above file.
			- Tick on checkbox to select this file.
			- Click on Share button.
			- Select SPACE1 and SPACE2.
			- Share.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to SPACE1 and SPACE2.*/
		
		info("Share file in multi-spaces");
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", fileName));
		
		//Select file
		check(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", fileName));	
		//check share button
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		click(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);
		siteExplorerHome.shareDocumentToManySpaces(fileName, spaceList, "");
		
		
		/*Step number: 3
		*Step Name: Step 3:Check file is shared in spaces
		*Step Description: 
			- Log in by other members of SPACE1 and SPACE2.
			- Check AS and Space Documents.
		*Input Data: 
			
		*Expected Outcome: 
			- Activity of File shared will be displayed in Space AS and Intranet AS.
			- A symlink of File is added in Space Documents > Shared folder.*/ 

		manageLoginOut.signIn(DATA_USER2, DATA_PASS);	
		Utils.pause(3000);
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName1);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName1);
		
		homepage.goToHomePage();
		
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName2);
		siteExplorerHome.checkSharedFileSymlink(fileName,spaceName2);
 	}

	/**
	*<li> Case ID:130813.</li>
	*<li> Test Case Name: Check Share button NOT displayed in Action Bar while a folder is ticked and opened.</li>
	*<li> Pre-Condition: userA has permission to user Admin View.</li>
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
			- Select Admin View.
			- Add Folder: FolderA.
		*Input Data: 
			
		*Expected Outcome: 
			- The folder is added.*/
	
		String folderName = "Folder130813-"+getRandomNumber();
		
		info("Add folder");
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickAdminView();
		Utils.pause(3000);	
		//Open Documents folder
		click(siteExplorerHome.ELEMENT_DOCUMENT_PARENT_FOLDER.replace("${folderName}", "Documents"));
		siteExplorerHome.goToAddNewFolder();
		siteExplorerHome.createFolder(folderName,"");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PARENT_FOLDER.replace("${folderName}", folderName));		

		/*Step number: 2
		*Step Name: Step 2:Check Share button NOT displayed.
		*Step Description: 
			- Go to parent folder of FolderA.
			- Tick on checkbox to select FolderA.
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is NOT displayed in Action bar.*/ 
		info("Check Share button NOT displayed");
		check(siteExplorerHome.ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX.replace("${fileName}", folderName));
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
 	}

	/**
	*<li> Case ID:130817.</li>
	*<li> Test Case Name: Check Share button NOT displayed in Action Bar while more than one node is ticked.</li>
	*<li> Pre-Condition: userA has permission to use Admin View.</li>
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
			- Select Admin View.
			- Add/upload multi-files.
		*Input Data: 
			
		*Expected Outcome: 
			- The files are added/uploaded.*/

		String fileName1 = attFileData.getAttachFileByArrayTypeRandom(1);
		String fileName2 = attFileData.getAttachFileByArrayTypeRandom(2);	
		
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add(fileName1);
		fileList.add(fileName2);
		
		String folderName = "Folder130817-"+getRandomNumber();
		
		info("Add/Upload a file");
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		
		homepage.goToDocuments();		
		siteExplorerHome.clickAdminView();
		siteExplorerHome.goToAddNewFolder();
		Utils.pause(3000);	
		siteExplorerHome.createFolder(folderName,"");
		siteExplorerHome.uploadFileToFolder(folderName, fileList);
		Utils.pause(3000);						
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of Share button.
		*Step Description: 
			- Go to parent folder of the files above.
			- Tick on checkboxes to select multi-nodes
		*Input Data: 
			
		*Expected Outcome: 
			- Share button is NOT displayed in Action bar.*/ 
		info("Check the display of Share button");
		check(siteExplorerHome.ELEMENT_DOCUMENT_FILE_CHECKBOX);
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_SHARE);	
 	}
}