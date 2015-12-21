package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.ManageAlert;
import org.openqa.selenium.WebDriver;

public class SEOManagement extends ECMSLocator {
	
	ManageAlert magAlert;
		
	public SEOManagement(WebDriver driver) {
		this.driver=driver;
		magAlert = new ManageAlert(driver);
	}
	/**
	 * Delete a added language
	 * @param language
	 */
	public void deleteLanguage(String language){
		click(ELEMENT_SEO_SELECTED_LANGUAGE.replace("${language}",language));
		click(ELEMENT_SEO_DELETE);
		magAlert.acceptAlert();
		click(ELEMENT_SEO_CLOSE);
	}
	
		
}
