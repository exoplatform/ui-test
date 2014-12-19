package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePlatform extends TestBase{
	WikiHomePage wHome;
	CalendarHomePage cHome;
	SpaceManagement sMang;
	
	//Left panel
	public final By ELEMENT_WIKI_LINK_PLF=By.xpath("//*[@data-original-title='Wiki']");
	public final By ELEMENT_HOME_LINK_PLF=By.xpath("//*[@data-original-title='Home']");
	public final By ELEMENT_CALENDAR_LINK_PLF=By.xpath("//*[@data-original-title='Calendar']");
	public final By ELEMENT_MY_SPACE_LINK_PLF=By.xpath("//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'My Spaces')]");
	public final String ELEMENT_SPECIFIC_SPACE_LINK_PLF ="//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'{$space}')]";

	//Middle homepage panel
	public final By ELEMENT_HOMPAGE_MIDDLE_PANEL = By.id("OfficeMiddle");
	

	//Right panel

	/**
	 * constructor
	 * @param dr
	 */
	public HomePagePlatform(WebDriver dr){
		this.driver=dr;
		wHome = new WikiHomePage(dr);
		cHome = new CalendarHomePage(dr);
		sMang = new SpaceManagement(dr);
	}
	
	/**
	 * Go to Wiki portlet
	 */
	public void goToWiki(){
		info("--Go to Wiki--");
		click(ELEMENT_WIKI_LINK_PLF);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGE_LINK);	
	}

	/**
	 * Go to Home page
	 */
	public void goToHomePage(){
		info("--Go to Home page--");
		click(ELEMENT_HOME_LINK_PLF);
		waitForAndGetElement(ELEMENT_HOMPAGE_MIDDLE_PANEL);
	}
	
	/**
	 * Go to Home Calendar Page
	 */
	public void goToCalendarPage(){
		info("-- Go to calendar home page --");
		click(ELEMENT_CALENDAR_LINK_PLF);
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_WORKING_PANEL);
	}
	
	/**
	 * Go to My Space
	 */
	public void goToMySpace(){
		info("-- Go to My space page --");
		click(ELEMENT_MY_SPACE_LINK_PLF);
		waitForAndGetElement(sMang.ELEMENT_SPACE_MY_SPACE_PORTLE);
	}
}
