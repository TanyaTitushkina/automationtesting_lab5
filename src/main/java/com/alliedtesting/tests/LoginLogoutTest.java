package com.alliedtesting.tests;

import com.alliedtesting.core.BaseTest;
import com.alliedtesting.poms.Menu;
import org.testng.annotations.Test;

public class LoginLogoutTest extends BaseTest {
    @Test
    public void loginLogoutTest(){
//        loginWithDefaultUser();
        loginWithUser("allied2019", "LbQ8al0&HVnH2vLrnn1");

        Menu menu = new Menu(driver);
        menu.logoutLnk.click();
        menu.waitTitleToBe("Welcome to Allied Automation", 10);
    }
}
