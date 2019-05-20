package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.poms.Content;
import com.alliedtesting.poms.Menu;
import org.testng.annotations.Test;

public class DeleteBasicPage extends BaseTest {

    @Test
    public void deleteBasicPage() {
        loginWithDefaultUser();
        Menu menu = new Menu(driver);
        if (!menu.contentLnk.isDisplayed()) {
            menu.manageLnk.click();
        }
        menu.contentLnk.click();

        menu.waitTitleToBe("Content", 10);

        Content content = new Content(driver);
        String pageTitle = "Hobbits";
        content.checkContentType(pageTitle, "Basic page");
        content.selectContent(pageTitle);

        if(!"Delete content".equals(content.selectAction.getText())) {
            content.selectAction.selectByVisibleText("Delete content");
        }

        content.applyBtn.click();

        menu.waitTitleToBe("Are you sure you want to delete this content item?",2);
        content.deleteBtn.click();

        content.checkContentMsgStatus("Deleted 1 content item.");
    }
}
