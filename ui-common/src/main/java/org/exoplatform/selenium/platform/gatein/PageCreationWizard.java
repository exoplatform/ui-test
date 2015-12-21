package org.exoplatform.selenium.platform.gatein;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
/**
 * Path: Edit-->Page-->Add Pages
 */
public class PageCreationWizard extends GateinLocator {

	ContentList contList;
	ContentDetail contDetail;
	ManageAlert magAlert;
	Button but;
    PortalManagePages portMgPg;
	public PageCreationWizard(WebDriver dr){
		this.driver = dr;
		contList = new ContentList(dr);
		contDetail = new ContentDetail(dr);
		magAlert = new ManageAlert(dr);
		but = new Button(dr);
		portMgPg = new PortalManagePages(driver);
	}

	/**
	 * Input data in page info page at step 1
	 * @param name
	 * @param isMode
	 * @param lang
	 * @param disName
	 * @param isVis
	 * @param isPub
	 */
	public void inputPageInfoStep1(String name, Boolean isMode, String lang, String disName, Boolean isVis, Boolean isPub){
		info("Input data in page info page at step 1");
		if(name!=null && name!=""){
			info("Input name");
			type(ELEMENT_PAGE_NAME_INPUT,name,true);
		}
		if(isMode!=null){
			info("Input mode");
			if(isMode)
				check(ELEMENT_PAGE_MODE_CHECKBOX,2);
			else
				uncheck(ELEMENT_PAGE_MODE_CHECKBOX,2);
		}
		if(lang!=null && lang!=""){
			info("Input language");
			select(ELEMENT_PAGE_LANGUAGE_SELECT_BOX,lang);
		}
		if(disName!=null && disName!=""){
			info("Input display name");
			type(ELEMENT_PAGE_DISPLAY_NAME_INPUT,name,true);
		}
		if(isVis!=null){
			info("Input Visible");
			if(isVis)
				check(ELEMENT_PAGE_VISIBLE_CHECKBOX,2);
			else
				uncheck(ELEMENT_PAGE_VISIBLE_CHECKBOX,2);
		}
		if(isPub!=null){
			info("Input publication date");
			if(isPub)
				check(ELEMENT_PAGE_PUBLICATION_DATE_CHECKBOX,2);
			else
				uncheck(ELEMENT_PAGE_PUBLICATION_DATE_CHECKBOX,2);
		}
	}

