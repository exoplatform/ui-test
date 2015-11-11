package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class CKEditor_InsertObject extends PlatformBase{

		Button button;
		ManageAccount magAcc;
		NavigationToolbar navToolBar;
		ActionBar actBar;
		ManageAlert magAlt;

		//Ecms
		EcmsBase ecms;
		EcmsPermission ecmsPer;
		ContextMenu cMenu;
		ContentTemplate cTemplate;

		@BeforeMethod
		public void beforeMethod(){
			initSeleniumTest();
			driver.get(plfURL);
			button = new Button(driver, this.plfVersion);
			magAcc = new ManageAccount(driver, this.plfVersion);
			navToolBar = new NavigationToolbar(driver, this.plfVersion);
			actBar = new ActionBar(driver, this.plfVersion);
			ecms = new EcmsBase(driver, this.plfVersion);
			ecmsPer = new EcmsPermission(driver);
			cMenu = new ContextMenu(driver);
			cTemplate = new ContentTemplate(driver, this.plfVersion);
			magAlt = new ManageAlert(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
		}

		@AfterMethod
		public void afterMethod() {
			info("-- User signOut --");
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
	/**
	*<li> Case ID:119894.</li>
	*<li> Test Case Name: Insert Content Link.</li>
	*Pending : No files to selected
	*/
	@Test(groups="pending")
	public void test01_InsertContentLink() {
		String name="webct119894";
		
		navToolBar.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor (Accessible Media, Web content, Illustrated Web Content, Announcement, Product, not File)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		*Step Name: Insert Content link
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Insert Content Link] icon
			- Navigate to files, images, videos you want
			- Save&Close
		*Input Data: 
			
		*Expected Outcome: 
			Files/videos/music are displayed in CKEditor as link. Users click to see content or downloadImages are shown directly.*/ 

 	}

	/**
	*<li> Case ID:119892.</li>
	*<li> Test Case Name: Insert gadgets.</li>
	*Pending : Impossible to select the gadgets in the gadgetWindow
	*jira issue : https://jira.exoplatform.org/browse/ECMS-6390
	*/
	@Test(groups="pending")
	public  void test02_InsertGadgets() {
		info("Test 2: Insert gadgets");
		String name="webct119894";
		
		navToolBar.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);

		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor (Accessible Media, Web content, Illustrated web contentm Announcement, Product)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		*Step Name: Insert gadgets
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Insert Gadgets] icon
			- Choose a or some gadgets
			- Save&Close
		*Input Data: 
			
		*Expected Outcome: 
			Gadget is added and well displayed in CKeditor*/ 

 	}

	/**
	*<li> Case ID:119895.</li>
	*<li> Test Case Name: Insert Images.</li>
	*/
	@Test
	public  void test03_InsertImages() {
		info("Test 3: Insert Images");
		String name="webct119895";
		String urlImage="http://www.exoplatform.com/rest/jcr/repository/collaboration/sites/website/web%20contents/10.Homepage/newhomepage/images/banner1.jpg";
		
		navToolBar.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		
		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choose a template with CKEditor (Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web content, announcement, product, not nt:file)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		*Step Name: Insert gadgets
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Image] icon
			- Paste a image url, eg http://www.hoasaigon.com.vn/kcfinder/upload/images/hoa
			-huong
			-duong.jpgOr Click [Browser Server] then choose an image in local 
			- Click OK
			- Save&Close
		*Input Data: 
		
		*Expected Outcome: 
			Image is added and well displayed in CKeditor.*/ 
		click(cTemplate.ELEMENT_IMAGE_BUTTON_ADD);
		type(cTemplate.ELEMENT_IMAGE_LINK_FORM,urlImage,true);
		click(cTemplate.ELEMENT_ACCEPT_ADD_IMAGE);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		waitForAndGetElement(By.xpath(cTemplate.ELEMENT_IMAGE_CHECK_ON_DOCUMENT_VIEW.replace("{$image}", urlImage)));
		
		info("Reset data");
		cMenu.deleteDocument(By.linkText(name));
 	}

	/**
	*<li> Case ID:119896.</li>
	*<li> Test Case Name: Insert Portal Link.</li>
	*/
	@Test
	public  void test04_InsertPortalLink() {
		info("Test 4: Insert Portal Link");
		String name="webct119896";
		String url="http://google.com";
		
		navToolBar.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor (Accessible Media, Web content, Illustrated Web Content, Announcement, Product, not File)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		*Step Name: Insert portal link
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Insert link to a site page] icon
			- Navigate to local portal link by click [Get Portal Link] then select a linkOr Paste a internet link into URL box, eg http://google.com
			- Click OK
			- Save&Close
		*Input Data: 
			
		*Expected Outcome: 
			Link is displayed in CKEditor as link. Users click to go to link.*/ 
		click(cTemplate.ELEMENT_ADD_SIMPLE_LINK);
		type(cTemplate.ELEMENT_SIMPLE_LINK_FORM,url,false);
		click(cTemplate.ELEMENT_ACCEPTD_ADD_LINK);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		waitForAndGetElement(By.xpath(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", url)));
		click(By.xpath(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", url)));

		info("Check the page is on google page");
		waitForAndGetElement(ELEMENT_GOOGLE_PAGE_LOGO);
		
		info("Reset data");
		driver.get(DEFAULT_PLF_URL +"/intranet");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(name));
 	}

	/**
	*<li> Case ID:119897.</li>
	*<li> Test Case Name: Insert Special characters.</li>
	*/
	@Test
	public  void test05_InsertSpecialCharacters() {
		info("Test 5: Insert Special characters");
		String name="webct119897";
		String specialChar="!#@*";
		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor (Accessible Media, Web Content, Illustrated Web Content, Announcement, Product, not File)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/
		navToolBar.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		/*Step number: 2
		*Step Name: Insert Content link
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Insert Special Characters] icon
			- Select some special characters
			- Save&Close
		*Input Data: 
			
		*Expected Outcome: 
			- Special characters are added*/ 
		click(cTemplate.ELEMENT_ADD_SPECIAL_CHAR);
		click(By.xpath(cTemplate.ELEMENT_SELECT_SPECIAL_CHAR.replace("${char}", "!")));
		click(cTemplate.ELEMENT_ADD_SPECIAL_CHAR);
		click(By.xpath(cTemplate.ELEMENT_SELECT_SPECIAL_CHAR.replace("${char}", "#")));
		click(cTemplate.ELEMENT_ADD_SPECIAL_CHAR);
		click(By.xpath(cTemplate.ELEMENT_SELECT_SPECIAL_CHAR.replace("${char}", "@")));
		click(cTemplate.ELEMENT_ADD_SPECIAL_CHAR);
		click(By.xpath(cTemplate.ELEMENT_SELECT_SPECIAL_CHAR.replace("${char}", "*")));
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		waitForAndGetElement(By.xpath(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", specialChar)));
		
		cMenu.deleteDocument(By.linkText(name));
 	}	

	/**
	*<li> Case ID:119898.</li>
	*<li> Test Case Name: Insert table.</li>
	*/
	@Test
	public  void test06_InsertTable() {
		info("Test 6: Insert table");
		String name="webct119898";
		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor (Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web content, announcement, product, not nt:file)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/
		navToolBar.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		/*Step number: 2
		*Step Name: Insert Content link
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Insert Table] icon
			- Input value to create table
			- Click OK
			- Input value into table
			- Save&Close
		*Input Data: 
			
		*Expected Outcome: 
			- Table is added*/ 
		click(cTemplate.ELEMENT_ADD_TABLE);
		click(cTemplate.ELEMENT_ACCEPT_TABLE_ADD);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		waitForAndGetElement(cTemplate.ELEMENT_CHECK_TABLE_EXIST);
		
		cMenu.deleteDocument(By.linkText(name));
		
 	}

	/**
	*<li> Case ID:119893.</li>
	*<li> Test Case Name: Insert videos.</li>
	*Pending : a plugin is needed to play and see the video
	*/
	@Test(groups="pending")
	public  void test07_InsertVideos() {
		info("Test 7: Insert videos");
		
		/*Step Number: 1
		*Step Name: Choose a template
		*Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor (Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web contentm announcement, product, not nt:file)
		*Input Data: 
			
		*Expected Outcome: 
			Template is chosen*/
		
		/*Step number: 2
		*Step Name: Insert gadgets
		*Step Description: 
			- Fill name
			- Mouse focus on CKEditor
			- Click [Flash] icon
			- Paste a url vieo, eg http://www.youtube.com/watch?v=bx5BUbiIXFw
			- Click OK
			- Save&Close
		*Input Data: 
			
		*Expected Outcome: 
			Video is added and well displayed in CKeditor. You can play and view it directly.*/ 

 	}
}
