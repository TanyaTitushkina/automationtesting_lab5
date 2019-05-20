package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.*;
import org.testng.annotations.Test;

public class AddNewBasicPage extends BaseTest {

    @Test
    public void addNewBasicPage() {
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
        addContent.basicPageLnk.click();

        menu.waitTitleToBe("Create Basic page", 10);
        CreateArticle createArticle = new CreateArticle(driver);
        String pageTitle = "Hobbits";
        createArticle.titleInput.sendKeys(pageTitle);
        createArticle.sourceFormatLnk.click();
        Helpers.sleep(2000);
        createArticle.textAreaInput.sendKeysWithoutCheckEquality("<p><strong>Famous Hobbits:</strong></p><p>Bilbo Baggins</p><p>Frodo Baggins</p><p>Sam Gamgee</p><p>Merry Brandybuck</p><p>Pippin Took</p>");
        createArticle.menuSettings.click();
        createArticle.provideMenuLink.select();
        createArticle.saveBtn.click();

        menu.waitTitleToBe(pageTitle, 3);
        Article article = new Article(driver);
        article.checkCreatedPageMessage(pageTitle);
        article.checkIfContains("Famous Hobbits");

        menu.contentLnk.click();
        menu.waitTitleToBe("Content", 10);
        content.checkContentType(pageTitle, "Basic page");
        content.checkContentStatus(pageTitle, "Published");
    }
}
