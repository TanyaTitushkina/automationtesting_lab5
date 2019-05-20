package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class AddNewArticle extends BaseTest {

    @Test
    public void addNewArticle(){
        loginWithDefaultUser();
        Menu menu = new Menu(driver);
        if(!menu.contentLnk.isDisplayed()){
            menu.manageLnk.click();
        }
        menu.contentLnk.click();

        menu.waitTitleToBe("Content", 10);

        Content content = new Content(driver);
        content.addContentBtn.click();

        menu.waitTitleToBe("Add content", 10);
        AddContent addContent = new AddContent(driver);
        addContent.articleLnk.click();

        menu.waitTitleToBe("Create Article", 10);
        CreateArticle createArticle = new CreateArticle(driver);
        String newArticleNumber = RandomStringUtils.randomAlphanumeric(4);
        String newArticleTitle = "test article " + newArticleNumber;
        createArticle.titleInput.sendKeys(newArticleTitle);
        createArticle.fillInContent("content to check");
        createArticle.tagInput.sendKeys("test");
        createArticle.imageInput.setFileToUpload("D:/IdeaProjects/automationtesting/resources/1468678199118775206.jpg");
        Helpers.waitForVisibleElement(driver, createArticle.altText,2);
        createArticle.altText.sendKeys("shire");
        createArticle.saveBtn.click();

        log.info("===Check new Article is published with correct content===");
        menu.waitTitleToBe(newArticleTitle, 10);
        Article article = new Article(driver);
        article.checkCreatedArticleMessage(newArticleTitle);
        article.contentTxt.checkIfEquals("content to check");
        article.tagTxt.checkIfEquals("test");

        log.info("===Check new Article's presence in content table===");
        menu.contentLnk.click();
        menu.waitTitleToBe("Content", 10);
        content.checkContentType(newArticleTitle, "Article");
        content.checkContentStatus(newArticleTitle, "Published");
    }
}
