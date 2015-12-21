package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalBranding extends GateinLocator {

	public PortalBranding(WebDriver driver) {
		this.driver = driver;
	}


	public PortalBranding uploadFile(String link, Object... params) {
		info("Upload a file to Site Explorer");
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		if (waitForAndGetElement(ELEMENT_BUTTON_UPLOAD, DEFAULT_TIMEOUT, 0) == null) {
			info("wrong page");
		}

		
		WebElement upload=waitForAndGetElement(ELEMENT_UPLOAD_LINK,DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; " +
				"arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", upload);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible'", upload);
		Utils.pause(10000);
		info("Select a file to upload");
		upload.sendKeys(getAbsoluteFilePath(link));
		info("Upload file " + getAbsoluteFilePath(link));
		info("Switch to Parent window");
		switchToParentWindow();
		if (verify) {
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[@id='PreviewImg']"));
		}
		info("Upload file successfully");
		Utils.pause(2000);
		return new PortalBranding(driver);
	}
}
