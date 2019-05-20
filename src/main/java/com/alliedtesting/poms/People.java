package com.alliedtesting.poms;

import com.alliedtesting.controls.WebButton;
import com.alliedtesting.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class People extends AbstractPOM {

    public People(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(.,'Add user')]")
    public WebButton addUserBtn;

}
