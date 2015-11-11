package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.publishactivities;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * By   PhuongDT
 * Date 16/09/2013
 * Update by QuynhPT
 * Date 23/10/2014
 */
public class ECMS_SE_PublishActivities_FilesActivities  extends PlatformBase {
	//Platform
	ManageAccount magAcc;
	ActionBar actBar;
	NavigationToolbar navToolBar;
	ManageMember magMember;
	HomePageActivity activity;

	//Ecms
	EcmsBase ecms;
	ContentTemplate cTemplate;
	SitesExplorer siteExp;
	ContextMenu cMenu;
	Button btn;

	//Variables
	String folder =null;
	String num_ran=null;
	String spacename=null ;
	String spacedesc=null;

	@BeforeTest
	public void beforeTest() {
		initSeleniumTest();
		//initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		magAcc = new ManageAccount(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		cMenu= new ContextMenu(driver, this.plfVersion);
		btn = new Button(driver, this.plfVersion);
		activity = new HomePageActivity(driver, this.plfVersion);
		magMember = new ManageMember(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterTest
	public void afterTest() {
		info("Logout ECMS");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod(){
		num_ran=getRandomNumber();
		folder = "folder_"+num_ran;
		spacename = "Space"+num_ran;
		spacedesc = "Description Of Space_"+num_ran;
	}


	/**
	 * == Add File activity after uploading a file in a space ==
	 * Test case ID: 119397
	 * Steps: 	    - Connect to Intranet
				    - Add new space
				    - Open Documents in this space
				    - Upload a file
				    - Back to the Home page
	 * Expectation: - A File activity is added to the activity stream
	 */
	@Test
	public void test01_AddFileActivityAfterUploadingAFileInASpace(){
		//Declare variable
		String file = "ECMS_DMS_SE_Upload_imgfile.jpg";

		//Add new space
		magMember.goToMySpacePage();
		magMember.addNewSpace(spacename, spacedesc);

		//Open Documents in this space
		waitForAndGetElement(magMember.ELEMENT_DOCUMENTS_TAB);
		click(magMember.ELEMENT_DOCUMENTS_TAB);

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- A File activity is added to the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "423 KB", "", "", "", "");

		/*Clear data*/
		info ("-- Clear data --");
		magMember.goToMySpacePage();
		magMember.deleteSpace(spacename);
	}

	/**
	 * == Add File activity after uploading a file in CE ==
	 * Test case ID: 119398
	 * Steps:       - Connect to Intranet
	 			    - Go to Sites explorer (except personal drive)
				    - Upload a file
				    - Back to the Home page
	 * Expectation: - A File activity is added to the activity stream
	 */
	@Test
	public void test02_AddFileActivityAfterUploadingAFileInCE(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file));

		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file: image, pdf or office document (except personal document drive)
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Clear data*/
		info ("-- Clear data --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile);
	}


	/**
	 * == Not update File activity after creating a new folder ==
	 * Test case ID: 119443
	 * Step 1: 	    - Connect to Intranet
				    - Open Sites Explorer 
				    - Create a new folder
				    - Back to the Home page
	 * Expectation: - No activity is displayed in the activity stream
	 */
	@Test
	public void test03_NotUpdateFileActivityAfterCreatingANewFolder(){
		//Declare variable
		By elementFolder = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", folder));

		//Open Sites Explorer
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		//Create a new folder
		info("-- Create parent folder --");
		cTemplate.createNewFolder(folder, folderType.None);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//No activity is displayed in the activity stream
		info("-- Check activity after adding a folder --");
		waitForElementNotPresent(activity.ELEMENT_CONTENT_NAME.replace("@{fileName}", folder));

		/*Clear data*/
		info ("-- Clear data --");
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementFolder);	
	}