	/**
	 * Add content in page editor
	 * @param tab
	 * @param element
	 */
	public void addApplication(Object tab, Object element) {
		click(ELEMENT_APPLICATION_TAB_ACTIVE);
		click(tab);
		Utils.pause(1000);
		dragAndDropToObject(element,ELEMENT_PAGEEDITOR_VIEWPAGE);
	}
	/**
	 * Add an application to a layout
	 * @param nameApp
	 * @param appLocator
	 * @param layoutLocator
	 */
	public void addApp(String tabName,String nameApp,Object appLocator,Object layoutLocator){
		info("Add an application to the layout");
		click(ELEMENT_APPLICATION_TAB);
		Utils.pause(1000);
		if(!tabName.isEmpty())
			click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", tabName));
		dragAndDropToObject(appLocator,layoutLocator);
		info("Verify that the application is shown in the layout");
		waitForAndGetElement(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",nameApp),3000,0);
		info("The application is shown in the layout page");
		Utils.pause(2000);
		saveChangesPageEditor();
	}


	/**
	 * Add a Content list to a Page by folder
	 * @param titlePage
	 * @param Description
	 * @param language
	 * @param path
	 * @param folder
	 */
	public void addContentlistByFolder(String path,String folder){
		addApplication(ELEMENT_APPLICATION_CONTENT_TAB,ELEMENT_APPLICATION_CONTENT_LIST);
		mouseOver(ELEMENT_PAGEEDITOR_VIEWPAGE,true);
		click(contList.ELEMENT_CONTENT_LIST_EDIT_BTN);
		contList.selectFolderContent(path,folder);
		click(contList.ELEMENT_CONTENT_LIST_SAVE_BTN);
		click(contList.ELEMENT_CONTENT_LIST_CLOSE_BTN);
		click(ELEMENT_PAGE_FINISH_BTN);
		Utils.pause(2000);
	}
	/**
	 * Add a Content list to a page by content
	 * @param path
	 * @param content
	 */
	public void addContentListByContent(String path,String content){
		addApplication(ELEMENT_APPLICATION_CONTENT_TAB,ELEMENT_APPLICATION_CONTENT_LIST);
		mouseOver(ELEMENT_PAGEEDITOR_VIEWPAGE,true);
		click(contList.ELEMENT_CONTENT_LIST_EDIT_BTN);
		check(contList.ELEMENT_CONTENT_LIST_BY_CONTENT_MODE, 2);
		contList.selectFolderContent(path,content);
		click(contList.ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN);
		click(contList.ELEMENT_CONTENT_LIST_SAVE_BTN);
		click(contList.ELEMENT_CONTENT_LIST_CLOSE_BTN);
		click(ELEMENT_PAGE_FINISH_BTN);
	}
	/**
	 * Add a Cotent Detail to a page
	 * @param path
	 * @param content
	 */
	public void addContentDetail(String path, String content){
		addApplication(ELEMENT_APPLICATION_CONTENT_TAB,ELEMENT_APPLICATION_CONTENT_DETAIL);
		mouseOver(ELEMENT_PAGEEDITOR_VIEWPAGE,true);
		click(contDetail.ELEMENT_CONTENT_DETAIL_EDIT_BTN);
		contDetail.selectFolderContent(path,content);
		click(contDetail.ELEMENT_CONTENT_DETAIL_SAVE_BTN);
		click(contDetail.ELEMENT_CONTENT_DETAIL_CLOSE_BTN);
		click(ELEMENT_PAGE_FINISH_BTN);
	}
	/**
	 * Create a simple page
	 * @param title
	 * @param description
	 */
	public void addAPageSimple(String title,String description){
		info("Input the title and descript");
		inputPageInfoStep1(title, true, "English",description, true,false);
		info("click on Next button of step 1");
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		info("click on Next button of step 2");
		click(ELEMENT_ADDNEWPAGE_BTNNEXT);
		saveChangesPageEditor();
	}
	/**
	 * Add a Container
	 * @param numRow  this name of containers as: oneRow,twoRow...
	 * @param verify
	 */
	public void addContainer(String numRow, boolean... verify){
		click(ELEMENT_CONTAINER_TAB);
		boolean isVerify = (verify.length > 0 ? verify[0]: true);
		info("Add container");
		info("Add new container: " + numRow);
		try{
			click(ELEMENT_CONTAINER_TAB);
		}catch(org.openqa.selenium.UnhandledAlertException e){
			magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock",40000);
			clearCache();
		}
		click(By.linkText("Rows Layout"));
		dragAndDropToObject(By.id(numRow), By.className("UIRowContainer"));
		Utils.pause(2000);
		if (isVerify){
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
			waitForAndGetElement(ELEMENT_CONTAINER_TITLE.replace("${title}","Container"));
		}
		saveChangesPageEditor();
		info("the container is added");
	}

	/**
	 * Edit a container
	 * @param newTitle
	 * @param width
	 * @param height
	 */
	public void editContainer(String oldTitle,String newTitle,String width, String height){
		info("Edit container");
		click(ELEMENT_SWITCH_VIEW_MODE);
		click(ELEMENT_CONTAINER_TAB);
		if(!oldTitle.isEmpty())
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}",oldTitle),true);
		else
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
		Utils.pause(3000);
		if(!oldTitle.isEmpty())
			click(ELEMENT_EDIT_CONTAINER_ICON_BY_NAME.replace("${name}", oldTitle));
		else
			click(ELEMENT_EDIT_CONTAINER_ICON);
		if(!newTitle.isEmpty())
			type(ELEMENT_CONTAINER_POPUP_TITLE, newTitle, true);
		if(!width.isEmpty())
			type(ELEMENT_CONTAINER_POPUP_WIDTH, width, true);
		if(!height.isEmpty())
			type(ELEMENT_CONTAINER_POPUP_HEIGHT, height, true);
		//but.save();
		waitForAndGetElement(ELEMENT_SAVE_BTN_2);
		click(ELEMENT_SAVE_BTN_2);
		mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
		waitForAndGetElement(ELEMENT_CONTAINER_TITLE.replace("${title}",newTitle));
		saveChangesPageEditor();
		info("the container is edited");
	}

