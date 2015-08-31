package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_Profile_RecentActivities extends SOC_TestConfig_2{

	/**
	 *<li> Case ID:122953.</li>
	 *<li> Test Case Name: Check my Recent Activities section.</li>
	 *<li> Pre-Condition: - User A has more 5 activities in his stream (wiki,forum,poll,document...with title, is mentioned,post activity,like,comment)</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122954.</li>
	 *<li> Test Case Name: Check the Recent Activities section of another user.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User B has over 5 activities in his stream (wiki,forum,poll,document...with title,post activity)</li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test
	public  void test01_02_CheckMyRecentActivitiesSection() throws AWTException {
		info("Test 1: Check my Recent Activities section");
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		String uploadFileName = atData.getAttachFileByArrayTypeRandom(9);
		String textDes1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		//String folderPath=siteExPath.getSiteExpPathByIndex(6);
		String nameDrive=siteExDrive.getSiteExpDriveByIndex(4);
		String folderPath=siteExPath.getSiteExpPathByIndex(10);
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mention = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		//Create data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();
	

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		magAc.signIn(username1, password1);

		/*Step Number: 1
		 *Step Name: Step 1 : go to my profile
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- The section "Recent activities" is displayed at the bottom of the mid
			-column
			- Button "View All" at the bottom to redirect to My activities*/
		info("add mention");
		hpAct.mentionUserActivity(DATA_USER1,mention);

		info("share a document and comment");
		driver.navigate().refresh();
		hpAct.openUploadPopup(nameDrive,folderPath);
		hpAct.uploadFileFromAS("TestData/",uploadFileName);
		hpAct.shareFileActivity(nameDrive,folderPath, uploadFileName, textDes);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",uploadFileName));
		hpAct.addComment(textDes, comment);

		info("add activity");
		driver.navigate().refresh();
		hpAct.addActivity(textDes1, link);
		Utils.pause(3000);
		driver.navigate().refresh();
		hpAct.addActivity(textDes2, "");

		info("goto my profile");
		navTool.goToMyProfile();
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);
		click(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);

		/*Step number: 2
		 *Step Name: Step 2: Check Activity
		 *Step Description: 
			- Check classic activity of Recent Activities section
		 *Input Data: 

		 *Expected Outcome: 
			summary of the activity is displayed : 
			- Avatar
			- Type
			- Activity message / title
			- Source link if provided*/ 
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_FILE.replace("${title}",textDes).replace("${file}", uploadFileName));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_FILE_USER_ICON.replace("${user}", username1));
		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", comment));

		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK.replace("${title}", textDes1).replace("${link}", link));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK_USER_ICON.replace("${user}", username1));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}", textDes2));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_MENTION_USER.replace("${content}", mention).replace("${user}",DATA_USER1));

		info("Clear Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
}