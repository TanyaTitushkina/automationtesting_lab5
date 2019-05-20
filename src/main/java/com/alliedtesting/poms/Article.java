package com.alliedtesting.poms;

import com.alliedtesting.controls.WebButton;
import com.alliedtesting.controls.WebLink;
import com.alliedtesting.controls.WebText;
import com.alliedtesting.core.AbstractPOM;
import com.alliedtesting.core.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Article extends AbstractPOM {

    public Article(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//div[@role='contentinfo']")
    public WebText infoMessage;

    @FindBy(xpath="//article/div[contains(@class,'node__content')]//p")
    public WebText contentTxt;

    @FindBy(xpath = "//ul[@class='links field__items']//a")
    public WebText tagTxt;

    @FindBy(xpath = "//a[@id='edit-backlink' and text()='Back to content editing']")
    public WebLink backToEditLnk;

    public void checkCreatedArticleMessage(String articleName){
        String message = "Article " + articleName + " has been created.";
        Helpers.check2StringIfContains(infoMessage.getText(), message);
    }

    public void checkCreatedPageMessage(String basicPageName){
        String message = "Basic page " + basicPageName + " has been created.";
        Helpers.check2StringIfContains(infoMessage.getText(), message);
    }

    public void checkIfContains(String text){
        Assert.assertTrue(contentTxt.getText().contains(text));
    }
}
