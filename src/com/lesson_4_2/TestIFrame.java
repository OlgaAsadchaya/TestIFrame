package com.lesson_4_2;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIFrame {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.close();
    }
    @Test
    public void iFrameTest() {
        WebElement textframe = driver.findElement(By.id("mce_0_ifr"));

        driver.switchTo().frame(textframe);
        WebElement el = driver.findElement(By.xpath("//body[@id='tinymce' and @class='mce-content-body ']"));
        el.clear();
        el = driver.findElement(By.xpath("//body[@id='tinymce' and @class='mce-content-body ']"));
        el.sendKeys("Hello ");

        driver.switchTo().parentFrame();
        WebElement button = driver.findElement(By.xpath("//i[@class='mce-ico mce-i-bold']"));
        button.click();
        driver.switchTo().frame(textframe);
        el = driver.findElement(By.xpath("//body[@id='tinymce' and @class='mce-content-body ']"));
        el.sendKeys("world!");

        WebElement textEl = driver.findElement(By.xpath("//body[@id='tinymce' and @class='mce-content-body ']/p"));
        String txt = textEl.getText();
        assertEquals(txt, "Hello \uFEFFworld!");
    }
}
