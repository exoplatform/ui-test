package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WcmAdmin extends EcmsBase {

	/* Manage ECM Main Functions */ 
	// Categories and Tags
	public static final By ELEMENT_MANAGE_CATEGORIES_LINK = By.linkText("Manage Categories");

	// Content Presentation
	public static final By ELEMENT_CONTENT_PRESENT = By.xpath("//div[contains(text(),'Content Presentation')]");
	public static final By ELEMENT_MANAGEMENT_VIEW = By.xpath("//a[contains(text(),'Manage View')]");
	public static final By ELEMENT_EDIT_VIEW = By.xpath("//*[@id='UIViewList']/table/tbody/tr[9]/td[5]/div/img[2]");
	public static final By ELEMENT_COLLABORATOR = By.linkText("Collaboration");
	public static final By ELEMENT_SYMLINK_CHECKBOX = By.id("addSymLink");
	public static final By ELEMENT_BACK_BUTTON = By.linkText("Back");
	public static final By ELEMENT_MANAGE_DRIVER_LINK = By.linkText("Manage Drives");
	
	//Content type
	public static final By ELEMENT_CONTENT_TYPES = By.xpath("//div[contains(text(),'Content Types')]");
	public static final By ELEMENT_MANAGE_NODETYPE = By.linkText("Manage Node Type");
	public static final By ELEMENT_NODETYPE_TEXT = By.id("NodeTypeText");
	public static final By ELEMENT_NODETYPE_SEARCH = By.xpath("//a[@title = 'Search']");

	//-Add Category tree Form - Screen1
	public static final By ELEMENT_CATEGORIES_TREE_NAME = By.id("TaxoTreeName");
	public static final By ELEMENT_CATEGORIES_WORKSPACE = By.id("TaxoTreeWorkspace");
	public static final By ELEMENT_ADD_PATH_LINK = By.xpath("//img[@title='Add Path']");
	public static final By ELEMENT_POPUP_HOME_PATH = By.id("PopupTaxonomyJCRBrowser");
	public static final By ELEMENT_NEXT_BUTTON = By.linkText("Next");
	// Add Category tree form -screen2
	public static final By ELEMENT_PREVIOUS_BUTTON = By.linkText("Previous");
	// Add Category tree form -screen3
	public static final By ELEMENT_ACTION_TYPE = By.id("actionType");
	public static final By ELEMENT_ACTION_NAME = By.id("actionName");
	public static final By ELEMENT_LIFE_CYCLE = By.id("lifecycle");
	public static final By ELEMENT_SELECT_PATH_ICON = By.xpath("//input[@id='targetPath']/../../td/a/img[@class='ActionIcon SelectPath24x24Icon']");
	public static final By ELEMENT_UP_LEVEL = By.xpath("//*[@id='UITaxonomyTreeCreateChild']//..//a[@title='Up Level']");
	public static final By ELEMENT_ALERT_VISIBLE = By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator' and contains(@style,'visible')]");	

	public static final By ELEMENT_ADD_CATEGORY_BUTTON = By.linkText("Add Category Tree");

	/*Manage Drive Page */
	//add drive
	public static final By ELEMENT_ADD_DRIVER_BUTTON = By.linkText("Add Drive"); 
	//edit drive
	public static final By ELEMENT_DRIVER_EDIT_POPUP = By.id("EditDriveManagerPopup");
	
	//Add Driver form 
	public static final By ELEMENT_ADD_DRIVER_POPUP = By.id("AddDriveManagerPopup");
	public static final By ELEMENT_APPLY_VIEW_TAB = By.xpath("//div[contains(text(),'Apply Views')]");
	public static final By ELEMENT_DRIVER_TAB = By.xpath("//div[contains(text(),'Drive')]");
	public static final By ELEMENT_WORKSPACE = By.id("workspace");
	public static final By ELEMENT_ADD_PATH = By.xpath("//img[@title='Add Path']");
	public static final By ELEMENT_ADD_PATH_POPUP = By.id("JCRBrowser");
	public static final By ELEMENT_JCR_SYSTEM = By.xpath("//div[contains(text(),'jcr:system')]/../../td/a[@title='select']/div");
	public static final By ELEMENT_HOME_PATH = By.id("homePath");
	public static final By ELEMENT_ADD_PERMISSION = By.xpath("//img[@title='Add Permission']");
	public static final By ELEMENT_PERMISSION_TEXTBOX = By.id("permissions");
	public static final By ELEMENT_ALLOW_CREATE_FOLDER = By.id("allowCreateFolders");
	public static final By ELEMENT_DRIVER_NAME = By.id("name");
	
	
	/* Manage View Page */
	public static By ELEMENT_ICON_VIEW_WCM_EDIT= By.xpath("//div[@title='WCM View']/../..//*[@class='EditInfoIcon']");
	//public static By ELEMENT_LINK_TAB_PUBLICATION= By.xpath("//a[contains(text(),'Publication')]");
	public static By ELEMENT_CHECKBOX_VERSION=By.id("manageVersions");
	public static By ELEMENT_BUTTON_BACK=By.linkText("Back");
	public static By ELEMENT_ADDNEW_BUTTON = By.linkText("Add View");
	public static By ELEMENT_TEMPLATE_VIEW_NAME = By.id("viewName");
	public static By ELEMENT_TEMPLATE_VIEW = By.id("template");
	public static By ELEMENT_TAB_NAME = By.id("tabName");
	public static By ELEMENT_TAB_ECM_TEMPLATE = By.xpath("//div[@class='MiddleTab' and text()='ECM Templates']");
	public static By ELEMENT_ECM_TEMPLATE_CONTENT = By.id("content");
	public static By ELEMENT_ECM_TEMPLATE_NAME = By.id("name");
	public static By ELEMENT_ECM_TEMPLATE_TYPE = By.id("homeTemplate");
	public static By ELEMENT_ENABLE_VERSION = By.id("enableVersion");
	public static By ELEMENT_EDIT_VIEW_FORM = By.xpath("//span[@class='PopupTitle' and text()='Edit View']");
	public static By ELEMENT_VERSION_OPTION = By.id("version"); 
	public static By ELEMENT_ECM_TEMPLATE_FORM = By.xpath("span[@class='PopupTitle' and text()='Add ECM Template']");
	public static By ELEMENT_ADDVIEW_FORM = By.xpath("span[@class='PopupTitle' and text()='Add New']");
	  
	
	//-------SEO Management Form--------------------
	public static By ELEMENT_SEO_FORM = By.id("UISEOPopupWindow");
	public static By ELEMENT_DESCRIPTION_TEXTAREA = By.id("description");
	public static By ELEMENT_KEYWORD_TEXTAREA = By.id("keywords");
	public static By ELEMENT_ROBOT_INDEX_COMBOX = By.id("index");
	public static By ELEMENT_ROBOT_FOLLOW_COMBOX = By.id("follow");
	public static By ELEMENT_SITE_MAP_CHECKBOX = By.id("sitemap");
	public static By ELEMENT_FREQUENCY_COMBOX = By.id("frequency");
	public static By ELEMENT_PRIORITY = By.id("priority");
	
	//Manage Template    
	public static By ELEMENT_MANAGE_TEMPLATE = By.linkText("Manage Templates");
	public static By ELEMENT_TEMPLATE_LABEL = By.id("label");
	public static By ELEMENT_TEMPLATE_NAME = By.id("name");
	public static By ELEMENT_MANAGE_TEMPLATE_SCREEN = By.xpath("//div[contains(text(),'Manage Templates')]");
	public static By ELEMENT_IS_DOCUMENT_TEMPLATE = By.id("isDocumentTemplate");  
	public static By ELEMENT_DIALOG_TAB = By.xpath("//div[@class='MiddleTab' and text()='Dialog']");
	public static By ELEMENT_VIEW_TAB = By.xpath("//div[@class='MiddleTab' and text()='View']");
	public static By ELEMENT_CSS_TAB = By.xpath("//div[@class='MiddleTab' and text()='CSS']");
	  
	
	//Setup to show [Add symlink] in action bar
	//set Add symlink view in action bar
	public static void setViewSymlink(){
		mouseOver(ELEMENT_LINK_SETUP, false);
		mouseOver(ELEMENT_MENU_CONTENT_LINK, false);
		click(ELEMENT_LINK_CONTENT_ADMIN);
		click(ELEMENT_CONTENT_PRESENT);
		click(ELEMENT_MANAGEMENT_VIEW);
		click(ELEMENT_EDIT_VIEW);
		click(ELEMENT_COLLABORATOR);
		WebElement check = waitForAndGetElement(ELEMENT_SYMLINK_CHECKBOX);
		if (check.isSelected()==false){
			click(ELEMENT_SYMLINK_CHECKBOX);
			click(ELEMENT_SAVE_BUTTON);
		}else{
			click(ELEMENT_BACK_BUTTON);
		}
		click(ELEMENT_SAVE_BUTTON);		  
	}

	public static void gotoManageNoteType(){
		goToContentAdministration();
		click(ELEMENT_CONTENT_TYPES);
		click(ELEMENT_MANAGE_NODETYPE);
		waitForElementPresent(By.xpath("//div[@class='FunctionTitle' and text() = 'Manage Node Type']"));
	}

	public static void doNodeTypeSearch(String keyword){
		type(ELEMENT_NODETYPE_TEXT, keyword, true);
		click(ELEMENT_NODETYPE_SEARCH);
	}
	// manage category----------------------------------------------------	  
	//function go to content administration
	public static void goToContentAdministration(){
		mouseOver(ELEMENT_LINK_SETUP, true);
		mouseOver(ELEMENT_MENU_CONTENT_LINK, true);
		click(ELEMENT_LINK_CONTENT_ADMIN);		  
	}
	// function to expand node on category tree
	public static void expandNode(String node, boolean ex){
		By ELEMENT_EXPAND = By.xpath("//div[@title='" + node + "']/../../a[@class='ExpandIcon exo_taxonomy16x16Icon default16x16Icon']");
		By ELEMENT_DISEXPAND = By.xpath("//div[@title='" + node + "']/../../a[@class='CollapseIcon exo_taxonomy16x16Icon default16x16Icon']");		  
		//goToNode(By.linkText(node));
		if (ex && (getElement(ELEMENT_EXPAND) != null)){
			click(ELEMENT_EXPAND);
		}
		if (!ex && (getElement(ELEMENT_DISEXPAND) != null)){
			click(ELEMENT_DISEXPAND);
		}
	}

	//function add child category for a category
	public static void addChildCategory(String cat_name, String child_name){
		info("Add child category " + child_name + "for category " + cat_name);
		click(By.xpath("//div[@title='"+cat_name+"']/../../td/div/img[@title='Add']"));
		type(By.id("taxonomyName"),child_name,false);
		click(ELEMENT_SAVE_BUTTON);
		expandNode(cat_name,true);
		waitForElementPresent(By.linkText(child_name));
		info("Add child category is successful");
	}

	//function copy and paste category
	public static void copyAndPasteCategory(String child1, String child2){
		By ELEMENT_CHILD_NEW = By.xpath("//div[@title='"+child2+"']/../../div/div/a/div[@title='"+child1+"']");

		click(By.xpath("//div[@title='"+child1+"']/../../td/div/img[@title='Copy']"));
		click(By.xpath("//div[@title='"+child2+"']/../../td/div/img[@title='Paste']"));
		expandNode(child2,true);
		waitForElementPresent(ELEMENT_CHILD_NEW);
		info("Copy category " + child1 + "to category " + child2 + "is successful");
	}

	//function cut and paste category
	public static void cutAndPasteCategory(String child1, String child2){
		By ELEMENT_CHILD_NEW = By.xpath("//div[@title='" + child2 + "']/../../div/div/a/div[@title='"+child1+"']");

		click(By.xpath("//div[@title='" + child1 + "']/../../td/div/img[@title='Cut']"));
		click(By.xpath("//div[@title='" + child2 + "']/../../td/div/img[@title='Paste']"));
		expandNode(child2,true);
		waitForElementPresent(ELEMENT_CHILD_NEW);
		click(ELEMENT_UP_LEVEL);
		waitForElementNotPresent(By.linkText("//*[@id='UITaxonomyTreeCreateChild']//../a/div[@title='" + child1 + "']"));
		info("Cut category " + child1 + "to category " + child2 + "is successful");
	}

	//delete category
	public static void deleteCategory(String categoryName){
		By ELEMENT_DELETE = By.xpath("//div[@title='" + categoryName + "']/../../td/div/img[contains(@title,'Delete')]");

		if (getElement(ELEMENT_DELETE) != null){
			click(ELEMENT_DELETE); 
			acceptAlert();
			waitForElementNotPresent(ELEMENT_DELETE);
			info("Delete category " + categoryName + "is successful");
		}else{
			info("Do not found element to delete");
		}
	}

	//function select home path
	public static void selectHomePath(String homePath){
		String[] temp;
		/* delimiter */
		String delimiter = "/";

		temp = homePath.split(delimiter);
		for(int i =0; i < temp.length - 1 ; i++){
			info("Go to "+temp[i]);
			click(By.linkText(temp[i]));
			pause(100);
		}
		click(By.xpath("//div[contains(text(),'" + temp[temp.length - 1] + "')]/../../td/a[@title='select']"));
	}

	//add new category tree: step 1 - Specify the category name, workspace and home path.
	public static void addNewCategoryTree_Step1(String name, String workspace, String homePath){
		type(ELEMENT_CATEGORIES_TREE_NAME,name, false);
		select(ELEMENT_CATEGORIES_WORKSPACE, workspace);
		click(ELEMENT_ADD_PATH_LINK);
		waitForElementPresent(ELEMENT_POPUP_HOME_PATH);
		selectHomePath(homePath);
	}

	//add new category tree: step 2 - Set permission for the category tree.
	public static void addNewCategoryTree_Step2(String user_Per, boolean read, boolean add, boolean set, boolean remove){
		selectUser(user_Per);
		setPermissionOfNode(read,add,set,remove);
		click(ELEMENT_SAVE_BUTTON);
		waitForElementPresent(By.xpath("//div[@title='" + user_Per + "']"));
		info("Add user permission for category is successfull");
	}

	//add new category tree: step 3 - Add an action to the category tree to complete the creation or update
	public static void addNewCategoryTree_Step3(String actionName){
		select(ELEMENT_ACTION_TYPE, "exo:taxonomyAction");
		type(ELEMENT_ACTION_NAME,actionName,false);
		select(ELEMENT_LIFE_CYCLE, "User Action");
		click(ELEMENT_SELECT_PATH_ICON);
		click(By.xpath("//div[contains(text(),'jcr:system')]/../../td/a[@title='select']"));
		click(ELEMENT_SAVE_BUTTON);
	}

	//add new category tree: step 4 - Edit the taxonomy tree by adding, copying, cutting and selecting permissions.
	public static void addNewCategoryTree_Step4(String name, String childname1, String childname2 ){
		//--add child category
		addChildCategory(name, childname1);
		click(ELEMENT_UP_LEVEL);
		addChildCategory(name, childname2);

		//--copy and paste
		copyAndPasteCategory(childname1, childname2);
		deleteCategory(childname1);
		click(ELEMENT_UP_LEVEL);

		//--cut and paste
		cutAndPasteCategory(childname1, childname2);

		//--set permission
		//		  info("Set permission for category");
		//		  click(ELEMENT_CLOSE_BUTTON);
		deleteCategory(childname2);
	}


	//function add new category tree
	public static void addNewCategoryTree(String name,String workspace, String homePath, String user_Per, boolean read, boolean add, boolean set, boolean remove, String actionName, String childname1, String childname2 ){
		By ELEMENT_CATEGORY_TREE = By.xpath("//div[@title='" + name + "']");

		info("Add new category tree with name: " + name);
		click(ELEMENT_MANAGE_CATEGORIES_LINK);
		click(ELEMENT_ADD_CATEGORY_BUTTON);

		//Step 1: Specify the category name, workspace and home path. 
		info("Add new category tree - step 1: Specify the category name, workspace and home path.");
		addNewCategoryTree_Step1(name, workspace, homePath);
		click(ELEMENT_NEXT_BUTTON);

		//step 2: Set permission for the category tree.
		info("Add new category tree - step 2: Set permission for the category tree.");
		addNewCategoryTree_Step2(user_Per,read,add,set,remove);
		click(ELEMENT_NEXT_BUTTON);

		//step 3: Add an action to the category tree to complete the creation or update
		info("Add new category tree - step 3: Add an action to the category tree to complete the creation or update");
		addNewCategoryTree_Step3(actionName);

		//step 4: Edit the taxonomy tree by adding, copying, cutting and selecting permissions.
		info("Add new category tree - step 4: Edit the taxonomy tree by adding, copying, cutting and selecting permissions");
		//addNewCategoryTree_Step4(name, childname1, childname2);
		click(ELEMENT_CLOSE_BUTTON);

		//check add new category tree successfully
		waitForElementPresent(ELEMENT_CATEGORY_TREE);
		assert isElementPresent(ELEMENT_CATEGORY_TREE):"Add new category tree is not successful";
		info("Add new category is successful");
	}
	/*
	 * Add function icon to the Publication tab on action bar
	 * locator: locator of checkbox icon of function on the edit View form
	 */
	public static void addFunctionToActionBar(By locator){
		goToContentAdministration();
		click(ELEMENT_CONTENT_PRESENT);
		click(ELEMENT_MANAGEMENT_VIEW);
		click(ELEMENT_ICON_VIEW_WCM_EDIT);
		click(ELEMENT_LINK_TAB_PUBLICATION);
		if (!waitForAndGetElement(locator).isSelected())
		click(locator);
		click(ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_BUTTON_BACK);
		click(ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}

	  //------------manage driver------------------
	  //function go to Manage Driver from main screen
	  public static void goToManageDriver(){
		  info("Go to manage driver form");
		  goToContentAdministration();
		  click(ELEMENT_CONTENT_PRESENT);
		  click(ELEMENT_MANAGE_DRIVER_LINK);
	  }
	  
	  //function add new driver in manage driver form
	  public static void addNewDriver(String driverName, String workspace, String path, String group, String member, String permission, String view ){
		  By ELEMENT_EXO_ECM = By.xpath("//div[contains(text(),'" + path + "')]/../../td/a[@title='select']/div");
		  By ELEMENT_DRIVER = By.xpath("//div[@title='" + driverName + "']");

		  
		  click(ELEMENT_ADD_DRIVER_BUTTON);
		  
		  //check add driver form
		  info("Add new driver with name " + driverName);
		  waitForElementPresent(ELEMENT_ADD_DRIVER_POPUP);
		  assert isElementPresent(ELEMENT_DRIVER_TAB):"Add driver form is wrong";
		  assert isElementPresent(ELEMENT_APPLY_VIEW_TAB):"Add driver form is wrong";
		  
		  //input name
		  type(ELEMENT_DRIVER_NAME,driverName,false);
		  
		  // Select workspace for new drive
		  select(ELEMENT_WORKSPACE, workspace);
		  
		  //select path for new drive
		  click(ELEMENT_ADD_PATH);
		  waitForElementPresent(ELEMENT_ADD_PATH_POPUP);
		  assert waitForAndGetElement(By.xpath("//select[@id='workspaceName']/option[@value='" + workspace + "']")).isSelected():"Homepath display wrong";
		  if (waitForAndGetElement(ELEMENT_EXO_ECM) != null){
			  click(ELEMENT_EXO_ECM);
		  }else{
			  info("list node of homepath display false");
		  }
		  assert getValue(ELEMENT_HOME_PATH).equals("/" + path + ""):"select homepath is false";
		  
		  //Set permission on new drive
		  info("Set Permission for driver");
		  click(ELEMENT_ADD_PERMISSION);
		  selectGroup(group);	
		  click(By.linkText(member));
		  assert getValue(ELEMENT_PERMISSION_TEXTBOX).contains(permission):"Set permission is not true";
		  
		  //Select Allow Folder Creation
		  select(ELEMENT_ALLOW_CREATE_FOLDER, "Folder");
		  
		  //Choose applied view
		  click(ELEMENT_APPLY_VIEW_TAB);
		  selectCheckboxList(view);
		  
		  //Complete add new drive
		  click(ELEMENT_SAVE_BUTTON);
		  select(By.xpath("//select[contains(@id,'maxPageSize')]"),"20");
		  waitForElementPresent(ELEMENT_DRIVER);
		  assert isElementPresent(ELEMENT_DRIVER):"Add new driver is unsuccessful";
		  info("Create new driver is successful");
	  }
	  
	  //function select checkbox list using id of checkbox
	  public static void selectCheckboxList(String viewList){
		  String[] temp = viewList.split("/");
		  if (temp.length != 0){
			  for (int i=0; i < temp.length ; i++ ){
				  if (waitForAndGetElement(By.id(temp[i])) != null){
					  if(waitForAndGetElement(By.id(temp[i])).isSelected() == false){
						  click(By.id(temp[i]));
						  info("Select checkbox with id " + temp[i]);
					  }
				  }else{
					  info("Can not found checkbox with id " + temp[i]);
				  }
			  }
		  }else{
			  info("Input checkbox list wrong");
		  }
	  }
  
	  //-----------SEO management------------------------------------
		/* function input data on SEO management Form
		 * @des: data input to description
		 * @key: data input to keyword
		 * @index: option of checkbox robot index
		 * @follow: option of checkbox robot follow
		 * @sitemap: = true: tick, = false: no tick
		 * @fre: option of checkbox frequency
		 * @pri: data input to priority
		 */
		public static void inputDataSeo(String des, String key, String index, String follow, boolean sitemap, String fre, String pri){
			
			waitForElementPresent(ELEMENT_SEO_FORM);
			waitForAndGetElement(ELEMENT_DESCRIPTION_TEXTAREA).clear();
			waitForAndGetElement(ELEMENT_KEYWORD_TEXTAREA).clear();
			waitForAndGetElement(ELEMENT_PRIORITY).clear();
			type(ELEMENT_KEYWORD_TEXTAREA, key, true);
			info("Input data on SEO management");
			if (des != ""){
				type(ELEMENT_DESCRIPTION_TEXTAREA, des, true);
			}
			if (key != ""){
				type(ELEMENT_KEYWORD_TEXTAREA, key, true);
			}
			select(ELEMENT_ROBOT_INDEX_COMBOX, index);
			select(ELEMENT_ROBOT_FOLLOW_COMBOX, follow);
			if ((sitemap && waitForAndGetElement(ELEMENT_SITE_MAP_CHECKBOX).isSelected() == false) || (sitemap == false && waitForAndGetElement(ELEMENT_SITE_MAP_CHECKBOX).isSelected())){
				click(ELEMENT_SITE_MAP_CHECKBOX);
			}
			select(ELEMENT_FREQUENCY_COMBOX, fre);
			if (pri != ""){
				type(ELEMENT_PRIORITY, pri, true);
			}
			click(ELEMENT_SAVE_BUTTON);
			info("finish input data on SEO management");
		}
		
	  /**
     * 
     * @param anchor: Button's label to open form
     * @param formTitle: Form's title
     */
    public static void openForm(String anchor, String formTitle){
      click(By.linkText(anchor));     
      waitForElementPresent(By.xpath("//span[@class='PopupTitle' and text()='" + formTitle + "']"));
    }
      
    /**
     * Select Membership
     * @param groupPath: Group string is separate by slash, for example platform/web-contributors
     * @param membership: Membership 
     * @param anchor: link icon to open select memebership form
     */
    public static void selectMembership(String groupPath, String membership, String anchor) throws Exception{
      click(By.xpath("//img[@title='" + anchor + "']"));
      selectGroup(groupPath);
      click(By.linkText(membership));
    }

	  /**
	   * Open Manage Template Screen
	   */
	  public static void openManageTemplateForm(){	    
	    goToContentAdministration();
	    click(ELEMENT_CONTENT_PRESENT);
	    click(ELEMENT_MANAGE_TEMPLATE);
	    waitForElementPresent(ELEMENT_MANAGE_TEMPLATE_SCREEN);	    
	  }	 
	  
	  /**
	   * Open Add New Template Form
	   */
	  public static void openAddNewTemplateForm() {
	    openForm("Add","Template Form");	    
	  }
	  /**
	   * Fill data to Add New Template Form
	   * @param label: Template's label
	   * @param groupId: Group string is separate by slash, for example platform/web-contributors
	   * @param membership: Membership
	   * @throws Exception
	   */
	 
	  public static void fillAddNewTemplateForm(String templateLabel, String templateName, String groupPath, String membership) throws Exception{   
      type(ELEMENT_TEMPLATE_LABEL,templateLabel, false);
      select(ELEMENT_TEMPLATE_NAME, templateName);
      click(ELEMENT_IS_DOCUMENT_TEMPLATE);
      selectMembership(groupPath,membership,"Add Permission");      
      //Switch between tabs
      click(ELEMENT_DIALOG_TAB);      
      click(ELEMENT_VIEW_TAB);
      click(ELEMENT_CSS_TAB);
      save();
      waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'" + templateName + "')]"));      
    }
	  
	  public static void deleleTemplate(String templateName, String confirmMessage) throws Exception{
      By locator = By.xpath("//div[@class='Text' and contains(text(),'" + templateName + "')]/ancestor::tr//img[@class='DeleteIcon']");
      click(locator);     
      waitForConfirmation(confirmMessage);
      waitForElementNotPresent(By.xpath("//div[@class='Text' and contains(text(),'" + templateName + "')]"));
	  }
	  
    /**
     * Go to Manage View Screen
     */
    public static void gotoManageViews(){
      goToContentAdministration();
      click(ELEMENT_CONTENT_PRESENT);
      click(ELEMENT_MANAGEMENT_VIEW);
      waitForElementPresent(By.xpath("//div[contains(text(),'Manage View')]"));
    }
    
    public static void gotoEcmTemplates(){
      //gotoManageViews();
      click(ELEMENT_TAB_ECM_TEMPLATE);
    }
    /**
     * 
     * @param name : Template name
     * @param template : Template view
     * @throws Exception
     */
    public static void fillAddNewViewForm(String name, String template) throws Exception{
      type(ELEMENT_TEMPLATE_VIEW_NAME, name, true);
      selectMembership("Organization/Management/Human Resources", "*","Add Permission");
      select(ELEMENT_TEMPLATE_VIEW, template);
      addTab("Test Tab", "addCategory");
      save();
      waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'" + name + "')]"));
    }
    
    /**
     * Add tab to view
     * @param tabname : Tab name
     * @param property : Tab Property
     * @throws Exception
     */
    public static void addTab(String tabname, String property) throws Exception{
      click(By.linkText("Add Tab"));
      waitForTextPresent("Tab Form");
      type(ELEMENT_TAB_NAME, tabname, true);
      click(By.id(property));
      save();
      waitForTextNotPresent("Tab Form");
    }
    /**
     * Delete a view
     * @param viewName : View Name
     * @param confirmMessage : Confirm Message
     * @throws Exception
     */
    public static void deleteView(String viewName, String confirmMessage, boolean verify) throws Exception{
      By locator = By.xpath("//div[@class='Text' and contains(text(),'" + viewName + "')]/ancestor::tr//img[@class='DeleteIcon']");
      click(locator);     
      waitForConfirmation(confirmMessage);
      if(verify == true) waitForElementNotPresent(By.xpath("//div[@class='Text' and contains(text(),'" + viewName + "')]"));      
    }
    /**
     * Edit View 
     * @param viewName : View Name
     * @throws Exception
     */
    public static void editView(String viewName) throws Exception{
      waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'" + viewName + "')]"));
      By locator = By.xpath("//div[@class='Text' and contains(text(),'" + viewName + "')]/ancestor::tr//img[@class='EditInfoIcon']");
      click(locator);
      waitForElementPresent(ELEMENT_EDIT_VIEW_FORM);
    }
    /**
     * Create version for a view
     * @param viewName : View Name
     * @param versionNumber : Number of version that you want to create. It require 2 version at least
     * @throws Exception
     */
    public static void createVersion(String viewName, int versionNumber) throws Exception{
      if(versionNumber < 2){
        assert false : ("Number of versions is required 2 at least");
      }
      for(int i = 0; i < versionNumber ; i++) {
        editView(viewName);
        if(getElement(ELEMENT_ENABLE_VERSION).isEnabled()) check(ELEMENT_ENABLE_VERSION);          
        save();
        pause(500);
      }
    }
    /**
     * Restore a view to a certain version
     * @param viewName : View Name
     * @param orderVersion : Version that want to restore
     * @throws Exception
     */
    public static void restoreVersion(String viewName, int orderVersion) throws Exception{
      editView(viewName);
      String order = "" + orderVersion;
      select(ELEMENT_VERSION_OPTION, order);
      click(By.linkText("Restore"));
      waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'" + viewName + "')]/ancestor::tr//div[@class='Text' and text()='" + orderVersion + "']"));
    }
    
    /**
     * Fill data to Add New ECM Template Form
     * @param templateContent : Template Content
     * @param templateName : Template Name
     * @param templateType : Template Type
     * @throws Exception
     */
    public static void fillEcmTemplateForm(String templateContent, String templateName, String templateType) throws Exception{
      type(ELEMENT_ECM_TEMPLATE_CONTENT, templateContent, true);
      type(ELEMENT_ECM_TEMPLATE_NAME, templateName, true);
      select(ELEMENT_ECM_TEMPLATE_TYPE, templateType);
      save();      
      waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'" + templateName + "')]"));
    }
}

