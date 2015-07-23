package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpaceHomePage extends PlatformBase{

	
	public final By ELEMENT_SPACE_PANEL=By.xpath(".//*[@id='UIMySpacesPortlet']");
	
	//select menu (actvity stream, forum, agenda etc ..)
	public final By ELEMENT_SPACE_MENU_ACTIVITY_STREAM = By.xpath(".//*[@class='tabName' and contains(text(),' Activity Stream')]");
	public final By ELEMENT_SPACE_MENU_AGENDA = By.xpath(".//*[@id='calendar' and contains(text(),'Agenda')]");
	public final By ELEMENT_SPACE_MENU_FORUMS = By.xpath(".//*[@class='tabName' and contains(text(),'Forums')]");
	public final By ELEMENT_SPACE_MENU_WIKI = By.xpath(".//*[@class='tabName' and contains(text(),'Wiki')]");
	public final By ELEMENT_SPACE_MENU_DOCUMENTS = By.xpath(".//*[@class='tabName' and contains(text(),'Documents')]");
	public final By ELEMENT_SPACE_MENU_SETTINGS = By.xpath(".//*[@class='tabName' and contains(text(),'Space Settings')]");
	public final By ELEMENT_SPACE_MENU_ANSWER = By.xpath(".//*[@class='tabName' and contains(text(),'Answer')]");
	public final String ELEMENT_SPACE_NAME = ".//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name'][contains(text(),'${name}')]";
	public final String ELEMENT_SPACE_MENU_DISPLAYORDER_ID = ".//*[@id='spaceMenuTab']/li[${number}]//*[contains(@class,'${tab}')]";
	public final String ELEMENT_SPACE_MENU_DISPLAYORDER = ".//*[@id='spaceMenuTab']/li[${number}]//*[contains(text(),'${tab}')]";
	
	//Navigation menu
	public final By ELEMENT_HORIZOLTAL_MENU_BAR = By.xpath(".//*[@id='spaceMenuTab']");
	public final String ELEMENT_SPACE_TAB_NAME=".//*[@id='spaceMenuTab']//*[contains(text(),'${name}')]";
	public final By ELEMENT_ACTIVITY_STREAM_TAB = By.xpath(".//*[contains(@class,'uiIconAppSpaceActivityStreamPortlet')]");
	public final By ELEMENT_FORUM_TAB = By.xpath(".//*[contains(@class,'uiIconAppForumPortlet')]");
	public final By ELEMENT_WIKI_TAB = By.xpath(".//*[contains(@class,'uiIconAppWikiPortlet')]");
	public final By ELEMENT_DOCUMENT_TAB = By.xpath(".//*[contains(@class,'uiIconAppFileExplorerPortlet')]");
	public final By ELEMENT_AGENDA_TAB = By.xpath(".//*[contains(@class,'uiIconAppCalendarPortlet')]");
	public final By ELEMENT_MEMBER_TAB = By.xpath(".//*[contains(@class,'uiIconAppMembersPortlet')]");
	public final By ELEMENT_SPACE_SETTING_TAB = By.xpath(".//*[contains(@class,'uiIconAppSpaceSettingPortlet')]");
	
	public By ELEMENT_SPACE_SPACE_SETTINGS=By.xpath("//*[@class='uiIconAppSpaceSettingPortlet uiIconDefaultApp']/..");
	public By ELEMENT_SPACE_WIKI_TAB=By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Wiki')]");
	public By ELEMENT_SPACE_WIKI_TAB_CONTENT=By.xpath("//*[@id='UIWikiPortlet']");
	public By ELEMENT_SPACE_WIKI_TAB_ACTIVE=By.xpath("//*[@class='active item']/*[@data-toggle='tab']/*[contains(text(),'Wiki')]");
	public String ELEMENT_SPACE_MENU_TAB=".//*[@id='spaceMenuTab']//*[@id='${tab}']";
	public By ELEMENT_SPACE_MENU_MORE = By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'More')]");
	public By ELEMENT_SPACE_MENU_DASHBOARD = By.xpath(".//*[@id='spaceMenuTab']//*[@id='DashboardPortlet']");
	public By ELEMENT_SPACE_SPACE_SETTINGS_TITLE = By.xpath(".//*[@id='UISpaceSettingPortlet']/h3[text()='Space Configuration']");
	
	//left menu
	public String ELEMENT_SPACE_LEFT_MENU_SPACE_NAME = ".//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'${name}')]";
	
	
	SpaceSettingManagement setSpaceMg;
	/**
	 * constructor
	 * @param dr
	 */
	public SpaceHomePage(WebDriver dr){
		this.driver=dr;
		
	}
	
	/**
	 * Open Space setting page
	 */
	public void goToSpaceSettingTab(){
		info("--Open Setting tab of the space");
		info("Click on the tab");
		Utils.pause(2000);
		click(ELEMENT_SPACE_SPACE_SETTINGS,0,true);
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_SPACE_SPACE_SETTINGS_TITLE);
		info("Space setting page is shown");
	}
	/**
	 * Open Wiki portlet of space
	 */
	public void goToWikiTab(){
		info("--Open Wiki tab of the space");
		info("Click on the tab");
		waitForAndGetElement(ELEMENT_SPACE_WIKI_TAB,3000,0).click();
		info("wiki page is shown");
	}
	/**
	 * Open a space from left menu
	 * @param name
	 */
	public void goToSpace(String name){
		info("Go to the Space:"+name);
		waitForAndGetElement(ELEMENT_SPACE_LEFT_MENU_SPACE_NAME.replace("${name}",name),2000,0).click();
		waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}",name),2000,0);
		info("The space is shown");
	}
}