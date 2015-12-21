package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class ConnectionsLocator extends PlatformBase {

	public final By ELEMENT_CONNECTION_EVERYONE_TITLE=By.xpath(".//*[@id='UIAllPeople']//*[contains(text(),'Contacts Directory')]");
	public final String ELEMENT_CONNECTION_CONNECT_BTN = "//a[contains(@href,'${user}')]/../..//*[text()='Connect']";
	public final String ELEMENT_CONNECTION_CANCEL_BTN = "//a[contains(@href,'${user}')]/../..//*[text()='Cancel Request']";
	public final String ELEMENT_CONNECTION_REVOVE_BTN = "//a[contains(@href,'${user}')]/../..//*[text()='Remove Connection']";

	public final String ELEMENT_CONNECTION_IGNORE_BTN =" //a[contains(@href,'${user}')]/../..//*[text()='Ignore']";
    public final String ELEMENT_CONNECTION_CONFIRM_BTN =" //a[contains(@href,'${user}')]/../..//*[text()='Confirm']";
    public final String ELEMENT_CONNECTION_USER_AVARTAR="//a[contains(@href,'${user}')]/img";
    public final String ELEMENT_CONNECTION_USER_NAME="//a[contains(@href,'${user}') and @data-key='title']";
    
    public final By ELEMENT_ALL_CONNECTIONS_TAB=By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'all-people')]");
    public final By ELEMENT_MY_CONNECTIONS_TAB = By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'network')]");
    public final By ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB = By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'receivedInvitations')]");
    public final By ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB = By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'pendingRequests')]");
    
    public final By ELEMENT_ALL_RESULTS = By.id("searchAll");
    public final By ELEMENT_NAME_OF_PEOPLE = By.id("name");
    public final By ELEMENT_POSITIONS_OF_PEOPLE = By.id("position");
    public final By ELEMENT_SKILL_OF_PEOPLE = By.id("skills");
    public final By ELEMENT_SEARCH_BUTTON = By.id("SearchButton");
    public final String ELEMENT_USER_LINK = "//*[@class='spaceTitle']//*[contains(@href,'${userName}')]";
    public final String ELEMENT_USER_AVATAR =".//*[@alt='${fullname}']";
}