package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.Article;
import com.alliedtesting.poms.Content;
import com.alliedtesting.poms.CreateArticle;
import com.alliedtesting.poms.Menu;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class EditArticle extends BaseTest {

    @Test
    public void editArticle() {
        loginWithDefaultUser();
        Menu menu = new Menu(driver);
        if (!menu.contentLnk.isDisplayed()) {
            menu.manageLnk.click();
        }
        menu.contentLnk.click();

        menu.waitTitleToBe("Content", 10);

        Content content = new Content(driver);
        String articleTitle = "Article1";
        content.editContentBtn(articleTitle).click();

        menu.waitTitleToBe("Edit Article " + articleTitle, 20);
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.clearContent();
        createArticle.fillInContent("content was edited");
        createArticle.tagInput.clear();
        createArticle.tagInput.sendKeys("edited");
        try {
            if(createArticle.removeImageBtn.isDisplayed()){
                createArticle.removeImageBtn.click();
        }}catch (NoSuchElementException e) {
            log.info("This Article hasn't a picture");
            if (createArticle.imageInput.isDisplayed()) {
                createArticle.imageInput.setFileToUpload("D:/IdeaProjects/automationtesting/resources/1468678199118775206.jpg");
                Helpers.waitForVisibleElement(driver, createArticle.altText, 2);
                createArticle.altText.sendKeys("shire");
            }
        }
        createArticle.saveBtn.click();

        menu.waitTitleToBe("Content", 10);
        content.checkContentMsgStatus("Article " + articleTitle + " has been updated");

        log.info("Check Article is published with edited content");
        content.goToContent(articleTitle);
        menu.waitTitleToBe(articleTitle, 10);
        Article article = new Article(driver);
        article.contentTxt.checkIfEquals("content was edited");
        article.tagTxt.checkIfEquals("edited");
    }
}
