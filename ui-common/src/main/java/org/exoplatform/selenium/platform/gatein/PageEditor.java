package org.exoplatform.selenium.platform.gatein;
import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.WebDriver;

public class PageEditor extends PlatformBase {
	//Common
	public final By ELEMENT_EDIT_PORTLET_ICON = By.xpath("//*[@data-original-title='Edit Portlet']");
	public final By ELEMENT_DELETE_PORTLET_ICON = By.xpath("//*[@data-original-title='Delete Portlet']");
	public final String ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET =".//*[@class='portletLayoutDecorator'][contains(text(),'${name}')]";
	public final By ELEMENT_CONTAINER_TAB=By.xpath(".//*[@id='UIPortalComposerTab']//*[@data-target='#contList']");
	public final By ELEMENT_APPLICATION_TAB=By.xpath(".//*[@id='UIPortalComposerTab']//*[@data-target='#appList']");
	
	//Edit portlet form
	public final By ELEMENT_EDIT_PORTLET_FORM=By.id("tab-UIPortletForm");
	public final By ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Close']");
	public final By ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Save']");
	public final By ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Save Settings']");
	public final By ELEMENT_EDIT_PORTLET_FORM_SAVE_AND_CLOSE_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Save And Close']");
	public final By ELEMENT_EDIT_PORTLET_FORM_CANCEL_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Cancel']");
	public final By ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE = By.xpath("//*[@id='resultsPerPage']");
	public final String ELEMENT_EDIT_PORTLET_SEARCH_OPT_CHECKED = ".//*[@id='lstSearchInOptions']//*[contains(.,'$opt')]/../*[@checked='checked']";
	//Application
	public final String ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT="//*[@title='$category']/*[contains(@class,'uiIconArrowRight')]";
	public final String ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_DOWN="//*[@title='$category']/*[contains(@class,'uiIconArrowDown')]";
	public final String ELEMENT_EDIT_PORTLET_APPLICATION_ID="//*[contains(@id,'$portlet')]";
	
	//Finish and Abort button
	public final By ELEMENT_EDIT_PORTLET_FINISH = By.xpath("//*[@data-original-title='Finish']");
	public final By ELEMENT_EDIT_PORTLET_ABORT= By.xpath("//*[@data-original-title='Abort']");
	
	//View page properties
	public final By ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES=By.xpath("//*[@class='btn btn-primary PageProfileIcon']");
	public final By ELEMENT_PERMISSTION_SETTING_TAB = By.xpath("//*[@data-target='#PermissionSetting-tab']");
	public final By ELEMENT_PUBLIC_MODE=By.id("publicMode");
	
	//Middle container
	public final By ELEMENT_EDIT_PAGE_PAGE = By.id("UIPage");
	public final By ELEMENT_PAGE_EDITOR_SAVE_BUTTON=By.xpath("//*[@id='UIPageForm']//*[text()='Save']");
	public final By ELEMENT_PAGE_EDITOR_SAVE_AND_CLOSE_BUTTON=By.xpath("//*[text()='Save And Close']");
	public final By ELEMENT_PAGE_EDITOR_CLOSE_BUTTON=By.id("Close");
	public final By ELEMENT_FRAME_CONTAIN_PORTLET = By.xpath("//div[contains(@id,'UIPortlet')]");
	public final By ELEMENT_EDIT_SAVE_BUTTON=By.xpath("//*[text()='Save']");
	public final By ELEMENT_PAGE_OK_BUTTON=By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");
	
	//Container
	public final String ELEMENT_CONTAINER_ID=".//*[@id='${id}']/div/div[1]/div/div";
	
	
	public PageEditor(WebDriver dr){
		driver = dr;
	}
	
    /**
     * Edit a Portlet with locator of the portlet
     * @param locatorPortlet
     */
	public void goToEditPortlet(Object locatorPortlet){
		info("Go to edit portlet");
		mouseOver(locatorPortlet, true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForAndGetElement(ELEMENT_EDIT_PORTLET_FORM);
	}
	
	/**
	 * Edit a Portlet with the name of portlet
	 * @param namePortlet
	 */
	public void goToEditPortlet(String namePortlet){
		info("Go to edit portlet");
		mouseOver(ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET.replace("${name}", namePortlet), true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForAndGetElement(ELEMENT_EDIT_PORTLET_FORM);
	}
	
	/**
	 * Finish Editing PageLayout
	 */
	public void finishEditLayout(){
		info("Finish Editing PageLayout");
		click(ELEMENT_EDIT_PORTLET_FINISH);
		waitForElementNotPresent(ELEMENT_EDIT_PORTLET_FINISH,60000);
	}
	/**
	 * Select an application and move to tagerget container
	 * @param portletPath (Format: catgory-porlets). Ex: answer-AnswersPortlet)
	 * @param targetLocator
	 */
	public void selectApplication(String portletPath,Object targetLocator ){
		info("Select an application and move to tagerget container");
		String[] portlets = portletPath.split("-");
		if(portlets.length>1){
			for(int i = 0; i<portlets.length-1;i++){
				if(waitForAndGetElement(ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT.replace("$category",portlets[i]),5000,0)!=null)
					click(ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT.replace("$category",portlets[i]));
				waitForAndGetElement(ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_DOWN.replace("$category",portlets[i]));
			}
		}
		dragAndDropToObject(ELEMENT_EDIT_PORTLET_APPLICATION_ID.replace("$portlet",portlets[portlets.length-1]), targetLocator);
	}
}
