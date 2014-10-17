package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.concurrent.TimeUnit;

import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * by quynhpt
 * date 14/10/2014
 * 
 */
public class CKeditor extends PlatformBase {
	ForumManagePost mngPost;
	ForumManageTopic mngTopic;
	ForumBase foruBas;
	private WebElement el;

	// ----------------------CKeditor
	// -------------------------------------------------------------------------------
	public final By ELEMENT_CKEDITOR_TEXT_CONTENT = By
			.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public final By ELEMENT_CKEDITOR_POPUP = By
			.xpath(".//*[@id='UIForumPopupWindow']");
	public final By ELEMENT_CKEDITOR_MAXIMIZE = By
			.xpath(".//*[@class='cke_button_icon cke_button__maximize_icon']");
	public final By ELEMENT_CKEDITOR_MAXIMIZE_STYLE = By
			.xpath(".//*[@id='ThreadContent-tab'][@style='position: static; overflow: visible; z-index: 9995;']");
	public final By ELEMENT_CKEDITOR_MINIMIZE_STYLE = By
			.xpath(".//*[@id='ThreadContent-tab'][@style='']");

	public final By ELEMENT_CKEDITOR_CUT = By
			.xpath(".//*[@class='cke_button_icon cke_button__cut_icon']");
	public final By ELEMENT_CKEDITOR_COPY = By
			.xpath(".//*[@class='cke_button_icon cke_button__copy_icon']");
	public final By ELEMENT_CKEDITOR_PASTE = By
			.xpath(".//*[@class='cke_button_icon cke_button__pastetext_icon']");
	public final By ELEMENT_CKEDITOR_UNDO = By
			.xpath(".//*[@class='cke_button_icon cke_button__undo_icon']");
	public final By ELEMENT_CKEDITOR_REDO = By
			.xpath(".//*[@class='cke_button_icon cke_button__redo_icon']");
	public final By ELEMENT_CKEDITOR_BOLD = By
			.xpath(".//*[@class='cke_button_icon cke_button__bold_icon']");
	public final By ELEMENT_CKEDITOR_ITALIC = By
			.xpath(".//*[@class='cke_button_icon cke_button__italic_icon']");
	public final By ELEMENT_CKEDITOR_UNDERLINE = By
			.xpath(".//*[@class='cke_button_icon cke_button__underline_icon']");
	public final By ELEMENT_CKEDITOR_ALIGNLEFT = By
			.xpath(".//*[@class='cke_button_icon cke_button__justifyleft_icon']");
	public final By ELEMENT_CKEDITOR_ALIGNRIGHT = By
			.xpath(".//*[@class='cke_button_icon cke_button__justifyright_icon']");
	public final By ELEMENT_CKEDITOR_CENTER = By
			.xpath(".//*[@class='cke_button_icon cke_button__justifycenter_icon']");
	public final By ELEMENT_CKEDITOR_JUSTIFY = By
			.xpath(".//*[@class='cke_button_icon cke_button__justifyblock_icon']");
	public final By ELEMENT_CKEDITOR_INSERT_REMOVE_NUMLIST = By
			.xpath(".//*[@class='cke_button_icon cke_button__numberedlist_icon']");
	public final By ELEMENT_CKEDITOR_INSERT_REMOVE_BULLETLIST = By
			.xpath(".//*[@class='cke_button_icon cke_button__bulletedlist_icon']");
	public final By ELEMENT_CKEDITOR_DECREASE_INDENT = By
			.xpath(".//*[@class='cke_button_icon cke_button__outdent_icon']");
	public final By ELEMENT_CKEDITOR_INCREASE_INDENT = By
			.xpath(".//*[@class='cke_button_icon cke_button__indent_icon']");
	public final By ELEMENT_CKEDITOR_TEXT_COLOR = By
			.xpath(".//*[@class='cke_button_icon cke_button__textcolor_icon']");
	public final String ELEMENT_CKEDITOR_TEXT_COLOR_STYLE = ".//span[@class='cke_colorbox'][@style='background-color:#${codeColor}']";
	public final By ELEMENT_CKEDITOR_BLOCK_QUOTE = By
			.xpath(".//*[@class='cke_button_icon cke_button__blockquote_icon']");
	public final By ELEMENT_CKEDITOR_ADD_UPDATE_CODE = By
			.xpath(".//*[@class='cke_button_icon cke_button__syntaxhighlight_icon']");
	public final By ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_TAB = By
			.xpath(".//*[@class='cke_dialog_tabs']/a[@title='Source code']");
	public final By ELEMENT_CKEDITOR_ADD_UPDATE_CODE_ADVANCE_TAB = By
			.xpath(".//*[@class='cke_dialog_tabs']/a[@title='Advanced']");
	public final By ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_TEXTAREA = By
			.xpath(".//div[@class='cke_dialog_ui_input_textarea']");
	public final By ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_LANGUAGE_BOX = By
			.xpath(".//div[@class='cke_dialog_ui_input_select']");
	public final String ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_LANGUAGE_ITEM = ".//div[@class='cke_dialog_ui_input_select']//option[contains(text(),'${nameLang}')]";
	public final By ELEMENT_CKEDITOR_ADD_UPDATE_CODE_OK_BUTTON = By
			.xpath(".//*[@id='cke_708_label']");
	public final By ELEMENT_CKEDITOR_HELP_BB_CODE = By
			.xpath(".//*[@class='cke_button_icon cke_button__helpbbcode.btn_icon']");
	public final By ELEMENT_CKEDITOR_BBCODE_HELP_CONTENT = By
			.xpath(".//*[@id='SubBody']//td[contains(text(),'BB Code')]");

