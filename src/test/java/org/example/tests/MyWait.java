package org.example.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyWait {

    private WebDriver driver;

    @FindBy(css = "button[title='Нет, спасибо']")
    private WebElement banner;

    public void WaitForElement(int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        wait.until(ExpectedConditions.elementToBeClickable(banner));
    }

    public MyWait(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
