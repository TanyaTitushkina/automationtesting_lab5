package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.*;
import org.testng.annotations.Test;

public class AddNewComment extends BaseTest {

    @Test
    public void addNewComment() {
        loginWithDefaultUser();
        Menu menu = new Menu(driver);
        menu.homeLnk.click();

        HomePage homePage = new HomePage(driver);
        homePage.openArticle("Article1");

        Comment comment = new Comment(driver);
        comment.subjectInput.sendKeys("Comment Subject");
        comment.fillInComment("I like to post comments");
        comment.saveBtn.click();

        Article article = new Article(driver);
        Helpers.check2StringIfContains(article.infoMessage.getText(), "Your comment has been posted.");

        comment.checkCommentSubject("Comment Subject");
        comment.checkCommentText("I like to post comments");

    }
}
