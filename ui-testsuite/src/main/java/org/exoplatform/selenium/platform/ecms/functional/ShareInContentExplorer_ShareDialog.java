package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ContentAdministration;
import org.exoplatform.selenium.platform.administration.ContentAdministration.specificView;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class ShareInContentExplorer_ShareDialog extends PlatformBase {

		ManageLogInOut manageLoginOut;
		
		SiteExplorerHome siteExplorerHome;
		AttachmentFileDatabase attFileData;
		HomePagePlatform homepage;
		SpaceManagement spaceManage;
								
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
			
			spaceManage = new SpaceManagement(driver);		
									
			info("End setUpBeforeMethod");
		}
	
		@AfterMethod
		public void afterMethod(){	
			manageLoginOut.signOut();
			driver.manage().deleteAllCookies();
			driver.quit();			
		}		
		
			
	/**
	*<li> Case ID:130840.</li>
	*<li> Test Case Name: Check the spaces selected are listed.</li>
	*<li> Pre-Condition: userA is members of some Spaces.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckTheSpacesSelectedAreListed() {
		info("Test 1: Check the spaces selected are listed");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select Icon/List View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		String spaceName = "space130840";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
		ArrayList<String> spaceList = new ArrayList<>();
		
		info("User creates some spaces");		
			
		for(int i = 0; i < 5; i++){
			spaceName = spaceName + i;
			spaceList.add(spaceName);
			
			homepage.goToAllSpace();
			Utils.pause(3000);
			spaceManage.goToCreateSpace();			
			spaceManage.addNewSpaceSimple(spaceName, "");			
		}
				
		homepage.goToDocuments();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		//upload filename has a redundant space. Fx: abc .jpg
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
		
		/*Step number: 2
		*Step Name: Step 2:Share the file.
		*Step Description: 
			- Select this file.
			- Click on Share button in Action bar.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/
		
		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
				
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);
		
		/*Step number: 3
		*Step Name: Step 3:Check spaces selected will be listed.
		*Step Description: 
			- Click on "Share with" drop-down list.
			- Select space by space in list.
		*Input Data: 
			
		*Expected Outcome: 
			- Spaces selected will be listed under "Share with" drop-down list.
			- Each space is displayed as Mentioning Tag: Space name and (x) icon.
			- Can remove each space by (x) button.
			- If a space is selected many times, space name listed is not duplicated.*/ 
		info("Select spaces to share");
		for(String spaceElement:spaceList){
			//Click space list
			click(siteExplorerHome.ELEMENT_SPACE_LIST);
			//Select a space
			waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE
													.replace("${spaceName}", spaceElement));
			click(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceElement));
			Utils.pause(3000);
			waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE_TO_SHARE
													.replace("${spaceName}", spaceElement));
		}	
		//Select the first space again
		//Click space list
		click(siteExplorerHome.ELEMENT_SPACE_LIST);
		click(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceList.get(0)));

		info("Remove space");
		for(String spaceElement:spaceList){
			click(siteExplorerHome.ELEMENT_SELECTED_SPACE_TO_SHARE.replace("${spaceName}", spaceElement));
			waitForElementNotPresent(siteExplorerHome.ELEMENT_SELECTED_SPACE_TO_SHARE
													.replace("${spaceName}", spaceElement));
		}	
 	}

	/**
	*<li> Case ID:130842.</li>
	*<li> Test Case Name: Check elements in UI of Sharing Dialog form.</li>
	*<li> Pre-Condition: userA is member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckElementsInUIOfSharingDialogForm() {
		info("Test 2: Check elements in UI of Sharing Dialog form");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Select List/Icon View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		String spaceName = "space130842";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
				
		info("User creates space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.goToCreateSpace();
		spaceManage.addNewSpaceSimple(spaceName, "");
						
		homepage.goToDocuments();
		siteExplorerHome.clickListView();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		//upload filename has a redundant space. Fx: abc .jpg
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
				
		
		/*Step number: 2
		*Step Name: Step 2:Share the file.
		*Step Description: 
			- Select this file.
			- Click on Share button.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/
		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);				
		
		/*Step number: 3
		*Step Name: Step 3:Check the display of Sharing Dialog.
		*Step Description: 
			- Check the elements in UI.Refer to images in attachment.
		*Input Data: 
			
		*Expected Outcome: 
			- Title: "Share".
			- The file icon and filename are displayed correctly.
			- A drop-down list "Share with". Suggestion text in gray color: "Select a Space"
			- A message textbox. Suggestion text in gray color: "Add a comment about to this file...".
			- A drop -down list "Access: Space members" with 2 options: "Can view" and "Can edit".
			- "Share" and "Cancel" and Close (x) buttons.*/ 
		info("Check title");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_DIALOG_TITLE);
		
		info("Check drop-down list");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_SPACE_DROPDOWN);		

		info("Check message textbox");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_COMMENT_BOX);		

		info("Check Access");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", siteExplorerHome.SHARE_ACCESS_CAN_VIEW));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", siteExplorerHome.SHARE_ACCESS_CAN_EDIT));
		
		info("Check buttons");
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Share"));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace("${name}", "Cancel"));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_CLOSE_BUTTON);			
 	}

	/**
	*<li> Case ID:130843.</li>
	*<li> Test Case Name: Check "Share with" dropdown list: Spaces List and Filter.</li>
	*<li> Pre-Condition: userA is members of some Spaces.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckShareWithDropdownList_SpacesListAndFilter() {
		info("Test 3: Check Share with dropdown list: Spaces List and Filter");
		/*Step Number: 1
		*Step Name: Step 1:Add/Upload a file.
		*Step Description: 
			- Log in by userA..
			- Go to Content Explorer.(Ex:Personal Documents / Space Documents / Site Explorer).
			- Select Icon/List View.
			- Add new content or upload a file.
		*Input Data: 
			
		*Expected Outcome: 
			- The content / file is added.*/

		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		String spaceName1 = "abc" + getRandomNumber();
		String spaceName2 = "abd" + getRandomNumber();
		String spaceName3 = "efgh" + getRandomNumber();
		
		ArrayList<String> spaceList = new ArrayList<String>();
		spaceList.add(spaceName1);
		spaceList.add(spaceName2);
		spaceList.add(spaceName3);
				
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
				
		info("User creates some spaces");
		for(int i = 0; i < 3; i++){
			homepage.goToAllSpace();
			Utils.pause(3000);
			spaceManage.goToCreateSpace();
			spaceManage.addNewSpaceSimple(spaceList.get(i), "");
		}
		
		info("Upload file");
		homepage.goToDocuments();
		siteExplorerHome.clickListView();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		//upload filename has a redundant space. Fx: abc .jpg
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
				
		
		/*Step number: 2
		*Step Name: Step 2:Share the file.
		*Step Description: 
			- Select this file.
			- Click on Share button.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/

		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);						
		
		/*Step number: 3
		*Step Name: Step 3:Check "Share with" dropdown list in Sharing Dialog.
		*Step Description: 
			- Click on "Share with" drop-down list.
			- Enter keyword in Filter textbox.
			- Search.
		*Input Data: 
			
		*Expected Outcome: 
			- Suggestion text in gray color: "Select a Space"
			- A Filter textbox displayed with suggestion text: "Filter".
			- All spaces that current user is member should be listed under Filter textbox.
			- Spaces contains the keyword are listed when Filtering by keyword.*/ 

		info("Check space list");
		click(siteExplorerHome.ELEMENT_SPACE_LIST);	
		//Check filter
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_SPACE_FILTER);
		//Check spaces
		waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName1));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName2));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName3));
		
		info("Filter");
		type(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_SPACE_FILTER, "ab", true);
		Utils.pause(3000);
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName1));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName2));
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName3));
		
		info("Filter again");
		type(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_SPACE_FILTER, "gh", true);
		Utils.pause(3000);
		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName1));
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName2));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}", spaceName3));
 	}

	/**
	*<li> Case ID:130846.</li>
	*<li> Test Case Name: Check Access drop-down list when sharer has "Edit" permission on file shared.</li>
	*<li> Pre-Condition: userA is member of Space1.userA has Edit permission on a file 'File1'.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckAccessDropdownListWhenSharerHasEditPermissionOnFileShared() {
		info("Test 4: Check Access drop-down list when sharer has Edit permission on file shared");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Access 'File1' where userA has Edit permission.
			- Click Share button in Action bar.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/


		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		String spaceName = "space130846";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
				
		info("User creates space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.goToCreateSpace();
		spaceManage.addNewSpaceSimple(spaceName, "");
						
		homepage.goToDocuments();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		//upload filename has a redundant space. Fx: abc .jpg
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
						
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
				
		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);			
		
		/*Step number: 2
		*Step Name: Step 2: Check Access drop-down list.
		*Step Description: 
			- Check Access drop-down list.
			- Click on Access drop-down list.
		*Input Data: 
			
		*Expected Outcome: 
			- A drop-down list with label: "Access: Space members"
			- Default value is "Can view".
			- There are 2 options: "Can view" and "Can edit".
			- Can select one of these 2 options.*/ 
		info("Check Access");
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", siteExplorerHome.SHARE_ACCESS_CAN_VIEW));
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", siteExplorerHome.SHARE_ACCESS_CAN_EDIT));	
		
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_VIEW, "");
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_EDIT, "");
		
		homepage.goToHomePage();
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName);		
 	}

	/**
	*<li> Case ID:130849.</li>
	*<li> Test Case Name: Check Sharing a file to a space.</li>
	*<li> Pre-Condition: userA is member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckSharingAFileToASpace() {
		info("Test 5: Check Sharing a file to a space");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Select this file and click Share button in Action bar.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/

		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		String spaceName = "space130849";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
				
		info("User creates space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.goToCreateSpace();
		spaceManage.addNewSpaceSimple(spaceName, "");
						
		homepage.goToDocuments();
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		//upload filename has a redundant space. Fx: abc .jpg
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
						
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));		
				
		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);				
		
		/*Step number: 2
		*Step Name: Step 2: Check File is shared to a space successfully.
		*Step Description: 
			- Select a space: Space1.
			- Put comment in Comment textbox.
			- Select "Can view" / "Can edit".
			- Click on Share button in Share Dialog.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1 successfully:
			-> Member of space can see activity in Intranet AS and Space AS.
			-> A symlink is added in Space Documents > Shared folder.*/ 
		
		siteExplorerHome.shareDocumentToSpaceWithAccessRight(spaceName, siteExplorerHome.SHARE_ACCESS_CAN_EDIT, "This is a comment");
		
		homepage.goToHomePage();
		siteExplorerHome.checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName);	
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);
 	}

	/**
	*<li> Case ID:131462.</li>
	*<li> Test Case Name: Check Access drop-down list when sharer has "View" permission only on file shared.</li>
	*<li> Pre-Condition: UserA is member of Space1.A source file 'File1' already existed. 
	userA has Access permission only on this file:
	-> In WCM, admin user configured so that normal users can access Collaborations in List View.
	-> Upload File1 to Intranet / Documents.
	-> Set Permission for File1 so that userA can Read only, other normal users can NOT access.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckAccessDropdownListWhenSharerHasViewPermissionOnlyOnFileShared() {
		info("Test 6: Check Access drop-down list when sharer has View permission only on file shared");
		/*Step Number: 1
		*Step Name: Step 1:Share file when sharer has only "View" permission on file shared.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer: Personal Documents.
			- Select 'File1' where userA has Access permission only.
			- Click on Share button in Action bar.Note:From Personal Documents drive, select Collaborations drive > Intranet > Documents.
		*Input Data: 
			
		*Expected Outcome: 
			- Share Dialog is displayed.*/

		info("Set permission");
		ContentAdministration contentAdministration  = new ContentAdministration(driver);
		
		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
				
		//Set permission to any
		String permissionToAdd = "*";
		contentAdministration.addPermissionToDrive("Collaboration", permissionToAdd);
		
		info("Set view type");
		specificView[] viewType = {specificView.LIST};			
		contentAdministration.addViewTypeToDrive("Collaboration", viewType);
		
		info("Upload file");
		homepage.goToDocuments();
		siteExplorerHome.goToPathOfDrive("/", "Collaboration");
		
		info("Upload file");
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
		siteExplorerHome.uploadFile("TestData/"+fileName);
		
		//upload filename has a redundant space. Fx: abc .jpg
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
				
		Utils.pause(3000);
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		
		info("Normal user logs in");
		manageLoginOut.signInWithoutRefresh(DATA_USER4,DATA_PASS);
		homepage.goToDocuments();
		siteExplorerHome.goToPathOfDrive("/", "Collaboration");
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	
		
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));	 
		
		info("Check the display of Share button.");
		click(siteExplorerHome.ELEMENT_DOCUMENT_FILE_NAME.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);		
		
		info("Check Share Dialog displayed");
		click(siteExplorerHome.ELEMENT_ACTIONBAR_SHARE);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT);				
		/*Step number: 2
		*Step Name: Step 2: Check Access drop-down list.
		*Step Description: 
			- Check Access drop-down list.
		*Input Data: 
			
		*Expected Outcome: 
			- Access drop-down list is NOT displayed.*/ 
		info("Check Access");
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", siteExplorerHome.SHARE_ACCESS_CAN_VIEW));
		waitForElementNotPresent(siteExplorerHome.ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION.replace("${option}", siteExplorerHome.SHARE_ACCESS_CAN_EDIT));
 	}
}