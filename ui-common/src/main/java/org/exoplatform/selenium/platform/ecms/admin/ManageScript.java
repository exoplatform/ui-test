package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author vuna2
 *
 */
public class ManageScript extends EcmsBase{

	public ManageScript(WebDriver dr,String...plfVersion) {
		super(dr);
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		// TODO Auto-generated constructor stub
	}

	Button button = new Button(driver);
	ManageAlert magAlert = new ManageAlert(driver);
	ECMainFunction ecMain = new ECMainFunction(driver);
	ManageDrive magDrv = new ManageDrive(driver);

	//Elements
	public final By ELEMENT_SCRIPT_CONTENT = By.name("scriptContent");
	public final By ELEMENT_SCRIPT_LABEL = By.name("scriptLabel");
	public final By ELEMENT_SCRIPT_NAME = By.name("scriptName");
	public final String ELEMENT_EDIT_SCRIPT_ICON = "//*[@id='tab-UIActionScriptContainer']//*[contains(text(), '${scriptLabel}')]/../..//*[contains(@class, 'uiIconEdit')]";
	public final String ELEMENT_DELETE_SCRIPT_ICON = "//*[@id='tab-UIActionScriptContainer']//*[contains(text(), '${scriptLabel}')]/../..//*[@class = 'uiIconDelete']";


	//Open form [Add Script]
	public void openAddScriptForm(){
		info("-- Opening [Add Script] Form --");
		click(ecMain.ELEMENT_ADD_SCRIPT_BUTTON);
		waitForAndGetElement(ELEMENT_SCRIPT_CONTENT);
	}

	//Add a script
	public void addScript(String scriptFileContent, String scriptLabel, String scriptName){
		if (isElementPresent(ELEMENT_SCRIPT_CONTENT)){
			info("-- Add Script Form has already opened --");
		}else {
			openAddScriptForm();
		}
		info("-- Adding a new Script --");
		String content = Utils.getFileContent(scriptFileContent);
		Utils.pause(500);
		type(ELEMENT_SCRIPT_CONTENT, content, true);		
		type(ELEMENT_SCRIPT_LABEL, scriptLabel, true);
		type(ELEMENT_SCRIPT_NAME, scriptName, true);

		button.save();
		assert checkScriptName(scriptName + ".groovy");
	}

	//Edit a Script
	public void editScript(String scriptName, String newScriptFileContent, String newScriptName, Object...params){
		Boolean enableVersion = (Boolean) (params.length > 0 ? params[0]: false);

		info("-- Editing Script --");
		click(ELEMENT_EDIT_SCRIPT_ICON.replace("${scriptLabel}", scriptName));
		if (!newScriptFileContent.isEmpty()){
			String content = Utils.getFileContent(newScriptFileContent);
			type(ELEMENT_SCRIPT_CONTENT, content, true);
		}
		if (enableVersion){
			click(ELEMENT_ENABLE_VERSION, 2);
		}
		if (!newScriptName.isEmpty()){
			type(ELEMENT_SCRIPT_LABEL, newScriptName, true);
		}
		button.save();
		if (!newScriptName.isEmpty()){
			assert checkScriptName(newScriptName);
		}else {
			assert !checkScriptName(scriptName);
		}
		Utils.pause(500);
	}

	//Delete a script
	public void deleteScript(String scriptLabel){
		info("-- Deleting Script...-- " + scriptLabel);
		int numPage =  1;
		if(waitForAndGetElement(ELEMENT_PAGE_TOTAL_NUMBER,5000,0) != null){
			numPage =  Integer.parseInt(waitForAndGetElement(ELEMENT_PAGE_TOTAL_NUMBER).getText());
			for(int i = 0; i < numPage; i++){
				if(waitForAndGetElement(ELEMENT_DELETE_SCRIPT_ICON.replace("${scriptLabel}", scriptLabel),5000,0) != null){
					break;
				}
				click(ELEMENT_NEXT_PAGE_ICON);
			}
		}
		Utils.pause(500);
		click(ELEMENT_DELETE_SCRIPT_ICON.replace("${scriptLabel}", scriptLabel));
		magAlert.acceptAlert();
		assert !checkScriptName(scriptLabel);
	}
	
	/**
	 * check script name in script list
	 * @param scriptLabel
	 * @return
	 */
	public boolean checkScriptName(String scriptLabel){
		info("Verify action name exists or not");
		int numPage =  1;
		if(waitForAndGetElement(ELEMENT_PAGE_TOTAL_NUMBER,5000,0) != null){
			numPage =  Integer.parseInt(waitForAndGetElement(ELEMENT_PAGE_TOTAL_NUMBER).getText());
			for(int i = 0; i < numPage; i++){
				if(waitForAndGetElement(ELEMENT_DELETE_SCRIPT_ICON.replace("${scriptLabel}", scriptLabel),5000,0) != null)
					return true;
				click(ELEMENT_NEXT_PAGE_ICON);
			}
		}
		if(waitForAndGetElement(ELEMENT_DELETE_SCRIPT_ICON.replace("${scriptLabel}", scriptLabel),5000,0) != null)
			return true;
		else
			return false;
	}
}
