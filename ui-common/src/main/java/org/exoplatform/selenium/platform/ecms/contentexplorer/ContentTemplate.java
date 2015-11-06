package org.exoplatform.selenium.platform.ecms.contentexplorer;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.CKeditor;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContentTemplate extends EcmsBase {

	ActionBar actBar;
	Button button;
	ManageAlert magAlert;
	CKeditor ck;

	public ContentTemplate(WebDriver dr, String... plfVersion) {
		super(dr);
		ck = new CKeditor(dr);
		actBar = new ActionBar(dr);
		button = new Button(dr);
		magAlert = new ManageAlert(dr);
		this.plfVersion = plfVersion.length > 0 ? plfVersion[0] : "4.1";
	}

	// Button: Categories > Add Item
	public final By ELEMENT_CATEGORIES_ADD_ITEM = By.xpath("//*[contains(text(), 'Categories')]/..//*[@title='Add Item']");

	// Select language
	public final By ELEMENT_SELECT_LANGUAGE = By.xpath("//*[@id='UIDocumentForm']//select[@name='content-lang']");

	// Announcement
	public final By ELEMENT_ANNOUNCEMENT_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Announcement']");
	public final By ELEMENT_ANNOUNCEMENT_NAME_TEXTBOX = By.id("name");
	public final By ELEMENT_ANNOUNCEMENT_SUMMARY_FRAME = By.xpath("//*[@id='cke_exo:summary']//iframe");

	// WebLink
	public final By ELEMENT_WEBLINK_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Web Link']");

	// WebContent
	public final By ELEMENT_WEBCONTENT_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Web Content']");
	public final By ELEMENT_ILLUSTRATED_WEBCONTENT_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Illustrated Web Content']");
	public final String ELEMENT_WEBCONTENT_CONTENT_PARAGRAPH_FORMAT = ".//${format}[contains(text(),'${content}')]";
	public final String ELEMENT_WEBCONTENT_CONTENT_PARAGRAPH_FONT = ".//*[contains(@style,'font-family:${font}')][contains(text(),'${content}')]";
	public final String ELEMENT_WEBCONTENT_CONTENT_PARAGRAPH_FONT_SIZE_FONT_NAME = ".//*[contains(@style,'font-size:${fontSize}px;')]//*[contains(@style,'font-family: ${fontName}')][contains(text(),'${content}')]";

	public final String ELEMENT_WEBCONTENT_CONTENT_NAME = ".//*[@ data-original-title='${nameContent}']";
	public final String ELEMENT_WEBCONTENT_CONTENT_NAME_DOCUMENT_VIEW = ".//*[@id='UITabContent']//p[contains(text(),'${content}')]";
	public final String ELEMENT_WEBCONTENT_CONTENT_DOCUMENT_VIEW_AS_LINK = ".//*[@id='UITabContent']//*[contains(@href,'${url}')]";
	public final String ELEMENT_CONTENT_WEBCONTENT_ALIGHLEFT = ".//*[@id='UITabContent']/p[contains(text(),'${content}')]";
	public final String ELEMENT_CONTENT_WEBCONTENT_ALIGHRIGHT = ".//*[@id='UITabContent']/p[contains(@style,'text-align: right;')][contains(text(),'${content}')]";
	public final String ELEMENT_CONTENT_WEBCONTENT_ALIGHCENTER = ".//*[@id='UITabContent']/p[contains(@style,'text-align: center;')][contains(text(),'${content}')]";
	public final By ELEMENT_CONTENT_WEBCONTENT_JUSTIFY = By.xpath("//*[@id='UITabContent']/p[contains(@style,'text-align: justify;')]");
	public final String ELEMENT_CONTENT_WEBCONTENT_BULLET = ".//*[@id='UITabContent']/ul/li[contains(text(),'${content}')]";
	public final String ELEMENT_CONTENT_WEBCONTENT_NUMBERIC = ".//*[@id='UITabContent']/ol/li[contains(text(),'${content}')]";
	public final String ELEMENT_CONTENT_WEBCONTENT_PAINT_TEXT_COLOR = ".//*[@id='UITabContent']//span[contains(@style,'color:#${codeColor};')][contains(text(),'${content}')]";
	public final String ELEMENT_CONTENT_WEBCONTENT_PAINT_TEXT_BACKGROUND_COLOR = ".//*[@id='UITabContent']//span[contains(@style,'background-color:#${codeColor};')][contains(text(),'${content}')]";

	public final By ELEMENT_WEBCONTENT_TITLE_TEXTBOX = By.id("title0");
	public final By ELEMENT_WEBCONTENT_NAME_TEXTBOX = By.id("name");
	public final By ELEMENT_WEBCONTENT_CONTENT_FRAME = By.xpath("//*[contains(@id,'cke_contents_htmlData')]/iframe");
	public final By ELEMENT_WEBCONTENT_CONTENT_FRAME_41 = By.xpath("//*[@id= 'cke_1_contents']//iframe");
	public final By ELEMENT_WEBCONTNET_CONTENT_FRAME_2 = By.xpath("//iframe[contains(@class,'cke_wysiwyg_frame cke_reset')]");
	public final By ELEMENT_WEBCONTENT_ADD_CONTENT_LINK = By.xpath("//*[@title='Insert Content Link']");
	public final By ELEMENT_ILLUSTRATION_TAB = By.xpath("//*[contains(text(),'Illustration')]");
	public final By ELEMENT_WEBCONTENT_UPLOAD_FRAME = By.xpath("//*[contains(@name,'uploadIFrame')]");
	public final By ELEMENT_IMAGE_BUTTON_ADD = By.xpath("//*[@class='cke_button_icon cke_button__image_icon']");
	public final By ELEMENT_ADD_SIMPLE_LINK = By.xpath(".//*[@class='cke_button_icon cke_button__link_icon']");
	public final By ELEMENT_ADD_SPECIAL_CHAR = By.xpath(".//*[@class='cke_button_icon cke_button__specialchar_icon']");
	public final By ELEMENT_ADD_TABLE = By.xpath(".//*[@class='cke_button_icon cke_button__table_icon']");
	public final By ELEMENT_WEBCONTENT_SUMMARY_FRAME = By.xpath("//td[contains(@id,'cke_contents_htmlData')]/iframe");
	public final By ELEMENT_WEBCONTENT_SUMMARY_FRAME_41 = By.xpath("//*[@id='cke_exo:summary']//iframe");
	public final By ELEMENT_WEBCONTENT_ADVANCE_TAB = By.xpath("//*[contains(text(),'Advanced')]");
	public final By ELEMENT_WEBCONTENT_CSS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentCSS')]");
	public final By ELEMENT_WEBCONTENT_JS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentJS')]");
	public final By ELEMENT_MAXIMIZE_ICON = By.xpath("//*[contains(@class, 'uiIconEcmsExpand')]");
	public final By ELEMENT_MINIMIZE_ICON = By.xpath("//*[contains(@class, 'uiIconEcmsCollapse')]");
	public final By ELEMENT_CHANGE_CONTENT_TYPE = By.xpath("//*[contains(@class, 'changeTypeLink')]");
	public final By ELEMENT_UI_DOCUMENT_WORKSPACE = By.id("UIDocumentWorkspace");
	// Webcontent for CK Editor
	public final By ELEMENT_WEBCONTENT_CKEDITOR_FRAME1_41 = By.xpath("//*[@id='tab4']//iframe");
	public final By ELEMENT_WEBCONTENT_CKEDITOR_FRAME2_41 = By.xpath("//*[@id='cke_exo:summary']//iframe");
	//public final String ELEMENT_WEBCONTENT_CONTENT_BOLD_ITALIC_UNDERLINE_STRIKE = ".//p/strong/em/u/s[contains(text(),'${content}')]";
	public final String ELEMENT_WEBCONTENT_CONTENT_BOLD_ITALIC_UNDERLINE_STRIKE = ".//p/s/u/em/strong[contains(text(),'${content}')]";
	
	// Illus Webcontent for CK Editor
	public final By ELEMENT_ILLWEBCONTENT_CKEDITOR_FRAME1_41 = By.xpath("//*[@id='tab1']//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public final By ELEMENT_ILLWEBCONTENT_CKEDITOR_FRAME2_41 = By.xpath("//*[@id='cke_exo:summary']//iframe");
	// Accessible Media
	public final By ELEMENT_ACCESSIBLEMEDIA_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Accessible Media']");
	public final By ELEMENT_ACCESSIBLE_MEDIA_CKEDITOR_FRAME = By.xpath("//*[@id='cke_alternativeText']//iframe");
	/*
	 * Added by PhuongDT Date 30/08/2013
	 */
	// HTML File
	public final By ELEMENT_NEW_HTML_FILE_LINK = By.xpath("//*[@class='templateLabel']//*[text()='HTML File']");
	public final By ELEMENT_HTML_FILE_NAME = By.id("name");
	public final By ELEMENT_HTML_FILE_CONTENT = By.id("contentHtml");
	public final By ELEMENT_HTML_FILE_LANGUAGE = By.className("selectbox");
	public final By ELEMENT_HTML_FILE_CKEDITOR_FRAME = By.xpath("//*[@class='cke_contents']/iframe");
	public final String ELEMENT_UITAB_CONTENT_UEMSTRONG_HTMLFILE = ".//*[@id='UIDocumentContainer']//u/em/strong[contains(text(),'${content}')]";
	public final String ELEMENT_UITAB_CONTENT_HTMLFILE = ".//*[@id='UIDocumentContainer']//p[contains(text(),'${content}')]";

	public final By ELEMENT_MAIN_CKEDITOR_FRAME_1 = By.xpath("//*[@id='tab1']//iframe");
	public final By ELEMENT_SUMMARY_CKEDITOR_FRAME_2 = By.xpath("//*[@id='cke_exo:summary']//iframe");
	/* End Added */
	// Accessible Breadcrum

	public final By ELEMENT_HTML_FILE_CKEDITOR_FRAME_41 = By.xpath("//*[@id='cke_contentHtml']//iframe");
	public final By ELEMENT_HTML_FILE_CKEDITOR_FRAME_BODY = By.tagName("body");
	/* End Added */
	// Contact us document
	public final By ELEMENT_CONTACTUS_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Contact Us']");
	public final By ELEMENT_CONTACTUS_RECIPIENT = By.xpath("//*[@name = 'select_a_recipientFieldName']");
	public final By ELEMENT_CONTACTUS_LANGUAGE = By.xpath("//*[@name = 'content-lang']");
	public final By ELEMENT_CONTACTUS_YOURNAME = By.id("your_nameFieldName");
	public final By ELEMENT_CONTACTUS_YOURADDRESS = By.id("your_addressFieldName");
	public final By ELEMENT_CONTACTUS_EMAIL = By.id("your_email_addressFieldName");
	public final By ELEMENT_CONTACTUS_PHONE = By.id("your_phone_numberFieldName");
	public final By ELEMENT_CONTACTUS_MSG = By.id("your_messageFieldName");

	// Accessible Breadcrumb
	public final String ELEMENT_FILES_LINK = "//*[@class='templateLabel']//*[text()='${fileName}']";

	public final By ELEMENT_MAIN_TAB = By.xpath("//*[text()='Main Content']");
	public final By ELEMENT_MAIN_TAB_NAME = By.id("name");
	public final By ELEMENT_MAIN_TAB_TITLE = By.id("title");
	public final By ELEMENT_ACCESSIBLE_MEDIA_MAIN_TAB_TITLE = By.id("title0");
	public final By ELEMENT_MAIN_TAB_LANGUAGE = By.className("selectbox");

	public final String ELEMENT_UITAB_CONTENT = ".//*[@id='UIDocumentContainer']//*[contains(text(),'${content}')]";
	public final String ELEMETN_CONTENT_OF_WEBCONTENT_FILES = ".//*[contains(text(),'${content}')]";
	public final String ELEMENT_UITAB_CONTENT_UEMSTRONG = ".//*[@id='UIDocumentContainer']//p/u/em/strong[contains(text(),'${content}')]";

	// CSS File
	public final By ELEMENT_CSS_FILE_LINK = By.xpath("//*[@class='templateLabel']//*[text()='CSS File']");

	// JS File
	public final By ELEMENT_JS_FILE_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Javascript File']");

	/* End Added */

	// File
	public final By ELEMENT_NEWFILE_LINK = By.xpath("//*[@class='templateLabel']//*[text()='File']");
	public final By ELEMENT_FILE_CONTENT_FRAME = By.xpath(".//*[@id='myTabContent']//iframe[@class='ECMIframe']");
	public final By ELEMENT_CONTENT_FILE_BLOCKQUTE = By.xpath(".//blockquote/p");
	public final By ELEMENT_CONTENT_FILE_INSCREASE = By.xpath("//*[contains(@style,'margin-left: 40.0px;')]");
	public final By ELEMENT_CONTENT_FILE_DESCREASE = By.xpath("html/body/p");

	public final By ELEMENT_NEWFILE_NAME_TEXTBOX = By.id("name");

	public final By ELEMENT_NEWFILE_CONTENT_FRAME = By.xpath("//*[@id='cke_1_contents']//iframe");

	public final By ELEMENT_NEWFILE_CONTENT_FRAME_41 = By.xpath("//*[@id='cke_contentHtml']//iframe");
	public final By ELEMENT_NEWFILE_TITLE_TEXTBOX = By.id("title0");
	public final By ELEMENT_NEWFILE_DESCRIPTION_TEXTBOX = By.id("description0");
	public final By ELEMENT_NEWFILE_CREATOR_TEXTBOX = By.id("creator0");
	public final By ELEMENT_NEWFILE_SOURCE_TEXTBOX = By.id("source0");
	public final By ELEMENT_NEWFILE_SOURCE_LINK_XPATH = By.xpath("//*[@title='Source']");
	public final By ELEMENT_NEWFILE_SOURCE_TEXTAREA_XPATH = By.xpath("//div[@id='cke_1_contents']/textarea");
	public final By ELEMENT_NEWFILE_FRAME_HTML_TAB_XPATH = By.xpath("//iframe[@class='ECMIframe']");
	public final By ELEMENT_NEWFILE_HTML_TAB_P_XPATH = By.xpath("//html/body/p");
	public final By ELEMENT_NEWFILE_HTML_TAB_B_XPATH = By.xpath("//html/body/b");
	public final By ELEMENT_NEWFILE_HTML_TAB_B_XPATH_2 = By.xpath("html/body/p/b");
	public final By ELEMENT_NEWFILE_TEXT_TAB_XPATH = By.xpath("//a[contains(text(),'View as Plain text')]");

	// Text options
	public final By ELEMENT_CHOOSE_TEMPLATES = By.xpath(".//span[contains(text(),'Templates')]/..");
	public final By ELEMENT_VIEW_SOURCE = By.xpath("//*[@class='cke_button_label cke_button__source_label' and contains(text(),'Source')]");

	// templates
	public final By ELEMENT_TEMPLATE_IMAGE_AND_TITLE = By.xpath("//*[@class='cke_tpl_title' and contains(text(),'Image and Title')]");
	public final By ELEMENT_BUTTON_CANCEL_TEMPLATE_CHOOSE = By.xpath("//*[@class='cke_dialog_ui_button' and contains(text(),'Cancel')]");
	public final By ELEMENT_TEMPLATE_CHECK_TEXT_ON_DOCUMENT_VIEW_WITH_IMAGE_TITLE_TEMPLATE = By.xpath(".//*[@id='UIDocumentWorkspace']//*[contains(text(),'Type the title here')]/../p[contains(text(),'Type the text here')]");

	public final String ELEMENT_NEWFILE_TEXT_TAB_P_CSS = ".textContent>pre";
	public final By ELEMENT_NEWFILE_MIME_COMBOX_ID = By.name("mimetype");
	public final By ELEMENT_NEWFILE_TEXTAREA_ID = By.id("contentHtml");
	public final By ELEMENT_NEWFILE_TEXTPLAIN = By.id("contentPlain");
	public final String ELEMENT_NEWFILE_PRE_CSS = ".content>pre";
	public final By ELEMENT_DOWNLOAD_FILE = By.xpath(".//*[@id='UIDocumentContainer']//*[@class='uiIconDownload']");
	public final By ELEMENT_DOWLOAD_AS_PDF = By.xpath(".//*[@id='UIDocumentInfo_PDF_']//*[@class='DownLoadIcon']");
	public final By ELEMENT_ZOOM_IN_ICON = By.xpath(".//*[@id='UIDocumentInfo_PDF_']//*[@class='ZoomIn']");
	public final By ELEMENT_ROTATE_LEFT_ICON = By.xpath(".//*[@id='UIDocumentInfo_PDF_']//*[@class='RotateLeft']");
	public final By ELEMENT_INFO_ICON = By.xpath(".//*[@id='UIDocumentInfo_PDF_']//*[@class='InfoIcon']");

	// Picture on Head Layout
	// exo:pictureOnHeadWebContent is changed to Illustrated Web Content
	public final By ELEMENT_HEAD_LAYOUT_LINK = By.linkText("Illustrated Web Content");
	public final By ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX = By.id("name");
	public final By ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX = By.id("title");
	public final By ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final By ELEMENT_HEAD_LAYOUT_UPLOAD_FILE = By.id("file");

	// Product
	public final By ELEMENT_PRODUCT_LINK = By.linkText("Product");
	public final By ELEMENT_PRODUCT_NAME_TEXTBOX = By.id("name");
	public final String ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE = ".//p/u/em/strong[contains(text(),'${content}')]";
	public final By ELEMENT_PRODUCT_FILE_BENEFIT_TAB = By.xpath(".//*[contains(@href,'#tab-benefits')]");

	public final By ELEMENT_PRODUCT_ILLUSTRATION = By.xpath("//input[@name='illustration']");
	public final By ELEMENT_PRODUCT_SUMMARY_FRAME = By.xpath("//*[@id='cke_contents_summary']/iframe");
	public final By ELEMENT_PRODUCT_SUMMARY_FRAME_41 = By.xpath("//*[@id='cke_summary']//iframe");
	public final By ELEMENT_PRODUCT_BENEFIT_FRAME = By.xpath("//*[@id='cke_contents_productBenefits']/iframe");
	public final By ELEMENT_PRODUCT_BENEFIT_FRAME_41 = By.xpath("//*[@id='cke_productBenefits']//iframe");
	public final By ELEMENT_PRODUCT_FEATURE_FRAME = By.xpath("//*[@id='cke_contents_productFeatures']/iframe");
	public final By ELEMENT_PRODUCT_FEATURE_FRAME_41 = By.xpath("//*[@id='cke_productFeatures']//iframe");

	public final By ELEMENT_FREE_CONT_ACCEPT = By.xpath("//form[contains(@id,'EditTextForm')]/a[2]");
	public final By ELEMENT_FREE_CONT_INPUT = By.xpath("//iframe[contains(@title,'Rich text editor, newText')]");
	public final String ELEMENT_FREE_CONT_INLINE = "//div[contains(@id,'CurrentText') and @title='Double-click to edit']";

	public final By ELEMENT_SAMPLE_CONT_ACCEPT = By.xpath("//form[contains(@id,'EditContentForm')]/a[2]");
	public final By ELEMENT_SAMPLE_CONT_INPUT = By.xpath("//textarea[contains(@id,'newContent')]");
	public final String ELEMENT_SAMPLE_CONT_INLINE = "//div[contains(@id,'CurrentContent') and @title='Double-click to edit']";
	public final By ELEMENT_SAMPLE_SUM_ACCEPT = By.xpath("//form[contains(@id,'EditSummaryForm')]/a[2]");
	public final By ELEMENT_SAMPLE_SUM_INPUT = By.xpath("//textarea[contains(@id,'newSummary')]");
	public final String ELEMENT_SAMPLE_SUM_INLINE = "//div[contains(@id,'CurrentSummary') and @title='Double-click to edit']";
	public final By ELEMENT_SAMPLE_TITLE_ACCEPT = By.xpath("//form[contains(@id,'EditTdGtmplTitleForm')]/a[2]");
	public final By ELEMENT_SAMPLE_TITLE_INPUT = By.xpath("//input[contains(@id,'newTdGtmplTitle')]");
	public final String ELEMENT_SAMPLE_TITLE_INLINE = "//div[contains(@id,'CurrentTdGtmplTitle') and @title='Double-click to edit']";

	public final String ELEMENT_MESSAGE_EDIT_INLINE = "An error occurred while modifying the required property.";
	public final String ELEMENT_MESSAGE_EDIT_CLOSE = "The changes you made will be lost if you close this form.";
	public final String ELEMENT_ENABLE_DMS_STRUCTURE = "enableStructure";
	public final By ELEMENT_PIC_IMGWIDTH = By.id("imageWidth");
	public final By ELEMENT_PIC_IMGHEIGHT = By.id("imageHeight");
	public final By ELEMENT_PIC_LANG = By.name("content-lang");
	public final By ELEMENT_PIC_CONT = By.xpath("//iframe[contains(@title,'Rich text editor, htmlData')]");
	public final By ELEMENT_CSS_NAME = By.id("name");
	public final By ELEMENT_CSS_ACTIVE = By.name("activeCSS");
	public final By ELEMENT_CSS_PRIORITY = By.id("CSSpriority");
	public final By ELEMENT_CSS_DATA = By.id("contentHtml");
	public final By ELEMENT_MYSITE_LINK = By.xpath("//a[contains(text(),'My Sites')]");
	public final By ELEMENT_ACME_SITE_LINK = By.linkText("acme");
	public final By ELEMENT_PAGE_TAG_P = By.xpath("//p");

	// JS
	public final By ELEMENT_NEW_JS_FILE_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Javascript File']");
	public final By ELEMENT_JS_NAME = By.id("name");
	public final By ELEMENT_JS_ACTIVE = By.name("activeJS");
	public final By ELEMENT_JS_PRIORITY = By.id("JSpriority");
	public final By ELEMENT_JS_LANGUAGE = By.name("content-lang");
	public final By ELEMENT_JS_DATA = By.id("contentHtml");

	// Data test
	public final String[] DATA_SPECIAL_CHARACTER = { "@", "`", "~", "!", "#",
			"$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "{", "}",
			"[", "]", "|", "\\", ";", ":", "'", "\"", "<", ",", ".", "/", "?" };
	public final String[] DATA_SPECIAL_CHARACTER_2 = { "`", "~", "!", "#", "$",
			"&", "*", "(", ")", "=", "{", "}", "+", ";", "<", ">", "/", "?",
			"\"", "\\" };
	public final String DATA_SPECIAL_CHARACTER_STRING = "~`!@#$%^&*()-_=+[]{}\\|;:\",<.>?/";

	// Add New Folder
	public final By ELEMENT_CREATE_FOLDER_BUTTON = By.xpath("//*[text()='Create Folder']");
	public final By ELEMENT_USE_CUSTOM_TYPE_FOLDER = By.xpath(".//*[@id='customTypeCheckBox']");
	public final String ELEMENT_VERIFY_FILE_CONTENT = "//*[contains(text(),'${content}')]";

	public final String ELEMENT_FOLDER_CONTENT = "//*[@title='${content}']";
	public final String ELEMENT_FOLDER_CONTENT_2 = ".//*[@id='UIDocumentNodeList']//*[contains(text(),'${content}')]";

	//public final String ELEMENT_FOLDER_CONTENT="//*[@title='${content}']";


	public final By ELEMENT_TAB_VIEW_AS_HTML = By.xpath(".//*[@id='myTab']//*[contains(@href,'#tab1')]");
	public final String ELEMENT_FILE_CONTENT_DECORATED = "//u/em/strong[contains(text(),'${content}')]";

	//public final String ELEMENT_FOLDER_CONTENT = "//*[@title='${content}']";

	// Message
	public final String MESSAGE_NAME_REQUIRED_FIELD = "The field \"Name\" is required.";
	public final String MESSAGE_CONTENT_REQUIRED_FIELD = "The field \"Content\" is required.";
	public final String MESSAGE_FIELD_NAME_INVALID_CHARS = "The field 'Name' contains some invalid characters. Please enter another value.";

	// Web Links
	public final By ELEMENT_LINK_NAME = By.id("name");
	public final By ELEMENT_LINK_URL = By.id("LinkURL");
	public final By ELEMENT_LINK_DESC = By.id("LinkDescription");
	public final By ELEMENT_LINK_LANGUAGE = By.name("content-lang");

	// Contact Us
	public final By ELEMENT_CONTACT_US_LINK = By
			.xpath("//*[@class='templateLabel']//*[text()='Contact Us']");
	public final By ELEMENT_YOUR_NAME = By.id("your_nameFieldName");
	public final By ELEMENT_YOUR_ADDRESS = By.id("your_addressFieldName");
	public final By ELEMENT_YOUR_EMAIL = By.id("your_email_addressFieldName");
	public final By ELEMENT_YOUR_PHONE = By.id("your_phone_numberFieldName");
	public final By ELEMENT_YOUR_MESSAGE = By.id("your_messageFieldName");
	public final String ELEMENT_VERIFY_MSG_CONTENT = "//*[contains(text(),'${content}')]";

	// Accessible media
	public final By ELEMENT_ACCESSIBLE_MEDIA_LINK = By.xpath("//i[@data-original-title='Accessible Media']");
	public final By ELEMENT_ACCESSIBLE_MEDIA_ALTERNATIVE_FRAME = By.xpath("//*[@id='cke_1_contents']/iframe");
	public final By ELEMENT_ACCESSIBLE_MEDIA_TITLE = By.id("title0");
	public final By ELEMENT_ACCESSIBLE_MEDIA_DESC = By.id("description0");
	public final String ELEMENT_ACCESSIBLE_MEDIA_CONTENT_UEMSTRONG = ".//u/em/strong[contains(text(),'${content}')]";

	// add box
	public final By ELEMENT_IMAGE_LINK_FORM = By.xpath(".//*[contains(text(),'URL')]/../div/div/input");
	public final By ELEMENT_SIMPLE_LINK_FORM = By.xpath(".//*[@class='cke_dialog_ui_hbox']/td[2]/div//*[contains(text(),'URL')]/../div/div/input");
	public final String ELEMENT_SELECT_SPECIAL_CHAR = ".//*[@class='cke_specialchar']/span[contains(text(),'${char}')]/..";
	public final By ELEMENT_ACCEPT_ADD_IMAGE = By.xpath(".//*[@class='cke_dialog_ui_hbox']//*[contains(text(),'OK')]");
	public final By ELEMENT_ACCEPTD_ADD_LINK = By.xpath("//*[@class='cke_dialog_ui_hbox']/td/a/span[contains(text(),'OK')]");
	public final By ELEMENT_ACCEPT_TABLE_ADD = By.xpath("//*[@class='cke_dialog_ui_hbox']/td[@class='cke_dialog_ui_hbox_first']/a[@class='cke_dialog_ui_button cke_dialog_ui_button_ok']");
	// check content image
	public final String ELEMENT_IMAGE_CHECK_ON_DOCUMENT_VIEW = "//*[@class='rightContainer']//*[@id='UIDocumentContainer']//img[@src='{$image}']";
	public final By ELEMENT_CHECK_TABLE_EXIST = By.xpath("//*[@id='UIDocumentWorkspace']//*[@id='UITabContent']/table");

	// right panel
	public final By ELEMENT_RIGHT_PANEL = By.xpath(".//*[@class='uiListGrid']");
	public final By ELEMENT_RIGHT_CLICK_UPLOAD_FILES = By.xpath(".//*[@id='JCRContextMenu']/div[@class='uiRightClickPopupMenu']/ul/li[3]/a/i[@class='uiIconEcmsUpload']");

	public final By ELEMENT_SEARCH_INPUT = By.xpath(".//*[@id='simpleSearch']");
	public final By ELEMENT_SEARCH_BUTTON = By.xpath(".//*[@id='SimpleSearch']/i");
	public final String ELEMENT_RESULT_OF_SEARCH_BY_TITLE = ".//*[@id='SimpleSearchResult']//*[@class='title' and contains(text(),'{$title}')]";

	/*
	 * =================== Create a new document/article/file
	 * ===================
	 */
	/*
	 * Add new article / Kofax / File Plan == REMOVED IN PLF 4 ==
	 */

	/**
	 * Template: File > Open [Add Category] Form
	 */
	public void openAddCategoryInFileTemplate() {
		info("-- Opening [Add Category] Form... --");
		if (isElementPresent(ELEMENT_CATEGORIES_ADD_ITEM)) {
			click(ELEMENT_CATEGORIES_ADD_ITEM);
		} else {
			click("//*[contains(text(), 'Categories')]/..//*[@data-original-title='Add Item']");
		}
		Utils.pause(500);
	}

	/**
	 * add new announcement
	 * 
	 * @param name
	 * @param sum
	 * @param isDecorated
	 * @param params
	 */
	public void createNewAnnouncement(String name, String sum,
			boolean isDecorated, Object... params) {
		click(ELEMENT_ANNOUNCEMENT_LINK);
		type(ELEMENT_ANNOUNCEMENT_NAME_TEXTBOX, name, true);

		inputFrame(ELEMENT_ANNOUNCEMENT_SUMMARY_FRAME, sum);
		if (isDecorated == true) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			// decorate
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);

		if (isDecorated == true)
			waitForAndGetElement(
					ELEMENT_ACCESSIBLE_MEDIA_CONTENT_UEMSTRONG.replace(
							"${content}", sum), 2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", sum), 2000, 1);
	}

	/**
	 * add new Free layout webcontent
	 * 
	 * @param name
	 * @param cont
	 * @param img
	 * @param sum
	 * @param css
	 * @param js
	 * @param params
	 */
	public void createNewWebContent(String name, String cont, String img,
			String sum, String css, String js, Object... params) {
		boolean lines = (Boolean) (params.length > 0 ? params[0] : false);
		String optionLang = (String) (params.length > 1 ? params[1] : "");
		boolean isVerify = (Boolean) (params.length > 0 ? params[0] : true);
		By eWebContentSum;
		if (this.plfVersion.equalsIgnoreCase("4.0")) {
			eWebContentSum = ELEMENT_WEBCONTENT_SUMMARY_FRAME;
		} else {
			eWebContentSum = ELEMENT_WEBCONTENT_SUMMARY_FRAME_41;
		}
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(ELEMENT_WEBCONTENT_LINK);
		type(ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		if (!optionLang.isEmpty()) {
			selectOption(ELEMENT_PIC_LANG, optionLang);
		}
		if (cont != "") {
			if (this.plfVersion.equalsIgnoreCase("4.0")) {
				inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME, cont, true);
			} else if (waitForAndGetElement(
					ELEMENT_WEBCONTENT_CONTENT_FRAME_41, 5000, 0) != null)
				inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41, cont,
						true);
			else {
				inputDataToFrame(ELEMENT_WEBCONTNET_CONTENT_FRAME_2, cont, true);
			}
			switchToParentWindow();

		}
		if (sum != "" || img != "") {
			click(ELEMENT_ILLUSTRATION_TAB);
			Utils.pause(3000);
			if (img != "") {
				WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
						DEFAULT_TIMEOUT, 0, 2);
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.display = 'block';", upload);
				upload.sendKeys(Utils.getAbsoluteFilePath(img));
				switchToParentWindow();
				String links[] = img.split("/");
				int length = links.length;
				waitForAndGetElement(
						By.xpath("//div[contains(text(),'" + links[length - 1]
								+ "')]"), DEFAULT_TIMEOUT, 1, 2);
			}
			if (!lines) {
				inputDataToFrame(eWebContentSum, sum);
				switchToParentWindow();
			} else {
				if (waitForAndGetElement(ELEMENT_WEBCONTENT_SUMMARY_FRAME_41,
						5000, 0, 1) != null)
					typeMultiLineInCkeContent(
							ELEMENT_WEBCONTENT_SUMMARY_FRAME_41, sum);
				else
					typeMultiLineInCkeContent(ELEMENT_WEBCONTENT_SUMMARY_FRAME,
							sum);
			}
		}
		if (css != "" || js != "") {
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, false);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, false);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isVerify)
			waitForAndGetElement(ELEMENT_NODE_LINK
					.replace("${nodeLabel}", name));
		Utils.pause(3000);
	}

	/**
	 * Add a content link in CKEditor
	 * 
	 * @param path
	 * @param file
	 */
	public void addContentLinkInFCKEditor(String path, String file) {
		String previousWindowHandle = driver.getWindowHandle();
		click(ELEMENT_WEBCONTENT_ADD_CONTENT_LINK);
		switchToNewWindow();

		String[] link = path.split("/");
		for (int i = 0; i < link.length; i++) {
			click(By.id(link[i]));
		}
		click(By.linkText(file));
		driver.switchTo().window(previousWindowHandle);
		Utils.pause(1000);
	}

	/**
	 * add new file
	 * 
	 * @param name
	 * @param cont
	 * @param title
	 * @param params
	 */
	public void createNewFile(String name, String cont, String title,
			Object... params) {
		String description = (String) (params.length > 0 ? params[0] : "");
		boolean lines = (Boolean) (params.length > 1 ? params[1] : false);
		boolean isMimeType = (Boolean) (params.length > 2 ? params[2] : false);
		String mimeType = (String) (params.length > 3 ? params[3] : "text/html");

		click(ELEMENT_NEWFILE_LINK);
		type(ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		if (isMimeType) {
			selectOption(ELEMENT_NEWFILE_MIME_COMBOX_ID, mimeType);
		}
		Utils.pause(300);
		if (!lines) {
			if (waitForAndGetElement(ELEMENT_NEWFILE_CONTENT_FRAME, 5000, 0, 2) != null) {
				inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME, cont, true);
			} else if (waitForAndGetElement(ELEMENT_NEWFILE_TEXTAREA_ID, 3000,
					0) != null) {
				type(ELEMENT_NEWFILE_TEXTAREA_ID, cont, true);
			} else if (waitForAndGetElement(ELEMENT_NEWFILE_CONTENT_FRAME_41,
					3000, 0) != null) {
				inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME_41, cont, true);

			}
			switchToParentWindow();
		} else {
			typeMultiLineInCkeContent(ELEMENT_NEWFILE_CONTENT_FRAME, cont);
		}
		if (mimeType.equalsIgnoreCase("text/plain"))
			type(ELEMENT_NEWFILE_TEXTPLAIN, cont, true);
		type(ELEMENT_NEWFILE_TITLE_TEXTBOX, title, false);
		if (!description.isEmpty()) {
			type(ELEMENT_NEWFILE_DESCRIPTION_TEXTBOX, description, false);
		}
		button.saveAndClose();
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(By.xpath(ELEMENT_VERIFY_FILE_CONTENT.replace(
				"${content}", cont)), DEFAULT_TIMEOUT, 1, 2);
		Utils.pause(1000);
	}

	/**
	 * Create a new File for CKeditor
	 * 
	 * @param name
	 * @param isDecorated
	 * @param cont
	 * @param params
	 */
	public void createNewFileForCK(String name, String cont,
			boolean isDecorated, Object... params) {
		String mimeType = (String) (params.length > 0 ? params[0] : "text/html");
		click(ELEMENT_NEWFILE_LINK);
		type(ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		selectOption(ELEMENT_NEWFILE_MIME_COMBOX_ID, mimeType);
		Utils.pause(300);
		if (!cont.isEmpty()) {
			inputFrame(ELEMENT_NEWFILE_CONTENT_FRAME_41, cont);
		}
		if (isDecorated) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			// decorate text
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		if (mimeType.equalsIgnoreCase("text/plain"))
			type(ELEMENT_NEWFILE_TEXTPLAIN, cont, true);

		button.saveAndClose();
		if (isDecorated) {
			click(ELEMENT_TAB_VIEW_AS_HTML);
			driver.switchTo().frame(
					waitForAndGetElement(By.xpath("//*[@class='ECMIframe']")));
			waitForAndGetElement(
					ELEMENT_FILE_CONTENT_DECORATED.replace("${content}", cont),
					2000, 1);
			switchToParentWindow();
		} else {
			click(ELEMENT_TAB_VIEW_AS_HTML);
			driver.switchTo().frame(
					waitForAndGetElement(By.xpath("//*[@class='ECMIframe']")));
			waitForAndGetElement(
					ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", cont),
					2000, 1);
			switchToParentWindow();
		}
		Utils.pause(1000);
	}

	/**
	 * function create new file with full input
	 * 
	 * @author lientm
	 * @param name
	 * @param cont
	 * @param title
	 * @param desc
	 * @param creator
	 * @param source
	 */
	public void createNewFullFile(String name, String cont, String title,
			String desc, String creator, String source) {

		click(ELEMENT_NEWFILE_LINK);
		type(ELEMENT_NEWFILE_NAME_TEXTBOX, name, true);
		if (isElementPresent(ELEMENT_NEWFILE_CONTENT_FRAME))
			inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME, cont, true);
		else
			inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME_41, cont, true);
		switchToParentWindow();
		if (title != "") {
			type(ELEMENT_NEWFILE_TITLE_TEXTBOX, title, true);
		}
		if (desc != "") {
			type(ELEMENT_NEWFILE_DESCRIPTION_TEXTBOX, desc, true);
		}
		if (creator != "") {
			type(ELEMENT_NEWFILE_CREATOR_TEXTBOX, creator, true);
		}
		if (source != "") {
			type(ELEMENT_NEWFILE_SOURCE_TEXTBOX, source, true);
		}
		button.saveAndClose();
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(By.xpath(ELEMENT_VERIFY_FILE_CONTENT.replace(
				"${content}", cont)), DEFAULT_TIMEOUT, 1, 2);
		Utils.pause(1000);
	}

	/**
	 * Create a new Illustrated Web content
	 * 
	 * @param name
	 * @param content
	 * @param file
	 * @param illustrationImage
	 * @param illustrationSummary
	 * @param css
	 * @param js
	 * @param params
	 */
	public void createNewIllustratedWebContent(String name, String content,
			String file, String illustrationImage, String illustrationSummary,
			String css, String js, Object... params) {
		info("-- Creating a new Illustrated Web Content --");
		// String imageWidth = (String) (params.length > 0 ? params[0]: "");
		// String imageHeight = (String) (params.length > 1 ? params[1]: "");
		String optionLang = (String) (params.length > 0 ? params[0] : "");
		Boolean verify = (Boolean) (params.length > 1 ? params[1] : true);

		click(ELEMENT_HEAD_LAYOUT_LINK);
		type(ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX, name, false);
		if (this.plfVersion.equalsIgnoreCase("4.0"))
			inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME, content);
		else if (waitForAndGetElement(ELEMENT_WEBCONTENT_CONTENT_FRAME_41,
				3000, 0) != null)
			inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content);
		else
			inputDataToFrame(ELEMENT_WEBCONTNET_CONTENT_FRAME_2, content);
		switchToParentWindow();
		if (file != "") {
			WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
					DEFAULT_TIMEOUT, 0, 2);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.display = 'block';", upload);
			upload.sendKeys(Utils.getAbsoluteFilePath(file));
			String links[] = file.split("/");
			int length = links.length;
			waitForAndGetElement(By
					.xpath("//*[contains(@class, 'select')]//*[contains(text(),'"
							+ links[length - 1] + "')]"));
		}

		if (!optionLang.isEmpty()) {
			selectOption(ELEMENT_PIC_LANG, optionLang);
		}

		// Illustration tab
		if (illustrationSummary != "" || illustrationImage != "") {
			click(ELEMENT_ILLUSTRATION_TAB);
			if (illustrationImage != "") {
				WebElement upload = waitForAndGetElement(
						ELEMENT_UPLOAD_ILLUSTRATION, DEFAULT_TIMEOUT, 0, 2);
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.display = 'block';", upload);
				upload.sendKeys(Utils.getAbsoluteFilePath(illustrationImage));
				String links[] = illustrationImage.split("/");
				int length = links.length;
				waitForAndGetElement(By.xpath("//div[contains(text(),'"
						+ links[length - 1] + "')]"));
			}
			if (this.plfVersion.equals("4.0"))
				inputDataToFrame(ELEMENT_WEBCONTENT_SUMMARY_FRAME,
						illustrationSummary);
			else
				inputDataToFrame(ELEMENT_WEBCONTENT_SUMMARY_FRAME_41,
						illustrationSummary);
			switchToParentWindow();
		}
		// Advanced tab
		if (css != "" || js != "") {
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, false);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, false);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (verify) {
			waitForAndGetElement(By.xpath(ELEMENT_VERIFY_FILE_CONTENT.replace(
					"${content}", content)));
		}
		Utils.pause(1000);
	}

	/**
	 * add new product
	 * 
	 * @param name
	 * @param title
	 */
	public void createNewProduct(String name, String title) {
		click(ELEMENT_PRODUCT_LINK);
		type(ELEMENT_PRODUCT_NAME_TEXTBOX, name, false);

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * function add new Product content with full inputs
	 * 
	 * @author lientm
	 * @param name
	 * @param illus
	 * @param sum
	 * @param benefit
	 * @param feature
	 * @param multiLine
	 */
	public void createFullNewProduct(String name, String illus, String sum,
			String benefit, String feature, boolean... multiLine) {
		boolean lines = multiLine.length > 0 ? multiLine[0] : false;
		CKeditor ck = new CKeditor(driver);

		click(ELEMENT_PRODUCT_LINK);
		type(ELEMENT_PRODUCT_NAME_TEXTBOX, name, false);
		if (illus != "") {
			WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
					DEFAULT_TIMEOUT, 0, 2);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.display = 'block';", upload);
			upload.sendKeys(Utils.getAbsoluteFilePath(illus));
			switchToParentWindow();
			String links[] = illus.split("/");
			int length = links.length;
			waitForAndGetElement(By.xpath("//div[contains(text(),'"
					+ links[length - 1] + "')]"));
		}
		if (sum != "") {
			if (!lines) {
				if (waitForAndGetElement(ELEMENT_PRODUCT_SUMMARY_FRAME, 5000,
						0, 2) != null)
					inputDataToFrame(ELEMENT_PRODUCT_SUMMARY_FRAME, sum, true);
				else {
					inputDataToFrame(ELEMENT_PRODUCT_SUMMARY_FRAME_41, sum,
							true);
					switchToParentWindow();
					ck.cke_Bold();
					ck.cke_Italic();
					ck.cke_Underline();
					switchToParentWindow();
				}
			} else {
				if (waitForAndGetElement(ELEMENT_PRODUCT_SUMMARY_FRAME, 5000,
						0, 2) != null)
					typeMultiLineInCkeContent(ELEMENT_PRODUCT_SUMMARY_FRAME,
							sum);
				else {
					typeMultiLineInCkeContent(ELEMENT_PRODUCT_SUMMARY_FRAME_41,
							sum);
					switchToParentWindow();
					ck.cke_Bold();
					ck.cke_Italic();
					ck.cke_Underline();
					switchToParentWindow();
				}
			}
		}
		if (benefit != "") {
			if (waitForAndGetElement(ELEMENT_PRODUCT_BENEFIT_FRAME, 5000, 0, 2) != null)
				inputDataToFrame(ELEMENT_PRODUCT_BENEFIT_FRAME, sum, true);
			else {
				inputDataToFrame(ELEMENT_PRODUCT_BENEFIT_FRAME_41, sum, true);
				switchToParentWindow();
				ck.cke_Bold();
				ck.cke_Italic();
				ck.cke_Underline();
				switchToParentWindow();
			}
		}
		if (feature != "") {
			if (waitForAndGetElement(ELEMENT_PRODUCT_FEATURE_FRAME, 5000, 0, 2) != null)
				inputDataToFrame(ELEMENT_PRODUCT_FEATURE_FRAME, sum, true);
			else {
				inputDataToFrame(ELEMENT_PRODUCT_FEATURE_FRAME_41, sum, true);
				switchToParentWindow();
				ck.cke_Bold();
				ck.cke_Italic();
				ck.cke_Underline();
				switchToParentWindow();
			}
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/**
	 * Create New Product for CKEditor
	 * 
	 * @param name
	 * @param sum
	 * @param benefits
	 * @param feature
	 * @param params
	 */
	public void createNewProductForCK(String name, String sum, String benefits,
			String feature, boolean... params) {
		boolean isDecoredSum = (boolean) (params.length > 0 ? params[0] : false);
		boolean isDecoredBenefits = (boolean) (params.length > 1 ? params[1]
				: false);
		boolean isDecoredFeature = (boolean) (params.length > 2 ? params[2]
				: false);

		click(ELEMENT_PRODUCT_LINK);
		type(ELEMENT_PRODUCT_NAME_TEXTBOX, name, false);

		if (sum != "") {
			inputFrame(ELEMENT_PRODUCT_SUMMARY_FRAME_41, sum);
			if (isDecoredSum) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("cke_summary");
				ck.cke_Italic("cke_summary");
				ck.cke_Underline("cke_summary");
			}
		}

		if (!benefits.isEmpty()) {
			inputFrame(ELEMENT_PRODUCT_BENEFIT_FRAME_41, benefits);

			if (isDecoredBenefits) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("cke_productBenefits");
				ck.cke_Italic("cke_productBenefits");
				ck.cke_Underline("cke_productBenefits");
			}
		}
		if (!feature.isEmpty()) {
			inputFrame(ELEMENT_PRODUCT_FEATURE_FRAME_41, feature);
			if (isDecoredFeature) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("cke_productFeatures");
				ck.cke_Italic("cke_productFeatures");
				ck.cke_Underline("cke_productFeatures");
			}
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);

		if (isDecoredSum)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE.replace(
							"${content}", sum), 2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_HTMLFILE.replace("${content}", sum),
					2000, 1);

		if (isDecoredFeature)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE.replace(
							"${content}", feature), 2000, 1);
		else
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_HTMLFILE.replace(
					"${content}", feature), 2000, 1);
		click(ELEMENT_PRODUCT_FILE_BENEFIT_TAB);
		if (isDecoredBenefits)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE.replace(
							"${content}", benefits), 2000, 1);
		else
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_HTMLFILE.replace(
					"${content}", benefits), 2000, 1);
	}

	/**
	 * Folder type
	 * 
	 */
	public enum folderType {
		None, Content, Document, Css;
	}


	/**
	 * add new Content Folder
	 * 
	 * @param title
	 * @param type
	 * @param params
	 *//*
	public void createNewFolder(String title, folderType type, Object... params) {
		Boolean checkFolder = (Boolean) (params.length > 0 ? params[0] : true);

		info("-- Creating a new folder --");
		actBar.goToAddNewFolder();
		check(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 2);
		switch (type) {
		case Content:
			type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
			selectOption(ELEMENT_FOLDER_TYPE_OPTION,
					ELEMENT_CONTENT_FOLDER_TYPE);
			break;
		case Document:
			selectOption(ELEMENT_FOLDER_TYPE_OPTION,
					ELEMENT_DOCUMENT_FOLDER_TYPE);
			type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
			break;
		case Css:
			selectOption(ELEMENT_FOLDER_TYPE_OPTION, ELEMENT_CSS_FOLDER_TYPE);
			type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
			break;
		case None:
			type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
			if (waitForAndGetElement(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 5000, 0) != null)
				uncheck(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 2);
			break;
		default:
			break;
		}
		click(ELEMENT_CREATE_FOLDER_BUTTON);
		if (checkFolder) {
			waitForAndGetElement(By.xpath(ELEMENT_FOLDER_CONTENT.replace(
					"${content}", title)));
		}*/

	// add new Content Folder
		public void createNewFolder(String title, folderType type, Object... params) {
			Boolean checkFolder = (Boolean) (params.length > 0 ? params[0] : true);
			//WebElement typeFolder = driver.findElement(By.id("customTypeCheckBox"));
			WebElement typeFolder = waitForAndGetElement(".//*[@id='customTypeCheckBox']", 4000, 0);
			info("-- Creating a new folder --");
			actBar.goToAddNewFolder();
			if (waitForAndGetElement(typeFolder, 2000,0) != null && !typeFolder.isSelected())
				check(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 2);
			switch (type) {
			case Content:
				type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
				selectOption(ELEMENT_FOLDER_TYPE_OPTION,
						ELEMENT_CONTENT_FOLDER_TYPE);
				break;
			case Document:
				selectOption(ELEMENT_FOLDER_TYPE_OPTION,
						ELEMENT_DOCUMENT_FOLDER_TYPE);
				type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
				break;
			case Css:
				selectOption(ELEMENT_FOLDER_TYPE_OPTION, ELEMENT_CSS_FOLDER_TYPE);
				type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
				break;
			case None:
				type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, true);
				if(waitForAndGetElement(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 5000, 0) != null)
					uncheck(ELEMENT_USE_CUSTOM_TYPE_FOLDER, 2);
				break;
			default:
				break;
			}
			click(ELEMENT_CREATE_FOLDER_BUTTON);
			if (checkFolder) {
				if (waitForAndGetElement(By.xpath(ELEMENT_FOLDER_CONTENT.replace("${content}", title)), 3000, 0) == null)
					waitForAndGetElement(By.xpath(ELEMENT_FOLDER_CONTENT_2.replace("${content}", title)));
			}
			Utils.pause(1000);
			info("A new folder was created");
		}

	/**
	 * Create a new Css file
	 * 
	 * @param name
	 * @param priority
	 * @param data
	 * @param params
	 */
	public void createNewCssFile(String name, String priority, String data,
			Object... params) {
		Boolean active = (Boolean) (params.length > 0 ? params[0] : false);
		String lang = (String) (params.length > 1 ? params[1] : "");

		info("-- Creating a new Css File --");
		type(ELEMENT_CSS_NAME, name, false);
		if (!active) {
			select(ELEMENT_CSS_ACTIVE, "False");
		} else {
			select(ELEMENT_CSS_ACTIVE, "True");
		}
		type(ELEMENT_CSS_PRIORITY, priority, false);
		if (!lang.isEmpty()) {
			select(ELEMENT_PIC_LANG, lang);
		}
		type(ELEMENT_CSS_DATA, data, true);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * Create a new JS file
	 * 
	 * @param name
	 * @param priority
	 * @param data
	 * @param params
	 */
	public void createNewJsFile(String name, String priority, String data,
			Object... params) {
		Boolean active = (Boolean) (params.length > 0 ? params[0] : false);
		String lang = (String) (params.length > 1 ? params[1] : "");

		info("-- Creating a new Js File --");
		type(ELEMENT_JS_NAME, name, true);
		if (!active) {
			select(ELEMENT_JS_ACTIVE, "False");
		} else {
			select(ELEMENT_JS_ACTIVE, "True");
		}
		type(ELEMENT_JS_PRIORITY, priority, true);
		if (!lang.isEmpty()) {
			selectOption(ELEMENT_JS_LANGUAGE, lang);
		}
		type(ELEMENT_JS_DATA, data, true);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * Create new HTML file
	 * 
	 * @param name
	 * @param params
	 */
	public void createNewHtmlFile(String name, boolean isDecorate,
			Object... params) {
		click(ELEMENT_NEW_HTML_FILE_LINK);
		inputHtmlFile(name, isDecorate, params);
	}

	/**
	 * Input Html file
	 * 
	 * @param name
	 * @param params
	 */
	public void inputHtmlFile(String name, boolean isDecorate, Object... params) {
		String lang = (String) (params.length > 0 ? params[0] : "");
		String content = (String) (params.length > 1 ? params[1] : "");
		info("-- Creating a new HTML File --");
		if (name != "")
			type(ELEMENT_HTML_FILE_NAME, name, true);
		if (!lang.isEmpty()) {
			selectOption(ELEMENT_HTML_FILE_LANGUAGE, lang);
		}

		if (!content.isEmpty()) {
			if (this.plfVersion.equalsIgnoreCase("4.0"))
				inputFrame(ELEMENT_HTML_FILE_CKEDITOR_FRAME, content);
			else
				inputFrame(ELEMENT_HTML_FILE_CKEDITOR_FRAME_41, content);
		}
		if (isDecorate) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorate)
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_UEMSTRONG.replace(
					"${content}", content), 2000, 1);
		else
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_HTMLFILE.replace(
					"${content}", content), 2000, 1);
		Utils.pause(1000);
	}

	/**
	 * Create a new WebLink
	 * 
	 * @param name
	 * @param url
	 * @param params
	 */
	public void createNewWebLink(String name, String url, Object... params) {
		click(ELEMENT_WEBLINK_LINK);
		String lang = (String) (params.length > 0 ? params[0] : "");
		String description = (String) (params.length > 1 ? params[1] : "");
		info("-- create new Web Link File --");
		type(ELEMENT_LINK_NAME, name, true);
		type(ELEMENT_LINK_URL, url, true);
		if (!lang.isEmpty()) {
			selectOption(ELEMENT_LINK_LANGUAGE, lang);
		}
		if (!description.isEmpty()) {
			type(ELEMENT_LINK_DESC, description, true);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * Create a new Contact Us
	 * 
	 * @param params
	 */
	public void createNewContactUs(Object... params) {
		click(ELEMENT_CONTACTUS_LINK);
		String recipient = (String) (params.length > 0 ? params[0] : "");
		String name = (String) (params.length > 1 ? params[1] : "");
		String address = (String) (params.length > 2 ? params[2] : "");
		String email = (String) (params.length > 3 ? params[3] : "");
		String phone = (String) (params.length > 4 ? params[4] : "");
		String lang = (String) (params.length > 5 ? params[5] : "");
		String msg = (String) (params.length > 6 ? params[6] : "");
		info("-- Creating a Contact Us document --");
		if (!recipient.isEmpty()) {
			selectOption(ELEMENT_CONTACTUS_RECIPIENT, recipient);
		}
		if (!name.isEmpty()) {
			type(ELEMENT_CONTACTUS_YOURNAME, name, true);
		}
		if (!address.isEmpty()) {
			type(ELEMENT_CONTACTUS_YOURADDRESS, address, true);
		}
		if (!email.isEmpty()) {
			type(ELEMENT_CONTACTUS_EMAIL, email, true);
		}
		if (!phone.isEmpty()) {
			type(ELEMENT_CONTACTUS_PHONE, phone, true);
		}
		if (!lang.isEmpty()) {
			selectOption(ELEMENT_CONTACTUS_LANGUAGE, lang);
		}
		if (!msg.isEmpty()) {
			type(ELEMENT_CONTACTUS_MSG, msg, true);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * Search a content
	 * 
	 * @param keyWordToSearch
	 * @param nameOfContent
	 */
	public void searchContent(String keyWordToSearch, String nameOfContent) {
		info("Type a text to simple search filed");
		type(ELEMENT_SEARCH_INPUT, keyWordToSearch, true);
		click(ELEMENT_SEARCH_BUTTON);
		waitForAndGetElement(ELEMENT_RESULT_OF_SEARCH_BY_TITLE.replace(
				"{$title}", nameOfContent), 2000, 1);
	}

	/**
	 * Create a new Accessible Media
	 * 
	 * @param name
	 * @param isDecorated
	 * @param params
	 */
	public void createNewAccessibleMedia(String name, boolean isDecorated,
			Object... params) {
		click(ELEMENT_ACCESSIBLEMEDIA_LINK);
		String title = (String) (params.length > 0 ? params[0] : "");
		String content = (String) (params.length > 1 ? params[1] : "");
		String lang = (String) (params.length > 2 ? params[2] : "");

		info("-- new file --");
		type(ELEMENT_MAIN_TAB_NAME, name, true);

		if (!lang.isEmpty()) {
			selectOption(ELEMENT_MAIN_TAB_LANGUAGE, lang);
		}

		if (!title.isEmpty()) {
			type(ELEMENT_ACCESSIBLE_MEDIA_MAIN_TAB_TITLE, title, true);
		}

		if (!content.isEmpty())
			inputFrame(ELEMENT_ACCESSIBLE_MEDIA_CKEDITOR_FRAME, content);
		if (isDecorated == true) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated == true)
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_UEMSTRONG.replace(
					"${content}", content), 2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", content), 2000,
					1);

	}

	/**
	 * Create a new Accessible Document
	 * 
	 * @param name
	 * @param params
	 */
	public void createNewAccessibleDocument(String name, boolean decorate,
			Object... params) {
		waitForAndGetElement(ELEMENT_MAIN_TAB);
		click(ELEMENT_MAIN_TAB);
		String title = (String) (params.length > 0 ? params[0] : "");
		String lang = (String) (params.length > 1 ? params[1] : "");
		String content = (String) (params.length > 2 ? params[2] : "");

		info("-- new Accessible Breadcrumb document	 --");
		type(ELEMENT_MAIN_TAB_NAME, name, true);

		if (!title.isEmpty()) {
			type(ELEMENT_MAIN_TAB_TITLE, title, true);
		}

		if (!lang.isEmpty()) {
			selectOption(ELEMENT_MAIN_TAB_LANGUAGE, lang);
		}

		if (!content.isEmpty()) {
			inputDataToFrame(ELEMENT_HTML_FILE_CKEDITOR_FRAME, content, true);
			switchToParentWindow();
		}

		if (decorate == true) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * Type a text to a Frame using for CKEDITOR
	 * 
	 * @param frameLocator
	 * @param content
	 */
	public void inputFrame(By frameLocator, String content, boolean...params) {
		boolean clear = params.length > 0 ? params[0] : true;
		info("Finding the frameLocator:" + frameLocator);
		WebElement e = waitForAndGetElement(frameLocator, DEFAULT_TIMEOUT, 1, 2);
		//scrollToElement(e,driver);
		info("Switch to the frame:" + frameLocator);
		driver.switchTo().frame(e);
		WebElement inputsummary = driver.switchTo().activeElement();
		info("Input the content:" + content);
		if (clear)
			inputsummary.clear();
		inputsummary.sendKeys(content);
		info("Back to parent window");
		switchToParentWindow();
	}

	/**
	 * Create a new content files that has many CKEditor with CKeditor format
	 * 
	 * @param ck
	 * @param fileName
	 * @param name
	 * @param title
	 * @param cont
	 * @param sum
	 * @param params
	 */
	public void createNewContentForCK(String fileName, String name,
			String title, String cont, String sum, boolean... params) {
		boolean isDecorated = (boolean) (params.length > 0 ? params[0] : false);

		click(ELEMENT_FILES_LINK.replace("${fileName}", fileName));
		Utils.pause(500);
		if (!name.isEmpty())
			type(ELEMENT_MAIN_TAB_NAME, name, true);
		info("title:" + title);
		if (!title.isEmpty())
			;
		type(ELEMENT_MAIN_TAB_TITLE, title, true);

		if (!cont.isEmpty()) {
			inputFrame(ELEMENT_MAIN_CKEDITOR_FRAME_1, cont);
			if (isDecorated) {
				// decorate text
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("tab1");
				ck.cke_Italic("tab1");
				ck.cke_Underline("tab1");
			}
		}

		// Illustration tab
		if (!sum.isEmpty()) {
			click(ELEMENT_ILLUSTRATION_TAB);
			inputFrame(ELEMENT_SUMMARY_CKEDITOR_FRAME_2, sum);
			if (isDecorated) {
				// decorate text
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("tab2");
				ck.cke_Italic("tab2");
				ck.cke_Underline("tab2");
			}
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG.replace("${content}", cont),
					2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", cont), 2000, 1);
	}

	/**
	 * Create a new Web content files that has many CKEditor with CKeditor
	 * format
	 * 
	 * @param fileName
	 * @param name
	 * @param params
	 */
	public void createNewWebContentForCK(String fileName, String name,
			boolean isDecorated, Object... params) {
		String title = (String) (params.length > 0 ? params[0] : "");
		String cont = (String) (params.length > 1 ? params[1] : "");
		String sum = (String) (params.length > 2 ? params[2] : "");
		String tab1 = (String) (params.length > 3 ? params[3] : "");
		String tab2 = (String) (params.length > 4 ? params[4] : "");

		click(ELEMENT_FILES_LINK.replace("${fileName}", fileName));
		Utils.pause(500);
		if (!name.isEmpty())
			type(ELEMENT_MAIN_TAB_NAME, name, true);

		if (!title.isEmpty())
			type(ELEMENT_MAIN_TAB_TITLE, title, true);

		if (!cont.isEmpty()) {
			if (this.plfVersion.equalsIgnoreCase("4.0")) {
				inputFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME, cont);
			} else if (waitForAndGetElement(
					ELEMENT_WEBCONTENT_CONTENT_FRAME_41, 5000, 0) != null)
				inputFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41, cont);
			else {
				inputFrame(ELEMENT_WEBCONTNET_CONTENT_FRAME_2, cont);
			}
			if (isDecorated == true) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab1);
				ck.cke_Italic(tab1);
				ck.cke_Underline(tab1);
			}
		}

		// Illustration tab
		if (!sum.isEmpty()) {
			click(ELEMENT_ILLUSTRATION_TAB);
			inputFrame(ELEMENT_SUMMARY_CKEDITOR_FRAME_2, sum);
			if (isDecorated == true) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab2);
				ck.cke_Italic(tab2);
				ck.cke_Underline(tab2);
			}
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated == true)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG.replace("${content}", cont),
					2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", cont), 2000, 1);
	}

	/**
	 * Create a new Ill. Web content files that has many CKEditor with CKeditor
	 * format
	 * 
	 * @param fileName
	 * @param name
	 * @param params
	 */
	public void createNewIllWebContentForCK(String fileName, String name,
			boolean isDecorated, Object... params) {
		String title = (String) (params.length > 0 ? params[0] : "");
		String cont = (String) (params.length > 1 ? params[1] : "");
		String sum = (String) (params.length > 2 ? params[2] : "");
		String tab1 = (String) (params.length > 3 ? params[3] : "");
		String tab2 = (String) (params.length > 4 ? params[4] : "");

		click(ELEMENT_FILES_LINK.replace("${fileName}", fileName));
		Utils.pause(500);
		if (!name.isEmpty())
			type(ELEMENT_MAIN_TAB_NAME, name, true);

		if (!title.isEmpty())
			type(ELEMENT_MAIN_TAB_TITLE, title, true);

		if (!cont.isEmpty()) {
			inputFrame(ELEMENT_ILLWEBCONTENT_CKEDITOR_FRAME1_41, cont);
			if (isDecorated) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab1);
				ck.cke_Italic(tab1);
				ck.cke_Underline(tab1);
			}
		}

		// Illustration tab
		if (!sum.isEmpty()) {
			click(ELEMENT_ILLUSTRATION_TAB);
			inputFrame(ELEMENT_SUMMARY_CKEDITOR_FRAME_2, sum);
			if (isDecorated) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab2);
				ck.cke_Italic(tab2);
				ck.cke_Underline(tab2);
			}
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG.replace("${content}", cont),
					2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", cont), 2000, 1);
	}

	/**
	 * Create content folder and check
	 * 
	 * @param name
	 * @param path
	 */
	public void createAndCheckContentFolder(String name, By path) {
		info("Create new content folder with name: " + name);
		createNewFolder(name, folderType.Content);
		waitForAndGetElement(path);
		info("Create new content folder successfully");
	}

	/**
	 * Create a new link
	 * 
	 * @param linkName
	 * @param url
	 * @param params
	 */
	public void createNewLink(String linkName, String url, Object... params) {
		String lang = (String) (params.length > 0 ? params[0] : "");
		String description = (String) (params.length > 1 ? params[1] : "");

		info("Create a new link" + linkName);
		type(ELEMENT_LINK_NAME, linkName, false);
		type(ELEMENT_LINK_URL, url, false);

		if (!lang.isEmpty()) {
			selectOption(ELEMENT_PIC_LANG, lang);
		}

		if (!description.isEmpty()) {
			type(ELEMENT_LINK_DESC, description, false);
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * Create a new Contact Us
	 * 
	 * @param name
	 * @param address
	 * @param email
	 * @param phone
	 * @param message
	 */
	public void createNewContactUs(String name, String address, String email,
			String phone, String message) {

		info("Create a new Contact Us");

		click(ELEMENT_CONTACT_US_LINK);

		type(ELEMENT_YOUR_NAME, name, true);

		type(ELEMENT_YOUR_ADDRESS, address, true);

		type(ELEMENT_YOUR_EMAIL, email, true);

		type(ELEMENT_YOUR_PHONE, phone, true);

		type(ELEMENT_YOUR_MESSAGE, message, true);

		button.saveAndClose();
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(By.xpath(ELEMENT_VERIFY_MSG_CONTENT.replace(
				"${content}", message)), DEFAULT_TIMEOUT, 1, 2);
		Utils.pause(1000);
	}

	/* =================== Edit a new document/article/file =================== */

	/*
	 * Edit content template == REMOVED IN PLF 4 ==
	 */

	/**
	 * Edit a Css File
	 * 
	 * @param name
	 * @param priority
	 * @param data
	 * @param params
	 */
	public void editCssFile(String name, String priority, String data,
			Object... params) {
		Boolean active = (Boolean) (params.length > 0 ? params[0] : false);
		String lang = (String) (params.length > 1 ? params[1] : "");

		info("-- Editing Css File --");
		actBar.goToEditDocument(name);
		if (!active) {
			select(ELEMENT_CSS_ACTIVE, "False");
		} else {
			select(ELEMENT_CSS_ACTIVE, "True");
		}
		type(ELEMENT_CSS_PRIORITY, priority, true);
		if (!lang.isEmpty()) {
			selectOption(ELEMENT_PIC_LANG, lang);
		}
		type(ELEMENT_CSS_DATA, data, true);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/**
	 * Edit a Js File
	 * 
	 * @param name
	 * @param priority
	 * @param data
	 * @param params
	 */
	public void editJsFile(String name, String priority, String data,
			Object... params) {
		Boolean active = (Boolean) (params.length > 0 ? params[0] : false);
		String lang = (String) (params.length > 1 ? params[1] : "");

		info("-- Editing Js File --");
		actBar.goToEditDocument(name);
		if (!active) {
			select(ELEMENT_JS_ACTIVE, "False");
		} else {
			select(ELEMENT_JS_ACTIVE, "True");
		}
		type(ELEMENT_JS_PRIORITY, priority, true);
		if (!lang.isEmpty()) {
			select(ELEMENT_JS_LANGUAGE, lang);
		}
		type(ELEMENT_JS_DATA, data, true);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/**
	 * Edit File document
	 * 
	 * @param titleFile
	 * @param titleEdit
	 * @param contentEdit
	 * @param params
	 */
	public void editFile(String titleFile, String titleEdit,
			String contentEdit, Object... params) {
		int option = (Integer) (params.length > 0 ? params[0] : 2);
		actBar.goToEditDocument(titleFile);
		Utils.pause(500);
		if (contentEdit != "") {
			if (waitForAndGetElement(ELEMENT_NEWFILE_CONTENT_FRAME, 5000, 0, 2) != null) {
				inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME, contentEdit,
						true);
			} else if (waitForAndGetElement(ELEMENT_NEWFILE_TEXTAREA_ID, 3000,
					0) != null) {
				type(ELEMENT_NEWFILE_TEXTAREA_ID, contentEdit, true);
			} else {
				inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME_41, contentEdit,
						true);
			}
			switchToParentWindow();
		}
		if (titleEdit != "") {
			type(ELEMENT_NEWFILE_TITLE_TEXTBOX, titleEdit, true);
		}

		if (option == 0) {
			button.save();
			waitForAndGetElement(button.ELEMENT_SAVE_CLOSE_BUTTON);
		} else if (option == 1) {
			magAlert.acceptAlert();
			button.ok();
			button.close();
			Utils.pause(500);
		} else {
			button.saveAndClose();
			waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		}
	}

	/**
	 * Edit Illustrated Web Content
	 * 
	 * @param title
	 * @param contentToEdit
	 * @param file
	 * @param illustrationImage
	 * @param illustrationSummary
	 * @param css
	 * @param js
	 * @param params
	 */
	public void editIllustratedWebContent(String title, String contentToEdit,
			String file, String illustrationImage, String illustrationSummary,
			String css, String js, Object... params) {
		String imageWidth = (String) (params.length > 0 ? params[0] : "");
		String imageHeight = (String) (params.length > 1 ? params[1] : "");
		String lang = (String) (params.length > 2 ? params[2] : "");

		info("-- Editing Illustrated Web Content -- " + title);
		actBar.goToEditDocument(title);

		if (this.plfVersion.equalsIgnoreCase("4.0"))
			inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME, contentToEdit,
					true);
		else if (waitForAndGetElement(ELEMENT_WEBCONTENT_CONTENT_FRAME_41,
				5000, 0) != null)
			inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41,
					contentToEdit, true);
		else {
			inputDataToFrame(ELEMENT_WEBCONTNET_CONTENT_FRAME_2, contentToEdit,
					true);
		}

		switchToParentWindow();

		// Main Content tab
		if (file != "") {
			WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
					DEFAULT_TIMEOUT, 0, 2);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.display = 'block';", upload);
			upload.sendKeys(Utils.getAbsoluteFilePath(file));
			switchToParentWindow();
			String links[] = file.split("/");
			int length = links.length;
			waitForAndGetElement(By.xpath("//div[contains(text(),'"
					+ links[length - 1] + "')]"));
		}
		if (!imageWidth.isEmpty())
			type(ELEMENT_PIC_IMGWIDTH, imageWidth, true);
		if (!imageHeight.isEmpty())
			type(ELEMENT_PIC_IMGHEIGHT, imageHeight, true);
		if (!lang.isEmpty())
			selectOption(ELEMENT_PIC_LANG, lang);

		// Illustration tab
		if (illustrationSummary != "" || illustrationImage != "") {
			click(ELEMENT_ILLUSTRATION_TAB);
			waitForAndGetElement(By
					.xpath("//*[@class='active']//*[contains(text(), 'Illustration')]"));
			if (illustrationImage != "") {
				if (waitForAndGetElement(ELEMENT_UPLOAD_REMOVE, 3000, 0) != null) {
					click(ELEMENT_UPLOAD_REMOVE);
				}
				Utils.pause(1000);
				click(ELEMENT_ILLUSTRATION_TAB);
				WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
						DEFAULT_TIMEOUT, 0, 2);
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.display = 'block';", upload);
				upload.sendKeys(Utils.getAbsoluteFilePath(illustrationImage));
				// //
				switchToParentWindow();
				String links[] = illustrationImage.split("/");
				int length = links.length;
				waitForAndGetElement(By.xpath("//div[contains(text(),'"
						+ links[length - 1] + "..." + "')]"));
			}
			inputDataToFrame(ELEMENT_WEBCONTENT_SUMMARY_FRAME,
					illustrationSummary, true);
			switchToParentWindow();
		}

		// Advanced tab
		if (css != "" || js != "") {
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, true);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, true);
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(By.xpath(ELEMENT_VERIFY_FILE_CONTENT.replace(
				"${content}", contentToEdit)));
	}

	/**
	 * Edit WebContent Inline
	 * 
	 * @param openField
	 * @param inputField
	 * @param value
	 * @param accept
	 */
	public void editWebContentInline(String openField, By inputField,
			String value, By accept) {
		enableEditMode(true);
		doubleClickOnElement(openField);
		inputDataToFrame(inputField, value, true);
		switchToParentWindow();
		click(accept);
		waitForAndGetElement(By.xpath("//p[contains(text(),'" + value + "')]"));
	}

	/**
	 * Edit a WebContent
	 * 
	 * @param title
	 * @param contentToEdit
	 * @param img
	 * @param sum
	 * @param css
	 * @param js
	 */
	public void editWebContent(String title, String contentToEdit, String img,
			String sum, String css, String js) {
		info("-- Editing Web Content --" + title);
		actBar.goToEditDocument(title);
		driver.navigate().refresh();
		// waitForAndGetElement(ELEMENT_WEBCONTENT_NAME_TEXTBOX).clear();
		if (contentToEdit != "") {
			if (this.plfVersion.equalsIgnoreCase("4.1"))
				inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41,
						contentToEdit, true);
			else
				inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME,
						contentToEdit, true);
			switchToParentWindow();
		}

		if (sum != "" || img != "") {
			click(ELEMENT_ILLUSTRATION_TAB);
			waitForAndGetElement(By
					.xpath("//*[@class='active']//*[contains(text(), 'Illustration')]"));
			if (img != "") {
				WebElement removeIcon = waitForAndGetElement(ELEMENT_UPLOAD_REMOVE);
				if (removeIcon != null) {
					click(ELEMENT_UPLOAD_REMOVE);
				}
				Utils.pause(1000);
				click(ELEMENT_ILLUSTRATION_TAB);
				WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
						DEFAULT_TIMEOUT, 0, 2);
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.display = 'block';", upload);
				upload.sendKeys(Utils.getAbsoluteFilePath(img));
				// //
				switchToParentWindow();
				String links[] = img.split("/");
				int length = links.length;
				waitForAndGetElement(By.xpath("//div[contains(text(),'"
						+ links[length - 1] + "..." + "')]"));
			}
			if (waitForAndGetElement(ELEMENT_WEBCONTENT_SUMMARY_FRAME_41, 5000,
					0, 1) != null)
				typeMultiLineInCkeContent(ELEMENT_WEBCONTENT_SUMMARY_FRAME_41,
						sum);
			else
				typeMultiLineInCkeContent(ELEMENT_WEBCONTENT_SUMMARY_FRAME, sum);
			switchToParentWindow();
		}

		if (css != "" || js != "") {
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, true);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, true);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/**
	 * Edit language for a document
	 * 
	 * @param name
	 * @param language
	 */
	public void editLanguageForDocument(String name, String language) {
		actBar.goToEditDocument(name);

		select(ELEMENT_SELECT_LANGUAGE, language);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SELECT_LANGUAGE);
	}

	/**
	 * Create an accessible media document
	 * 
	 * @author thuntn
	 * @param name
	 * @param lang
	 * @param title
	 * @param desc
	 * @param alter
	 */
	public void createAccessibleMedia(String name, String lang, String title,
			String desc, String alter) {

		info("Create an accessible media" + name);
		click(ELEMENT_ACCESSIBLE_MEDIA_LINK);

		if (name != null)
			type(ELEMENT_LINK_NAME, name, false);
		if (lang != null)
			select(ELEMENT_JS_LANGUAGE, lang);
		if (title != null)
			type(ELEMENT_ACCESSIBLE_MEDIA_TITLE, title, true);
		if (desc != null)
			type(ELEMENT_ACCESSIBLE_MEDIA_DESC, desc, true);

		if (alter != null)
			inputDataToFrame(ELEMENT_ACCESSIBLE_MEDIA_ALTERNATIVE_FRAME, alter,
					true);

		switchToParentWindow();
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * editNewContentForCK
	 * 
	 * @param name
	 * @param title
	 * @param cont
	 * @param sum
	 * @param params
	 */
	public void editNewContentForCK(String name, String title, String cont,
			String sum, boolean... params) {
		boolean isDecorated = (boolean) (params.length > 0 ? params[0] : false);
		actBar.goToEditDocument(name);
		info("title:" + title);
		if (title != null && !title.isEmpty())
			type(ELEMENT_MAIN_TAB_TITLE, title, true);

		if (!cont.isEmpty()) {
			inputFrame(ELEMENT_MAIN_CKEDITOR_FRAME_1, cont);
			if (isDecorated) {
				// decorate text
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("tab1");
				ck.cke_Italic("tab1");
				ck.cke_Underline("tab1");
			}
		}

		// Illustration tab
		if (!sum.isEmpty()) {
			click(ELEMENT_ILLUSTRATION_TAB);
			inputFrame(ELEMENT_SUMMARY_CKEDITOR_FRAME_2, sum);
			if (isDecorated) {
				// decorate text
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("tab2");
				ck.cke_Italic("tab2");
				ck.cke_Underline("tab2");
			}
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG.replace("${content}", cont),
					2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", cont), 2000, 1);
	}

	/**
	 * Edit a new Web content files that has many CKEditor with CKeditor format
	 * 
	 * @param name
	 * @param isDecorated
	 * @param params
	 */
	public void editNewWebContentForCK(String name, boolean isDecorated,
			Object... params) {
		String title = (String) (params.length > 0 ? params[0] : "");
		String cont = (String) (params.length > 1 ? params[1] : "");
		String sum = (String) (params.length > 2 ? params[2] : "");
		String tab1 = (String) (params.length > 3 ? params[3] : "");
		String tab2 = (String) (params.length > 4 ? params[4] : "");

		actBar.goToEditDocument(name);
		Utils.pause(500);
		if (!title.isEmpty())
			type(ELEMENT_MAIN_TAB_TITLE, title, true);

		if (!cont.isEmpty()) {
			if (this.plfVersion.equalsIgnoreCase("4.0")) {
				inputFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME, cont);
			} else if (waitForAndGetElement(
					ELEMENT_WEBCONTENT_CONTENT_FRAME_41, 5000, 0) != null)
				inputFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41, cont);
			else {
				inputFrame(ELEMENT_WEBCONTNET_CONTENT_FRAME_2, cont);
			}
			if (isDecorated == true) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab1);
				ck.cke_Italic(tab1);
				ck.cke_Underline(tab1);
			}
		}

		// Illustration tab
		if (!sum.isEmpty()) {
			click(ELEMENT_ILLUSTRATION_TAB);
			inputFrame(ELEMENT_SUMMARY_CKEDITOR_FRAME_2, sum);
			if (isDecorated == true) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab2);
				ck.cke_Italic(tab2);
				ck.cke_Underline(tab2);
			}
		}

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated == true)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG.replace("${content}", cont),
					2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", cont), 2000, 1);
	}

	/**
	 * Edit a new Ill. Web content files that has many CKEditor with CKeditor
	 * format
	 * 
	 * @param name
	 * @param isDecorated
	 * @param params
	 */
	public void editNewIllWebContentForCK(String name, boolean isDecorated,
			Object... params) {
		String title = (String) (params.length > 0 ? params[0] : "");
		String cont = (String) (params.length > 1 ? params[1] : "");
		String sum = (String) (params.length > 2 ? params[2] : "");
		String tab1 = (String) (params.length > 3 ? params[3] : "");
		String tab2 = (String) (params.length > 4 ? params[4] : "");

		actBar.goToEditDocument(name);
		Utils.pause(500);
		if (!title.isEmpty())
			type(ELEMENT_MAIN_TAB_TITLE, title, true);

		if (!cont.isEmpty()) {
			inputFrame(ELEMENT_ILLWEBCONTENT_CKEDITOR_FRAME1_41, cont);
			if (isDecorated == true) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab1);
				ck.cke_Italic(tab1);
				ck.cke_Underline(tab1);
			}
		}

		// Illustration tab
		if (!sum.isEmpty()) {
			click(ELEMENT_ILLUSTRATION_TAB);
			inputFrame(ELEMENT_SUMMARY_CKEDITOR_FRAME_2, sum);
			if (isDecorated = true) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				// decorate text
				ck.cke_Bold(tab2);
				ck.cke_Italic(tab2);
				ck.cke_Underline(tab2);
			}
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated == true)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG.replace("${content}", cont),
					2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", cont), 2000, 1);
	}

	/**
	 * Upload a file by click on the right panel
	 * 
	 * @param link
	 * @param params
	 */
	public void uploadByRightClickOnTheRightPanel(String link, Object... params) {

		rightClickOnElement(ELEMENT_RIGHT_PANEL);

		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		((JavascriptExecutor) driver)
				.executeScript(
						"arguments[0].style.visibility = 'block'; arguments[0].style.height = '1px'; "
								+ "arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
						waitForAndGetElement(ELEMENT_UPLOAD_LINK,
								DEFAULT_TIMEOUT, 1, 2));

		Utils.pause(10000);
		info(link);
		driver.findElement(ELEMENT_UPLOAD_LINK).sendKeys(
				Utils.getAbsoluteFilePath(link));
		info("Upload file " + Utils.getAbsoluteFilePath(link));
		switchToParentWindow();
		info("waiting...upload progress bar is hidded");
		waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR);

		if (verify) {
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'"
					+ links[length - 1] + "')]"));
		}
		info("Upload file successfully");
		Utils.pause(2000);
	}

	/**
	 * Edit a new Accessible Media
	 * 
	 * @param name
	 * @param isDecorated
	 * @param params
	 */
	public void editNewAccessibleMedia(String name, boolean isDecorated,
			Object... params) {
		actBar.goToEditDocument(name);
		String title = (String) (params.length > 0 ? params[0] : "");
		String content = (String) (params.length > 1 ? params[1] : "");
		String lang = (String) (params.length > 2 ? params[2] : "");

		if (!lang.isEmpty()) {
			selectOption(ELEMENT_MAIN_TAB_LANGUAGE, lang);
		}

		if (!title.isEmpty()) {
			type(ELEMENT_ACCESSIBLE_MEDIA_MAIN_TAB_TITLE, title, true);
		}

		if (!content.isEmpty())
			inputFrame(ELEMENT_ACCESSIBLE_MEDIA_CKEDITOR_FRAME, content);
		if (isDecorated == true) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorated == true)
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_UEMSTRONG.replace(
					"${content}", content), 2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", content), 2000,
					1);
	}

	/**
	 * add new announcement
	 * 
	 * @param name
	 * @param sum
	 * @param isDecorated
	 * @param params
	 */
	public void editNewAnnouncement(String name, String sum,
			boolean isDecorated, Object... params) {
		actBar.goToEditDocument(name);
		inputFrame(ELEMENT_ANNOUNCEMENT_SUMMARY_FRAME, sum);
		if (isDecorated == true) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			// decorate
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(1000);

		if (isDecorated == true)
			waitForAndGetElement(
					ELEMENT_ACCESSIBLE_MEDIA_CONTENT_UEMSTRONG.replace(
							"${content}", sum), 2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT.replace("${content}", sum), 2000, 1);
	}

	/**
	 * Edit a new File for CKeditor
	 * 
	 * @param name
	 * @param isDecorated
	 * @param cont
	 * @param params
	 */
	public void editNewFileForCK(String name, String cont, boolean isDecorated) {
		actBar.goToEditDocument(name);
		Utils.pause(300);
		if (!cont.isEmpty()) {
			inputFrame(ELEMENT_NEWFILE_CONTENT_FRAME_41, cont);
		}
		if (isDecorated) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			// decorate text
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		button.saveAndClose();
		if (isDecorated) {
			click(ELEMENT_TAB_VIEW_AS_HTML);
			driver.switchTo().frame(
					waitForAndGetElement(By.xpath("//*[@class='ECMIframe']")));
			waitForAndGetElement(
					ELEMENT_FILE_CONTENT_DECORATED.replace("${content}", cont),
					2000, 1);
			switchToParentWindow();
		} else {
			click(ELEMENT_TAB_VIEW_AS_HTML);
			driver.switchTo().frame(
					waitForAndGetElement(By.xpath("//*[@class='ECMIframe']")));
			waitForAndGetElement(
					ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", cont),
					2000, 1);
			switchToParentWindow();
		}
		Utils.pause(1000);
	}

	/**
	 * Input Html file
	 * 
	 * @param name
	 * @param params
	 */
	public void editNewHtmlFile(String name, boolean isDecorate,
			Object... params) {
		String lang = (String) (params.length > 0 ? params[0] : "");
		String content = (String) (params.length > 1 ? params[1] : "");
		info("-- Creating a new HTML File --");
		actBar.goToEditDocument(name);
		Utils.pause(300);
		if (!lang.isEmpty()) {
			selectOption(ELEMENT_HTML_FILE_LANGUAGE, lang);
		}

		if (!content.isEmpty()) {
			if (this.plfVersion.equalsIgnoreCase("4.0"))
				inputFrame(ELEMENT_HTML_FILE_CKEDITOR_FRAME, content);
			else
				inputFrame(ELEMENT_HTML_FILE_CKEDITOR_FRAME_41, content);
		}
		if (isDecorate) {
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		if (isDecorate)
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_UEMSTRONG.replace(
					"${content}", content), 2000, 1);
		else
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_HTMLFILE.replace(
					"${content}", content), 2000, 1);
		Utils.pause(1000);
	}

	/**
	 * edit New Product for CKEditor
	 * 
	 * @param name
	 * @param sum
	 * @param benefits
	 * @param feature
	 * @param params
	 */
	public void editNewProductForCK(String name, String sum, String benefits,
			String feature, boolean... params) {
		boolean isDecoredSum = (boolean) (params.length > 0 ? params[0] : false);
		boolean isDecoredBenefits = (boolean) (params.length > 1 ? params[1]
				: false);
		boolean isDecoredFeature = (boolean) (params.length > 2 ? params[2]
				: false);

		actBar.goToEditDocument(name);
		Utils.pause(300);

		if (sum != "") {
			inputFrame(ELEMENT_PRODUCT_SUMMARY_FRAME_41, sum);
			if (isDecoredSum) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("cke_summary");
				ck.cke_Italic("cke_summary");
				ck.cke_Underline("cke_summary");
			}
		}

		if (!benefits.isEmpty()) {
			inputFrame(ELEMENT_PRODUCT_BENEFIT_FRAME_41, benefits);

			if (isDecoredBenefits) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("cke_productBenefits");
				ck.cke_Italic("cke_productBenefits");
				ck.cke_Underline("cke_productBenefits");
			}
		}
		if (!feature.isEmpty()) {
			inputFrame(ELEMENT_PRODUCT_FEATURE_FRAME_41, feature);
			if (isDecoredFeature) {
				pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
				ck.cke_Bold("cke_productFeatures");
				ck.cke_Italic("cke_productFeatures");
				ck.cke_Underline("cke_productFeatures");
			}
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);

		if (isDecoredSum)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE.replace(
							"${content}", sum), 2000, 1);
		else
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_HTMLFILE.replace("${content}", sum),
					2000, 1);

		if (isDecoredFeature)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE.replace(
							"${content}", feature), 2000, 1);
		else
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_HTMLFILE.replace(
					"${content}", feature), 2000, 1);
		click(ELEMENT_PRODUCT_FILE_BENEFIT_TAB);
		if (isDecoredBenefits)
			waitForAndGetElement(
					ELEMENT_UITAB_CONTENT_UEMSTRONG_PRODUCTFILE.replace(
							"${content}", benefits), 2000, 1);
		else
			waitForAndGetElement(ELEMENT_UITAB_CONTENT_HTMLFILE.replace(
					"${content}", benefits), 2000, 1);
	}
}