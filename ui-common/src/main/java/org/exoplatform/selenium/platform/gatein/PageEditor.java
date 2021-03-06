package org.exoplatform.selenium.platform.gatein;
import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

public class PageEditor extends GateinLocator {
	
	
	public PageEditor(WebDriver dr){
		driver = dr;
	}
	
    /**
     * Edit a Portlet with locator of the portlet
     * @param locatorPortlet
     */
	public void goToEditPortlet(Object locatorPortlet){
		info("Go to edit portlet");
		Utils.pause(3000);
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
	/**
	 * Verify application permission
	 * @param cat
	 * @param app
	 * @param isEnable
	 */
	public void verifyAppPermission(String cat,String app,boolean isEnable){
		info("verify application permission");
		scrollBarToGetElement(By.xpath(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat)));
		click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat),0,true);
	    if(isEnable){
	    	waitForAndGetElement(ELEMENT_APPLICATION_NAME.replace("$app", app));
	    }else{
	    	waitForElementNotPresent(ELEMENT_APPLICATION_NAME.replace("$app", app));
	    }
	    
	}
	/**
	 * Verify category permission
	 * @param cat
	 * @param isEnable
	 */
	public void verifyCatPermission(String cat,boolean isEnable){
		info("verify category permission");
	    if(isEnable){
	    	scrollBarToGetElement(By.xpath(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat)));
	    }else{
	    	waitForElementNotPresent(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", cat));
	    }
	}
	/**
	 * Verify permission edit delete application
	 * @param app
	 * @param isEnable
	 */
	public void verifyEditDeleteAppPerm(String app,boolean isEnable){
		mouseOver(ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET.replace("${name}", app), true);
		if(isEnable){
			waitForAndGetElement(ELEMENT_EDIT_PORTLET.replace("${portlet}", app));
			waitForAndGetElement(ELEMENT_DELETE_PORTLET.replace("${portlet}", app));
		}else{
			waitForElementNotPresent(ELEMENT_EDIT_PORTLET.replace("${portlet}", app));
			waitForElementNotPresent(ELEMENT_DELETE_PORTLET.replace("${portlet}", app));
		}
	}
}
