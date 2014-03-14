/**
 * Generated by havtt at 2014/01/07 08:49:49
 *
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.exoplatform.selenium.platform.gatein.functional.managepages;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.DashBoard;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author havtt
 *
 */
public class Gatein_ManagePages_Create extends DashBoard{

	ManageAccount magAc;
	NavigationToolbar navTool;
	PageManagement pageMag;
	PageEditor pageE;
	Button button;
	ManageAlert magAlert;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		navTool = new NavigationToolbar(driver);
		pageMag = new PageManagement(driver);
		pageE = new PageEditor(driver);
		button = new Button(driver);
		magAlert = new ManageAlert(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 *<li> Case ID:74244.</li>
	 *<li> Test Case Name: Create new user's page with existing page name for the same user.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test02_CreateNewUsersPageWithExistingPageNameForTheSameUser() {
		info("Test 2: Create new user's page with existing page name for the same user");
		String pageName = "FuncManagePageName09";
		String pageTitle = "FuncManagePageTitle09";
		String groupPath = "Platform /Content Management ";
		String membership = "*";

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Show form to add new page
		 *Input Data: 
			In Pages management form:
			- Click Add new page at the bottom of pages list
		 *Expected Outcome: 
			Add new page form appears:
			- Owner type "portal" is selected as default
			- The name of the current portal is automatically selected for Owner Id, ensuring the edit permissions are assigned to users who can edit the current portal.*/
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Create the first page
		 *Input Data: 
			Create page for current user
		 *Expected Outcome: 
			Create new page successfully*/
		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Create same name page
		 *Input Data: 
			Create another page for current user has the same name with created page at step 2
		 *Expected Outcome: 
			Show message page name already exists, user has to choose another name*/ 
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page for portal with Dashboard layout");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_DASHBOARD, true);

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle);
	}


	/**
	 *<li> Case ID:74222.</li>
	 *<li> Test Case Name: Check displaying of the page which create  when user has right access on the page and porlet but has no right access on the container which contains the portlet.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test04_CheckDisplayingOfThePageWhichCreateWhenUserHasRightAccessOnThePageAndPorletButHasNoRightAccessOnTheContainerWhichContainsThePortlet() {
		info("Test 4: Check displaying of the page which create  when user has right access on the page and porlet but has no right access on the container which contains the portlet");
		String pageName = "Page74222";
		String category = "Administration";
		String portletId = "Administration/RegisterPortlet";
		String portletTitle = "Register New Account";
		String url = baseUrl + "/intranet/home/" + pageName;
		String username1 = DATA_USER2;
		String password1 = DATA_PASS;

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Show form to add new page wizard, step 1
		 *Input Data: 
			- Log in 
			- Select page of user/group/portal
			- Click add new page
		 *Expected Outcome: 
			Show form at step 1*/
		info("Add new page by Wizard");
		navTool.goToPageCreationWizard();
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Complete step 1
		 *Input Data: 
			- Select parent node or not
			- Enter valid data 
			- Click next
		 *Expected Outcome: 
			- Step 1 is completed*/
		pageE.gotoPageEditorAndSelectLayout(pageName, 2);
		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Set access right for container on page
		 *Input Data: 
			- In step 2, select a layout and click Next
			- in step 3: 
			- Choose Container tab
			- Click Edit container
			- Select [access permission] tab
			- Select user who can't access page,can access the container
			- Click Save
		 *Expected Outcome: 
			Set Access permission for container successfully*/

		info("Edit permission of page, portlet and container");
		pageE.goToEditContainer(ELEMENT_CONTAINER_ROW_0);

		Utils.pause(2000);
		//Edit permission of portlet

		click(ELEMENT_CONTAINER_PERMISSION_TAB);
		check(ELEMENT_ACCESS_PERMISSION_MAKEITPUBLIC,2);
		button.save();
		//Edit permission of page
		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Set access right for portlet on page
		 *Input Data: 
			- Select Application tab in Page editor
			- Drag & drop some portlet into container
			- Move mouse over the portlet and click on [edit] icon on mark layer of the portlet
			- Select [Access permission] tab
			- Select user who can access page, can access the portlet
			- Click [Save]
			- Click Save
		 *Expected Outcome: 
			Set Access permission for portlet successfully*/

		pageE.addNewPortlet(category,portletId,By.xpath(ELEMENT_CONTAINER_ROW_0));
		pageE.goToEditPortlet(By.className("portletLayoutDecorator"));
		click(ELEMENT_ACCESS_PERMISSION_TAB);
		check(ELEMENT_ACCESS_PERMISSION_MAKEITPUBLIC,2);
		button.saveAndClose();
		pageE.finishEditLayout();

		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Check displaying of page which createwhen user has right access on page and container but has no right access on the portlet on the container
		 *Input Data: 
			- Login by user can access the page and portlet but can't access the container
			- Select page above
		 *Expected Outcome: 
			- User can see some portlets on page except portlet in the container that he does not have access right*/ 
		info("Check view permission of user who is not granted Access Permission to container toward the page");
		magAc.signOut();
		magAc.signIn(username1, password1);

		info("Go to the page");
		driver.get(url);
		isTextPresent(portletTitle);

		info("Restore data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToManagePages();
		pageMag.deletePage(PageType.PORTAL, pageName);
	}

	/**
	 *<li> Case ID:74101.</li>
	 *<li> Test Case Name: Create new portal page with name is the same with existing group page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test05_CreateNewPortalPageWithNameIsTheSameWithExistingGroupPage() {
		info("Test 5: Create new portal page with name is the same with existing group page");
		String pageName = "FuncManagePageName08";
		String pageTitle1 = "FuncManagePageTitle0801";
		String pageTitle2 = "FuncManagePageTitle0802";
		String groupPath = "Platform /Content Management ";
		String ownerId = "/organization/management/executive-board";
		String membership = "*";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add page for group
		 *Input Data: 
			- Login by manager of at least a group & can access Page management
			- Create page for his group
		 *Expected Outcome: 
			Create page successfully*/
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page #1 for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle2, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true, ownerId);



		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add same name page
		 *Input Data: 
			- Login by user has right to access page management & can edit current portal
			- Create a page for current portal with the same name as created page at step 1
		 *Expected Outcome: 
			Create page successfully*/ 
		info("Add page #2 for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle1, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle1);
		pageMag.deletePage(PageType.GROUP, pageTitle2);
	}


	/**
	 *<li> Case ID:74081.</li>
	 *<li> Test Case Name: Create same name portal pages in different portals.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test07_CreateSameNamePortalPagesInDifferentPortals() {
		info("Test 7: Create same name portal pages in different portals");
		String pageName = "FuncPageName07";
		String pageTitle1 = "FuncPageTitle0701";
		String pageTitle2 = "FuncPageTitle0702";
		String groupPath1 = "Platform /Content Management ";
		String groupPath2 = "Platform /Development ";
		String membership = "*";
		String url = baseUrl + "/acme";

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add page
		 *Input Data: 
			- Login by user who has right to edit current portal & can access page management
			- Create new page for current portal
		 *Expected Outcome: 
			Create page successfully*/
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add portal page #1 for portal Intranet");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle1, true, null, groupPath1, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, false);

		info("Navigate to ACME portal");
		driver.get(url);
		navTool.goToManagePages();


		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add same name page
		 *Input Data: 
			- Change another portal to use
			- Create a page for current portal with the same name as created page at step 1
		 *Expected Outcome: 
			Create page successfully*/ 
		info("Add portal page #2 for different portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle2, true, null, groupPath2, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, false);

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle1);
		pageMag.deletePage(PageType.PORTAL, pageTitle2);
	}



	/**
	 *<li> Case ID:74069.</li>
	 *<li> Test Case Name: Create same name portal pages in the same portal.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test08_CreateSameNamePortalPagesInTheSamePortal() {
		info("Test 8: Create same name portal pages in the same portal");
		String pageName = "FuncManagePageName06";
		String pageTitle1 = "FuncManagePageTitle0601";
		String pageTitle2 = "FuncManagePageTitle0602";
		String groupPath = "Platform /Content Management ";
		String membership = "*";


		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add page
		 *Input Data: 
			- Login by user who has right to edit current portal & can access page management
			- Create new page for current portal
		 *Expected Outcome: 
			Create page successfully*/
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page #1 for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle1, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add same name page
		 *Input Data: 
			Create another page for the same portal with the same name as page name at step 1
		 *Expected Outcome: 
			Show message alerts page name already exists*/ 
		info("Add page #2 for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle2, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, false);
		isElementPresent(ELEMENT_PAGE_EXIST_WARNING_MSG);
		button.ok();

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle1);
	}



	/**
	 *<li> Case ID:74056.</li>
	 *<li> Test Case Name: Create new page for portal with valid values.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test09_CreateNewPageForPortalWithValidValues() {
		info("Test 9: Create new page for portal with valid values");
		String pageName = "FuncManagePageName05";
		String pageTitle = "FuncManagePageTitle05";
		String groupPath = "Platform /Content Management ";
		String membership = "*";

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Show form to add new page
		 *Input Data: 
			In Add new page form: 
			- Choose â€œPortalâ€ for Owner type in Page Setting
		 *Expected Outcome: 
			- Owner id is current portal's name, can not change
			- Permission Setting tab appears*/


		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Setup page layout
		 *Input Data: 
			- Input valid Page Name
			- Select Page Layout tab
		 *Expected Outcome: 
			Layouts list is displayed in right pane, sample image for selecting layout is displayed in left pane*/


		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Show form to assign access right for more users
		 *Input Data: 
			- Select Permission Setting/Access Permission
			- Click Add Permission
		 *Expected Outcome: 
			A from to select group & corresponding membership type to assign access right is shown*/


		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Assign access right
		 *Input Data: 
			- Choose group from left pane
			- Choose membership type from right pane
		 *Expected Outcome: 
			Selected group & membership type is added in to Access list*/


		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Check remove access right for a group of user after assigned
		 *Input Data: 
			- Click Delete icon corresponding to group/membership type in Access list
			- Click OK to confirm
		 *Expected Outcome: 
			Corresponding group/membership type is removed from Access list*/


		/*Step number: 6
		 *Step Name: -
		 *Step Description: 
			Step 6: Complete adding portal page
		 *Input Data: 
			Click Save
		 *Expected Outcome: 
			- Add new page successfully
			- New page is added into existing pages list with input information*/ 

		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle);
	}



	/**
	 *<li> Case ID:74044.</li>
	 *<li> Test Case Name: Create new page for group with valid values.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test10_CreateNewPageForGroupWithValidValues() {
		info("Test 10 Create new page for group with valid values");
		String pageName = "FuncManagePageName04";
		String pageTitle = "FuncManagePageTitle04";
		String groupPath = "Platform /Content Management ";
		String ownerId = "/organization/management/executive-board";
		String membership = "*";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Show form to add new page
		 *Input Data: 
			In Add new page form: 
			- Choose â€œGroupâ€ for Owner type in Page Setting
			- Choose a group from list
		 *Expected Outcome: 
			- Owner id is current group's name, can not change
			- Permission Setting tab appears*/

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Setup page layout
		 *Input Data: 
			- Input valid Page Name
			- Select Page Layout tab
		 *Expected Outcome: 
			Layouts list is displayed in right pane, sample image for selecting layout is displayed in left pane*/


		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Show form to assign access right for more users
		 *Input Data: 
			- Select Permission Setting/Access Permission
			- Click Add Permission
		 *Expected Outcome: 
			A from to select group & corresponding membership type to assign access right is shown*/


		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Assign access right
		 *Input Data: 
			- Choose group from left pane
			- Choose membership type from right pane
		 *Expected Outcome: 
			Selected group & membership type is added in to Access list*/


		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Check remove access right for a group of user after assigned
		 *Input Data: 
			- Click Delete icon corresponding to group/membership type in Access list
			- Click OK to confirm
		 *Expected Outcome: 
			Corresponding group/membership type is removed from Access list*/


		/*Step number: 6
		 *Step Name: -
		 *Step Description: 
			Step 6: Complete adding group page
		 *Input Data: 
			Click Save
		 *Expected Outcome: 
			- Add new page successfully
			- New page is added into existing pages list with input information*/ 
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true, ownerId);

		info("Restore data");
		pageMag.deletePage(PageType.GROUP, pageTitle);
	}



	/**
	 *<li> Case ID:74032.</li>
	 *<li> Test Case Name: Create group page with name the same with existing portal page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test11_CreateGroupPageWithNameTheSameWithExistingPortalPage() {
		info("Test 11 Create group page with name the same with existing portal page");
		String pageName = "FuncManagePageName03";
		String pageTitle1 = "FuncManagePageTitle0301";
		String pageTitle2 = "FuncManagePageTitle0302";
		String groupPath = "Platform /Content Management ";
		String ownerId = "/organization/management/executive-board";
		String membership = "*";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add page for portal
		 *Input Data: 
			- Login by user who has right to edit current portal & can access to "Page management" page
			- Create new page for current using portal
		 *Expected Outcome: 
			Create page successfully*/
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page #1 for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle1, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Create same name page
		 *Input Data: 
			- Login by another user who is manager of a group
			- Create new page for his group with name is the same with created page's name at step 1
		 *Expected Outcome: 
			Create page successfully*/ 
		info("Add page #2 for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle2, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true, ownerId);

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle1);
		pageMag.deletePage(PageType.GROUP, pageTitle2);

	}


	/**
	 *<li> Case ID:74009.</li>
	 *<li> Test Case Name: Create same name group pages in different groups.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test13_CreateSameNameGroupPagesInDifferentGroups() {
		info("Test 13 Create same name group pages in different groups");
		String pageName = "FuncManagePageName02";
		String pageTitle1 = "FuncManagePageTitle0201";
		String pageTitle2 = "FuncManagePageTitle0202";
		String ownerId1 = "/organization/management/executive-board";
		String ownerId2 = "/organization/employees";
		String groupPath1 = "Platform /Content Management ";
		String groupPath2 = "Platform /Development ";
		String membership = "*";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add page
		 *Input Data: 
			Login by user has right to access "Page Management" page
			- Create page for his group
		 *Expected Outcome: 
			Create page successfully*/
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page #1 for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle1, true, null, groupPath1, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true, ownerId1);

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Create same name page
		 *Input Data: 
			- Keep current user or login by manager of another group
			- Create new page for another group with name the same with created page at step 1
		 *Expected Outcome: 
			Add new page successfully*/ 
		info("Add page #2 for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle2, true, null, groupPath2, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true, ownerId2);

		info("Restore data");
		pageMag.deletePage(PageType.GROUP, pageTitle1);
		pageMag.deletePage(PageType.GROUP, pageTitle2);
	}



	/**
	 *<li> Case ID:73996.</li>
	 *<li> Test Case Name: Create same name group pages in the same group.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 08:49:49</li>
	 */
	@Test
	public  void test14_CreateSameNameGroupPagesInTheSameGroup() {
		info("Test 14 Create same name group pages in the same group");
		String pageName = "FuncPageName01";
		String pageTitle1 = "FuncPageTitle0101";
		String pageTitle2 = "FuncPageTitle0102";
		String ownerId = "/organization/management/executive-board";
		String groupPath = "Platform /Content Management ";
		String membership = "*";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add page
		 *Input Data: 
			- Login by user has right to access admin tool
			-bar(belong to (platform/administration), Page management & is manager of at least one group 
			- Go to group/administration/page management
			- Click Add new page
		 *Expected Outcome: 
			Create page successfully*/
		info("Go to Page Management");
		navTool.goToManagePages();
		info("Add page #1 for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle1, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true, ownerId);
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Create same name page
		 *Input Data: 
			- Keep current user
			- Create new page for his group again with the same name as the page created at step 1
		 *Expected Outcome: 
			Show message alerts page name already exists*/ 
		info("Add page #2 for Group");
		pageMag.addNewPageAtManagePages(PageType.GROUP, pageName, pageTitle2, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, false, ownerId);
		isElementPresent(ELEMENT_PAGE_EXIST_WARNING_MSG);
		magAlert.acceptAlert();
		info("Restore data");
		pageMag.deletePage(PageType.GROUP, pageTitle1);
	}


}