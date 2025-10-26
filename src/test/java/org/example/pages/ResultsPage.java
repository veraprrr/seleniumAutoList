package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {

    private WebDriver driver;

    @FindBy(css = "#text")
    private WebElement searchField;

    @FindBy(css = "input[name='text']")
    private WebElement inputField;

    @FindBy(css = "a > h2")
    private List<WebElement> results;

    public void clickElement(int num) {
        results.get(num).click();
        int searchNumber = num + 1;
        System.out.println("Кликнули по " + searchNumber + " элементу");
    }

    public String getTextFromSearchField(){
        String val = inputField.getAttribute("value");
        System.out.println("В строке поиска текст: " + val);
        return val;
    }

    public ResultsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
