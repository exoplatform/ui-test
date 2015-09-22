package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.wiki.RichTextEditor.macroCategories;
import org.testng.annotations.Test;

public class Wiki_Macro_Edit_OtherCheck extends WIKI_TestConfig{
	/**
	*<li> Case ID:139266.</li>
	*<li> Test Case Name: Edit JIRA macro.</li>
	*<li> Pre-Condition: 
	*A wiki page with JIRA macro is added
	Source code: 
		{{jira URL="https://jira.exoplatform.org/"}}
		SOC-123
		ECMS-235
		{{/jira}}

	Rich Text: 
		URL="https://jira.exoplatform.org/
		Content: 
		SOC-123
		ECMS-235</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_EditJIRAMacro() {
		info("Test 1: Edit JIRA macro");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String macroContentCate = wikiMacroData.getDataMacroCategoriesByArrayTypeRandom(1);
		String macroJIRA = wikiMacroData.getWikiMacroByIndex(3);
		
		String macroJIRAURL = lnkData.getLinkByArrayTypeRandom(4);
		String macroJIRAContent1 = txData.getContentByIndex(20);
		
		String macroJIRAContent2 = txData.getContentByIndex(22);
		String macroJIRAContent3 = txData.getContentByIndex(23);
		
		String macroJIRAContent4 = txData.getContentByIndex(21);
		String macroJIRAContent5 = txData.getContentByIndex(24);
		String macroJIRAContent6 = txData.getContentByIndex(25);
		
		info("Create data test");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		wikiMg.goToRichTextEditor();
		richEditor.addSimplePage(title, "");
		info("Add JIRA macro for page");
		richEditor.goToMacro(macroContentCate, macroJIRA);
		richEditor.insertMacroJIRA(macroJIRAURL, macroJIRAContent1);
		richEditor.goToMacroCreateBtn();
		Utils.pause(5000);
		pressEndKey(this.driver);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		info ("Verify that page was created successfully with JIRA macro");
		wValidate.verifyPageContent(title, macroJIRAContent2);
		wValidate.verifyPageContent(title, macroJIRAContent3);
		arrayPage.add(title);

		/*Step number: 1
		*Step Name: Step 1:Edit JIRA macro
		*Step Description: 
			- Choose a page having JIRA macro 
			- Ensure the page is in the Rich Text mode
			- Select JIRA macro
			- Select Macro --> Edit Macro Properties or double click  JIRA Macro in content area
			- Change value for macro in the form
			- Click Apply
		*Input Data: 
			
		*Expected Outcome: 
			JIRA macro is updated with new change*/ 
		info("Edit a wiki page");
		wHome.goToAPage(title);
		wHome.goToEditPage();
		Utils.pause(5000);
		richEditor.selectJIRAMacro();
		richEditor.goToEditMacro();
		richEditor.insertMacroJIRA(macroJIRAURL, macroJIRAContent4);
		richEditor.goToMacroEditFormApplyBtn();
		pressEndKey(this.driver);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		info("Verify that JIRA macro was edited successfully in page");
		wValidate.verifyPageContent(title, macroJIRAContent5);
		wValidate.verifyPageContent(title, macroJIRAContent6);
 	}
	
	/**
	*<li> Case ID:139285.</li>
	*<li> Test Case Name: Collapse/Expand All Macro.</li>
	*<li> Pre-Condition: 
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CollapseExpandAllMacro() {
		info("Test 2: Collapse/Expand All Macro");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String macroContentCate = wikiMacroData.getDataMacroCategoriesByArrayTypeRandom(1);
		String macroJIRA = wikiMacroData.getWikiMacroByIndex(3);
		
		String macroJIRAURL = lnkData.getLinkByArrayTypeRandom(4);
		String macroJIRAContent1 = txData.getContentByIndex(20);
		
		String macroJIRAContent2 = txData.getContentByIndex(22);
		String macroJIRAContent3 = txData.getContentByIndex(23);
		
		String macroFormattingCate = wikiMacroData.getDataMacroCategoriesByArrayTypeRandom(3);
		String macroColor = wikiMacroData.getWikiMacroByIndex(9);
		
		String message = txData.getContentByArrayTypeRandom(1);
		String color = colorData.getclassNameByIndex(4);
		
		String macroJIRACollapse = wikiMacroData.getWikiCollapseByIndex(3);
		String macroColorCollapse = wikiMacroData.getWikiCollapseByIndex(9);
		
		/*Step number: 1
		*Step Name: Step 1: Create  a wiki page
		*Step Description: 
			- Connect to Intranet
			- Click Wiki on the left panel to go to Wiki page
			- Click [Add Page] --> [Blank Page]
		*Input Data: 
			
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode */ 
		info("Create data test");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		wikiMg.goToRichTextEditor();
		richEditor.addSimplePage(title, "");
		
		/*Step number: 2
		*Step Name: Step 2: Add some macros
		*Step Description: 
			- Input some text into content, then select Macro --> Insert Macro
			- Select some macros to add into page
			- Click Insert Macro
		*Input Data: 
			
		*Expected Outcome: 
			 Some macro are displayed correctly */
		info("Add JIRA Macro for page");
		richEditor.goToMacro(macroContentCate, macroJIRA);
		richEditor.insertMacroJIRA(macroJIRAURL, macroJIRAContent1);
		richEditor.goToMacroCreateBtn();
		info("Add color macro for page");
		richEditor.goToMacro(macroFormattingCate, macroColor);
		richEditor.insertMacroColor(color, message);
		
		/*Step number: 3
		*Step Name: Step 3: Check Collapse All 
		*Step Description: 
			 Select Macro --> Collapse All or select macro at content area, press Ctrl Shift C and Ctrl Shift E key
		*Input Data: 
			
		*Expected Outcome: 
			 Show all macro with type is added */
		info ("Collapse all Macro by clicking Collapse All");
		richEditor.CollapseAllMacro(false);
		richEditor.verifyCollapsemacro(macroJIRACollapse);
		richEditor.verifyCollapsemacro(macroColorCollapse);
		switchToParentWindow();
		
		/*Step number: 4
		*Step Name: Step 4: Check Expand all
		*Step Description: 
			  Select Macro --> Expand All or select macro at content area, press Ctrl Shift C and Ctrl Shift E
		*Input Data: 
			
		*Expected Outcome: 
			Show detail for each macro in page*/
		info ("Expand all macro by clicking Expand All");
		richEditor.ExpandAllMacro(false);
		richEditor.verifyExpandmacro(macroCategories.COLOR, message, color);
		richEditor.verifyExpandmacro(macroCategories.JIRA, macroJIRAContent2);
		richEditor.verifyExpandmacro(macroCategories.JIRA, macroJIRAContent3);
		switchToParentWindow();
		
		info("Collapse All Macro by Ctl key");
		richEditor.CollapseAllMacro(true);
		richEditor.verifyCollapsemacro(macroJIRACollapse);
		richEditor.verifyCollapsemacro(macroColorCollapse);
		switchToParentWindow();
		
		info("Expand All Macro by Ctl key");
		richEditor.ExpandAllMacro(true);
		richEditor.verifyExpandmacro(macroCategories.COLOR, message, color);
		richEditor.verifyExpandmacro(macroCategories.JIRA, macroJIRAContent2);
		richEditor.verifyExpandmacro(macroCategories.JIRA, macroJIRAContent3);
		switchToParentWindow();
 	}
}