	/**
	 * == Displaying of file format (image, pdf, office) in the File activity (1) ==
	 * Test case ID: 119444
	 * Steps: 	    - Connect to Intranet
				    - Go to SE and  Upload a file: image, pdf or office document (except personal document drive)
				    - Go back intranet home page
	 * Expectation: - The File activity is displayed in the activity stream
                    - A preview of the file is displayed (only first page for documents)

		PENDING: check  preview of the file is displayed (only first page for documents)
		Refer: https://jira.exoplatform.org/browse/FQA-1250
	 */
	@Test (groups={"pending"})
	public void test04_DisplayingOfFileFormatImagePdfOfficeInTheFileActivity1(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file));

		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file: image, pdf or office document (except personal document drive)
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "1 MB", "", "", "", "");

		//- A preview of the file is displayed (only first page for documents)

		/*Clear data*/
		info ("-- Clear data --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile);	
	}

	/**
	 * == Display the content of the File activity ==
	 * Test case ID: 119445
	 * Steps: 	- Connect to Intranet 
                - Go to CE and upload a file (except personal document drive)
                - Go back intranet home page
	 * Expectation: - The File activity is displayed in the activity stream with following informations:
					  + File's title if exist, or file's name instead
   					  + File description if exist
					  + Version (if exist) and file size
	 */
	@Test
	public void test05_DisplayTheContentOfTheFileActivity(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file));
		String title = "Title of uploaded file";
		String desc = "Description of uploaded file";

		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file: image, pdf or office document (except personal document drive)
		ecms.uploadFile("TestData/"+file);

		//File's title if exist, or file's name instead
		//File description if exist
		info("Edit title of uploaded file");
		navToolBar.goToSiteExplorer();
		if(isElementNotPresent(elementfile) && isElementPresent(By.xpath(actBar.ELEMENT_VIEW_MODE_LINK.replace("${viewName}", "Web")))){
			actBar.goToViewMode("Web");
			click(siteExp.ELEMENT_SIDEBAR_FILE_EXPLORER);
		}
		ecms.goToNode(elementfile);
		actBar.goToEditDocument(file);
		ecms.editUploadedFile(file, "", title, desc, "", "");

		//Version (if exist) and file size
		actBar.publishDocument();

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "1", desc, "");
		activity.checkTitleAfterEditing(file, title);

		/*Clear data*/
		info ("-- Clear data --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile);	
	}

	/**
	 * == Edit a file from the File activity from intranet home page ==
	 * Test case ID: 119446
	 * Step 1: Create file activity
	 * Step 2: Edit file from activity stream
	 */
	@Test
	public void test06_EditAFileFromTheFileActivityFromIntranetHomePage(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file));

		/*Step 1: Create file activity*/
		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Step 2: Edit file from activity stream*/
		//From the file activity, click on the link "Edit"
		info("Click Edit icon in activity");
		activity.goToEditFromContentActivity(file);
		//assert getValue(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX).contains(file);
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_NAME.replace("${nameContent}", file));
		activity.backToHomePageFromEditContentScreen();

		/*Clear data*/
		info ("-- Clear data --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile);	
	}

	/**
	 * == Remove the File activity after deleting a file in Content explorer ==
	 * Test case ID: 119447
	 * Step 1: Create file activity
	 * Step 2: Delete File
	 */
	@Test
	public void test07_RemoveTheFileActivityAfterDeletingAFileInContentExplorer(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file));

		/*Step 1: Create file activity*/
		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Step 2: Delete File*/
		//Delete the file
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile);

		//Back to the Homepage
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is removed from the activity stream
		info("-- The File activity is removed from the activity stream --");
		waitForElementNotPresent(activity.ELEMENT_CONTENT_NAME.replace("@{fileName}", file));
	}

	/**
	 * == Delete a File activity from intranet activity stream by owner ==
	 * Test case ID: 119448
	 * Step 1: Create file activity
	 * Step 2: See delete activity icon
	 * Step 3: Delete activity
	 */
	@Test
	public void test08_DeleteAFileActivityFromIntranetActivityStreamByOwner(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file));

		/*Step 1: Create file activity*/
		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Step 2: See delete activity icon*/
		/*Step 3: Delete activity*/
		activity.deleteActivity(file);

		//The File activity is removed from the activity stream
		info("-- The File activity is removed from the activity stream --");
		waitForElementNotPresent(activity.ELEMENT_CONTENT_NAME.replace("@{fileName}", file));

		/*Clear data*/
		info ("-- Clear data --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile);
	}

	/**
	 * == Displaying of file format (image, pdf, office) in the File activity (2) ==
	 * Test case ID: 119449
	 * Step 1: 	- Connect to Intranet
				- Go to CE and  Upload a file: image, pdf or office document (except personal document drive)
				- Go back intranet home page
		Verify by comparison width heigh and width: The orientation of the file can be portrait or landscape following its original orientation
	 */
	@Test
	public void test09_DisplayingOfFileFormatImagePdfOfficeInTheFileActivity2(){
		//Declare variable
		String file1 = "portrait08.jpg";
		String file2 = "landscape08.jpg";
		By elementfile1 = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file1));
		By elementfile2 = By.xpath(siteExp.ELEMENT_SE_NODE.replace("{$node}", file2));

		//Go to SE
		navToolBar.goToSiteExplorer();

		//Upload a file: image, pdf or office document (except personal document drive)
		ecms.uploadFile("TestData/"+file1);
		ecms.uploadFile("TestData/"+file2);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file1, "", "File", "", "", "", "", "");
		activity.checkInforAfterAddingDocument(file2, "", "File", "", "", "", "", "");

		//- The orientation of the file can be portrait or landscape following its original orientation
		WebElement element = waitForAndGetElement(By.xpath(ELEMENT_GET_URL_IMAGE.replace("${name}", file1)));
		String src = element.getAttribute("src"); 
		driver.navigate().to(src);
		WebElement image = waitForAndGetElement(By.xpath(ELEMENT_GET_URL_IMAGE.replace("${name}", src)));
		int height = image.getSize().getHeight();
		assert(height==300);

		driver.navigate().back();
		element = waitForAndGetElement(By.xpath(ELEMENT_GET_URL_IMAGE.replace("${name}", file2)));
		src = element.getAttribute("src"); 
		driver.navigate().to(src);
		image = waitForAndGetElement(By.xpath(ELEMENT_GET_URL_IMAGE.replace("${name}", src)));
		height = image.getSize().getHeight();
		assert(height==89);
		driver.navigate().back();

		/*Clear data*/
		info ("-- Clear data --");
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(elementfile1);
		cMenu.deleteData(elementfile2);
	}

	/**
	 * == Delete a File activity from space activity stream by owner ==
	 * Test case ID: 119450
	 * Step 1: Create file activity
	 * Step 2: See delete activity icon
	 * Step 3: Delete activity
	 */
	@Test
	public void test10_DeleteAFileActivityFromSpaceActivityStreamByOwner(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";

		/*Step 1: Create file activity*/
		//Add new space
		magMember.goToMySpacePage();
		magMember.addNewSpace(spacename, spacedesc);

		//Open Documents in this space
		waitForAndGetElement(magMember.ELEMENT_DOCUMENTS_TAB);
		click(magMember.ELEMENT_DOCUMENTS_TAB);

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Step 2: See delete activity icon*/
		/*Step 3: Delete activity*/
		activity.deleteActivity(file);

		//The File activity is removed from the activity stream
		info("-- The File activity is removed from the activity stream --");
		waitForElementNotPresent(activity.ELEMENT_CONTENT_NAME.replace("@{fileName}", file));

		/*Clear data*/
		info ("-- Clear data --");
		magMember.goToMySpacePage();
		magMember.deleteSpace(spacename);
	}

	/**
	 * == Remove the File activity after deleting a file in Space document ==
	 * Test case ID: 119451
	 * Step 1: Create file activity
	 * Step 2: Delete File
	 */
	@Test
	public void test11_RemoveTheFileActivityAfterDeletingAFileInSpaceDocument(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";
		By elementfile = By.xpath(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}", file));

		/*Step 1: Create file activity*/
		//Add new space
		magMember.goToMySpacePage();
		magMember.addNewSpace(spacename, spacedesc);

		//Open Documents in this space
		waitForAndGetElement(magMember.ELEMENT_DOCUMENTS_TAB);
		click(magMember.ELEMENT_DOCUMENTS_TAB);

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Step 2: Delete File*/
		//Delete file from space
		magMember.goToMySpacePage();
		magMember.doAction("Edit", spacename);
		waitForAndGetElement(magMember.ELEMENT_DOCUMENTS_TAB);
		click(magMember.ELEMENT_DOCUMENTS_TAB);
		cMenu.deleteData(elementfile);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//The File activity is removed from the activity stream
		info("-- The File activity is removed from the activity stream --");
		waitForElementNotPresent(activity.ELEMENT_CONTENT_NAME.replace("@{fileName}", file));

		/*Clear data*/
		info ("-- Clear data --");
		magMember.goToMySpacePage();
		magMember.deleteSpace(spacename);
	}


	/**
	 * == Edit a file from the File activity from space ==
	 * Test case ID: 119452
	 * Step 1: Create file activity
	 * Step 2: Edit file from activity stream
	 */
	@Test
	public void test12_EditAFileFromTheFileActivityFromSpace(){
		//Declare variable
		String file = "KS_Wiki_Attachment_pdffile.pdf";

		/*Step 1: Create file activity*/
		//Add new space
		magMember.goToMySpacePage();
		magMember.addNewSpace(spacename, spacedesc);

		//Open Documents in this space
		waitForAndGetElement(magMember.ELEMENT_DOCUMENTS_TAB);
		click(magMember.ELEMENT_DOCUMENTS_TAB);

		//Upload a file
		ecms.uploadFile("TestData/"+file);

		//Back to the Home page
		info("-- Back to the Home page --");
		navToolBar.goToHomePage();

		//- The File activity is displayed in the activity stream
		info("-- A File activity is added to the activity stream --");
		activity.checkInforAfterAddingDocument(file, "", "File", "91 bytes", "", "", "", "");

		/*Step 2: Edit file from activity stream*/
		//From the file activity, click on the link "Edit"
		info("Click Edit icon in activity");
		activity.goToEditFromContentActivity(file);
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_NAME.replace("${nameContent}", file));
		//assert getValue(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX).equalsIgnoreCase(file);
		activity.backToHomePageFromEditContentScreen();

		/*Clear data*/
		info ("-- Clear data --");
		magMember.goToMySpacePage();
		magMember.deleteSpace(spacename);
	}
}
