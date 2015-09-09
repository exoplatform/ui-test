package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Wildcard_Forum extends Wildcard_TestConfig_3{

	/**
	*<li> Case ID:128044.</li>
	*<li> Test Case Name: Check "Moderator" permission in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A. UserB is NOT member of GROUP A.
	- Category Cat A already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckModeratorPermissionInACategoryWithWildcardMembershipType() {
		info("Test 1: Check Moderator permission in a category with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set Moderator permission in a category for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			-Go to Forum > Add/Edit Category CatA > Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Tick on "Moderator" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to moderate CatA.*/
		forumCatMag.editPermOfCategory(cat, groupA, "*",true,false,false,false);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can edit/moderate in CatA.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > CatA
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can edit/moderate the forums in CatA: "More Actions" and "Moderation" menus are displayed.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		forumMag.checkDisplayOfForumManage(forum, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT edit/moderate in CatA.
		*Step Description: 
			- Log in as userB.
			- Go to Forum > CatA
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit/moderate the forums under CatA.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToForum();
		forumMag.checkDisplayOfForumManage(forum, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128048.</li>
	*<li> Test Case Name: Check "Moderator" permission in a forum with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A. UserB is NOT member of GROUP A.
	- FORUM A already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckModeratorPermissionInAForumWithWildcardMembershipType() {
		info("Test 2: Check Moderator permission in a forum with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Moderator permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			-Forum > Add/Edit Forum FORUM A> Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Tick on "Moderartor" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to moderate the forum.*/
		forumMag.editPermOfForum(forum, groupA, "*",true,false,false,false);
		
		/*Step number: 2
		*Step Name: Step 2: Check userA can moderate FORUM A.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > FORUM A
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can work with "More Actions" menu and "Moderation" menu in FORUM A and topics in FORUM A.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkDisplayOfTopicManage(forum,topic, true);
		
		/*Step number: 3
		*Step Name: Step 3: Check userB can moderate FORUM A.
		*Step Description: 
			- Log in as userB.
			- Go to Forum > FORUM A
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit/moderate in FORUM A.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToForum();
		foTopic.checkDisplayOfTopicManage(forum,topic, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128046.</li>
	*<li> Test Case Name: Check "Permission to post replies" in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A. UserB is NOT member of GROUP A.
	- Category Cat A already existed with forums</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckPermissionToPostRepliesInACategoryWithWildcardMembershipType() {
		info("Test 3: Check Permission to post replies in a category with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Permission to post replies" to a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			-Go to Forum > Add/Edit Category CatA > Permissions.
			- Select Role: Add GROUP A with wildcard membership.
			- Tick on "Permission to Post replies" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant the permission to post replies in topics of forums in CatA.*/
		forumCatMag.editPermOfCategory(cat, groupA, "*",false,false,true,false);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can post the reply in topics of forums in CatA.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > CatA
			- Open a Forum > Open a Topic.
		*Input Data: 
			
		*Expected Outcome: 
			- The button "Post Reply" is enable
			- UserA can post a reply and quick reply in topics of the forum.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkEnableOfPostReply(topic, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT post the reply in topics of forums in CatA.
		*Step Description: 
			- Log in as userB.
			- Go to Forum > CatA
			- Open a Forum > Open a Topic.
		*Input Data: 
			
		*Expected Outcome: 
			- The button "Post Reply" is NOT enable
			- UserA can NOT post a reply and quick reply in topics of the forum.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER3,DATA_PASS);
		hp.goToForum();
		foTopic.checkEnableOfPostReply(topic, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128050.</li>
	*<li> Test Case Name: Check "Permission to post replies" in a forum with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- FORUM A already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckPermissionToPostRepliesInAForumWithWildcardMembershipType() {
		info("Test 4: Check Permission to post replies in a forum with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Permission to post replies" for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with a Forum manager
			-Forum > Add/Edit Forum FORUM A> Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Keep checkbox "Permission to post replies" is ticked.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to post relies in topics of the forum.*/
		forumMag.editPermOfForum(forum, groupA, "*",false,false,true,false);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Permission to post replies" of an user with wildcard membership in the group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Forum 
			-
			-> FORUM A
			- Open a Topic
		*Input Data: 
			
		*Expected Outcome: 
			- The button "Post Reply" is enable
			- User A can post a reply/ quick reply in topics of FORUM A.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkEnableOfPostReply(topic, true);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128045.</li>
	*<li> Test Case Name: Check "Permission to start topics" in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A. UserB is NOT member of GROUP A.
	- Category Cat A already existed with forums.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckPermissionToStartTopicsInACategoryWithWildcardMembershipType() {
		info("Test 5: Check Permission to start topics in a category with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		forumCatMag.addCategorySimple(cat,"",cat);
		forumMag.addForumSimple(forum,"",forum);
		/*Step Number: 1
		*Step Name: Step 1:Set "Permission to start topics" for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			-Go to Forum > Add/Edit Category CatA> Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Tick on "Permission to start topics" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to add topic in forums of CatA.*/
		forumCatMag.editPermOfCategory(cat, groupA, "*",false,true,false,false);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can start topic in forums of CatA.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > CatA.
			- Open a Forum
		*Input Data: 
			
		*Expected Outcome: 
			- The button "Start a Topic" is enable.
			- UserA can add topics to the forums of CatA.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		forumMag.checkEnableOfStartTopic(forum, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT start topic in forums of CatA.
		*Step Description: 
			- Log in as userB.
			- Go to Forum > CatA.
			- Open a Forum
		*Input Data: 
			
		*Expected Outcome: 
			- The button "Start a Topic" is NOT enable.
			- UserB can NOT add topics to the forums of CatA.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER3,DATA_PASS);
		hp.goToForum();
		forumMag.checkEnableOfStartTopic(forum, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128049.</li>
	*<li> Test Case Name: Check "Permission to start topics" in a Forum with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- FORUM A already exist and User A has not access</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckPermissionToStartTopicsInAForumWithWildcardMembershipType() {
		info("Test 6: Check Permission to start topics in a Forum with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		forumCatMag.addCategorySimple(cat,"",cat);
		forumMag.addForumSimple(forum,"",forum);
		/*Step Number: 1
		*Step Name: Step 1:Set "Permission to start topics" for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			-Forum > Add/Edit Forum FORUM A > Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Keep checkbox "Permission to start topics" is ticked.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to start topics in the forum*/
		forumMag.editPermOfForum(forum, groupA, "*",false,true,false,false);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Permission to start topics" of an user in this group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Forum > FORUM A
		*Input Data: 
			
		*Expected Outcome: 
			- The button "Start a Topic" is enable
			- User A can add a topic to FORUM A*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		forumMag.checkEnableOfStartTopic(forum, true);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128047.</li>
	*<li> Test Case Name: Check "Permission to view posts" in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A. UserB is NOT member of GROUP A.
	- Category Cat A already existed with forums.</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/FORUM-1185
	*/
	@Test (groups="pending")
	public  void test07_CheckPermissionToViewPostsInACategoryWithWildcardMembershipType() {
		info("Test 7: Check Permission to view posts in a category with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Permission to view posts" to a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Forum > Add/Edit Category CatA> Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Tick checkbox "Permission to view posts" only.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to only view the posts in topics of forums of this category.*/
		forumCatMag.editPermOfCategory(cat, groupA, "*",false,false,false,true);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view the posts in topics of CatA.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > CatA
			- Open a Forum / Open any topic.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can see the posts in each topics.
			- The buttons "Start a topic" and "Post Reply" are disable*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkEnableOfViewPost(forum, topic, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT view the posts in topics of CatA.
		*Step Description: 
			- Log in as userB.
			- Go to Forum.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT see any topic in forum of CatA.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER3,DATA_PASS);
		hp.goToForum();
		foTopic.checkEnableOfViewPost(forum, topic, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128052.</li>
	*<li> Test Case Name: Check "Permission to view posts" in a forum with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- FORUM A already existed.</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/FORUM-1185
	*/
	@Test (groups="pending")
	public  void test08_CheckPermissionToViewPostsInAForumWithWildcardMembershipType() {
		info("Test 8: Check Permission to view posts in a forum with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Permission to view posts" for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with a Forum manager
			-Forum > Add/Edit Forum FORUM A> Permissions tab.
			- Select Role: Add GROUP A with wildcard membership.
			- Keep checkbox "Permission to view posts" only is ticked.
		*Input Data: 
			
		*Expected Outcome: 
			Users in group are grant permission to only view the posts in topics of the forum.*/
		forumMag.editPermOfForum(forum, groupA, "*",false,false,false,true);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Permission to view posts" of an user with wildcard membership in this group.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > FORUM A
			- Open the topics.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can see the posts in each topics.
			- The buttons "Start a Topic" and "Post Reply" are disable.
			- User A cannot Start a topic in FORUM A; and can not post a reply in topics of FORUM A.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkEnableOfViewPost(forum, topic, true);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128053.</li>
	*<li> Test Case Name: Check "Restrict who can post in this topic to" in a topic with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckRestrictWhoCanPostInThisTopicToInATopicWithWildcardMembershipType() {
		info("Test 9: Check Restrict who can post in this topic to in a topic with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Restrict who can post in this topic to" for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with a Forum manager
			-Forum > Open a Forum >Add/Edit a Topic TOPIC1> Permissions tab.
			- Select Role: add a group with a wildcard permission (User A in this group)
			- Keep checkbox "Restrict who can post in this topic to" is ticked.
		*Input Data: 
			
		*Expected Outcome: 
			- User A is grant permission to post in this topic*/
		foTopic.editPermOfTopic(topic, groupA, "*",true, false);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Restrict who can post in this topic to" permission of an user in this group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Forum 
			-
			-> Open the forum.
			- Open the topic TOPIC1.
		*Input Data: 
			
		*Expected Outcome: 
			- User can post the replies/quick reply in TOPIC1.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkEnableOfPostReply(topic, true);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128051.</li>
	*<li> Test Case Name: Check "Restrict who can view in this topic to" in a topic with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/FORUM-1185
	*/
	@Test (groups= "pending")
	public  void test10_CheckRestrictWhoCanViewInThisTopicToInATopicWithWildcardMembershipType() {
		info("Test 10 Check Restrict who can view in this topic to in a topic with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		addCatForumTopic(cat,forum,topic);
		/*Step Number: 1
		*Step Name: Step 1:Set "Restrict who can view in this topic to" permission to a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with a Forum manager
			-Forum > Open a Forum >Add/Edit a Topic > Permissions tab.
			- Select Role: add a group with a wildcard permission (User A in this group)
			- Keep checkbox "Restrict who can view in this topic to" only is ticked.
		*Input Data: 
			
		*Expected Outcome: 
			- User A is grant permission to only view the posts in the topic.*/
		foTopic.editPermOfTopic(topic, groupA, "*",false, true);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Restrict who can view in this topic to" permission of an user in this group
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Forum 
			-
			-> Open the forum
			- Open the Topic
		*Input Data: 
			
		*Expected Outcome: 
			- User can view the posts in the topic.
			- The button "Post Reply" is disable
			- User A cannot post a reply/quick reply in this topic.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		foTopic.checkEnableOfPostReply(topic, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128043.</li>
	*<li> Test Case Name: Check "Restricted Audience" permission in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A. UserB is NOT member of GROUP A.
	- Category CatA already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckRestrictedAudiencePermissionInACategoryWithWildcardMembershipType() {
		info("Test 11 Check Restricted Audience permission in a category with wildcard membership type");
		String cat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String forum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToForum();
		forumCatMag.addCategorySimple(cat,"",cat);
		forumMag.addForumSimple(forum,"",forum);
		/*Step Number: 1
		*Step Name: Step 1:Set Restricted Audience of a category for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Forum > Add/Edit Category CatA > Restricted Audience.
			- Select Role: select GROUP A with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to access CatA.*/
		forumCatMag.editRestrictedAudience(cat, groupA, "*");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can access CatA.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > CatA
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can access CatA.
			- Forums of CatA are displayed.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		forumCatMag.checkDisplayOfCat(cat, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT access CatA.
		*Step Description: 
			- Log in as userB.
			- Go to Forum.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT view/access CatA.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER3,DATA_PASS);
		hp.goToForum();
		forumCatMag.checkDisplayOfCat(cat, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		deleteCategory(cat);
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128054.</li>
	*<li> Test Case Name: Check sending a Private Message to a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- UserB is NOT member of GROUP A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckSendingAPrivateMessageToAGroupWithWildcardMembershipType() {
		info("Test 12 Check sending a Private Message to a group with wildcard membership type");
		String title =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1:Send Private Message to a group with wildcard membership.
		*Step Description: 
			- Log in by any user.
			- Go to Forum > Private Messages > Compose New Message > Send to field.
			- Select Role: select GROUP A with wildcard membership.
			- Enter title and content of message. Send this message.
		*Input Data: 
			
		*Expected Outcome: 
			- Group with wildcard membership will be filled in "Send to" field.
			- Message is sent.*/
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.goComposeMessage();
		msgManage.writeMessageToGroup(groupA,"*", title, content);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can receive the Private Message.
		*Step Description: 
			- Log in as userA.
			- Go to Forum > Private Messages.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can receive this private message.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.checkDisplayOfMessage(title, DATA_USER1, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT receive the Private Message.
		*Step Description: 
			- Log in as userB.
			- Go to Forum > Private Messages.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT receive this private message.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2,DATA_PASS);
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.checkDisplayOfMessage(title, DATA_USER1, false);
 	}}