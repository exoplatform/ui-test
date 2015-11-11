package org.exoplatform.selenium.platform.wiki.functional.template;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.Template;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*--
 *-- Author: HaKT
 *-- Date: 17 Dec 2012
 *   updated by anhpp
 **/
public class Wiki_Template_Add extends Template{

	ManageAccount magAc;
	Button button;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAc = new ManageAccount(driver);
		button = new Button(driver);

		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 118295
	 * Case 01: Create new template
	 * - Create new Template
	 * - Go to Wiki home
	 * - Select add page from template
	 * - Verify new template is in list
	 * - Delete template
	 */
	@Test
	public void test01_createNewTemplate(){

		String DATA_TEMPLATE_TITLE="New_Template_01";
		String DATA_TEMPLATE_DESC="This is New Template 01";
		String DATA_TEMPLATE_CONTENT="Content of New Template 01";

		info("Create new template");

		goToTemplateManagement();

		addTemplate(DATA_TEMPLATE_TITLE, DATA_TEMPLATE_DESC, DATA_TEMPLATE_CONTENT);

		goToWikiHome();

		goToAddTemplateWikiPage();

		waitForAndGetElement(ELEMENT_NEW_TEMPLATE_LINK.replace("${TEMPLATE_TITLE}", DATA_TEMPLATE_TITLE));

		click(ELEMENT_CLOSE_TEMPLATE_LIST);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE);
	}

	/**
	 * Qmetry ID: 118297 (Wiki > Template > Add)
	 * Case 02: Create new template when using syntax
	 */
	@Test
	public void test02_createNewTemplateWhenUsingSyntax(){
		/*
		 * Step 1: Open form to create new template
		 * - Go to Browser -> Wiki Settings
		   - Select Template tab
		   - Click on Add More Templates link
		 */
		String DATA_TEMPLATE_TITLE_2="New_Template_2";
		String DATA_TEMPLATE_DESC_2="Table_effect_template";
		String DATA_TEMPLATE_CONTENT_2="aaa/bbb/ccc/ddd";
		String[] temp = DATA_TEMPLATE_CONTENT_2.split("/");
		String DATA_TEMPLATE_CONTENT_IN_TABLE="|=" + temp[0] + "|=" + temp[1] + "\n|" + temp[2] + "|" + temp[3];
		goToTemplateManagement();
		
		/*
		 *  Step 2: Create new template with Table tag
		 */
		info("Create new template with table effect");
		addTemplate(DATA_TEMPLATE_TITLE_2, DATA_TEMPLATE_DESC_2, DATA_TEMPLATE_CONTENT_IN_TABLE);
		
		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_2);
		
		/*
		 * Step 3: Create new template with Bold tag
		 */
		String DATA_TEMPLATE_TITLE_3="New_Template_3";
		String DATA_TEMPLATE_DESC_3="This is New Template 3";
		String DATA_TEMPLATE_CONTENT_3="Content_In_Bold";
		String DATA_TEMPLATE_CONTENT_IN_BOLD="**"+DATA_TEMPLATE_CONTENT_3+"**";

		info("Create new templatewith bold effect");
		addTemplate(DATA_TEMPLATE_TITLE_3, DATA_TEMPLATE_DESC_3, DATA_TEMPLATE_CONTENT_IN_BOLD);
		
		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_3);
		
		/*
		 * Step 4: Create new template with Bulleted list effect
		 */
		
		String DATA_TEMPLATE_TITLE_4="New_Template_4";
		String DATA_TEMPLATE_DESC_4="Bullet_effect_template";
		String DATA_TEMPLATE_CONTENT_4="aaa/bbb";
		String[] temp1 = DATA_TEMPLATE_CONTENT_4.split("/");
		String DATA_TEMPLATE_CONTENT_IN_BULLET="* "+ temp1[0] + "\n* " + temp1[1];

		info("Create new template with bullet effect");
		addTemplate(DATA_TEMPLATE_TITLE_4, DATA_TEMPLATE_DESC_4, DATA_TEMPLATE_CONTENT_IN_BULLET);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_4);
		
		/*
		 * Step 5: Create new template with italic tag
		 */
		String DATA_TEMPLATE_TITLE_5="New_Template_5";
		String DATA_TEMPLATE_DESC_5="This is New Template 5";
		String DATA_TEMPLATE_CONTENT_5="Content_In_Italic";
		String DATA_TEMPLATE_CONTENT_IN_ITALIC="//"+DATA_TEMPLATE_CONTENT_5+"//";

		info("Create new template with italic effect");
		addTemplate(DATA_TEMPLATE_TITLE_5, DATA_TEMPLATE_DESC_5, DATA_TEMPLATE_CONTENT_IN_ITALIC);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_5);
		
		/*
		 * Step 6: Create new template with Heading tag
		 */
		
		String DATA_TEMPLATE_TITLE_6="New_Template_6";
		String DATA_TEMPLATE_DESC_6="This is New Template 6";
		String DATA_TEMPLATE_CONTENT_6="Content_In_Heading_effect";
		String DATA_TEMPLATE_CONTENT_IN_HEADING="="+DATA_TEMPLATE_CONTENT_6+"=";

		info("Create new template with heading effect");
		addTemplate(DATA_TEMPLATE_TITLE_6, DATA_TEMPLATE_DESC_6, DATA_TEMPLATE_CONTENT_IN_HEADING);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_6);
		
		/*
		 * Step 7: Create new template with Link tag
		 */
		
		String DATA_TEMPLATE_TITLE_7="New_Template_7";
		String DATA_TEMPLATE_DESC_7="Link_effect_template";
		String DATA_TEMPLATE_CONTENT_7="http://google.com.vn";
		String DATA_TEMPLATE_CONTENT_IN_LINK="[[" + DATA_TEMPLATE_CONTENT_7 + "]]" ;

		info("Create new template with link effect");
		addTemplate(DATA_TEMPLATE_TITLE_7, DATA_TEMPLATE_DESC_7, DATA_TEMPLATE_CONTENT_IN_LINK);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_7);
		
		/*
		 * Step 8: Create new template with Numbered list tag
		 */
		
		String DATA_TEMPLATE_TITLE_8="New_Template_8";
		String DATA_TEMPLATE_DESC_8="Number_effect_template";
		String DATA_TEMPLATE_CONTENT_8="aaa/bbb";
		String[] temp2 = DATA_TEMPLATE_CONTENT_8.split("/");
		String DATA_TEMPLATE_CONTENT_IN_NUMBER="1. "+ temp2[0] + "\n11. " + temp2[1];

		info("Create new template with number effect");
		addTemplate(DATA_TEMPLATE_TITLE_8, DATA_TEMPLATE_DESC_8, DATA_TEMPLATE_CONTENT_IN_NUMBER);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_8);
		
		/*
		 * Step 9: Create new template with strike tag
		 */
		
		String DATA_TEMPLATE_TITLE_9="New_Template_9";
		String DATA_TEMPLATE_DESC_9="This is New Template 9";
		String DATA_TEMPLATE_CONTENT_9="Content_In_Strike";
		String DATA_TEMPLATE_CONTENT_IN_STRIKE="--"+DATA_TEMPLATE_CONTENT_9+"--";

		info("Create new template with strike effect");
		addTemplate(DATA_TEMPLATE_TITLE_9, DATA_TEMPLATE_DESC_9, DATA_TEMPLATE_CONTENT_IN_STRIKE);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_9);
		
		/*
		 * Step 10: Create new template with underline tag
		 */
		
		String DATA_TEMPLATE_TITLE_10="New_Template_10";
		String DATA_TEMPLATE_DESC_10="This is New Template 10";
		String DATA_TEMPLATE_CONTENT_10="Content_In_Underline";
		String DATA_TEMPLATE_CONTENT_IN_UNDERLINE="__"+DATA_TEMPLATE_CONTENT_10+"__";

		info("Create new template with underline effect");
		addTemplate(DATA_TEMPLATE_TITLE_10, DATA_TEMPLATE_DESC_10, DATA_TEMPLATE_CONTENT_IN_UNDERLINE);

		info("restore data");
		deleteTemplate(DATA_TEMPLATE_TITLE_10);
	}
	
	/**
	 * Qmetry ID: 118305
	 * Case 03: Create new page using new template
	 * - Create new Template
	 * - Go to Wiki home
	 * - Add new page with new template
	 * - Verify result
	 * - Delete Page
	 * - Delete template
	 */
	@Test
	public  void test03_createPageUsingNewTemplate(){

		String DATA_TEMPLATE_TITLE="New_Template_ForPage_01";
		String DATA_TEMPLATE_DESC="This is New Template 01";
		String DATA_TEMPLATE_CONTENT="Content of New Template 01";
		String DATA_PAGE_TITLE="Page_Template_01";
		
		info("Create new template");
		
		goToTemplateManagement();
		
		addTemplate(DATA_TEMPLATE_TITLE, DATA_TEMPLATE_DESC, DATA_TEMPLATE_CONTENT);
		
		waitForAndGetElement(ELEMENT_NEW_TEMPLATE_LINK.replace("${TEMPLATE_TITLE}", DATA_TEMPLATE_TITLE));

		goToWikiHome();
		
		addWikiPageFromTemplate(DATA_PAGE_TITLE, 0, DATA_TEMPLATE_TITLE);
	
		waitForAndGetElement(ELEMENT_VERIFY_PAGE_CONTENT.replace("${TEMPLATE_CONTENT}", DATA_TEMPLATE_CONTENT));
	
		info("restore data");
		deleteCurrentWikiPage();
		deleteTemplate(DATA_TEMPLATE_TITLE);
	}
}
