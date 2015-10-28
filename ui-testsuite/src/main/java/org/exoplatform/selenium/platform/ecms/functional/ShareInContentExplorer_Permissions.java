package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class ShareInContentExplorer_Permissions  extends PlatformBase {
		ManageLogInOut manageLoginOut;
		
		NavigationToolbar navTool;	
		SiteExplorerHome siteExplorerHome;
		AttachmentFileDatabase attFileData;
		HomePagePlatform homepage;
		SpaceManagement spaceManage;

		SiteExplorerDriveDatabase siteExDrive;
		Button button;
				
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
								
			siteExDrive = new SiteExplorerDriveDatabase();
			siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
			
			button = new Button(driver);
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
	*<li> Case ID:130847.</li>
	*<li> Test Case Name: Check the default permission of Space is applied on Shared folder.</li>
	*<li> Pre-Condition: userA is member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckTheDefaultPermissionOfSpaceIsAppliedOnSharedFolder() {
		info("Test 1: Check the default permission of Space is applied on Shared folder");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared in Space1.*/

		String spaceName = "space130847";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
			
		info("Init spaces");	
		//Create 2 spaces with 2 members each space
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		siteExplorerHome.uploadAndShareDocumentToSpace(fileName, spaceName, "");	
				
		manageLoginOut.signOut();		
		
		/*Step number: 2
		*Step Name: Step 2: Check the default permission of space is applied for the shared folder.
		*Step Description: 
			- Log in by other space members.
			- Go to Space Document > Shared folder.
			- Check Permissions of Shared folder.
		*Input Data: 
			
		*Expected Outcome: 
			- The default permission of Shared folder is inherited from Space Documents > root folder.
			- The permissions of symlink file is same as source file.*/ 
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		homepage.goToMySpaces();
		spaceManage.goToSpace(spaceName);
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);
		
		info("Check Shared folder permission");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_PERMISSION);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION_DIALOG_TITLE);
		Utils.pause(3000);
			
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName).replace("${permission}", "read"),2000,2,1);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName)
				.replace("${permission}", "addNode"),2000,2,1);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}",spaceName)
																			.replace("${permission}", "remove"),2000,2,1);	
		
		
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Close"));
		
		info("Check symlink permission");
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																	.replace("${spaceName}", spaceName)
																	.replace("${fileName}", fileName));	
		click(siteExplorerHome.ELEMENT_ACTIONBAR_PERMISSION);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION_DIALOG_TITLE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName)
																	.replace("${permission}", "read"),2000,2,1);			
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName)
																	.replace("${permission}", "addNode"),2000,2,1);		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName)
																	.replace("${permission}", "remove"),2000,2,1);						
 	}

	/**
	*<li> Case ID:130854.</li>
	*<li> Test Case Name: Check user of space can view document when sharing with View permission.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckUserOfSpaceCanViewDocumentWhenSharingWithViewPermission() {
		info("Test 2: Check user of space can view document when sharing with View permission");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1 with "Can view" permission.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130854";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
			
		info("Init spaces");	
		//Create 2 spaces with 2 members each space
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		homepage.goToDocuments();		
		
		siteExplorerHome.uploadFile("TestData/"+ fileName);
		
		
		//upload filename has a redundant space. Ex: abc .jpg
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_VIEW, "");
		manageLoginOut.signOut();
		
		/*Step number: 2
		*Step Name: Step 2: Check member of space can view only.
		*Step Description: 
			- Log in by userB, member of space.
			- Go to Space Documents > Shared folder.
			- Open the symlink of file.
		*Input Data: 
			
		*Expected Outcome: 
			- File is opened with View permission only.
			- Edit button will NOT be shown.*/ 
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		homepage.goToMySpaces();
		spaceManage.goToSpace(spaceName);
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);
		//Open file
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));	
		Utils.pause(3000);
		waitForElementNotPresent(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT);
 	}

	/**
	*<li> Case ID:130855.</li>
	*<li> Test Case Name: Check user of space can edit document when sharing with Edit permission.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckUserOfSpaceCanEditDocumentWhenSharingWithEditPermission() {
		info("Test 3: Check user of space can edit document when sharing with Edit permission");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1 with "Can edit" permission.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130855";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
			
		info("Init spaces");	
		//Create space with 2 members
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		navTool.goToSiteExplorer();	
		
		siteExplorerHome.uploadFile("TestData/"+ fileName);
				
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_EDIT, "");
		manageLoginOut.signOut();		
		/*Step number: 2
		*Step Name: Step 2: Check member of space can edit the file.
		*Step Description: 
			- Log in by userB, member of space.
			- Go to Space Documents > Shared folder.
			- Open the symlink of file.
		*Input Data: 
			
		*Expected Outcome: 
			- File is opened with Edit permission.
			- Edit button is shown.
			- Member of space can edit this source file via symlink.*/ 
		
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		homepage.goToMySpaces();
		spaceManage.goToSpace(spaceName);
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);
		//Open file
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));		
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT);	
		click(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_EDIT_FORM);
 	}

	/**
	*<li> Case ID:130856.</li>
	*<li> Test Case Name: Check the file is updated when user of space edit file via symlink.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckTheFileIsUpdatedWhenUserOfSpaceEditFileViaSymlink() {
		info("Test 4: Check the file is updated when user of space edit file via symlink");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file with "Can edit" permission.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130856";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(19);	
		String updateTile = "New title 130856";
		String updateContent = "New content 130856";
		
		String driveName = siteExDrive.getSiteExpDriveByIndex(0);//Collaboration	
		
		info("Init spaces");	
		//Create 2 spaces with 2 members
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);		
		navTool.goToSiteExplorer();
		siteExplorerHome.openDrives();
		siteExplorerHome.selectADrive(driveName);	
		
		siteExplorerHome.uploadFile("TestData/"+ fileName);
		String tmpFileName=fileName.substring(0,fileName.lastIndexOf("."));
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", tmpFileName));	
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", tmpFileName));	
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_EDIT, "");
		manageLoginOut.signOut();		
		
		/*Step number: 2
		*Step Name: Step 2: Member of space edits the file.
		*Step Description: 
			- Log in by userB.
			- Go to Space Documents > Shared folder.
			- Open file by clicking on symlink of file.
			- Edit this file (content and properties).
			- Save.
		*Input Data: 
			
		*Expected Outcome: 
			- File is opened with Edit permission.
			- Member of space can edit this file.
			Note:User will not have the right to remove the child-node of a document. 
			Ex: Accessible Media File, remove content and upload new content. 
			This will not be allowed because user does not have Delete permission.*/

		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		homepage.goToMySpaces();
		spaceManage.goToSpace(spaceName);
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);
		//Open file
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));		
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT);	
		click(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_EDIT_FORM);	
		
		info("Update title");
		type(siteExplorerHome.ELEMENT_FILE_FORM_TITLE,updateTile, true );
		
		info("Update content");
		type(siteExplorerHome.ELEMENT_DOCUMENT_CONTENT,updateContent, true );
		
		info("Save & Close");
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		manageLoginOut.signOut();
		
		/*Step number: 3
		*Step Name: Step 3:Check the file after it is updated.
		*Step Description: 
			- Log in by userA.
			- Open the source file.
		*Input Data: 
			
		*Expected Outcome: 
			- Can see the last person who updated the document in List View/Admin View.
			- The content and properties updated will be shown correctly.*/ 

		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
		navTool.goToSiteExplorer();
		siteExplorerHome.openDrives();
		siteExplorerHome.selectADrive(driveName);	
		
		info("Check last update author");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_UPDATED_USER.replace("${fileName}", tmpFileName)
																.replace("${user}", DATA_USER2));	
		info("Check content");
		//open file
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", tmpFileName));
		click(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_EDIT_FORM);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_UPDATED_TITLE.replace("${title}", updateTile));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_UPDATED_CONTENT.replace("${content}", updateContent));
 	}

	/**
	*<li> Case ID:131629.</li>
	*<li> Test Case Name: Check permissions table of source file after sharing file to space.</li>
	*<li> Pre-Condition: userA is member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckPermissionsTableOfSourceFileAfterSharingFileToSpace() {
		info("Test 5: Check permissions table of source file after sharing file to space");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1 with "Can view" or "Can edit" permission.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to space members.*/

		String spaceName = "space131629";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
				
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);	
		
		info("Init space");	
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.goToCreateSpace();
		spaceManage.addNewSpaceSimple(spaceName, "");
		Utils.pause(3000);
		
		navTool.goToSiteExplorer();		
		siteExplorerHome.uploadFile("TestData/"+ fileName);
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_VIEW, "");
				
		/*Step number: 2
		*Step Name: Step 2: Check Permissions table of source file.
		*Step Description: 
			- Open the source file.
			- Check Permissions.
		*Input Data: 
			
		*Expected Outcome: 
			- Users of space is set "Read" permission in source file If shared with "View" permission.
			- Users of space is set "Read" and "Modify" permission in source file If shared with "Edit" permission.*/ 

		info("Check permission");
		if(waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_EDIT,5000,0)==null){
			info("Click on More menu");
			click(siteExplorerHome.ELEMENT_ACTIONBAR_MORE);
		}
		click(siteExplorerHome.ELEMENT_ACTIONBAR_PERMISSION);	
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION_DIALOG_TITLE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName)
																			.replace("${permission}", "read"),2000,2,1);
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceName)
																			.replace("${permission}", "addNode"),2000,2,1);		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}",spaceName)
																			.replace("${permission}", "remove"),2000,2,1);						
 	}

	/**
	*<li> Case ID:131630.</li>
	*<li> Test Case Name: Check permissions table of space when sharing file from space to other spaces.</li>
	*<li> Pre-Condition: - 3 spaces created: SPACE1, SPACE2, SPACE3.
		- userA is members of these spaces.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckPermissionsTableOfSpaceWhenSharingFileFromSpaceToOtherSpaces() {
		info("Test 6: Check permissions table of space when sharing file from space to other spaces");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to SPACE1 > Documents.
			- Add/Upload a file.
			- Share this file to SPACE2 with "Can view" permission.
			- Share ths file to SPACE3 with "Can edit" permission.
		*Input Data: 
			
		*Expected Outcome: 
			- File of SPACE1 is shared to space members of SPACE2 and SPACE3.*/

		String spaceName = "space131630";
		ArrayList<String> spaceNameList = new ArrayList<String>();
				
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
				
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);	
		
		info("Init spaces");	
		for(int i = 1; i <=3; i++){
			spaceName = spaceName + i;
			spaceNameList.add(spaceName);
			homepage.goToAllSpace();
			Utils.pause(3000);
			spaceManage.goToCreateSpace();
			spaceManage.addNewSpaceSimple(spaceName, "");
			Utils.pause(3000);	
			spaceName = "space131630";
		}
		
		info("Go to space1");
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceNameList.get(0));
		spaceManage.goToDocumentTab();
				
		info("Upload file");
		siteExplorerHome.uploadFile("TestData/"+fileName);
				
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
		doubleClickOnElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceNameList.get(1), siteExplorerHome.SHARE_ACCESS_CAN_VIEW, "");
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceNameList.get(2), siteExplorerHome.SHARE_ACCESS_CAN_EDIT, "");
				
		
		/*Step number: 2
		*Step Name: Step 2: Check Permissions table of source file.
		*Step Description: 
			- Go to SPACE1 > Documents.
			- Check Permissions of source file.
		*Input Data: 
			
		*Expected Outcome: 
			With source file: 
			- Users of SPACE1 have full permissions.
			- Users of SPACE2 have Read permission only.
			- Users of SPACE3 have Read and Edit permissions.*/

		info("Check permission");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_PERMISSION);	
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION_DIALOG_TITLE);
		
		info("Check permission space 1");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(0))
																			.replace("${permission}", "read"),2000,2,1);			
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(0))
																.replace("${permission}", "addNode"),2000,2,1);		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(0))
																.replace("${permission}", "remove"),2000,2,1);	
		
		info("Check permission space 2");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(1))
																			.replace("${permission}", "read"),2000,2,1);			
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(1))
																.replace("${permission}", "addNode"),2000,2,1);		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(1))
																.replace("${permission}", "remove"),2000,2,1);		
		
		
		info("Check permission space 3");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(2))
																			.replace("${permission}", "read"),2000,2,1);			
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(2))
																.replace("${permission}", "addNode"),2000,2,1);		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(2))
																.replace("${permission}", "remove"),2000,2,1);		
		
		/*Step number: 3
		*Step Name: Step 3:Check Permission table of symlink file.
		*Step Description: 
			- Go to SPACE2 > Documents > Shared folder.
			- Check Permissions of symlink file.
		*Input Data: 
			
		*Expected Outcome: 
			- Users of SPACE2 have Read permission only.*/
		info("Go to space 2");
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceNameList.get(1));
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceNameList.get(1));
		
		info("Check permission of symlink");
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
												.replace("${spaceName}", spaceNameList.get(1))
												.replace("${fileName}", fileName));	
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(1))
																				.replace("${permission}", "read"),2000,2,1);			
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(1))
																				.replace("${permission}", "addNode"),2000,2,1);		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(1))
																				.replace("${permission}", "remove"),2000,2,1);	
		/*Step number: 4
		*Step Name: Step 4:Check Permission table of symlink file.
		*Step Description: 
			- Go to SPACE3 > Documents > Shared folder.
			- Check Permissions of symlink file.
		*Input Data: 
			
		*Expected Outcome: 
			- Users of SPACE3 have Read and Edit permissions.*/ 
		info("Go to space 3");
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceNameList.get(2));
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceNameList.get(2));
		
		info("Check permission of symlink");
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
												.replace("${spaceName}", spaceNameList.get(2))
												.replace("${fileName}", fileName));	
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(2))
																		.replace("${permission}", "read"),2000,2,1);			
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(2))
																		.replace("${permission}", "addNode"),2000,2,1);		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_PERMISSION.replace("${spaceName}", spaceNameList.get(2))
																		.replace("${permission}", "remove"),2000,2,1);				
 	}
}