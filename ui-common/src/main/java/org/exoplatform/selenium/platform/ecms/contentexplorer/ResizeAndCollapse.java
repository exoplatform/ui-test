package org.exoplatform.selenium.platform.ecms.contentexplorer;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ResizeAndCollapse extends EcmsBase {
	
	public ResizeAndCollapse(WebDriver dr) {
		super(dr);
	}
	
	BrowserPreferences brPre = new BrowserPreferences(driver);
	
	//Panel limit
	public final By ELEMENT_PANEL_LIMIT = By.className("resizeBar");
	public final By ELEMENT_PANEL_COLLAPSE_BUTTON = By.className("resizeButton");
	public final By ELEMENT_LEFT_CONTAINER = By.id("LeftContainer");
	
	//Right panel
	public final By ELEMENT_RIGHT_CONTAINER = By.className("rightContainer");
	public final String ELEMENT_BORDER_COLUMN = "//*[@class='lineLeft'][${position}]";
	public final String ELEMENT_COLUMN_NAME = "//*[@class='columnName' and contains(., '${name}')]";
	
	/**function show side bar in personal document
	 * @author lientm
	 */
	public void showSideBar(){
		WebElement panel = waitForAndGetElement(ELEMENT_PANEL_LIMIT, 5000, 0);
		if (panel == null){
			brPre.setUpPreferenceOption("showSideBar");
		}
	}
	
	/**function resize limit panel
	 * @author lientm
	 * @param xOffset
	 */
	public void resizeLimitPanel(int xOffset){
		Actions res = new Actions(driver);
		info("Resize limit panel to " + xOffset);
		res.dragAndDropBy(waitForAndGetElement(ELEMENT_PANEL_LIMIT), xOffset, 0).build().perform();
		Utils.pause(2000);
	}
	
	/**function resize column in right panel
	 * @author lientm
	 * @param pos
	 * @param xOffset
	 */
	public void resizeColumnInRightPanel(String pos, int xOffset){
		Actions res = new Actions(driver);
		info("Resize column to " + xOffset);
		res.dragAndDropBy(waitForAndGetElement(ELEMENT_BORDER_COLUMN.replace("${position}", pos)), xOffset, 0).build().perform();
		Utils.pause(2000);
	}
}
