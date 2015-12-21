package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class IDELocator extends PlatformBase {
	
	public final By ELEMENT_PLF_IDE_DISPLAY = By.xpath("//*[@class='navItemSelected' and text()='IDE']");
	public final By ELEMENT_PLF_IDE_FOLDER = By.xpath("//*[@class='treeCellSelected']//*[contains(text(),'dev-monit')]");
	public final By ELEMENT_PLF_IDE_WORKSPACE = By.xpath("//*[contains(text(),'Workspace')]");
}
