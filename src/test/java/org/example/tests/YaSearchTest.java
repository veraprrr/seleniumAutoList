package org.example.tests;

import org.example.pages.MainPage;
import org.example.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YaSearchTest {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.ya.ru");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium.dev";
        String searchUrl = "https://www.selenium.dev/";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        MyWait mw = new MyWait(driver);
        mw.WaitForElement(4);

        banner();

        List<WebElement> results = driver.findElements(By.cssSelector("a > h2"));

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);
        ArrayList tabs = new ArrayList<> (driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1).toString());

        assertEquals(searchUrl, driver.getCurrentUrl(), "Не открылась первая вкладка");

    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";

        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        banner();

        ResultsPage rp = new ResultsPage(driver);

        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }

    public void banner (){
        By bannerClose = By.cssSelector("button[title='Нет, спасибо']");
        WebElement bannerClick = driver.findElement(bannerClose);
        bannerClick.click();
    }
}