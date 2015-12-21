package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;


public class SOC_HomePage extends SOC_TestConfig_1 {
	/**
	 *<li> Case ID:121888.</li>
	 *<li> Test Case Name: Like Activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test (priority=22)
	public  void test01_LikeActivity() {
		info("Test 1: Like Activity");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		hp.goToHomePage();

		/*Step Number: 1
		 *Step Name: Step 1: Like/Unlike Activity
		 *Step Description: 
			- Go to Intranet home
			- Click on Like activity in action bar part of an activity
		 *Input Data: 

		 *Expected Outcome: 
			- Like button is highlighted and the number of likers is updated*/

		clickByJavascript((hp.ELEMENT_PUBLICATION_LIKE).replace("${title}", name));
		waitForAndGetElement((hp.ELEMENT_PUBLICATION_LIKED).replace("${title}", name));

		/*Step number: 2
		 *Step Name: Check Likes part
		 *Step Description: 
			- Check avatar
			- Mouse over the avatar
		 *Input Data: 

		 *Expected Outcome: 
			- Avatar of liker is added into likes part, the oldest liker is displayed at the right and the newest at the left.
			- Profile pictures of users popup*/ 
		//mouseOver(hp.ELEMENT_PUBLICATION_WHOLIKED, true);
		mouseHoverByJavaScript(hp.ELEMENT_PUBLICATION_WHOLIKED, 2);
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_WHOLIKEDPOPUP);
	}

	/**
	 *<li> Case ID:121909.</li>
	 *<li> Test Case Name: Add comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:121933.</li>
	 *<li> Test Case Name: Delete comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test (priority=23)
	public  void test02_15_AddDeleteComment() {
		info("Test 2: Add comment");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		hp.goToHomePage();

		/*Step Number: 1
		 *Step Name: Add comment for activity
		 *Step Description: 
			- Go to Intranet home
			- Select the activity
			- Click comment icon to show input text field
			- input the comment and click comment
		 *Input Data: 

		 *Expected Outcome: 
			- Comment will be shown in comment section of activity*/ 
		hpAct.addComment(name,content2);
		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", content2));

		info("Test 15: Delete comment");
		hp.goToHomePage();
		hpAct.deleteComment(name, content2);
		waitForElementNotPresent(hpAct.ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", content2));
	}

	/**
	 *<li> Case ID:121910.</li>
	 *<li> Test Case Name: Delete your activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test (priority=1)
	public  void test03_DeleteYourActivity() {
		info("Test 3: Delete your activity");
		//Actions actions = new Actions(driver);
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		hp.goToHomePage();

		/*Step Number: 1
		 *Step Name: - Delete a comment
		 *Step Description: 
			- Go to Intranet home
			- Select the activity 
			- mouse over activity you want to delete
			- Click the (x) icon to delete
		 *Input Data: 

		 *Expected Outcome: 
			- Comment will be shown in comment section of activity
			- the (x) icon display on the top
			-right of activity
			- activity is deteled successfully*/ 
		if(browser.contains("iexplorer")){
			info("Click X icon");
			Utils.pause(2000);
			waitForAndGetElement(hp.ELEMENT_PUBLICATION_AUTHOR.replace("${title}", name), DEFAULT_TIMEOUT, 1);
			mouseOver((hp.ELEMENT_PUBLICATION_AUTHOR).replace("${title}", name), true);
			waitForAndGetElement(hp.ELEMENT_PUBLICATION_DELETE.replace("${title}", name), 5000, 1);
			Utils.pause(2000);
			clickByJavascript((hp.ELEMENT_PUBLICATION_DELETE).replace("${title}", name), 2);
			
			info("clik OK button");
			waitForAndGetElement(hp.ELEMENT_DELETE_POPUP_OK, DEFAULT_TIMEOUT, 1);
			Utils.pause(2000);
			
			click(hp.ELEMENT_DELETE_POPUP_OK, 2);
			waitForElementNotPresent(hp.ELEMENT_PUBLICATION_AUTHOR.replace("${title}", name));
		}
		else{
			Utils.pause(2000);
			mouseOver((hp.ELEMENT_PUBLICATION_AUTHOR).replace("${title}", name), true);
			waitForAndGetElement(hp.ELEMENT_PUBLICATION_DELETE.replace("${title}", name), 5000, 1);
			click((hp.ELEMENT_PUBLICATION_DELETE).replace("${title}", name));
			click(hp.ELEMENT_DELETE_POPUP_OK);
			waitForElementNotPresent(hp.ELEMENT_PUBLICATION_TITLE.replace("${title}", name));
		}
	}

	/**
	 *<li> Case ID:121915.</li>
	 *<li> Test Case Name: Mention a user in activity composer.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test (priority=2)
	public  void test04_MentionAUserInActivityComposer() throws AWTException {
		info("Test 4: Mention a user in activity composer");
		String text = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";

		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		
		/*Step Number: 1
		 *Step Name: Step 1: Mentions on User Activity Stream
		 *Step Description: 
			- Login Intranet home
			- Hover the mouse over the name of user and select My Activity streams
			- Write something 
			-Entering @ followed by the user name
			- Select name is wrapped in the input
			- Click Share button
		 *Input Data: 

		 *Expected Outcome: 
			- The suggestion list is hidden
			- In the activity stream, mentions are displayed as a link on "Firstname Lastname" to the user's activities page*/ 
		hp.goToHomePage();
		hpAct.mentionUserActivity(username,text);
		waitForAndGetElement(By.xpath(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", text)));
		
	}

	/**
	 *<li> Case ID:121923.</li>
	 *<li> Test Case Name: Share your status.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test (priority=3)
	public  void test05_ShareYourStatus() {
		info("Test 5: Share your status");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Share your status
		 *Step Description: 
			- Log in 
			- Go to Home page
			- Input into activity input field and click share
		 *Input Data: 

		 *Expected Outcome: 
			- The message will show in Activity Stream*/ 
		hp.goToHomePage();
		hpAct.addActivity(name,"");
		waitForElementNotPresent(hp.ELEMENT_PUBLICATION_TITLE.replace("${title}", name));
	}

	/**
	 *<li> Case ID:121924.</li>
	 *<li> Test Case Name: Add a document.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=4)
	public  void test06_AddADocument() {
		info("Test 6: Add a document");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String path=siteExPath.getSiteExpPathByIndex(5);
		String nameDrive=siteExDrive.getSiteExpDriveByIndex(2);

		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(name, content);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		hp.goToHomePage();

		/*Step Number: 1
		 *Step Name: - Go to Select File Dialog
		 *Step Description: 
			- Log in
			- Goto Homepage
			- In Activity Composer click File (icon)
		 *Input Data: 

		 *Expected Outcome: 
			- Select File Dialog shows up*/

		/*Step number: 2
		 *Step Name: - Select document
		 *Step Description: 
			- Browse the Select File Dialog to choose a document
			- Click Select
		 *Input Data: 

		 *Expected Outcome: 
			- A breadcrumb is displaying the current position of the user in the browsed drive.
			- the name of the file should be displayed under the activity input field.*/

		/*Step number: 3
		 *Step Name: - Share the document
		 *Step Description: 
			- Click Share button
		 *Input Data: 

		 *Expected Outcome: 
			- Activity is added into activity stream*/ 
		hpAct.shareFileActivity(nameDrive, path, name, content);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",content).replace("${file}",name));
		
	}

	/**
	 *<li> Case ID:121925.</li>
	 *<li> Test Case Name: Add New folder and upload file.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=24)
	public  void test07_AddNewFolderAndUploadFile() {
		info("Test 7: Add New folder and upload file");

		String uploadFileName = fData.getAttachFileByArrayTypeRandom(9);
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String folderPath=siteExPath.getSiteExpPathByIndex(5);
		String nameDrive=siteExDrive.getSiteExpDriveByIndex(2);

		/*Step Number: 1
		 *Step Name: - Go to Select File Dialog
		 *Step Description: 
			- Log in
			- Goto Homepage
			- In Activity Composer click File (icon)
		 *Input Data: 

		 *Expected Outcome: 
			- Select File Dialog shows up*/

		//selectFile(driverName,true,folderPath,"",uploadFileName,folder);

		/*Step number: 2
		 *Step Name: - Create new folder
		 *Step Description: 
			- Browse the Select File Dialog to select the path for the folder to be created
			- Click Add new folder icon
			- Input folder name and click ok
		 *Input Data: 

		 *Expected Outcome: 
			- A breadcrumb is displaying the current position of the user in the browsed drive.
			- A popup shows up to users to put the folder name
			- A folder is created and shown in the list*/
		/*Step number: 3
		 *Step Name: - Upload a file
		 *Step Description: 
			- Select the folder and click upload icon
			- Browse and select a file to upload
		 *Input Data: 

		 *Expected Outcome: 
			- File Dialog shows up
			- Progress bar shows the status of uploading
			- after the file is uploaded, filename is showed under activity input field*/

		/*Step number: 4
		 *Step Name: - Share the document
		 *Step Description: 
			- Click Share button
		 *Input Data: 

		 *Expected Outcome: 
			- Activity is added into activity stream*/ 
		hp.goToHomePage();
		Utils.pause(3000);
		//hpAct.openUploadPopup(nameDrive,folderPath);
		//hpAct.uploadFileFromAS("TestData/",uploadFileName);
		hpAct.shareFileActivity(nameDrive,folderPath, uploadFileName, textDes);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",uploadFileName));

	}

	/**
	 *<li> Case ID:121926.</li>
	 *<li> Test Case Name: Add a link.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=5)
	public  void test08_AddALink() {
		info("Test 8: Add a link");
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		
		/*Step Number: 1
		 *Step Name: - Add a link
		 *Step Description: 
			- Log in
			- Goto Homepage
			- In Activity Composer click Add Link
			- Input URL and click Attach
		 *Input Data: 

		 *Expected Outcome: 
			- A input text field shows up to users to input the URL
			- The infomation of URL is shown in featured content parts*/

		/*click(hp.ELEMENT_PUBLICATION_ADDLINK);
		type(hp.ELEMENT_PUBLICATION_ADDLINK_INPUT, "http://www.ted.com", false);
		click(hp.ELEMENT_PUBLICATION_ADDLINK_ATTCHBTN);*/

		/*Step number: 2
		 *Step Name: - Share the document
		 *Step Description: 
			- Click Share button
		 *Input Data: 

		 *Expected Outcome: 
			- Activity is added into activity stream*/ 
		hp.goToHomePage();
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));

	}

	/**
	 *<li> Case ID:121927.</li>
	 *<li> Test Case Name: Load previous activity page automatically.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=6)
	public  void test09_LoadPreviousActivityPageAutomatically() {
		info("Test 9: Load previous activity page automatically");

		/*Step Number: 1
		 *Step Name: - Create more than 1 page of activities (Default 20)
		 *Step Description: 
			- Log in
			- Go to homepage and create more than 20 activities
		 *Input Data: 

		 *Expected Outcome: 
			- more than 20 activities are created successfully*/
		String textDesfirst = null;
		String textDesMedi= null;
		String textDeslast= null;
		
		for(int i=0; i<=10; i++) {
			if(i==0){
				textDesfirst=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
				textDesMedi=textDesfirst;
			}
			else if(i==10){
				textDeslast=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
				textDesMedi=textDeslast;
			}
			else
			textDesMedi = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hp.goToHomePage();
			hpAct.addActivity(textDesMedi,"");
			Utils.pause(2000);
		}

		/*Step number: 2
		 *Step Name: - check the loading automaticallyprevious activity page
		 *Step Description: 
			- log out andlog in again
			- go to home page
			- scroll to the bottom of the activity stream
		 *Input Data: 

		 *Expected Outcome: 
			-the first page of last activities is displayed
			- previous activities' pages are load automatically*/ 
		magAc.signIn(USER_ROOT,PASS_ROOT);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,5500)", "");
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",textDesfirst));
		Utils.pause(2000);
		hp.loadMoreActivities();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",textDesfirst));
	}

	/**
	 *<li> Case ID:121928.</li>
	 *<li> Test Case Name: Check [All activities] filter.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=7)
	public  void test10_CheckAllActivitiesFilter() {
		info("Test 10 Check [All activities] filter");
		/*Step Number: 1
		 *Step Name: - Goto social homepage
		 *Step Description: 
			- Log in
			- Goto homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Home page is displayed*/
        String text=getRandomString();
		driver.navigate().refresh();
		hp.goToHomePage();
		hpAct.addActivity(text,"");

		/*Step number: 2
		 *Step Name: - Check [All activity] filter
		 *Step Description: 
			- In the drop
			-down select box, select [All Activities]
		 *Input Data: 

		 *Expected Outcome: 
			- All activities are displayed in activity stream*/ 

		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES);

	}

	/**
	 *<li> Case ID:121929.</li>
	 *<li> Test Case Name: Check [My Spaces] filter.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=8)
	public  void test11_CheckMySpacesFilter() {
		info("Test 11 Check [My Spaces] filter");
		/*Step Number: 1
		 *Step Name: - Goto social homepage
		 *Step Description: 
			- Log in
			- Goto homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Home page is displayed*/

		driver.navigate().refresh();
		hp.goToHomePage();

		/*Step number: 2
		 *Step Name: - Check [My Spaces] filter
		 *Step Description: 
			- In the drop
			-down select box, select [My Space]
		 *Input Data: 

		 *Expected Outcome: 
			- It shows only activities created in space where the user is a member*/ 

		clickByJavascript(hp.ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES);
		clickByJavascript(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE_OPTION);
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE);
	}

	/**
	 *<li> Case ID:121930.</li>
	 *<li> Test Case Name: Check [Connections] filter.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=9)
	public  void test12_CheckConnectionsFilter() {
		info("Test 12 Check [Connections] filter");
		/*Step Number: 1
		 *Step Name: - Goto intranet homepage
		 *Step Description: 
			- Log in
			- Goto intranet homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Home page is displayed*/

		driver.navigate().refresh();
		hp.goToHomePage();

		/*Step number: 2
		 *Step Name: - Check [Connections] filter
		 *Step Description: 
			- In the drop
			-down select box, select [Connnection]
		 *Input Data: 

		 *Expected Outcome: 
			shows only activities created by the user's connections and by the user himself, outside a space*/ 
		//click(hp.ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES);
		clickByJavascript(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYSPACE);
		clickByJavascript(hp.ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION_OPTION);
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION);
	}

	/**
	 *<li> Case ID:121931.</li>
	 *<li> Test Case Name: Check [My Activities] filter.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=10)
	public  void test13_CheckMyActivitiesFilter() {
		info("Test 13 Check [My Activities] filter");
		/*Step Number: 1
		 *Step Name: - Go to intranethomepage
		 *Step Description: 
			- Log in
			- Go to intranet homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Home page is displayed*/

		driver.navigate().refresh();
		hp.goToHomePage();

		/*Step number: 2
		 *Step Name: - check [My Activities] filter
		 *Step Description: 
			- In the drop
			-down select box, select [My Activities]
		 *Input Data: 

		 *Expected Outcome: 
			shows only activities where the user has been @mentionned, the user has commented or liked, and the user's activities (inside and outside a space)*/ 
		if(browser.contains("iexplorer")){
			clickByJavascript(hp.ELEMENT_PUBLICATION_DISPLAYMODE_CONNECTION);
			waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION, DEFAULT_TIMEOUT, 1);
			clickByJavascript(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION);
		}
		else{
			waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES, DEFAULT_TIMEOUT, 1);
			click(hp.ELEMENT_PUBLICATION_DISPLAYMODE_ALLACTIVITIES);
			waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION, DEFAULT_TIMEOUT, 1);
			click(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES_OPTION);
		}
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYMODE_MYACTIVITIES);
	}

	/**
	 *<li> Case ID:121932.</li>
	 *<li> Test Case Name: Check activities order.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=11)
	public  void test14_CheckActivitiesOrder() {
		info("Test 14 Check activities order");

		/*Step Number: 1
		 *Step Name: - Check order of activities
		 *Step Description: 
			- Log in and go to homepage
			- Check order of activity
		 *Input Data: 

		 *Expected Outcome: 
			- Homepage is displayed
			- the order of activities is based on activity's last action date. 
			The last action date is the latest of:
			1. The publication date 
			2. The date of the last comment posted*/ 
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		String activity2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity2, "");
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		info("verify order of 2 nodes after added");
		driver.navigate().refresh();
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER.replace("${number}", "2").replace("${title}", activity2));
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER.replace("${number}", "3").replace("${title}", activity1));
		
		info("comment in activity1");
		hpAct.addComment(activity1, comment);
		driver.navigate().refresh();
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER.replace("${number}", "2").replace("${title}", activity1));
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER.replace("${number}", "3").replace("${title}", activity2));
	}

	/**
	 *<li> Case ID:121934.</li>
	 *<li> Test Case Name: Check Layout of Activities.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=12)
	public  void test16_CheckLayoutOfActivities() {
		info("Test 16 Check Layout of Activities");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Check layout of activity
		 *Step Description: 
			- log in and goto intranet hompage
			- Select an activity to check its layout
		 *Input Data: 

		 *Expected Outcome: 
			An activity is made out of different parts:(see attached)
			1.the author
			2.the author's avatar
			3.the space (optional)
			4.the type (optional) 
			5.the activity message
			6.the featured content (optional)
			7.the Action bar (Comment and Like links + custom actions)
			8.the like section (optional)
			9.the comment section (optional)*/ 
		hp.goToHomePage();
		hpAct.addActivity(name, "");

		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_FIRSTPOST_AUTHOR.replace("${name}",DATA_NAME_ROOT));
		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_FIRSTPOST_AUTHORAVATAR);
		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_FIRSTPOST_ACTIVITYTEXT);
		waitForAndGetElement(hpAct.ELEMENT_ICON_COMMENT.replace("${title}",name));
		waitForAndGetElement(hpAct.ELEMENT_ICON_LIKE.replace("${title}",name));
		
	}

	/**
	 *<li> Case ID:121935.</li>
	 *<li> Test Case Name: View Comments.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=13)
	public  void test17_ViewComments() {
		info("Test 17 View Comments");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: - Check view of comment
		 *Step Description: 
			- goto home page
			- select an activity with more than 2 comments on it (fx: 10 comments)
			- click on the message "View all 10 comments"
		 *Input Data: 

		 *Expected Outcome: 
			- onlythe latest (most recently posted) two comments are displayed below the activity.
			- "View all 10 comments" message is shown 
			- all comments is displayed, in the time order (oldest at the top)*/ 
		hp.goToHomePage();
		hpAct.addActivity(name, "");
		String commentfirst=null;
		String comment=null;
		String commentSecondlast=null;
		String commentlast=null;
		for(int i=0; i<=9; i++) {
			if(i==0){
				commentfirst=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
				comment = commentfirst;
			}else if (i==8){
				commentSecondlast=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
				comment = commentSecondlast;
			}else if (i==9){
				commentlast=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
				comment = commentlast;
			}else
			comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hpAct.addComment(name, comment);
			Utils.pause(2000);
		}

		driver.navigate().refresh();
		info("Verify that only second last and last comment are shown");
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",name).replace("${commentText}",commentSecondlast));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",name).replace("${commentText}",commentlast));
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",name).replace("${commentText}",commentfirst));
		info("Verify that view all comment links is shown and clickable on it");
		clickByJavascript(hpAct.ELEMENT_PUBLICATION_SEEALLCOMMENTBTN.replace("${activity}",name));

		info("Verify that all comments is shown");
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",name).replace("${commentText}",commentSecondlast));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",name).replace("${commentText}",commentlast));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",name).replace("${commentText}",commentfirst));
	}

	/**
	 *<li> Case ID:121936.</li>
	 *<li> Test Case Name: Mention a user in comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test(priority=14)
	public  void test18_MentionAUserInComment() throws AWTException {
		info("Test 18 Mention a user in comment");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);

		/*Step Number: 1
		 *Step Name: Add comment with mention
		 *Step Description: 
			- Select the activity that has some comments
			- input the comment with mention and click comment
		 *Input Data: 

		 *Expected Outcome: 
			- Input text field is displayed in activity, click comment button to show comment textbox
			- Comment will be added into comment section of activity*/ 
		hp.goToHomePage();
		hpAct.addActivity(name,null);
		hpAct.addCommentWithMentionUser(name,username,text);
	}

	/**
	 *<li> Case ID:121937.</li>
	 *<li> Test Case Name: Relation Activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=15)
	public  void test19_RelationActivity() {
		info("Test 19 Relation Activity");
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		addUserPage.addUser(username1, password, email1, username1, username1);
		
		magAc.signIn(username,password);
		/*Step Number: 1
		 *Step Name: - Invite another user
		 *Step Description: 
			- Login with User A and go to Intranet
			- Go to Connections 
			- Find user B and send a request
		 *Input Data: 

		 *Expected Outcome: 
			- Request is sent to the user B*/
		hp.goToConnections();
		connMag.connectToAUser(username1);

		/*Step number: 2
		 *Step Name: - Accept request
		 *Step Description: 
			- Login as user B and goto my Connection
			- Accept the request from user A
		 *Input Data: 

		 *Expected Outcome: 
			- A Relation activity is displayed to the activity stream*/ 
		
		magAc.signIn(username1,password);
		hp.goToConnections();
		connMag.acceptAConnection(username);
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_ACTIVITYTEXT_CONNECTED.replace("${user}",username+" "+username), DEFAULT_TIMEOUT, 1);
	    
	}

	/**
	 *<li> Case ID:121938.</li>
	 *<li> Test Case Name: Create a new space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 */
	@Test(priority=16)
	public  void test20_CreateASpace() {
		info("Test 20 Create a new space");

		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: - Create a new Space
		 *Step Description: 
			- Goto homepage
			- Click [Join a space] on left Navigation
			- Click [Add new space] button
			- Fill the information and click create
			- Check homepage
		 *Input Data: 

		 *Expected Outcome: 
			- a new space is created
			- an activity is added into activity stream
			- Informations displayed in the featured content are :
			1. Space's avatar
			2. Space's description
			3. Number of members.*/ 

		hp.goToHomePage();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace);
		
		hp.goToHomePage();
		hpAct.checkActivity(contentSpace);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_AVATAR.replace("${space}",space));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_DESCRIPTION.replace("${space}",space).replace("${des}",contentSpace));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_MEMBER_NUMBER.replace("${space}",space).replace("${num}","1 Member"));
	}

	/**
	 *<li> Case ID:121942.</li>
	 *<li> Test Case Name: Update Profile - change of avatar.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=25)
	public  void test24_UpdateProfileChangeOfAvatar() {
		info("Test 24: Update Profile - change of avatar");
		String newAvatar = fData.getAttachFileByArrayTypeRandom(26);
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		
		magAc.signIn(username,password);

		/*Step Number: 1
		 *Step Name: - Change Avatar
		 *Step Description: 
			- Connect to Intranet
			- Click username on Top Navitgation 
			-> My Profile
			- Click [Change the avatar] and upload new avatar
			- Check homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A user profile activity is updated in the activity stream
			- A comment is added: Avatar has been updated.*/ 
		navTool.goToMyProfile();
		myProfile.goToEditProfile();
		myProfile.changeAvatar("TestData/"+newAvatar);
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",username+" "+username).replace("${commentText}","Avatar has been updated."));
	}

	/**
	 *<li> Case ID:121943.</li>
	 *<li> Test Case Name: Update Profile - Update Basic information.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=20)
	public  void test25_UpdateProfileUpdateBasicInformation() {
		info("Test 25 Update Profile - Update Basic information");
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		String email1 = getRandomString()+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		
		magAc.signIn(username,password);
		/*Step Number: 1
		 *Step Name: - Change Avatar
		 *Step Description: 
			- Connect to Intranet
			- Click username on Top Navitgation 
			-> My Profile
			- Click Edit to edit basic information
			- Check homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A user profile activity is updated in the activity stream
			- A comment is added: 	Basic informations has been updated.*/ 
		magAc.signIn(username,password);
		navTool.goToMyProfile();
		myProfile.goToEditProfile();
		myProfile.updateBasicInformation("","",email1);
		myProfile.saveCancelUpdateInfo(true);
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",username+" "+username)
				.replace("${commentText}","Contact information has been updated"));
	}

	/**
	 *<li> Case ID:121944.</li>
	 *<li> Test Case Name: Check order of the activities.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=21)
	public  void test26_CheckOrderOfTheActivities() {
		info("Test 26 Check order of the activities");

		String name = txData.getContentByArrayTypeRandom(1)+"1";
		String content = txData.getContentByArrayTypeRandom(1)+"1";
		String name2 = txData.getContentByArrayTypeRandom(1)+"2";
		String content2 = txData.getContentByArrayTypeRandom(1)+"2";
		String name3 = txData.getContentByArrayTypeRandom(1)+"3";
		String content3 = txData.getContentByArrayTypeRandom(1)+"3";

		/*Step Number: 1
		 *Step Name: Check the order of activities
		 *Step Description: 
			- Login to the platform
			- Create activities of different kinds (ECMS webcontents, ECMS symlinks, share files to Personal Documents/Public, create space ...etc), at least 10 activities
		 *Input Data: 

		 *Expected Outcome: 
			- The activities should be displayed in the good order (newest at the top)
			- We have only 1 activity per kind (no duplication*/ 
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(name, content);
		CreNewDoc.saveAndClose();
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE);
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(name2, content2);
		CreNewDoc.saveAndClose();
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE);
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.PRODUCT);
		CreNewDoc.addNewProduct(name3, content3);
		CreNewDoc.saveAndClose();

		hp.goToHomePage();

		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER_WEBCONTENT.replace("${number}", "4"), DEFAULT_TIMEOUT, 1);
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER_FILE.replace("${number}", "3"), DEFAULT_TIMEOUT, 1);
		waitForAndGetElement(hp.ELEMENT_PUBLICATION_DISPLAYORDER_PRODUCT.replace("${number}", "2"), DEFAULT_TIMEOUT, 1);
	}

	/**
	 *<li> Case ID:121941.</li>
	 *<li> Test Case Name: Promote a member as manager.</li>
	 *<li> Pre-Condition: a space activity is shared in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(priority=19)
	public  void test23_PromoteAMemberAsManager() {
		info("Test 23: Promote a member as manager");
	
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username,password);
		/*Step Number: 1
		 *Step Name: - Promote a member as manager
		 *Step Description: 
			- Connect to Intranet
			- Open a space
			- Click [Settings] -> Members
			- Click Grant Manager to promote the user we want
			- Back to the Homepage
		 *Input Data: 
	
		 *Expected Outcome: 
			- The Space activity content isn't updated in the activity stream
			- A comment is added: $user has been promoted as space's manager.*/ 
	
		hp.goToHomePage();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace);
		
		
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username1,false,"");
		
		magAc.signIn(username1,password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		
		magAc.signIn(username,password);
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.changeRole(fullName1);
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_DESCRIPTION.replace("${space}",space).replace("${des}",contentSpace));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_SPACE_LAST_COMMENT.replace("${space}",space)).getText()
		.contains("$name has been promoted as the space's manager.".replace("$name",fullName1));
	
	}

	/**
	 *<li> Case ID:121939.</li>
	 *<li> Test Case Name: User join a space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 */
	@Test(priority=17)
	public  void test21_UserJoinASpace() {
		info("Test 21: User join a space");
	
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username,password);
	
		/*Step Number: 1
		 *Step Name: - Create a new Space
		 *Step Description: 
			- Goto homepage
			- Click [Join a space] on left Navigation
			- Click [Add new space] button
			- Fill the information and click create
			- Check homepage
		 *Input Data: 
	
		 *Expected Outcome: 
			- a new space is created
			- an activity is added into activity stream
			- Informations displayed in the featured content are :
			1. Space's avatar
			2. Space's description
			3. Number of members.*/ 
	
		hp.goToHomePage();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace);
	
		/*Step Number: 2
		 *Step Name: - Invite other user
		 *Step Description: 
			- goto Space setting -> member 
			- Click [select user] icon and select  user B  to invite
			- Click [Invite] Icon
		 *Input Data: 
	
		 *Expected Outcome: 
			- User is added into the table below and status in [Actions] column is [Cancel request]*/ 
		
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username1,true,fullName1);
		
		/*Step Number: 3
		 *Step Name: - User B join space
		 *Step Description: 
			- Log in as user B
			- Click [Join a space]
			- Click [accept] to join the space
			- Check homepage
		 *Input Data: 
	
		 *Expected Outcome: 
			- A comment is added into activity
			- Message: "Has joined the space." is shown*/ 
		
		magAc.signIn(username1,password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_USERJOIN_SPACE.replace("${user}",fullName1));
	}

	/**
	 *<li> Case ID:121940.</li>
	 *<li> Test Case Name: Rename a space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 */
	@Test(priority=18)
	public  void test22_RenameASpace() {
		info("Test 22: Rename a Space");
	
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newSpace = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
	
		/*Step Number: 1
		 *Step Name: - Rename space
		 *Step Description: 
			- Connect to Intranet and goto space
			- Click [setting] to edit space
			- In the setting form, rename space
			- Back to the Homepage
		 *Input Data: 
	
		 *Expected Outcome: 
			- The Space activity is updated in the activity stream withy the new title
			- A comment is added: Name has been updated to: $value.*/ 
	
		hp.goToHomePage();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace);
	
		spaHome.goToSpaceSettingTab();
		spaMg.editSpaceSimple(space, newSpace, "",false,"");
		spaMg.saveChangesSpace();
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_CHANGE_NAME.replace("${space}",newSpace));
	
	}
}