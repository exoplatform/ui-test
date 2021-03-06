package org.exoplatform.selenium.platform.objectdatabase.social;

import java.util.ArrayList;
import java.util.Random;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class ActivityCommentDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> mode;
	public ArrayList<String> content;
	public ArrayList<String> newContent;
	
	public ActivityCommentDatabase(ArrayList<Integer> type, ArrayList<String> mode,
			ArrayList<String> content){
		this.type = type;
		this.mode = mode;
		this.content = content;
		
	}

	public ActivityCommentDatabase() {
		type  = new ArrayList<Integer>();
		mode  = new ArrayList<String>();
		content  = new ArrayList<String>();
		newContent  = new ArrayList<String>();
	}

	public void setData(String userDataFile, String userSheet,Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length-1; i++)
		{	
			if (!testData[i][1].isEmpty()) {
				info("mode:" + i + " " + testData[i][1]);
				type.add(Integer.valueOf(testData[i][0]));
				mode.add(testData[i][1]);
				content.add(testData[i][2]);
			}
		}
	}
	/**
	 * Get the conent by type
	 * @param type
	 * @return
	 */
	public Integer getRandomIndexByType(int type){
		Random randomGenerator = new Random();
		newContent.clear();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				newContent.add(this.content.get(i));
			}
		}
		Integer index = randomGenerator.nextInt(newContent.size());
		return index;
	}
	/**
	 * Get a message from file by index
	 * @param index
	 * @return message 
	 */
	public String getNotiMessage(int index){
		return content.get(index);
	}
	/**
	 * Get message from file by random type
	 * @param type
	 * @return
	 */
	public String getMessageByArrayTypeRandom(int...type){
		ArrayList<String> arrayMessage = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					arrayMessage.add(this.content.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(arrayMessage.size());
		String message = arrayMessage.get(index);
		info("Message is: "+message);
		return message;
	}
}
