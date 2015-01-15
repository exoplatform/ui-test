package org.exoplatform.selenium.platform.wiki.functional.search;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * updated by anhpp
 */
public class Wiki_Space_Switcher extends BasicAction{
	ManageAccount magAc;
	ManageMember magMember;

	//@Parameters({ "platform","browser","version", "url" })
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();//true,platform,browser,version,url);
		driver.get(baseUrl);
		magAc = new ManageAccount(driver, this.plfVersion);
		magMember = new ManageMember(driver, this.plfVersion);
		magAc.signIn(DATA_USER1,DATA_PASS);;
	}

	@AfterMethod
	public void afterTest(){
		//signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:118267
	 * Test Case Name: When user is member of 0 Space, space switcher should not display spaces list and input text.
	 * Pre-Condition: User is member of 0 space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 * Bug: https://jira.exoplatform.org/browse/WIKI-914
	 */
	
	// REMOVED
	
	/**
	 * Case ID: 118268
	 * Test Case Name: When user is member of at least one space, input text should be displayed.
	 * Pre-Condition: User is member of space "Mobile"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test01_WhenUserIsMemberOfAtLeastOneSpaceInputTextShouldBeDisplayed() {
		info("Test 01: When user is member of at least one space, input text should be displayed");
		String spaceName = "Mobiles";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(spaceName, "");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Go to Company wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Company wiki is displayed
				- Breadcrumb is displaying Space Switcher*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space Switcher
		 *Input Data: 

		 *Expected Outcome: 
				- The first item of the list is an input text field*/ 
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT);

		magMember.goToAllSpaces();
		magMember.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID: 118264 + 118266
	 * Test Case Name: Search should start on first characters inputed and When no results search result, space switcher should display "No Spaces".
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test02_03_SearchShouldStartOnFirstCharactersInputedAndDisplayNoSpaces() {
		info("Test 03: Search should start on first characters inputed");
		String mobileSpace = "Mobile";
		String longNameSpace = "Long title for a space name 30";
		magMember.goToMySpacePage();
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "x" in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying "No Spaces"*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("x");
		waitForAndGetElement(ELEMENT_NO_SPACE_OPTION);

		//restore data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);
	}

	/**
	 * Case ID: 118262
	 * Test Case Name: Search is working as a filter in the list of spaces.
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test04_SearchIsWorkingAsAFilterInTheListOfSpaces() {
		info("Test 04: Search is working as a filter in the list of spaces");
		String mobileSpace = "Mobile118262";
		String longNameSpace = "Long title for a space name 31";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/

		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "Mo" in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying :* Mobile*/
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("Mo");
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", mobileSpace.toLowerCase()));
		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
				- Delete "Mo" from input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of last browsed space is displayed again*/
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).clear();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).click();
		type(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS," ",true);
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", mobileSpace.toLowerCase()));
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", longNameSpace.toLowerCase().replace(" ", "_")));
		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
				- Input "bile"
		 *Input Data: 

		 *Expected Outcome: 
				- List of sapces is updated and displaying : * Mobile*/ 
		
		type(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS,"bile",true);
		//waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("bile");
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", mobileSpace.toLowerCase()));

		//restore data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);
	}


	/**
	 * Case ID:118261
	 * Test Case Name: Search is not case sensitive.
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test05_SearchIsNotCaseSensitive() {
		info("Test 05: Search is not case sensitive");
		String mobileSpace = "Mobile118261";
		String longNameSpace = "Long title for a space name 32";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "mob" in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying :* Mobile*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("mo");
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", mobileSpace.toLowerCase()));

		//resote data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);
	}

	/**
	 * Case ID: 118260
	 * Test Case Name: Search doesn't take into account order of words.
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test06_SearchDoesntTakeIntoAccountOrderOfWords() {
		info("Test 06: Search doesn't take into account order of words");
		String mobileSpace = "Mobile118260";
		String longNameSpace = "Long title for a space name 33";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/

		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "title" in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying :* "Long title for a space name 30"*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("title");
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", longNameSpace.toLowerCase().replace(" ", "_")));

		//resote data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);
	}

	/**
	 * Case ID: 118259
	 * Test Case Name: Search - Space switcher is scrollable when there are more than 10 results.
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test07_SearchSpaceSwitcherIsScrollableWhenThereAreMoreThan10Results() {
		info("Test 07: Search - Space switcher is scrollable when there are more than 10 results");
		String mobileSpace = "Mobile118259";
		String longNameSpace = "Long title for a space name 34";
		String spacePrefix = "space118259";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");
		for (int i = 1; i < 12; i++) {
			magMember.goToMySpacePage();
			magMember.addNewSpace(spacePrefix+i, "");
		}
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "Spac" in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying :* Space 1* Space 2* Space 3* Space 4* Space 5* Space 6* Space 7* Space 8* Space 9* Space 10User can scroll down to see* Space 11* Long title for a space name 30*/
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("Spac");;
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", longNameSpace.toLowerCase().replace(" ", "_")));
		for (int i = 1; i < 12; i++) {
			waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", (spacePrefix+i).toLowerCase().replace(" ", "_")));
		}

		WebElement spaceList = waitForAndGetElement(By.className("spaceList"));

		String str1 = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].clientHeight;", spaceList));
		String str = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", spaceList));
		int clientHeight = Integer.parseInt(str1);
		int scrollHeight = Integer.parseInt(str);
		assert clientHeight<scrollHeight;

		//resote data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);
		for (int i = 1; i < 12; i++) {
			magMember.deleteSpace(spacePrefix+i, 300000);
		}
	}

	/**
	 * Case ID: 118263
	 * Test Case Name: Search should not take into account spaces at the end of a text inputted.
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test08_SearchShouldNotTakeIntoAccountSpacesAtTheEndOfATextInputted() {
		info("Test 08: Search should not take into account spaces at the end of a text inputted");
		String mobileSpace = "Mobile118263";
		String longNameSpace = "Long title for a space name 35";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "Mo" (with 2 spaces at the end) in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying :* Mobile*/
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("Mo");
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", mobileSpace.toLowerCase()));

		//resote data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);
	}

	/**
	 * Case ID: 118265
	 * Test Case Name: Search should take into account spaces in the middle of text inputted.
	 * Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by khanhnt at 2014/01/17 08:41:09
	 */
	@Test
	public  void test09_SearchShouldTakeIntoAccountSpacesInTheMiddleOfTextInputted() {
		info("Test 09 Search should take into account spaces in the middle of text inputted");
		String mobileSpace = "Mobile118265";
		String longNameSpace = "Long title for a space name 36";
		magMember.goToMySpacePage();	
		magMember.addNewSpace(mobileSpace, "");
		magMember.goToMySpacePage();
		magMember.addNewSpace(longNameSpace, "");

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
				- Using left sidebar navigation, go into the wiki
		 *Input Data: 

		 *Expected Outcome: 
				- Wiki is displayed
				- Breadcrumb is displayed with space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
				- Open the Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
				- Input text is displayed*/

		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
				- Input "Long title" in the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of spaces is updated and displaying :* Long title for a space name 30*/
		info("Input data to search");
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys("Long title");
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", longNameSpace.toLowerCase().replace(" ", "_")));
		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
				- Remove "Long title" from the input text
		 *Input Data: 

		 *Expected Outcome: 
				- List of last browsed spaces is displayed again*/
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).clear();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).sendKeys(" ");

		//Press Enter key
		Utils.javaSimulateKeyPress(KeyEvent.VK_ENTER);
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", mobileSpace.toLowerCase()));
		waitForAndGetElement(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", longNameSpace.toLowerCase().replace(" ", "_")));
		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
				- Input "Longtitle" (with 2 spaces between "Long" and "title") in the input text
		 *Input Data: 

		 *Expected Outcome: 
				Space switcher is displaying :
				- "No Spaces"*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).sendKeys("Longtitle");
		waitForAndGetElement(ELEMENT_NO_SPACE_OPTION);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).clear();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT_FOCUS).sendKeys("Long  title");
		waitForAndGetElement(ELEMENT_NO_SPACE_OPTION);

		//resote data
		magMember.goToMySpacePage();	
		magMember.deleteSpace(mobileSpace,300000);
		magMember.deleteSpace(longNameSpace, 300000);

	}
}