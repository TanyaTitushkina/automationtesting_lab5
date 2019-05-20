package com.alliedtesting.poms;

import com.alliedtesting.controls.WebButton;
import com.alliedtesting.controls.WebLink;
import com.alliedtesting.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddContent extends AbstractPOM {

    public AddContent(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//ul/li/a/span[contains(.,'Article')]")
    public WebLink articleLnk;

    @FindBy(xpath = "//ul/li/a/span[contains(.,'Basic page')]")
    public WebLink basicPageLnk;

}
