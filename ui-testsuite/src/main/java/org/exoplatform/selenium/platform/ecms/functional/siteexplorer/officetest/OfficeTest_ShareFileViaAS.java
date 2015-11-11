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
import org.exoplatform.selenium.platform.social.ManageMember;
import org.testng.annotations.*;

	/**
	* @author eXo
	*
	*/
	public class OfficeTest_ShareFileViaAS extends PlatformBase{

		//Platform
		ManageAccount magAcc;
		ActionBar actBar;
		NavigationToolbar navToolBar;
		HomePageActivity hp;
		SearchAdministration searchAdmin;
		ManageMember magMember;
		
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
			magMember = new ManageMember(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
			

		}

		@AfterMethod
		public void afterMethods() {
			info("Logout ECMS");
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
	/**
	*<li> Case ID:119919.</li>
	*<li> Test Case Name: Sharing office documents in homepage activity stream.</li>
	*<li> Pre-Condition: 
	*/
	@Test
	public  void test01_SharingOfficeDocumentsInHomeAs() {
		info("Test 1: Sharing office documents in homepage activity stream");
		
		String fileName="odt-example2.odt";
	    String folderPath="Collaboration/sites/intranet/documents";
		String nameDrive="General Drives";
		String description = "Share office documents"+getRandomNumber();
		navToolBar.goToHomePage();
		
		/*Step Number: 1
		 *Step Name: Share file in Homepage
		 *Step Description: 
			- Go to home intranet
			- Click on file icon and upload office file types: .doc, .docx, .xls, .xlsx, 
			.ppt, .pptx, .rtf, .odt, .ods, .odp
            - Share these files
		 *Input Data: 

		 *Expected Outcome: 
			Files are shared on homepage activity stream
			*/
		info("Share a pdf file activity");
		hp.addActivity(nameDrive,folderPath,"TestData/",fileName,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(hp.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",fileName),3000,1);
		
		
		/*Step number: 2
		*Step Name: View File icon
		*Step Description: 
			-View File icon of sharing files on activity stream
		 *Input Data: 

		 *Expected Outcome: 
			on Activity stream, content of the 1st page of office file is shown as thumbnail
			*/
		info("Verify Preview of the 1st page");
		waitForAndGetElement(hp.ELEMENT_FILE_PREVIEW.replace("{$file}",fileName),2000,1);
		
		info("Delete datata");
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		ecms.deleteData(fileName);
		Utils.pause(5000);
		
 	}


	/**
	*<li> Case ID:119921.</li>
	*<li> Test Case Name: Sharing office documents in my activity stream.</li>
	*<li> Pre-Condition: 
	*/
	@Test
	public  void test02_SharingOfficeDocumentsInMyAS()  {
		info("Test 02: Sharing office documents in my activity stream");
		String fileName="rtf-example2.rtf";
		
	    String folderPath="Collaboration/sites/intranet/documents";
		String nameDrive="General Drives";
		String description = "Share office documents"+getRandomNumber();
		navToolBar.goToMyActivity();
		/*Step Number: 1
		 *Step Name: Share file in Homepage
		 *Step Description: 
			- Connect to the intranet
			- Click Username at top right of top navigation
			- Click My Activity Stream
			- Click on file icon and upload office file types: .doc, .docx, .xls, .xlsx, 
			.ppt, .pptx, .rtf, .odt, .ods, .odp
			- Share these files
		 *Input Data: 

		 *Expected Outcome: 
			Files are shared on homepage activity stream.*/
		info("Share a pdf file activity");
		hp.addActivity(nameDrive,folderPath,"TestData/",fileName,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(hp.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",fileName),3000,1);
		/*Step number: 2
		 *Step Name:View File icon
		 *Step Description: 
			View File icon of sharing files on activity stream
		 *Input Data: 

		 *Expected Outcome: 
			on Activity stream, content of the 1st page of office file is shown as thumbnail*/ 
		info("Verify Preview of the 1st page");
		waitForAndGetElement(hp.ELEMENT_FILE_PREVIEW.replace("{$file}",fileName),2000,1);
		
		info("Delete datata");
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}


	/**
	 *<li> Case ID:119920.</li>
	 *<li> Test Case Name: Sharing office documents in space activity stream.</li>
	 */
	@Test
	public  void test03_SharingOfficeDocumentsInSpaceAS() {
		info("Test 03: Sharing office documents in space activity stream");
		String fileName="ppt-example2.ppt";
		String folderPath="Collaboration/sites/intranet/documents";
		String nameDrive="General Drives";
		String description = "Share office documents"+getRandomNumber();
		String spaceName = "Space"+getRandomNumber();

		//Create data
		//Add new space
		info("Create data");
		magMember.goToMySpacePage();
		magMember.addNewSpace(spaceName, "");
		/*Step Number: 1
		 *Step Name: Step 1: Share file in Homepage
		 *Step Description: 
			- Connect to the intranet
			- Add new space 
			- Stand at space activity stream
			- Click on file icon and upload office file types: .doc, .docx, .xls, .xlsx, .ppt, 
			.pptx, .rtf, .odt, .ods, .odp
			- Share these files
		 *Input Data: 

		 *Expected Outcome: 
			Files are shared on homepage activity stream*/
		info("Share a pdf file activity");
		hp.addActivity(nameDrive,folderPath,"TestData/",fileName,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(hp.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",fileName),3000,1);
		/*Step number: 2
		 *Step Name: Step 2: View File icon
		 *Step Description: 
			View File icon of sharing files on activity stream
		 *Input Data: 

		 *Expected Outcome: 
			on Activity stream, content of the 1st page of office file is shown as thumbnail*/ 
		info("Verify Preview of the 1st page");
		waitForAndGetElement(hp.ELEMENT_FILE_PREVIEW.replace("{$file}",fileName),2000,1);
		
		info("Delete datata");
		navToolBar.goToSiteExplorer();
		cTemplate.goToNode("intranet");
		cTemplate.goToNode("documents");
		ecms.deleteData(fileName);
		Utils.pause(5000);
	}
}
