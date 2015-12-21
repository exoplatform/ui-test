package org.exoplatform.selenium.platform.gatein;

import org.openqa.selenium.WebDriver;

public class DisableUser extends GateinLocator {

	public DisableUser(WebDriver dr) {
		this.driver = dr;
	}
	
	/**
	 * Define status's types as Enable,Disable and All
	 */
	public enum typeStatus{
		Enabled,Disabled,All;
	}

    /**
     * Select status's type
     * @param type
     *              is a value as: Enable,Disable and All
     */
	public void selectStatus(typeStatus type){
		switch(type){
		case Enabled:
			selectOption(ELEMENT_DISABLE_USER_DROP_BOX,type.toString().toUpperCase());
			break;
		case Disabled:
			break;
		case All:
			break;
		}
	}
}
