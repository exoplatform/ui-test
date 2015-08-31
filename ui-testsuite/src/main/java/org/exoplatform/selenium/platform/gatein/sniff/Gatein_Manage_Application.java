package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;


import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Gatein_Manage_Application extends GateIn_TestConfig{

	/**
	*<li> Case ID:123075.</li>
	*<li> Test Case Name: Import application.</li>
	*//*
	@Test
	public  void test02_ImportApplication() {
		info("Test 02: Import application");
		Step Number: 1
		*Step Name: Step 1: Import application		
		*Step Description: 
			- Go to Application registry
			- Click Auto import
		*Input Data: 
			
		*Expected Outcome: 
			All existing portlets are imported successfully and displayed on left list 
			( there are someones added categories: Integration,Navigation, System, etc)
		navToolBar.goToApplication();
		appRegistry.displayImportApplicaions();
		navToolBar.goToApplication();
		appRegistry.importAllApplications();
		
		info("Verify that all applications are imported successfully");
		appGateInData.getAllApplications();
		appRegistry.checkImportedApplications(appGateInData.newCategory,appGateInData.newName);
 	}

	*//**
	*<li> Case ID:123076.</li>
	*<li> Test Case Name:Show Import Application icon.</li>
	*/
	@Test
	public void test01_ShowImportApplicationIcon() {
		info("Test 01: Show Import Application icon");
		/*Step Number: 1
		*Step Name: Step 1: Show Import Application icon
		*Step Description: 
			- Go to Administration/Application registry
			- Click Edit this page
			- Choose edit portlet
			- Check import application
		*Input Data: 
			
		*Expected Outcome: 
			Show Import Application icon on Application registry portlet*/
		appRegistry.displayImportApplicaions();
		 

 	}

	/**
	*<li> Case ID:123084.</li>
	*<li> Test Case Name: Hide Import Application icon.</li>
	*/
	@Test
	public  void test03_HideImportApplicationIcon() {
		info("Test 3:Hide Import Application icon");
		/*Step Number: 1
		*Step Name: Step 1: Hide Import Application icon
		*Step Description: 
			- Go to Administration/Application registry
			- Click Edit this page
			- Choose edit portlet
			- Un-check import application
		*Input Data: 
			
		*Expected Outcome: 
			Import Application icon don't show in Application registry portlet 
*/			 
		appRegistry.HideShowImportApplicaion();
 	}

	/**
	*<li> Case ID:123092.</li>
	*<li> Test Case Name: Add category.</li>
	*<li> Case ID:123077.</li>
	*<li> Test Case Name: Delete category.</li>
	*/
	@Test
	public  void test05_15_Add_DeleteCategory() {
		info("Test 05: Add category");
		String categoryName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String displayName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Add category
		*Step Description: 
			- Go to Administration/Application registry
			- Choose category tab
			- Click Add category icon
			- Input some fields require
			- Click Save
	
		*Input Data: 
			
		*Expected Outcome: 
			- Add category successfully*/
			
		navToolBar.goToApplication();
		appRegistry.addACategory(categoryName, displayName, des);
		
		/*Step Number: 1
		*Step Name: Step 1: Delete category
		*Step Description: 
			- Go to Administration/Application registry
			- Choose category tab
			- Select a category and click Delete icon
		*Input Data: 
			
		*Expected Outcome: 
			- The category is removed from the list*/
		info("Test 15: Delete category");
		appRegistry.deleteCategory(categoryName);

 	}

	/**
	*<li> Case ID:123078.</li>
	*<li> Test Case Name: Add application into category.</li>
	*/
	@Test
	public  void test04_AddApplicationIntoCategory() {
		info("Test 04: Add application into category");
		String categoryName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String displayName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String desCategory= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index = appAddGateinData.getRandomIndexByType(1);
		String nameApp = appAddGateinData.newDisplayName.get(index);
		String des = appAddGateinData.newDescription.get(index);

		/*Step Number: 1
		*Step Name: Step 1: Add application into category
		*Step Description: 
			- Select a category to add application
			- Click Add application into category icon
			- Input display name and choose application type and click Add
		*Input Data: 
			
		*Expected Outcome: 
			Applications can be added into existing category*/
		navToolBar.goToApplication();
		appRegistry.addACategory(categoryName, displayName, desCategory);
		appRegistry.addApplicationToCategory(categoryName, nameApp,des);
		info("Delete the category");
		appRegistry.deleteCategory(categoryName);
	}

	/**
	*<li> Case ID:123079.</li>
	*<li> Test Case Name: View Portlets.</li>
	*/
	@Test
	public  void test06_ViewPortlets() {
		info("Test 06: View Portlets");
		int index = appGateInData.getRandomIndexByType(1);
		String dispayName = appGateInData.newName.get(index);
		String nameCategory = appGateInData.newCategory.get(index);
		String appName = appGateInData.newAppName.get(index);
		String des = appGateInData.newDes.get(index);
		/*Step Number: 1
		*Step Name: Step 1: View Portlets
		*Step Description: 
			- Go to Administration/Application registry/Portlet
		*Input Data: 
			
		*Expected Outcome: 
			Show content details of each application*/
	     navToolBar.goToApplication();
	     appRegistry.selectAPortlet(nameCategory,dispayName,false);
	     appRegistry.viewDetailPortlet(dispayName, appName, des);    
	}

	/**
	*<li> Case ID:123080.</li>
	*<li> Test Case Name: Add manual gadget.</li>
	**<li> Case ID:123086.</li>
	*<li> Test Case Name: Delete manual gadget.</li>
	*/
	@Test
	public  void test07_09_AddDelete_ManualGadget() {
		info("Test 07: Add manual gadget");
		int index = creatGateinData.getRandomIndexByType(1);
		String name = creatGateinData.newName.get(index);
		String xmlCode = creatGateinData.xmlCode.get(index);
		System.out.println(xmlCode);
		String xmlCode1 = creatGateinData.newXMLCode.get(index);
		System.out.println(xmlCode1);
		/*Step Number: 1
		*Step Name: Step 1: Add local gadget
		*Step Description: 
			- Choose Gadget tab
			- Click [Create a new gadget]
			- Input field require
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- Add local gadget successfully*/
		 navToolBar.goToApplication();
		 appRegistry.goToGadgetPage();
		 gadMg.createNewGadget(name, xmlCode);
		 info("Test 09: Delete manual gadget");
		 /*Step Number: 1
			*Step Name: Step 1: Delete local gadget
			*Step Description: 
				- Choose Gadget tab
				- Select a local gadget in list
				- Click Delete icon 
			*Input Data: 
				
			*Expected Outcome: 
				- The local gadget is removed from the list
				*/
		 gadMg.deleteGadget(name);
	}

	/**
	*<li> Case ID:123085.</li>
	*<li> Test Case Name: Edit manual gadget.</li>
	*/
	@Test
	public  void test08_EditManualGadget() {
		info("Test 08: Edit manual gadget");
		int index = creatGateinData.getRandomIndexByType(1);
		String name = creatGateinData.newName.get(index);
		String xmlCode = creatGateinData.newXMLCode.get(index);
		String newXmlCode="";
		String newName="";
		if (index == creatGateinData.lastIndex) {
			newXmlCode = creatGateinData.newXMLCode.get(index - 1);
			newName = creatGateinData.newName.get(index - 1);
		}else {
			newXmlCode = creatGateinData.newXMLCode.get(index+1);
			newName = creatGateinData.newName.get(index+1);
		}
		/*Step Number: 1
		*Step Name: Step 1: Edit local gadget
		*Step Description: 
			- Choose Gadget tab
			- Select a local gadget
			- Click edit a local gadget
		*Input Data: 
			
		*Expected Outcome: 
			- The local gadget is updated with the change value
			*/
		 navToolBar.goToApplication();
		 appRegistry.goToGadgetPage();
		 gadMg.createNewGadget(name, xmlCode);
		 gadMg.editAGadget(name, newXmlCode, newName);
		 
		 gadMg.deleteGadget(newName);
	}
	
	/**
	*<li> Case ID:123087.</li>
	*<li> Test Case Name: Add manual gadget into category.</li>
	*/
	@Test
	public  void test10_AddManualGadgetIntoCategory() {
		info("Test 10: Add manual gadget into category");
		int index = creatGateinData.getRandomIndexByType(1);
		String name = creatGateinData.newName.get(index);
		String xmlCode = creatGateinData.newXMLCode.get(index);
		String category = cateGateinData.categories.get(1);
		/*Step Number: 1
		*Step Name: Step 1: Add local gadget into category
		*Step Description: 
			- Choose Gadget tab
			- Add a local gadget
			- Click "Click here to add into categories " link
			- Check a category and click Save
		*Input Data: 
			
		*Expected Outcome: 
			- Add local gadget successfully
			- The gadget is added successfully into category
			*/
	
		navToolBar.goToApplication();
		appRegistry.goToGadgetPage();
		gadMg.createNewGadget(name, xmlCode);
		info("Add the gadget to a category:"+category);
		gadMg.addIntoCategory(category);
		
		 gadMg.deleteGadget(name);
	}

	/**
	*<li> Case ID:123088.</li>
	*<li> Test Case Name: Check refresh function.</li>
	*PENDING CASE: BECAUSE AFTER EDITING THE GADGET, ALL CHANGES AREA UPDATED, 
	*WE DON'T NEED TO CLICK ON REFRESH BUTTON TO CHECK THE CHANGES
	*/
	@Test(groups="pendings")
	public  void test11_CheckRefreshFunction() {
		info("Test 11: Check refresh function");
		/*Step Number: 1
		*Step Name: Step 1: Check refresh function
		*Step Description: 
			- Choose Gadget tab
			- Select a local gadget and 
			- click Edit 
			- Click Refresh
		*Input Data: 
			
		*Expected Outcome: 
			- The local gadget is updated with the change value
			- Refresh gadget successfully
			*/
	}

	/**
	*<li> Case ID:123089.</li>
	*<li> Test Case Name: Add remote gadget.</li>
	**<li> Case ID:123090.</li>
	*<li> Test Case Name: Delete remote gadget.</li>
	*/
	@Test
	public  void test12_13_Add_Delete_RemoteGadget() {
		info("Test 12: Add remote gadget");
		int indx = remoteGadData.getRandomIndexByType(1);
		String url = remoteGadData.newLinks.get(indx);
		String name = remoteGadData.newTitle.get(indx);
		/*Step Number: 1
		*Step Name: Step 1: Add local gadget
		*Step Description: 
			- Choose Gadget tab
			- Click [Add a remote gadget]
			- Input remote gadget ( ex: http://www.labpixies.com/campaigns/memory_game/memory_game.xml)
			- Click Add button

		*Input Data: 
			
		*Expected Outcome: 
			- Add remote gadget successfully*/
			
		navToolBar.goToApplication();
		appRegistry.goToGadgetPage();
		gadMg.addRemoteGadget(url);
		info("Verify that the gadget is added successfully");
		waitForAndGetElement(gadMg.ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",name),2000,0);
		info("Test 13: Delete remote gadget");
		/*Step Number: 1
		*Step Name: Step 1: Delete remote gadget
		*Step Description: 
			- Choose Gadget tab
			- Select a remote gadget in list
			- Click Delete icon 

	
		*Input Data: 
			
		*Expected Outcome: 
			- The remote gadget is removed from the list*/
			
		gadMg.deleteGadget(name);
	}

	/**
	*<li> Case ID:123091.</li>
	*<li> Test Case Name: Add remote gadget into category.</li>
	*/
	@Test
	public  void test14_AddRemoteGadgetIntoCategory() {
		info("Test 14: Add remote gadget into category");
		int indx = remoteGadData.getRandomIndexByType(1);
		String url = remoteGadData.newLinks.get(indx);
		String name = remoteGadData.newTitle.get(indx);
		String category = cateGateinData.categories.get(1);
		/*Step Number: 1
		*Step Name: Step 1: Add remote gadget into category
		*Step Description: 
			- Choose Gadget tab
			- Add a remote gadget
			- Click "Click here to add into categories " link
			- Check a category and click Save
	
		*Input Data: 
			
		*Expected Outcome: 
			- Add remote gadget successfully
			- The gadget is added successfully into category
			*/
		navToolBar.goToApplication();
		appRegistry.goToGadgetPage();
		gadMg.addRemoteGadget(url);
		gadMg.addIntoCategory(category);
		gadMg.deleteGadget(name);
	
	}

	/**
	*<li> Case ID:123092.</li>
	*<li> Test Case Name: Edit category.</li>
	*/
	@Test
	public  void test16_EditCategory() {
		info("Test 16: Edit category");
		String num=getRandomNumber();
		String categoryName= txData.getContentByArrayTypeRandom(1)+ num;
		String displayName= txData.getContentByArrayTypeRandom(1)+ num;
		String des= txData.getContentByArrayTypeRandom(1)+ num;
		String num1=getRandomNumber();
		String newDisplayName= txData.getContentByArrayTypeRandom(1)+ num1;
		String newDes= txData.getContentByArrayTypeRandom(1)+ num1;
		
		/*Step Number: 1
		*Step Name: Step 1: Add category
		*Step Description: 
			- Go to Administration/Application registry
			- Choose category tab
			- Click Add category icon
			- Input some fields require
			- Click Save
	
		*Input Data: 
			
		*Expected Outcome: 
			- Add category successfully*/
			
		navToolBar.goToApplication();
		appRegistry.addACategory(categoryName, displayName, des);
		
		/*Step Number: 1
		*Step Name: Step 1: Edit category
		*Step Description: 
			- Go to Administration/Application registry
			- Select a category and click Edit icon
			- Change some fields and click Save
	
		*Input Data: 
			
		*Expected Outcome: 
			- The category is updated with the change value*/
			
		appRegistry.editCategory(categoryName, newDisplayName, newDes);
		appRegistry.deleteCategory(categoryName);
	
	}
}