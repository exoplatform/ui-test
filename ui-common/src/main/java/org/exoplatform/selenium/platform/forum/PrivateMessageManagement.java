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
	
	//send messages
	public By ELEMENT_SEND_TO_MESSAGE = By.id("SendTo");
	public By ELEMENT_TITLE_MESSAGE = By.id("MailTitle");
	public By ELEMENT_MESSAGE_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public By ELEMENT_SEND_BUTTON = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='uiAction']//*[contains(text(),'Send')]");
	
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
}
