package org.exoplatform.selenium.platform.objectdatabase.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class WikiMacroDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> wikiCate;
	public ArrayList<String> wikiMac;
	public ArrayList<String> wikiMacCollapse;
	
	/**
	 * WikiMacroDatabase
	 * @param type
	 * @param wikiCate
	 * @param wikiMac
	 */
	public WikiMacroDatabase(ArrayList<Integer> type, ArrayList<String> wikiCate, ArrayList<String> wikiMac, ArrayList<String> wikiMacCollapse){
		this.type = type;
		this.wikiMac = wikiCate;
		this.wikiMac = wikiMac;
		this.wikiMacCollapse = wikiMacCollapse;
	}

	/**
	 * Create array
	 */
	public WikiMacroDatabase() {
		type = new ArrayList<Integer>();
		wikiCate = new ArrayList<String>();
		wikiMac  = new ArrayList<String>();
		wikiMacCollapse = new ArrayList<String>();
	}

	/**
	 * Set data
	 * @param userDataFile
	 * @param userSheet
	 * @param opParams
	 * @throws Exception
	 */
	public void setWikiMacroData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length-1; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			wikiCate.add(testData[i][1]);
			wikiMac.add(testData[i][2]);
			wikiMacCollapse.add(testData[i][3]);
		}
	}
	
	/**
	 * Get Macro Categories by Index
	 * @param index
	 */
	public String getWikiCategoriesByIndex(int index){
		return wikiCate.get(index);
	}
	
	/**
	 * Get Macro by Index
	 * @param index
	 */
	public String getWikiMacroByIndex(int index){
		return wikiMac.get(index);
	}
	
	/**
	 * Get Macro Categories by Index
	 * @param index
	 */
	public String getWikiCollapseByIndex(int index){
		return wikiMacCollapse.get(index);
	}
	
	/**
	 * Get Macro by type
	 * @param type
	 * @return array
	 */
	public ArrayList<String> getMacroByType(int type){
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				array.add(this.wikiMac.get(i));
			}
		}
		return array;
	}
	
	/**
	 * Get Macro Categories by type
	 * @param type
	 * @return array
	 */
	public ArrayList<String> getMacroCategoriesByType(int type){
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				array.add(this.wikiCate.get(i));
			}
		}
		return array;
	}
	
	/**
	 * Get Macro Categories Random by type
	 * @param type
	 * @return Content
	 */
	public String getDataMacroCategoriesByArrayTypeRandom(int...type){
		ArrayList<String> array = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					array.add(this.wikiCate.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(array.size());
		String Content = array.get(index);
		info("Content is: "+Content);
		return Content;
	}
	
	/**
	 * Get Macro Random by type
	 * @param type
	 * @return Content
	 */
	public String getDataMacroByArrayTypeRandom(int...type){
		ArrayList<String> array = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					array.add(this.wikiMac.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(array.size());
		String Content = array.get(index);
		info("Content is: "+Content);
		return Content;
	}

}
