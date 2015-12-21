package org.exoplatform.selenium.platform.TaskManagement;

import org.openqa.selenium.WebDriver;
import static org.exoplatform.selenium.TestLogger.info;

/**
 * This class will define actions about management project
 *
 */
public class ManagementTasks extends TaskManagementLocator {
	
	TaskManagementHome tasHome;
	
	public ManagementTasks(WebDriver dr){
		this.driver=dr;
		tasHome=new TaskManagementHome(dr);
	}
	
	
    /**
     * Define options in Task list
     *
     */
	public enum optionTask{
		Incoming,All_Tasks,Overdue,Today,Tommorrow,Upcoming;
	}
	
	
	/**
	 * Select an option in Task List
	 * @param op
	 *          is an option in the list as: Incoming,All tasks,...
	 */
	public void selectOptionTask(optionTask op){
		info("--Open Task Management--");
		tasHome.goToTasks();
		switch(op){
		case Incoming:
			info("Select Incomming option");
			break;
		case All_Tasks:
			info("Select All tasks option");
			break;
		case Overdue:
			info("Select Overdue option");
			break;
		case Today:
			info("Select Today option");
			break;
		case Tommorrow:
			info("Select Tommorrow option");
			break;
		case Upcoming:
			info("Select Upcoming option");
			break;
		default:
			info("No option in the list.Please select correct option.");
			break;
		}
	}
	
	/**
	 * Open Sorting list of Incoming
	 */
	public void goToSortIncomingList(){
		info("Click on Sort list");
	}
	
	/**
	 * Open Group list in Incoming
	 */
	public void goToGroupIncoming(){
		info("Click on Group list");
	}
	
	
	
}
