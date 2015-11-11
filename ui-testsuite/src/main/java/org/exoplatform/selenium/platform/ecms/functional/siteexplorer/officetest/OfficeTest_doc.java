package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.officetest;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.SearchAdministration;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.testng.annotations.*;

	/**
	* @author eXo
	*
	*/
	public class OfficeTest_doc extends PlatformBase{

		//Platform
		ManageAccount magAcc;
		ActionBar actBar;
		NavigationToolbar navToolBar;
		HomePageActivity hp;
		SearchAdministration searchAdmin;
		
		//Ecms
		EcmsBase ecms;
		ContentTemplate cTemplate;
		SitesExplorer siteExp;
		ContextMenu cMenu;	
		
		@BeforeMethod
		public void beforeMethods() {
			initSeleniumTest();
			driver.get(plfURL);
			info("Login ECMS with " + DATA_USER1);
			magAcc = new ManageAccount(driver);
			actBar = new ActionBar(driver);
			cTemplate = new ContentTemplate(driver);
			siteExp = new SitesExplorer(driver);
			navToolBar = new NavigationToolbar(driver);
			ecms = new EcmsBase(driver);
			cMenu = new ContextMenu(driver);
			hp = new HomePageActivity(driver);
			searchAdmin = new SearchAdministration(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
			

		}

		@AfterMethod
		public void afterMethods() {
			info("Logout ECMS");
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
	/**
	*<li> Case ID:119280.</li>
	*<li> Test Case Name: Actions on activity of a .doc file at home page.</li>
	*<li> Pre-Condition: 
	*A. Upload .doc file from your local machine with some pages or "doc-sample.doc" in .doc folder (Attachments tab)
	*B. Edit "exo.preoperties" file:
	*1. Redirect to the directory: $PLATFORM_TOMCAT_HOME/gatein/conf/exo.properties (in Tomcat) 
	*or $PLATFORM_JBOSS_HOME/standalone/configuration/gatein/exo.properties (in Jboss)
	*2. Find "JOD Converter (Documents preview)" section and delete "#" marks on the start of lines as:
	*+exo.jodconverter.enable=false
	*+exo.jodconverter.portnumbers=2002
	*+exo.jodconverter.officehome=/usr/lib/libreoffice
	*+exo.jodconverter.taskqueuetimeout=30000
	*+exo.jodconverter.taskexecutiontimeout=120000
	*+exo.jodconverter.maxtasksperprocess=200
	*+exo.jodconverter.retrytimeout=120000
	*3. After deleted all "#" marks of above lines, find "exo.jodconverter.enable" parameter 
	*and change "fasle" to "true" value.
	*4. Find "exo.jodconverter.officehome" parameter. 
	*If on your computer installed libreoffice, keep this value. 
	*But if on your computer installed openoffice 4, 
	*change the value from "/usr/lib/libreoffice" to "/opt/openoffice4" 
	*on linux or "c:programfilesx86Openoffice4" 
	*on Windows or "c:programfilesx86Libreoffice4" 
	*on Windows5. Restart server</li>
	*/
	@Test
	public  void test01_ActionsOnActivityOfA_docFileAtHomePage() {
		info("Test 1: Actions on activity of a .doc file at home page");
		
		String fileName="doc-example-1.doc";
		
		String user="John Smith";
		info("Pre conditions");
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		navToolBar.goToHomePage();
		
		/*Step Number: 1
		 *Step Name: Check activity athome page
		 *Step Description: 
			Go to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			An activity for this .doc file is shown with:
			- Preview of the 1st page 
			- Document name
			- File size
			- Version if have
			- Actions: Like/ Comment
			*/
		info("Verify author name");
		waitForAndGetElement(hp.ELEMENT_ACTIVITY_AUTHOR_NAME.replace("${index}","1").replace("${author}",user),2000,1);
		info("Verify Preview of the 1st page");
		waitForAndGetElement(hp.ELEMENT_FILE_PREVIEW.replace("{$file}",fileName),2000,1);
		info("Verify File size");
		waitForAndGetElement(hp.ELEMENT_FILE_SIZE.replace("@{fileName}",fileName).replace("${size}", "95"),2000,1);
		info("Verify actions");
		waitForAndGetElement(hp.ELEMENT_FILE_COMMENT_ICON.replace("${file}",fileName),2000,1);
		waitForAndGetElement(hp.ELEMENT_FILE_LIKE_ICON.replace("${file}",fileName),2000,1);
		
		/*Step number: 2
		 * PENDING: THE REQUIREMENT IS CHANGED. SO, THIS STEP IS REMOVED
		*Step Name: View document
		*Step Description: 
			- Click icon [View]OrClick preview iconOr Click Name of this document on activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- A pop up is shown to preview document content with 
			- .doc file name
			- Download icon
			- A toolbar with + paginator+ zoom icon and preview ratio, + Rotate left & right icon+ View info icon +Save as PDF file
			- User can execute actions above successfully
			*/
		
		/*info("Show the doc from the view ");
		click(hp.ELEMENT_CONTENT_VIEW_LINK_41.replace("@{fileName}", fileName));
		waitForAndGetElement(hp.ELEMENT_FILE_VIEW_NAME.replace("${fileName}", fileName),2000,1);
		waitForAndGetElement(hp.ELEMENT_FILE_CHECK_TOOLBAR_VIEW,2000,1);
		waitForAndGetElement(hp.ELEMENT_FILE_DOMWLOAD_TOOLBAR_VIEW,2000,1);
		click(hp.ELEMENT_FILE_DOMWLOAD_TOOLBAR_VIEW);
		click(hp.ELEMENT_FILE_VIEW_CLOSE);
		hp.goToEditFromContentActivity(fileName);*/

		/*Step number: 3
		 *Step Name: Download document
		 *Step Description: 
			Click Icon [Download]
		 *Input Data: 

		 *Expected Outcome: 
			Document is downloaded*/

		click(hp.ELEMENT_FILE_DOWNLOAD_BTN.replace("${file}",fileName));
		checkFileExisted(fileName);
		navToolBar.goToSiteExplorer();
		ecms.deleteData(fileName);
		Utils.pause(5000);
		
		/*Step number: 4
		 *Step Name: Edit document
		 *Step Description: 
		 * PENDING: THE REQUIREMENT IS CHANGED. THIS STEP IS REMOVED.
		*Step Name: Edit document
		*Step Description: 
			- Click icon [Edit]
			- Delete old file
			- Upload new file
			- Save & Close
			- Click Back icon
		 *Input Data: 

		 *Expected Outcome: 
			Document is edited successfully*/ 
		
		/*info("Test 4: Edit a .doc file");
		hp.editContent("TestData/"+fileName2);
		navToolBar.goToSiteExplorer();*/
		
		
 	}


	/**
	*<li> Case ID:119915.</li>
	*<li> Test Case Name: Download a .doc file.</li>
	*<li> Pre-Condition: 
	*A. a .doc file exists in SEB. 
	*"The configuration should write more clearly as on bellow:Edit ""exo.preoperties"" file:
	*1. Redirect to the directory: $PLATFORM_TOMCAT_HOME/gatein/conf/exo.properties (in Tomcat) 
	*or $PLATFORM_JBOSS_HOME/standalone/configuration/gatein/exo.properties (in Jboss)
	*2. Find ""JOD Converter (Documents preview)"" section and delete ""#"" marks on the start of lines as:
	*+exo.jodconverter.enable=false
	*+exo.jodconverter.portnumbers=2002
	*+exo.jodconverter.officehome=/usr/lib/libreoffice
	*+exo.jodconverter.taskqueuetimeout=30000
	*+exo.jodconverter.taskexecutiontimeout=120000
	*+exo.jodconverter.maxtasksperprocess=200
	*+exo.jodconverter.retrytimeout=1200003. 
	*After deleted all ""#"" marks of above lines, find ""exo.jodconverter.enable"" parameter 
	*and change ""fasle"" to ""true"" value.
	*4. Find ""exo.jodconverter.officehome"" parameter. 
	*If on your computer installed libreoffice, keep this value. 
	*But if on your computer installed openoffice 4, 
	*change the value from ""/usr/lib/libreoffice"" to ""/opt/openoffice4"" 
	*on linux or ""c:programfilesx86Openoffice4"" on Windows 
	*or ""c:programfilesx86Libreoffice4"" on Windows5. Restart server</li>
	*PENDING: THIS CASE IS CHECKECD IN DOCUMENT PREVIEW AND OPEN IN OFFICE.SO, THIS CASE IS REMOVED
	*/
	@Test(groups="pending")
	public  void test03_DownloadA_docFile()  {
		info("Test 3: Download a .doc file");
		String fileName="doc-example-3.doc";
		
		info("Pre conditions");
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		cTemplate.goToNode(fileName);
		click(cTemplate.ELEMENT_DOWNLOAD_FILE);
		checkFileExisted(fileName);
		/*Step Number: 1
		 *Step Name: Open .doc file
		 *Step Description: 
			- Click title of .doc file
		 *Input Data: 

		 *Expected Outcome: 
			Content of .doc file is well displayed.*/

		/*Step number: 2
		 *Step Name: Download
		 *Step Description: 
			- Click [Download] icon on top right of document
			- Click [Save]
		 *Input Data: 

		 *Expected Outcome: 
			.doc file is saved successfully with right format and possible to open by office in local machine*/ 
		info("Clear data");
		driver.navigate().refresh();
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}


	/**
	 *<li> Case ID:119923.</li>
	 *<li> Test Case Name: Edit a .doc file via webdav.</li>
	 *<li> Pre-Condition: A.Upload .doc file from your local machine with some pages orin .doc folder (Attachments tab)B. Mount a WebDAV Drive with a random folder1.Connect to server at your computer a. On Linux: 
	- Click on File folder and press Ctrl+L
	- Paste the link: dav://[YOUR
	-IP
	-ADDRESS
	-SERVER]:8080/rest/private/jcr/repository/collaboration b. On Windows 7:
	-Open the Computer window, then click Map network drive.
	-Paste the link: http://[YOUR
	-IP
	-ADDRESS
	-SERVER]:8080/rest/private/jcr/repository/collaboration c. On Windows 8: 
	-Click the Start button in the bottom left
	-hand corner of your screen.  
	-Select Computer from the interface.  
	-Choose "Map Network Drive" from the quick menu at the bottom of the screen. 
	-Paste the link: http://[YOUR
	-IP
	-ADDRESS
	-SERVER]:8080/rest/private/jcr/repository/collaboration2. Click Connect3. Fill user: john/gtnC. If you still do not connect successfully, check if you have followed instructions in WebDAV restrictions on bellow:1. Windows 7
	- Click Start, type regedit in the Start Search box, then hit Enter to open the Windows Registry Editor.
	- Find the key: HKEY_LOCAL_MACHINESYSTEMCurrentControlsetservicesWebClientParameters.
	- Select BasicAuthLevel and change its value to 2. If this does not exist, create it as a REG_DWORD key.
	- Reboot your OS.2. Windows 8
	- Go to Windows Registry Editor, then find the key: HKEY_LOCAL_MACHINESYSTEMCurrentControlsetservicesWebClientParameters.
	- Select UseBasicAuth and change its value to 1. If this does not exist, create it as a REG_DWORD key.
	- Select BasicAuthLevel and change its value to 2. If this does not exist, create it as a REG_DWORD key.
	- Reboot your OS.</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/06 09:43:17</li>
	 */
	@Test(groups="pending")
	public  void test05_EditA_docFileViaWebdav() {
		info("Test 5: Edit a .doc file via webdav");
		/*Step Number: 1
		 *Step Name: Step 1: Edit the file via webdav
		 *Step Description: 
			- Go to folder which has .doc file
			- Open file and edit it
			- Save change
		 *Input Data: 

		 *Expected Outcome: 
			- File is updated*/

		/*Step number: 2
		 *Step Name: Step 2: Check change on PLF
		 *Step Description: 
			- Open edited file on web browser
		 *Input Data: 

		 *Expected Outcome: 
			- File is updated correctly as step 2*/ 

	}

	/**
	 *<li> Case ID:119922.</li>
	 *<li> Test Case Name: Remote edition of a .doc file.</li>
	 *<li> Pre-Condition: A. Upload .doc file from your local machine with some pages or "doc
	-sample.doc" in .doc folder (Attachments tab)B. 
	Environment: IE on Window Operating System with Microsoft office is installed
	C. Edit "exo.preoperties" file:
	1. Redirect to the directory: $PLATFORM_TOMCAT_HOME/gatein/conf/exo.properties 
	(in Tomcat) or 
	$PLATFORM_JBOSS_HOME/standalone/configuration/gatein/exo.properties (in Jboss)
	2. Find "JOD Converter (Documents preview)" section and delete "#" marks on the start of lines as:
	+exo.jodconverter.enable=false
	+exo.jodconverter.portnumbers=2002
	+exo.jodconverter.officehome=/usr/lib/libreoffice
	+exo.jodconverter.taskqueuetimeout=30000
	+exo.jodconverter.taskexecutiontimeout=120000
	+exo.jodconverter.maxtasksperprocess=200
	+exo.jodconverter.retrytimeout=1200003. 
	After deleted all "#" marks of above lines, 
	find "exo.jodconverter.enable" parameter and change "fasle" to "true" value.
	4. Find "exo.jodconverter.officehome" parameter. 
	If on your computer installed libreoffice, keep this value. 
	But if on your computer installed openoffice 4, 
	change the value from "/usr/lib/libreoffice" to "/opt/openoffice4" on linux 
	or "c:programfilesx86Openoffice4" on Windows 
	or "c:programfilesx86Libreoffice4" on Windows5. Restart server</li>
	 */
	@Test(groups="pending")
	public  void test06_RemoteEditionOfA_docFile() {
		info("Test 6: Remote edition of a .doc file");
		String fileName="test02_uploadfile.doc";

		info("Pre conditions");
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);

		/*Step Number: 1
		 *Step Name: Preview .doc file
		 *Step Description: 
			- Open .doc file
		 *Input Data: 

		 *Expected Outcome: 
			The file is opened successfully*/
		cTemplate.goToNode(fileName);
		/*Step number: 2
		 *Step Name: Edit .doc file
		 *Step Description: 
			- Right click on .doc file then select [Download and allow Edition]
			- Select [Open]
		 *Input Data: 

		 *Expected Outcome: 
			- Document is opened in Microsoft word*/

		/*Step number: 3
		 *Step Name: Edit content directly
		 *Step Description: 
			- Edit content of this .doc file directly
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			Content of this doc is saved successfully*/ 

	}

	/**
	 *<li> Case ID:119916.</li>
	 *<li> Test Case Name: Save a .doc file as PDF file.</li>
	 *<li> Pre-Condition: A. Upload .doc file from your local machine with some pages or "doc
	-sample.doc" in .doc folder(Attachments tab)B. "The configuration should write more clearly as on bellow:Edit ""exo.preoperties"" file:1. Redirect to the directory: $PLATFORM_TOMCAT_HOME/gatein/conf/exo.properties (in Tomcat) or $PLATFORM_JBOSS_HOME/standalone/configuration/gatein/exo.properties (in Jboss)2. Find ""JOD Converter (Documents preview)"" section and delete ""#"" marks on the start of lines as:+exo.jodconverter.enable=false+exo.jodconverter.portnumbers=2002+exo.jodconverter.officehome=/usr/lib/libreoffice+exo.jodconverter.taskqueuetimeout=30000+exo.jodconverter.taskexecutiontimeout=120000+exo.jodconverter.maxtasksperprocess=200+exo.jodconverter.retrytimeout=1200003. After deleted all ""#"" marks of above lines, find ""exo.jodconverter.enable"" parameter and change ""fasle"" to ""true"" value.4. Find ""exo.jodconverter.officehome"" parameter. If on your computer installed libreoffice, keep this value. But if on your computer installed openoffice 4, change the value from ""/usr/lib/libreoffice"" to ""/opt/openoffice4"" on linux or ""c:programfilesx86Openoffice4"" on Windows or ""c:programfilesx86Libreoffice4"" on Windows5. Restart server</li>
	B. "The configuration should write more clearly as on bellow:
	Edit ""exo.preoperties"" file:
	1. Redirect to the directory: 
	$PLATFORM_TOMCAT_HOME/gatein/conf/exo.properties (in Tomcat) 
	or $PLATFORM_JBOSS_HOME/standalone/configuration/gatein/exo.properties (in Jboss)
	2. Find ""JOD Converter (Documents preview)"" section and delete ""#"" marks on the start of lines as:
	+exo.jodconverter.enable=false
	+exo.jodconverter.portnumbers=2002
	+exo.jodconverter.officehome=/usr/lib/libreoffice
	+exo.jodconverter.taskqueuetimeout=30000
	+exo.jodconverter.taskexecutiontimeout=120000
	+exo.jodconverter.maxtasksperprocess=200
	+exo.jodconverter.retrytimeout=1200003. 
	After deleted all ""#"" marks of above lines, find ""exo.jodconverter.enable"" parameter and 
	change ""fasle"" to ""true"" value.4. Find ""exo.jodconverter.officehome"" parameter. 
	If on your computer installed libreoffice, keep this value. 
	But if on your computer installed openoffice 4, 
	change the value from ""/usr/lib/libreoffice"" 
	to ""/opt/openoffice4"" on linux or ""c:programfilesx86Openoffice4"" 
	on Windows or ""c:programfilesx86Libreoffice4"" on Windows5. Restart server</li>
	PENDING: THE FUNCTION SAVE AS PDF FILE IS CHANGED BY NEW REQUIREMENT.THIS CASE IS REMOVED
	*/
	@Test(groups="pending")
	public  void test07_SaveA_docFileAsPDFFile() {
		info("Test 7: Save a .doc file as PDF file");
		String fileName="doc-example-4.doc";
		String fileNamePdf="pdf-example.pdf";
		
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		cTemplate.goToNode(fileName);
		click(cTemplate.ELEMENT_DOWLOAD_AS_PDF);
		checkFileExisted(fileNamePdf);
		/*Step Number: 1
		 *Step Name: Preview .doc file
		 *Step Description: 
			- Open .doc file
		 *Input Data: 

		 *Expected Outcome: 
			the file's content is viewed as well*/

		/*Step number: 2
		 *Step Name: Save as PDF file
		 *Step Description: 
			Click [Save as PDF file]
		 *Input Data: 

		 *Expected Outcome: 
			Document is saved as .PDF file in your local machine*/ 
		info("Clear data");
		driver.navigate().refresh();
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}

	/**
	 *<li> Case ID:119925.</li>
	 *<li> Test Case Name: Search a .doc file (based on keyword).</li>
	 *<li> Pre-Condition: - Upload .doc file from your local machine with some pages or "doc
	-sample.doc" in .doc folder (Attachments tab)</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/06 09:43:17</li>
	 */
	@Test
	public  void test08_SearchA_docFile_basedOnKeyword_() {
		info("Test 8: Search a .doc file (based on keyword)");

		String fileName="doc-example-4.doc";
		String searchName="doc-example";
		
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		switchToParentWindow();
		driver.navigate().refresh();
		cTemplate.searchContent(searchName,fileName);

		/*Step Number: 1
		 *Step Name: Search key word
		 *Step Description: 
			- In the top navigation, go to the search field
			- Input keyword(s) issued from doc
			-sample.doc in order to search for this file (words of the document, title..etc)
			- Type enter
		 *Input Data: 

		 *Expected Outcome: 
			The file doc
			-sample.doc is found and listed in the results*/

		navToolBar.goToSearchInToolbar(searchName, true);
		/*Step number: 2
		 *Step Name: Search key word
		 *Step Description: 
			- Go to ECMS 
			- Input the same keyword in the search field
			- Type enter
		 *Input Data: 

		 *Expected Outcome: 
			The file doc
			-sample.doc is found and listed in the results*/
		info("Clear data");
		navToolBar.goToSiteExplorer();
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}

	/**
	 *<li> Case ID:119926.</li>
	 *<li> Test Case Name: Upload a .doc file from action bar.</li>
	 *<li> Pre-Condition: Take .doc file from your local machine with some pages or "doc
	-sample.doc" in .doc folder(Attachments tab)</li>
	PENDING: THIS CASE IS CHECKED IN PREVIOUS CASES. THE CASE IS REMOVED
	*/
	@Test(groups="pending")
	public  void test09_UploadA_docFileFromActionBar() {
		info("Test 9: Upload a .doc file from action bar");
		String fileName="doc-example-3.doc";
		
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		ecms.uploadFile("TestData/"+fileName,true);
		/*Step Number: 1
		 *Step Name: Upload .doc file
		 *Step Description: 
			- Log in
			- Go to Aministration/Content/ Sites Explorer
			- Choose a driveOr
			- Go to Intranet/Documents
			- Click [Upload] button on action bar 
			- Select .doc file
		 *Input Data: 

		 *Expected Outcome: 
			the file is uploaded successfully*/ 
		info("Clear data");
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}

	/**
	 *<li> Case ID:119928.</li>
	 *<li> Test Case Name: Upload a .doc file via WEBDAV.</li>
	 *<li> Pre-Condition: A. Take all .doc files from your local machine with some pages orin .doc folder (Attachments tab)B. Mount a WebDAV Drive with a random folder1.Connect to server at your computer a. On Linux: 
	- Click on File folder and press Ctrl+L
	- Paste the link: dav://[YOUR
	-IP
	-ADDRESS
	-SERVER]:8080/rest/private/jcr/repository/collaboration b. On Windows 7:
	-Open the Computer window, then click Map network drive.
	-Paste the link: http://[YOUR
	-IP
	-ADDRESS
	-SERVER]:8080/rest/private/jcr/repository/collaboration c. On Windows 8: 
	-Click the Start button in the bottom left
	-hand corner of your screen.  
	-Select Computer from the interface.  
	-Choose "Map Network Drive" from the quick menu at the bottom of the screen. 
	-Paste the link: http://[YOUR
	-IP
	-ADDRESS
	-SERVER]:8080/rest/private/jcr/repository/collaboration2. Click Connect3. Fill user: john/gtnC. If you still do not connect successfully, check if you have followed instructions in WebDAV restrictions on bellow:1. Windows 7
	- Click Start, type regedit in the Start Search box, then hit Enter to open the Windows Registry Editor.
	- Find the key: HKEY_LOCAL_MACHINESYSTEMCurrentControlsetservicesWebClientParameters.
	- Select BasicAuthLevel and change its value to 2. If this does not exist, create it as a REG_DWORD key.
	- Reboot your OS.2. Windows 8
	- Go to Windows Registry Editor, then find the key: HKEY_LOCAL_MACHINESYSTEMCurrentControlsetservicesWebClientParameters.
	- Select UseBasicAuth and change its value to 1. If this does not exist, create it as a REG_DWORD key.
	- Select BasicAuthLevel and change its value to 2. If this does not exist, create it as a REG_DWORD key.
	- Reboot your OS.</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/06 09:43:17</li>
	 */
	@Test(groups="pending")
	public  void test10_UploadA_docFileViaWEBDAV() {
		info("Test 10 Upload a .doc file via WEBDAV");
		/*Step Number: 1
		 *Step Name: Upload .doc file
		 *Step Description: 
			- Copy a .doc file toWEBDAV
		 *Input Data: 

		 *Expected Outcome: 
			the file is shown on Webdav successfully*/

		/*Step number: 2
		 *Step Name: Check the uploaded file on the platform
		 *Step Description: 
			- Login with administrator
			- Go to Administration
			->Content
			->Site Explorer
			- Select the drive and the folder that are connected via WebDav
		 *Input Data: 

		 *Expected Outcome: 
			the file is shown into the folder on the platform*/

		/*Step number: 3
		 *Step Name: Open the file
		 *Step Description: 
			Click on the file's title
		 *Input Data: 

		 *Expected Outcome: 
			the content of the file is shown as well*/ 

	}

	/**
	 *<li> Case ID:119929.</li>
	 *<li> Test Case Name: Upload a .doc file when drap and drop from local machine.</li>
	 *<li> Pre-Condition: A. Take all .doc files from your local machine with some pages or "doc
	-sample.doc" in .doc folder (Attachments tab)B. Repeat steps for small and big files</li>
	 *Pending : impossible to drag and drop from local machine
	 */
	@Test(groups="pending")
	public  void test11_UploadA_docFileWhenDrapAndDropFromLocalMachine() {
		info("Test 11 Upload a .doc file when drap and drop from local machine");
		/*Step Number: 1
		 *Step Name: Upload .doc file
		 *Step Description: 
			- Log in
			- Go to Aministration/Content/ Sites Explorer
			- Choose a driveOr
			- Go to Intranet/Documents
			- Drag & Drop a .doc file from local machine to Site Explorer
		 *Input Data: 

		 *Expected Outcome: 
			the file is uploaded successfully*/ 

	}

	/**
	 *<li> Case ID:119927.</li>
	 *<li> Test Case Name: Upload a .doc file when Right click on main pane.</li>
	 *<li> Pre-Condition: Take .doc file from your local machine with some pages or "doc
	-sample.doc" in Functional > ECMS > Sites explorer > Office Test (Attachments tab)</li>
	*/
	@Test
	public  void test12_UploadA_docFileWhenRightClickOnMainPane() {
		info("Test 12 Upload a .doc file when Right click on main pane");
		String fileName="doc-example-5.doc";
		
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		
		cTemplate.uploadByRightClickOnTheRightPanel("TestData/"+fileName,true);
		/*Step Number: 1
		 *Step Name: Upload .doc file
		 *Step Description: 
			- Log in
			- Go to Aministration/Content/ Sites Explorer
			- Choose a driveOr
			- Go to Intranet/Documents
			- Right click on main pane 
			- Select [Upload Files]
			-Select .doc file
		 *Input Data: 

		 *Expected Outcome: 
			the file is uploaded successfully*/ 
		info("Clear data");
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}

	/**
	 *<li> Case ID:119930.</li>
	 *<li> Test Case Name: View a .doc file in Site Explorer.</li>
	 *<li> Pre-Condition: A. Take .doc file from your local machine with some pages or "doc
	-sample.doc" in .doc folder (Attachments tab)B. Edit "exo.preoperties" file:1. Redirect to the directory: $PLATFORM_TOMCAT_HOME/gatein/conf/exo.properties (in Tomcat) or $PLATFORM_JBOSS_HOME/standalone/configuration/gatein/exo.properties (in Jboss)2. Find "JOD Converter (Documents preview)" section and delete "#" marks on the start of lines as:+exo.jodconverter.enable=false+exo.jodconverter.portnumbers=2002+exo.jodconverter.officehome=/usr/lib/libreoffice+exo.jodconverter.taskqueuetimeout=30000+exo.jodconverter.taskexecutiontimeout=120000+exo.jodconverter.maxtasksperprocess=200+exo.jodconverter.retrytimeout=1200003. After deleted all "#" marks of above lines, find "exo.jodconverter.enable" parameter and change "fasle" to "true" value.4. Find "exo.jodconverter.officehome" parameter. If on your computer installed libreoffice, keep this value. But if on your computer installed openoffice 4, change the value from "/usr/lib/libreoffice" to "/opt/openoffice4" on linux or "c:programfilesx86Openoffice4" on Windows or "c:programfilesx86Libreoffice4" on Windows5. Restart server</li>
	PENDING: THIS CASE IS CHECKED IN DOCUMENT PREVIEW AND OPEN IN OFFICE. SO, THIS CASE IS REMOVED
	*/
	@Test(groups="pending")
	public  void test13_ViewA_docFileInSiteExplorer() {
		info("Test 13 View a .doc file in Site Explorer");
		String fileName="doc-example-4.doc";
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		navToolBar.goToSiteExplorer();
		/*Step Number: 1
		 *Step Name: Upload .doc file
		 *Step Description: 
			- Log in
			- Go to Aministration/Content/ Sites Explorer
			- Upload a .doc file to Site Explorer
		 *Input Data: 

		 *Expected Outcome: 
			the file is uploaded successfully*/
		cTemplate.goToNode(fileName);
		waitForAndGetElement(cTemplate.ELEMENT_DOWLOAD_AS_PDF,2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_DOWNLOAD_FILE,2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_ZOOM_IN_ICON,2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_ROTATE_LEFT_ICON,2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_INFO_ICON,2000,1);
		/*Step number: 2
		 *Step Name: Preview .doc file
		 *Step Description: 
			- Double
			-click on the file
		 *Input Data: 

		 *Expected Outcome: 
			Content of .doc file is well displayed with
			- File Name
			- Download icon
			- A toolbar with + paginator,+ zoom icon and preview ratio, + Rotate left & right icon+ View info icon +Save as PDF file*/ 
		info("Clear data");
		navToolBar.goToSiteExplorer();
		ecms.deleteFile(fileName);
		Utils.pause(5000);
 	}

	/**
	*<li> Case ID:119918.</li>
	*<li> Test Case Name: Delete a .doc file.</li>
	*/
	@Test
	public  void test02_Delete_Docfile() {
		info("Test 2: Delete a .doc file");
		String fileName="doc-example-2.doc";
		info("Pre conditions");
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		
		info("Verify that the file is uploaded and shown on AS");
		navToolBar.goToHomePage();
		waitForAndGetElement(hp.ELEMENT_FILE_PREVIEW.replace("{$file}",fileName),2000,1);
		/*Step number: 1
		*Step Name: Delete .doc file
		*Step Description: 
			- Right click on document then [Delete]
			- Click [Delete] at Confirmation dialog
		*Input Data: 
			
		*Expected Outcome: 
			- Document is deleted
			- Activity at intranet homepage is deleted
		*/
		navToolBar.goToSiteExplorer();
		ecms.deleteData(fileName);
		Utils.pause(5000);
		navToolBar.goToHomePage();
		waitForElementNotPresent(hp.ELEMENT_FILE_PREVIEW.replace("{$file}",fileName),2000,1);
		
	}
	/**
	*<li> Case ID:119917.</li>
	*<li> Test Case Name: Edit a .doc file.</li>
	*
	*/
	@Test
	public void test04_Edit_Docfile(){
		info("Test 4: Edit a .doc file");
		String fileName="doc-example-3.doc";
		String fileName2="KS_Wiki_Attachment_Office_file.doc";
		info("Pre conditions");
		navToolBar.goToSiteExplorer();
		ecms.uploadFile("TestData/"+fileName);
		
		/*Step number: 1
		*Step Name: Preview .doc file
		*Step Description: 
			- Open .doc file
		*Input Data: 
			
		*Expected Outcome: 
			the file's content is viewed as well*/ 
		info("Open the file");
		cTemplate.goToNode(fileName);
		/*Step number: 2
		*Step Name: Edit .doc file
		*Step Description: 
			- Click [Edit] icon on action bar, or Right click on document then [Edit]
			- From Content field, delete the file
			- Upload a other .doc file
			- Click on Save and Close button
		*Input Data: 
			
		*Expected Outcome: 
			Document is edited and new document content is shown.*/ 
		
		info("Edit a .doc file");
		actBar.goToEditDocument(fileName);
		ecms.editUploadedFile(fileName,"TestData/"+fileName2,"","","","");
		info("Verify that the content of the file is changed");
		waitForAndGetElement(ecms.ELEMENT_FILE_CONTENT_PAGE);
		
		navToolBar.goToSiteExplorer();
		ecms.deleteData(fileName);
	}
}
