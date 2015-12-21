package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class PlatformPermissionLocator extends PlatformBase {

	//Option select permission button
	public final By ELEMENT_SELECT_USER_ICON = By.xpath("//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_SELECT_USER_ICON1 = By.xpath("//*[contains(@class,'uiIconSelectUser')]");
	public final By ELEMENT_SELECT_GROUP_ICON = By.xpath("//*[@class='uiIconGroup uiIconLightGray']");
	public final By ELEMENT_SELECT_MEMBERSHIP_ICON = By.xpath("//*[@class='uiIconMembership uiIconLightGray']");

	//User permission
	public final String ELEMENT_USER_CHECKBOX = "//*[text()='${user}']/../..//*[@type='checkbox']";
	public final String ELEMENT_USER_LIST="//*[@id='UIListUsers']//*[contains(.,'${user}')]";
	public final By ELEMENT_ADD_USERS_BUTTON = By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");
	public final By ELEMENT_SEARCH_USER_INPUT = By.id("Quick Search");
	public final By ELEMENT_QUICK_SEARCH_BUTTON = By.xpath("//a[@data-original-title='Quick Search']/i");
	public final By ELEMENT_SELECT_SEARCH = By.name("filter");
	public final By ELEMENT_USER_CLOSE_BUTTON = By.xpath("//*[@id='UIUserSelector']//*[contains(@class,'btn')][contains(.,'Close')]");
	//Group permission
	public final By ELEMENT_SELECT_GROUP_POPUP = By.id("UIPopupGroupMembershipSelector");
	public final By ELEMENT_SELECT_THIS_GROUP = By.linkText("Select this Group");
	public final String ELEMENT_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIGroupMember')]//a[contains(.,'$group')]";

	//Membership permission
	public final By ELEMENT_SELECT_MEMBERSHIP_POPUP = By.xpath("//*[contains(@id,'UIGroupMember')]");

}