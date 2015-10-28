package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class ShareInContentExplorer_Symlink extends PlatformBase {

		ManageLogInOut manageLoginOut;
		
		SiteExplorerHome siteExplorerHome;
		AttachmentFileDatabase attFileData;
		HomePagePlatform homepage;
		
		SpaceHomePage spaceHome;
						
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
	*<li> Case ID:130857.</li>
	*<li> Test Case Name: Check symlink added in Shared folder when file shared in Content Explorer.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckSymlinkAddedInSharedFolderWhenFileSharedInContentExplorer() {
		info("Test 1: Check symlink added in Shared folder when file shared in Content Explorer");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as any user.
			- Go to Personal Documents / Space Documents / Site Explorer.
			- Add/Upload a file.
			- Share this file in Content Explorer.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to space.*/

		String spaceName = "space130857";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
		
		//Create space with 2 members
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("Upload file and share to space");	
		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		siteExplorerHome.uploadAndShareDocumentToSpace(fileName, spaceName,"");
		manageLoginOut.signOut();	
		
		/*Step number: 2
		*Step Name: Step 2: Check symlink of file is added in Shared folder.
		*Step Description: 
			- Log in by member of space.
			- Go to Space Documents > Shared folder.
		*Input Data: 
			
		*Expected Outcome: 
			- A symlink of file shared is added in this folder.*/ 

		info("Check symlink");	
		manageLoginOut.signInWithoutRefresh(DATA_USER2,DATA_PASS);
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName);
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);		
 	}

	/**
	*<li> Case ID:130858.</li>
	*<li> Test Case Name: Check Shared folder created If it does not exist while file is shared.</li>
	*<li> Pre-Condition: userA and userB are members of Space1.Shared folder is NOT existed in Space1 Documents.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckSharedFolderCreatedIfItDoesNotExistWhileFileIsShared() {
		info("Test 2: Check Shared folder created If it does not exist while file is shared");
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

		String spaceName = "space130858";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
		
		//Create space with 2 members
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("Upload file and share to space");	
		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
	
		info("Check Shared folder not exist in space");	
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName);
		siteExplorerHome.waitForElementNotPresent(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		
		siteExplorerHome.uploadAndShareDocumentToSpace(fileName, spaceName, "");
		manageLoginOut.signOut();			
		
		/*Step number: 2
		*Step Name: Step 2: Check symlink of file is added in Shared folder.
		*Step Description: 
			- Log in by userB, member of Space1.
			- Go to Space1 Documents.
			- Open Shared folder.
		*Input Data: 
			
		*Expected Outcome: 
			- Shared folder is created under Space1 Documents.
			- A symlink of file shared is added in Shared folder.*/ 
		info("Check symlink");	
		manageLoginOut.signInWithoutRefresh(DATA_USER2,DATA_PASS);
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName);		
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);	
 	}

	/**
	*<li> Case ID:130859.</li>
	*<li> Test Case Name: Check symlink and AS are deleted when source file is deleted.</li>
	*<li> Pre-Condition: Shared folder is NOT existed in Space Documents.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckSymlinkAndASAreDeletedWhenSourceFileIsDeleted() {
		info("Test 3: Check symlink and AS are deleted when source file is deleted");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as any user.
			- Go to Personal Documents / Space Documents / Site Explorer.
			- Add/Upload a file.
			- Share this file in Content Explorer.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to space.*/


		String spaceName = "space130859";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);
		
		//Create space with 2 members
		siteExplorerHome.initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
		
		info("Upload file and share to space");	
		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
	
		info("Check Shared folder not exist in space");	
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName);
		siteExplorerHome.waitForElementNotPresent(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		
		siteExplorerHome.uploadAndShareDocumentToSpace(fileName, spaceName, "");
		manageLoginOut.signOut();				
		
		/*Step number: 2
		*Step Name: Step 2: Check symlink of file is added in Shared folder.
		*Step Description: 
			- Log in by member of space.
			- Go to Space Documents.
		*Input Data: 
			
		*Expected Outcome: 
			- Shared folder is created.
			- A symlink of file shared is added in this folder.*/

		info("Check symlink");	
		manageLoginOut.signInWithoutRefresh(DATA_USER2,DATA_PASS);
		homepage.goToMySpaces();
		homepage.goToSpecificSpace(spaceName);		
		siteExplorerHome.checkSharedFileSymlink(fileName, spaceName);			
		manageLoginOut.signOut();
		
		/*Step number: 3
		*Step Name: Step 3:Delete the file.
		*Step Description: 
			- Log in by user who has permission to delete the file.
			- Remove the file shared.
		*Input Data: 
			
		*Expected Outcome: 
			- File is removed.*/

		manageLoginOut.signInWithoutRefresh(DATA_USER1,DATA_PASS);
		Utils.pause(3000);
		siteExplorerHome.deleteSymlink(spaceName, fileName);
		
		/*Step number: 4
		*Step Name: Step 4:Check symlink and activity is removed.
		*Step Description: 
			- Log in by member of space.
			- Go to Space Documents > Shared folder.
			- Go to AS.
		*Input Data: 
			
		*Expected Outcome: 
			- Symlink of shared file is deleted.
			- Activity of shared file is deleted.*/ 
		manageLoginOut.signInWithoutRefresh(DATA_USER2,DATA_PASS);
		Utils.pause(3000);
		info("Activities are deleted");
		siteExplorerHome.checkShareActivityAfterDeleted(spaceName, true);
		info("Symlink is deleted");
		siteExplorerHome.checkSharedFileSymlinkAfterDeleted(fileName, spaceName);
 	}
}