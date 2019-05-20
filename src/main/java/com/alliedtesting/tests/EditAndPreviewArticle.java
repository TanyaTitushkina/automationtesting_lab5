package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.Article;
import com.alliedtesting.poms.Content;
import com.alliedtesting.poms.CreateArticle;
import com.alliedtesting.poms.Menu;
import org.testng.annotations.Test;

public class EditAndPreviewArticle extends BaseTest {

    @Test
    public void editAndPreviewArticle() {
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
        String articleNewContent = "content was edited";
        createArticle.fillInContent(articleNewContent);
        createArticle.tagInput.clear();
        createArticle.tagInput.sendKeys("edited");

        log.info("===Preview edited content===");
        createArticle.previewBtn.click();

        menu.waitTitleToBe(articleTitle, 10);
        Article article = new Article(driver);
        article.contentTxt.checkIfEquals(articleNewContent);
        article.tagTxt.checkIfEquals("edited");
        article.backToEditLnk.click();

        log.info("===Check after Preview content has the same values===");
        createArticle.titleInput.checkIfEquals(articleTitle);
        Helpers.sleep(500);
        createArticle.checkContent(articleNewContent);

        log.info("===Save new content===");
        createArticle.saveBtn.click();
    }
}
