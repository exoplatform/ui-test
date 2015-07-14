package org.exoplatform.selenium.platform.objectdatabase.task;

import java.util.ArrayList;
import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

/**
 * @date Jul 10, 2015
 * @author anhpp
 */

public class WelcomeMessageDatabase {
    
	public ArrayList<String> welcomeMessage;

	public WelcomeMessageDatabase(ArrayList<String> welcomeMessage){
		this.welcomeMessage = welcomeMessage;
	}

	public WelcomeMessageDatabase() {
		welcomeMessage  = new ArrayList<String>();
	}

	public void setWelcomeMessageData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			welcomeMessage.add(testData[i][0]);
		}
	}
	/**
	 * Get a message from file by index
	 * @param index
	 * @return welcomeMessage 
	 */
	public String getWelcomeMessage(int index){
		return welcomeMessage.get(index);
	}
}



