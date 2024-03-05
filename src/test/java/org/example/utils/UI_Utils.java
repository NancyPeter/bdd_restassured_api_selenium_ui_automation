package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UI_Utils {

    public static boolean wait_for_an_element(WebDriver driver, int time_unit, String dynamic_xpath){
        WebDriverWait wait=new WebDriverWait(driver,time_unit);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamic_xpath)));
        return true;
    }

    /*  Selenium Methods */
    public static WebDriver LaunchBrowser(WebDriver driver, String browsername){
        if(browsername.equals("SF")){
            System.setProperty("webdriver.safari.driver", "Drivers\\safaridriver.exe");
            driver= new SafariDriver();
        }else if(browsername.equals("CH")){
            System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
            driver= new ChromeDriver();
        }else if(browsername.equals("FF")){
            driver= new FirefoxDriver();
        }else if(browsername.equals("IE")){
            System.setProperty("webdriver.ie.driver", "Drivers\\IEDriverServer.exe");
            driver= new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        return driver;
    }

    public static void OpenURL(WebDriver driver, String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void MouseOver(WebDriver driver, WebElement element){
        Actions actObj=new Actions(driver);
        actObj.moveToElement(element).build().perform();
    }

    public static void dropDown(WebElement WE, String VisibleText){
        Select selObj=new Select(WE);
        selObj.selectByVisibleText(VisibleText);
    }

    public static String Click(WebDriver driver, int time_unit, String dynamic_xpath){
        boolean response = wait_for_an_element(driver, time_unit, dynamic_xpath);
        if(response){
            driver.findElement(By.xpath(dynamic_xpath)).click();
            return "passed";
        }else{
            return "element not located";
        }
    }

    public static String SendKeys(WebDriver driver, int time_unit, String dynamic_xpath){
        boolean response = wait_for_an_element(driver, time_unit, dynamic_xpath);
        if(response){
            driver.findElement(By.xpath(dynamic_xpath)).sendKeys("Test");
            return "passed";
        }else{
            return "element not located";
        }
    }

    public static String DragAndDrop(WebDriver driver, String source_element, String dest_element){
        WebElement sourceLocator = driver.findElement(By.xpath(source_element));
        WebElement destinationLocator = driver.findElement(By.xpath(dest_element));
        Actions actions=new Actions(driver);
        actions.dragAndDrop(sourceLocator, destinationLocator).build().perform();
        return "passed";
    }

}
