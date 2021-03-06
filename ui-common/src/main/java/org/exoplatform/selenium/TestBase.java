package org.exoplatform.selenium;

import org.testng.Assert;
import static org.exoplatform.selenium.TestLogger.debug;
import static org.exoplatform.selenium.TestLogger.error;
import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class TestBase {
	
	
	private static Scanner scanner;
	public WebDriver driver;
	public WebDriver newDriver;
	public Boolean isDriver = true;

	protected int DEFAULT_TIMEOUT = 30000; //milliseconds = 30 seconds
	protected int WAIT_INTERVAL = 1000; //milliseconds  
	public int loopCount = 0;	
	protected boolean ieFlag;	 
	protected boolean chromeFlag;

	protected String plfVersion = "";
	public final int ACTION_REPEAT = 5;
	public static boolean firstTimeLogin = false;
	public Actions action;

	//Driver path
	public String uploadfile= getAbsoluteFilePath("TestData\\attachFile.exe");
	public String downloadfile=getAbsoluteFilePath("TestData\\downloadIE9.exe");
	public String ieDriver=getAbsoluteFilePath("TestData\\IEDriverServer.exe");
	public String chromeDriver= getAbsoluteFilePath("TestData\\chromedriver.exe");
	public String chromeDriverUbuntu= getAbsoluteFilePath("TestData\\chromedriver");

	/*========System Property====================*/
	public static String baseUrl;
	public static String browser;
	public static String server;

	protected Boolean isRandom;
	protected Boolean isUseFile;

	protected String jdbcDriver;
	protected String dbUrl;
	protected String user;
	protected String pass;

	protected String sqlWiki;
	protected String sqlAttach;
	protected String sqlUser;
	protected String sqlContent;

	protected String siteExpDrivePath;
	protected String siteExpPathPath;
	protected String siteExpContentTypeFilePath;
	protected String videoLinksFilePath;
	protected String dataTestFilePath;

	protected String defaultSheet;

	protected String userDataFilePath;
	protected String userInfoFilePath;
	protected String mailSuffixFilePath;
	protected String userSearchOptionFilePath;
	
	protected String attachmentFilePath;
	protected String texboxFilePath;
	protected String permissionPath;
	protected String linkPath;
	protected String changLangDataPath;
	

	protected String contactIMFilePath;
	protected String contactPhoneFilePath;
	protected String activityMesFilePath;
	protected String conStatusFilePath;
	protected String chatStatusFilePath;
	protected String groupsCalenderFilePath;
	protected String remoteGadgetDataFilePath;
	protected String languageFilePath;
	protected String appGateinDataFilePath;
	protected String getStartFilePath;

	protected String gadgetFilePath;
	protected String containerFilePath;
	protected String appLayoutFilePath;
	protected String nativeEvent;
	protected String groupNameDataFilePath;

	protected String appListGateinFilePath;
	protected String appAddGateinFilePath;
	protected String createNewGateinFilePath;
	protected String categoriesGateinFilePath;
	protected String pageMagListFilePath;
	protected String portalDefaultFilePath;
	protected String portalPermisGroupFilePath;
	protected String portalPermisMemFilePath;
	protected String gateinDefaultGroupsFilePath;
	protected String gateinNodesFilePath;
	//Social
	protected String spaceUIFilePath;
	protected String spaceVisibleFilePath;
	protected String spaceRegistrationFilePath;
	protected String spaceappFilePath;
	protected String spaceNavigationDefaultNodesFilePath;
	protected String spaceGroupsFilePath;
	protected String spaceWarnMessageFilePath;

	//Calendar
	protected String calGroupNameFilePath;
	protected String calTabNameFilePath;
	protected String calCommentsFilePath;
	protected String calRemoteFilePath;
	
	//Task Management
	protected String colorNamefilePath;


	protected String notiIntranetFilePath;
	protected String notiEmailFilePath;
	protected String actCommentFilePath;
	protected String notiCatFilePath;
	protected String notiFormatEmailFilePath;
	protected String notiLabelFilePath;
	protected String notiMessageFilePath;
	
	//Wiki
	protected String imageLinksFilePath;
	protected String wikiTemplateFilePath;
	protected String wikiMessageFilePath;
	protected String wikiRichTextFilePath;
	protected String wikiWarningsFilePath;
	protected String sourceTextEffectFilePath;
	protected String wikiMacroFilePath;
	
	//Permission
	protected String permisGroupFilePath;
	protected String permisMemFilePath;
	

	protected static String ssoType;

	/*========Default System Property=============*/
	public final String DEFAULT_SSOTYPE="";
	public final String DEFAULT_NATIVE_EVENT = "true";
	public final String DEFAULT_BASEURL="http://localhost:8080/portal";
	public final String DEFAULT_BROWSER="firefox";//iexplorer, firefox, chrome
	public final String DEFAULT_SERVER="ubuntu"; //win, ubuntu

	public final  Boolean DEFAULT_ISRANDOM = true;
	public final  Boolean DEFAULT_ISUSEFILE = true;

	public final  String DEFAULT_JDBCDRIVER = "com.mysql.jdbc.Driver";
	public final  String DEFAULT_DBURL = "jdbc:mysql://localhost:3306/selenium";
	public final  String DEFAULT_USERMYSQL = "root";
	public final  String DEFAULT_USERPASS = "exo";

	public final  String DEFAULT_SQLWIKI = "select * from wiki order by id asc";
	public final  String DEFAULT_SQLATTACHMENT = "select * from space order by id asc";
	public final  String DEFAULT_SQLUSER = "select type,username,password,email from user order by id asc";
	public final  String DEFAULT_SQLCONTENT = "select * from textbox order by id asc";

	public final String DEFAULT_SHEET="sheet1";
	public final String DEFAULT_USERFILEURL="DataDriven/" + "user.xls";
	public final String DEFAULT_ATTACHMENTFILEURL="DataDriven/" + "attachment_file.xls";
	public final String DEFAULT_TEXTBOXFILEURL="DataDriven/" + "textbox.xls";
	public final String DEFAULT_WIKITEMPLATEFILEURL="DataDriven/" + "wiki_template.xls";


	public final String DEFAULT_PERMISSIONURL="DataDriven/"+"permission.xls";
	public final String DEFAULT_SITEEXPLORERDRIVE="DataDriven/" + "se_drive.xls";
	public final String DEFAULT_SITEEXPLORERPATH="DataDriven/" + "se_path.xls";
	public final String DEFAULT_DATATESTPATH="DataDriven/" + "dataTest_folder_fath.xls";
	public final String DEFAULT_SITE_EXPLORER_CONTENT_TYPE_PATH="DataDriven/"+"se_content_types.xls";
	public final String DEFAULT_VIDEO_EMBBED_LINKS_PATH = "DataDriven/"+"video_embbed_links.xls";

	public final String DEFAULT_CONTACTIMURL="DataDriven/"+"contact_im.xls";
	public final String DEFAULT_CONTACTPHONEURL="DataDriven/"+"contact_phone.xls";
	public final String DEFAULT_ACTIVITYMESSAGEURL ="DataDriven/"+"activity_message.xls";
	public final String DEFAULT_CONNECTSTATUSURL ="DataDriven/"+"connect_status.xls";

	public final String DEFAULT_WIKIRICHTEXTFILEURL="DataDriven/" + "wiki_richtext.xls";
	public final String DEFAULT_CHANGELANGUADATAURL="DataDriven/" + "ChangeLanguage.xls";
	public final String DEFAULT_REMOTEGADGETURL="DataDriven/"+"remote_gadget_links.xls";
	public final String DEFAULT_APPGATEINURL="DataDriven/"+"application_gatein.xls";
	public final String DEFAULT_GETTINGSTARTEDURL="DataDriven/"+"getting_started.xls";
	public final String DEFAULT_WIKIMESSAGEURL = "DataDriven/"+"wiki_message.xls";

	public final String DEFAULT_GADGETURL = "DataDriven/"+"gatein_gadget.xls";
	public final String DEFAULT_CONTAINERURL="DataDriven/"+"containers_layout.xls";
	public final String DEFAULT_LANGUAGEURL="DataDriven/"+"language.xls";
	public final String DEFAULT_SELECTPATHURL="DataDriven/"+"selectPath.xls";
	public final String DEFAULT_MEMBERSHIPURL="DataDriven/"+"membership.xls";

	public final String DEFAULT_APPLAYOUTURL="DataDriven/"+"applications_layout.xls";

	public final String DEFAULT_APPLISTGETINURL="DataDriven/"+"gatein_applications_list.xls";
	public final String DEFAULT_APPADDGATEINURL="DataDriven/"+"gatein_applications_add.xls";
	public final String DEFAULT_CREATENEWGATEINURL="DataDriven/"+"create_new_gatein.xls";
	public final String DEFAULT_CATEGORIGATEINURL="DataDriven/"+"gatein_categories.xls";
	public final String DEFAULT_PAGE_MANAGEMENT_LIST_URL="DataDriven/"+"gatein_page_management_list.xls";
	public final String DEFAULT_PORTAL_DEFAULT_GATEIN_URL="DataDriven/"+"gatein_portal_defaults.xls";
	public final String DEFAULT_PORTAL_PERMISSION_GROUP_URL="DataDriven/"+"gatein_portal_groups.xls";
	public final String DEFAULT_PORTAL_PERMISSION_MEMBERSHIPS_URL="DataDriven/"+"gatein_portal_memberships.xls";

	public final String DEFAULT_USERINFOURL="DataDriven/"+"userinfo.xls";
	public final String DEFAULT_MAILSUFFIXURL="DataDriven/"+"mail_suffix.xls";
	public final String DEFAULT_USERSEARCHOPTIONURL="DataDriven/"+"gatein_user_search_options.xls";

	public final String DEFAULT_GATEINDEFAULTGROUPSURL="DataDriven/"+"gatein_default_groups.xls";
	public final String DEFAULT_GATEINNODESURL="DataDriven/"+"gatein_nodes.xls";

	public final String DEFAULT_LINKSURL="DataDriven/"+"links.xls";
	public final String DEFAULT_GROUPNAMEURL="DataDriven/"+"gatein_group_name.xls";

	public final String DEFAULT_CHATSTATUS_URL = "DataDriven/"+"chat_status.xls";
	

	//Social
	public final String DEFAULT_SPACE_UI_URL ="DataDriven/"+"space_GUI.xls";
	public final String DEFAULT_SPACEVISIBLEFILEURL="DataDriven/" + "space_visibility.xls";
	public final String DEFAULT_SPACEREGISTRATIONFILEURL="DataDriven/" + "space_registration.xls";
	public final String DEFAULT_SPACEAPPLICATIONURL="DataDriven/"+"space_application.xls";
	public final String DEFAULT_SPACE_NAVIGATION_DEFAULT_NODES_URL = "DataDriven/"+"space_navigation_default_nodes.xls";
	public final String DEFAULT_SPACE_GROUPS_URL="DataDriven/"+"space_groups.xls";
	public final String DEFAULT_SPACE_WARNING_MESSAGE_URL="DataDriven/"+"space_warning_message.xls";
	public final String DEFAULT_NOTIFICATION_INTRANET_URL = "DataDriven/"+"soc_notification_intranet.xls";
	public final String DEFAULT_NOTIFICATION_EMAIL_URL = "DataDriven/"+"soc_notification_email.xls";
	public final String DEFAULT_ACTIVITY_COMMENT_URL="DataDriven/"+"activity_comment.xls";
	public final String DEFAULT_NOTIFICATION_CATEGORY_URL="DataDriven/"+"soc_notification_category.xls";
	public final String DEFAULT_NOTIFICATION_FORMAT_EMAIL_URL="DataDriven/"+"soc_notification_format_email.xls";
	public final String DEFAULT_NOTIFICATION_LABLE_URL="DataDriven/"+"soc_notification_label.xls";
	public final String DEFAULT_NOTIFICATION_MESSAGE_URL="DataDriven/"+"soc_notification_message.xls";

	//Calendar
	public final String DEFAULT_CALENDAR_GROUP_NAME_URL="DataDriven/"+"cal_group.xls";
	public final String DEFAULT_CALENDAR_TAB_NAME_URL="DataDriven/"+"cal_tabs.xls";
	public final String DEFAULT_CALENDAR_COMMENTS_URL="DataDriven/"+"cal_comments.xls";
	public final String DEFAULT_CALENDAR_REMOTE_URL="DataDriven/"+"cal_remote.xls";

	//Task Management
	public final String DEFAULT_COLOR_FILE_URL="DataDriven/"+"color.xls";
	
	//Wiki
	public final String DEFAULT_WIKI_IMAGE_LINKS_FILE_URL="DataDriven/"+"wiki_image_links.xls";
	public final String DEFAULT_WIKI_WARNING_FILE_URL="DataDriven/"+"wiki_warnings.xls";
	public final String DEFAULT_SOURCE_TEXT_EFFECT ="DataDriven/"+"wiki_sourceEditor_effects.xls";
	public final String DEFAULT_WIKI_MACRO_FILE_URL="DataDriven/"+"wiki_macro.xls";
	//Permission
	public final String DEFAULT_PERMISSION_GROUP_FILE_URL="DataDriven/"+"permission_groups.xls";
	public final String DEFAULT_PERMISSION_MEMBERSHIP_FILE_URL="DataDriven/"+"permission_memberships.xls";
	
	/*======= Welcome Screen (Term and Conditions)   =====*/
	public final By ELEMENT_REGISTER_SKIP_BUTTON=By.xpath(".//*[@id='UIPortalLoginFormAction']/input[contains(@value,'Skip')]");
	public final By ELEMENT_REGISTER_YOUR_SOFTWARE_BUTTON=By.xpath(".//*[@id='UIPortalLoginFormAction']/a");
	/*======= Register Screen =====*/
	public final By ELEMENT_REGISTER_SKIP_BTN = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@name='btnSkip']");
	
	/*======= Welcome Screen (Term and Conditions) =====*/
	public final By ELEMENT_FIRSTNAME_ACCOUNT = By.name("firstNameAccount");
	public final By ELEMENT_LASTNAME_ACCOUNT = By.name("lastNameAccount");
	public final By ELEMENT_EMAIL_ACCOUNT = By.name("emailAccount");
	public final By ELEMENT_CONFIRM_PASS_ACCOUNT = By.name("confirmUserPasswordAccount");
	public final By ELEMENT_ROOT_PASS_ACCOUNT = By.name("adminPassword");
	public final By ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT = By.name("confirmAdminPassword");
	public final By ELEMENT_AGREEMENT_CHECKBOX = By.xpath("//*[@id = 'agreement']");
	public final By ELEMENT_INPUT_USERNAME = By.name("username");
	public final String ELEMENT_INPUT_USERNAME_ID = "username";
	public final By ELEMENT_CONTINUE_BUTTON = By.xpath("//button[text()='Continue' and @class='btn active']");
	public final By ELEMENT_START_BUTTON = By.xpath("//button[text()='Start']");
	public final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");
	public final By ELEMENT_INPUT_PASSWORD = By.name("password");
	public final String ELEMENT_INPUT_PASSWORD_ID="UIPortalLoginFormControl";
	public final By ELEMENT_ACCOUNT_NAME_LINK = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a/img");
	public final By ELEMENT_PLF_INFORMATION = By.id("platformInfoDiv");

	public final String ELEMENT_TERM_CONDITION_BOX = "//div[@class='header' and text()='Terms and Conditions Agreement']/..";
	public final By ELEMENT_CONTINUE_BUTTON_DISABLE = By.xpath("//button[text()='Continue' and @class='btn inactive']");
	public final By ELEMENT_TERM_CONDITION_CONTENT = By.xpath("//div[@id='AccountSetup' and @class='content']");

	public final By ELEMENT_ACCOUNT_SETUP = By.xpath("//div[@class='header' and text()='Account Setup']");
	public final By ELEMENT_USER_ADMIN = By.id("adminFirstName");
	public final By ELEMENT_SKIP_BUTTON = By.xpath("//button[text()='Skip']");
	public final By ELEMENT_YOUR_ACCOUNT_LABEL = By.xpath("//h5[contains(text(), 'Create your account')]");
	public final By ELEMENT_ADMIN_PASS_LABEL = By.xpath("//h5[contains(text(), 'Admin Password')]");
	public final By ELEMENT_ACCOUNT_ERROR = By.xpath("//*[@class='accountSetupError']");

	//SSO Login with OpenAM
	public final By ELEMENT_INPUT_PASSWORD_OPENAM = By.name("IDToken2");
	public final By ELEMENT_INPUT_USERNAME_OPENAM = By.name("IDToken1");
	public final By ELEMENT_SIGN_IN_BUTTON_OPENAM = By.xpath("//*[@class='button primary' and @value='Log In']");

	//SSO Login with CAS
	public final By ELEMENT_INPUT_PASSWORD_CAS = By.id("password");
	public final By ELEMENT_INPUT_USERNAME_CAS = By.id("username");
	public final By ELEMENT_SIGN_IN_BUTTON_CAS = By.xpath(".//*[contains(@class,'btn-submit')]");

	//Upload file popup
	public final By ELEMENT_UPLOAD_SELECT_BUTTON = By.xpath("//*[@class='uploadButton']/*[@class='btn']");
	public final By ELEMENT_UPLOAD_POPUP_FILE = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Attach File']");
	public final By ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT = By.name("file");
	public final By ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON = By.xpath(".//*[@id='UIAttachFileForm']//button[text()='Save']");
	public final String ELEMENT_UPLOAD_POPUP_NAMEFILE = "//*[@class='fileNameLabel' and contains(text(),'${fileName}')]";

	public final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");
	
	
	/*======== End of Term and conditions =====*/	
	
	public final By ELEMENT_SKIP_REGISTER_BTN = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@value = 'Skip']");
	public final By ELEMENT_CONTINUE_BTN = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@value = 'Continue']");
	
	/**
	 * Get System Property
	 */
	public void getSystemProperty(){
		nativeEvent = System.getProperty("nativeEvent");
		browser = System.getProperty("browser");
		server = System.getProperty("server");
		baseUrl = System.getProperty("baseUrl");

		ssoType = System.getProperty("ssoType");

		jdbcDriver = System.getProperty("jdbcDriver");
		dbUrl = System.getProperty("dbUrl");
		user = System.getProperty("user");
		pass = System.getProperty("pass");
		sqlWiki = System.getProperty("sqlWiki");
		sqlAttach = System.getProperty("sqlAttach");
		sqlUser = System.getProperty("sqlUser");

		defaultSheet = System.getProperty("defaultSheet");

		userDataFilePath = System.getProperty("userDataFilePath");
		userInfoFilePath = System.getProperty("userInfoFilePath");
		mailSuffixFilePath = System.getProperty("mailSuffixFilePath");
		userSearchOptionFilePath = System.getProperty("userSearchOptionFilePath");
		wikiRichTextFilePath = System.getProperty("wikiRichTextFilePath");
		attachmentFilePath = System.getProperty("attachmentFilePath");
		texboxFilePath = System.getProperty("texboxFilePath");
		languageFilePath = System.getProperty("languageFilePath");
		permissionPath = System.getProperty("permissionPath");
		wikiTemplateFilePath = System.getProperty("wikiTemplateFilePath");

		changLangDataPath = System.getProperty("changLangDataPath");
		remoteGadgetDataFilePath = System.getProperty("remoteGadgetDataFilePath");
		appGateinDataFilePath = System.getProperty("appGateinDataFilePath");
		getStartFilePath = System.getProperty("getStartFilePath");
		wikiMessageFilePath = System.getProperty("wikiMessageFilePath");
		gateinDefaultGroupsFilePath = System.getProperty("gateinDefaultGroupsFilePath");
		gateinNodesFilePath = System.getProperty("gateinNodesFilePath");

		siteExpDrivePath=System.getProperty("siteExpDrivePath");
		siteExpPathPath=System.getProperty("siteExpPathPath");
		siteExpContentTypeFilePath = System.getProperty("siteExpContentTypeFilePath");

		dataTestFilePath = System.getProperty("dataTestPath");
		linkPath=System.getProperty("linkPath");

		gadgetFilePath = System.getProperty("gadgetFilePath");
		containerFilePath = System.getProperty("containerFilePath");
		appLayoutFilePath = System.getProperty("appLayoutFilePath");
		groupNameDataFilePath = System.getProperty("groupNameDataFilePath");
		appListGateinFilePath = System.getProperty("appListGateinFilePath");
		appAddGateinFilePath = System.getProperty("appAddGateinFilePath");
		createNewGateinFilePath =System.getProperty("createNewGateinFilePath");
		categoriesGateinFilePath = System.getProperty("categoriesGateinFilePath");
		pageMagListFilePath = System.getProperty("pageMagListFilePath");
		portalDefaultFilePath = System.getProperty("portalDefaultFilePath");
		portalPermisGroupFilePath = System.getProperty("portalPermisGroupFilePath");
		portalPermisMemFilePath = System.getProperty("portalPermisMemFilePath");
		videoLinksFilePath = System.getProperty("videoLinksFilePath");
		conStatusFilePath = System.getProperty("conStatusFilePath");
		contactIMFilePath = System.getProperty("contactIMFilePath");
		contactPhoneFilePath = System.getProperty("contactPhoneFilePath");
		activityMesFilePath = System.getProperty("activityMesFilePath");
		chatStatusFilePath = System.getProperty("chatStatusFilePath");
		
		//Social
		spaceUIFilePath = System.getProperty("spaceUIFilePath");
		spaceVisibleFilePath = System.getProperty("spaceVisibleFilePath");
		spaceRegistrationFilePath = System.getProperty("spaceRegistrationFilePath");
		spaceappFilePath=System.getProperty("spaceappFilePath");
		spaceGroupsFilePath = System.getProperty("spaceGroupsFilePath");
		spaceWarnMessageFilePath = System.getProperty("spaceWarnMessageFilePath");
		notiEmailFilePath = System.getProperty("notiEmailFilePath");
		notiIntranetFilePath = System.getProperty("notiIntranetFilePath");
		spaceNavigationDefaultNodesFilePath = System.getProperty("spaceNavigationDefaultNodesFilePath");
		actCommentFilePath = System.getProperty("actCommentFilePath");
		notiCatFilePath = System.getProperty("notiCatFilePath");
		notiFormatEmailFilePath = System.getProperty("notiFormatEmailFilePath");
		notiLabelFilePath = System.getProperty("notiLabelFilePath");
		notiMessageFilePath = System.getProperty("notiMessageFilePath");
		
		//Calendar
		calGroupNameFilePath = System.getProperty("calGroupNameFilePath");
		calTabNameFilePath =System.getProperty("calTabNameFilePath");
		calCommentsFilePath =System.getProperty("calCommentsFilePath");
		calRemoteFilePath = System.getProperty("calRemoteFilePath");
		
		//Task Management
		colorNamefilePath =System.getProperty("colorNamefilePath");
		
		//Wiki
		imageLinksFilePath = System.getProperty("imageLinksFilePath");
		wikiWarningsFilePath= System.getProperty("wikiWarningsFilePath");
		sourceTextEffectFilePath = System.getProperty("sourceTextEffectFilePath");
		wikiMacroFilePath = System.getProperty("wikiMacroFilePath");
		
		//Permission
		permisGroupFilePath = System.getProperty("permisGroupFilePath");
		permisMemFilePath = System.getProperty("permisMemFilePath");


		if (ssoType==null) ssoType = DEFAULT_SSOTYPE;

		if (nativeEvent==null) nativeEvent = DEFAULT_NATIVE_EVENT;
		if (browser==null) browser = DEFAULT_BROWSER;
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		if (server==null) server = DEFAULT_SERVER;

		if (isRandom==null) isRandom = DEFAULT_ISRANDOM;
		if (isUseFile==null) isUseFile = DEFAULT_ISUSEFILE;

		if (jdbcDriver==null) jdbcDriver = DEFAULT_JDBCDRIVER;
		if (dbUrl==null) dbUrl = DEFAULT_DBURL;
		if (user==null) user = DEFAULT_USERMYSQL;
		if (pass==null) pass = DEFAULT_USERPASS;

		if (sqlWiki==null) sqlWiki = DEFAULT_SQLWIKI;
		if (sqlAttach==null) sqlAttach = DEFAULT_SQLATTACHMENT;
		if (sqlUser==null) sqlUser = DEFAULT_SQLUSER;
		if (sqlContent==null) sqlContent = DEFAULT_SQLCONTENT;

		if (defaultSheet==null) defaultSheet = DEFAULT_SHEET;

		if (siteExpDrivePath==null) siteExpDrivePath = DEFAULT_SITEEXPLORERDRIVE;
		if (siteExpPathPath==null) siteExpPathPath = DEFAULT_SITEEXPLORERPATH;
		if (siteExpContentTypeFilePath==null) siteExpContentTypeFilePath = DEFAULT_SITE_EXPLORER_CONTENT_TYPE_PATH;
		if (dataTestFilePath==null) dataTestFilePath = DEFAULT_DATATESTPATH;

		if (userDataFilePath==null) userDataFilePath = DEFAULT_USERFILEURL;
		if (userInfoFilePath==null) userInfoFilePath = DEFAULT_USERINFOURL;
		if (mailSuffixFilePath==null) mailSuffixFilePath = DEFAULT_MAILSUFFIXURL;
		if (userSearchOptionFilePath==null) userSearchOptionFilePath = DEFAULT_USERSEARCHOPTIONURL;
		if (wikiRichTextFilePath==null) wikiRichTextFilePath = DEFAULT_WIKIRICHTEXTFILEURL;
		if (attachmentFilePath==null) attachmentFilePath = DEFAULT_ATTACHMENTFILEURL;
		if (wikiTemplateFilePath==null) wikiTemplateFilePath = DEFAULT_WIKITEMPLATEFILEURL;
		if (texboxFilePath==null) texboxFilePath = DEFAULT_TEXTBOXFILEURL;
		if (languageFilePath==null) languageFilePath = DEFAULT_LANGUAGEURL;
		if (permissionPath==null) permissionPath = DEFAULT_PERMISSIONURL;

		if (contactIMFilePath==null) contactIMFilePath= DEFAULT_CONTACTIMURL;
		if (contactPhoneFilePath==null) contactPhoneFilePath= DEFAULT_CONTACTPHONEURL;
		if (activityMesFilePath==null) activityMesFilePath= DEFAULT_ACTIVITYMESSAGEURL;
		if (conStatusFilePath==null) conStatusFilePath= DEFAULT_CONNECTSTATUSURL;
		if (changLangDataPath==null) changLangDataPath = DEFAULT_CHANGELANGUADATAURL;
		if (remoteGadgetDataFilePath==null) remoteGadgetDataFilePath = DEFAULT_REMOTEGADGETURL;
		if (appGateinDataFilePath==null) appGateinDataFilePath = DEFAULT_APPGATEINURL;
		if (getStartFilePath==null) getStartFilePath = DEFAULT_GETTINGSTARTEDURL;
		if (wikiMessageFilePath==null) wikiMessageFilePath = DEFAULT_WIKIMESSAGEURL;
		if (linkPath==null) linkPath = DEFAULT_LINKSURL;
		if (gateinDefaultGroupsFilePath==null) gateinDefaultGroupsFilePath = DEFAULT_GATEINDEFAULTGROUPSURL;
		if (gateinNodesFilePath==null) gateinNodesFilePath = DEFAULT_GATEINNODESURL;

		if (gadgetFilePath==null) gadgetFilePath = DEFAULT_GADGETURL;
		if (containerFilePath==null) containerFilePath = DEFAULT_CONTAINERURL;
		if (appLayoutFilePath==null) appLayoutFilePath = DEFAULT_APPLAYOUTURL;
		if (groupNameDataFilePath==null) groupNameDataFilePath = DEFAULT_GROUPNAMEURL;

		if (appListGateinFilePath==null) appListGateinFilePath = DEFAULT_APPLISTGETINURL;
		if (appAddGateinFilePath==null) appAddGateinFilePath = DEFAULT_APPADDGATEINURL;

		if (createNewGateinFilePath==null) createNewGateinFilePath = DEFAULT_CREATENEWGATEINURL;
		if (categoriesGateinFilePath==null) categoriesGateinFilePath = DEFAULT_CATEGORIGATEINURL;
		if (pageMagListFilePath==null) pageMagListFilePath = DEFAULT_PAGE_MANAGEMENT_LIST_URL;

		if (portalDefaultFilePath==null) portalDefaultFilePath = DEFAULT_PORTAL_DEFAULT_GATEIN_URL;
		if (portalPermisGroupFilePath==null) portalPermisGroupFilePath = DEFAULT_PORTAL_PERMISSION_GROUP_URL;
		if (portalPermisMemFilePath==null) portalPermisMemFilePath = DEFAULT_PORTAL_PERMISSION_MEMBERSHIPS_URL;
		if (videoLinksFilePath==null) videoLinksFilePath = DEFAULT_VIDEO_EMBBED_LINKS_PATH;
		if (chatStatusFilePath==null) chatStatusFilePath = DEFAULT_CHATSTATUS_URL;
		

		//Social
		if (spaceUIFilePath==null) spaceUIFilePath = DEFAULT_SPACE_UI_URL;
		if (spaceVisibleFilePath==null) spaceVisibleFilePath = DEFAULT_SPACEVISIBLEFILEURL;
		if (spaceRegistrationFilePath==null) spaceRegistrationFilePath = DEFAULT_SPACEREGISTRATIONFILEURL;
		if (spaceappFilePath==null) spaceappFilePath= DEFAULT_SPACEAPPLICATIONURL;
		if (spaceNavigationDefaultNodesFilePath == null) spaceNavigationDefaultNodesFilePath = DEFAULT_SPACE_NAVIGATION_DEFAULT_NODES_URL;
		if (spaceGroupsFilePath==null) spaceGroupsFilePath=DEFAULT_SPACE_GROUPS_URL;
		if (spaceWarnMessageFilePath==null) spaceWarnMessageFilePath=DEFAULT_SPACE_WARNING_MESSAGE_URL;
		if (notiEmailFilePath==null) notiEmailFilePath=DEFAULT_NOTIFICATION_EMAIL_URL;
		if (notiIntranetFilePath==null) notiIntranetFilePath = DEFAULT_NOTIFICATION_INTRANET_URL;
		if (actCommentFilePath==null) actCommentFilePath = DEFAULT_ACTIVITY_COMMENT_URL;
		if (notiCatFilePath==null) notiCatFilePath = DEFAULT_NOTIFICATION_CATEGORY_URL;
		if (notiFormatEmailFilePath==null) notiFormatEmailFilePath = DEFAULT_NOTIFICATION_FORMAT_EMAIL_URL;
		if (notiLabelFilePath==null) notiLabelFilePath= DEFAULT_NOTIFICATION_LABLE_URL;
		if (notiMessageFilePath==null) notiMessageFilePath = DEFAULT_NOTIFICATION_MESSAGE_URL;
			
		//Calendar
		if (calGroupNameFilePath==null) calGroupNameFilePath=DEFAULT_CALENDAR_GROUP_NAME_URL;
		if (calTabNameFilePath==null) calTabNameFilePath=DEFAULT_CALENDAR_TAB_NAME_URL;
		if (calCommentsFilePath==null) calCommentsFilePath=DEFAULT_CALENDAR_COMMENTS_URL;
		if (calRemoteFilePath==null) calRemoteFilePath=DEFAULT_CALENDAR_REMOTE_URL;
		
		//Task Management
		if (colorNamefilePath==null) colorNamefilePath=DEFAULT_COLOR_FILE_URL;
		
		//Wiki
		if (imageLinksFilePath==null) imageLinksFilePath=DEFAULT_WIKI_IMAGE_LINKS_FILE_URL;
		if (wikiWarningsFilePath==null) wikiWarningsFilePath=DEFAULT_WIKI_WARNING_FILE_URL;
		if (sourceTextEffectFilePath==null) sourceTextEffectFilePath=DEFAULT_SOURCE_TEXT_EFFECT;
		if (wikiMacroFilePath==null) wikiMacroFilePath=DEFAULT_WIKI_MACRO_FILE_URL;
		//Permission
		if (permisGroupFilePath==null) permisGroupFilePath=DEFAULT_PERMISSION_GROUP_FILE_URL;
		if (permisMemFilePath==null) permisMemFilePath=DEFAULT_PERMISSION_MEMBERSHIP_FILE_URL;

		userDataFilePath = getAbsoluteFilePath(userDataFilePath);
		userInfoFilePath = getAbsoluteFilePath(userInfoFilePath);
		mailSuffixFilePath = getAbsoluteFilePath(mailSuffixFilePath);
		userSearchOptionFilePath = getAbsoluteFilePath(userSearchOptionFilePath);
		wikiRichTextFilePath = getAbsoluteFilePath(wikiRichTextFilePath);
		attachmentFilePath = getAbsoluteFilePath(attachmentFilePath);
		texboxFilePath = getAbsoluteFilePath(texboxFilePath);
		languageFilePath = getAbsoluteFilePath(languageFilePath);
		permissionPath = getAbsoluteFilePath(permissionPath);
		wikiTemplateFilePath = getAbsoluteFilePath(wikiTemplateFilePath);

		changLangDataPath = getAbsoluteFilePath(changLangDataPath);
		remoteGadgetDataFilePath = getAbsoluteFilePath(remoteGadgetDataFilePath);
		appGateinDataFilePath = getAbsoluteFilePath(appGateinDataFilePath);
		getStartFilePath = getAbsoluteFilePath(getStartFilePath);
		wikiMessageFilePath = getAbsoluteFilePath(wikiMessageFilePath);


		contactIMFilePath = getAbsoluteFilePath(contactIMFilePath);
		contactPhoneFilePath = getAbsoluteFilePath(contactPhoneFilePath);
		activityMesFilePath =getAbsoluteFilePath(activityMesFilePath);
		conStatusFilePath = getAbsoluteFilePath(conStatusFilePath);
		chatStatusFilePath = getAbsoluteFilePath(chatStatusFilePath);

		siteExpDrivePath = getAbsoluteFilePath(siteExpDrivePath);
		siteExpPathPath = getAbsoluteFilePath(siteExpPathPath);
		siteExpContentTypeFilePath = getAbsoluteFilePath(siteExpContentTypeFilePath);

		dataTestFilePath = getAbsoluteFilePath(dataTestFilePath);
		linkPath = getAbsoluteFilePath(linkPath);
		gateinDefaultGroupsFilePath = getAbsoluteFilePath(gateinDefaultGroupsFilePath);
		gateinNodesFilePath = getAbsoluteFilePath(gateinNodesFilePath);

		gadgetFilePath = getAbsoluteFilePath(gadgetFilePath);
		containerFilePath = getAbsoluteFilePath(containerFilePath);
		appLayoutFilePath = getAbsoluteFilePath(appLayoutFilePath);

		appListGateinFilePath = getAbsoluteFilePath(appListGateinFilePath);
		appAddGateinFilePath = getAbsoluteFilePath(appAddGateinFilePath);
		createNewGateinFilePath = getAbsoluteFilePath(createNewGateinFilePath);
		categoriesGateinFilePath = getAbsoluteFilePath(categoriesGateinFilePath);
		pageMagListFilePath = getAbsoluteFilePath(pageMagListFilePath);
		portalDefaultFilePath = getAbsoluteFilePath(portalDefaultFilePath);
		portalPermisGroupFilePath = getAbsoluteFilePath(portalPermisGroupFilePath);
		portalPermisMemFilePath = getAbsoluteFilePath(portalPermisMemFilePath);
		groupNameDataFilePath = getAbsoluteFilePath(groupNameDataFilePath);
		videoLinksFilePath = getAbsoluteFilePath(videoLinksFilePath);
		

		//social
		spaceUIFilePath = getAbsoluteFilePath(spaceUIFilePath);
		spaceappFilePath = getAbsoluteFilePath(spaceappFilePath);
		spaceVisibleFilePath = getAbsoluteFilePath(spaceVisibleFilePath);
		spaceRegistrationFilePath = getAbsoluteFilePath(spaceRegistrationFilePath);
		spaceNavigationDefaultNodesFilePath = getAbsoluteFilePath(spaceNavigationDefaultNodesFilePath);
		spaceGroupsFilePath = getAbsoluteFilePath(spaceGroupsFilePath);
		spaceWarnMessageFilePath = getAbsoluteFilePath(spaceWarnMessageFilePath);
		notiEmailFilePath = getAbsoluteFilePath(notiEmailFilePath);
		notiIntranetFilePath = getAbsoluteFilePath(notiIntranetFilePath);
		actCommentFilePath = getAbsoluteFilePath(actCommentFilePath);
		notiCatFilePath = getAbsoluteFilePath(notiCatFilePath);
		notiFormatEmailFilePath = getAbsoluteFilePath(notiFormatEmailFilePath);
		notiLabelFilePath = getAbsoluteFilePath(notiLabelFilePath);
		notiMessageFilePath = getAbsoluteFilePath(notiMessageFilePath);
		
		//Calendar
		calGroupNameFilePath = getAbsoluteFilePath(calGroupNameFilePath);
		calTabNameFilePath =getAbsoluteFilePath(calTabNameFilePath);
		calCommentsFilePath=getAbsoluteFilePath(calCommentsFilePath);
		calRemoteFilePath = getAbsoluteFilePath(calRemoteFilePath);
		
		//Task Management
		colorNamefilePath = getAbsoluteFilePath(colorNamefilePath);
		
		//wIKI
		imageLinksFilePath = getAbsoluteFilePath(imageLinksFilePath);
		wikiWarningsFilePath = getAbsoluteFilePath(wikiWarningsFilePath);
		sourceTextEffectFilePath = getAbsoluteFilePath(sourceTextEffectFilePath);
		wikiMacroFilePath = getAbsoluteFilePath(wikiMacroFilePath);
		//Permission
		permisGroupFilePath = getAbsoluteFilePath(permisGroupFilePath);
		permisMemFilePath = getAbsoluteFilePath(permisMemFilePath);
	}

	/**
	 * Init Chrome driver
	 */
	public ChromeDriver initChromeDriver(){
		info("Init chrome driver");
		getSystemProperty();
		String pathFile="";
		String fs = File.separator;
		String temp=System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";;
		pathFile=temp.replace("/", fs).replace("\\", fs);

		if(server.contains("ubuntu")){
			System.setProperty("webdriver.chrome.driver",chromeDriverUbuntu) ;
		}
		else if(server.contains("win")){
			System.setProperty("webdriver.chrome.driver",chromeDriver) ;
		}
		else{
			System.setProperty("webdriver.chrome.driver",chromeDriverUbuntu) ;
		}

		// Add the WebDriver proxy capability.
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		String[] switches = {"start-maximized","remote-debugging-port=9222"};
		capabilities.setCapability("chrome.switches", switches);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", pathFile);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(capabilities);
	}

	/**
	 * Init IE driver
	 */
	public WebDriver initIEDriver(){
		info("Init IE driver");
		System.setProperty("webdriver.ie.driver",ieDriver) ;
		DesiredCapabilities  capabilitiesIE = DesiredCapabilities.internetExplorer();
		capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
		if ("true".equals(nativeEvent)){
			info("Set nativeEvent is TRUE");
			capabilitiesIE.setCapability("nativeEvents", true);
		}
		else{
			info("Set nativeEvent is FALSE");
			capabilitiesIE.setCapability("nativeEvents", false);
		}
		capabilitiesIE.setCapability("javascriptEnabled", true);
		capabilitiesIE.setCapability("requireWindowFocus", true);
		capabilitiesIE.setCapability("enablePersistentHover", false);
		capabilitiesIE.setCapability("ignoreZoomSetting", true);
		capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilitiesIE.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
		return new InternetExplorerDriver(capabilitiesIE);
	}

	/**
	 * 
	 * Init FF driver
	 */
	public WebDriver initFFDriver(){
		String pathFile="";
		if ("win".equals(server)){
			pathFile = System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\TestOutput";
		}
		else{
			pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";
		}
		info("Init FF driver");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("plugins.hide_infobar_for_missing_plugin", true);
		profile.setPreference("dom.max_script_run_time", 0);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		info("Save file to " + pathFile);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", pathFile);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-xpinstall;" +
				"application/x-zip;application/x-zip-compressed;application/x-winzip;application/zip;" +
				"gzip/document;multipart/x-zip;application/x-gunzip;application/x-gzip;application/x-gzip-compressed;" +
				"application/x-bzip;application/gzipped;application/gzip-compressed;application/gzip" +
				"application/octet-stream" +
				";application/pdf;application/msword;text/plain;" +
				"application/octet;text/calendar;text/x-vcalendar;text/Calendar;" +
				"text/x-vCalendar;image/jpeg;image/jpg;image/jp_;application/jpg;" +
				"application/x-jpg;image/pjpeg;image/pipeg;image/vnd.swiftview-jpeg;image/x-xbitmap;image/png;application/xml;text/xml;text/icalendar;");

		profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
		profile.setPreference("pref.downloads.disable_button.edit_actions", true);
		profile.setPreference("pdfjs.disabled", true); 
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		return new FirefoxDriver(profile);
	}

	public void initSeleniumTestWithOutTermAndCondition(Object... opParams){

		getSystemProperty();
		if("chrome".equals(browser)){
			driver = initChromeDriver();
			chromeFlag = true;
		} else if ("iexplorer".equals(browser)){
			driver = initIEDriver();
			ieFlag = true;
		} else {
			driver = initFFDriver();
		}
		action = new Actions(driver);
	}

	/**
	 * init newDriver
	 */
	public void initNewDriver(){
		getSystemProperty();
		if("chrome".equals(browser)){
			newDriver = initChromeDriver();
		} else if ("iexplorer".equals(browser)){
			newDriver = initIEDriver();
			ieFlag = true;
		} else {
			newDriver = initFFDriver();
		}
		newDriver.manage().window().maximize();
	}
	/**
	 * Start new driver without cache
	 */
	public void startNewDriver(){
		getSystemProperty();
		if("chrome".equals(browser)){
			newDriver = new ChromeDriver();
		} else if ("iexplorer".equals(browser)){
			newDriver = new InternetExplorerDriver();
			ieFlag = true;
		} else {
			newDriver = new FirefoxDriver();
		}
		newDriver.manage().window().maximize();
		newDriver.navigate().refresh();
		newDriver.navigate().to(baseUrl);
	}
	
	/**
	 * Check term and conditions
	 * @param opParams
	 */
	public void termsAndConditions(Object... opParams){
		info("Term and conditions");
		Boolean isCreateAccount = (Boolean)(opParams.length>0 ? opParams[0]:true);
		ManageLogInOut acc = new ManageLogInOut(driver);
		driver.get(baseUrl);
		info("Agreement page");
		if (waitForAndGetElement(ELEMENT_REGISTER_SKIP_BTN, DEFAULT_TIMEOUT, 2) != null) {
			info("Skipp register");
			clickByJavascript(ELEMENT_REGISTER_SKIP_BTN, 2);
			Utils.pause(5000);
			}
		if (waitForAndGetElement(ELEMENT_AGREEMENT_CHECKBOX, 3000, 0, 2) != null) {
			info("-- Checking the terms and conditions agreement... --");
			click(ELEMENT_AGREEMENT_CHECKBOX, 2);
			click(ELEMENT_CONTINUE_BUTTON);
			waitForTextNotPresent("terms and conditions agreement");
			
			if(waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON,3000,0,2)!=null){
				info("-- Skipping register account--");
				click(ELEMENT_REGISTER_SKIP_BUTTON);
				waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
			}
			
		}else if(waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON,3000,0,2)!=null){
			info("-- Skipping register account--");
			info("Click on Continue button");
			if(waitForAndGetElement(ELEMENT_CONTINUE_BTN,3000,0,2)!=null){
				click(ELEMENT_CONTINUE_BTN);
				waitForElementNotPresent(ELEMENT_CONTINUE_BTN);
			}else{
				click(ELEMENT_REGISTER_SKIP_BUTTON);
				waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
			}
			
		}
		if (waitForAndGetElement(ELEMENT_ROOT_PASS_ACCOUNT, 3000, 0, 2) != null){
			info("-- Creating an Admin account: FQA... --");
			if(isCreateAccount==true){
				accountSetup();
				info("-- Administrator account (FQA) has been created successfully... --");
				driver.navigate().refresh();
				acc.signOut();
			}
		} 
		Utils.pause(3000);   
		info("End of term and conditions");
	}
	/**
	 * Create new first account
	 */
	public void accountSetupWithoutGreeting(){
		click(ELEMENT_INPUT_USERNAME);
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, "gtngtn", true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, "gtngtn", true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
		//click(ELEMENT_SUBMIT_BUTTON);
		clickByJavascript(ELEMENT_SUBMIT_BUTTON, 2);
		waitForTextNotPresent("Create your account");
	}

	/**
	 * Account setup
	 */
	public void accountSetup(){
		accountSetupWithoutGreeting();
		click(ELEMENT_START_BUTTON,0,true);
		waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK);
	}

	/**
	 * init browser
	 * @param opParams
	 */
	public void initSeleniumTest(Object... opParams){
		initSeleniumTestWithOutTermAndCondition();
		driver.manage().window().maximize();
		driver.navigate().refresh();
		termsAndConditions(opParams);
	}

	/**
	 * initNewIEBrowserWithNativeEvent
	 */
	public void initNewIEBrowserWithNativeEvent(){
		info("initNewIEBrowserWithNativeEvent");
		System.setProperty("webdriver.ie.driver",ieDriver) ;
		DesiredCapabilities  capabilitiesIE = DesiredCapabilities.internetExplorer();
		capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
		capabilitiesIE.setCapability("nativeEvents", true);
		capabilitiesIE.setCapability("ignoreZoomSetting", true);
		capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
		newDriver = new InternetExplorerDriver(capabilitiesIE);
	}

	/**
	 * Get element
	 * @param locator
	 * @param opParams
	 * @return an element
	 */
	public WebElement getElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
		WebDriver wDriver;
		if(isDriver)
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);
		else
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: newDriver);
		WebElement elem = null;
		try {
			elem = wDriver.findElement(by);
		} catch (NoSuchElementException e) {

		}
		return elem;
	}

	/**
	 * get an element
	 * @param locator
	 * @param opParams
	 * @return element
	 */
	public WebElement getDisplayedElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
		WebDriver wDriver;
		if(isDriver)
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);
		else
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: newDriver);
		WebElement e = null;
		try {
			if(by != null)
				e = wDriver.findElement(by);
			if (e != null){
				if (isDisplay(by)) return e;
			}
		} catch (NoSuchElementException ex) {
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		}
		finally{
			loopCount=0;
		}
		return null;
	}

	/**
	 * verify element exists or not
	 * @param locator
	 * @return true if element exists
	 * 			false if element doesn't exist
	 */
	public boolean isElementPresent(Object locator) {
		return getElement(locator) != null;
	}

	/**
	 * verify element exists or not
	 * @param locator
	 * @return true if element doesn't exists
	 * 			false if element exist
	 */
	public boolean isElementNotPresent(Object locator) {
		return !isElementPresent(locator);
	}

	/**
	 * Get element
	 * @param locator
	 * 					locator of element
	 * @param opParams
	 * 					opPram[0]: timeout
	 * 					opPram[1]: 0,1
	 * 					0: No Assert
	 * 					1: Assert
	 * @return an element
	 */
	public WebElement waitForAndGetElement(Object locator, Object... opParams) {
		WebElement elem = null;
		int timeout = (Integer) (opParams.length>0 ? opParams[0] : DEFAULT_TIMEOUT);
		int isAssert = (Integer) (opParams.length > 1 ? opParams[1]: 1);
		int notDisplayE = (Integer) (opParams.length > 2 ? opParams[2]: 0);
		WebDriver wDriver;
		if(isDriver)
			wDriver = (WebDriver) (opParams.length > 3 ? opParams[3]: driver);
		else
			wDriver = (WebDriver) (opParams.length > 3 ? opParams[3]: newDriver);
		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator,wDriver);
			}else{
				elem = getDisplayedElement(locator,wDriver);
			}
			if (null != elem) return elem;
			Utils.pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		info("cannot find element after " + timeout/1000 + "s.");
		return null;
	}

	/**
	 * Get element
	 * @param locator
	 * 					locator of element
	 * @param opParams
	 * 					opPram[0]: timeout
	 * 					opPram[1]: 0,1
	 * 					0: No Assert
	 * 					1: Assert
	 * @return	an element
	 */
	public WebElement waitForElementNotPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;
		int notDisplayE = opParams.length > 2 ? opParams[2]: 0;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator);
				//elem = getDisplayedElement(locator);
			}else{
				elem = getDisplayedElement(locator);
			}
			if (null == elem) return null;
			Utils.pause(WAIT_INTERVAL);
		}

		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
		info("Element doesn't disappear after " + timeout/1000 + "s.");
		return elem;
	}

	/**
	 * 
	 * @param text
	 * @param opts
	 * @return 	true if text exist
	 * 			false if test is not exist
	 */
	public boolean isTextPresent(String text, int...opts) {
		int display = opts.length > 0 ? opts[0] : 1;
		Utils.pause(500);
		String allVisibleTexts = getText(By.xpath("//body"),display);
		return allVisibleTexts.contains(text);
	}

	/**
	 * get text of element
	 * @param locator
	 * @param opts
	 * @return text of element
	 */
	public String getText(Object locator,int...opts) {
		WebElement element = null;
		int display = opts.length > 0 ? opts[0] : 1;
		try {
			element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get list of element
	 * @param xpath
	 * @return list of elements
	 */
	public List<WebElement> getElements(String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			return getElements(xpath);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * verify text exists or noet
	 * @param text
	 * @return true if text exists
	 * 			false if text doesn't exits
	 */
	public boolean isTextNotPresent(String text) {
		return !isTextPresent(text);
	}

	/**
	 * drag and drop element
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
		info("--Drag and drop to object--");
		Actions action = new Actions(driver);
		try {
			WebElement source = waitForAndGetElement(sourceLocator);
			WebElement target = waitForAndGetElement(targetLocator);

			action.dragAndDrop(source, target).build().perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			dragAndDropToObject(sourceLocator, targetLocator);
		}catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				switchToParentWindow();
			} catch (NoAlertPresentException eNoAlert) {
			}
		}

		finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}
	/**
	 * Drag an object
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void dragObject(String sourceLocator, String targetLocator){
		info("--Drag an object--");
		Actions action = new Actions(this.driver);
		WebElement source = waitForAndGetElement(sourceLocator);
		WebElement target = waitForAndGetElement(targetLocator);

		try {
			action.clickAndHold(source).moveToElement(target).release().build().perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			action.clickAndHold(source).moveToElement(target).release().build().perform();
		}catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				switchToParentWindow();
			} catch (NoAlertPresentException eNoAlert) {
			}
		}

		finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}
	/**
	 * Click by using javascript
	 * @param locator
	 * @param opParams
	 */
	public void clickByJavascript(Object locator, Object... opParams){
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);	
		WebElement e = null;
		if(locator instanceof WebElement){
			e=(WebElement) locator;
		}
		else{
			info("wait and get element");
			e = waitForAndGetElement(locator,DEFAULT_TIMEOUT, 1, notDisplay);
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", e);
	}
	/**
	 * Type by java script
	 * @param locator
	 * @param value
	 * @param opParams
	 */
	public void typeByJavascript(Object locatorById, String value,Object... opParams){
		Utils.pause(3000);
		((JavascriptExecutor)driver).executeScript("document.getElementById('"+locatorById+"').value='"+value+"'");
	}
	
	

	/**
	 * click action
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);	
		WebElement element = null;
		Actions actions = new Actions(driver);
		try {
			if(browser.contains("iexplorer")||browser.contains("chrome")){
				info("use javasript to click");
				clickByJavascript(locator, notDisplay);
			}
			else{
				if (!locator.getClass().getName().contains("WebElement")) {
					info("wait and get elements");
					element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
				}
				else{
					element=(WebElement) locator;
				}
				if (browser.contains("chrome")) {
					scrollToElement(element, driver);
				}
				if(element.isEnabled()){
					info("click element");
					actions.click(element).perform();
				}
				else {
					info("Element is not enabled");
					info("click element by javascript");
					clickByJavascript(locator, notDisplay);
				}
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			info("click element by javascript");
			clickByJavascript(locator, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			info("click element by javascript");
			clickByJavascript(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}


	/**
	 * clear cache
	 */
	public void clearCache(){
		Actions actionObject = new Actions(driver);
		try{
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch(WebDriverException e){	
			debug("Retrying clear cache...");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}
	}

	/**
	 * Use this function to verify if a check-box is checked (using when creating a portal/publicMode)
	 * @param locator
	 * @param opParams
	 */
	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
			if (browser.contains("chrome")) {
				scrollToElement(element, driver);
			}
			if (!element.isSelected()) {
				actions.click(element).perform();
				if(waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE).getAttribute("type")!=null && waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE).getAttribute("type")!="checkbox"){
					info("Checkbox is not checked");
					if (!element.isSelected()) {
						info("check by javascript");
						waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
						//mouseOver(locator, true);
						clickByJavascript(locator, notDisplayE);
					}
				}
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			check(locator, opParams);
		} finally {
			loopCount = 0;
		}
		Utils.pause(2000);
	}

	/**
	 * get value attribute
	 * @param locator
	 * @return value of element
	 */
	public String getValue(Object locator) {
		try {
			return waitForAndGetElement(locator).getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);			
			Utils.pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Mouse hover by Javascript
	 * @param locator
	 * @param opParams
	 */
	public void mouseHoverByJavaScript(Object locator, Object...opParams)
	{
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		WebElement targetElement;
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		targetElement = waitForAndGetElement(locator,5000, 1, notDisplay);
		((JavascriptExecutor)driver).executeScript(mouseOverScript, targetElement);
	}

	/**
	 * mouse over action
	 * @param locator
	 * @param safeToSERE
	 * @param opParams
	 */
	public void mouseOver(Object locator, boolean safeToSERE, Object...opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			if (safeToSERE) {
				for (int i = 1; i < ACTION_REPEAT; i++){
					if (!locator.getClass().getName().contains("WebElement")) {
						element = waitForAndGetElement(locator, 5000, 0, notDisplay);
					}
					else{
						element=(WebElement) locator;
					}
					if (element == null){
						Utils.pause(WAIT_INTERVAL);
					} else {
						actions.moveToElement(element).perform();
						break;
					}
				}
			} else {
				if (!locator.getClass().getName().contains("WebElement")) {
					element = waitForAndGetElement(locator);
				}
				else{
					element=(WebElement) locator;
				}
				actions.moveToElement(element).perform();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			mouseOver(locator, safeToSERE);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * mouse over and clic
	 * @param locator
	 */
	public void mouseOverAndClick(Object locator) {
		WebElement element;
		Actions actions = new Actions(driver);
		if (ieFlag) {
			element = getDisplayedElement(locator);
		} else {
			element = waitForAndGetElement(locator);
		}
		actions.moveToElement(element).click(element).build().perform();
	}

	/**
	 * wait for text present
	 * @param text
	 * @param opts
	 */
	public void waitForTextPresent(String text, int...opts) {
		int waitTime = opts.length > 0 ? opts[0] : DEFAULT_TIMEOUT;
		int display = opts.length > 1 ? opts[1] : 1;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextPresent: " + text);
			}
			if (isTextPresent(text,display)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * wait for text not present
	 * @param text
	 * @param wait
	 */
	public void waitForTextNotPresent(String text,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextNotPresent: " + text);
			}
			if (isTextNotPresent(text)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * wait for msg
	 * @param message
	 * @param wait
	 */
	public void waitForMessage(String message,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		Utils.pause(500);
		waitForAndGetElement("//*[contains(text(),'"+message+"')]",waitTime);
	}

	/**
	 * type to textbox
	 * @param locator
	 * @param value
	 * @param validate
	 * @param opParams
	 */
	public void type(Object locator, String value, boolean validate, Object...opParams) {	
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Timeout at type: " + value + " into " + locator);
				}
				WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);		
				if (element != null){
					if (validate) element.clear();
					element.click();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Repeat action..." + loop + "time(s)");
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Select option from combo box
	 * @param locator
	 * @param option
	 * @param display
	 */
	public void select(Object locator, String option, int...display) {
		int isDisplay = display.length > 0 ? display[0] : 1;
		try {
			for (int second = 0;; second++) {
				if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
					Assert.fail("Timeout at select: " + option + " into " + locator);
				}
				Select select = new Select(waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,isDisplay));
				select.selectByVisibleText(option);
				if (option.equals(select.getFirstSelectedOption().getText())) {
					break;
				}
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			select(locator, option);
		} finally {
			loopCount = 0;
		}
		Utils.pause(500);
	}

	/**
	 * un-check a checked-box
	 * @param locator
	 * @param opParams
	 */
	public void uncheck(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);

			if (element.isSelected()) {
				actions.click(element).perform();
				if (element.isSelected()) {
					info("uncheck by javascript");
					waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
					clickByJavascript(locator, notDisplayE);
				}
			} else {
				info("Element " + locator + " is already unchecked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			uncheck(locator, opParams);
		} finally {
			loopCount = 0;
		}
		Utils.pause(2000);
	}

	/**
	 * rightClickOnElement
	 * @param locator
	 * @param opParams
	 */
	public void rightClickOnElement(Object locator, int...opParams) {
		int display = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		Utils.pause(500);
		try {
			WebElement element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			actions.contextClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			rightClickOnElement(locator);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * doubleClickOnElement
	 * @param locator
	 */
	public void doubleClickOnElement(Object locator) {
		Actions actions = new Actions(driver);
		WebElement element;
		try {
			if (!locator.getClass().getName().contains("WebElement")) {
				element = waitForAndGetElement(locator);
			}
			else{
				element=(WebElement) locator;
			}
			actions.doubleClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			doubleClickOnElement(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * checkCycling
	 * @param e
	 * @param loopCountAllowed
	 */
	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Exception:" + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Cycled: " + e.getMessage());
		}
		info("Repeat... " + loopCount + "time(s)");
		loopCount++;
	}

	/**
	 * function to switch to parent windows
	 */
	public void switchToParentWindow (){
		try
		{
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent= null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1){
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			Utils.pause(1000);
		}
		catch (WebDriverException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * check element displays or net
	 * @param locator
	 * @return true if element displays
	 * 			false if element doesn't display
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try{
			if (e!=null)
				bool = e.isDisplayed();
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			isDisplay(locator);
		}
		finally{
			loopCount=0;
		}
		return bool;
	}

	/**
	 * function set driver to auto open new window when click link
	 */
	public void getDriverAutoOpenWindow(){
		FirefoxProfile fp = new FirefoxProfile();		
		fp.setPreference("browser.link.open_newwindow.restriction", 2);
		driver = new FirefoxDriver(fp);
		baseUrl = System.getProperty("baseUrl");
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		action = new Actions(driver);
		termsAndConditions();
	}

	/**
	 * function: check a file existed in folder
	 * @param file file name (eg: export.zip)
	 * @return true -> file exist
	 * false-> file is not exist
	 */
	public boolean checkFileExisted(String file){
		String pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/" + file;
		boolean found = false;

		if (new File(pathFile).isFile()){
			found = true;
		}
		info("File exists: " + file + " is " + found);
		return found;
	}

	/**
	 * function delete file in folder test output
	 * @param file file name
	 */
	public void deleteFile(String file){
		String fs = File.separator;
		String pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/" + file;
		pathFile=pathFile.replace("/", fs).replace("\\", fs);
		File Files = new File(pathFile);
		if(checkFileExisted(file)){
			Files.setWritable(true);
			Files.delete();
		}
		if (checkFileExisted(file) == false){
			info("Delete file successfully");
		}else info("Have error when delete file");
	}

	/**
	 * cutPasteFileFromOutputToTestData
	 * @param fileName
	 */
	public void cutPasteFileFromOutputToTestData(String fileName){
		String fs = File.separator;
		String source = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput/" + fileName;
		source=source.replace("/", fs);
		//directory where file will be copied
		String target = System.getProperty("user.dir") + "/src/main/resources/TestData/";
		target=target.replace("/", fs);

		//name of source file
		File sourceFile = new File(source);
		String name = sourceFile.getName();

		File targetFile = new File(target+name);

		//copy file from one location to other
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//delete file in TestOutput
		deleteFile("TestOutput/" + fileName);
	}

	/**
	 * 
	 * define language
	 *
	 */
	public enum Language{
		en, fr, vi, lo;
	}

	/**
	 * set language 
	 * @param language
	 */
	public void getDriverSetLanguage(Language language){
		String locale = language.toString();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", locale);
		driver = new FirefoxDriver(profile);
		baseUrl = System.getProperty("baseUrl");
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		action = new Actions(driver);
		termsAndConditions();
	}

	/**
	 * function get current Date of system follow a format
	 * @param format
	 * @return current Date of system
	 */
	public String getCurrentDate(String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return (dateFormat.format(date)); 
	}
	/**
	 * Get current date with time zone
	 * @param format
	 * @param local
	 * @return current Date with correct time zone
	 */
	public String getCurrentDate(String format,String local){
		DateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		df.setTimeZone(TimeZone.getTimeZone(local));
		System.out.println("Date and time in"+local+": " + df.format(date));
		return df.format(date);
	}

	/**
	 * Add 1 minute to current date time
	 * @param min
	 * @param format
	 * @return string minute
	 */
	public String addMinuteToCurrentDateTime(int min, String...format){
		DateFormat dateFormat = format.length > 0 ? new SimpleDateFormat(format[0]) : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, min);
		return (dateFormat.format(cal.getTime()));	
	}

	/** Get date in format "dd"
	 * @param gap distance from current date
	 * @return date in format "dd"
	 */
	public String getDate(int gap, String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, gap);
		info(dateFormat.format(cal.getTime()));
		return (dateFormat.format(cal.getTime()));	
	}
	/**
	 * Get date from firstDayOf Week
	 * @param gap
	 * @param format
	 * @return date in format
	 */
	public String getDateFromFirstDayOfWeek(int gap, String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		calendar.add(Calendar.DAY_OF_MONTH, gap);
		String getDateFromFirstDayOfWeek = dateFormat.format(calendar.getTime());
		info("getDateFromFirstDayOfWeek:"+getDateFromFirstDayOfWeek);
		return getDateFromFirstDayOfWeek;
	}

	/** Get day of week
	 * @param gap distance from current date
	 * @return day of week (monday, tuesday,..., sunday)
	 */
	public int getDayOfWeek(int gap){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, gap);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Get day of the next month
	 * @param format
	 * @return date
	 */
	public String getDayOfNextMonth(String format,int dayNum,int weekNum){
	   DateFormat dateFormat = new SimpleDateFormat(format);
	   Calendar calendar = Calendar.getInstance();
	   
	   Calendar nextMonth = Calendar.getInstance();
	   nextMonth.setTime(calendar.getTime());
	   nextMonth.add(Calendar.DAY_OF_WEEK_IN_MONTH,weekNum);
	   nextMonth.add(Calendar.DAY_OF_MONTH,dayNum);
	   String dayOfNextMonth= dateFormat.format( nextMonth.getTime());
	   System.out.println("dayOfNextMonth:"+dayOfNextMonth);
	   return dayOfNextMonth;
	 
	}
	/**
	 * Get the day of next year
	 * @param format
	 * @param year
	 * @return dayOfYear
	 */
	public String getDayOfNextYear(String format,int year){
	   DateFormat dateFormat = new SimpleDateFormat(format);
	   Calendar calendar = Calendar.getInstance();
	   
	   Calendar nextYear = Calendar.getInstance();
	   nextYear.setTime(calendar.getTime());
	   nextYear.add(Calendar.YEAR,year);
	   nextYear.add(Calendar.MONTH,12);
	   String dayOfYear= dateFormat.format(nextYear.getTime());
	   System.out.println("dayOfYear:"+dayOfYear);
	   return dayOfYear;
	}
	/**
	 * Get the day of next week
	 * @param format
	 * @return
	 */
	public String getDayOfNextWeek(String format){
	   DateFormat dateFormat = new SimpleDateFormat(format);
	   Calendar calendar = Calendar.getInstance();
	   String dayOfNextMonth1= dateFormat.format(  calendar.getTime());
	   System.out.println("dayOfNextMonth1:"+dayOfNextMonth1);
	   
	   Calendar nextWeek = Calendar.getInstance();
	   nextWeek.setTime(calendar.getTime());
	   nextWeek.add(Calendar.DATE,7);
	   String dayOfNextWeek= dateFormat.format(nextWeek.getTime());
	   return dayOfNextWeek;
		 
		}
	/**
	 * Get the number of current week
	 * @return weekNum
	 */
	public int getWeekNumber(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	
	public int getDayNumber(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DATE);
	}
	/**
	 * Get current month/day/year
	 * @param format as MMM for month, dd for day, or yyyy for year
	 * @return dateFormat.format(now.getTime())
	 */
	public String getCurrentMonthDayYear(String format){
		DateFormat dateFormat = new SimpleDateFormat (format);
		Calendar now = Calendar.getInstance();
		return dateFormat.format(now.getTime());
	}
	/**
	 * Get minute in format "mm" from current date
	 * @return minute
	 * 
	 */
	public int getMinute(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		int minute = cal.get(Calendar.MINUTE);
		return (minute); 
	}

	/**
	 * Change attribute "display" of HTML tag from "none" to "block" 
	 * @param locator
	 */
	public void changeDisplayAttributeHTML(Object locator){
		WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';",element);	
	}


	/**
	 * setPreferenceRunTime
	 */
	public void setPreferenceRunTime(){
		FirefoxProfile fp = new FirefoxProfile();

		fp.setPreference("dom.max_script_run_time", 30);
	}

	/** change lanugage of browser
	 * @param language
	 * English: "en"
	 * French: "fr"
	 */
	public void initFFBrowserWithSetLanguageBrowser(String language){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages",language);   
		driver = new FirefoxDriver(profile);
		baseUrl = System.getProperty("baseUrl");
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		action = new Actions(driver);
	}

	/**
	 * get random string
	 * @return random string
	 */
	public String getRandomString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * get a list of random numbers
	 * @return random numbers
	 */
	public String getRandomNumber() {
		char[] chars = "0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
	/**
	 * Create a String list by size
	 * @param name
	 *             is the name of array's members
	 * @param size
	 * @return value
	 */
	public ArrayList<String> getListData(String name,int size){
		ArrayList<String> array = new ArrayList<String>();
		for(int i=1;i<size;i++){
			String item = name+" "+String.valueOf(i);
			array.add(item);
		}
		return array;
	}

	/**
	 * Copy and paste a string from one locator to other
	 * 
	 * @param origin
	 * @param target
	 * @param value
	 */
	public void copyPasteString(By origin, By target, String value) {
		WebElement element1 = driver.findElement(origin);
		WebElement element2 = driver.findElement(target);

		info("Type into the first locator");
		element1.clear();
		element1.click();
		element1.sendKeys(value);

		info("Copy from the first locator");
		element1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element1.sendKeys(Keys.chord(Keys.CONTROL, "c"));

		info("Paste to the second locator");
		element2.click();
		element2.sendKeys(Keys.chord(Keys.CONTROL, "v"));
	}

	/**
	 * Get minute in format "HH" from current date
	 * @return hours
	 * 
	 */
	public int getHours(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		int minute = cal.get(Calendar.HOUR);
		return (minute); 
	}

	/**
	 * @param object
	 * @return = true: if there is not scroll bar on element
	 *         = false: if there is scroll bar on element
	 */
	public boolean checkExitScrollBar(By object){
		WebElement element = waitForAndGetElement(object);
		String scrollHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", element));
		String offsetHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].offsetHeight;", element));
		info("scrollHeight: " + scrollHeight);
		info("offsetHeight: " + offsetHeight);
		int scroll = Integer.parseInt(scrollHeight);
		int offset = Integer.parseInt(offsetHeight);
		return scroll == offset;
	}

	/**
	 * function get an element from link text when cannot get by text in xpath
	 * @param text
	 * @return an element from link text
	 */
	public WebElement getElementFromTextByJquery(String text){

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Utils.pause(2000);
		try{
			WebElement web = (WebElement) js.executeScript("return $(\"a:contains('" + text + "')\").get(0);");
			return web;
		}catch(org.openqa.selenium.WebDriverException e){
			WebElement web = (WebElement) js.executeScript("return $(\"a:contains('" + text + "')\").get(0);");
			return web;
		}
	}

	/**
	 * scrollBarToGetElement
	 * @param object
	 * @param opParams
	 */
	public void scrollBarToGetElement(By object, int...opParams) {
		int display = opParams.length > 0 ? opParams[0]: 0;
		WebElement element = waitForAndGetElement(object,5000,1,display);
		JavascriptExecutor jse;
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * inputDataToCKEditor
	 * @param framelocator
	 * @param data
	 */
	public void inputDataToCKEditor(By framelocator, String data){
		info("input data to ckeditor");
		Utils.pause(2000);
		try {
			WebElement inputsummary = null;
			WebElement e = waitForAndGetElement(framelocator,DEFAULT_TIMEOUT,1,2);
			driver.switchTo().frame(e);
			inputsummary = driver.switchTo().activeElement();
			inputsummary.click();
			inputsummary.clear();
			if ("iexplorer".equals(browser)){
				if ("true".equals(nativeEvent)){
					info("Set nativeEvent is TRUE");
					((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "' + document.body.innerHTML;");
				}
				else{
					info("Set nativeEvent is FALSE");
					//inputsummary.sendKeys(data);
					((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "' + document.body.innerHTML;");
				}
			} else {
				((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "' + document.body.innerHTML;");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			inputDataToCKEditor (framelocator, data);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			inputDataToCKEditor (framelocator,data);
		}catch (WebDriverException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			inputDataToCKEditor (framelocator,data);
		}
		switchToParentWindow();
	}
	/**
	 * Press Enter key
	 */
	public void pressEnterKey(){
		action.sendKeys(Keys.ENTER).perform();
		action.release();
	}
	/**
	 * Press End Key
	 * @param driver
	 */
	public void pressEndKey(WebDriver driver){
		info("Press End key");
		action = new Actions(driver);
		action.sendKeys(Keys.END).perform();
		action.release();
	}
	
	public void pressHomeKey(WebDriver driver){
		info("Press Home key");
		action = new Actions(driver);
		action.sendKeys(Keys.HOME).perform();
		action.release();
	}
	

	/**
	 * Import a Category
	 * 
	 * @param pathFile
	 * @param fileName
	 */
	public void importFile(String pathFile, String fileName) {
		info("Attach a file");
		click(ELEMENT_UPLOAD_SELECT_BUTTON);
		uploadFileUsingRobot(pathFile +"/"+ fileName);
		waitForAndGetElement(ELEMENT_UPLOAD_POPUP_NAMEFILE.replace("${fileName}", fileName));
		click(ELEMENT_SAVE_BTN);
		Utils.pause(2000);
	}

	/**
	 * This function returns a absolute path from a relative path
	 * @param relativeFilePath
	 * @return - FQA-2092: Run and check calendar sniff on IE and FF
	 */
	public String getAbsoluteFilePath(String relativeFilePath){
		String fs = File.separator;
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
		absolutePath=absolutePath.replace("/", fs).replace("\\", fs);;
		return absolutePath;
	}

	/**
	 * Get a File Content
	 * @param filePath
	 * @return fileContent
	 */
	public String getFileContent(String filePath){
		String path = getAbsoluteFilePath(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			error("Failed to find location of... " + filePath);
		}
		scanner = new Scanner(fis,"UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();		
		return inputStreamString;	
	}

	/**
	 * Get a file name from current Url
	 * @param driver
	 * @param params
	 * @return fileName
	 */
	public String getFileNameFromCurrentUrl(WebDriver driver, Object...params){
		Boolean extension = (Boolean) (params.length > 0 ? params[0] : false);

		String currentUrl = driver.getCurrentUrl();
		File file = new File(currentUrl);
		String fileNameWithExt = file.getName();

		if (extension){
			int position = fileNameWithExt.lastIndexOf(".");
			String fileNameWithOutExt = null;
			if (position >= 0) {
				fileNameWithOutExt = fileNameWithExt.substring(0, position);
			}else{
				fileNameWithOutExt = fileNameWithExt;
			}
			return fileNameWithOutExt;
		}else {
			return fileNameWithExt;
		}
	}

	/**
	 * Attach file in attach popup
	 * @param pathFile
	 * @param fileName
	 */
	public void attachFile(String pathFile, String fileName) {
		info("Attach a file");
		WebElement element = waitForAndGetElement(ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT, DEFAULT_TIMEOUT, 1,2);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", element);
		info("Get the file to attach");
		element.sendKeys(getAbsoluteFilePath(pathFile+fileName));
		info("Verify that the file is attached");
		waitForAndGetElement(ELEMENT_UPLOAD_POPUP_NAMEFILE.replace("${fileName}", fileName));
		info("The file is attached successfully");
		info("Click on Save button");
		click(ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Upload file using AutoIt
	 * @param file
	 */
	public void uploadFileUsingAutoIt(String file){
		info("Upload file using AutoIt");
		String fs = File.separator;
		String path=getAbsoluteFilePath("TestData\\attachFile.exe") + " " + getAbsoluteFilePath(file.replace("/", fs));
		try {
			info(path);
			Runtime.getRuntime().exec(path);
			info("done upload");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Download file using autoit
	 * @param file
	 */
	public void downloadFileUsingAutoIt(String file){
		info("Download file using AutoIt");
		String download = "TestData\\downloadIE9.exe";
		String fs = File.separator;
		String pathDownload = getAbsoluteFilePath(download);
		try {
			Process proc=Runtime.getRuntime().exec(pathDownload + " " + getAbsoluteFilePath("TestData" +fs + "TestOutput" + fs + file));
			InputStream is = proc.getInputStream();
			int retCode = 0;
			while(retCode != -1)
			{
				retCode = is.read();
				info("Now Exiting");
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Download file using Robot class
	 * @param element
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void downloadFileUsingRobot(WebElement element) throws AWTException, InterruptedException {
		info("Upload file using Robot");
		Robot robot = new Robot();

		// Get the focus on the element..don't use click since it stalls the driver         
		element.sendKeys("");

		//simulate pressing enter           
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Wait for the download manager to open           
		Utils.pause(2000);
		// Switch to download manager tray via Alt+N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);

		// Press S key to save           
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		Thread.sleep(2000);

		// Switch back to download manager tray via Alt+N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);

		// Tab to X exit key
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		// Press Enter to close the Download Manager
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * Download file using Robot class via URL download link
	 * @param element
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void downloadFileUsingRobotViaURL() throws AWTException, InterruptedException {
		info("Upload file using Robot");
		Robot robot = new Robot();

		//simulate pressing enter           
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Wait for the download manager to open           
		Utils.pause(2000);
		// Switch to download manager tray via Alt+N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);

		// Press S key to save           
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		Thread.sleep(2000);

		// Switch back to download manager tray via Alt+N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);

		// Tab to X exit key
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		// Press Enter to close the Download Manager
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	/**
	 * setClipboardData
	 * @param string
	 */
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * uploadFileUsingRobot
	 * @param fileLocation
	 */
	public void uploadFileUsingRobot(String fileLocation) {
		Utils.pause(3000);
		info("Upload file using Robot");
		String fs = File.separator;
		String path=getAbsoluteFilePath(fileLocation.replace("/", fs));
		info("path in uploadRobot:"+path);
		try {
			Robot robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_X);
			//Setting clipboard with file location
			setClipboardData(path);
			//native key strokes for CTRL, V and ENTER keys

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.pause(1000);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	/**
	 *  This function returns a absolute path from a relative path that get from excel file
	 * @param relativeFilePath
	 * @return absolutePath
	 */
	public static String getAbsoluteFilePathFromFile(String relativeFilePath) {
		String curDir = System.getProperty("user.home");
		String absolutePath = curDir + relativeFilePath;
		info("absolutePath:" + absolutePath);
		return absolutePath;
	}
	
	/**
	 * uploadFileUsingRobot using for Document preview
	 * @param fileLocation
	 */
	public void uploadFileUsingRobotDocumentPreview(String fileLocation) {
		Utils.pause(3000);
		info("Upload file using Robot");
		String fs = File.separator;
		//String path=getAbsoluteFilePath(fileLocation.replace("/", fs));
		String path=getAbsoluteFilePathFromFile(fileLocation.replace("/", fs));
		info("path in uploadRobot:"+path);
		try {
			Robot robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_X);
			//Setting clipboard with file location
			setClipboardData(path);
			//native key strokes for CTRL, V and ENTER keys

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.pause(1000);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	/**
	 * Get date by text format
	 * ex. Saturday, Febuary 16, 2015
	 * @param format
	 */
	public String getDateByTextFormat(String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		info(date);
		return date;
	}
	/**
	 * Get first day of week
	 * @param format
	 * @return firstDayOfWeek
	 */
	public String getFirstDayOfWeek(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		String firstDayOfWeek = dateFormat.format(calendar.getTime());
		info("firstDayOfWeek:"+firstDayOfWeek);
		return firstDayOfWeek;
	}

	/**
	 * Get last day of week
	 * @param format
	 * @return firstDayOfWeek
	 */
	public String getLastDayOfWeek(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar currentDate = Calendar.getInstance();
		int firstDayOfWeek = currentDate.getFirstDayOfWeek();

		Calendar startDate = Calendar.getInstance();
		startDate.setTime(currentDate.getTime());
		int days = (startDate.get(Calendar.DAY_OF_WEEK) + 7 - firstDayOfWeek) % 7;
		startDate.add(Calendar.DATE, -days);

		Calendar endDate = Calendar.getInstance();
		endDate.setTime(startDate.getTime());
		endDate.add(Calendar.DATE, 5);
		String lastDayOfWeek = dateFormat.format(endDate.getTime());
		info("lastDayOfWeek:"+lastDayOfWeek);
		return lastDayOfWeek;
	}
	/**
	 * Scroll to a element on the website
	 * @param element
	 * @param driver
	 */
	public static void scrollToElement(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	/**
	 * Scroll to bottom of the page of website
	 * @param driver
	 */
	public static void scrollToBottomPage(WebDriver driver){
		info("Scroll to the bottom of the page");
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," +
        "document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	/**
	 *This function will try to get an element. if after timeout, the element is not found.
	 *The function will refresh the page and find the element again.
	 * @param element
	 */
	public void waitElementAndTryGetElement(Object element,Boolean... isClicked){
		info("-- Starting finding element --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				if(waitForAndGetElement(element,3000,0)!=null);
				break;
			}
			if (waitForAndGetElement(element, 5000, 0) != null){
				info("Element "+element+" is displayed");
				if(isClicked.length>0 && isClicked[0]==true)
					click(element);
				break;
			}
			info("Retry...[" + repeat + "]");
			this.driver.navigate().refresh();
		}
		Utils.pause(2000);
		info("The elemnt is shown successfully");
	}
	
	/**
	 * Check if a checkbox is checked or not
	 * @Author: QuyenNT
	 * Date: Oct 30, 2015
	 */
	public boolean checkCheckBoxAttribute(String checkedElement){
		info("Check checkbox attribute");
		WebElement checkBox= waitForAndGetElement(checkedElement,2000,2,1);
		if (checkBox != null && !checkBox.isSelected()) {
			info("Checkbox is NOT selected");
			return false;
		} else if (checkBox != null && checkBox.isSelected()){
			info("Checkbox IS SELECTED");
			return true;
		}
		
		return false;
	}
}
