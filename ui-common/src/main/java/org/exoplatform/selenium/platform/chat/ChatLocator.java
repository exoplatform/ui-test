package org.exoplatform.selenium.platform.chat;

import org.exoplatform.selenium.platform.PlatformBase;

import org.openqa.selenium.By;


public class ChatLocator extends PlatformBase {
    public final String ELEMENT_CHAT_UISTATUSPROFILEPORTLET = ".//*[@id='UIStatusProfilePortlet']//*[@class='${icon}' and @data-original-title='${status}']";
    public final String ELEMENT_CHAT_TOOLTIP = "//*[contains(@class,'tooltip-inner') and contains(text(),'${tooltip}')]";
    public final String ELEMENT_CHAT_STATUS = "//a[@class='chat-status']/*[contains(text(),'${status}')]";
    public final By ELEMENT_CHAT_ICON = By.xpath(".//*[@id='chat-status']/a");
}



