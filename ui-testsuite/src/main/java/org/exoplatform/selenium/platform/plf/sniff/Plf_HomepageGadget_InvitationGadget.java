package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class Plf_HomepageGadget_InvitationGadget extends Plf_TestConfig{
	/**
	 *<li> Case ID:120861.</li>
	 *<li> Test Case Name: Not show Invitation gadget.</li>
	 *<li> Pre-Condition: - UserA does not receive any invitation from user or space</li>
	 */
	@Test
	public void test01_NotShowInvitationGadget(){
		info("Test 01: Not show Invitation gadget");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: - Check if no invitation
		 *Step Description: 
			- Login as UserA 
			- Go to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			This gadget is not shown*/ 
		driver.navigate().refresh();
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_GADGET);
	}

	/**
	 *<li> Case ID:120854.</li>
	 *<li> Test Case Name: Check display of Invitation Gadget.</li>
	 *<li> Pre-Condition: - There are 3 connection request are sent to userA
	- There are 2 invitation form space are sent to userA</li>
	 */
	@Test
	public void test02_CheckDisplayInvitationGadget(){
		
		info("prepare data");
		/*Step Number: 1
		 *Step Name: Check display gadget
		 *Step Description: 
			- Login as userA
			- Open intranet home
			- Check Invitation gadget
		 *Input Data: 

		 *Expected Outcome: 
			- A maximum number of displayed requests is 4
			-The oldest request will appear at the bottom while the newest will appear on the top
			- For user request, the gadget displays the profile picture of the user, his name and if available, his title.
			- For Spaces request, the gadget displays the space icon, the name, privacy status which is private or public, and the number of members in the space.
			- The title of the gadget will show the total number of requests received which is 4*/ 
		info("Create datatest");
		String space1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2= username2 + mailSuffixData.getMailSuffixRandom();

		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3 + mailSuffixData.getMailSuffixRandom();
		
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4= username4 + mailSuffixData.getMailSuffixRandom();

		/*Create data test*/
		info("Add new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);

		info("--Send request 2 to John");
		info("Sign in with username1 account");
		magAc.signIn(username1, password);
		hp.goToConnections();
		connMg.connectToAUser(username4);
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1,space1);
		spaceHome.goToSpaceSettingTab();
		setMag.inviteUser(DATA_USER1,false,"");

		info("Sign in with username2 account");
		magAc.signIn(username2, password);
		hp.goToConnections();
		connMg.connectToAUser(username4);
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space2,space2);
		spaceHome.goToSpaceSettingTab();
		setMag.inviteUser(DATA_USER1,false,"");

		info("Sign in with username3 account");
		magAc.signIn(username3, password);
		hp.goToConnections();
		connMg.connectToAUser(username4);
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space3,space3);
		spaceHome.goToSpaceSettingTab();
		setMag.inviteUser(username4,false,"");

		magAc.signIn(username4,password);
		info("Verify that the maximum number of displayed requests is 4");
		info("Verify that for user request, the portlet will displayes the profile picture of the user, his name");
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username3));
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username2));
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username1));

		waitForAndGetElement(hp.ELEMENT_INVITAITONS_SPACE_ICON.replace("${name}",space3));
		waitForAndGetElement(hp.ELEMENT_INVITAITONS_SPACE_ICON.replace("${name}",space2));
		waitForElementNotPresent(hp.ELEMENT_INVITAITONS_SPACE_ICON.replace("${name}",space1));

		info("Verify that for space request, the gadget displays the space icon, the name, privacy status which is private or public, and the number of members in the space.");
		waitForAndGetElement(hp.ELEMENT_INVITAITONS_SPACE_STATUS_MEMBERS.replace("${name}",space3).replace("${statusMember}","Private Space - 1 Members"));

		info("Verify that The title of the gadget will show the total number of requests received which is 5");
		waitForAndGetElement(By.xpath(hp.ELEMENT_INVITATIONS_NUMBER.replace("${number}", "6")),2000,1);

		info("Delete DATA for the last test");
		info("Signin with james account");
		magAc.signIn(username1, password);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space1, false);

		magAc.signIn(username2, password);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space2, false);

		magAc.signIn(username3, password);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space3, false);

	}
	/**
	 *<li> Case ID:120855.</li>
	 *<li> Test Case Name: Accept a request.</li>
	 *<li> Pre-Condition: - Add users (root, mary)
	- Root sends request to connect to John
	- Mary sends connection request to John</li>
	 */
	@Test
	public void test03_AcceptARequest(){
		info("Test03: Accept a Request");
		info("prepare data");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2= username2 + mailSuffixData.getMailSuffixRandom();
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username2 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);

		/*Step Number: 1
		 *Step Name: Accept a request
		 *Step Description: 
			- Login as John
			- Open intranet homepage
			- On this gadget, mouse over an invitation of mary
			- Click on Accept button
		 *Input Data: 
		/*Expected Outcome: 
			- The invitation of root, mary is shown on the invitation gadget
			- The Accept and Refuse button are displayed.
			- John is connected to mary and the invitation fades out and is permanently removed from the list
			- Request of root are moving to the top of the gadget if needed*/ 
		info("Sign in with mary account");
		magAc.signIn(username1, password);
		hp.goToConnections();
		connMg.connectToAUser(username3);

		info("--Send request 2 to John");
		magAc.signIn(username2, password);
		hp.goToConnections();
		connMg.connectToAUser(username3);

		magAc.signIn(username3,password);
		mouseOver(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username2),true);
		click(hp.ELEMENT_INVITATIONS_PEOPLE_ACCEPT_BTN.replace("${name}",username2), 2,true);
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username2));
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username1));

	}

	/**
	 *<li> Case ID:120856.</li>
	 *<li> Test Case Name: Refuse a request.</li>
	 *<li> Pre-Condition: - Root sends connection request to mary
	- John sends connection request to mary</li>
	 */
	@Test
	public void test04_RefuseARequest(){
		info("Test 04: Refuse a request");
		info("prepare data");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2= username2 + mailSuffixData.getMailSuffixRandom();
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1, password);
		//createRequestsConnections();
		/*Step Number: 1
		 *Step Name: Refuse a request
		 *Step Description: 
			- Login as mary
			- Open intranet homepage
			- On this gadget, mouse over an invitation of Jack(demo)
			- Click on Refuse icon
		 *Input Data: 

		 *Expected Outcome: 
			- Invitations of root, james are displayed on Invitation gadget
			- The Accept and Refuse button are displayed.
			- The invitation of jack fades out and is permanently removed from the list
			- Requests of James is moved to the top of the gadget*/
		info("Sign in with mary account");
		magAc.signIn(username1, password);
		hp.goToConnections();
		connMg.connectToAUser(username3);

		info("--Send request 2 to John");
		magAc.signIn(username2, password);
		hp.goToConnections();
		connMg.connectToAUser(username3);

		info("Sign in with john account");
		mouseOver(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username2),true);
		Utils.pause(2000);
		click(hp.ELEMENT_INVITATIONS_PEOPLE_REFUSE_BTN.replace("${name}",username2), 2,true);
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username2));
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}",username1));
	}
}