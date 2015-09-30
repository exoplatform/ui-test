package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.Test;

	public class ShareInContentExplorer_ActivityStream extends ECMS_TestConfig{

	/**
	*<li> Case ID:130860.</li>
	*<li> Test Case Name: Check the display of Author in AS when sharing a file in CE.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckTheDisplayOfAuthorInASWhenSharingAFileInCE() {
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared successfully.*/

		info("Test 1: Check the display of Author in AS when sharing a file in CE");
		String spaceName = "space130860";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);		
		
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("User A login");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of activity
		*Step Description: 
			- Log in by userB, member of space.
			- Go to AS to check Activity.
			- Check Author Name of activity.Refer to image in Attachment.
		*Input Data: 
			
		*Expected Outcome: 
			- Author Name displayed with full name followed by a label "shared a document".
			- Generic User popup displayed while mouse hover.
			- User Profile is opened when Author Name is clicked.
			- Space Name is displayed in activity and opened when space name is clicked.*/ 

		info("Check the display of activity");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		
		//Check Shared content
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", DATA_NAME_USER1).replace("${spaceName}", spaceName));
		
		//User popup
		mouseOver(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_AUTHOR.replace("${author}", DATA_NAME_USER1), true);
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_POPUP.replace("${author}", DATA_NAME_USER1));
		
		//Open User Profile 
		click(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_AUTHOR.replace("${author}", DATA_NAME_USER1));	
		waitForAndGetElement(siteExplorerHome.ELEMENT_PROFILE_NAME.replace("${author}", DATA_NAME_USER1));
		
		//Back to home page
		homepage.goToHomePage();
		//Open space displayed in shared document
		click(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", DATA_NAME_USER1).replace("${spaceName}", spaceName));
		waitForAndGetElement(spaceLocator.ELEMENT_SPACE_NAME.replace("${name}", spaceName));
		
		info("End test01_CheckTheDisplayOfAuthorInASWhenSharingAFileInCE");
 	}

	/**
	*<li> Case ID:130861.</li>
	*<li> Test Case Name: Check Activity message displayed when sharing a file in CE.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckActivityMessageDisplayedWhenSharingAFileInCE() {
		info("Test 2: Check Activity message displayed when sharing a file in CE");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1: Enter text message in Comment field in Share Dialog.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared with text message successfully.*/
		
		String spaceName = "space130861";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
		String comment = "Message of Test 2";
				
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("User A login");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, comment);
		manageLoginOut.signOut();		
		
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of activity
		*Step Description: 
			- Log in by userB, member of space.
			- Go to AS to check Activity.
		*Input Data: 
			
		*Expected Outcome: 
			- The activity about sharing file is displayed in Intranet AS and Space AS.
			- The activity is displayed with activity message under Author section.*/ 

		info("Check the display of activity");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		
		//Check Shared content in Intranet AS
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_COMMENT.
								replace("${author}", DATA_NAME_USER1).replace("${spaceName}", spaceName).
								replace("${comment}", comment));
		
		//Open space displayed in shared document
		click(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", DATA_NAME_USER1).replace("${spaceName}", spaceName));
		waitForAndGetElement(spaceLocator.ELEMENT_SPACE_NAME.replace("${name}", spaceName));
		//Check Shared content in Space AS
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_COMMENT_IN_SPACE.
												replace("${author}", DATA_NAME_USER1).replace("${comment}", comment));
		
		info("End Test 2: Check Activity message displayed when sharing a file in CE");		
 	}

	/**
	*<li> Case ID:130864.</li>
	*<li> Test Case Name: Check Document name displayed with link in AS.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDocumentNameDisplayedWithLinkInAS() {
		info("Test 3: Check Document name displayed with link in AS");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared successfully.*/

		String spaceName = "space130864";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
						
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("User A login");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();		
					
		/*Step number: 2
		*Step Name: Step 2: Check the display of activity
		*Step Description: 
			- Log in by userB, member of space.
			- Go to AS to check Activity.Refer to image in Attachment.
		*Input Data: 
			
		*Expected Outcome: 
			- The document is displayed with correct icon, filename, file size.
			- File name is displayed as a link.Note:
			- File icon is displayed as thumbnail if the file type is supported (ex image, PDF...).
			- File can be previewed/played in thumbnail if file type is supported (ex movie).
			- When Filename is clicked: File is opened in Office if Open In Office feature is supported.
			- When Filename is clicked: File is re
			-directed to Content Explorer if Open In Office is NOT supported.*/ 

		info("Check the display of activity");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		
		//Check fileName
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_FILE_NAME.
								replace("${author}", DATA_NAME_USER1).replace("${fileName}", fileName));
		
		//Check file icon
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_ICON.
									replace("${author}", DATA_NAME_USER1).replace("${fileName}", fileName));
		//Open file
		click(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_ICON.
				replace("${author}", DATA_NAME_USER1).replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_FILE_PREVIEW);
		
		info("End Test 3: Check Document name displayed with link in AS");				
		
 	}

	/**
	*<li> Case ID:130865.</li>
	*<li> Test Case Name: Check Activity deleted in AS when symlink is deleted.</li>
	*<li> Pre-Condition: UserA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckActivityDeletedInASWhenSymlinkIsDeleted() {
		info("Test 4: Check Activity deleted in AS when symlink is deleted");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130865";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
						
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("User A login");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();		
		
		
		/*Step number: 2
		*Step Name: Step 2: Sharing file is displayed in AS.
		*Step Description: 
			- Log in by userB, member of space.
			- Go to Space1's Documents.
		*Input Data: 
			
		*Expected Outcome: 
			- A symlink of file shared is added in Shared folder.
			- Activity about sharing file is displayed in Intranet AS, Space AS.*/

		info("Check the display of activity");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		

		checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName);
		checkSharedFileSymlink(fileName,spaceName);
			
		manageLoginOut.signOut();
		
		/*Step number: 3
		*Step Name: Step 3:Remove symlink in Shared folder.
		*Step Description: 
			- Log in as userA.
			- Access Space > Shared folder.
			- Remove the symlink.
		*Input Data: 
			
		*Expected Outcome: 
			- Symlink is removed from Shared folder.*/
		info("Remove symlink in Shared folder");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);		
		homepage.goToSpecificSpace(spaceName);
		
		spaceManage.goToDocumentTab();
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Open shared folder
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Find the shared file
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));
		//Delete symlink
		rightClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		//confirm
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
																.replace("${spaceName}", spaceName)
																.replace("${fileName}", fileName));
		manageLoginOut.signOut();
		info("Remove symlink successfully");
		
		/*Step number: 4
		*Step Name: Step 4:Check activity of sharing file in AS.
		*Step Description: 
			- Log in by userB.
			- Check Intranet AS.
			- Check Space AS.
		*Input Data: 
			
		*Expected Outcome: 
			- Activity of shared file is deleted.*/ 
		
		info("Check activity of sharing file in AS");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);	
		
		checkShareActivityAfterDeleted(spaceName, true);
		
		info("Test 4: Check Activity deleted in AS when symlink is deleted");
 	}

	/**
	*<li> Case ID:130866.</li>
	*<li> Test Case Name: Check Activities deleted in AS when Shared folder is deleted.</li>
	*<li> Pre-Condition: userA and userB are members of Space1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckActivitiesDeletedInASWhenSharedFolderIsDeleted() {
		info("Test 5: Check Activities deleted in AS when Shared folder is deleted");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload some files.
			- Share these files to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- Files are shared to Space1.*/
		String spaceName = "space130866";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
						
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("User A login");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();		
		
		
		/*Step number: 2
		*Step Name: Step 2: Sharing file is displayed in AS.
		*Step Description: 
			- Log in as userB, member of space.
			- Go to Space Documents.
		*Input Data: 
			
		*Expected Outcome: 
			- Symlinks of files shared are added in Shared folder.
			- Activities about sharing files are displayed in Intranet AS, Space AS.*/

		info("Sharing file is displayed in AS");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		

		checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName);
		checkSharedFileSymlink(fileName,spaceName);
		
		manageLoginOut.signOut();		
		
		/*Step number: 3
		*Step Name: Step 3:Remove Shared folder.
		*Step Description: 
			- Log in as userA.
			- Access Space > Space Documents.
			- Remove Shared folder.
		*Input Data: 
			
		*Expected Outcome: 
			- Shared folder is deleted.*/

		info("Remove Shared folder");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);		
		homepage.goToSpecificSpace(spaceName);
		
		spaceManage.goToDocumentTab();
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		
		//Delete Shared folder
		rightClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		//confirm
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION.replace("${action}", "Delete"));		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		
		manageLoginOut.signOut();
		info("Remove symlink successfully");		
		
		/*Step number: 4
		*Step Name: Step 4:Check activities of sharing file in AS.
		*Step Description: 
			- Log in as userB.
			- Check Intranet AS.
			- Check Space AS.
		*Input Data: 
			
		*Expected Outcome: 
			- Activities of shared files are deleted.*/ 
		info("Check activity of sharing file in AS");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);	
		
		checkShareActivityAfterDeleted(spaceName, true);
		
		info("Test 5: Check Activities deleted in AS when Shared folder is deleted");
 	}

	/**
	*<li> Case ID:130906.</li>
	*<li> Test Case Name: Check Activity is removed when Space is deleted.</li>
	*<li> Pre-Condition: UserA is manager of Space1.UserB is member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckActivityIsRemovedWhenSpaceIsDeleted() {
		info("Test 6: Check Activity is removed when Space is deleted");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/
		String spaceName = "space130906";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);	
						
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("User A login");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();	
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of Activity about File Shared.
		*Step Description: 
			- Log in by userB, member of space.
			- Check Intranet AS and Space AS.
		*Input Data: 
			
		*Expected Outcome: 
			- The activity of shared file is displayed.*/

		info("Check the display of Activity about File Shared");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		

		checkDisplayOfSharedDocument(DATA_NAME_USER1, fileName, spaceName);
				
		manageLoginOut.signOut();			
		
		/*Step number: 3
		*Step Name: Step 3:Remove Space.
		*Step Description: 
			- Log in byuserA, Space Manager.
			- Remove space.
		*Input Data: 
			
		*Expected Outcome: 
			- Space is removed.*/

		info("Remove Space");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);		
		homepage.goToAllSpace();
		spaceManage.deleteSpace(spaceName, false);
				
		manageLoginOut.signOut();					
		
		/*Step number: 4
		*Step Name: Step 4: Check the display of Activity about File Shared.
		*Step Description: 
			- Log in by userB.
			- Check the display of Activity about File Shared.
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is not displayed any more.*/ 
		info("Check the display of Activity about File Shared");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		

		checkShareActivityAfterDeleted(spaceName, false);
				
		info("End Test 6: Check Activity is removed when Space is deleted");
 	}
	
	
	/*
	 * Activity about shared document is displayed in Intranet Activity Stream & Space Activity Stream
	 */
	private void checkDisplayOfSharedDocument(String shareOwner, String fileName, String spaceName){
		//Check Shared content in Intranet AS
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT
																.replace("${author}", shareOwner)
																.replace("${spaceName}", spaceName));
		
		//Open space displayed in shared document
		click(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT.replace("${author}", shareOwner).replace("${spaceName}", spaceName));
		waitForAndGetElement(spaceLocator.ELEMENT_SPACE_NAME.replace("${name}", spaceName));
		//Check Shared content in Space AS
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE.replace("${author}", shareOwner));		
	} 	
	
	private void checkSharedFileSymlink( String fileName, String spaceName){
		//Go to Documents of Space
		spaceManage.goToDocumentTab();
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Open shared folder
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		//Find the shared file
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
												.replace("${spaceName}", spaceName)
												.replace("${fileName}", fileName));				
	}
	
	private void checkShareActivityAfterDeleted(String spaceName, boolean verifyInSpace){		
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT
														.replace("${author}", DATA_NAME_USER1)
														.replace("${spaceName}", spaceName));
		if(verifyInSpace){
			homepage.goToSpecificSpace(spaceName);
			spaceManage.goToActivityStreamTab();
			waitForElementNotPresent(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE.replace("${author}", DATA_NAME_USER1));
		}	
	}
}