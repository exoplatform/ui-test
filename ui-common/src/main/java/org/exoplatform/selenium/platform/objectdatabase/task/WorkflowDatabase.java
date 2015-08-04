package org.exoplatform.selenium.platform.objectdatabase.task;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

/**
 * @date Aug 04, 2015
 * @author anhpp
 */

public class WorkflowDatabase {
    
	public ArrayList<String> flow;
	public ArrayList<String> newFlow;
	public ArrayList<Integer> type;

	public WorkflowDatabase(ArrayList<Integer> type,ArrayList<String> flow){
		this.type = type;
		this.flow = flow;
		
	}

	public WorkflowDatabase() {
		type  = new ArrayList<Integer>();
		flow  = new ArrayList<String>();
		newFlow  = new ArrayList<String>();
	}

	public void setWorkflowData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		info("testData.length"+testData.length);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			flow.add(testData[i][1]);
			
		}
	}
	/**
	 * Get flow
	 * @param index
	 * @return flow 
	 */
	public String getFlow(int index){
		return flow.get(index);
	}
	/**
	 * Get random flow by type
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
		String flow = newFlow.get(index);
		info("flow is: "+flow);
		return flow;
	}
}



