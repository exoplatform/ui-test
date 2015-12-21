package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.WebDriver;

public class FaqPage  extends GateinLocator {
	NavigationToolbar navTool;
	PageCreationWizard pagMang;
	PageEditor pagEditor;
	HomePagePlatform hp;
	ManageAlert alert;
	ApplicationRegistry arPage;

	public FaqPage(WebDriver dr){
		driver = dr;
		navTool = new NavigationToolbar(dr);
		pagMang = new PageCreationWizard(dr);
		pagEditor = new PageEditor(dr);
		hp= new HomePagePlatform(dr);
		alert=new ManageAlert(dr);
		arPage=new ApplicationRegistry(dr);
	}
	/**
	 * Create FAQ Page
	 */
	public void createFaqPage(){
		info("Show all import application");
		arPage.displayImportApplicaions();
		arPage.importAllApplications();
		hp.goToHomePage();

		info("Create FAQ Page");
		navTool.goToAddPage();
		click(pagMang.ELEENT_NODE_NAME.replace("$name", "Home"));
		pagMang.inputPageInfoStep1("FAQ", null, null, "FAQ", null, null);
		click(pagMang.ELEMENT_PAGE_NEXT_BUTTON);
		click(pagMang.ELEMENT_PAGE_NEXT_BUTTON);
		pagEditor.selectApplication("answer-FAQPortlet", pagEditor.ELEMENT_EDIT_PAGE_PAGE);
		click(pagEditor.ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES);
		click(pagEditor.ELEMENT_PERMISSTION_SETTING_TAB);
		check(pagEditor.ELEMENT_PUBLIC_MODE,2);
		click(pagEditor.ELEMENT_PAGE_EDITOR_SAVE_BUTTON);
		pagEditor.finishEditLayout();
	}

	/**
	 * function go to edit FAQ portlet in FAQ page
	 */
	public void goToEditFaqPortlet(){
		hp.goToFaq();
		info("Go to edit FAQ portlet");
		navTool.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
	}
	
	/**
	 * setting template
	 * @param data
	 */
	public void settingFAQTemplate(String data){
		click(ELEMENT_FAQ_EDIT_TEMPLATE_TAB);
		type(ELEMENT_FAQ_EDIT_TEMP_INPUT, data, false);
		click(pagEditor.ELEMENT_EDIT_SAVE_BUTTON);
		click(pagEditor.ELEMENT_PAGE_OK_BUTTON);
	}
}
