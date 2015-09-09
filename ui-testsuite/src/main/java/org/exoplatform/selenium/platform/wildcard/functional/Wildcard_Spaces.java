package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Wildcard_Spaces extends Wildcard_TestConfig_5{

	/**
	*<li> Case ID:128078.</li>
	*<li> Test Case Name: Check "Administrator" role in Space Members of an user with wildcard membership type.</li>
	*<li> Pre-Condition: - SPACEA already existed.
	- UserA has NOT joined SPACE A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAdministratorRoleInSpaceMembersOfAnUserWithWildcardMembershipType() {
		info("Test 1: Check Administrator role in Space Members of an user with wildcard membership type");
		/*Step Number: 1
		*Step Name: Step 1:Add user to Space group with wildcard membership.
		*Step Description: 
			- Log in by Admin user.
			- Go to Group Management.
			- Add userA to SPACEA with * membership (e.g *:/spaces/SPACEA)
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is added to Space group with wildcard membership.*/
		
		/*Step number: 2
		*Step Name: Step 2:Check userA is listed in Members tab as Administrator role.
		*Step Description: 
			- Log in by UserA.
			- Go to MY SPACES > All Spaces.
			- SPACEA > Members tab.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is listed as Administrator of Space.
			- UserA can manage members of the space*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToAllSpace();
		spaHome.openSpace(spaceA,true);
		spaMg.verifyManager(arrayUsers.get(0), true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA is NOT listed in Members tab as Administrator role.
		*Step Description: 
			- Log in as Admin user.
			- Go to Group Management.
			- Remove userA from SPACEA.
			- Open SPACE A.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is NOT listed in Members tab of SPACE A.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		removeUserSpace();
		hp.goToSpecificSpace(spaceA);
		spaMg.verifyManager(arrayUsers.get(0), false);
		
		info("delete data");
		deleteAllUsers();
 	}

	/**
	*<li> Case ID:128077.</li>
	*<li> Test Case Name: Check "Manager" role in Space Settings of an user with wildcard membership type.</li>
	*<li> Pre-Condition: - SPACEA already existed.
	- UserA has not joined SPACEA.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckManagerRoleInSpaceSettingsOfAnUserWithWildcardMembershipType() {
		info("Test 2: Check Manager role in Space Settings of an user with wildcard membership type");
		/*Step Number: 1
		*Step Name: Step 1:Add userA to Space with * membership.
		*Step Description: 
			- Log in by Admin user.
			- Go to Group Management.
			- Add userA to SpaceA with * membership (e.g *:/spaces/SPACEA).
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is added to Space group with * membership.*/

		/*Step number: 2
		*Step Name: Step 2:Check UserA is listed in Space Settings as a manager.
		*Step Description: 
			- Log in by UserA.
			- Go to MY SPACES > All Spaces.
			- Open SPACEA > Space Settings.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is listed as Manager in Space Settings.
			- Switch button "Yes/No": Value = Yes; and disable for switching.
			- Delete button is disappeared.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToAllSpace();
		spaHome.openSpace(spaceA,true);
		spaHome.goToSpaceSettingTab();
		spaSetMg.verifyMember(arrayUsers.get(0), true);
		spaSetMg.verifyPermOfMember(arrayUsers.get(0), false);
		
		/*Step number: 3
		*Step Name: Step 3:Remove UserA from Space
		*Step Description: 
			- Log in by Admin user.
			- Go to Group Management.
			- Remove userA from SPACEA.
			- Go to SPACEA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is NOT listed in Space Settings.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		removeUserSpace();
		hp.goToSpecificSpace(spaceA);
		spaHome.goToSpaceSettingTab();
		spaSetMg.verifyMember(arrayUsers.get(0), false);
		
		info("delete data");
		deleteAllUsers();
 	}

	/**
	*<li> Case ID:128076.</li>
	*<li> Test Case Name: Check accessing "Space Settings" of user with wildcard membership type.</li>
	*<li> Pre-Condition: - SPACEA already existed.
	- UserA has NOT joined SPACEA yet.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckAccessingSpaceSettingsOfUserWithWildcardMembershipType() {
		info("Test 3: Check accessing Space Settings of user with wildcard membership type");
		/*Step Number: 1
		*Step Name: Step 1:Add user to Space group with wildcard membership.
		*Step Description: 
			- Log in Admin user.
			- Go to Group Management.
			- Add userA to SPACEA with * membership (e.g *:/spaces/SPACEA)
		*Input Data: 
			
		*Expected Outcome: 
			- UserA is added to Space group with wildcard membership.*/

		/*Step number: 2
		*Step Name: Step 2:Check userA can access Space and Space Settings tab.
		*Step Description: 
			- Log in by UserA.
			- Go to MY SPACES > All Spaces 
			- SPACEA > Space Settings.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can access SPACEA.
			- UserA can access Space Settings.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToAllSpace();
		spaHome.openSpace(spaceA,true);
		spaHome.goToSpaceSettingTab();
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT access Space and Space Settings tab.
		*Step Description: 
			- Log in by Admin user.
			- Go to Group Management.
			- Remove userA from SPACE A.
			- Log in by userA.
			- Go to MY SPACES > All Spaces.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT access SPACEA.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		removeUserSpace();
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToAllSpace();
		spaHome.openSpace(spaceA,false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		deleteAllUsers();
 	}}