	public final By ELEMENT_CKEDITOR_BUTTON_RESIZE = By
			.xpath(".//*[@class='uiIconResize pull-right uiIconLightGray']");
	public final By ELEMENT_CKEDITOR_BUTTON_CANCEL = By
			.xpath(".//*[@id='UITopicForm']//button[contains(text(),'Cancel')]");
	public final By ELEMENT_CKEDITOR_BUTTON_SUBMIT = By
			.xpath(".//*[@id='UITopicForm']//button[contains(text(),'Submit')]");
	public final By ELEMENT_CKEDITOR_BUTTON_PREVIEW = By
			.xpath(".//*[@id='UITopicForm']//button[contains(text(),'Preview')]");

	// --------------------------------------------common
	// functions-------------------------------------------------------

	/**
	 * Setup a new CKeditor with a driver and plf version
	 * @param dr         new driver
	 * @param plfVersion The version of platform
	 */
	public CKeditor(WebDriver dr, String... plfVersion) {
		driver = dr;
		this.plfVersion = plfVersion.length > 0 ? plfVersion[0] : "4.0";
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		foruBas = new ForumBase();
	}

	/**
	 * To return new line into the content
	 */
	public void cke_ReturnLine() {
		if (isElementPresent(ELEMENT_CKEDITOR_TEXT_CONTENT)) {
			el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.END);
			el.sendKeys(Keys.ENTER);
		}
	}

	/**
	 * Maximize/Minize CKeditor
	 */
	public void cke_Max_Minmize() {
		if (isElementPresent(ELEMENT_CKEDITOR_MAXIMIZE))
			click(ELEMENT_CKEDITOR_MAXIMIZE);
	}

	/**
	 * Select a text and click on cut button
	 */
	public void cke_Cut() {
		if (isElementPresent(ELEMENT_CKEDITOR_TEXT_CONTENT)) {
			info("cut a text");
			el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			el.sendKeys(Keys.chord(Keys.CONTROL, "x"));
		}
	}

	/**
	 * Select all the text and click on Copy button
	 */
	public void cke_Copy() {
		if (isElementPresent(ELEMENT_CKEDITOR_TEXT_CONTENT)) {
			info("copy a text");
			el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			el.sendKeys(Keys.chord(Keys.CONTROL, "c"));
		}
	}

	/**
	 * Click on paste button
	 */
	public void cke_Paste() {
		if (isElementPresent(ELEMENT_CKEDITOR_TEXT_CONTENT)) {
			info("Paste a text");
			el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.END);
			el.sendKeys(Keys.ENTER);
			el.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		}
	}

	/**
	 * Click on undo button
	 */
	public void cke_Undo() {
		if (isElementPresent(ELEMENT_CKEDITOR_UNDO))
			click(ELEMENT_CKEDITOR_UNDO);
	}

	/**
	 * Click on redo button
	 */
	public void cke_Redo() {
		if (isElementPresent(ELEMENT_CKEDITOR_REDO))
			click(ELEMENT_CKEDITOR_REDO);
	}

	/**
	 * Select all the text and click on bold button
	 * 
	 */
	public void cke_Bold() {
		if (isElementPresent(ELEMENT_CKEDITOR_BOLD)) {
			info("Bold a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_BOLD);
		}
	}

	/**
	 * Select all the text and click on italic button
	 * 
	 */
	public void cke_Italic() {
		if (isElementPresent(ELEMENT_CKEDITOR_ITALIC)) {
			info("italic a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_ITALIC);
		}
	}

	/**
	 * Select all the text and click on underline button
	 * 
	 */
	public void cke_Underline() {
		if (isElementPresent(ELEMENT_CKEDITOR_UNDERLINE)) {
			info("Underline a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_UNDERLINE);
		}
	}

	/**
	 * Click on align left to put the text on the left side
	 */
	public void cke_AlignLeft() {
		if (isElementPresent(ELEMENT_CKEDITOR_ALIGNLEFT)) {
			info("Align left a text");
			click(ELEMENT_CKEDITOR_ALIGNLEFT);
		}
	}

	/**
	 * Click on align right to put the text on the right side
	 */
	public void cke_AlignRight() {
		if (isElementPresent(ELEMENT_CKEDITOR_ALIGNRIGHT)) {
			info("Align right a text");
			click(ELEMENT_CKEDITOR_ALIGNRIGHT);
		}
	}

	/**
	 * Click on center button to put the text in the center
	 */
	public void cke_Center() {
		if (isElementPresent(ELEMENT_CKEDITOR_CENTER)) {
			info("put a text in the center");
			click(ELEMENT_CKEDITOR_CENTER);
		}
	}

	/**
	 * Click on justify button
	 */
	public void cke_Justify() {
		if (isElementPresent(ELEMENT_CKEDITOR_JUSTIFY)) {
			info("justify a text");
			click(ELEMENT_CKEDITOR_JUSTIFY);
		}
	}

	/**
	 * Select all the text and click on Insert/remove numeric list
	 * 
	 */
	public void cke_InsertRemoveNumList() {
		if (isElementPresent(ELEMENT_CKEDITOR_INSERT_REMOVE_NUMLIST)) {
			info("insert/remove number list of a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_INSERT_REMOVE_NUMLIST);
		}
	}

	/**
	 * Select all the text and click on Insert/remove bullet list
	 * 
	 */
	public void cke_InsertRemoveBulletList() {
		if (isElementPresent(ELEMENT_CKEDITOR_INSERT_REMOVE_BULLETLIST)) {
			info("insert/remove bullet list of a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_INSERT_REMOVE_BULLETLIST);
		}
	}

	/**
	 * Select all the text and click on Decrease indent button
	 * 
	 */
	public void cke_DescrIndent() {
		if (isElementPresent(ELEMENT_CKEDITOR_DECREASE_INDENT)) {
			info("decrease indent of a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_DECREASE_INDENT);
		}
	}

	/**
	 * Select all the text and click on Increase indent button
	 * 
	 */
	public void cke_InscrIndent() {
		if (isElementPresent(ELEMENT_CKEDITOR_INCREASE_INDENT)) {
			info("increase indent of a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_INCREASE_INDENT);
		}
	}

	/**
	 * Select all the text. Then click on Text Color button and select a color
	 * into the table
	 * @param codeColor get a code of a color into the color table
	 */
	public void cke_PaintColorText(String codeColor) {
		if (isElementPresent(ELEMENT_CKEDITOR_INCREASE_INDENT)) {
			info("paint a text");
			WebElement el = driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT);
			el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			click(ELEMENT_CKEDITOR_TEXT_COLOR);
			// select a color into the color table
			waitForAndGetElement(ELEMENT_CKEDITOR_TEXT_COLOR_STYLE);
			click(By.xpath(ELEMENT_CKEDITOR_TEXT_COLOR_STYLE.replace(
					"${codeColor}", codeColor)));
		}
	}

	/**
	 * Click on Block Quote button
	 */
	public void cke_BlockQuote() {
		if (isElementPresent(ELEMENT_CKEDITOR_BLOCK_QUOTE)) {
			info("block quote a text");
			click(ELEMENT_CKEDITOR_BLOCK_QUOTE);
		}
	}

	/**
	 * Open the Source code tab Click on dropbox and select a language into the
	 * list
	 * @param nameLang the name of the language will be selected into the dropdown list
	 */
	public void cke_addUpdateCodeSourCode(String nameLang) {
		if (isElementPresent(ELEMENT_CKEDITOR_ADD_UPDATE_CODE)) {
			info("add a source code with source code tab");
			click(ELEMENT_CKEDITOR_ADD_UPDATE_CODE);
			// open the language list
			waitForAndGetElement(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_LANGUAGE_BOX);
			click(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_LANGUAGE_BOX);
			// select a languege into the list
			waitForAndGetElement(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_LANGUAGE_BOX);
			click(By.xpath(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_SOURCECODE_LANGUAGE_ITEM
					.replace("${nameLang}", nameLang)));
			click(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_OK_BUTTON);
		}

	}

	/**
	 * open Advance tab select the checkboxes
	 */
	public void cke_addUpdateCodeAdvance() {
		if (isElementPresent(ELEMENT_CKEDITOR_ADD_UPDATE_CODE)) {
			info("add a source code with advance tab");
			click(ELEMENT_CKEDITOR_ADD_UPDATE_CODE);
			// open Advance tab
			waitForAndGetElement(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_ADVANCE_TAB);
			click(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_ADVANCE_TAB);
			// select the checkboxes
			// ...................
			click(ELEMENT_CKEDITOR_ADD_UPDATE_CODE_OK_BUTTON);
		}
	}

	/**
	 * Open the BB code help popup
	 */
	public void cke_BBCodeHelp() {
		if (isElementPresent(ELEMENT_CKEDITOR_ADD_UPDATE_CODE)) {
			info("open BB code help");
			click(ELEMENT_CKEDITOR_HELP_BB_CODE);
		}
	}

	/**
	 * Resize the popup
	 * @param x 
	 * @param y
	 */
	public void cke_resize(int x, int y) {
		if (isElementPresent(ELEMENT_CKEDITOR_BUTTON_RESIZE)) {
			info(" click and hold Pan button");
			el = driver
					.findElement(ELEMENT_CKEDITOR_BUTTON_RESIZE);
			
			Actions builder = new Actions(driver);
			builder.clickAndHold(el).moveByOffset(x,y).build()
					.perform();
		}
	}

	/**
	 * Input a text into the content
	 * @param tex the text will be input into the content
	 */
	public void inputDataInContent(String tex) {
		info("Input data in the content section");
		if (isElementPresent(ELEMENT_CKEDITOR_TEXT_CONTENT))
			if (tex != "" && tex != null) {
				if (waitForAndGetElement(
						mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, 5000, 0) != null)
					inputDataToFrame(
							mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, tex,
							false);
				else
					// (this.plfVersion.equalsIgnoreCase("4.0"))
					foruBas.inputDataToFrameInFrame(
							mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_1,
							mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_2, tex, true,
							false);
				switchToParentWindow();
			}
	}

	/**
	 * Put the focus into the content
	 */
	public void focusDataInContent() {
		info("Input data in the content section");
		if (isElementPresent(ELEMENT_CKEDITOR_TEXT_CONTENT))
		//	if (tex != "" && tex != null) {
				if (waitForAndGetElement(
						mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, 5000, 0) != null)
					    driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT).sendKeys(Keys.ARROW_LEFT);
				else
					// (this.plfVersion.equalsIgnoreCase("4.0"))
					driver.findElement(ELEMENT_CKEDITOR_TEXT_CONTENT).sendKeys(Keys.ARROW_LEFT);
					/*foruBas.inputDataToFrameInFrame(
							mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_1,
							mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_2, tex, true,
							false);*/
				switchToParentWindow();
			//}
	}

	/**
	 * Close the popup with no any changes
	 */
	public void cancelPopup() {
		if (isElementPresent(ELEMENT_CKEDITOR_BUTTON_CANCEL))
			click(ELEMENT_CKEDITOR_BUTTON_CANCEL);
	}

	/**
	 * Open the popup under preview mode
	 */
	public void previewPopup() {
		if (isElementPresent(ELEMENT_CKEDITOR_BUTTON_PREVIEW))
			click(ELEMENT_CKEDITOR_BUTTON_PREVIEW);
	}

	/**
	 * Save all changes of the popup
	 */
	public void submitPopup() {
		if (isElementPresent(ELEMENT_CKEDITOR_BUTTON_SUBMIT))
			click(ELEMENT_CKEDITOR_BUTTON_SUBMIT);
	}

	/**
	 * Verify an element displays or not on the page
	 * @param locator  the locator of the element on the page
	 * */
	public boolean isElementPresent(By locator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return driver.findElements(locator).size() > 0;
	}

}
