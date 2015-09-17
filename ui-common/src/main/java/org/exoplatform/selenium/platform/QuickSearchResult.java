package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import static org.exoplatform.selenium.TestLogger.info;

public class QuickSearchResult extends PlatformBase{
	
	public final By ELEMENT_QUICKSEARCHRESULT_NUMBEROFRESULT = By.xpath("//*[contains(text(), 'Results 1 to 5')]");
	public final By ELEMENT_QUICKSEARCHRESULT_SORTBY = By.xpath("//*[@class='btn dropdown-toggle']");
	public final By ELEMENT_QUICKSEARCHRESULT_SORTBY_DATE = By.xpath("//*[@sort='date']");
	public final String ELEMENT_QUICKSEARCHRESULT_ICONDOC = "//*[@class='uiIcon64x64TemplateFolderDefault uiIcon64x64Templateacme_product']/../..//*[text()='${title}']";
	public final By ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH=By.xpath(".//*[@class='message']/*[contains(text(),'See All Search Results')]");
	
	public final String ELEMENT_QUICKSEARCH_TASKS_TITLE = ".//th[contains(.,'Tasks in Tasks')]/..//*[contains(@class,'tasksInTasks')]//strong[contains(.,'$task')]";
	public final String ELEMENT_QUICKSEARCH_TASKS_LINK = "//*[contains(@class,'tasksInTasks')]//strong[contains(.,'$task')]/..";
	public final By ELEMENT_QUICKSEARCH_TASKS_ICON = By.xpath("//*[contains(@class,'tasksInTasks')]//*[contains(@class,'uiIconTick')]");
	public final By ELEMENT_QUICKSEARCH_TASKS_COMPLETED_ICON = By.xpath("//*[contains(@class,'tasksInTasks')]//*[contains(@class,'uiIconBlue')]");
	
