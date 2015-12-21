package org.exoplatform.selenium.platform.acme;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class AcmeHomePageLocator extends PlatformBase {

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
	
	
	
}
