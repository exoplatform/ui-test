package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.filemanagementview;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * @author: HaKT
 * @date: 04/09/2013
 * updated by anhpp
 */
public class ECMS_SE_FileManagementView_Actions_CreateEditDeleteNode extends PlatformBase {
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ContentTemplate conTemp;
	EcmsBase ecms;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		navToolBar = new NavigationToolbar(driver,this.plfVersion);
		magAcc = new ManageAccount(driver,this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS); 
		ecms = new EcmsBase(driver,this.plfVersion);
		actBar = new ActionBar(driver,this.plfVersion);
		conTemp = new ContentTemplate(driver,this.plfVersion);

		info("Config New Content on action bar for admin view if it is not available on action bar");
		navToolBar.goToPersonalDocuments();
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK, "Admin", "Admin");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
	}

	@AfterMethod
	public void afterTest(){
		//		magAcc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 * CaseId: 119496,119558,119560
	 * Add Edit Delete a File document
	 */
	@Test
	public void test01_02_03_AddEditDeleteFileDocument(){
		String File_Name="File_Case_67416_ 78956";
		String File_Content="File_Content_67416";
		String File_Content_Edit="Edited File_Content_67416_To_ 78956";

		info("Add Edit Delete a File document");
		actBar.goToAddNewContent();
		conTemp.createNewFile(File_Name, File_Content, "");

		info("Edit File & verify edited content");
		conTemp.editFile(null, "", File_Content_Edit);
		assert driver.getPageSource().contains(File_Content_Edit);

		info("Delete Fle document");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		actBar.actionsOnElement(File_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119317
	 * Add/Delete a new folder
	 */
	@Test
	public void test04_AddDeleteNewFolder(){

		String Folder_Name = "Folder_Case-78915" + getRandomNumber();
		info("Add/Delete a new folder");
		//conTemp.createNewFolder(Folder_Name, folderType.None);
		actBar.goToAddNewFolder();
		type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, Folder_Name, true);
		click(conTemp.ELEMENT_CREATE_FOLDER_BUTTON);
		
		info("Restore data");
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Folder_Name));
		actBar.actionsOnElement(Folder_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119541
	 * Create JS file
	 */
	@Test
	public void test05_AddNewJsFile(){
		String Js_Name = "Js_Case_78917";
		String Js_Data = "alert(\"Hello! I am an alert box!!\");";
		info("Create JS file");
		actBar.goToAddNewContent();
		click(conTemp.ELEMENT_NEW_JS_FILE_LINK);
		conTemp.createNewJsFile(Js_Name, "0",Js_Data);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Js_Name));
		actBar.actionsOnElement(Js_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119542
	 * Create a web Link
	 */
	@Test
	public void test06_AddNewWebLink(){

		String Web_Link_Name="Web_link_case_78918";
		String URL = "http://google.com.vn";
		info("reate a web Link");
		actBar.goToAddNewContent();

		click(conTemp.ELEMENT_WEBLINK_LINK);

		conTemp.createNewLink(Web_Link_Name, URL);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Web_Link_Name));
		actBar.actionsOnElement(Web_Link_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119543
	 * Create an Accessible media
	 */
	@Test
	public void test07_AddNewAccessibleMedia(){
		String name = "AccessibleMedia_78920" ;
		String lang = "en";
		info("Create an Accessible media");
		actBar.goToAddNewContent();

		//	Create new accessible media here
		conTemp.createAccessibleMedia(name,lang,name,name,name);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", name));
		actBar.actionsOnElement(name, actionType.DELETE);

	}

	/**
	 * CaseId: 119544
	 * Create an Announcement
	 */
	@Test
	public void test08_AddNewAnnoucement(){

		String Announcement_Name = "Announcement_Case_78922";
		String Announcement_Summary ="Accounceent_summary_case_78922";
		info("Create an Announcement");
		actBar.goToAddNewContent();
		conTemp.createNewAnnouncement(Announcement_Name, Announcement_Summary,false);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Announcement_Name));
		actBar.actionsOnElement(Announcement_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119545
	 * Create a new css file
	 */
	@Test
	public void test09_AddNewCssFile(){

		String CssFile_Name="CSS_File_case_78923";
		String CSS_Data = "p{color: red;}";
		info("Create a new css file");
		actBar.goToAddNewContent();
		click(conTemp.ELEMENT_CSS_FILE_LINK);
		conTemp.createNewCssFile(CssFile_Name, "", CSS_Data);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", CssFile_Name));
		actBar.actionsOnElement(CssFile_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119546
	 * Add new HTML File document
	 */
	@Test
	public void test10_AddNewHtmlFile(){

		String Html_File_Name="HTML_File_case_78925";
		String Html_content= "Content Of HTML file";

		info("Add new HTML File document");
		actBar.goToAddNewContent();
		conTemp.createNewHtmlFile(Html_File_Name,false,"", Html_content); 

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Html_File_Name));
		actBar.actionsOnElement(Html_File_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119547
	 * Add new Illustrated Web Content
	 */
	@Test
	public void test11_AddNewIllustratedWebContent(){

		String Illustrated_WC_Name="Illustrated_WC_case_78926";
		String Illustrated_WC_content= "Content Of Illustrated_WC";
		String path2Image = "TestData/Winter.jpg";

		info("Add new Illustrated Web Content");
		actBar.goToAddNewContent();
		conTemp.createNewIllustratedWebContent(Illustrated_WC_Name, Illustrated_WC_content, path2Image, "", "", "", "");

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Illustrated_WC_Name));
		actBar.actionsOnElement(Illustrated_WC_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119548
	 * Add new Web Content
	 */
	@Test
	public void test12_AddNewWebContent(){

		String WC_Name="WC_case_78927";
		String WC_content= "Content Of WC";
		info("Add new Web Content");
		actBar.goToAddNewContent();
		conTemp.createNewWebContent(WC_Name, WC_content, "", "", "", "",false);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", WC_Name));
		actBar.actionsOnElement(WC_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119549
	 * Add new Product
	 */
	@Test
	public void test13_AddNewProduct(){
		String Product_Name="Product_case_78928";
		String Product_Summary="Summary of product case78928";
		String Product_Benefit="Benefit of product case78928";
		String Product_Feature="Features of product case78928";

		info("Add new Product");
		actBar.goToAddNewContent();
		conTemp.createFullNewProduct(Product_Name, "", Product_Summary,Product_Benefit , Product_Feature);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", Product_Name));
		actBar.actionsOnElement(Product_Name, actionType.DELETE);
	}

	/**
	 * CaseId: 119550
	 * Add new Contact us
	 */
	@Test
	public void test14_AddNewContactUs(){
		String Your_Name="John Smith";
		String Your_Address="Address of case 78930";
		String Your_Email="exoservice@gmail.com";
		String Your_Phone="1234567890";
		String Your_Message="I like this product";
		String ContactUs_Name = getCurrentDate("yyyy.MM.dd");

		info("Add new Contact us");
		actBar.goToAddNewContent();
		conTemp.createNewContactUs(Your_Name, Your_Address, Your_Email, Your_Phone, Your_Message);

		info("Restore data");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", ContactUs_Name));
		actBar.actionsOnElement(ContactUs_Name, actionType.DELETE);

	}

	/**
	 * CaseId: 119559
	 * Edit illustrated web content
	 */
	@Test
	public void test15_EditIllustratedWebContent(){
		String Illustrated_WC_Name="Illustrated_WC_case_78957";
		String Illustrated_WC_content= "Content Of Illustrated_WC";
		String path2Image = "TestData/Winter.jpg";
		String Illustrated_WC_Edited_content= "Edited: Content Of Illustrated_WC";

		info("Edit illustrated web content");
		actBar.goToAddNewContent();
		conTemp.createNewIllustratedWebContent(Illustrated_WC_Name, Illustrated_WC_content, path2Image, "", "", "", "");

		conTemp.editIllustratedWebContent(null, Illustrated_WC_Edited_content, "", "", "", "", "");
		waitForTextPresent(Illustrated_WC_Edited_content);

		info("Delete File document");
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		actBar.actionsOnElement(Illustrated_WC_Name, actionType.DELETE);
	}


}
