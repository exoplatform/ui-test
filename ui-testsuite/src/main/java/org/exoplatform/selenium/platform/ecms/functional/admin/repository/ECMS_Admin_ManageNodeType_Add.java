package org.exoplatform.selenium.platform.ecms.functional.admin.repository;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageNodeType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECMS_Admin_ManageNodeType_Add extends PlatformBase {

	//General
	Button button;
	ManageAccount magAcc;

	//Ecms
	EcmsBase ecms;
	ECMainFunction ecMain;
	ManageNodeType magNode;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		button = new Button(driver);
		magAcc = new ManageAccount(driver);
		ecms = new EcmsBase(driver);
		ecMain = new ECMainFunction(driver);
		magNode = new ManageNodeType(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		alert = new ManageAlert(driver, this.plfVersion);
		ecMain.goToNodeTypeTab();
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Test name: Add new Node type when put valid data in all required fields
	 * Test Id: 124712
	 */
	@Test
	public void test01_AddNewNodeType(){
		info("Test 01: Add new Node type when put valid data in all required fields");
		String nodeTypeName = "exoplatform124712";
		String superTypes = "app:application/app:category";

		info("-- Add a new node type --");
		ecMain.goToNodeTypeTab();

		//Add new Node Type
		magNode.addNewNodeType(nodeTypeName, superTypes);

		//Verify and view node type
		magNode.viewNodeType(nodeTypeName);		
	}

	/**
	 * Test name: Add Child node  with valid data.
	 * Test Id: 124721
	 */
	@Test
	public void test02_AddChildNodeWithValidData(){
		info("Test 02: Add Child node  with valid data.");
		String nodeTypeName = "exoplatform124721";
		String superTypes = "app:application/app:category";
		String namespace ="webdav";
		String childName = "node124721";
		String primaryType= "app:application";
		String requirePrimary="app:application";
		ecMain.goToNodeTypeTab();
		click(button.ELEMENT_ADD_BUTTON);
		magNode.addChildNode(namespace, childName, primaryType, requirePrimary);
		type(magNode.ELEMENT_NODE_TYPE_NAME, nodeTypeName, true);
		magNode.addNodeType(nodeTypeName, superTypes);
		//Verify and view node type
		magNode.viewNodeType(nodeTypeName);		
	}
}
