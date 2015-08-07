package org.exoplatform.selenium.platform.objectdatabase.task;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

/**
 * @date Aug 07, 2015
 * @author anhpp
 */

public class PriorityDatabase {
    
	public ArrayList<String> priority;
	public ArrayList<String> newPriority;
	public ArrayList<Integer> type;

	public PriorityDatabase(ArrayList<Integer> type,ArrayList<String> priority){
		this.type = type;
		this.priority = priority;
		
	}

	public PriorityDatabase() {
		type  = new ArrayList<Integer>();
		priority  = new ArrayList<String>();
		newPriority  = new ArrayList<String>();
	}

	public void setPriorityData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		info("testData.length"+testData.length);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			priority.add(testData[i][1]);
			
		}
	}
	/**
	 * Get priority
	 * @param index
	 * @return priority
	 */
	public String getPriority(int index){
		return priority.get(index);
	}
	/**
	 * Get random priority by type
	 * @param type
	 * @return priority
	 */
	public String getPriorityByArrayTypeRandom(int...type){
		newPriority.clear();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					newPriority.add(this.priority.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(newPriority.size());
		String priority = newPriority.get(index);
		info("priority is: "+priority);
		return priority;
	}
}



