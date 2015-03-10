package org.exoplatform.selenium.platform.objectdatabase.common;

import java.util.ArrayList;
import java.util.Random;


public class TextBoxDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> content;

	/**
	 * TextBoxDatabase
	 * @param type
	 * @param content
	 */
	public TextBoxDatabase(ArrayList<Integer> type, ArrayList<String> content){
		this.type = type;
		this.content = content;
	}

	/**
	 * TextBoxDatabase
	 */
	public TextBoxDatabase(){
		type  = new ArrayList<Integer>();
		content  = new ArrayList<String>();
	}

	/**
	 * setContentData
	 * @param userDataFile
	 * @param userSheet
	 * @param opParams
	 * @throws Exception
	 */
	public void setContentData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			content.add(testData[i][1]);
		}
	}
	
	/**
	 * Get file name by index
	 * @param index
	 * @return
	 */
	public String getContentByIndex(int index){
		return content.get(index);
	}
	
	/**
	 * get file name random
	 * @return
	 */
	public String getContentRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.content.size());
		String Content = this.content.get(index);
		return Content;
	}

	/**
	 * getArrayContentByType
	 * @param type
	 * @return
	 */
	public ArrayList<String> getArrayContentByType(int type){
		ArrayList<String> arrayContent = new ArrayList<String>();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				arrayContent.add(this.content.get(i));
			}
		}
		return arrayContent;
	}

	/**
	 * getArrayContentByArrayType
	 * @param type
	 * @return
	 */
	public ArrayList<String> getArrayContentByArrayType(int...type){
		ArrayList<String> arrayContent = new ArrayList<String>();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					arrayContent.add(this.content.get(i));
				}
			}
		}
		return arrayContent;
	}

	/**
	 * getContentByArrayTypeRandom
	 * @param type
	 * @return Content
	 */
	public String getContentByArrayTypeRandom(int type){
		ArrayList<String> arrayContent = new ArrayList<String>();
		Random randomGenerator = new Random();
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type)
					arrayContent.add(this.content.get(i));
			}
		int index = randomGenerator.nextInt(arrayContent.size());
		String Content = arrayContent.get(index);
		return Content;
	}
}
