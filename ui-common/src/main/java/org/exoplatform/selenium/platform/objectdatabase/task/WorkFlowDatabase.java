package org.exoplatform.selenium.platform.objectdatabase.task;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

/**
 * @date Jul 20, 2015
 * @author anhpp
 */

public class WorkFlowDatabase {
    

	public ArrayList<Integer> type;
	public ArrayList<String> flow;
	public ArrayList<String> newFlow;
	
	public WorkFlowDatabase(ArrayList<Integer> type, ArrayList<String>flow){
		this.type = type;
		this.flow = flow;
	}

	public WorkFlowDatabase(){
		type  = new ArrayList<Integer>();
		flow  = new ArrayList<String>();
		newFlow =new ArrayList<String>();
	}
    /**
     * Set data
     * @param userDataFile
     * @param userSheet
     * @param opParams
     * @throws Exception
     */
	public void setData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			flow.add(testData[i][1]);
		}
	}
	/**
	 * Get flow by index
	 * @param index
	 * @return
	 */
	public String getFlowByIndex(int index){
		return flow.get(index);
	}
	
	/**
	 * Get flow by random
	 * @return flow;
	 */
	public String getFlowByRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.flow.size());
		return this.flow.get(index);
	}
		
	/**
	 * Get flow by type
	 * @param type
	 * @return flow;
	 */
	public String getFlowByArrayTypeRandom(int...type){
		newFlow.clear();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					newFlow.add(this.flow.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(newFlow.size());
		String className = newFlow.get(index);
		info("className is: "+className);
		return className;
	}
	

}



