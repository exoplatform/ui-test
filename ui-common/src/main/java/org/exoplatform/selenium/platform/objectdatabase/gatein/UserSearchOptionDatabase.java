package org.exoplatform.selenium.platform.objectdatabase.gatein;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class UserSearchOptionDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> userSearchOption;
	
	public final int USER_SEARCH_OPTION_BY_USER_NAME = 0;
	public final int USER_SEARCH_OPTION_BY_LAST_NAME = 1;
	public final int USER_SEARCH_OPTION_BY_FIRST_NAME = 2;
	public final int USER_SEARCH_OPTION_BY_EMAIL = 3;

	public UserSearchOptionDatabase(ArrayList<Integer> type, ArrayList<String> userSearchOption){
		this.type = type;
		this.userSearchOption = userSearchOption;
	}

	public UserSearchOptionDatabase(){
		type  = new ArrayList<Integer>();
		userSearchOption  = new ArrayList<String>();
	}

	public void setUserSearchOptionData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			userSearchOption.add(testData[i][1]);
		}
	}
	
	/**
	 * Get user Search Option by index
	 * @param index
	 * @return userSearchOption.get(index)
	 */
	public String getUserSearchOptionByIndex(int index){
		return userSearchOption.get(index);
	}
	
	/**
	 * get User Search Option random
	 * @return userSearchOption
	 */
	public String getUserSearchOptionRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.userSearchOption.size());
		String userSearchOption = this.userSearchOption.get(index);
		return userSearchOption;
	}
	
}
