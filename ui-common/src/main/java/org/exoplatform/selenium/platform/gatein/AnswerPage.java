package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.WebDriver;

public class AnswerPage extends GateinLocator {
	NavigationToolbar navTool;
	PageCreationWizard pagMang;
	PageEditor pagEditor;
	HomePagePlatform hp;
	ManageAlert alert;
	ApplicationRegistry arPage;

	public AnswerPage(WebDriver dr){
		driver = dr;
		navTool = new NavigationToolbar(dr);
		pagMang = new PageCreationWizard(dr);
		pagEditor = new PageEditor(dr);
		hp= new HomePagePlatform(dr);
		alert=new ManageAlert(dr);
		arPage=new ApplicationRegistry(dr);
	}
	/**
	 * Create Answer Page
	 */
	public void createAnswerPage(){
		info("Show all import application");
		arPage.displayImportApplicaions();
		arPage.importAllApplications();
		hp.goToHomePage();

		info("Create Answer Page");
		navTool.goToAddPage();
		click(pagMang.ELEMENT_PAGE_UP_LEVEL);
		pagMang.inputPageInfoStep1("Answers", null, null,"Answers", null, null);
		click(pagMang.ELEMENT_PAGE_NEXT_BUTTON);
		click(pagMang.ELEMENT_PAGE_NEXT_BUTTON);
		pagEditor.selectApplication("answer-AnswersPortlet", pagEditor.ELEMENT_EDIT_PAGE_PAGE);
		click(pagEditor.ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES);
		click(pagEditor.ELEMENT_PERMISSTION_SETTING_TAB);
		check(pagEditor.ELEMENT_PUBLIC_MODE,2);
		click(pagEditor.ELEMENT_PAGE_EDITOR_SAVE_BUTTON);
		pagEditor.finishEditLayout();
		Utils.pause(2000);
	}


	/**function go to edit answer portlet in answer page
	 */
	public void goToEditAnswerPortlet(){
		hp.goToAnswer();
		info("Go to edit answer portlet");
		navTool.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		Utils.pause(2000);
	}

	/**
	 * 
	 * @param isCheckMode
	 */
	public void doPublicMode(Boolean isCheckMode){
		info("Check public mode or not");
		click(ELEMENT_ANSWER_PERMISSION_TAB);
		if(isCheckMode!=null){
			if(isCheckMode)
				check(ELEMENT_ANSWER_PERMISSION_TAB_PUBLIC_MODE,2);
			else
				uncheck(ELEMENT_ANSWER_PERMISSION_TAB_PUBLIC_MODE,2);
		}
		Utils.pause(2000);
	}

	/**
	 * function setting display for categories in answer portlet
	 * @param categoryScope
	 * @param display
	 */
	public void setDisplayCategoryScoping(String categoryScope, Boolean display){
		info("setting display for categories in answer portlet");
		String[] cat = categoryScope.split("/");
		clickByJavascript(ELEMENT_CATEGORY_SCOPING_TAB);
		for (int i = 0; i < cat.length; i++){
			if(display!=null){
				if (display){
					check(ELEMENT_CATEGORY_IN_SCOPE_TAB.replace("${catName}", cat[i]), 2);
				}else {
					uncheck(ELEMENT_CATEGORY_IN_SCOPE_TAB.replace("${catName}", cat[i]), 2);
				}
			}
		}
		click(pagEditor.ELEMENT_EDIT_SAVE_BUTTON);
		click(pagEditor.ELEMENT_PAGE_OK_BUTTON);
	}

	/**
	 * select forum in caltegory
	 * @param forumPath (Ex: cat1/forum1)
	 */
	public void selectForumCategory(String forumPath){
		info("select forum in caltegory");
		String []item=forumPath.split("/");
		if(item.length>1){
			for(int i = 0; i<item.length-1; i++){
				if(waitForAndGetElement(ELEMENT_CATEGORY_COLLAPSE_ITEM.replace("$name", item[i]),5000,0)!=null){
					click(ELEMENT_CATEGORY_COLLAPSE_ITEM.replace("$name", item[i]));
					waitForAndGetElement(ELEMENT_CATEGORY_EXPAND_ITEM.replace("$name", item[i]));
				}
			}
			click(ELEMENT_CATEGORY_NODE_ITEM.replace("$name", item[item.length-1]));
		}
	}

