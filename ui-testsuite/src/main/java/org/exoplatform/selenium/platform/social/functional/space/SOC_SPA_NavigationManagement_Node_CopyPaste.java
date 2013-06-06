package org.exoplatform.selenium.platform.social.functional.space;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.social.SpaceManagement.*;
import static org.exoplatform.selenium.platform.social.SpaceNavigation.*;

import org.exoplatform.selenium.platform.social.SocialBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 * 
 * @author thaopth
 * Date: 05/12/2012
 */
public class SOC_SPA_NavigationManagement_Node_CopyPaste extends SocialBase {
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	public int timeToDeleteSpace = 120000;

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		signIn(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods(){
		info("-- Logout --");
		signOut();
		driver.quit();
		actions = null;
	}
	/*
	 * SOC/Space/Navigation Management/Node/Copy&Paste
	 * Case 01: Copy and Paste a node into another node in the same navigation
	 */
	@Test
	public void test01_CopyAndPasteNodeIntoAnotherNodeInTheSameNavigation() {
		String DATA_SPACE_NAME = "SpaceCopyNodeCase01";
		String DATA_NODE_NAME = "Node01";
		String DATA_PARENT_NODE1 = "Discussions";
		String DATA_PARENT_NODE2 = "Wiki";

		info("--- Go to space page ---");	

		goToMySpacePage();

		info("---Create new space---");

		addNewSpace(DATA_SPACE_NAME, DATA_SPACE_NAME);

		goToSettings();
 
		goToNavigation();

		click(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE1));

		addNodeDoNotSelectPage(DATA_NODE_NAME, DATA_NODE_NAME, DATA_NODE_NAME);
		
		click(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE1));

		copySpaceNode(By.xpath(NODE_PATH.replace("${nodeLabel}",DATA_NODE_NAME)));

		pasteSpaceNode(By.xpath(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE2)));

		waitForElementPresent(NODE_PATH.replace("${nodeLabel}",DATA_NODE_NAME));
		
		//Clear data

		restoreData(DATA_SPACE_NAME,timeToDeleteSpace);
	}
	/*
	 * SOC/Space/Navigation Management/Node/Copy&Paste
	 * Case 02: Copy and Paste a node into another node on different space navigation
	 */
	@Test
	public void test02_CopyAndPasteNodeIntoAnotherNodeOnDifferentSpaceNavigation() {
		String DATA_SPACE_NAME1 = "SpaceCutNodeCase002a";
		String DATA_SPACE_NAME2 = "SpaceCutNodeCase002b";
		String DATA_NODE_NAME = "Node01";
		String DATA_PARENT_NODE1 = "Discussions";
		String DATA_PARENT_NODE2 = "Wiki";

		info("--- Go to space page ---");	

		goToMySpacePage();

		info("---Create new space---");

		addNewSpace(DATA_SPACE_NAME1, DATA_SPACE_NAME1);
		
		goToMySpacePage();
		
		addNewSpace(DATA_SPACE_NAME2, DATA_SPACE_NAME2);

		goToSettings();
 
		goToNavigation();

		click(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE1));

		addNodeDoNotSelectPage(DATA_NODE_NAME, DATA_NODE_NAME, DATA_NODE_NAME);
		
		click(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE1));

		copySpaceNode(By.xpath(NODE_PATH.replace("${nodeLabel}",DATA_NODE_NAME)));
		
		goToMySpacePage();
		
		accessSpace(DATA_SPACE_NAME1);
		
		goToSettings();
		
		goToNavigation();
				
		rightClickOnElement(By.xpath(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE2)));

		waitForElementNotPresent(ELEMENT_PASTE_NODE_LINK);
		
		//Clear data

		restoreData(DATA_SPACE_NAME1,timeToDeleteSpace);
		restoreData(DATA_SPACE_NAME2,timeToDeleteSpace);
	}
	/*
	 * SOC/Space/Navigation Management/Node/Copy&Paste
	 * Case 03: Copy and Paste a node into the same place
	 */
	@Test
	public void test03_CopyAndPasteNodeIntoTheSamePlace() {
		String DATA_SPACE_NAME = "SpaceCopyNodeCase03";
		String DATA_NODE_NAME = "Node01";
		String DATA_PARENT_NODE = "Discussions";

		info("--- Go to space page ---");	

		goToMySpacePage();

		info("---Create new space---");

		addNewSpace(DATA_SPACE_NAME, DATA_SPACE_NAME);

		goToSettings();
 
		goToNavigation();

		click(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE));

		addNodeDoNotSelectPage(DATA_NODE_NAME, DATA_NODE_NAME, DATA_NODE_NAME);
		
		click(NODE_PATH.replace("${nodeLabel}",DATA_PARENT_NODE));

		copySpaceNode(By.xpath(NODE_PATH.replace("${nodeLabel}",DATA_NODE_NAME)));

		pasteSpaceNode(By.xpath("//a[@class='NodeIcon DefaultPageIcon NodeSelected' and @title='Discussions']"));

		waitForTextPresent(WARNING_EXISTING_NODE);

		click(ELEMENT_OK_BUTTON);

		//Clear data

		restoreData(DATA_SPACE_NAME,timeToDeleteSpace);
	}
}