package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;


public class ECMS_Permission extends ECMSLocator {

	ManageAlert mngAlert;
	
	public ECMS_Permission(WebDriver driver) {
		this.driver = driver;
		mngAlert = new ManageAlert(driver);
	}

	/**
	 * Wrong path, ToCorrect
	 * @param user
	 * @param read
	 * @param modify
	 * @param remove
	 */
	public void modifyRightCheckBox(String user, boolean read, boolean modify, boolean remove) {
		if(read==true) {
			check(By.xpath("//*[@name='"+user+"read']"));
		}
		if(modify==true) {
			check(By.xpath("//*[@name='"+user+"addNode]"));
		}
		if(remove==true) {
			check(By.xpath("//*[@name=''"+user+"remove]"));
		}
	}
    /**
     * Delete permission of a node
     * @param name
     */
	public void deletePermissionNode(String name) {
		if (waitForAndGetElement(ELEMENT_PERMISSION_USER_OR_GROUP_NAME.replace("${name}", name),
				3000, 0) != null) {
			info("Click on Delete button of the node:" + name);
			click(By.xpath((ELEMENT_PERMISSION_DELETE).replace("${name}", name)));
			info("click on OK button of alert popup");
			mngAlert.acceptAlert();
			info("Finished deleting permission of the node");
		}
	}
    /**
     * Change right
     * @param user
     * @param name
     * @param read
     * @param modify
     * @param remove
     * @param opt
     */
	public void changeRight(String user, String name, boolean read, boolean modify, boolean remove, String...opt) {
		if(user == "user") {
			info("User is a user");
			info("Click on Select User button");
			click(ELEMENT_PERMISSION_SELECTUSER);
			info("Click on Add User button");
			click(By.xpath((ELEMENT_PERMISSION_USER_ADDUSER).replace("${name}", name)));
		}
		if(user == "membership") {
			info("User is a membership");
			info("Type a mebership for textbox user");
			type( ELEMENT_PERMISSION_TEXTBOXUSER, ""+opt[0]+":/"+opt[1]+"",true);
		}
		if(user == "all") {
			info("User is all");
			info("Click on Select everyone button");
			click(ELEMENT_PERMISSION_SELECTEVERYONE);
		}
		info("Check on checkbox for reading, modifying and removing");
		selectCheckBoxRight(read, modify, remove);
		info("Click on Save button");
		click(ELEMENT_PERMISSION_SAVE);
		info("Finished changing right");
	}
    /**
     * Select a check box about right
     * @param read
     * @param modify
     * @param remove
     */
	public void selectCheckBoxRight(boolean read, boolean modify, boolean remove) {
		info("Select check boxes right");
		if(read==true){
			info("Read right is true, click on Read checkbox");
			check(ELEMENT_PERMISSION_CHECKBOXREAD,2);
		}
		if(modify==true){
			info("Modify right is true, click on Modify checkbox");
			check(ELEMENT_PERMISSION_CHECKBOXMODIFY,2);
		}
		if(remove==true){
			info("Remove right is true, click on Remove checkbox");
			check(ELEMENT_PERMISSION_CHECKBOXREMOVE,2);
		}
		Utils.pause(5000);
		info("Finished selecting right checkbox");
	}
    /**
     * Close permission form
     */
	public void closePermission() {
		info("Close permission form");
		click(ELEMENT_PERMISSION_CLOSE);
	}
	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembershipOfDrive(String groupPath, String membership){
		String[] temp;	
		info("select permission for drive");
		waitForAndGetElement(ELEMENT_DRIVE_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_DRIVE_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_DRIVE_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
		waitForElementNotPresent(ELEMENT_DRIVE_SELECT_MEMBERSHIP_POPUP);
	}
	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembershipOfCat(String groupPath, String membership){
		String[] temp;	
		info("select permission for category");
		waitForAndGetElement(ELEMENT_CAT_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_CAT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_CAT_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
		waitForElementNotPresent(ELEMENT_CAT_SELECT_MEMBERSHIP_POPUP);
	}
	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembershipOfLock(String groupPath, String membership){
		String[] temp;	
		info("select permission for lock");
		waitForAndGetElement(ELEMENT_LOCK_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_LOCK_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_LOCK_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
	}
	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembershipOfTag(String groupPath, String membership){
		String[] temp;	
		info("select permission for tag");
		click(ELEMENT_PERMISSION_SELECTMEMBERSHIP,0,true);
		waitForAndGetElement(ELEMENT_TAG_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
		Utils.pause(1000);
	}	
	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembershipOfQuery(String groupPath, String membership){
		String[] temp;	
		info("select permission for query");
		click(ELEMENT_PERMISSION_ADD,0,true);
		waitForAndGetElement(ELEMENT_TAG_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
		Utils.pause(1000);
	}
	
}
