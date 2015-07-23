package org.exoplatform.selenium.platform.task;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.WebDriver;

/**
 * This class will define all actions on Home page of the feature
 *
 */
public class TaskManagementHome extends TaskManagementLocatorObject {
	
	public TaskManagementHome(WebDriver dr){
		this.driver=dr;
	}
	
	/**
	 * Go to Project area by clicking on Project Header on left panel
	 */
	public void goToProjects(){
		info("--Go to Project area--");
	}
	/**
	 * Go to Labels are by clicking on Label Header on left panel
	 */
	public void goToLabels(){
		info("--Go to Labels area--");
	}
	/**
	 * Go to List View by clicking on List button on Action bar
	 */
	public void goToListView(){
		info("--Go to List View--");
	}
	/**
	 * Go to Board by clicking on Board button on Actions bar
	 */
	public void goToBoard(){
		info("--Go to Board--");
	}
	
	/**
	 * Define options in Sort list
	 */
	public enum optionSortList{
		Title,Status,Created_Date,Due_Date,Priority,Rank;
	}
	
	
	/**
	 * Select an option in Sort list
	 * @param op
	 *            is an option in the list as: Title,Create Date,...
	 */
	public void selectOptionSort(optionSortList op){
		switch(op){
		case Title:
			info("Select Title option");
			break;
		case Status:
			info("Select Status option");
			break;
		case Created_Date:
			info("Select Create Date option");
			break;
		case Due_Date:
			info("Select Due Date option");
			break;
		case Priority:
			info("Select Priority option");
			break;
		case Rank:
			info("Select Rank option");
			break;
		default:
			info("No option in the list. Please select correct option");
			break;
		}
	}
	
	
	/**
	 * Define options in Group list of Incoming
	 */
	public enum optionGroup{
		None,Assignee,Label,Project,Due_Date;
	}
	
	/**
	 * Select an option in Group list of Incoming and All Tasks
	 * @param op
	 *           is an option in the list as: None,Assignee,...
	 */
	public void selectOptionGroup(optionGroup op){
		switch(op){
		case None:
			info("Select None option");
			break;
		case Assignee:
			info("Select Assignee option");
			break;
		case Label:
			info("Select Label option");
			break;
		case Project:
			info("Select Project option");
			break;
		case Due_Date:
			info("Select Due Date option");
			break;
		default:
			info("No option in the list. Please select correct option.");
			break;
		}
	}
	
}
