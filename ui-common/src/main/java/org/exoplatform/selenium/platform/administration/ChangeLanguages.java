package org.exoplatform.selenium.platform.administration;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.WebDriver;

public class ChangeLanguages extends AdministrationLocator {
	
	
	public ChangeLanguages(WebDriver dr){
		driver = dr;
	} 
	/**
	 * Select a language and changed it
	 * @param language
	 */
	public void changeLanguage(String language, String applyText){
		info("Select language and change it");
		waitForAndGetElement(ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE);
		click(ELEMENT_CHANGELANGUAGE_LANGUAGE.replace("${language}",language));
		click(ELEMENT_AVATAR_CHANGELANGUAGE_APPLY.replace("${text}",applyText));
	}
}