	/**
	 * Function setting discussion in answer portlet
	 * @param discuss
	 * @param forumPath
	 */
	public void settingDiscussion(Boolean discuss, String forumPath){
		info("setting discussion in answer portlet");
		click(ELEMENT_DISCUSSION_TAB);
		if(discuss!=null){
			if (discuss){
				check(ELEMENT_ENABLE_DISCUSSION_CHECKBOX, 2);
				click(ELEMENT_ADD_FORUM);
				selectForumCategory(forumPath);
			}else {
				uncheck(ELEMENT_ENABLE_DISCUSSION_CHECKBOX, 2);
			}
		}
		click(pagEditor.ELEMENT_EDIT_SAVE_BUTTON);
		click(pagEditor.ELEMENT_PAGE_OK_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * function setting email template for answser portlet
	 * @param tab
	 * @param content
	 */
	public void settingEmailTemplate(int tab, String content){
		info("setting email template for answser portlet");
		click(ELEMENT_MAIL_NOTIFICATION_TEMPLATE_TAB);
		switch (tab) {
		case 1:
			click(ELEMENT_MAIL_NEW_QUESTION_TAB);
			inputDataToCKEditor(ELEMENT_MAIL_NEW_QUESTION_FRAME, content);
			break;
		case 2:
			click(ELEMENT_MAIL_EDIT_ANSWER_TAB);
			inputDataToCKEditor(ELEMENT_MAIL_EDIT_ANSWER_FRAME, content);
			break;
		case 3:
			click(ELEMENT_MAIL_MOVE_QUESTION_TAB);
			inputDataToCKEditor(ELEMENT_MAIL_MOVE_QUESTION_FRAME, content);
			break;
		case 4:
			click(ELEMENT_MAIL_NEW_QUESTION_TAB);
			click(ELEMENT_NEW_QUESTION_RELOAD_DEFAULT_EMAIL);
			break;
		case 5:
			click(ELEMENT_MAIL_EDIT_ANSWER_TAB);
			click(ELEMENT_EDIT_ANSWER_RELOAD_DEFAULT_EMAIL);
			break;
		case 6:
			click(ELEMENT_MAIL_MOVE_QUESTION_TAB);
			click(ELEMENT_MOVE_QUESTION_RELOAD_DEFAULT_EMAIL);
			break;
		}
		switchToParentWindow();
		click(pagEditor.ELEMENT_EDIT_SAVE_BUTTON);
		click(pagEditor.ELEMENT_PAGE_OK_BUTTON);
	}

	/**
	 * function setting display mode in answer portlet
	 * @param all
	 * @param date
	 * @param ascending
	 * @param opts
	 */
	public void settingDisplayMode(Boolean all, Boolean date, Boolean ascending, Boolean isVote, Boolean isSubmit, Boolean rss, Boolean avatar, Boolean allowUser){
		info("setting display mode in answer portlet");
		click(ELEMENT_DISPLAY_MODE_TAB);
		if(all!=null){
			if (all){
				select(ELEMENT_SELECT_DISPLAY_MODE, "All");
			}else {
				select(ELEMENT_SELECT_DISPLAY_MODE, "Approved");
			}
		}
		if(date!=null){
			if (date){
				select(ELEMENT_SELECT_ORDER_BY_MODE, "Created Date");
			}else {
				select(ELEMENT_SELECT_ORDER_BY_MODE, "Alphabet/Index");
			}
		}
		if(ascending!=null){
			if (ascending){
				select(ELEMENT_SELECT_DIRECTION_MODE, "Ascending order");
			}else {
				select(ELEMENT_SELECT_DIRECTION_MODE, "Descending order");
			}
		}
		if(isVote!=null){
			if (isVote){
				check(ELEMENT_ENABLE_VOTE_COMMENT, 2);
			}else {
				uncheck(ELEMENT_ENABLE_VOTE_COMMENT, 2);
			}
		}
		if(rss!=null){
			if (rss){
				check(ELEMENT_ENABLE_RSS, 2);
			}else {
				uncheck(ELEMENT_ENABLE_RSS, 2);
			}
		}
		if(avatar!=null){
			if (avatar){
				check(ELEMENT_VIEW_AVATAR, 2);
			}else {
				uncheck(ELEMENT_VIEW_AVATAR, 2);
			}
		}
		if(allowUser!=null){
			if (avatar){
				check(ELEMENT_POST_QUESTION_IN_ROOT, 2);
			}else {
				uncheck(ELEMENT_POST_QUESTION_IN_ROOT, 2);
			}
		}
		click(pagEditor.ELEMENT_EDIT_SAVE_BUTTON);
		click(pagEditor.ELEMENT_PAGE_OK_BUTTON);
	}
}
