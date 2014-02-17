package org.exoplatform.selenium.platform.forum.functional.forum.post;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
* @author phuongdt
*
*/
public class Forum_Forum_Post_BBCode extends ForumBase{
	ManageAccount magAc;
	ForumManageCategory mngCat;
	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		magAc = new ManageAccount(driver);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		mngFru = new ForumManageForum(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngPost = new ForumManagePost(driver, this.plfVersion);
		button = new Button(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	* Case ID:72277.
	* Test Case Name: Check Bold tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test01_CheckBoldTagEffect() {
		String titleCat = "Category 72277";
		String titleForum = "Forum 72277";
		String titleTop = "Topic 72277";
		String post1 = "Post 72277";
		
		info("Test 1: Check Bold tag effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/

		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 3
		*Step Name: Step 3: Compose post with Bold tag
		*Step Description: 
			- Input text inside [b][/b] tag into Quick reply â€“ Message text areaFor example: [b]Hello[/b]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/
		
		/*Step number: 4
		*Step Name: Step 4: Preview post with Bold effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Bold effect like Hello*/
		

		/*Step number: 5
		*Step Name: Step 5: View post with Bold effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Bold effect.*/ 
		mngPost.postReply(post1, "[b]"+post1+"[/b]", "", "","",true);
		waitForAndGetElement(By.xpath("//strong[text()='"+post1+"']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//strong[text()='"+post1+"']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);

 	}

	/**
	* Case ID:72438.
	* Test Case Name: Check Italic tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test02_CheckItalicTagEffect() {
		String titleCat = "Category 72438";
		String titleForum = "Forum 72438";
		String titleTop = "Topic 72438";
		String post1 = "Post 72438";
		
		info("Test 2: Check Italic tag effect");
		/*Step Number: 1
		*Step Name: - Login by User 
		- Click on Forum button on the main action bar
		*Step Description: 
			Step 1: Login and Open Forum application
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Italic tag
		*Step Description: 
			- Input text inside [i][/i] tag into Quick reply Message text areaFor example: [i]Great![/i]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Italic effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Italic effects like Great!*/

		/*Step number: 5
		*Step Name: Step 5: View post with Italic effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Italic effect*/ 
		mngPost.postReply(post1, "[i]"+post1+"[/i]", "", "","",true);
		waitForAndGetElement(By.xpath("//i[text()='"+post1+"']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//i[text()='"+post1+"']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72561.
	* Test Case Name: Check Underline tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test03_CheckUnderlineTagEffect() {
		String titleCat = "Category 72561";
		String titleForum = "Forum 72561";
		String titleTop = "Topic 72561";
		String post1 = "Post 72561";
		info("Test 3: Check Underline tag effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Underline tag
		*Step Description: 
			- Input text inside [u][/u] tag into Quick reply Message text areaFor example: [u]Good Morning[/u]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Underline effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Underline effect like Good Morning*/

		/*Step number: 5
		*Step Name: Step 5: View post with Underline effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Underline effect.*/ 
		mngPost.postReply(post1, "[u]"+post1+"[/u]", "", "","",true);
		waitForAndGetElement(By.xpath("//u[text()='"+post1+"']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//u[text()='"+post1+"']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72665.
	* Test Case Name: Check Size tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test04_CheckSizeTagEffect() {
		String titleCat = "Category 72665";
		String titleForum = "Forum 72665";
		String titleTop = "Topic 72665";
		String post1 = "Post 72665";
		
		info("Test 4: Check Size tag effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Size tag
		*Step Description: 
			- Input text inside [size=][/size] tag into Quick reply Message text areaFor example: [size=200]HUGE![/size]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Size effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Underline effect like HUGE!*/

		/*Step number: 5
		*Step Name: Step 5: View post with Size effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Size effect.*/ 
		mngPost.postReply(post1, "[size=10]"+post1+"[/size]", "", "","",true);
		waitForAndGetElement(By.xpath("//font[@size='10' and text()='"+post1+"']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//font[@size='10' and text()='"+post1+"']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72743.
	* Test Case Name: Check Color tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test05_CheckColorTagEffect() {
		String titleCat = "Category 72743";
		String titleForum = "Forum 72743";
		String titleTop = "Topic 72743";
		String post1 = "Post 72743";
		
		info("Test 5: Check Color tag effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Color tag
		*Step Description: 
			- Input text inside [Color=][/color] tag into Quick reply Message text areaFor example: [color=red]Hello![/color]or[color=#FF0000]Hello![/color]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Color effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Color effect like Hello!*/

		/*Step number: 5
		*Step Name: Step 5: View post with Color effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Color effect.*/ 
		mngPost.postReply(post1, "[color=red]"+post1+"[/color]", "", "","",true);
		waitForAndGetElement(By.xpath("//font[@color='red' and text()='"+post1+"']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//font[@color='red' and text()='"+post1+"']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72813.
	* Test Case Name: Check combined formatting tags.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test06_CheckCombinedFormattingTags() {
		String titleCat = "Category 72813";
		String titleForum = "Forum 72813";
		String titleTop = "Topic 72813";
		String post1 = "Post 72813";
		
		info("Test 6: Check combined formatting tags");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with combined tags
		*Step Description: 
			- Input text inside some tags such as [size=][/size] [Color=][/color] [b][/b]into Quick reply â€“ Message text areaFor example: [size=200][color=red][b]LOOK AT ME![/b][/color][/size]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with combined effects
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with combined effect like LOOK AT ME!*/

		/*Step number: 5
		*Step Name: Step 5: View post with combined effects
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Size, Color and Bold effect.*/ 
		mngPost.postReply(post1, "[b][size=10][color=red]"+post1+"[/color][/size][/b]", "", "","",true);
		waitForAndGetElement(By.xpath("//strong/font[@size='10']/font[@color='red' and text()='"+post1+"']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//strong/font[@size='10']/font[@color='red' and text()='"+post1+"']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72869.
	* Test Case Name: Check Quote tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test07_CheckQuoteTagEffect() {
		String titleCat = "Category 72869";
		String titleForum = "Forum 72869";
		String titleTop = "Topic 72869";
		String post1 = "Post 72869";
		
		info("Test 7: Check Quote tag effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Quote tag
		*Step Description: 
			- Input text inside [quote=][/quote] tag into Quick reply Message text areaFor example: [quote=Mr. Bin]The text Mr. Bin wrote would go here[/quote]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Quote effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Quote effect with text added â€œOriginally Posted by Mr. Binâ€ before the actual text and quote text will have gray background*/

		/*Step number: 5
		*Step Name: Step 5: View post with Quote effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Quote effect.*/ 
		mngPost.postReply(post1, "[quote="+post1+"]"+post1+"[/quote]", "", "","",true);
		waitForAndGetElement(By.xpath("//div[@class='contentQuote']/div[@class='titleQuote' and text()='"+post1+":']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//div[@class='contentQuote']/div[@class='titleQuote' and text()='"+post1+":']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72919.
	* Test Case Name: Check Code tag effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test08_CheckCodeTagEffect() {
		String titleCat = "Category 72919";
		String titleForum = "Forum 72919";
		String titleTop = "Topic 72919";
		String post1 = "Post 72919";
		String styleCode = "margin: 3px 0px; padding: 5px; overflow: auto; text-align: left; line-height: 14px;";
		
		info("Test 8: Check Code tag effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Code tag
		*Step Description: 
			- Input text inside [code][/code] tag into Quick reply “ Message text areaFor example: [code]echo "This is some code";[/code]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Code effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Code effect with different font style and background*/

		/*Step number: 5
		*Step Name: Step 5: View post with Code effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Quote effect.*/ 
		mngPost.postReply(post1, "[code]"+post1+"[/code]", "", "","",true);
		String style = waitForAndGetElement(By.xpath("//div[text()='"+post1+"']/..")).getAttribute("style");
		info(style);
		info(styleCode);
		assert style.contains(styleCode);
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		style = waitForAndGetElement(By.xpath("//div[text()='"+post1+"']/..")).getAttribute("style");
		info(style);
		info(styleCode);
		assert style.contains(styleCode);
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:72964.
	* Test Case Name: Check Unordered list effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test09_CheckUnorderedListEffect() {
		String titleCat = "Category 72964";
		String titleForum = "Forum 72964";
		String titleTop = "Topic 72964";
		String postTitle = "Post 72964";
		String postContent = "[list][*]Red[*]Blue[*]Yellow[/list]";
		
		info("Test 9: Check Unordered list effect");
		/*Step Number: 1
		*Step Name: -Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Unordered list tag
		*Step Description: 
			- Input text inside [list][/list] tag with defined symbol [*] into Quick reply Message text area
			For example:[list][*]Red[*]Blue[*]Yellow[/list]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Unordered list effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Unordered list effect like below:* Red* Blue* Yellow*/

		/*Step number: 5
		*Step Name: Step 5: View post with Unordered list effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Unordered list effect.*/ 
		mngPost.postReply(postTitle, postContent, "", "","",true);
		waitForAndGetElement(By.xpath("//ul/li[1][@style='list-style-type:disc;' and text()='Red']"));
		waitForAndGetElement(By.xpath("//ul/li[2][@style='list-style-type:disc;' and text()='Blue']"));
		waitForAndGetElement(By.xpath("//ul/li[3][@style='list-style-type:disc;' and text()='Yellow']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//ul/li[1][@style='list-style-type:disc;' and text()='Red']"));
		waitForAndGetElement(By.xpath("//ul/li[2][@style='list-style-type:disc;' and text()='Blue']"));
		waitForAndGetElement(By.xpath("//ul/li[3][@style='list-style-type:disc;' and text()='Yellow']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:73004.
	* Test Case Name: Check Ordered list effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test10_CheckOrderedListEffect() {
		String titleCat = "Category 73004";
		String titleForum = "Forum 73004";
		String titleTop = "Topic 73004";
		String postTitle = "Post 73004";
		String postContent = "[list=1][*]Red[*]Blue[*]Yellow[/list]";
		
		info("Test 10 Check Ordered list effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Ordered list tag
		*Step Description: 
			- Input text inside [list=][/list] tag into Quick reply â€“ Message text area
			For example:[list=1][*]Go to the shops[*]Buy a new computer[*]Swear at computer when it crashes[/list]or[list=a][*]The first possible answer[*]The second possible answer[*]The third possible answer[/list]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Ordered list effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Ordered list effect like below: 1. Go to the shops 2. Buy a new computer 3. Swear at computer when it crashesor a. The first possible answer b. The second possible answer c. The third possible answer*/

		/*Step number: 5
		*Step Name: Step 5: View post with Ordered list effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Ordered list effect.*/ 
		mngPost.postReply(postTitle, postContent, "", "","",true);
		waitForAndGetElement(By.xpath("//ol/li[1][@style='list-style-type:decimal;' and text()='Red']"));
		waitForAndGetElement(By.xpath("//ol/li[2][@style='list-style-type:decimal;' and text()='Blue']"));
		waitForAndGetElement(By.xpath("//ol/li[3][@style='list-style-type:decimal;' and text()='Yellow']"));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.xpath("//ol/li[1][@style='list-style-type:decimal;' and text()='Red']"));
		waitForAndGetElement(By.xpath("//ol/li[2][@style='list-style-type:decimal;' and text()='Blue']"));
		waitForAndGetElement(By.xpath("//ol/li[3][@style='list-style-type:decimal;' and text()='Yellow']"));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:73039.
	* Test Case Name: Check Link effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test11_CheckLinkEffect() {
		String titleCat = "Category 73039";
		String titleForum = "Forum 73039";
		String titleTop = "Topic 73039";
		String postTitle = "Post 73039";
		String postContent = "[url=http://www.phpbb.com/]PostContent 73004[/url]";
		String linkContent = "PostContent 73004";
		
		info("Test 11 Check Link effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Select Knowledge Suite/Forum Application from navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Link tag
		*Step Description: 
			- Input text inside [url=][/url] taginto Quick reply â€“ Message text area
			For example:[url=http://www.phpbb.com/]Visit phpBB![/url]or[url]http://www.phpbb.com[/url]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Unordered list effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with link effect on text. For example, link effect on Visit phpBB text or directly on http://www.phpbb.com. When clicking, another site will be linked.*/

		/*Step number: 5
		*Step Name: Step 5: View post with Unordered list effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with link effect.*/ 
		mngPost.postReply(postTitle, postContent, "", "","",true);
		waitForAndGetElement(By.linkText(linkContent));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.linkText(linkContent));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:73074.
	* Test Case Name: Check Email effect.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test12_CheckEmailEffect() {
		String titleCat = "Category 73074";
		String titleForum = "Forum 73074";
		String titleTop = "Topic 73074";
		String postTitle = "Post 73074";
		String postContent = "[email]fqaexovn@gmail.com[/email]";
		String postLink = "fqaexovn@gmail.com";
		
		info("Test 12 Check Email effect");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Select Knowledge Suite/Forum Application from navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Email tag
		*Step Description: 
			- Input text inside [email][/email] taginto Quick reply Message text area
			For example:[email]th4nhc0n9@yahoo.com[/email]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Email effect
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with Email effect on text. For example, email effect on th4nhc0n9@yahoo.com. When clicking, it will be automatically filled in Send To field of your Mail application such as Out look..*/

		/*Step number: 5
		*Step Name: Step 5: View post with Email effect
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created successfully with Email effect.*/ 
		mngPost.postReply(postTitle, postContent, "", "","",true);
		waitForAndGetElement(By.linkText(postLink));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		waitForAndGetElement(By.linkText(postLink));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}

	/**
	* Case ID:73105.
	* Test Case Name: Check Adding image to a post.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/01/22 10:24:54
	*/
	@Test
	public  void test13_CheckAddingImageToAPost() {
		String titleCat = "Category 73105";
		String titleForum = "Forum 73105";
		String titleTop = "Topic 73105";
		String postTitle = "Post 73105";
		String postContent = "[img]http://www.google.com/intl/en_ALL/images/logo.gif[/img]";
		String srcLink = "http://www.google.com/intl/en_ALL/images/logo.gif";
		
		info("Test 13 Check Adding image to a post");
		/*Step Number: 1
		*Step Name: Step 1: Login and Open Forum application
		*Step Description: 
			- Login by User 
			- Click on Forum button on the main action bar
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully
			- Forum Application is displayed properly*/
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleTop));
		
		/*Step number: 2
		*Step Name: Step 2: Select Forum and Open New topic form
		*Step Description: 
			- Click on specific Forum in Category
			- Click on specific Topic in Forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic List is shown
			- Topic content is shown*/

		/*Step number: 3
		*Step Name: Step 3: Compose post with Image tag
		*Step Description: 
			- Input text inside [img][/img] taginto Quick reply Message text area
			For example:[img]http://www.google.com/intl/en_ALL/images/logo.gif[/img]
		*Input Data: 
			
		*Expected Outcome: 
			- Text is input properly*/

		/*Step number: 4
		*Step Name: Step 4: Preview post with Image
		*Step Description: 
			- Click Preview button
		*Input Data: 
			
		*Expected Outcome: 
			- View post form is shown
			- Post content is shown with image. For example, logo.gif is shown.*/

		/*Step number: 5
		*Step Name: Step 5: View post with Image
		*Step Description: 
			- Click Quick Reply button
		*Input Data: 
			
		*Expected Outcome: 
			- Post is created with image.*/ 
		mngPost.postReply(postTitle, postContent, "", "","",true);
		WebElement preElement = waitForAndGetElement(By.xpath("//*[@class='postContent']//img"));
		String src = preElement.getAttribute("src"); 
		assert (src.contentEquals(srcLink));
		click(mngPost.ELEMENT_POST_CLOSE_BUTTON);
		click(mngPost.ELEMENT_SUBMIT_BUTTON);
		waitForElementNotPresent(mngPost.ELEMENT_POST_POPUP_NEW);
		preElement = waitForAndGetElement(By.xpath("//*[@class='postContent']//img"));
		src = preElement.getAttribute("src"); 
		assert (src.contentEquals(srcLink));
		
		//Clear data
		info("Clear data");
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
 	}
}
