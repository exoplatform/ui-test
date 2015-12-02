package org.exoplatform.selenium.platform.acme;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.ContentList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AcmeHomePage extends PlatformBase {

	//Register
	public final By ELEMENT_REGISTER_LINK=By.xpath(".//*[@class='RegisterIcon']");
	public final By ELEMENT_REGISTER_TITLE=By.xpath("//*[@class='title'][contains(text(),'Register New Account')]");
	public final By ELEMENT_REGISTER_USERNAME=By.xpath(".//*[@id='username']");
	public final By ELEMENT_REGISTER_PASSWORD=By.xpath(".//*[@id='password']");
	public final By ELEMENT_REGISTER_CONFIRM_PASSWORD=By.xpath(".//*[@id='confirmPassword']");
	public final By ELEMENT_REGISTER_FIRSTNAME=By.xpath(".//*[@id='firstName']");
	public final By ELEMENT_REGISTER_LASTNAME=By.xpath(".//*[@id='lastName']");
	public final By ELEMENT_REGISTER_DISPLAYNAME=By.xpath(".//*[@id='displayName']");
	public final By ELEMENT_REGISTER_EMAIL=By.xpath(".//*[@id='emailAddress']");
	
	
	public final By ELEMENT_TOPBAR_SEARCHBOX = By.xpath("//*[@class='keyword']");
	public final String ELEMENT_SEARCHRESULT_TITLE = "//*[@class='content']//*[text()='${title}']";
	public final By ELEMENT_BARMENU_NEWS = By.xpath("//*[@title='News']");
	public final By ELEMENT_TOPIC_DEFENSE = By.xpath("//*[@title='Defense']");
	public final String ELEMENT_NEWS_DEFENSE_CONTENT= ".//*[@class='ContentHotNews']//*[text()='${title}']";
	public final String ELEMENT_OVERVIEWS_CONTENT = ".//*[@data-original-title='${title}']";
	
	//navigation menu
	public final By ELEMENT_NAVIGATION_MENU_OVERVIEW_FRENCH = By.xpath(".//*[@id='navigation-generator']//a[@title='Présentation']");
	public final By ELEMENT_NAVIGATION_MENU_OVERVIEW_ENGLISH= By.xpath(".//*[@id='navigation-generator']//a[@title='Overview']");
	
	ContentList contList;
	public AcmeHomePage(WebDriver driver) {
		this.driver= driver;
		contList = new ContentList(driver);
	}

	/**
	 * Search in the quick search box on acme
	 * @param name
	 */
	public void searchQuickSearchBox(String name) {
		waitForAndGetElement(ELEMENT_TOPBAR_SEARCHBOX);
		type(ELEMENT_TOPBAR_SEARCHBOX, name, true);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		action.release();
	}
	
	/**
	 * go to news page on acme
	 */
	public void goToNews() {
		click(ELEMENT_BARMENU_NEWS);
		Utils.pause(2000);
	}
	
	/**
	 * Edit preference of Content list
	 * @param newContent
	 */
	public void editPreferenceContentList(String newContent){
		mouseOver(contList.ELEMENT_CONTENT_LIST_CONTENT_BOX, true);
		click(contList.ELEMENT_CONTENT_LIST_CONTENT_BOX_PREFERENCES_BTN );
		click(contList.ELEMENT_CONTENT_LIST_DISPLAY_SETTING_TAB);
		type(contList.ELEMENT_DISPLAY_SETTING_TAB_HEADER_FIELD, newContent, true);
		click(contList.ELEMENT_CONTENT_LIST_SAVE_BTN);
	}
	
}
