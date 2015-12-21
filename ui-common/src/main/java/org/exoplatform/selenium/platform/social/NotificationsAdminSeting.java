package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationsAdminSeting extends SocialLocator {
	
	/**
	 * constructor
	 * @param dr
	 */
	public NotificationsAdminSeting(WebDriver dr){
		this.driver=dr;
	}
	
	/**
	 * Check the title of Admin notification page
	 */
	public void verifyTilePage(){
		info("Verify the title of Notification Admin page");
		waitForAndGetElement(ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
		info("The page is shown");
	}
	
	/**
	 * Verify that a notification belongs to a category
	 * @param category
	 *                 is the category's name
	 * @param type
	 *                 is notification's type
	 */
	public void verifyNotiBelongToCategory(String category,notiMode type){
		switch(type){
		case NewUser:
			info("Verify that New user notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","NewUserPlugin"));
			info("The notification is shown with correct category");
			break;
		case ConnectionRequest:
			info("Verify that Connection Request notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","RelationshipReceivedRequestPlugin"));
			info("The notification is shown with correct category");
			break;
		case Space_Join:
			info("Verify that Space Join Request notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","RequestJoinSpacePlugin"));
			info("The notification is shown with correct category");
			break;
		case Space_Invitation:
			info("Verify that Space invitation notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","SpaceInvitationPlugin"));
			info("The notification is shown with correct category");
			break;
		case Space_Post:
			info("Verify that Post on My Spaces notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","PostActivitySpaceStreamPlugin"));
			info("The notification is shown with correct category");
			break;
		case AS_Post:
			info("Verify that Post on My Stream notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","PostActivityPlugin"));
			info("The notification is shown with correct category");
			break;
		case AS_Like:
			info("Verify that Like notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","LikePlugin"));
			info("The notification is shown with correct category");
			break;
		case AS_Mention:
			info("Verify that Mention notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","ActivityMentionPlugin"));
			info("The notification is shown with correct category");
			break;
		case AS_Comment:
			info("Verify that Comment notification belongs to "+category);
			waitForAndGetElement(ELEMENT_BELONGS_TO_CATEGORY.
					replace("$category",category).replace("$notification","ActivityCommentPlugin"));
			info("The notification is shown with correct category");
			break;
		}
		
	}
	/**
	 * Define notification's type
	 * as: New User for email, New User for intranet, Connection Request for email.....
	 */
	public enum notificationType{
		NewUser_email,NewUser_intranet,ConnectionRequest_email,ConnectionRequest_intranet,
		AS_Comment_email,AS_Comment_intranet,AS_Like_email,AS_Like_intranet,
		AS_Mention_email,AS_Mention_intranet,
		AS_Post_email,AS_Post_intranet,Space_Invitation_email,
		Space_Invitation_intranet,Space_Join_email,Space_Join_intranet,
		Space_Post_email,Space_Post_intranet;
	}
	/**
	 * Disable notification
	 * @param notifToDisable
	 */
	public void disableNotification(notificationType notifToDisable){
		switch(notifToDisable){
		case NewUser_email:
			info("Click on Edit button");
			click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
			uncheck(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case NewUser_intranet:
			info("Click on Edit button");
			click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
			uncheck(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
			info("Verify that intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case ConnectionRequest_email:
			click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
			uncheck(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case ConnectionRequest_intranet:
			click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
			uncheck(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Comment_email:
			click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Comment_intranet:
			click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Like_email:
			click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Like_intranet:
			click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Mention_email:
			click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Mention_intranet:
			click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Post_email:
			click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Post_intranet:
			click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
			uncheck(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Join_email:
			click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
			uncheck(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Join_intranet:
			click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
			uncheck(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Invitation_email:
			click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
			uncheck(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Invitation_intranet:
			click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
			uncheck(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Post_email:
			click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
			uncheck(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForElementNotPresent(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Post_intranet:
			click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
			uncheck(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForElementNotPresent(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		

		}
	}
	/**
	 * Enable notification
	 * @param notifToDisable
	 */
	public void enableNotification(notificationType notifToDisable){
		switch(notifToDisable){
		case NewUser_email:
			info("Click on Edit button");
			click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is shown");
			waitForAndGetElement(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case NewUser_intranet:
			info("Click on Edit button");
			click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
			info("Verify that intranet notification is shown");
			waitForAndGetElement(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case ConnectionRequest_email:
			click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
			info("Verify that email notification is shown");
			waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case ConnectionRequest_intranet:
			click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
			info("Verify that Intranet notification is shown");
			waitForAndGetElement(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Comment_email:
			click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Comment_intranet:
			click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Like_email:
			click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Like_intranet:
			click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Mention_email:
			click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Mention_intranet:
			click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Post_email:
			click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case AS_Post_intranet:
			click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForAndGetElement(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Join_email:
			click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Join_intranet:
			click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Invitation_email:
			click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Invitation_intranet:
			click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForAndGetElement(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Post_email:
			click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
			info("Verify that email notification is hidded");
			waitForAndGetElement(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE,3000,1);
			break;
		case Space_Post_intranet:
			click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
			if(waitForAndGetElement(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX_CHECKED,2000,0)==null)
				check(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX,2);
			info("Click on Save button");
			click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
			info("Verify that Intranet notification is hidded");
			waitForAndGetElement(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE,3000,1);
			break;
		}
	}
	/**
	 * Define notification plugin is disable all
	 */
	public enum notiMode{
		NewUser,ConnectionRequest,AS_Comment,AS_Like,
		AS_Mention,AS_Post,Space_Invitation,Space_Join,Space_Post;
	}
	
	/**
	 * Verify that notification's type is disabled all
	 * @param op
	 */
	public void veriftyNotificationTypeDisable(notiMode op){
		switch(op){
		case NewUser:
			info("Verify that New user is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_NEW_USER_DISBALE);
			break;
		case ConnectionRequest:
			info("Verify that Connection Request is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_DISBALE);
			break;
		case AS_Comment:
			info("Verify that Activity Comment is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_DISBALE);
			break;
		case AS_Like:
			info("Verify that Activity like is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_DISBALE);
			break;
		case AS_Mention:
			info("Verify that Activity mention is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_DISBALE);
			break;
		case AS_Post:
			info("Verify that Activity post is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_DISBALE);
			break;
		case Space_Invitation:
			info("Verify that Space invitation is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_DISBALE);
			break;
		case Space_Join:
			info("Verify that Space join is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_DISBALE);
			break;
		case Space_Post:
			info("Verify that Space post is disabled");
			waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_DISBALE);
			break;
		}
		
		
	}
	/**
	 * Change information of Notification Sender area
	 * @param name
	 * @param address
	 */
	public void changeNotificationSender(String name,String address){
		scrollToBottomPage(this.driver);
		if(!name.isEmpty()){
			info("Input name");
			type(ELEMENT_ADMIN_NOTIFICATION_SENDER_NAME,name,true);
		}
		if(!address.isEmpty()){
			info("Input address");
			type(ELEMENT_ADMIN_NOTIFICATION_SENDER_ADDRESS,address,true);
		}	
		info("Click on Save button");
		click(ELEMENT_ADMIN_NOTIFICATION_SENDER_SAVE_BTN);
	}
	/**
	 * Verify fail when input incorrect address in Notification Sender area
	 */
	public void verifyFailChangeNotificationSender(){
		info("Verify error message when input invalid email");
		waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_ERROR_MESSAGE_INVALID_EMAIL);
	}
	
	/**
	 * Verify success when input correct name and address in Notification Sender area
	 */
	public void verifySuccessChangeNotificationSender(String name,String email){
		info("Verify success message when input correct name and email");
		waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_SUCCESS_MESSAGE.replace("$name",name).replace("$email",email));
	}
	/**
	 * Reset all Notification by default
	 */
	public void resetAllChangedNotifications(){
		resetNewUserNotification();
		resetConnectRequestNotification();
		resetSpaceJoinNotification();
		resetSpaceInvitationNotification();
		resetSpacePostNotification();
		resetActivityLikeNotification();
		resetActivityMentionNotification();
		resetActivityPostNotification();
		resetActivityCommentNotification();
		changeNotificationSender("eXo","noreply@exoplatform.com");
	}
	/**
	 * Reset New User Notification
	 */
	public void resetNewUserNotification(){
		By newuser_email=ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE;
		By newuser_intranet=ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE;
		By newuser_disable=ELEMENT_ADMIN_NOTIFICATION_NEW_USER_DISBALE;
		
		if(waitForAndGetElement(newuser_email,3000,0)==null 
				|| waitForAndGetElement(newuser_intranet,3000,0)==null 
				|| waitForAndGetElement(newuser_disable,3000,0)!=null){
			info("Reset New User Notifiction");
			click(ELEMENT_NEW_USER_NOTIFICATION_EDIT_BTN);
			check(ELEMENT_NEW_USER_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_NEW_USER_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_NEW_USER_NOTIFICATION_SAVE_BTN);
			waitForAndGetElement(newuser_email);
			waitForAndGetElement(newuser_intranet);
		}
	}
	
	/**
	 * Reset Connection Request Notification
	 */
	public void resetConnectRequestNotification(){
		By connect_email=ELEMENT_NEW_USER_EMAIL_NOTIFICATION_TITLE;
		By connect_intranet=ELEMENT_NEW_USER_INTRANET_NOTIFICATION_TITLE;
		By connect_disable=ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_DISBALE;
		
		if(waitForAndGetElement(connect_email,3000,0)==null 
				|| waitForAndGetElement(connect_intranet,3000,0)==null 
				|| waitForAndGetElement(connect_disable,3000,0)!=null){
			info("Reset Connection Request Notifiction");
			click(ELEMENT_CONNECTION_REQUEST_EDIT_BTN);
			check(ELEMENT_CONNECTION_REQUEST_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_CONNECTION_REQUEST_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_CONNECTION_REQUEST_SAVE_BTN);
			waitForAndGetElement(connect_email);
			waitForAndGetElement(connect_intranet);
		}
	}
	/**
	 * Reset Space Join Notification
	 */
	public void resetSpaceJoinNotification(){
		By space_join_email=ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_TITLE;
		By space_join_intranet=ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_TITLE;
		By space_join_disable=ELEMENT_ADMIN_NOTIFICATION_SPACE_JOIN_DISBALE;
		
		if(waitForAndGetElement(space_join_email,3000,0)==null 
				|| waitForAndGetElement(space_join_intranet,3000,0)==null 
				|| waitForAndGetElement(space_join_disable,3000,0)!=null){
			info("Reset Space Join Notifiction");
			click(ELEMENT_SPACE_NOTIFICATION_JOIN_EDIT_BTN);
			check(ELEMENT_SPACE_JOIN_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_SPACE_JOIN_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_SPACE_JOIN_NOTIFICATION_SAVE_BTN);
			waitForAndGetElement(space_join_email);
			waitForAndGetElement(space_join_intranet);
		}
	}
	
	/**
	 * Reset Space Invitation Notification
	 */
	public void resetSpaceInvitationNotification(){
		By space_invitation_email=ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_TITLE;
		By space_invitation_intranet=ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_TITLE;
		By space_invitation_disable=ELEMENT_ADMIN_NOTIFICATION_SPACE_INVITATION_DISBALE;
		
		if(waitForAndGetElement(space_invitation_email,3000,0)==null 
				|| waitForAndGetElement(space_invitation_intranet,3000,0)==null 
				|| waitForAndGetElement(space_invitation_disable,3000,0)!=null){
			info("Reset Space invitation Notifiction");
			click(ELEMENT_SPACE_NOTIFICATION_INVITATION_EDIT_BTN);
			check(ELEMENT_SPACE_INVITATION_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_SPACE_INVITATION_NOTIFICATION_SAVE_BTN);
			waitForAndGetElement(space_invitation_email);
			waitForAndGetElement(space_invitation_intranet);
		}
	}
	
	/**
	 * Reset Space Post Notification
	 */
	public void resetSpacePostNotification(){
		By space_post_email=ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_TITLE;
		By space_post_intranet=ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_TITLE;
		By space_post_disable=ELEMENT_ADMIN_NOTIFICATION_SPACE_POST_DISBALE;
		
		if(waitForAndGetElement(space_post_email,3000,0)==null 
				|| waitForAndGetElement(space_post_intranet,3000,0)==null 
				|| waitForAndGetElement(space_post_disable,3000,0)!=null){
			info("Reset Space post Notifiction");
			click(ELEMENT_SPACE_NOTIFICATION_POST_EDIT_BTN);
			check(ELEMENT_SPACE_POST_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_SPACE_POST_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_SPACE_POST_NOTIFICATION_SAVE_BTN);
			waitForAndGetElement(space_post_email);
			waitForAndGetElement(space_post_intranet);
		}
	}
	
	/**
	 * Reset Activity Like Notification
	 */
	public void resetActivityLikeNotification(){
		By as_like_email=ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_TITLE;
		By as_like_intranet=ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_TITLE;
		By as_like_disable=ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_DISBALE;
		
		if(waitForAndGetElement(as_like_email,3000,0)==null 
				|| waitForAndGetElement(as_like_intranet,3000,0)==null 
				|| waitForAndGetElement(as_like_disable,3000,0)!=null){
			info("Reset Activity Like Notifiction");
			click(ELEMENT_ACTIVITY_LIKE_EDIT_BTN);
			check(ELEMENT_ACTIVITY_LIKE_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_ACTIVITY_LIKE_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_ACTIVITY_LIKE_SAVE_BTN);
			waitForAndGetElement(as_like_email);
			waitForAndGetElement(as_like_intranet);
		}
	}
	
	/**
	 * Reset Activity Mention Notification
	 */
    public void resetActivityMentionNotification(){
    	By as_mention_email=ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_TITLE;
		By as_mention_intranet=ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_TITLE;
		By as_mention_disable=ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_DISBALE;
		
		if(waitForAndGetElement(as_mention_email,3000,0)==null 
				|| waitForAndGetElement(as_mention_intranet,3000,0)==null 
				|| waitForAndGetElement(as_mention_disable,3000,0)!=null){
			info("Reset Activity Mention Notifiction");
			click(ELEMENT_ACTIVITY_MENTION_EDIT_BTN);
			check(ELEMENT_ACTIVITY_MENTION_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_ACTIVITY_MENTION_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_ACTIVITY_MENTION_SAVE_BTN);
			waitForAndGetElement(as_mention_email);
			waitForAndGetElement(as_mention_intranet);
		}
	}
    
    /**
	 * Reset Activity post Notification
	 */
    public void resetActivityPostNotification(){
    	By as_post_email=ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_TITLE;
		By as_post_intranet=ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_TITLE;
		By as_post_disable=ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_POST_DISBALE;
		
		if(waitForAndGetElement(as_post_email,3000,0)==null 
				|| waitForAndGetElement(as_post_intranet,3000,0)==null 
				|| waitForAndGetElement(as_post_disable,3000,0)!=null){
			info("Reset Activity post Notifiction");
			click(ELEMENT_ACTIVITY_POST_EDIT_BTN);
			check(ELEMENT_ACTIVITY_POST_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_ACTIVITY_POST_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_ACTIVITY_POST_SAVE_BTN);
			waitForAndGetElement(as_post_email);
			waitForAndGetElement(as_post_intranet);
		}
	}
    
    /**
	 * Reset Activity comment Notification
	 */
    public void resetActivityCommentNotification(){
    	By as_comment_email=ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_TITLE;
		By as_comment_intranet=ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_TITLE;
		By as_comment_disable=ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_DISBALE;
		
		if(waitForAndGetElement(as_comment_email,3000,0)==null 
				|| waitForAndGetElement(as_comment_intranet,3000,0)==null 
				|| waitForAndGetElement(as_comment_disable,3000,0)!=null){
			info("Reset Activity comment Notifiction");
			click(ELEMENT_ACTIVITY_COMMENT_EDIT_BTN);
			check(ELEMENT_ACTIVITY_COMMENT_EMAIL_NOTIFICATION_CHECKBOX,2);
			check(ELEMENT_ACTIVITY_COMMENT_INTRANET_NOTIFICATION_CHECKBOX,2);
			click(ELEMENT_ACTIVITY_COMMENT_SAVE_BTN);
			waitForAndGetElement(as_comment_email);
			waitForAndGetElement(as_comment_intranet);
		}
	}
    /**
     * Verify that a notification is enabled
     * @param type
     *           as NewUser_email, NewUser_intranet,...
     */
    public void verifyNotificationTypeEnable(notificationType type){
    	switch(type){
    	case NewUser_email:
    		break;
    	case NewUser_intranet:
    		break;
    	case AS_Comment_email:
    		info("Verify that email for comment notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_ENABLE_EMAIL);
    		info("The notification is shown successfully");
    		break;
    	case AS_Comment_intranet:
    		info("Verify that intranet for comment notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_COMMENT_ENABLE_INTRANET);
    		info("The notification is shown successfully");
    		break;
    	case AS_Like_email:
    		info("Verify that email for like notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_ENABLE_EMAIL);
    		info("The notification is shown successfully");
    		break;
    	case AS_Like_intranet:
    		info("Verify that intranet for like notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_LIKE_ENABLE_INTRANET);
    		info("The notification is shown successfully");
    		break;
    	case AS_Mention_email:
    		info("Verify that email for connection request notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_ENABLE_EMAIL);
    		info("The notification is shown successfully");
    		break;
    	case AS_Mention_intranet:
    		info("Verify that intranet for connection request notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_ACTIVITY_MENTION_ENABLE_INTRANET);
    		info("The notification is shown successfully");
    		break;
    	case AS_Post_email:
    		break;
    	case AS_Post_intranet:
    		break;
    	case ConnectionRequest_email:
    		info("Verify that email for connection request notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_ENABLE_EMAIL);
    		info("The notification is shown successfully");
    		break;
    	case ConnectionRequest_intranet:
    		info("Verify that intranet for connection request notification is shown");
    		waitForAndGetElement(ELEMENT_ADMIN_NOTIFICATION_CONNECTION_REQUEST_ENABLE_INTRANET);
    		info("The notification is shown successfully");
    		break;
    	case Space_Invitation_email:
    		break;
    	case Space_Invitation_intranet:
    		break;
    	case Space_Join_email:
    		break;
    	case Space_Join_intranet:
    		break;
    	case Space_Post_email:
    		break;
    	case Space_Post_intranet:
    		break;
    	}
    }
    /**
     * Verify the label of notification's types
     * @param label
     *               as: Someone comments on one of my activities,...
     */
    public void verifyLabelNotificationType(String label){
    	info("Verify that the label of Comment notification is correct");
		waitForAndGetElement(ELEMENT_NOTIFICATION_LABEL_NAME.replace("$label", label));
		info("the label is correct");
    	
    }
    /**
     * Verify the name of notification's types
     * @param name
     *              as: Comment, Like on Activity
     */
    public void verifyNameNotificationType(String name){
    	info("Verify that the label of Comment notification is correct");
		waitForAndGetElement(ELEMENT_NOTIFICATION_LABEL_NAME.replace("$label",name));
		info("the label is correct");
    }
   
}