	/**
	 * Move a container to new place in layout
	 * 
	 * @param title
	 *            is the name of container that will be dragged and dropped
	 * @param sourceLocator
	 *            is the locator of the container that will be moved up or down
	 *            targetLocator
	 * @param targetLocator
	 *            is the locator of the portlet or the container that will be
	 *            replaced position by sourceLocator
	 * @param heightTarget
	 *            is height size of the portlet or the container that will be
	 *            replaced position by sourceLocator
	 */
	public void moveContainer(String title,Object sourceLocator,Object targetLocator,int heightTarget){
		info("Move container to new place");
		try{
			click(ELEMENT_CONTAINER_TAB);
		}catch(org.openqa.selenium.UnhandledAlertException e){
			magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock",40000);
			clearCache();
		}
		if (!title.isEmpty()) {
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}",
					title), true);
			Utils.pause(3000);

		} else {
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
			Utils.pause(3000);
		}

		WebElement elSource = waitForAndGetElement(sourceLocator,2000,0);
		WebElement elTarget = waitForAndGetElement(targetLocator,2000,0);

		info("Get the size of target");
		Dimension size = elTarget.getSize();

		Actions builder = new Actions(this.driver);
		info("Hold the source");
		builder.clickAndHold(elSource).build().perform();
		info("Move the mouse to the middle of the portlet");
		Action actionMove = builder.moveToElement(elTarget).build();
		actionMove.perform();
		info("Move the mouse under the portlet");
		Action actionMove1 = builder.moveByOffset(-(size.width/2),-(size.height/2)+heightTarget).build();
		actionMove1.perform();
		info("Drop the source");
		Utils.pause(3000);
		Action actiondrop = builder.release().build();
		actiondrop.perform();
		Utils.pause(2000);

		saveChangesPageEditor();
		info("the container is moved succefully");
	}

	/**
	 * Check the positions of containers or portlets before and after changed their position in the layout
	 * @param positionFirst is the position before changed
	 * @param positionEnd   is the position after changed
	 */
	public void checkPositions(Object positionFirst, Object positionEnd){
		info("Verify that positions of element is changed");
		waitForElementNotPresent(positionFirst,2000,1);
		waitForAndGetElement(positionEnd,2000,1);
		saveChangesPageEditor();
	}

	/**
	 * Delete a contain in the layout
	 * @param name
	 */
	public void deleteContainer(String name){
		info("Delete the container");
		try{
			click(ELEMENT_CONTAINER_TAB);
		}catch(org.openqa.selenium.UnhandledAlertException e){
			magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock",40000);
			clearCache();
		}
		if(!name.isEmpty()){
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}",
					name), true);
			Utils.pause(3000);
			click(ELEMENT_DELETE_CONTAINER_ICON_BY_NAME.replace("${name}",name));
			magAlert.acceptAlert();
			Utils.pause(2000);
			waitForElementNotPresent(ELEMENT_DELETE_CONTAINER_ICON_BY_NAME.replace("${name}",name));
		}
		saveChangesPageEditor();
		info("the container is deleted");
	}

	/**
	 * Edit an application with changes about title, width and height
	 * @param oldTitle
	 * @param newTitle
	 * @param width
	 * @param height
	 */
	public void editApplication(String oldTitle,String newTitle,String width,String height) {
		// TODO Auto-generated method stub
		Utils.pause(3000);
		info("Edit application");
		mouseOver(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",oldTitle),true);
		click(ELEMENT_APPLICATION_EDIT_ICON.replace("${name}",oldTitle));
		click(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TAB);
		if(!newTitle.isEmpty())
			type(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TITLE,newTitle,true);
		if(!width.isEmpty())
			type(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_WIDTH,width,true);
		if(!height.isEmpty())
			type(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_HEIGHT,height,true);
		Utils.pause(2000);
	}

	/**
	 * Save and close application popup after finishing editing
	 * Save and close page editor
	 */
	public void saveChangesApplication(){
		info("Save all changes of an application");
		click(ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SAVE);
		Utils.pause(2000);
	}

	/**
	 * Save all changes of page editor
	 */
	public void saveChangesPageEditor(){
		info("Save change Page Editor");
		waitForAndGetElement(ELEMENT_PAGEEDITOR_FINISHBTN);
		click(ELEMENT_PAGEEDITOR_FINISHBTN);
		Utils.pause(3000);
	}

	/**
	 * Move an application to new place
	 * @param titleSource is the title of applicattion source that will be moved to new place
	 * @param titleTarget is the title of application target that application source will be followed
	 * @param heightTarget is the height of application target
	 */
	public void moveApplication(String titleSource,String titleTarget,int heightTarget){
		info("Move an application to new place");
		click(ELEMENT_APPLICATION_TAB);
		if (!titleSource.isEmpty()) {
			info("titleSource:"+titleSource);
			info("titleTarget:"+titleTarget);
			mouseOver(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",titleSource),true);
			Utils.pause(3000);
		} 
		WebElement elSource = waitForAndGetElement(ELEMENT_APPLICATION_HOLDER_MOVE.replace("${name}",titleSource),2000,1);
		WebElement elTarget = waitForAndGetElement(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",titleTarget),2000,0);
		if(elTarget==null)
			elTarget=waitForAndGetElement(ELEMENT_DROP_SOURCE_HAS_LAYOUT,2000,1);

		info("Get the size of target");
		Dimension size = elTarget.getSize();

		Actions builder = new Actions(this.driver);
		info("Hold the source:");
		builder.clickAndHold(elSource).build().perform();
		info("Move the mouse to the middle of the portlet");
		Action actionMove = builder.moveToElement(elTarget).build();
		actionMove.perform();
		info("Move the mouse under the portlet");
		Action actionMove1 = builder.moveByOffset(-(size.width/2),-(size.height/2)+heightTarget).build();
		actionMove1.perform();
		info("Drop the source");
		Utils.pause(3000);
		Action actiondrop = builder.release().build();
		actiondrop.perform();
		Utils.pause(2000);

		saveChangesPageEditor();
		info("the application is moved succefully");
	}

	/**
	 * Delete an application
	 * @param name
	 */
	public void deleteApplication(String name){
		info("Delete the application");
		click(ELEMENT_APPLICATION_TAB);
		if(!name.isEmpty()){
			mouseOver(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),true);
			Utils.pause(3000);
			click(ELEMENT_APPLICATION_DELETE_ICON.replace("${name}",name));
			magAlert.acceptAlert();
			Utils.pause(2000);
			waitForElementNotPresent(ELEMENT_APPLICATION_DELETE_ICON.replace("${name}",name));
		}
		saveChangesPageEditor();
		info("the container is deleted");
	}

	/**
	 * Change to Switch view mode
	 * @param verify
	 */
	public void switchViewMode(boolean... verify){
		info("Click on Switch view mode button");
		click(ELEMENT_SWITCH_VIEW_MODE);
		if(verify.length>0)
			waitForAndGetElement(ELEMENT_SWITCH_VIEW_MODE_NAME_APPLICATION_CLASS,2000,0);
	}
	/**
	 * View properties
	 * @param verify
	 */
	public void viewProperties(boolean... verify){
		info("Click on Switch view mode button");
		click(ELEMENT_VIEW_PROPERTIES);
		if(verify.length>0)
			waitForAndGetElement(ELEMENT_VIEW_PROPERTIES_POPUP,2000,0);
	}
	/**
	 * Get old title of a page 
	 * @return title
	 */
	public String getOldTitle(){
		WebElement el= this.driver.findElement(ELEMENT_VIEW_PROPERTIES_TITLE);
		String tilte =  el.getAttribute("value");
		info("tilte:"+tilte);
		return tilte;
	}
	/**
	 * Change Properties of a page
	 * @param title
	 * @param groupsPath
	 * @param memberShips
	 * @param isShowMaxWindow
	 */
	public void changeProperties(String title,String groupsPath,String memberShips,boolean isAccessPermision,boolean isEditPermission,boolean... isShowMaxWindow){
		if(!title.isEmpty()){
			info("Input new title");
			type(ELEMENT_VIEW_PROPERTIES_TITLE,title,true);
		}
		if(!groupsPath.isEmpty()){
			info("Select a group");
			click(ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB);
			if(isAccessPermision==true){
				click(ELEMENT_VIEW_PROPERTIES_ADD_PERMISSION_BTN);
				info("Select a group");
				selectGroup(groupsPath);
				info("Select a meberships");
				selectMemberShip(memberShips);
			}
			if(isEditPermission==true){
				info("Select Edit permission settings tab");
				click(ELEMENT_VIEW_PROPERTIES_EDIT_PERMISSITION_SETTINGS);
				info("Click on Select permission button");
				click(ELEMENT_VIEW_PROPERTIES_SELECT_PERMISSION_BTN);
				selectGroupEditTab(groupsPath);
				info("Select a meberships");
				selectMemberShipEditTab(memberShips);
			}
		}
		if(isShowMaxWindow.length>0){
			info("Check on show Max window checkbox");
			check(ELEMENT_VIEW_PROPERTIES_SHOW_MAX_WINDOW,2);
		}
		saveChangeProperties();
	}
	/**
	 * Save changes all when View Properties
	 */
	public void saveChangeProperties(){
		info("Save all changes");
		click(ELEMENT_VIEW_PROPERTIES_SAVE_BTN);
		Utils.pause(2000);
	}

	/**
	 * Select a group in permission selector popup
	 * @param groupsPath is path of groups as:Platform/Content Manangement
	 */
	public void selectGroup(String groupsPath){
		info("Select a group with the path:"+groupsPath);
		String[] groups = groupsPath.split("/");
		for(String groupSelect: groups){
			info("Select group:"+groupSelect);
			click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_GROUP.replace("${group}", groupSelect),0,true);
		}
		Utils.pause(2000);
	}
	/**
	 * Select a group in permission selector popup
	 * @param groupsPath is path of groups as:Platform/Content Manangement
	 */
	public void selectGroupEditTab(String groupsPath){
		info("Select a group with the path:"+groupsPath);
		String[] groups = groupsPath.split("/");
		for(String groupSelect: groups){
			info("Select group:"+groupSelect);
			click(ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_GROUP.replace("${group}", groupSelect),0,true);
		}
		Utils.pause(2000);
	}
	/**
	 * Select a membership of a group
	 * @param memberShip
	 */
	public void selectMemberShip(String memberShip){
		info("Select a membership:"+memberShip);
		click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}",memberShip),0,true);
		Utils.pause(2000);
	}
	/**
	 * Select a membership of a group
	 * @param memberShip
	 */
	public void selectMemberShipEditTab(String memberShip){
		info("Select a membership:"+memberShip);
		click(ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}",memberShip),0,true);
		Utils.pause(2000);
	}
	/**
	 * Reset default values of Page's properties after changed
	 * @param title
	 * @param groupPath
	 * @param memberShip
	 * @param isShowMaxWindow
	 */
	public void resetValuesProperties(String title, String groupPath,String editPermission,boolean... isShowMaxWindow){
		if(!title.isEmpty()){
			info("Reset old name");
			type(ELEMENT_VIEW_PROPERTIES_TITLE,title,true);
		}
		if(!groupPath.isEmpty()){
			info("remove a group");
			removeGroup(groupPath.toLowerCase());
		}
		if(!editPermission.isEmpty()){
			info("Remove a meberships");
			deleteEditPermission();
		}
		if(isShowMaxWindow.length>0){
			info("UnCheck on show Max window checkbox");
			uncheck(ELEMENT_VIEW_PROPERTIES_SHOW_MAX_WINDOW,2);
		}
		saveChangeProperties();
	}
	/**
	 * Remove a group permission
	 * @param group
	 */
	public void removeGroup(String group){
		info("Click on Delete button of the group:"+ group);
		click(ELEMENT_VIEW_PROPERTIES_GROUP_REMOVE_BTN.replace("${group}", group));
		alert.acceptAlert();
		info("The group is removed");
		waitForElementNotPresent(ELEMENT_VIEW_PROPERTIES_GROUP_REMOVE_BTN.replace("${group}", group));
	}
	/**
	 * Delete edit permission of a page
	 */
	public void deleteEditPermission(){
		info("Click on Delete Permission");
		click(ELEMENT_VIEW_PROPERTIES_DELETE_EDIT_PERMISSION_BTN);
		Utils.pause(2000);
	}

	/**function: Edit view properties when edit layout
	 * @param pageName name of page you want to edit
	 * @param newtitle new Name of page you want to edit
	 * @param groupId Group Id when select permission
	 * @param membership membership when select permission
	 */
	public void editViewProperties(String newtitle, String groupId, String membership){
		waitForAndGetElement(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		info("Edit properties of page");
		if(newtitle.length()>0)
			type(ELEMENT_VIEWPAGE_PAGETITLE, newtitle, true);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		setEditPermissions(groupId, membership);
	}
    /**
     * Edit permission when view properties
     * @param groupId
     * @param membership
     * @param isAccess
     * @param isEdit
     * @param isMoveApp
     * @param isMoveCon
     */
	public void editPermInViewProperties(String groupId, String membership,boolean isAccess,boolean isEdit,boolean isMoveApp,boolean isMoveCon){
		info("edit permission in view properties");
		waitForAndGetElement(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES,0,true);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		if(isAccess){
			 check(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX,2);
		}else{ 
			 check(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX,2);
			 uncheck(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX,2);
			 setAccessPermissions(groupId, membership);
		}
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		if(isEdit){
			
		}else{
			setEditPermissions(groupId, membership);
		}
		click(ELEMENT_MOVE_APPS_PERMISSION_SETTING,0,true);
		if(isMoveApp){
			
		}else{
			setMoveAppsPermissions(groupId, membership);
		}
		click(ELEMENT_MOVE_CONTAINERS_PERMISSION_SETTING,0,true);
		if(isMoveCon){
			
		}else{
			setMoveContainersPermissions(groupId, membership);
		}
		click(ELEMENT_SAVE_BTN);
		saveChangesPageEditor();
	}
	/**
	 * edit permission in container permission
	 * @param cont
	 * @param groupId
	 * @param membership
	 * @param isAccess
	 * @param isMoveApp
	 * @param isMoveCon
	 */
	public void editPermInContainer(String cont,String groupId, String membership,boolean isAccess,boolean isMoveApp,boolean isMoveCon){
		info("set permission for container");
		
		click(ELEMENT_CONTAINER_TAB,0,true);
		if(!cont.isEmpty())
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}",cont),true);
		else
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
		click(ELEMENT_EDIT_CONTAINER_ICON,0,true);
		Utils.pause(3000);
		click(ELEMENT_CONTAINER_PERMISSION_SETTING_TAB,0,true);
		if(isAccess){
			 
		}else{
			 uncheck(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX,2);
			 setAccessPermissions(groupId,membership);
		}
		click(ELEMENT_SAVE_BTN_2,0,true);
		saveChangesPageEditor();
	}
	/**function: Edit permission when view properties
	 * @param groupId Group Id when select permission
	 * @param membership membership when select permission
	 */
	public void setupEditPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
		String selectedMembership = ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

		info("--Setting edit permission to " + groupId + ", " + membership + "--");
		String[] groups = groupId.split("/");
		click(ELEMENT_PERMISSION_SELECTOR_BUTTON);
		Utils.pause(500);
		waitForTextPresent("Permission Selector");
		for (String group : groups) {
			String groupToSelect = ELEMENT_SELECT_EDIT_GROUP_ITEM.replace("${group}", group);
			click(groupToSelect);
		}
		click(membershipToSelect);
		waitForAndGetElement(selectedMembership, 3000, 1, 2);
		click(ELEMENT_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_EDIT_PERMISSION_SETTING);		
	}
	
	
	/**function: Edit permission when view properties
	 * @param groupId Group Id when select permission
	 * @param membership membership when select permission
	 */
	public void setEditPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
		String selectedMembership = ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

		info("--Setting edit permission to " + groupId + ", " + membership + "--");
		String[] groups = groupId.split("/");
		click(ELEMENT_SELECT_PERMISSION_BUTTON);
		Utils.pause(500);
		waitForTextPresent("Permission Selector");
		for (String group : groups) {
			String groupToSelect = ELEMENT_SELECT_EDIT_GROUP_ITEM.replace("${group}", group);
			click(groupToSelect);
		}
		click(membershipToSelect);
		waitForTextNotPresent("Permission Selector");
		waitForAndGetElement(selectedMembership, DEFAULT_TIMEOUT, 1, 2);
		click(ELEMENT_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_EDIT_PERMISSION_SETTING);
		saveChangesPageEditor();
	}
	/**
	 * Set access permission 
	 * @param groupId
	 * @param membership
	 */
	public void setAccessPermissions(String groupId, String membership){
		String[] groups = groupId.split("/");
		click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN,0,true);
		for(String groupSelect: groups){
			info("Select group:"+groupSelect);
			click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}", groupSelect),0,true);
		}
		click(ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP.replace("${member}",membership),0,true);
	}
	/**
	 * Set move apps permissions
	 */
	public void setMoveAppsPermissions(String groupId, String membership) {
		String[] groups = groupId.split("/");
		uncheck(ELEMENT_PAGEEDITOR_MOVE_APPS_PUBLIC_CHECKBOX,2);
		click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN,0,true);
		for(String groupSelect: groups){
			info("Select group:"+groupSelect);
			click(ELEMENT_EDIT_PERMISSION_MOVE_APPS_SELECT.replace("${group}", groupSelect),0,true);
		}
		click(ELEMENT_EDIT_PERMISSION_MOVE_APPS_SELECT.replace("${group}",membership),0,true);
	}
	/**
	 * Set move apps permissions
	 */
	public void setMoveContainersPermissions(String groupId, String membership) {
		String[] groups = groupId.split("/");
		uncheck(ELEMENT_PAGEEDITOR_MOVE_CONTAINERS_PUBLIC_CHECKBOX,2);
		click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN,0,true);
		for(String groupSelect: groups){
			info("Select group:"+groupSelect);
			click(ELEMENT_EDIT_PERMISSION_MOVE_CONTAINERS_SELECT.replace("${group}", groupSelect),0,true);
		}
		click(ELEMENT_EDIT_PERMISSION_MOVE_CONTAINERS_SELECT.replace("${group}",membership),0,true);
	}
	/**
	 * Select access permission
	 */
	public void selectAccessPerm(String group,String member){
		info("select group and membership");
		click(ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN,0,true);
		if(group.length()>0 && member.length()>0){
			selectGroup(group);
			selectMemberShip(member);
		}else{
			check(ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX,2);
		}
		
	}
	/**
	 * Verify permission drag drop application
	 * @param tabName
	 * @param nameApp
	 * @param appLocator
	 * @param layoutLocator
	 * @param isEnable
	 */
	public void verifyDragDropAppPerm(String tabName,String nameApp,Object appLocator,Object layoutLocator,boolean isEnable){
		info("verify drag drop application");
		click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", tabName),0,true);
		dragAndDropToObject(appLocator,layoutLocator);
		if(isEnable){
			waitForAndGetElement(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",nameApp),3000,0);
		}else{
			waitForElementNotPresent(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",nameApp),3000,0);
		}
	}
	/**
	 * Verify permission drag drop container
	 */
	public void verifyDragDropConPerm(String numRow,boolean isVerify){
		info("verify drag drop container");
		click(ELEMENT_CONTAINER_TAB);
		info("Add new container: " + numRow);
		try{
			click(ELEMENT_CONTAINER_TAB);
		}catch(org.openqa.selenium.UnhandledAlertException e){
			magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock",40000);
			clearCache();
		}
		click(By.linkText("Rows Layout"));
		dragAndDropToObject(By.id(numRow), By.className("UIRowContainer"));
		Utils.pause(2000);
		mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
		if (isVerify){
			waitForAndGetElement(ELEMENT_CONTAINER_TITLE.replace("${title}","Container"));
		}else{
			waitForElementNotPresent(ELEMENT_CONTAINER_TITLE.replace("${title}","Container"));
		}
	}	
	
	/**
	 * Add an application to a layout with an user not having permission
	 * @param nameApp
	 * @param appLocator
	 * @param layoutLocator
	 */
	public void addAppWithoutPermission(String tabName,String nameApp,Object appLocator,Object layoutLocator){
		info("Add an application to the layout");
		click(ELEMENT_APPLICATION_TAB);
		Utils.pause(1000);
		if(!tabName.isEmpty())
			click(ELEMENT_APPLICATION_SUB_TAB.replace("${tabName}", tabName));
		dragAndDropToObject(appLocator,layoutLocator);
		info("Verify that the application is NOT shown in the layout");
		waitForElementNotPresent(ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",nameApp),3000,1);
		
		Utils.pause(2000);
		saveChangesPageEditor();
	}
	
	
	/**
	 * Delete a contain in the layout by an user have no permission
	 */
	public void deleteContainerWithoutPermission(String id){
		info("Delete the container");
		try{
			click(ELEMENT_CONTAINER_TAB);
			Utils.pause(3000);
		}catch(org.openqa.selenium.UnhandledAlertException e){
			magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock",40000);
			clearCache();
		}
		if(!id.isEmpty()){
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}",id), true, 1);
			Utils.pause(3000);
			waitForElementNotPresent(ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}",id),1);
		}
		saveChangesPageEditor();
	}
	
	/**
	 * Select a container and open Edit form
<<<<<<< HEAD
<<<<<<< HEAD
	 * @param containerLocation
	 * @param containerEditLocation
=======
=======
>>>>>>> FQA-2759:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Site Permissions
<<<<<<< HEAD
	 * By: QuyenNT
=======
	 * @Author: QuyenNT
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
=======
	 * By: QuyenNT
>>>>>>> FQA-2759:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Site Permissions
	 * Date: Nov 11, 2015	
<<<<<<< HEAD
	 * @param:
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
=======
>>>>>>> FQA-2759:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Site Permissions
	 */
	public void openContainerEditForm(String containerLocation, String containerEditLocation){
		info("Select Container tab");
		click(ELEMENT_CONTAINER_TAB);
		mouseOver(containerLocation, true);
		waitForAndGetElement(containerEditLocation);
		click(containerEditLocation);
		waitForAndGetElement(ELEMENT_EDITING_CONTAINER_POPUP);
	}
	
	/**
<<<<<<< HEAD
	 *  Delete a container without permission
	 * @param containerId
	 * @param containerDeleteId
=======
	 * Delete a container without permission
<<<<<<< HEAD
<<<<<<< HEAD
	 * By: QuyenNT
=======
	 * @Author: QuyenNT
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
=======
	 * By: QuyenNT
>>>>>>> FQA-2759:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Site Permissions
	 * Date: Nov 11, 2015	
<<<<<<< HEAD
	 * @param:
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
=======
>>>>>>> FQA-2759:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Site Permissions
	 */
	public void deleteContainerWithoutPermission(String containerId, String containerDeleteId){
		info("Delete the container");
		try{
			click(ELEMENT_CONTAINER_TAB);
			Utils.pause(3000);
		}catch(org.openqa.selenium.UnhandledAlertException e){
			magAlert.waitForConfirmation("The target block ID to update is not found : EmptyAjaxBlock",40000);
			clearCache();
		}
		if(!containerId.isEmpty()){
			mouseOver(ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}",containerId), true, 1);
			Utils.pause(3000);
			waitForElementNotPresent(ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}",containerDeleteId),1);
		}
		saveChangesPageEditor();
	}	
	
}
