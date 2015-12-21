package org.exoplatform.selenium.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.ManageAlert;
import org.openqa.selenium.WebDriver;


public class ForumPermission extends ForumLocator{
    
	/**
	 * constructor
	 * @param dr
	 */
	public ForumPermission(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
	}
	
	/**
	 * select permission in category
	 * @param isMod
	 * @param isStartTop
	 * @param isPostReply
	 * @param isViewPost
	 */
	public void selectPermInCategory(boolean isMod,boolean isStartTop,boolean isPostReply,boolean isViewPost){
		info("select permission");
		if(isMod){
			check(ELEMENT_PERM_MOD_CHECKBOX,2);
		}else{
			uncheck(ELEMENT_PERM_MOD_CHECKBOX,2);
		}
		if(isStartTop){
			check(ELEMENT_PERM_STARTTOP_CHECKBOX,2);
		}else{
			uncheck(ELEMENT_PERM_STARTTOP_CHECKBOX,2);
		}
		if(isPostReply){
			check(ELEMENT_PERM_POSTREPLY_CHECKBOX,2);
		}else{
			uncheck(ELEMENT_PERM_POSTREPLY_CHECKBOX,2);
		}
		if(isViewPost){
			check(ELEMENT_PERM_VIEWPOST_CHECKBOX,2);
		}else{
			uncheck(ELEMENT_PERM_VIEWPOST_CHECKBOX,2);
		}
	}
	/**
	 * Select permission group membership
	 * @param groupPath
	 * @param member
	 * @param isMod
	 * @param isStartTop
	 * @param isPostReply
	 * @param isViewPost
	 */
	public void selectPermGroupMember(String groupPath,String member,boolean isMod,boolean isStartTop,boolean isPostReply,boolean isViewPost){
		String[] temp;	
		info("select group membership");
		click(ELEMENT_PERM_TAB,0,true);
		click(ELEMENT_PERM_ROLE_ICON,0,true);
		waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]),0,true);
		}
		click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", member),0,true);
		waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		click(ELEMENT_PERM_ADD_BTN,0,true);
		selectPermInCategory(isMod,isStartTop,isPostReply,isViewPost);
	}
	/**
	 * Select permission group membership
	 * @param groupPath
	 * @param member
	 */
	public void selectPermGroupMemberRestricted(String groupPath,String member){
		String[] temp;	
		info("select group membership");
		click(ELEMENT_RESTRICTED_ROLE_ICON,0,true);
		waitForAndGetElement(ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]),0,true);
		}
		click(ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP.replace("$group", member),0,true);
		waitForElementNotPresent(ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP);
	}
	/**
	 * select permission in topic
	 * @param isView
	 * @param isPost
	 */
	public void selectPermInTopic(boolean isPost,boolean isView){
		info("select permission");
		if(isPost){
			check(ELEMENT_PERM_MOD_CHECKBOX,2);
		}else{
			uncheck(ELEMENT_PERM_MOD_CHECKBOX,2);
		}
		if(isView){
			check(ELEMENT_PERM_STARTTOP_CHECKBOX,2);
		}else{
			uncheck(ELEMENT_PERM_STARTTOP_CHECKBOX,2);
		}
	}
	/**
	 * Select permission group membership in topic
	 * @param groupPath
	 * @param member
	 * @param isView
	 * @param isPost
	 */
	public void selectPermGroupMemberInTopic(String groupPath,String member,boolean isView,boolean isPost){
		String[] temp;	
		info("select group membership");
		click(ELEMENT_PERM_TAB,0,true);
		click(ELEMENT_PERM_ROLE_ICON,0,true);
		waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]),0,true);
		}
		click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", member),0,true);
		waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		click(ELEMENT_PERM_ADD_BTN,0,true);
		selectPermInTopic(isView, isPost);
	}
	/**
	 * Select permission group membership
	 * @param groupPath
	 * @param member
	 */
	public void selectPermGroupMemberMes(String groupPath,String member){
		String[] temp;	
		info("select group membership");
		click(ELEMENT_MESSAGE_ROLE_ICON,0,true);
		waitForAndGetElement(ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]),0,true);
		}
		click(ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP.replace("$group", member),0,true);
		waitForElementNotPresent(ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP);
	}
}



