package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class Wiki_Settings extends Wiki_TestConfig{

	/**
	 *<li> Case ID:122830.</li>
	 *<li> Test Case Name: Add new template.</li>
	 *Jira Issue : https://jira.exoplatform.org/browse/WIKI-991
	 */
	@Test(groups="pending")
	public  void test01_AddNewTemplate() {
		info("Test 1: Add new template");
		/*Step Number: 1
		 *Step Name: Step 1: Add new template
		 *Step Description: 
			- Click on Browse at top right and select Wiki Settings action
			- Click Template tab 
			-> Select Add More Templates button
			- Input all fields required and click Save template
		 *Input Data: 

		 *Expected Outcome: 
			- Template is added successfully and listed in Template table*/ 
		hp.goToWiki();

	}

	/**
	 *<li> Case ID:122832.</li>
	 *<li> Test Case Name: Edit template.</li>
	 *<li> Case ID:122838.</li>
	 *<li> Test Case Name: Delete template.</li>
	 * ERROR : Change this test when " create template " work again. Actually this test use native elements.
	 */
	@Test 
	public  void test02_04_EditTemplate() {
		info("Test 2: Edit template");
		String template = wTempData.getWikiTemplateRandom();
		String newTitle=txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSettingMg.editTemplate(template, newTitle, "", "");
		waitForAndGetElement(wSettingMg.ELEMENT_EDIT_TEMPLATE.replace("{$template}",newTitle));
		/*Step Number: 1
		 *Step Name: Step 1: Edit template
		 *Step Description: 
			- Click on Browse at top right and select Wiki Settings action
			- Click Template link 
			- Choose a template in list and click edit icon
			- Change something and click Save template
		 *Input Data: 

		 *Expected Outcome: 
			- Template is edited successfully and listed in Template table*/ 

		info("Test 4: Delete template");
		/*Step Number: 1
		 *Step Name: Step 1: Delete template
		 *Step Description: 
			- Click on Browse at top right and select Wiki Settings action
			- Click Template link 
			- Select a template in list and click Delete
			- Click OK in the confirmation message to accept your deletion.
		 *Input Data: 

		 *Expected Outcome: 
			- The Deleted template is not displayed in list*/ 
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSettingMg.deleteTemplate(newTitle);
	}

	/**
	 *<li> Case ID:122833.</li>
	 *<li> Test Case Name: Preview new template.</li>
	 *Jira issue : https://jira.exoplatform.org/browse/WIKI-991
	 */
	@Test
	public  void test03_PreviewNewTemplate() {
		info("Test 3: Preview new template");
		/*Step Number: 1
		 *Step Name: Step 1: Preview new template
		 *Step Description: 
			- Click on Browse at top right and select Wiki Settings action
			- Click Template link 
			-> Add more Template
			- Input all fields required and click Preview icon
		 *Input Data: 

		 *Expected Outcome: 
			- Display the preview mode of the currently template*/ 
		hp.goToWiki();
		wHome.goToWikiSettingPage();
	}

	/**
	 *<li> Case ID:122840.</li>
	 *<li> Test Case Name: Add Permission for Wiki.</li>
	 *<li> Pre-Condition: A wiki page is created at intranet wiki.</li>
	 *<li> Case ID:122842.</li>
	 *<li> Test Case Name: Edit permission for Wiki.</li>
	 *<li> Case ID:122843.</li>
	 *<li> Test Case Name: Delete permission for Wiki.</li>
	 */
	@Test
	public  void test05_06_07_AddEditDeletePermissionForWiki() {
		info("Test 5: Add Permission for Wiki");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki,wiki);
		wikiMg.saveAddPage();

		/*Step Number: 1
		 *Step Name: Step 1: Add Permission
		 *Step Description: 
			- Check the header (top bar) of the Wiki
			- Click Browse button at top right and select Wiki Settings action, choose Permission tab
			- Click select user/select group/select membership then click Add
			- Tick permission for her/him
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			New permission is added in list*/
		wHome.goToPermissions();
		click(wHome.ELEMENT_REMOVE_PERMISSION.replace("{$name}", "any"));
		PlfPerm.selectUserPermission("mary", 1);
		click(wHome.ELEMENT_ADD_PERMISSION);
		click(wHome.ELEMENT_SAVE_PERMISSION);
		/*Step number: 2
		 *Step Name: Step 2: Check permission of added user
		 *Step Description: 
			- Login as user or member of the group above 
			- Go to wiki application
			- Open the wiki page at step 1
		 *Input Data: 

		 *Expected Outcome: 
			- This user can do actions as permission set in step 1.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		info("Check if mary can see the page");
		hp.goToWiki();
		wHome.selectAPage(wiki);

		info("Test 6: Edit permission for Wiki");
		// Give the edit permission for mary
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToWiki();
		wHome.selectAPage(wiki);
		wHome.goToPermissions();
		check(By.xpath(wHome.ELEMENT_CHECK_PERMISSION_EDIT_PAGE.replace("{$name}", "mary")),2);
		click(wHome.ELEMENT_SAVE_PERMISSION);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToWiki();
		wHome.selectAPage(wiki);
		// check if mary has the rights
		wHome.goToEditPage();

		info("Test 7: Delete permission for Wiki");
		// remove the permission for mary
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToWiki();
		wHome.selectAPage(wiki);
		wHome.goToPermissions();
		click(wHome.ELEMENT_REMOVE_PERMISSION.replace("{$name}", "mary"));
		click(wHome.ELEMENT_SAVE_PERMISSION);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToWiki();
		waitForElementNotPresent(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);

		// delete data
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToWiki();
		wHome.selectAPage(wiki);
		wHome.deleteWiki(wiki);
	}

	/**
	 *<li> Case ID:122844.</li>
	 *<li> Test Case Name: Add Permission for space wiki.</li>
	 *<li> Case ID:122846.</li>
	 *<li> Test Case Name: Edit permission for space wiki.</li>
	 *<li> Case ID:122845.</li>
	 *<li> Test Case Name: Delete permission for space wiki.</li>
	 */
	@Test
	public void test08_09_AddEditPermissionForSpaceWiki() {
		info("Test 8: Add Permission for space wiki");
		String space =txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link="";
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space, "");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki,wiki);
		wikiMg.saveAddPage();
		wHome.goToPermalink();
		link=wHome.getPermalink();

		/*Step Number: 1
		 *Step Name: Step 1: Add Permission
		 *Step Description: 
			- Open a page of the space that you want to set the permissions.
			- Click More 
			-> Page Permission
			- Choose select user/select group/select membership then click Add
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			New permission is added in list*/
		wHome.goToPermissions();
		PlfPerm.selectUserPermission("mary", 1);
		click(wHome.ELEMENT_ADD_PERMISSION);
		click(wHome.ELEMENT_SAVE_PERMISSION);
		/*Step number: 2
		 *Step Name: Step 2: Check permission of added user
		 *Step Description: 
			- Login as user or member of the group above 
			- Open the wiki page at step 1
		 *Input Data: 

		 *Expected Outcome: 
			- This user can do actions as permission set in step 1.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(link);
		waitForAndGetElement(By.xpath(wHome.ELEMENT_WIKI_HOME_PAGE_TITLE.replace("${title}", wiki)));

		info("Test 10 Edit permission for space wiki");

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.selectAPage(wiki);
		wHome.goToPermissions();
		// mary can now edit the page
		check(wHome.ELEMENT_CHECK_PERMISSION_EDIT_PAGE.replace("{$name}", "mary"),2);
		click(wHome.ELEMENT_SAVE_PERMISSION);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(link);
		wHome.goToEditPage();
		magAc.signOut();

		info("Test 9: Delete permission for space wiki");
		magAc.signIn(DATA_USER1, DATA_PASS);

		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.selectAPage(wiki);
		wHome.goToPermissions();
		click(wHome.ELEMENT_REMOVE_PERMISSION.replace("{$name}", "mary"));
		click(wHome.ELEMENT_SAVE_PERMISSION);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(link);
		waitForAndGetElement(wHome.ELEMENT_WIKI_PAGE_NOT_FOUND);

		/*// delete data

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToMySpaces();
		spaMg.deleteSpace(space, false);*/
	}
}