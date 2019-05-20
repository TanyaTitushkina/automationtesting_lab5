package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.Article;
import com.alliedtesting.poms.Content;
import com.alliedtesting.poms.CreateArticle;
import com.alliedtesting.poms.Menu;
import org.testng.annotations.Test;

public class EditAndPreviewBasicPage extends BaseTest {

    @Test
    public void editAndPreviewBasicPage() {
        loginWithDefaultUser();
        Menu menu = new Menu(driver);
        if (!menu.contentLnk.isDisplayed()) {
            menu.manageLnk.click();
        }
        menu.contentLnk.click();

        menu.waitTitleToBe("Content", 10);

        Content content = new Content(driver);
        String pageTitle = "Hobbits";
        content.editContentBtn(pageTitle).click();

        menu.waitTitleToBe("Edit Basic page " + pageTitle, 20);
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.clearContent();
        String pageNewContent = "At the beginning of the story was Bilbo Baggins.";
        createArticle.fillInContent(pageNewContent);

        log.info("===Preview edited content===");
        createArticle.previewBtn.click();

        menu.waitTitleToBe(pageTitle, 3);
        Article article = new Article(driver);
        article.contentTxt.checkIfEquals(pageNewContent);
        article.backToEditLnk.click();

        createArticle.titleInput.checkIfEquals(pageTitle);
        Helpers.sleep(500);
        createArticle.checkContent(pageNewContent);

        log.info("===Save new content===");
        createArticle.saveBtn.click();
    }
}
