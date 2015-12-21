package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class BrandingLocator extends PlatformBase {

	public final By ELEMENT_UPLOAD_LINK = By.name("file");

	public final By ELEMENT_PLF_BRANDINGPAGE = By.xpath("//*[@class='uiBreadcumbsNavigations']//*[text()='Branding']");
	public final By ELEMENT_BANDING_PAGE_SELECT_LOGO =By.xpath(".//h4[text()='Select Logo']");
	public final By ELEMENT_BANDING_PAGE_SELECT_NAVIGATION_BAR_STYLE =By.xpath(".//h4[text()='Select Navigation Bar Style']");
	public final By ELEMENT_BANDING_PAGE_PREVIEW =By.xpath(".//h4[text()='Preview']");
	
	//Theme selection
	public final By ELEMENT_PLF_BRANDING_SELECTTHEME = By.xpath("//*[@class='btn dropdown-toggle']");
	public final By ELEMENT_PLF_BRANDING_THEMEDARK = By.xpath("//*[@class='OptionItem' and text()='Dark']");
	public final By ELEMENT_PLF_BRANDING_THEMELIGHT = By.xpath("//*[@class='OptionItem' and text()='Light']");
	
	//Displayed top bar 
	public final By ELEMENT_PLF_BRANDING_TOPBAR_THEMELIGHT = By.xpath("//*[@class='UIContainer UIToolbarContainer  UIToolbarContainerLight']");
	public final By ELEMENT_PLF_BRANDING_TOPBAR_THEMEDARK = By.xpath("//*[@class='UIContainer UIToolbarContainer  UIToolbarContainerLight']");
	public final By ELEMENT_PLF_BRANDING_TOPBAR_LOGO = By.xpath("//*[@alt='Home' and contains(@src, 'logo_preview.png')]");
	
	//Button
	public final By ELEMENT_BUTTON_SAVE = By.xpath("//*[text()='Save']");
	public final By ELEMENT_BUTTON_CANCEL=By.xpath(".//*[@id='cancel']");
	public final By ELEMENT_BUTTON_UPLOAD = By.xpath("//*[@id='btUpload']");
	
}
