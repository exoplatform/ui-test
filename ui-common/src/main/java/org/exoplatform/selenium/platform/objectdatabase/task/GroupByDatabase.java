package org.exoplatform.selenium.platform.objectdatabase.task;

import java.util.ArrayList;
import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

/**
 * @date Jul 15, 2015
 * @author anhpp
 */

public class GroupByDatabase {
    
	public ArrayList<String> groupBy;

	public GroupByDatabase(ArrayList<String> groupBy){
		this.groupBy = groupBy;
	}

	public GroupByDatabase() {
		groupBy  = new ArrayList<String>();
	}

	public void setGroupByData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			groupBy.add(testData[i][0]);
		}
	}
	/**
	 * Get type of sort by
	 * @param index
	 * @return GroupBy 
	 */
	public String getGroupBy(int index){
		return groupBy.get(index);
	}
}



