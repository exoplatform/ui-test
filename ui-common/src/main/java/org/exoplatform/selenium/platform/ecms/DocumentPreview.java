package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.ActivityStreamLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DocumentPreview extends ActivityStreamLocator {
	
	public DocumentPreview(WebDriver dr) {
		this.driver=dr;
	}
	
	/**
	 * Close Preview mode and Back to Activity stream by pressing ECS
	 */
	public void closeByPressECS(){
		info("press ESC key");
		Actions action = new Actions (this.driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		action.release();
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_PREVIEW_MODE_CROSS_ICON,3000,1);
	}
	
   /**
    * Close Preview mode and Back to Activity stream by clicking Cross (X) icon
    */
   public void closeByClickCrossIcon(){
	   info("Close preview mode by clicking on Cross (X) icon");
	   waitForAndGetElement(ELEMENT_PREVIEW_MODE_CROSS_ICON,3000,1);
	   click(ELEMENT_PREVIEW_MODE_CROSS_ICON);
	   waitForElementNotPresent(ELEMENT_PREVIEW_MODE_CROSS_ICON,3000,1);
   }
   /**
    * Close Preview mode by clicking on Background
    */
   public void closeByClickBackground(){
	   info("Click on background");
	   info("Get a pixel on Black background");
	    WebElement el = waitForAndGetElement(ELEMENT_PREVIEW_MODE,2000,0);
		Point dis = el.getLocation();
		int x = dis.getX()+20;
		int y = dis.getY()+50;
		info("x is:"+x);
		info("y is:"+y);
		info("click on black background of Display area");
		Actions action = new Actions(this.driver);
		action.moveToElement(el,0,0).moveByOffset(x,y).click().build().perform();
				
		info("Verify that the preview is closed");
		waitForElementNotPresent(ELEMENT_PREVIEW_MODE,3000,1);
   }
   
   /**
    * Check properties of shadow mask display
    */
   public void shadowMask(int wd_preview, int wd_br){
	   info("Check shadow Mask");
	        if (wd_preview>=wd_br)
	        	assert true;
	        else 
                assert false: ("The shadow mask doesn't occupie the whole area of the browser window.");
   }
   
   /**
	 * Collapse Comment area
	 */
	public void collapseCommentArea(){
		info("Collapse comment area");
	    waitForAndGetElement(ELEMENT_COMMENT_COLLAPSE_ICON,2000,0);
		click(ELEMENT_COMMENT_COLLAPSE_ICON);
		waitForAndGetElement(ELEMENT_COMMENT_EXPAND_ICON,2000,1);
		info("Collapse comment area is successfully");
	}
	
	/**
	 * Expand Comment area
	 */
	public void expandCommentArea(){
		info("Expand comment area");
	    waitForAndGetElement(ELEMENT_COMMENT_EXPAND_ICON,2000,1);
		click(ELEMENT_COMMENT_EXPAND_ICON);
		waitForAndGetElement(ELEMENT_COMMENT_COLLAPSE_ICON,2000,1);
		info("Expand comment area is successfully");
	}
	
	/**
	 * press Enter to add comments to Comment area
	 * @param text
	 * @param number is the number of comments that are added to the area
	 */
	public void addComment(String text, int number) {
		info("Start to add a comment to Comment area");
		for(int i=0;i<number;i++){
			info("Input a text into input comment field");
			waitForAndGetElement(ELEMENT_COMMENT_INPUT_FIELD,2000,1);
			type(ELEMENT_COMMENT_INPUT_FIELD,text,true);
			
			info("Press Enter to add a comment to Comment area");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		}
		info("Finish adding a comment to Comment area");
	}
		
}
