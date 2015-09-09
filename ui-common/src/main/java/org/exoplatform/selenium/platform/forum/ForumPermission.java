package org.exoplatform.selenium.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @date Oct 20, 2015
 * @author anhpp
 */

public class ForumPermission extends PlatformBase{
    
	/**
	 * constructor
	 * @param dr
	 */
	public ForumPermission(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
	}
	public final By ELEMENT_PERM_MOD_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[2]//input");
	public final By ELEMENT_PERM_STARTTOP_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[3]//input");
	public final By ELEMENT_PERM_POSTREPLY_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[4]//input");
	public final By ELEMENT_PERM_VIEWPOST_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[5]//input");
	public final By ELEMENT_PERM_TAB = By.xpath("//*[contains(@data-toggle,'tab')][contains(.,'Permissions')]");
	public final By ELEMENT_PERM_ROLE_ICON= By.xpath(".//*[contains(@id,'Permission')]//*[contains(@class,'uiIconMembership')]");
	public final By ELEMENT_RESTRICTED_ROLE_ICON= By.xpath(".//*[@id='DetailTab']//*[contains(@class,'uiIconMembership')]");
	public final By ELEMENT_MESSAGE_ROLE_ICON= By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconMembership')]");
	public final By ELEMENT_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[@id='UIPopupActionChildPopupWindow']");
	public final By ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP = By.xpath("//*[@id='UIForumChildPopupWindow']//*[contains(@class,'PopupContent')]");
	public final By ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[@id='UIPopupWindow']");
	public final String ELEMENT_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIPopupActionChildPopupWindow')]//a[contains(.,'$group')]";
	public final String ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP = "//*[@id='UIForumChildPopupWindow']//*[contains(@class,'PopupContent')]//a[contains(.,'$group')]";
	public final String ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP = ".//*[@id='UIPopupWindow']//a[contains(.,'$group')]";
	public final By ELEMENT_PERM_ADD_BTN=By.xpath("//*[contains(@id,'Permission')]//*[contains(@class,'addButton')]");
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



