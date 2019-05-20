package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.core.Helpers;
import com.alliedtesting.poms.AddUser;
import com.alliedtesting.poms.Menu;
import com.alliedtesting.poms.People;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewUserTest extends BaseTest {

    @Test
    public void addNewUserTest(){
        loginWithDefaultUser();
        Menu menu = new Menu(driver);
        if(!menu.peopleLnk.isDisplayed()){
            menu.manageLnk.click();
        }
        menu.peopleLnk.click();

        menu.waitTitleToBe("People", 10);

        People people = new People(driver);
        people.addUserBtn.click();

        menu.waitTitleToBe("Add user", 10);

        AddUser addUser = new AddUser(driver);

        String newRandomUser = RandomStringUtils.randomAlphanumeric(8);
        String newPassword = RandomStringUtils.randomAlphanumeric(16);

        log.info("User='" + newRandomUser + "'\nPass='" + newPassword + "'");

        addUser.emailInput.sendKeys(newRandomUser +"@mail.com");
        addUser.usernameAddUserInput.sendKeys(newRandomUser);
        addUser.passwordAddUserInput.sendKeys(newPassword);
        addUser.confirmPassInput.sendKeys(newPassword);
        addUser.statusRb.selectByVisibleText("Blocked");
//        addUser.setStatusRb("Blocked");
        addUser.notifyUserOfNewAccountChb.select();
        addUser.pictureFileInput.setFileToUpload("D:/IdeaProjects/automationtesting/resources/JD-44-512.png");
        addUser.timeZoneSelect.selectByVisibleText("Chisinau");
        addUser.createNewAccount.click();

        Helpers.waitForVisibleElement(driver, addUser.infoMessage, 20);
        String actualMessageTxt = addUser.infoMessage.getText().trim().replace("\\s+", " ");
        String expectedMessageTxt = "Created a new user account for " + newRandomUser +". No email has been sent.";
        Helpers.check2StringIfEquals(actualMessageTxt, expectedMessageTxt);
    }
}
