package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Wiki_Information extends Wiki_TestConfig {
	/**
	 *<li> Case ID:122813.</li>
	 *<li> Test Case Name: View General Page information</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=1)
	public  void test01_ViewGeneralPageInformation() {
		info("Test 01: View General Page information");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1:  Check Page info
		 *Step Description: 
			- Create a new page
			- Edit this page
			- Open this page
			- Focus Page info at top area
		 *Input Data: 

		 *Expected Outcome: 
			- The total number of revisions (eg V1, V2, V3) and this reversion is clickable to open page history 
			- Show full name of the original creator of the page and time page is created (eg, Added by John Smith at Mar 3, 2014 3:09 PM)
			- Last time the page was edited with a link to view changes (Eg, Last modified by John Smith at Mar 4, 2014 3:09 PM )
			- Restricted link by default
			- The total number of attachments as a link to open the attachments*/ 
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Verify that The total number of revisions (eg V1, V2, V3) and this reversion is clickable to open page history  ");
		wHome.viewInformationTable(title, "V1");
		info("Show full name of the original creator of the page");
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_INFORMATION_TABLE_CONTENT
				.replace("${reversion}", "v.1")
				.replace("${text}", "John Smith"),2000,0);
		info("time page is created");
		String date = getDateByTextFormat("MM dd, yyyy");
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_INFORMATION_TABLE_CONTENT
				.replace("${reversion}", "v.1").replace("${text}", date),2000,0);
		
		info("Edit the title of the wiki page");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,"");
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		
		info("Last time the page was edited with a link to view changes");
		String info = "Last modified by John Smith at "+date+"";
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_INFORMATION_AREA_EDIT_INFOR.replace("${info}",info),2000,0);
		
		info("Restricted link by default: Public");
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_INFORMATION_AREA_RESTRICTED_STATUS.replace("${status}","Public"),2000,0);
		
		info("The total number of attachments as a link to open the attachments");
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_INFORMATION_AREA_TOTAL_ATTACHEDFILES.replace("${number}","0"),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}

	/**
	 *<li> Case ID:122822.</li>
	 *<li> Test Case Name: View Page history to compare versions</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=2)
	public  void test02_ViewHistoryToCompareVersions() {
		info("Test 02: View Page history to compare versions");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Check page history
		 *Step Description: 
			- Add new page 
			- Edit  this page some times
			- Open an existing page by clicking on page name in navigation tree
			- Click More ->  [Page info]
			- Click [View Page History]
			- To compare two versions, select two check boxes corresponding to each relevant version, click [Compare the selected versions]

		 *Input Data: 

		 *Expected Outcome: 
			- A page which shows the changes between these two versions will be displayed.
			- The changes between two versions will be marked with colours:
			    +  Words/lines which are red-highlighted with strike-throughs indicate that they were removed.
			    +  Words/lines highlighted in green indicate that they were added.*/ 
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
	
		
		info("Edit the page first time");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,newTitle);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		
		info("Edit the page second time");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle1,newTitle1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle1),2000,0);
		
		info("Edit the page third time");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle2,newTitle2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle2),2000,0);
		
		info("Open Page info");
		wHome.goToPageInformation();
		
		info("Open Page history");
		wPageInfo.goToPageHistory();
		
		info("Open Compare reversion page");
		wPageInfo.compareTwoReversion("v.1","v.4");
		info("Compare reversion page is shown");
		waitForAndGetElement(wHome.ELEMENT_WIKI_PAGE_COMPARE_REVERSION_TITLE,2000,1);
		
		info("Verify that  Words/lines which are red-highlighted with strike-throughs indicate that they were removed");
		waitForAndGetElement(
				wikiMg.ELEMENT_PAGE_HISTORY_COMPARE_CONTENT.replace("${text}",
						content),2000,0).getCssValue("background-color").contains(
				"rgb(247,217,216)");
		
		info("Verify that Words/lines highlighted in green indicate that they were added");
		waitForAndGetElement(
				wikiMg.ELEMENT_PAGE_HISTORY_COMPARE_CONTENT.replace("${text}",
						newTitle2),2000,0).getCssValue("background-color").contains(
				"rgb(219,245,209)");
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle2);
	}
	
	/**
	 *<li> Case ID:122870.</li>
	 *<li> Test Case Name: View Page info</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=3)
	public  void test03_ViewPageInfo() {
		info("Test 03: View Page info");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String child1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String child2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Check page history
		 *Step Description: 
			- Create a new page under wiki home, eg WikiPage1
			- Edit this page
			- Create 2 child page for it, child 1 and child 2
			- Open this page
			- Click [More][Page Info]

		 *Input Data: 

		 *Expected Outcome: 
			Page information is shown
			- Summary: Title, author, Last changed by
			- Related page: list all related page. A button [Add More Relation] 
			- Hierarchy: list parent page and child
			- Recent changes: list all revisions and a button [View Page History] */ 
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,title);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
	
		
		info("Create child 1 for wiki page");
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(child1,child1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",child1),2000,0);
		
		info("Create child 2 for wiki page");
		wHome.selectAPage(title);
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(child2,child2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",child2),2000,0);
		
		info("Open Page info");
		String date = getDateByTextFormat("MM dd, yyyy");
		wHome.goToPageInformation();
		
		info("Summary: Title, author, Last changed by");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_SUMMARY_TITLE.replace("${content}",title),2000,0);
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_SUMMARY_AUTHOR.replace("${fullname}","John Smith").replace("${date}",date),2000,0);
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_SUMMARY_LAST_CHANGED.replace("${fullname}","John Smith").replace("${date}",date),2000,0);
		
		info("Related page: list all related page. A button [Add More Relation]");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_RELATED,2000,0);
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS,2000,0);

		info("Hierarchy: list parent page and child");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_HIERARCHY_PARENT_PAGES,2000,0);
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_HIERARCHY_CHILD_PAGES.replace("${child}",child1),2000,0);
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_HIERARCHY_CHILD_PAGES.replace("${child}",child2),2000,0);
		
		info("Recent changes: list all revisions and a button [View Page History]");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFOR_RECENT_CHANES,2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122854.</li>
	 *<li> Test Case Name: Add relations with Intranet portal</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=5)
	public  void test06_AddRelationWithIntranetPortal() {
		info("Test 06: Add relations with Intranet portal");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space 
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1" and "Space 2"
		 *Input Data: 

		 *Expected Outcome: 
			New space  is added successfully*/ 
		
		/*Step Number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 and space 2
		 *Step Description: 
			- Go to Space 1/ wiki and Space2/wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data: 

		 *Expected Outcome: 
			Add new page successfully*/ 
		
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Add new wiki page for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title1,title1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		info("Create space 2 and wiki page 2");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space2,space2);
		info("Add new wiki page for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title2,title2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		/*Step Number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			 Page 1 is displayed in the wiki*/ 
		
		/*Step Number: 4
		 *Step Name: Step 4: Go to Page Info
		 *Step Description: 
			- Open "More" Menu -> "Page Info"
		 *Input Data: 

		 *Expected Outcome: 
			Page infor of Page 1 are diplayed*/ 
		
		info("Open wiki page 1");
		spaHome.goToSpace(space1);
		spaHome.goToWikiTab();
		info("Open page 1 and Go to Page Info");
		wHome.goToAPage(title1);
		wHome.goToPageInformation();
		
		/*Step Number: 5
		 *Step Name: Step 5: Go to Related page form
		 *Step Description: 
			-  Click "Add More Relations" on Related Page form
		 *Input Data: 

		 *Expected Outcome: 
			The popup to select a related page is displayed*/ 
		info("Go to Related page form");
		wPageInfo.goToAddRelations();
		
		/*Step Number: 6
		 *Step Name: Step 6: Check when add relations from intranet portal
		 *Step Description: 
			- Open Space switcher component
			- Select "intranet"
			- Click on "Select" button

		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki tree of "Space 2" is displayed on the container below the space switcher
			- Popup is closed
			- "intranet's portal" is added as a related pages on page info layout*/ 
		
		info("Wiki tree of 'Space 2' is displayed on the container below the space switcher");
		String spaces =space1+"/"+space2;
		wikiMg.checkAddRelationDropDownList(spaces);
		info("Check when add relations from 2 different spaces");
		wPageInfo.addRelations("Intranet","Wiki Home");
		info("intranet's portal is added as a related pages on page info layout");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}","portal").replace("${col2}","Wiki Home"),2000,0);
		
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);
		spaMg.deleteSpace(space2,false);*/
	    
	}	
	
	
	
	/**
	 *<li> Case ID:122853.</li>
	 *<li> Test Case Name: Add relations from 2 different spaces</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=6)
	public void test07_AddRelationFrom2DiffenentSpaces() {
		info("Test 07: Add relations from 2 different spaces");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space 
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1" and "Space 2"
		 *Input Data: 

		 *Expected Outcome: 
			New space  is added successfully*/ 
		
		/*Step Number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 and space 2
		 *Step Description: 
			- Go to Space 1/ wiki and Space2/wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data: 

		 *Expected Outcome: 
			Add new page successfully*/ 
		
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Add new wiki page for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title1,title1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		info("Create space 2 and wiki page 2");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space2,space2);
		info("Add new wiki page for space 2");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title2,title2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		/*Step Number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			 Page 1 is displayed in the wiki*/ 
		
		/*Step Number: 4
		 *Step Name: Step 4: Go to Page Info
		 *Step Description: 
			- Open "More" Menu -> "Page Info"
		 *Input Data: 

		 *Expected Outcome: 
			Page infor of Page 1 are diplayed*/ 
		
		info("Open wiki page 1");
		spaHome.goToSpace(space1);
		spaHome.goToWikiTab();
		info("Open page 1 and Go to Page Info");
		wHome.goToAPage(title1);
		
		/*Step Number: 5
		 *Step Name: Step 5: Go to Related page form
		 *Step Description: 
			-  Click "Add More Relations" on Related Page form
		 *Input Data: 

		 *Expected Outcome: 
			The popup to select a related page is displayed*/ 
		info("Go to Related page form");
		wPageInfo.goToAddRelations();
		
		/*Step Number: 6
		 *Step Name: Step 6: Check when add relations from 2 different spaces
		 *Step Description: 
			- Open Space switcher component
			- Select "Space 2"
			- Select "Page 2"
			- Click on "Select" button

		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki tree of "Space 2" is displayed on the container below the space switcher
			- Popup is closed
			- "Page 2" is added as a related pages on page info layout*/ 
		
		info("Wiki tree of 'Space 2' is displayed on the container below the space switcher");
		String spaces =space1+"/"+space2;
		wikiMg.checkAddRelationDropDownList(spaces);
		info("Check when add relations from 2 different spaces");
		wPageInfo.addRelations(space2,title2);
		info("intranet's portal is added as a related pages on page info layout");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}",space2).replace("${col2}",title2),2000,0);
		
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);
		spaMg.deleteSpace(space2,false);*/
	    
	}	
	
	/**
	 *<li> Case ID:122856.</li>
	 *<li> Test Case Name: Add relation in the case there is no space</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=4)
	public void test05_AddRelationInCaseThereIsNoSpace() {
		info("Test 05: Add relation in the case there is no space");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1:  Add new page for wiki
		 *Step Description: 
			- Login portal
			- Go to wiki page
			- Add new page 
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/ 
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title1,title1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		/*Step Number: 2
		 *Step Name: Step 2: Go to Page Info
		 *Step Description: 
			- Open "More" Menu -> "Page Info"
		 *Input Data: 

		 *Expected Outcome: 
			- Page infos of Page 1 are displayed*/ 
		
		info("Go to Page Info");
		wHome.goToPageInformation();
		
		/*Step Number: 3
		 *Step Name: Step 3: Check relation when have no space
		 *Step Description: 
			  - Click "Add More Relations" on Related Page form
			  - Open Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
			 - The popup to select a related page is displayed
			 - The list of space switcher options is displayed and space list are not displayed in the space switcher list*/ 
		info("Go to Related page form");
		wPageInfo.goToAddRelations();
		info("Click on Drop down");
		waitForAndGetElement(wikiMg.ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN,2000,0).click();
		info("Verify that Intranet location is shown is the list");
		waitForAndGetElement(wikiMg.ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}","Intranet"),2000,0);
		info("Verify that My wiki location is shown is the list");
		waitForAndGetElement(wikiMg.ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}","My Wiki"),2000,0);
		info("Verify that The list of space switcher options is displayed and space list are not displayed in the space switcher list");
		waitForAndGetElement(wikiMg.ELEMENT_ADD_RELATED_POPUP_DROPDOWN_NOSPACE,2000,0);
		waitForAndGetElement(wikiMg.ELEMENT_ADD_RELATED_POPUP_CLOSE_BTN,2000,0).click();
		
		info("Delete the page");
		wHome.deleteWiki(title1);
	    
	}	
	
	/**
	 *<li> Case ID:122855.</li>
	 *<li> Test Case Name: Add relations in same space</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=7)
	public void test08_AddRelationWithSameSpace() {
		info("Test 08: Add relations in same space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space 
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			New space  is added successfully*/ 
		
		/*Step Number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1
		 *Step Description: 
			- Go to Space 1/ wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data: 

		 *Expected Outcome: 
			Add new pages successfully*/ 
		
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title1,title1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		info("Add new wiki page 2 for space 1");
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title2,title2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		/*Step Number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			 Page 1 is displayed in the wiki*/ 
		
		/*Step Number: 4
		 *Step Name: Step 4: Go to Page Info
		 *Step Description: 
			- Open "More" Menu -> "Page Info"
		 *Input Data: 

		 *Expected Outcome: 
			Page infor of Page 1 are diplayed*/ 
		
		info("Open page 1 and Go to Page Info");
		wHome.goToHomeWikiPage();
		wHome.goToAPage(title1);
		wHome.goToPageInformation();
		
		/*Step Number: 5
		 *Step Name: Step 5: Go to Related page form
		 *Step Description: 
			-  Click "Add More Relations" on Related Page form
		 *Input Data: 

		 *Expected Outcome: 
			The popup to select a related page is displayed*/ 
		info("Go to Related page form");
		wPageInfo.goToAddRelations();
		
		/*Step Number: 6
		 *Step Name: Step 6: Check when add relations from same space
		 *Step Description: 
			- Open Space switcher component
			- Select "Space 1"
			- Select "Page 2"
			- Click on "Select" button

		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki tree of "Space 1" is displayed on the container below the space switcher
			- Popup is closed
			- "Page 2" is added as a related pages on page info layout*/ 
		
		info("Check when add relations from same spaces");
		wPageInfo.addRelations(space1,title2);
		info("Page 2 is added as a related pages on page info layout");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}",space1).replace("${col2}",title2),2000,0);
		
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);*/
	    
	}
	/**
	 *<li> Case ID:122857.</li>
	 *<li> Test Case Name: Delete relations</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=8)
	public void test09_DeleteRelation() {
		info("Test 09: Delete relations");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space 
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			New space  is added successfully*/ 
		
		/*Step Number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1
		 *Step Description: 
			- Go to Space 1/ wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data: 

		 *Expected Outcome: 
			Add new pages successfully*/ 
		
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title1,title1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		info("Add new wiki page 2 for space 1");
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title2,title2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		/*Step Number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			 Page 1 is displayed in the wiki*/ 
		
		/*Step Number: 4
		 *Step Name: Step 4: Go to Page Info
		 *Step Description: 
			- Open "More" Menu -> "Page Info"
		 *Input Data: 

		 *Expected Outcome: 
			Page infor of Page 1 are diplayed*/ 
		
		info("Open page 1 and Go to Page Info");
        wHome.goToPageInformation();		
		/*Step Number: 5
		 *Step Name: Step 5: Go to Related page form
		 *Step Description: 
			-  Click "Add More Relations" on Related Page form
		 *Input Data: 

		 *Expected Outcome: 
			The popup to select a related page is displayed*/ 
		info("Go to Related page form");
		wPageInfo.goToAddRelations();
		
		/*Step Number: 6
		 *Step Name: Step 6: Check when add relations from same space
		 *Step Description: 
			- Open Space switcher component
			- Select "Space 1"
			- Select "Page 2"
			- Click on "Select" button

		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki tree of "Space 1" is displayed on the container below the space switcher
			- Popup is closed
			- "Page 2" is added as a related pages on page info layout*/ 
		
		info("Check when add relations from same spaces");
		wPageInfo.addRelations(space1,title2);
		info("Page 2 is added as a related pages on page info layout");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}",space1).replace("${col2}",title2),2000,0);
		
		/*Step Number: 7
		 *Step Name: Step 7: Add relation with portal
		 *Step Description: 
			- Continue add more relation for portal

		 *Input Data: 

		 *Expected Outcome: 
			Relation of is added as a related pages on page info layout*/ 
		
		info("Go to Related page form");
		wPageInfo.goToAddRelations();
		info("Add Relations with portal");
		wPageInfo.addRelations("Intranet","Wiki Home");
		waitForAndGetElement(wikiMg.ELEMENT_PAGE_INFO_RELATED_TABLE_CONTENT.replace("${col1}","portal").replace("${col2}","Wiki Home"),5000,1);
		
		
		/*Step Number: 8
		 *Step Name: Step 8: Check delete relations
		 *Step Description: 
			- Select the relation which want to delete in Related Page form
			- Click Remove icon 
			- Click OK to confirm messgae

		 *Input Data: 

		 *Expected Outcome: 
			- The relation is deleted and don't show in Related Page form*/ 
		info("The relation is deleted and don't show in Related Page form");
		wPageInfo.deleteRelation(title2);
		
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);*/ 
	}	
	
	/**
	 *<li> Case ID:122872.</li>
	 *<li> Test Case Name: Version creation</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test (priority=9)
	public  void test04_VersionCreation() {
		info("Test 04: Version creation");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(1);
		
		/*Step Number: 1
		 *Step Name: Step 1:  Create new wiki page
		 *Step Description: 
			- Add new wiki page 
		 *Input Data: 

		 *Expected Outcome: 
			New wiki page is created with version is V1*/ 
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,title);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Verify that New wiki page is created with version is V1");
		wHome.viewInformationTable(title, "V1");
		
		/*Step Number: 2
		 *Step Name: Step 2:  Edit title/wiki content by click [Edit] icon
		 *Step Description: 
			- Click edit icon
			- Edit title or content or edit both
			- Save
			- View page version  
		 *Input Data: 

		 *Expected Outcome: 
			New version is created*/ 
		
		info("Edit the page");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,newTitle);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		
		info("Verify that New wiki page is created with version is V2");
		wHome.viewInformationTable(newTitle, "V2");
		
		/*Step Number: 3
		 *Step Name: Step 3: upload/delete attachment
		 *Step Description: 
			- Click attachment icon at information area at the top of page
			- Add new attachment
			- Delete attachment
			- View page version  
		 *Input Data: 

		 *Expected Outcome: 
			No version is created*/ 
		info("Click on attachment icon at information area at the top of page");
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_INFORMATION_AREA_TOTAL_ATTACHEDFILES.replace("${number}","0"),2000,0).click();
		info("Add new attachment");
		//rtMode.attachFile("TestData/"+link);
		sourceEditor.attachFile("TestData/"+link);
		info("Delete attachment");
		wikiMg.deleteAttachmentFile();
		
		info("Verify that New wiki page is created with version still is V2. No version is created");
		wHome.viewInformationTable(newTitle, "V2");
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}
}