	//filter box
	public final By ELEMENT_SEARCHRESULT_ALLTYPECHECK = By.xpath("//*[@value='all' and @name='contentType' and @checked='checked']");
	public final By ELEMENT_SEARCHRESULT_FILESTYPECHECK = By.xpath("//*[@value='file' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_DOCTYPECHECK = By.xpath("//*[@value='document' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_DISCTYPECHECK = By.xpath("//*[@value='post' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_TASKTYPECHECK = By.xpath("//*[@value='task' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_EVENTSTYPECHECK = By.xpath("//*[@value='event' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_WIKITYPECHECK = By.xpath("//*[@value='wiki' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_SPACETYPECHECK = By.xpath("//*[@value='space' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_PEOPLETYPECHECK = By.xpath("//*[@value='people' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_ANSWERTYPECHECK = By.xpath("//*[@value='answer' and @name='contentType']");
	public final By ELEMENT_SEARCHRESULT_PAGETYPECHECK = By.xpath("//*[@value='page' and @name='contentType']");

	public final By ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX = By.xpath(".//*[@id='ToolBarSearch']//*[@name='adminkeyword']");
    public final String ELEMENT_QUICKSEARCH_LIST_CONTENT=".//*[@class='uiQuickSearchResult']//a[contains(.,'${name}')]";
	
    ///Searched results page-->task,event
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASK_NAME_NOTE= ".//*[@id='result']//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASK_DUEDATE= ".//*[@id='result']//*[contains(text(),'${name}')]/../../..//*[contains(text(),'Due for')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASK_ICON = ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_EVENT_NAME_NOTE= ".//*[@id='result']//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_EVENT_DUEDATE= ".//h6//*[contains(text(),'${name}')]/../..//*[contains(.,'${date}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_EVENT_ICON = ".//h6//*[contains(text(),'${name}')]/../../../../..//*[@class='avatar pull-left']";
	
	//Searched results page-->file
	public final String ELEMENT_SEARCHRESULT_CONTENT_FILE_TITLE = ".//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_FILE_LOCATION = "//*[contains(text(), '${name}')]/../..//*[contains(text(),'${location}')]"; 
	public final String ELEMENT_SEARCHRESULT_CONTENT_FILE_ICON = ".//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left ']"; 
	
	//Searched results page -->document
	public final String ELEMENT_SEARCHRESULT_CONTENT_DOC_ICON = ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DOC_TITLE = ".//h6//*[contains(text(),'${name}')]";
	
	//Searched results page -->discussion
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_ICON = ".//*[contains(text(),'${name}')]/../../..//*[@class='uiIconApp64x64Forum']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_TITLE = "//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_EXCERPT = ".//*[contains(text(),'${name}')]/../../..//*[@class='excerpt']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_FORUM_NAME = ".//*[contains(text(),'${topic}')]/../../..//*[@class='detail'][contains(text(),'${forum}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_DATE = ".//*[contains(text(),'${topic}')]/../../..//*[@class='detail'][contains(text(),'${date}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_RATING = ".//*[contains(text(),'${topic}')]/../../..//*[@class='avgRatingImages clearfix']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_NUMBER_REPLY =".//*[contains(text(),'${topic}')]/../../..//*[@class='detail'][contains(text(),'${number}')]";
	
	//Searched results page --> wiki
	public final String ELEMENT_SEARCHRESULT_CONTENT_WIKI_ICON = ".//h6//*[contains(text(),'${name}')]/../../../../..//*[@class='avatar pull-left']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_WIKI_TITLE = ".//h6//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_WIKI_LAST_MODIFICATION_DATE = ".//h6//*[contains(text(),'${name}')]/../../../../..//*[contains(text(),'${date}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_WIKI_LOCATION = ".//h6//*[contains(text(),'${name}')]/../../../../..//*[contains(text(),'${location}')]";
	
	//Searched results page --> Spaces
	public final String ELEMENT_SEARCHRESULT_CONTENT_SPACE_AVATAR = ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avatar pull-left userThumbnail']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_SPACE_TITLE = ".//h6//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_SPACE_DESCRIPTION = ".//p[contains(text(),'${des}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_SPACE_MEMBER_COUNT = ".//*[contains(text(),'${name}')]/../..//*[contains(text(),'${number}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_SPACE_STATUS = ".//*[contains(text(),'${name}')]/../..//*[contains(text(),'${status}')]";
	
	//Searched results page --> People
	public final String ELEMENT_SEARCHRESULT_CONTENT_USER_AVATAR = ".//h6[contains(.,'${fullname}')]/../..//*[@class='avatar pull-left userThumbnail']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_USER_FULL_NAME = ".//h6[contains(.,'${fullname}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_USER_TITLE = ".//h6[contains(.,'${fullname}')]/../..//*[@class='excerpt']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_USER_EMAIL = ".//h6[contains(.,'${fullname}')]/../..//*[@class='detail'][contains(.,'${email}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_USER_GENDER = ".//h6[contains(.,'${fullname}')]/../..//*[@class='detail'][contains(text(),'${gender}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_USER_PHONE = ".//h6[contains(.,'${fullname}')]/../..//*[@class='detail'][contains(text(),'${phone}')]";
	
	//Page searched results-->pages
	public final String ELEMENT_SEARCHRESULT_CONTENT_PAGE_ICON = ".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../../..//img";
	public final String ELEMENT_SEARCHRESULT_CONTENT_PAGE_TITLE = ".//*[@id='result']//h6//*[contains(text(),'${page}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_PAGE_EXCERPT=".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../..//*[@class='excerpt']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_BELONGS_TO=".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../..//*[contains(text(),'${site}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_URL=".//*[@id='result']//h6//*[contains(text(),'${page}')]/../../..//*[contains(.,'${url}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_HREF=".//*[@id='result']//h6//*[contains(text(),'${page}')]/..";
	
	//Searched results page -->Answers
	public final String ELEMENT_SEARCHRESULT_CONTENT_ANSWER_TITLE = ".//h6//*[contains(text(),'${name}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_ANSWER_ICON = ".//h6//*[contains(text(),'${name}')]/../../../..//*[@class='avatar pull-left']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_ANSWER_EXCERPT = ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='excerpt']";
	public final String ELEMENT_SEARCHRESULT_CONTENT_ANSWER_NUMBER_REPLY = ".//h6//*[contains(text(),'${name}')]/../../..//*[contains(text(),'${number}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_ANSWER_NUMBER_COMMENTS = ".//h6//*[contains(text(),'${name}')]/../../..//*[contains(text(),'${comment}')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_ANSWER_RATING = ".//h6//*[contains(text(),'${name}')]/../../..//*[@class='avgRatingImages clearfix']";
	
	//Searched result page --> Tasks
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASKS_TITLE = ".//h6[contains(.,'$task')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASKS_DES = ".//h6[contains(.,'$task')]/../..//*[@class='excerpt'][contains(.,'$des')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASKS_DUEDATE = "//h6//*[contains(text(),'$task')]/../../../*[contains(@class,'detail')]/*[contains(.,'$duedate')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASKS_PRIORITY = "//h6//*[contains(text(),'$task')]/../../../*[contains(@class,'detail')]/*[contains(@class,'uiIconColorPriority$priority')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASKS_ICON = ".//h6//*[contains(text(),'$task')]/../../../..//*[contains(@class,'TickGray')]";
	public final String ELEMENT_SEARCHRESULT_CONTENT_TASKS_COMPLETED_ICON = "//h6//*[contains(text(),'$task')]/../../../..//*[contains(@class,'TickBlue')]";
	
	public QuickSearchResult(WebDriver dr){
		driver = dr;
		
	} 
	
	/**
	 * Search a text
	 * @param textSearch
	 */
	public void search(String textSearch) {
		if (!textSearch.isEmpty()) {
			waitForAndGetElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).clear();
	        waitForAndGetElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).sendKeys(textSearch);
	        Utils.pause(5000);
	        driver.findElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		}else assert false:"Not input a text to search";
	}
	/**
	 * Check autosuggestion of task in quick search
	 * @param task
	 * @param isComplete
	 * @param isDisplay
	 */
	public void checkDislayAutoSuggestionOfTask(String task,boolean isComplete,boolean isDisplay){
		type(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,task,true);
		if(isDisplay){
			info("can view search result");
			waitForAndGetElement(ELEMENT_QUICKSEARCH_TASKS_TITLE.replace("$task", task));
			if(isComplete)
				waitForAndGetElement(ELEMENT_QUICKSEARCH_TASKS_COMPLETED_ICON);
			else
				waitForAndGetElement(ELEMENT_QUICKSEARCH_TASKS_ICON);
		}else{
			info("can not view search result");
			waitForElementNotPresent(ELEMENT_QUICKSEARCH_TASKS_TITLE.replace("$task", task));
		}
	}
	/**
	 * Check display of task result
	 * @param task
	 * @param priority
	 * @param project
	 * @param tag
	 * @param duedate
	 * @param description
	 * @param isComplete
	 */
	public void checkDisplayOfTaskResult (String task,String project,String priority,String tag,String description,String duedate,boolean isComplete){
		type(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,task,true);
		click(ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH);
		if(!project.isEmpty())
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_DUEDATE.replace("$duedate", project).replace("$task", task));
		if(!priority.isEmpty())
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_PRIORITY.replace("$priority", priority).replace("$task", task));
		if(!tag.isEmpty())
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_DUEDATE.replace("$duedate", tag).replace("$task", task));
		if(!description.isEmpty())
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_DES.replace("$des", description).replace("$task", task));
		if(!duedate.isEmpty())
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_DUEDATE.replace("$duedate", duedate).replace("$task", task));
		if(isComplete)
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_COMPLETED_ICON.replace("$task",task));
		else
			waitForAndGetElement(ELEMENT_SEARCHRESULT_CONTENT_TASKS_ICON.replace("$task", task));
	}
}

