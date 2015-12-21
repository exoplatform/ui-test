package org.exoplatform.selenium.platform.administration;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class AdministrationLocator extends PlatformBase{

	/***************************CONTENT ADMINISTRATION*****************************************************************************/
	public By ELEMENT_ADVANCED_CATEGORIES_ECM_FUNCTIONS =By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Advanced')]");
	public By ELEMENT_EXPLORER_CATEGORIES_ECM_FUNCTIONS =By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Explorer')]");
	public By ELEMENT_TEMPLATE_CATEGORIES_ECM_FUNCTIONS =By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Templates')]");
	public By ELEMENT_RESPONSITORY_CATEGORIES_ECM_FUNCTIONS=By.xpath("//*[@class='ecmAdminPanel pull-left']//*[@class='accordion-toggle collapsed']/a[contains(text(),'Repository')]");

	public By ELEMENT_ECMS_FUNCTIONS_DRIVES =By.xpath("//*[@class='uiIconEcmsDriveManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_ACTIONS =By.xpath("//*[@class='uiIconEcmsActionManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_SCRIPTS =By.xpath("//*[@class='uiIconEcmsScriptManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_QUERIES =By.xpath("//*[@class='uiIconEcmsQueriesManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_CATEGORIES =By.xpath("//*[@class='uiIconEcmsTaxonomyManagerTrees uiIconEcmsLightGray']");

	// function Advanced, ACTIONS
	public By ELEMENT_ADD_ACTION_TYPE = By.xpath("//*[@id='UIActionManager']//*[@class='btn']");
	public By ELEMENT_ECM_ACTION_NAME_FORM = By.xpath("//*[@class='uiForm UIActionTypeForm']//*[@id='name']");
	public By ELEMENT_ECM_ACTION_SCRIPT_FORM = By.xpath("//*[@class='uiForm UIActionTypeForm']//*[@class='selectbox']");
	public By ELEMENT_ECM_ACTION_VARIABLES_FORM = By.xpath("//*[@class='uiForm UIActionTypeForm']//*[@id='variables0']");
	public By ELEMENT_ECM_ACTION_SAVE_FORM = By.xpath("//*[@id='UIActionTypeForm']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_ACTION_LIST = "//*[@id='UIActionTypeList']//div[contains(text(),'{$name}')]";
	public String ELEMENT_ECM_ACTION_DELETE_LIST ="//*[@id='UIActionTypeList']//div[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_ECM_ACTION_EDIT_LIST ="//*[@id='UIActionTypeList']//div[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";

	//function Advanced, Scripts
	public By ELEMENT_ECM_ADVANCED_SCRIPT_ADD_SCRIPT = By.xpath("//*[@id='UIScriptList']//*[contains(text(),'Add Script')]");
	public By ELEMENT_ECM_ADVANCED_SCRIPT_NAME_FORM = By.xpath("//*[@id='scriptLabel']");
	public By ELEMENT_ECM_ADVANCED_SCRIPT_CONTENT_FORM = By.xpath("//*[@id='scriptContent']");
	public By ELEMENT_ECM_ADVANCED_SCRIPT_SCRIPT_FORM = By.xpath(".//*[@id='scriptName']");
	public By ELEMENT_ECM_ADVANCED_SCRIPT_SAVE_FORM = By.xpath("//*[@id='ScriptContainerPopup']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_ADVANCED_SCRIPT_EDIT_LIST = "//*[contains(text(),'{$name}')]/../..//*[@title='Edit']";
	public String ELEMENT_ECM_ADVANCED_SCRIPT_DELETE_LIST = ".//*[contains(text(),'{$name}')]/../..//*[@title='Delete']";
	public String ELEMENT_ECM_ADVANCED_SCRIPT_LIST = ".//*[@id='UIScriptList']//*[contains(text(),'{$name}')]";

	//function Advanced, queries
	public By ELEMENT_ECM_ADVANCED_QUERIES_ADD_QUERIES = By.xpath("//*[@id='UIQueriesList']//*[contains(text(),'Add Query')]");
	public By ELEMENT_ECM_ADVANCED_QUERIES_NAME_FORM = By.xpath(".//*[@id='UIQueriesForm']//*[@id='name']");
	public By ELEMENT_ECM_ADVANCED_QUERIES_QUERY_TYPE_FORM = By.xpath(".//*[@class='selectbox' and @name='type']");
	public By ELEMENT_ECM_ADVANCED_QUERIES_STATEMENT_FORM = By.xpath(".//*[@id='statement']");
	public By ELEMENT_ECM_ADVANCED_QUERIES_PERMISSION_FORM = By.xpath(".//*[@class='uiIconAddPermission uiIconLightGray']");
	public By ELEMENT_PERMISSION_ANY = By.xpath(".//*[@class='uiIconAddAnyPermission uiIconLightGray']");
	public By ELEMENT_ECM_ADVANCED_QUERIES_SAVE_FORM = By.xpath(".//*[@id='UIQueriesForm']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_ADVANCED_QUERIES_EDIT_BUTTON = "//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";
	public String ELEMENT_ECM_ADVANCED_QUERIES_TYPE_LIST = "//*[contains(text(),'{$name}')]/../../td[2]//*[contains(text(),'{$type}')]";
	public String ELEMENT_ECM_ADVANCED_QUERIES_DELETE_BUTTON = "//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_ECM_ADVANCED_QUERIES_LIST = ".//*[@id='UIQueriesList']//*[contains(text(),'{$name}')]";

	// functions Advanced, categories
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_CATEGORIES = By.xpath(".//*[@id='UITaxonomyTreeList']//*[contains(text(),'Add Category Tree')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_FORM = By.xpath(".//*[@id='TaxoTreeName']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM = By.xpath(".//*[@id='TaxonomyTreeMainForm']//*[contains(text(),'Next')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_2NDPAGE_FORM = By.xpath(".//*[@id='UIPermissionTreeForm']//*[contains(text(),'Next')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_3RDPAGE_FORM = By.xpath(".//*[@id='UIActionTaxonomyManager']//*[contains(text(),'Next')]");

	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE = By.xpath("//*[@id='UIPermissionTreeForm']//*[contains(text(),'Previous')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE_SAVE = By.xpath("//*[@id='UIPermissionTreeForm']//*[contains(text(),'Save')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE_READ=By.xpath("//*[@id='read']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE_ADD=By.xpath("//*[@id='add_node']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE_REMOVE=By.xpath("//*[@id='remove']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_3RDPAGE = By.xpath("//*[@id='UIActionTaxonomyManager']//*[contains(text(),'Previous')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_4THPAGE = By.xpath("//*[@id='UITaxonomyTreeCreateChild']//*[contains(text(),'Previous')]");

	public By ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_ACTION_FORM = By.xpath(".//*[@id='actionName']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_LIFECYCLE_FORM = By.xpath(".//*[@id='UIActionForm']//*[@class='selectbox']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_OPEN_TARGETPATH_ACTION_FORM = By.xpath(".//*[@id='UIActionForm']//*[@id='targetPath']/../a[1]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM = By.xpath(".//*[@class='uiIconAddRootNode uiIconLightGray']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_SAVE_FORM = By.xpath("//*[@id='UIActionTaxonomyManager']//*[contains(text(),'Save')]");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM = By.xpath("//*[@class='uiTaxonomyTreeWizard']//*[contains(text(),'Close')]");
	public String ELEMENT_ECM_ADVANCED_CATEGORIES_EDIT_FORM =".//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_SELECT_FORM = By.xpath("//*[@id='TaxonomyTreeMainForm']//*[@name='TaxoTreeWorkspace']");
	public By ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_HOME_PATH_FORM = By.xpath(".//*[@id='TaxonomyTreeMainForm']//*[@class='uiIconAddPath uiIconLightGray']");
	public String ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_LIST = ".//*[contains(text(),'{$name}')]/../../td[2]//*[contains(text(),'{$workspace}')]";
	public String ELEMENT_ECM_ADVANCED_CATEGORIES_DELETE =".//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

	public final String ELEMENT_SELECT_CATEGORY=".//*[@class='explorerTree']//*[contains(@title,'${name}')]";
	public By ELEMENT_ADD_SUB_CAT_BUTTON=By.xpath("//*[@data-original-title='Add' or @title='Add']");
	public By ELEMENT_NAME_CAT_TEXTBOX=By.id("taxonomyName");
	public By ELEMENT_ADD_CAT_SAVE_BUTTON=By.xpath("//*[@id='UITaxonomyTreeCreateChildForm']//*[@class='btn' and text()='Save']");
	
	public By ELEMENT_ECMS_FUNCTIONS_VIEWS =By.xpath("//*[@class='uiIconEcmsViewManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_TAGS =By.xpath("//*[@class='uiIconEcmsFolksonomyManager uiIconEcmsLightGray']");

	public By ELEMENT_ECMS_FUNCTIONS_NODES = By.xpath("//*[@class='uiIconEcmsNodeTypeManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_NAMESPACES = By.xpath("//*[@class='uiIconEcmsNamespaceManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_LOCKS = By.xpath("//*[@class='uiIconEcmsUnLockManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_DOCUMENTS = By.xpath("//*[@class='uiIconEcmsTemplatesManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_LIST = By.xpath("//*[@class='uiIconEcmsCLVTemplatesManager uiIconEcmsLightGray']");
	public By ELEMENT_ECMS_FUNCTIONS_METADATA = By.xpath("//*[@class='uiIconEcmsMetadataManager uiIconEcmsLightGray']");


	// Explorer, drives
	public By ELEMENT_ECM_EXPLORER_DRIVES_ADD_DRIVES = By.xpath("//*[@id='UIDriveList']//*[contains(text(),'Add Drive')]");
	public By ELEMENT_ECM_EXPLORER_NAME_DRIVES_FORM = By.xpath("//*[@id='name']");
	public By ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM = By.xpath("//a[contains(text(),'Apply Views')]");
	public By ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN = By.xpath("//*[@class='UIFormInputSet']//*[@id='Admin']");
	public By ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES = By.xpath("//*[@class='UIFormInputSet']//*[@id='Categories']");
	public By ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS = By.xpath("//*[@class='UIFormInputSet']//*[@id='Icons']");
	public By ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST = By.xpath("//*[@class='UIFormInputSet']//*[@id='List']");
	public By ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB = By.xpath("//*[@class='UIFormInputSet']//*[@id='Web']");
	public String ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ITEM = "//*[@class='UIFormInputSet']//*[@id='$item']";
	public By ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM = By.xpath("//*[@id='UIDriveForm']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_EXPLORER_DRIVES_EDIT_LIST = "//*[@id='UIDriveList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_DRIVES_DELETE_LIST = "//*[@id='UIDriveList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_DRIVES_VIEW_OF_VIEWS_LIST = "//*[@id='UIDriveList']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$view}')]";

	// Explorer, views
	public By ELEMENT_ECM_EXPLORER_VIEWS_ADD_VIEWS = By.xpath("//*[@class='UIViewList']//*[contains(text(),'Add View')]");
	public By ELEMENT_ECM_EXPLORER_NAME_VIEW_FORM = By.xpath("//*[@id='viewName']");

	public String ELEMENT_ECM_EXPLORER_CHOOSE_TAB_CATEGORY_VIEW_FORM ="//*[contains(text(),'{$tab}')and @class='control-label']/../div/span/input";
	public By ELEMENT_ECM_EXPLORE_TAB_NAME_VIEW_FORM = By.xpath("//*[@id='tabName']");
	public By ELEMENT_ECM_EXPLORE_SAVE_TAB_VIEW_FORM = By.xpath("//*[@id='UITabForm']//*[contains(text(),'Save')]");
	public By ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM = By.xpath("//*[@id='UIViewFormTabPane']//*[contains(text(),'Permission')]");
	public By ELEMENT_ECM_EXPLORER_ADD_PERMISSION_FORM = By.xpath("//*[@class='permission']//*[contains(text(),'Add')]");
	public By ELEMENT_ECM_EXPLORER_USER_PERMISSION_ADD = By.xpath("//*[@id='UIViewPermissionForm']//*[@class='uiIconSelectUser uiIconLightGray']");
	public By ELEMENT_ECM_EXPLORER_GROUP_PERMISSION_ADD = By.xpath("//*[@id='UIViewPermissionForm']//*[contains(@class,'uiIconSelectMember')]");
	
	public String ELEMENT_ECM_EXPLORER_SELECT_USER_LIST_PERMISSION = "//*[@id='UIListUsers']//*[@class='text' and contains(text(),'{$user}')]/../../td[5]/a";
	public By ELEMENT_ECM_EXPLORER_SAVE_FORM_ADD_VIEW = By.xpath("//*[@id='UIViewFormTabPane']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_EXPLORER_DELETE_PERMISSION_USER ="//*[@id='UIViewPermissionList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST = "//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_VIEW_DELETE_LIST = "//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_VIEW_PERMISSIONS_LIST ="//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$permission}')]";
	public String ELEMENT_ECM_EXPLORER_VIEW_SHOW_A_VIEW_LIST ="//*[@id='UIViewList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconView uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_NAME_VIEW_SHOW_VIEW = "//*[@id='viewName' and @value='{$name}']";
	public String ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW ="//*[@id='UITabList']//*[contains(text(),'{$tab}')]";
	//Explorer,Views-->Actions tab
	public By ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM = By.xpath("//*[@id='UIViewFormTabPane']//*[contains(text(),'Action')]");
	public By ELEMENT_ECM_EXPLORER_EDIT_ACTION_VIEW_FORM = By.xpath(".//*[@id='UITabList']//i[@class='uiIconEdit uiIconLightGray']");
	public By ELEMENT_ECM_EXPLORER_ADD_ACTION_VIEW_FORM = By.xpath(".//*[@id='UITabContainer']//button[text()='Add']");
	public By ELEMENT_ECM_EXPLORER_DELETE_ACTION_VIEW_FORM =By.xpath(".//*[@id='UITabList']//i[@class='uiIconDelete uiIconLightGray']");

	//Explorer,Views-->Actions tab-->Add/Edit popup
	public By ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE = By.xpath(".//*[@id='UIViewFormTabPane']//*[contains(text(),'Close')]");		
	public String ELEMENT_ECM_EXPLORER_EDIT = ".//*[@data-original-title='${nameView}']/../..//i[@class='uiIconEditInfo uiIconLightGray']";
	public By ELEMENT_ECM_EXPLORER_EDIT_VIEWS_SAVE_BUTTON = By.xpath(".//*[@id='UIViewFormTabPane']//button[text()='Save']");

	//Expolorer,Views-->Actions tab-->Add/Edit popup
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_CATEGORY=By.id("addCategory");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_TRANSLATION=By.id("addLocalizationLink");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_CONTENT_NAVIGATION=By.id("contentNavigation");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_IMPORT_NOTE=By.id("importNode");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_CATEGORIES=By.id("manageCategories");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_RELATIONS=By.id("manageRelations");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_APPROVE_CONTENT=By.id("publicationApproveContent");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_SHOW_JCR_STRUCTURE=By.id("showJCRStructure");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_METADATA=By.id("viewMetadatas");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_PROPERTIES=By.id("viewProperties");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_DOCUMENT=By.id("addDocument");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_SYMLINK=By.id("addSymLink");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_EDIT_DOCUMENT=By.id("editDocument");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_ACTIONS=By.id("manageActions");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_HIDE_SHOW_CONTENT=By.id("manageHidden");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_VERSIONS=By.id("manageVersions");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_PUBLISH=By.id("publicationPublish");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_TAG_DOCUMENT=By.id("taggingDocument");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_NODE_TYPE=By.id("viewNodeType");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VOTE=By.id("vote");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_ADD_FOLDER=By.id("addFolder");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_COMMENT=By.id("comment");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_EXPORT_NODE=By.id("exportNode");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_AUDITING=By.id("manageAuditing");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_MANAGE_PUBLICATION=By.id("managePublications");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_OVERLOAD_THUMBNAIL=By.id("overloadThumbnail");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_REQUEST_APPROVAL=By.id("publicationRequestApproval");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_UPLOAD=By.id("upload");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_VIEW_PERMISSIONS=By.id("viewPermissions");
	public By ELEMENT_ECM_EXPLORER_ACTIONS_POPUP_WATCH_DOCUMENT=By.id("watchDocument");
	public By ELEMENT_ECM_EXPORER_ACTIONS_POPUP_SAVE_BUTTON=By.xpath(".//*[@id='UITabForm']//button[text()='Save']");

	// explorer, tags
	public By ELEMENT_ECM_EXPLORER_TAGS_TAG_PERM_TAB = By.xpath("//*[contains(@data-target,'UITagPermissionManager')]");
	public By ELEMENT_ECM_EXPLORER_TAGS_ADD_STYLE_BUTTON = By.xpath("//*[@id='UITagManager']//*[contains(text(),'Add Style')]");
	public By ELEMENT_ECM_EXPLORER_TAGS_ADD_NAME_FORM = By.xpath("//*[@id='styleName']");
	public By ELEMENT_ECM_EXPLORER_TAGS_NUMBER_OCCURENCE_FORM = By.xpath("//*[@id='documentRange']");
	public By ELEMENT_ECM_EXPLORER_TAGS_HTML_STYLE_FORM = By.xpath("//*[@id='styleHTML']");
	public By ELEMENT_ECM_EXPLORER_TAGS_UPDATE_FORM = By.xpath("//*[@id='UITagStyleForm']//*[contains(text(),'Update')]");
	public String ELEMENT_ECM_EXPLORER_TAGS_EDIT_LIST = "//*[@id='UITagManager']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_TAGS_DELETE_LIST = "//*[@id='UITagManager']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconRemove uiIconLightGray']";
	public String ELEMENT_ECM_EXPLORER_TAGS_LIST_CHECK_HTML_CONTENT = "//*[@id='UITagManager']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$html}')]";
    public final By ELEMENT_ECM_EXPLORER_TAGS_PERM_TAB = By.xpath(".//*[contains(@data-target,'TagPermissionManager')]");
	//common element
	public By ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON = By.xpath(".//*[@class='uiIconAddPermission uiIconLightGray']");
	public By ELEMENT_ECM_COMMON_ANY_PERMISSION = By.xpath("//*[@class='uiIconAddAnyPermission uiIconLightGray']");


	// repository, nodes type
	public By ELEMENT_ECM_REPOSITORY_NODES_ADD = By.xpath("//*[@id='ListNodeType']//*[contains(text(),'Add')]");
	public By ELEMENT_ECM_REPOSITORY_NODES_NAME_FORM = By.xpath("//*[@id='nodeTypeName']");
	public By ELEMENT_ECM_REPOSITORY_NODES_SUPER_TYPES_FORM = By.xpath("//*[@id='superTypes']");
	public By ELEMENT_ECM_REPOSITORY_NODES_MIXIN_TYPES = By.xpath("//*[@name='mixinType']");
	public By ELEMENT_ECM_REPOSITORY_NODES_SAVE_FORM = By.xpath("//*[@id='UINodeTypeForm']//*[contains(text(),'Save')]");
	public By ELEMENT_ECM_REPOSITORY_NODES_SEARCH_NODE = By.xpath("//*[@id='NodeTypeText']");
	public String ELEMENT_ECM_REPOSITORY_NODES_SHOW_SPECIFIC_NODE = ".//*[@id='ListNodeType']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconPreview uiIconLightGray']";
	public String ELEMENT_ECM_REPOSITORY_NODES_CHECK_SUPER_TYPES = "//*[@id='superTypes' and @value='{$types}']";
	public By ELEMENT_ECM_REPOSITORY_NODES_CLOSE_FORM = By.xpath("//*[@id='UINodeTypeForm']//*[contains(text(),'Close')]");
	public By ELEMENT_ECM_REPOSITORY_NODES_OK_FORM = By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(),'OK')]");

	// repository, namespaces
	public By ELEMENT_ECM_REPOSITORY_NAMESPACES_ADD = By.xpath("//*[@id='UINamespaceManager']//*[contains(text(),'Register')]");
	public By ELEMENT_ECM_REPOSITORY_NAMESPACES_FORM_NAME = By.xpath("//*[@id='namespace']");
	public By ELEMENT_ECM_REPOSITORY_NAMESPACES_URI_FORM = By.xpath("//*[@id='uri']");
	public By ELEMENT_ECM_REPOSITORY_NAMESPACES_SAVE_FORM = By.xpath("//*[@id='UINamespaceForm']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX= ".//*[@id='UINamespaceList']//*[contains(text(),'{$prefix}')]/../..//*[contains(text(),'{$url}')]";

	// repository Locks
	public By ELEMENT_ECM_REPOSITORY_LOCKS_DEVELOPMENT_GROUP = By.xpath("//*[@id='UIPermissionSelector']//*[contains(text(),'Development')]");
	public By ELEMENT_ECM_REPOSITORY_LOCKS_ALL_GROUP = By.xpath("//*[@id='UIPermissionSelector']//*[contains(text(),'*')]");
	public String ELEMENT_ECM_REPOSITORY_CHECK_LOCK_PERMISSION = "//*[@id='UILockHolderList']//*[contains(text(),'{$group}')]";
	public String ELEMENT_ECM_REPOSITORY_DELETE_LOCK_PERMISSION = "//*[@id='UILockHolderList']//*[contains(text(),'{$group}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public By ELEMENT_ECM_REPOSITORY_MANAGE_LOCK = By.xpath("//*[@id='UIUnLockManager']//*[contains(text(),'Manage Lock')]");
	public String ELEMENT_ECM_REPOSITORY_UNLOCK_NODE_LIST = "//*[@id='UILockNodeList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconUnlockMini uiIconLightGray']";

	// templates, Documents
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_ADD_DOCUMENT = By.xpath("//*[@id='UITemplateContainer']//*[contains(text(),'Add Template')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_LABEL_FORM = By.xpath("//*[@id='label']");	
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_FORM = By.xpath("//*[@id='UITemplateForm']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST = "//*[@id='UITemplateList']//*[contains(text(),'{$name}')]";
	public String ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_EDIT = "//*[@id='UITemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_EDIT_FORM = By.xpath("//*[@id='UITemplateEditForm']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_TEMPLATES_DOCUMENTS_LIST_DELETE = "//*[@id='UITemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_TAB= By.xpath("//*[@data-toggle='tab'][contains(.,'Dialog')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_EDIT=By.xpath("//*[@id='DialogList']//*[contains(@class,'uiIconEdit')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_REMOVE_PERM=By.xpath(".//*[@id='DialogForm']//*[contains(@class,'uiIconRemovePermission')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_ADD_PERM=By.xpath(".//*[@id='DialogForm']//*[contains(@class,'uiIconAddPermission')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_DIALOG_SAVE=By.xpath(".//*[@id='DialogForm']//*[contains(.,'Save')][@class='btn']");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_TAB= By.xpath("//*[@data-toggle='tab'][contains(.,'View')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_EDIT=By.xpath("//*[@id='VewList']//*[contains(@class,'uiIconEdit')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_REMOVE_PERM=By.xpath("//*[@id='ViewForm']//*[contains(@class,'uiIconRemovePermission')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_ADD_PERM=By.xpath("//*[@id='ViewForm']//*[contains(@class,'uiIconAddPermission')]");
	public By ELEMENT_ECM_TEMPLATES_DOCUMENTS_VIEW_SAVE=By.xpath("//*[@id='ViewForm']//*[contains(.,'Save')][@class='btn']");
	
	// templates, List
	public By ELEMENT_ECM_TEMPLATES_LIST_ADD_LIST = By.xpath("//*[@id='ContentTemplateContainer']//*[contains(text(),'Add Template')]");
	public By ELEMENT_ECM_TEMPLATES_LIST_TEMPLATE_NAME_FORM = By.xpath("//*[@id='template']");
	public By ELEMENT_ECM_TEMPLATES_LIST_NAME_FORM = By.xpath("//*[@id='title']");
	public By ELEMENT_ECM_TEMPLATES_LIST_CONTENT_FORM = By.xpath("//*[@id='content']");
	public By ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM = By.xpath("//*[@id='UICLVTemplateForm_ContentTemplateContainer']//*[contains(text(),'Save')]");
	public String ELEMENT_ECM_TEMPLATES_LIST_CHECK_LIST ="//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]/../..//*[contains(text(),'{$template}')]";
	public String ELEMENT_ECM_TEMPLATES_LIST_CHECK_BY_NAME ="//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]";
	public String ELEMENT_ECM_TEMPLATES_LIST_EDIT_LIST ="//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconEdit uiIconLightGray']";
	public String ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST ="//*[@id='UICLVTemplateList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

	// templates, metadata
	public String ELEMENT_ECM_TEMPLATES_METADATA_LIST = "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]";
	public String ELEMENT_ECM_TEMPLATES_METADATA_FORM_EDIT = "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]/..//*[@class='uiIconEdit uiIconLightGray']";
	public By ELEMENT_ECM_TEMPLATES_METEDATA_FORM_EDIT_LABEL =By.xpath("//*[@id='metadataLabel']");
	public By ELEMENT_ECM_TEMPLATES_METADATA_FORM_APPLY = By.xpath("//*[@id='UIMetadataForm']//*[contains(text(),'Apply')]");
	public String ELEMENT_ECM_TEMPLATES_METADATA_FORM_SHOW = "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]/../..//*[@class='uiIconView uiIconLightGray']";
	public String ELEMENT_ECM_TEMPLATES_METADATA_CHECK_MATADATA_INFORMATION = "//*[@class='metadataInfoDetails']//*[contains(text(),'{$metadata}')]";
	public By ELEMENT_ECM_TEMPLATES_METADATA_CLOSE_VIEW = By.xpath("//*[@id='ViewMetadataPopup']//*[contains(text(),'Close')]");
	public String ELEMENT_ECM_TEMPLATES_METADATA_FORM_DELETE = "//*[@id='UIMetadataList']//*[contains(text(),'{$name}')]/..//*[@class='uiIconDelete uiIconLightGray']";
	public By ELEMENT_ECM_TEMPLATES_METADATA_FORM_OK_FORM = By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(),'OK')]");
    
	/*****************************************************CHANGE LANGUAGES****************************************************************************************/
	public final By ELEMENT_CHANGE_LANGUAGE_POPUP_TITLE = By.xpath(".//*[@id='UIMaskWorkspace']//h5");
	public final String ELEMENT_CHANGELANGUAGE_LANGUAGE = "//*[text()='${language}']";
	public final String ELEMENT_AVATAR_CHANGELANGUAGE_APPLY = "//*[text()='${text}']";
	
	/*****************************************************CONTENT SEARCH ADMINISTRATION*****************************************************************************/
	//table
	public final By ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_TITLE=By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");
	public final By ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_DESCRIPTION=By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");
	public final By ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_ACTION=By.xpath(".//*[@id='searchAdmin']//th[text()='Content Type']");
	
	//Action column
	public final String ELEMENT_SEARCHADMIN_ACTION_DISABLE_BUTTON = ".//*[contains(text(),'${type}')]/..//input[@value='Disable']";
	public final String ELEMENT_SEARCHADMIN_ACTION_ENABLE_BUTTON = ".//*[contains(text(),'${type}')]/..//input[@value='Enable']";
	
	/***************************************************MANAGE LAYOUT***********************************************************************************************/
	public final By ELEMENT_PERMISSION_PUBLIC_CHECKBOX=By.xpath(".//*[contains(@id,'UIListPermissionSelector')]//input[@id='publicMode']");
	public final By ELEMENT_PERMISSION_GRID=By.xpath(".//*[@id='PermissionGrid']");
	
	//*=========================================================EDIT/SITE/LAYOUT==============================================*\
	public final By ELEMENT_EDIT_SITE_LAYOUT_SITE_CONFIG_BTN=By.xpath(".//*[@id='UIPortalComposer']//*[contains(@class,'PageProfileIcon')]");
	public final By ELEMENT_EDIT_SITE_LAYOUT_SAVE_BTN=By.xpath(".//*[@id='UIPortalComposer']//*[contains(@class,'uiIconSave')]");
	
	//SITE CONFIG POPUP
	public final By ELEMENT_SITE_CONFIG_POPUP_PERMISSION_TAB=By.xpath(".//*[@id='UIMaskWorkspace']//*[contains(@data-target,'#PermissionSetting-tab')]");
	public final By ELEMENET_SITE_CONFIG_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIPortalForm']//button[1]");
	
	
	//Edit Portlet popup
	public final By ELEMENT_PORTLET_POPUP_PERMISSION_TAB=By.xpath(".//*[@data-target='#PortletPermission-tab']");
	public final By ELEMETN_PORTLET_POPUP_SAVE_BTN=By.xpath(".//*[@id='Save']");
	
	//HOME PAGE LAYOUT
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_BREADCRUM_NAVIGATION=By.xpath(".//*[contains(@id,'LeftBreadCrumbNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_BREADCRUM_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'LeftBreadCrumbNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_COMPANY_NAVIGATION=By.xpath(".//*[contains(@id,'LeftNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_COMPANY_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'LeftNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_GROUPS_NAVIGATION=By.xpath(".//*[contains(@id,'GroupsNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_GROUPS_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'GroupsNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_SPACES_NAVIGATION=By.xpath(".//*[contains(@id,'SpaceNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_SPACES_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'SpaceNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	//*=============================================================EDIT/PAGE/EDIT LAYOUT===========================================*\

	public final By ELEMENT_PAGE_EDIT_LAYOUT_PROPERITES_BTN =By.xpath(".//*[@id='UIPageEditor']//*[contains(@class,'PageProfileIcon')]");
	public final By ELEMENT_PAGE_EDIT_LAYOUT_SAVE_BTN=By.xpath(".//*[@id='UIPageEditor']//*[contains(@class,'uiIconSave')]");
	public final By ELEMENT_PAGE_EDIT_LAYOUT_CONTAINER_TAB=By.xpath(".//*[@data-target='#contList']");
	
	//WIKI CONTAINER
	public final By ELEMENT_PAGE_EDIT_LAYOUT_WIKI_CONTAINER=By.xpath(".//*[@id='myWikiPortlet']//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_PAGE_EDIT_LAYOUT_WIKI_CONTAINER_EDIT_BTN=By.xpath(".//*[@id='myWikiPortlet']//*[contains(@class,'EDITION-CONTAINER')]//*[contains(@class,'uiIconEdit')]");
	
	//EDIT CONTAINER POPUP
	public final By ELEMENT_CONTAINER_POPUP_PERMISSION_TAB=By.xpath(".//*[@data-target='#UIContainerPermission-tab']");
	public final By ELEMENT_CONTAINER_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIContainerForm']//button[1]");
	
	//PROPERTIES POPUP
	public final By ELEMENT_PROPERTIES_POPUP_PERMISSION_TAB=By.xpath(".//*[@data-target='#PermissionSetting-tab']");
	public final By ELEMENT_PROPERTIES_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIPageForm']//button[1]");
	
}
