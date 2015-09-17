package org.exoplatform.selenium.platform.administration;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ContentSearchAdministration extends PlatformBase {
	
	//table
	public final By ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_TITLE=By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");
	public final By ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_DESCRIPTION=By.xpath(".//*[@id='searchAdmin']//th[text()='Description']");
	public final By ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_ACTION=By.xpath(".//*[@id='searchAdmin']//th[text()='Action']");
	
	//Action column
	public final String ELEMENT_SEARCHADMIN_ACTION_DISABLE_BUTTON = ".//*[contains(text(),'${type}')]/..//input[@value='Disable']";
	public final String ELEMENT_SEARCHADMIN_ACTION_ENABLE_BUTTON = ".//*[contains(text(),'${type}')]/..//input[@value='Enable']";
	
	public ContentSearchAdministration(WebDriver dr){
		driver = dr;
	} 
	
}
