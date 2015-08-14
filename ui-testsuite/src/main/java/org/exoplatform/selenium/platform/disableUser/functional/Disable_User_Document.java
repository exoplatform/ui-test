package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.testng.annotations.*;


	public class Disable_User_Document extends Disable_User_TestConfig{
		public void createUser() {
			searchEmail = userSearchOptionData.getUserSearchOptionByIndex(3);
			membership = portMemPermisData.getContentByIndex(0);
            username = userInfoData.getUserNameByIndex(5)+getRandomString();
			password = userInfoData.getPassWordByIndex(5)+getRandomString();
			lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
			email = EMAIL_ADDRESS2;
			searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
			info("remove existed user with EMAIL_ADDRESS2");
			navToolBar.goToUsersAndGroupsManagement();
			info("Change disabled user status to ALL");
			userAndGroup.selectDisableStatus("All");
			userAndGroup.searchUser(EMAIL_ADDRESS2, searchEmail);
			if(isTextPresent(EMAIL_ADDRESS2))
			userAndGroup.deleteUser();
			
			info("Create new user");
			navToolBar.goToAddUser();
			addUserPage.addUser(username, password, email, username, lastName);
			navToolBar.goToUsersAndGroupsManagement();
	 	 	userAndGroup.goToGroupTab();
	 	 	userAndGroup.selectGroup("Platform/Content Management");
	 	 	userAndGroup.addUsersToGroup(username, membership, false, true);
	 	 	userAndGroup.selectGroup("Administration");
	 	 	userAndGroup.addUsersToGroup(username, membership, false, true);
	 	 	
		}
	/**
	*<li> Case ID:127992.</li>
	*<li> Test Case Name: Check disabled user in categories permission screen of Content administration.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDisabledUserInCategoriesPermissionScreenOfContentAdministration() {
		info("Test 1: Check disabled user in categories permission screen of Content administration");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Setting permission for category tree
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Content Administration 
			-
			-> Advanced 
			-
			-> Categories 
			-
			-> Add Category Tree
			- Input the name and Click next
			- Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		navToolBar.goToContentAdministration();
		caPage.checkUserSelectorOfCategory(cat,username,false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127987.</li>
	*<li> Test Case Name: Check disabled user in Permission of Document application.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128092.</li>
	*<li> Test Case Name: Check disabled user in Permission of Site Managerment drive.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckDisabledUserInPermissionOfDocumentApplication() {
		info("Test 2: Check disabled user in Permission of Document application");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Setting permission
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Documents > Permissions > Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		hp.goToDocuments();
		doubleClickOnElement(seHome.ELEMENT_FOLDERSELECTOR_PATH.replace("${path}","Documents"));
		seHome.checkUserSelectorECM(username,false);
		
		navToolBar.goToSiteExplorer();
		seHome.checkUserSelectorECM(username,false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:128094.</li>
	*<li> Test Case Name: Check disabled user in Permission of uploaded File.</li>
	*<li> Pre-Condition: User A is disabledUser B is adminUser B has already uploaded a file in Site Management drive</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDisabledUserInPermissionOfUploadedFile() {
		info("Test 4: Check disabled user in Permission of uploaded File");
		String file = attachFile.getAttachFileByArrayTypeRandom(1);
		createNewUser();
		disableUser();
		navToolBar.goToSiteExplorer();
		seHome.uploadFileWithDymanicPath(folderDataTestPath+ file,false);
		seHome.selectAFile(file);
		/*Step Number: 1
		*Step Name: Step 1: Setting permission
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Site Explorer 
			-
			-> Site Management drive 
			-
			-> Open uploaded file in pre-condition 
			-
			-> Choose Permissions on action bar 
			-
			-> Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		seHome.checkUserSelectorECM(username,false);
		
		seHome.deleteData(file);
		deleteUser();
 	}

	/**
	*<li> Case ID:127991.</li>
	*<li> Test Case Name: Check disabled user in tag permission screen of Content administration.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckDisabledUserInTagPermissionScreenOfContentAdministration() {
		info("Test 5: Check disabled user in tag permission screen of Content administration");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Open Tag Permission Manager
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Content Administration 
			-
			-> Explorer 
			-
			-> Tags 
			-
			-> Tag Permission Manager 
			-
			->Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		navToolBar.goToContentAdministration();
		caPage.checkUserSelectorOfTags(username,false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127990.</li>
	*<li> Test Case Name: Check disabled user in view permission screen of Content admin.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckDisabledUserInViewPermissionScreenOfContentAdmin() {
		info("Test 6: Check disabled user in view permission screen of Content admin");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Setting Permission in View Management
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Content Administration 
			-
			-> Explorer 
			-
			-> Views 
			-
			-> Edit View 
			-
			-> Permission 
			-
			-> Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		navToolBar.goToContentAdministration();
		caPage.checkUserSelectorOfView("List",username,false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127989.</li>
	*<li> Test Case Name: Check personal document of disabled user.</li>
	*<li> Pre-Condition: - User A is enable
	- User B is admin
	- User A has already uploaded some documents under Personal Document drive</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckPersonalDocumentOfDisabledUser() {
		info("Test 7: Check personal document of disabled user");
		String file = attachFile.getAttachFileByArrayTypeRandom(1);
		createNewUser();
		/*Step Number: 1
		*Step Name: Step 1: Open Personal Documents
		*Step Description: 
			- Connect to Intranet with User A
			- Open Document application
			- Open "Personal Documents" drive
		*Input Data: 
			
		*Expected Outcome: 
			- Files and Documents are displayed*/
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToDocuments();
		seHome.uploadFileWithDymanicPath(folderDataTestPath+ file,false);
		seHome.checkFileInPersonal(file, true);
		
		/*Step number: 2
		*Step Name: Step 2: Disable user
		*Step Description: 
			- Connect to Intranet with the User B
			- Disable the user A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is disabled*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		
		/*Step number: 3
		*Step Name: Step 3: Re-enable user
		*Step Description: 
			- Enable the User A again
		*Input Data: 
			
		*Expected Outcome: 
			- User A is re-enabled*/
		enableUser();
		
		/*Step number: 4
		*Step Name: Step 4: Check the displayed of uploaded documents after re-enabling user
		*Step Description: 
			- Connect to Intranet with User A
			- Open Document application
			- Open "Personal Documents" drive
		*Input Data: 
			
		*Expected Outcome: 
			- Files and Documents that are uploaded by User A are displayed*/ 
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToDocuments();
		seHome.checkFileInPersonal(file, true);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		deleteUser();
 	}

	/**
	*<li> Case ID:128093.</li>
	*<li> Test Case Name: Check uploaded document of disabled user in Site Explorer.</li>
	*<li> Pre-Condition: - User A is enable
	- User B is admin
	- User A has already uploaded some documents under Site Management drive</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckUploadedDocumentOfDisabledUserInSiteExplorer() {
		info("Test 8: Check uploaded document of disabled user in Site Explorer");
		String file = attachFile.getAttachFileByArrayTypeRandom(1);
		createUser();
		/*Step Number: 1
		*Step Name: Step 1: Open Personal Documents
		*Step Description: 
			- Connect to Intranet with User A
			- Open Site Explorer
			- Open "Site Management" drive
		*Input Data: 
			
		*Expected Outcome: 
			- Uploaded files and documents of User A are displayed*/
		magAc.signOut();
		magAc.signIn(username, password);
		navToolBar.goToSiteExplorer();
		seHome.uploadFileWithDymanicPath(folderDataTestPath+ file,false);
		seHome.checkFileInSE(file, true);
		
		/*Step number: 2
		*Step Name: Step 2: Disable user
		*Step Description: 
			- Connect to Intranet with the User B
			- Disable the user A
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disable*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		
		/*Step number: 3
		*Step Name: Step 3: Open Site Management drive
		*Step Description: 
			- Open Site Explorer
			- Open "Site Management" drive
		*Input Data: 
			
		*Expected Outcome: 
			- Files and Documents that are uploaded by User A are still remained with correct information
			- For example:+ In Web view: We can see the Author is User A+ In List view: We can see "Created on .... by User A"*/
		navToolBar.goToSiteExplorer();
		seHome.checkFileInSE(file, true);
		
		/*Step number: 4
		*Step Name: Step 4: Re-enable user
		*Step Description: 
			- Enable the User A again
			- Back to the User A session
			- Go to Site Explorer 
			-
			-> Site Management drive
		*Input Data: 
			
		*Expected Outcome: 
			- Files and Documents that are uploaded by User A are displayed with correct information
			- For example:+ In Web view: We can see the Author is User A+ In List view: We can see "Created on .... by User A"*/
		enableUser();
		
		/*Step number: 5
		*Step Name: Step 5: Check the displayed of uploaded documents after re-enabling user
		*Step Description: 
			- Connect to Intranet with User A
			- Open Site Explorer
			- Open "Site Management" drive
		*Input Data: 
			
		*Expected Outcome: 
			- Files and Documents that are uploaded by User A are displayed with correct information
			- For example:+ In Web view: We can see the Author is User A+ In List view: We can see "Created on .... by User A"*/ 
		magAc.signOut();
		magAc.signIn(username, password);
		navToolBar.goToSiteExplorer();
		seHome.checkFileInSE(file, true);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		deleteUser();
		navToolBar.goToSiteExplorer();
		seHome.deleteData(file);
 	}

	/**
	*<li> Case ID:127988.</li>
	*<li> Test Case Name: Check watch app in Content exoplorer app for a disabled user.</li>
	*<li> Pre-Condition: User A is disabledUser A has already a document watching</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckWatchAppInContentExoplorerAppForADisabledUser() {
		info("Test 9: Check watch app in Content exoplorer app for a disabled user");
		String name = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String contentMail = "The document "+name;
		createUser();
		navToolBar.goToSiteExplorer();
		seHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		magAc.signOut();
		magAc.signIn(username,password);
		navToolBar.goToSiteExplorer();
		seHome.selectAFile(name);
		seHome.watchDocument();
		
		/*Step Number: 1
		*Step Name: Step 1: Edit watched document
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Content explorer/Document, edit the document watched by the User A
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- The Document is edited*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		navToolBar.goToSiteExplorer();
		seHome.selectAFile(name);
		seHome.editDocument("",content2);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		/*Step number: 2
		*Step Name: Step 2: Check email inbox of disabled user
		*Step Description: 
			- Check mail Inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The User has not received a mail from watch app*/ 
		info("check mail");
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		checkEmailNotification(contentMail,false,false);
		switchToParentWindow();
		
		deleteUser();
		navToolBar.goToSiteExplorer();
		seHome.deleteData(name);
 	}}