package org.exoplatform.selenium.platform.objectdatabase.task;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class ColorDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> colorName;
	public ArrayList<String> className;
	public ArrayList<String> newClassName;
	
	public ColorDatabase(ArrayList<Integer> type, ArrayList<String>colorName,
			ArrayList<String>className){
		this.type = type;
		this.colorName = colorName;
		this.className = className;
	}

	public ColorDatabase(){
		type  = new ArrayList<Integer>();
		colorName  = new ArrayList<String>();
		className  = new ArrayList<String>();
		newClassName =new ArrayList<String>();
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
			colorName.add(testData[i][1]);
			className.add(testData[i][2]);
		}
	}
	/**
	 * Get class name by index
	 * @param index
	 * @return
	 */
	public String getclassNameByIndex(int index){
		return className.get(index);
	}
	
	/**
	 * Get class name by random
	 * @return className;
	 */
	public String getclassNameByRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.colorName.size());
		return this.className.get(index);
	}
	
	/**
	 * Get random index by type
	 * @param type
	 * @return index
	 */
	public Integer getRandomIndexByType(int type){
		Random randomGenerator = new Random();
		newClassName.clear();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				newClassName.add(this.colorName.get(i));
			}
		}
		Integer index = randomGenerator.nextInt(newClassName.size());
		return index;
	}
	
	/**
	 * getArrayIcolorNameByType
	 * @param type
	 * @return newcolorName
	 */
	public ArrayList<String> getArraycolorNameByType(int type){
		newClassName.clear();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				newClassName.add(this.colorName.get(i));
			}
		}
		return newClassName;
	}
	/**
	 * Get class name of an color by type
	 * @param type
	 * @return className;
	 */
	public String getClassNameByArrayTypeRandom(int...type){
		newClassName.clear();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					newClassName.add(this.className.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(newClassName.size());
		String className = newClassName.get(index);
		info("className is: "+className);
		return className;
	}
	
}
