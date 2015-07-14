package org.exoplatform.selenium.platform.objectdatabase.task;

import java.util.ArrayList;
import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

/**
 * @date Jul 15, 2015
 * @author anhpp
 */

public class SortByDatabase {
    
	public ArrayList<String> sortBy;

	public SortByDatabase(ArrayList<String> sortBy){
		this.sortBy = sortBy;
	}

	public SortByDatabase() {
		sortBy  = new ArrayList<String>();
	}

	public void setSortByData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			sortBy.add(testData[i][0]);
		}
	}
	/**
	 * Get type of sort by
	 * @param index
	 * @return SortBy 
	 */
	public String getSortBy(int index){
		return sortBy.get(index);
	}
}



