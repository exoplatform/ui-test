package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ActivityStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DocumentPreview extends ActivityStream {
	
    //Preview mode shadow
	public final By ELEMENT_PREVIEW_MODE=By.xpath(".//*[@id='UIDocumentPreview']");
		
	
	//New File Reader form-->Actions bar
	public final By ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN=By.cssSelector("#sidebarToggle");
	public final By ELEMENT_ACTIONS_SEARCH_BTN=By.cssSelector(".uiIconSearch");
	public final By ELEMENT_ACTIONS_ARROW_UP_BTN =By.cssSelector(".uiIconArrowUp");
	public final By ELEMENT_ACTIONS_ARROW_DOWN_BTN =By.cssSelector(".uiIconArrowDown");
	public final By ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX =By.cssSelector("#pageNumber");
	public final By ELEMENT_ACTIONS_ZOOM_OUT_BTN =By.cssSelector(".uiIconMinimize");
	public final By ELEMENT_ACTIONS_ZOOM_IN_BTN =By.cssSelector(".uiIconSimplePlusMini");
	public final By ELEMENT_ACTIONS_SCALE_SELECT_BOX =By.cssSelector("#scaleSelect");
	public final By ELEMENT_ACTIONS_DOWNLOAD_BTN =By.cssSelector("#toolbarViewerRight .uiIconDownload.uiIconLightGray");
	public final By ELEMENT_ACTIONS_FULLSCREEN_BTN =By.cssSelector("#presentationMode .uiIconEcmsExpand.uiIconLightGray");
	public final By ELEMENT_ACTIONS_TOOLS_BTN =By.cssSelector("#secondaryToolbarToggle .uiIconMoreAction.uiIconLightGray");
	
	//New File Reader form --> file content
	public final By ELEMENT_READER_FILE_CONTENT_PAGE_1 = By.xpath("//*[@id='viewerContainer']");
	
	//New File Reader form--> Right panel
	public final By ELEMENT_RIGHT_PANEL_HEADER = By.xpath(".//*[@id='UIPreviewCommentArea']//*[@class='title']");
	public final String ELEMENT_RIGHT_PANEL_PROFILE_AVATAR= ".//*[contains(@class,'avatarMedium ')]//img[contains(@alt,'${fullName}')]";
	public final String ELEMENT_RIGHT_PANEL_PROFILE_NAME_LINK=".//*[contains(@class,'rightBlock')]//*[contains(@href,'${firstName}')]";
	public final By ELEMENT_RIGHT_PANEL_PROFILE_DATE_TIME = By.xpath(".//*[contains(@class,'profile')]//*[contains(@class,'dateTime')]");
	public final By ELEMENT_RIGHT_PANEL_COMMENT_INPUT_BOX = By.cssSelector("#commentTextAreaPreview");
	public final By ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT=By.cssSelector(".noComment");
	
	/**
	 * Reader Viewer area for a file that has over 99 pages or over 5Mb
	 */
	public final By ELEMENT_PREVIEW_MODE_VIEW_READER= By.id("viewer");
	public final By ELEMENT_PEVIEW_MODE_NOT_AVAIABLE_ICON = By.xpath(".//*[@class='iconContainer']/i");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview of this document is not available.']");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_LARGE_FILE= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview is not available for content larger than 5 MB.']");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_MANY_PAGES= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview is not available for content with over 99 pages.']");
	public final By ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_DOWNLOAD_BUTTON= By.xpath(".//*[@class='uiIconDownload uiIconWhite']");
	public final By ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_OPEN_IN_DESKTOP= By.xpath(".//*[@class='btn'][text()='Open on Desktop']");
	public final By ELEMENT_PREVIEW_MODE_READER_AREA= By.id("mainContainer");
	
	 /**
	  * Exit preview mode
	  * ELEMENT_PREVIEW_MODE_CROSS_ICON : "X" icon on the top-right
	 */
	//public final By ELEMENT_PREVIEW_MODE_CROSS_ICON= By.xpath(".//a[@title='Close Window']");
	public final By ELEMENT_PREVIEW_MODE_CROSS_ICON= By.xpath(".//*[@id='UIDocumentPreview']//*[@title='Close Window']");
	
	/**
     * The top of Comment area
     */
    public final By ELEMENT_COMMENT_AREA_TOP_AVATAR = By.xpath(".//*[@id='UIPreviewCommentArea']//div[@class='profile clearfix']//img");
	public final String ELEMENT_COMMENT_AREA_TOP_FULL_NAME = ".//*[@id='UIPreviewCommentArea']//div[@class='rightBlock']//a[text()='${fullName}']";
    public final By ELEMENT_COMMENT_AREA_TOP_DATE_NOTIFICATION= By.xpath(".//*[@id='UIPreviewCommentArea']//p[@class='dateTime']");
    public final String ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TILTE= ".//*[@id='UIPreviewCommentArea']//p[@title='${contentTitle}']";
    public final String ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TITLE_TRUNCATED=".//*[@id='UIPreviewCommentArea']//p[text()='${textTruncated}']";
    public final By ELEMENT_COMMENT_AREA_TOP_COMMENT_ICON=By.xpath(".//*[@id='previewCommentLink']//i[@class='uiIconComment uiIconLightGray']");
    public final By ELEMENT_COMMENT_AREA_TOP_LIKE_ICON = By.xpath(".//*[@id='UIPreviewCommentArea']//*[contains(@class,'uiIconThumbUp')]");
    public final By ELEMENT_COMMENT_AREA_TOP_LIKE_ICON_BLUE= By.xpath(".//*[@id='UIPreviewCommentArea']//i[@class='uiIconThumbUp uiIconBlue']");
    public final String ELEMENT_COMMENT_AREA_TOP_LIKE_NUMBER = "//*[contains(@id,'UIPreviewCommentArea')]//*[contains(@href,'javascript:void(0);')][contains(.,'${number}')]";
	
	/**
	 * Comment area
	 */
	public final By ELEMENT_COMMENT_AREA= By.xpath(".//*[@class='uiBox commentArea pull-right']");
	public final By ELEMENT_COMMENT_COLLAPSE_ICON = By.xpath(".//*[contains(@id,'UIDocumentPreview')]//*[contains(@class,'uiIconMiniArrowRight')]"); 
	public final By ELEMENT_COMMENT_EXPAND_ICON = By.xpath(".//*[contains(@id,'UIDocumentPreview')]//*[contains(@class,'uiIconMiniArrowLeft')]"); 
	public final By ELEMENT_COMMENT_DOCUMENT_ICON = By.xpath(".//*[contains(@id,'UIDocumentPreview')]//*[contains(@class,'uiIcon16x16applicationpdf')]");
	public final By ELEMENT_COMMENT_DOCUMENT_NAME = By.xpath(".//*[@id='UIDocumentPreview']//*[@class='title']");
	public final By ELEMENT_COMMENT_INPUT_AVATAR = By.xpath(".//*[@class='commentInputBox']//*[@class='avatarXSmall pull-left']");
	public final By ELEEMNT_COMMENT_LIST_AVATAR=By.xpath(".//*[@class='commentList']//*[@class='avatarXSmall pull-left']");
	public final By ELEMENT_COMMENT_INPUT_FIELD = By.xpath(".//*[@id='commentTextAreaPreview']");
	public final By ELEMENT_COMMENT_INPUT_PLACEHOLDER= By.xpath(".//*[@placeholder='Add your comment...']");
	public final By ELEMENT_COMMENT_BOX = By.xpath(".//*[@id='commentTextAreaPreview']");
	public final String ELEMENT_COMMENT_CONTENT = ".//*[@class='cont'][contains(text(),'${text}')]";
	public final By ELEMENT_COMMENT_EMPTY = By.xpath(".//*[text()='No comment yet']");
	public final By ELEMENT_COMMENT_LIST = By.xpath(".//*[@id='UIPreviewCommentArea']//ul[@class='commentList']");
	public final String ELEMENT_COMMENT_DELETE_ICON="i.uiIconLightGray.uiIconClose";
    public final By ELEMENT_COMMENT_TEXT = By.xpath(".//*[@class='cont']");
	
	
	public DocumentPreview(WebDriver dr) {
		super(dr);
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
