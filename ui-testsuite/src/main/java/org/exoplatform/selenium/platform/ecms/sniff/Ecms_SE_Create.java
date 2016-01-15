package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Ecms_SE_Create extends ECMS_TestConfig_Part2{

	/**
	 *<li> Case ID:116569.</li>
	 *<li> Test Case Name: Create Content folder.</li>

	 *<li> Case ID:116645.</li>
	 *<li> Test Case Name: Delete Content folder.</li>
	 */

	@Test
	public  void test01_10_Create_DeleteContentFolder() {
		info("Test 1: Create Content folder");
        info("Create data test");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String folderType = "Content Folder";
		info("Finished creating data test");
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
		 *Step Name: -
		 *Step Description: 
			Step 1: Delete Content folder
		 *Input Data: 
			- Create a content folder
			- Right click on a folder and choose Delete 
			- Click Delete at confirmation
		 *Expected Outcome: 
			- Folder is deleted
			- A modal message appears with Undo option. You can click undo to restore*/ 
		info("Create a content folder");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.goToAddNewFolder();
		info("Create new file document");
		SEHome.createFolder(title, folderType);

		info("Test 10: Delete file document");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116572.</li>
	 *<li> Test Case Name: Delete File document.</li>

	 *<li> Case ID:116641.</li>
	 *<li> Test Case Name: Create File document.</li>

	 *<li> Case ID:116642.</li>
	 *<li> Test Case Name: Edit File document.</li>
	 */
	@Test
	public  void test02_06_07_Create_Edit_DeleteFileDocument() {
		info("Test 2: Delete File document");
		info("Create data test");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished creating data test");
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
		 *Step Name: -
		 *Step Description: 
			Step 1: Edit File document
		 *Input Data: 
			- Create a File
			- Click Edit on action bar, perform to edit it
			- Click Save & Close
		 *Expected Outcome: 
			File is Edited successfully*/ 
		info("Create a content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);

		info("Edit a content");
		SEHome.selectNode(name);
		SEHome.editDocument("",content2);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);

		info("Delete file document");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.deleteData(name);
	}

	/**
	 *<li> Case ID:116573.</li>
	 *<li> Test Case Name: Create Web Content document.</li>

	 *<li> Case ID:116643.</li>
	 *<li> Test Case Name: Edit Web Content document.</li>
	 *<li> Pre-Condition: A Web Content document is already created.</li>

	 *<li> Case ID:116644.</li>
	 *<li> Test Case Name: Delete Web Content document.</li>
	 *<li> Pre-Condition: A Web Content document is already created.</li>
	 */
	@Test
	public  void test03_08_09_Create_Edit_Delete_WebContentDocument() {
		info("Test 3: Create Web Content document");
		info("Create data test");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished creating data test");
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
		 *Step Name: Step 1: Edit Web Content document
		 *Step Description: 
			- Click Edit on action bar, perform to edit it
			- Click Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			The Web Content document is edited successfully*/ 
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);

		info("Edit the content");
		SEHome.selectNode(name);
		SEHome.editDocument("",content2);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);

		info("Delete file document");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.deleteData(name);
	}

	/**
	 *<li> Case ID:116579.</li>
	 *<li> Test Case Name: Insert documents/medias in a web content by FCK Content Selector.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_InsertDocumentsmediasInAWebContentByFCKContentSelector() {
		info("Test 4: Insert documents/medias in a web content by FCK Content Selector");
		info("Create data test");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = "exoplatform.com";
		info("Finished creating data test");
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
		 *Step Name: Step 1: Insert documents/medias in a web content by FCK Content Selector
		 *Step Description: 
			- Login acme by Admin
			- Select Administration 
			-
			-> Content 
			-
			-> Sites Explorer
			- Click New Content icon
			- Select template which allows rick text editor,i.e Web content.
			- In Main Content tab, clickInsert Content Link icon 
			- In FCK Content selector, choose any path where has contents, medias to select some content
			- Click Save & Close to switch to View mode
		 *Input Data: 

		 *Expected Outcome: 
			The selected content/media is inserted to Main Content as a link.In view mode, user can click this link to view/download file.*/ 
		info("Add a new content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(title, content);
		CreNewDoc.addLinkInWebContent(link);
		CreNewDoc.saveAndClose();
		
		
		String url = driver.getCurrentUrl();
		click(By.xpath("//*[contains(text(),'exoplatform.com')]"));
		info(url);
		driver.get(url);

	}

	/**
	 *<li> Case ID:116608.</li>
	 *<li> Test Case Name: Upload a file in Content explorer.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>

	 *<li> Case ID:116646.</li>
	 *<li> Test Case Name: Delete an uploaded file in content explorer.</li>
	 */
	@Test
	public  void test05_11_Upload_DeleteAFileInContentExplorer() {
		info("Test 5: Upload a file in Content explorer");
		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		info("Finished creating data test");
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
		 *Step Name: -
		 *Step Description: 
			Step 1: Open form to upload
		 *Input Data: 
			- Navigate Administration
			-> content 
			-> content explorer
			- Click on Upload icon on action bar or right click on the main pane then click Upload Files
		 *Expected Outcome: 
			- File Dialog open for user to choose files to upload*/
        info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Site Management");
		SEHome.uploadFile("TestData/"+file,true);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Upload files
		 *Input Data: 
			- Choose file you want to uploadYou can select many file by Press Control/ Shift then click files
			- Click Open
		 *Expected Outcome: 
			- Progress bar appears at the bottomUser can: + Cancel 1 file + Abort all + View tooltip of file size + Mouse over to see containing folder
			- Files are uploaded successfully*/ 
		
		info("Delete a file");
		SEHome.deleteData(file);
	}

	/**
	 *<li> Case ID:116676.</li>
	 *<li> Test Case Name: Upload a file in Intranet/Document.</li>

	 *<li> Case ID:116679.</li>
	 *<li> Test Case Name: Delete an uploaded file in intranet/Document.</li>
	 *<li> Pre-Condition: some uploaded files exist</li>
	 */
	@Test
	public  void test12_15_Upload_DeleteAFileInIntranetDocument() {
		info("Test 12 Upload a file in Intranet/Document");
		info("Create data test");
		String fileName = fData.getAttachFileByArrayTypeRandom(1);
		info("Finished creating data test");
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
		 *Step Name: -
		 *Step Description: 
			Step 1: Open form to upload
		 *Input Data: 
			- Login and goto intranet
			- Click Document in left Navigation
			- Click on Upload icon on action bar or right click on the main pane then click Upload Files
		 *Expected Outcome: 
			- File Dialog open for user to choose files to upload*/
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Site Management");
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Upload files
		 *Input Data: 
			- Choose file you want to uploadYou can select many file by Press Control/ Shift then click files
			- Click Open
		 *Expected Outcome: 
			- Progress bar appears at the bottomUser can: + Cancel 1 file + Abort all + View tooltip of file size + Mouse over to see containing folder
			- Files are uploaded successfully*/ 
		info("Upload a file");
		SEHome.uploadFile("TestData/"+fileName);
		waitForAndGetElement(SEHome.ELEMENT_GRID_LIST_CONTENT.replace("${file}",fileName));
		info("Delete the file");
		SEHome.deleteFile(fileName);
		
	}

	/**
	 *<li> Case ID:116677.</li>
	 *<li> Test Case Name: Upload a file in Space/Document.</li>
	 *<li> Pre-Condition: - a Space exists</li>
	 *<li> Post-Condition: </li>

	 *<li> Case ID:116678.</li>
	 *<li> Test Case Name: Delete an uploaded file in Space/Document.</li>
	 *<li> Pre-Condition: - A space exists, and some files is upload in Space/Document</li>
	 */
	@Test
	public  void test13_14_Upload_DeleteAFileInSpaceDocument() {
		info("Test 13 Upload a file in Space/Document");
		info("Create data test");
		String fileName = fData.getAttachFileByArrayTypeRandom(1);
		String spaceName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished creating data test");
		hp.goToMySpaces();
		spManag.addNewSpaceSimple(spaceName, content);
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
		 *Step Name: -
		 *Step Description: 
			Step 1: Open form to upload
		 *Input Data: 
			- Log in and access a space
			- Click Document Space application list
			- Click on Upload icon on action bar or right click on the main pane then click Upload Files
		 *Expected Outcome: 
			- File Dialog open for user to choose files to upload*/
	    
		navTool.goToSiteExplorer();
		SEHome.goToSpace(spaceName);
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Upload files
		 *Input Data: 
			- Choose file you want to uploadYou can select many file by Press Control/ Shift then click files
			- Click Open
		 *Expected Outcome: 
			- Progress bar appears at the bottomUser can: + Cancel 1 file + Abort all + View tooltip of file size + Mouse over to see containing folder
			- Files are uploaded successfully*/ 

		SEHome.uploadFile("TestData/"+fileName);
		click(SEHome.ELEMENT_ADDRESS_BAR_ICON_VIEW);
		click(SEHome.ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		waitForAndGetElement(SEHome.ELEMENT_SPACE_DRIVE_NODE_TREE_FILE.replace("${file}",fileName));
		info("Delete the file");
		SEHome.deleteData(fileName);
		
		
	}
}