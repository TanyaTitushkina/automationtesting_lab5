package com.alliedtesting.poms;

import com.alliedtesting.controls.WebLink;
import com.alliedtesting.controls.WebText;
import com.alliedtesting.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.Link;

public class Menu extends AbstractPOM {

    public Menu(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(@class,'title')]")
    public WebText pageTitle;

    @FindBy(xpath = "//ul[@class='clearfix menu']/li/a[contains(.,'Log out')]")
    public WebLink logoutLnk;

    @FindBy(xpath = "//div[@id='toolbar-administration']//a[contains(.,'Content')]")
    public WebLink contentLnk;

    @FindBy(xpath = "//div[@id='toolbar-administration']//a[contains(.,'Manage')]")
    public WebLink manageLnk;

    @FindBy(xpath = "//div[@class='toolbar-menu-administration']//a[contains(.,'People')]")
    public WebLink peopleLnk;

    @FindBy(xpath = "//ul[@class='clearfix menu']//a[text()='Home']")
    public WebLink homeLnk;

    public void waitTitleToBe(String value, int seconds){
        log.info("Check that page title is '" + value + "' in '" + seconds + "' seconds");
        (new WebDriverWait(driver, seconds)).until(ExpectedConditions.textToBePresentInElement(pageTitle, value));
    }

}
