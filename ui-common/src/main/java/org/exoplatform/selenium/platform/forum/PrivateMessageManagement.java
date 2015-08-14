package org.exoplatform.selenium.platform.forum;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrivateMessageManagement extends PlatformBase{
	PlatformPermission per;
	ManageAlert alert;
	Button button;
	ForumPermission forumPerm;
	// tab elements
	public By ELEMENT_TABS_SENT_MESSAGES = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Sent Messages')]");
	public By ELEMENT_TABS_INBOX = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Inbox')]");
	public By ELEMENT_TABS_COMPOSE_MESSAGE = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Compose New Message')]");
	public By ELEMENT_PRIVATE_MESSAGE_CANCEL_BUTTON = By.xpath("//*[@id='UIPrivateMessageForm']//*[contains(text(),'Cancel')]");
	
	//send messages
	public By ELEMENT_SEND_TO_MESSAGE = By.id("SendTo");
	public By ELEMENT_TITLE_MESSAGE = By.id("MailTitle");
	public By ELEMENT_MESSAGE_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public By ELEMENT_SEND_BUTTON = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='uiAction']//*[contains(text(),'Send')]");
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_GROUP_SELECTOR = By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconGroup')]");
	public final String ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP=".//*[contains(@title,'${name}')]";
	public final String ELEMENT_PRIVATE_MESSAGE_SELECT_A_GROUP=".//*[@id='UIGroupSelector']//*[contains(text(),'Select this Group')]";
	public final String ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY = "Your message was sent successfully.";
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_MEMBERSHIP_SELECTOR = By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconMembership uiIconLightGray')]");
	public final String ELEMENT_PRIVATE_MESSAGE_SELECT_A_MEMBERSHIP=".//*[@id='UIMemberShipSelector']//*[contains(text(),'${membership}')]";
	
	//inbox
	public String ELEMENT_TITLE_AUTHORS_INBOX = "//*[@id='UIListInBoxPrivateMessage']//*[contains(text(),'{$author}')]/../..//*[contains(text(),'{$title}')]";
	public String ELEMENT_CONTACT_INBOX = "//*[@id='PermissionInfo']//*[contains(text(),'{$contact}')]";
	public String ELEMENT_CONTENT_INBOX = "//*[@id='uiViewPrivateMessage']//*[contains(text(),'{$content}')]";
	public By ELEMENT_REPLY = By.xpath("//*[@id='uiViewPrivateMessage']//*[@class='uiIconReply uiIconLightGray']");
	public String ELEMENT_DELETE_MESSAGE = "//*[@id='UIListInBoxPrivateMessage']//*[contains(text(),'{$title}')]/../../..//*[contains(text(),'{$contact}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	
	// outbox
	public String ELEMENT_FORWARD_MESSAGE = "//*[@id='UIListSentPrivateMessage']//*[contains(text(),'{$title}')]/../../..//*[contains(text(),'{$contact}')]/../..//*[@class='uiIconForumForward uiIconForumLightGray']";
	
	public By ELEMENT_CONFIRM = By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'OK')]");
	
	/**
	 * constructor
	 * @param dr
	 */
	public PrivateMessageManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(driver);
		button = new Button(driver);
		forumPerm = new ForumPermission(driver);
	}
	
	/**
	 * Go send message tab
	 */
	public void goSendMessages(){
		click(ELEMENT_TABS_SENT_MESSAGES);
	}

	/**
	 * Go inbox tab
	 */
	public void goInbox(){
		click(ELEMENT_TABS_INBOX);
	}
	/**
	 * Go compose tab
	 */
	public void goComposeMessage(){
		click(ELEMENT_TABS_COMPOSE_MESSAGE);
	}
	
	/**
	 * Go to Inbox tab
	 */
	public void gotoInboxTab(){
		click(ELEMENT_TABS_INBOX);
	}
	
	/**
	 * Write message
	 * @param contact
	 * @param title
	 * @param content
	 */
	public void writeMessage(String contact, String title, String content){
		type(ELEMENT_SEND_TO_MESSAGE,contact,true);
		type(ELEMENT_TITLE_MESSAGE,title,true);
		inputFrame(ELEMENT_MESSAGE_CONTENT, content);
		click(ELEMENT_SEND_BUTTON);
		button.ok();
		Utils.pause(2000);
	}
	/**
	 * Write message to group
	 * @param groupPath
	 * @param member
	 * @param title
	 * @param content
	 */
	public void writeMessageToGroup(String groupPath,String member, String title, String content){
		forumPerm.selectPermGroupMemberMes(groupPath, member);
		type(ELEMENT_TITLE_MESSAGE,title,true);
		inputFrame(ELEMENT_MESSAGE_CONTENT, content);
		click(ELEMENT_SEND_BUTTON);
		button.ok();
		Utils.pause(2000);
	}
	/**
	 * Check inbox message
	 * @param contact
	 * @param title
	 * @param content
	 */
	public void checkInboxMessage(String contact, String title, String content){
		click(By.xpath(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}",title).replace("{$author}",contact)));
		waitForAndGetElement(By.xpath(ELEMENT_CONTENT_INBOX.replace("{$content}",content)));
	}
	/**
	 * Check display of message
	 * @param title
	 * @param author
	 * @param isDisplay
	 */
	public void checkDisplayOfMessage(String title,String author,boolean isDisplay){
		if(isDisplay){
			waitForAndGetElement(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}",title).replace("{$author}",author));
		}else{
			waitForElementNotPresent(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}",title).replace("{$author}",author));
		}
	}
	/**
	 * check inbox message not found
	 * @param title
	 */
	public void checkInboxMessageNotFound(String title){
		waitForElementNotPresent(By.xpath(ELEMENT_CONTACT_INBOX.replace("{$content}",title)));
	}
	
	/**
	 * Reply a message
	 * @param contact
	 * @param title
	 * @param newTitle
	 * @param content
	 */
	public void replyMessage(String contact, String title, String newTitle, String content){
		click(By.xpath(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}",title).replace("{$author}",contact)));
		click(ELEMENT_REPLY);
		type(ELEMENT_TITLE_MESSAGE,newTitle,true);
		inputFrame(ELEMENT_MESSAGE_CONTENT, content);
		click(ELEMENT_SEND_BUTTON);
		button.ok();
	}
	
	/**
	 * Forward a message
	 * @param contact
	 * @param title
	 * @param newContact
	 * @param newTitle
	 * @param newContent
	 */
	public void forwardMessage(String contact, String title, String newContact,String newTitle, String newContent){
		click(By.xpath(ELEMENT_FORWARD_MESSAGE.replace("{$title}",title).replace("{$contact}",contact)));
		
		type(ELEMENT_SEND_TO_MESSAGE,newContact,true);
		if(newTitle!="")
			type(ELEMENT_TITLE_MESSAGE,newTitle,true);
		
		if(newContent!="")
			inputFrame(ELEMENT_MESSAGE_CONTENT, newContent);
		click(ELEMENT_SEND_BUTTON);
		button.ok();
	}
	
	/**
	 * Delete a message
	 * @param title
	 * @param contact
	 */
	public void deleteMessage(String title, String contact){
		click(By.xpath(ELEMENT_DELETE_MESSAGE.replace("{$title}",title).replace("{$contact}",contact)));
		click(ELEMENT_CONFIRM);
		waitForElementNotPresent(By.xpath(ELEMENT_TITLE_AUTHORS_INBOX.replace("{$title}",title).replace("{$author}",contact)));
	}
	
	/**
	 * Open Select Group Form
	 */

	public void openSelectGroupForm(){
		click(ELEMENT_COMPOSE_NEW_MESSAGE_GROUP_SELECTOR);
	}
	
	/**
	 * Open Select Membership Form
	 */

	public void openSelectMembershipForm(){
		click(ELEMENT_COMPOSE_NEW_MESSAGE_MEMBERSHIP_SELECTOR);
	}
	
	/**
	 * Write message to group
	 * @param group
	 */

	public void writeMessageToGroup(String group, String title, String content){
		click (ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP.replace("${name}", group));
		click (ELEMENT_PRIVATE_MESSAGE_SELECT_A_GROUP.replace("${name}", group));
		type(ELEMENT_TITLE_MESSAGE,title,true);
		inputFrame(ELEMENT_MESSAGE_CONTENT, content);
		click(ELEMENT_SEND_BUTTON);
		waitForMessage(ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY);
		button.ok();
		Utils.pause(2000);
	}
	
	/**
	 * Write message to group
	 * @param group
	 */

	public void writeMessageToMembership(String group, String membership, String title, String content){
		click (ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP.replace("${name}", group));
		click (ELEMENT_PRIVATE_MESSAGE_SELECT_A_MEMBERSHIP.replace("${membership}", membership));
		type(ELEMENT_TITLE_MESSAGE,title,true);
		inputFrame(ELEMENT_MESSAGE_CONTENT, content);
		click(ELEMENT_SEND_BUTTON);
		waitForMessage(ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY);
		button.ok();
		Utils.pause(2000);
	}
	
	/**
	 * Go inbox tab
	 */
	public void cancelPrivateMessage(){
		click(ELEMENT_PRIVATE_MESSAGE_CANCEL_BUTTON);
	}
}
