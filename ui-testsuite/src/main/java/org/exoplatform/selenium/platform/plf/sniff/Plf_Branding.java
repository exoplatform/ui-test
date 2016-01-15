package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Plf_Branding extends Plf_TestConfig{
	
	/**
	 *<li> Case ID:120887.</li>
	 *<li> Test Case Name: Select navigation bar style.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_SelectNavigationBarStyle() {
		info("Test 1: Select navigation bar style");
		/*Step Number: 1
		 *Step Name: Open branding page
		 *Step Description: 
			- Login as admin
			- Connect to intranet home page
			- Go to Administration button 
			-> Portal 
			-> Branding
		 *Input Data: 

		 *Expected Outcome: 
			- Branding portlet is displayed*/

		navToolBar.goToBanding();
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDINGPAGE);


		/*Step number: 2
		 *Step Name: Show a theme select box
		 *Step Description: 
			- Go to the part to select the theme
			- Click on the select box
		 *Input Data: 

		 *Expected Outcome: 
			- The select box is opened with two choices:* Dark* Light*/

		click(branding.ELEMENT_PLF_BRANDING_SELECTTHEME);
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDING_THEMELIGHT,2000,1);
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDING_THEMEDARK,2000,1);

		/*Step number: 3
		 *Step Name: Select one theme
		 *Step Description: 
			- Select one from the list
		 *Input Data: 

		 *Expected Outcome: 
			- The full navigation bar preview is displaying the selected style navigation barover the navigation bar*/

		click(branding.ELEMENT_PLF_BRANDING_THEMELIGHT);
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDING_TOPBAR_THEMELIGHT,2000,1);

		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			- Click on save button
		 *Input Data: 

		 *Expected Outcome: 
			- The navigation bar of the intranet is displayed as preview*/ 
		
		click(branding.ELEMENT_BUTTON_SAVE);
		waitForAndGetElement(navToolBar.ELEMENT_TOOLBAR_THEMELIGHT,2000,1);
	}

	/**
	 *<li> Case ID:120888.</li>
	 *<li> Test Case Name: Select logo.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_SelectLogo() {
		info("Test 2: Select logo");
		
		String path = "Question_77048.png";
		
		/*Step Number: 1
		 *Step Name: Open branding page
		 *Step Description: 
			- Login as admin
			- Connect to intranet home page
			- Go to Administration button 
			-> Portal 
			-> Branding
		 *Input Data: 

		 *Expected Outcome: 
			- Branding portlet is displayed*/
		navToolBar.goToBanding();
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDINGPAGE,2000,1);
		
		/*Step number: 2
		 *Step Name: Upload a logo
		 *Step Description: 
			- Upload a new logo image
		 *Input Data: 

		 *Expected Outcome: 
			- The new logo image is uploaded*/

		branding.uploadFile("TestData/"+path);
		
		/*Step number: 3
		 *Step Name: Preview
		 *Step Description: 
			- Check preview boxes
		 *Input Data: 

		 *Expected Outcome: 
			- The logo must be displayed in :* preview boxe for the logo* preview of the full navigation bar*/
		
		//done in the upload function
		
		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			-Click on Save
		 *Input Data: 

		 *Expected Outcome: 
			New Logo is shown as preview*/ 
		click(branding.ELEMENT_BUTTON_SAVE);
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDING_TOPBAR_LOGO,2000,1);
		waitForAndGetElement(branding.ELEMENT_BANDING_PAGE_SELECT_LOGO);
		
	}

	/**
	 *<li> Case ID:120906.</li>
	 *<li> Test Case Name: Check display of branding portlet.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckDisplayOfBrandingPortlet() {
		info("Test 3: Check display of branding portlet");
		/*Step Number: 1
		 *Step Name: Open Branding page
		 *Step Description: 
			- Login as an admin
			- Go to intranet home page
			- Click on Administration 
			-> Portal 
			-> Branding
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet home page is opened
			- The Branding page is shown. There is 4 parts on the layout :  + Select Logo + Select Navigation Bar Style + Preview + Validation buttons
			- See attachment.*/ 
		navToolBar.goToBanding();
		waitForAndGetElement(branding.ELEMENT_PLF_BRANDINGPAGE,2000,1);
		waitForAndGetElement(branding.ELEMENT_BANDING_PAGE_SELECT_LOGO);
		waitForAndGetElement(branding.ELEMENT_BANDING_PAGE_SELECT_NAVIGATION_BAR_STYLE);
		waitForAndGetElement(branding.ELEMENT_BUTTON_SAVE);
		waitForAndGetElement(branding.ELEMENT_BUTTON_CANCEL);
	}
	
}