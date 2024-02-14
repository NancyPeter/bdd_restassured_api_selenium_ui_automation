package org.example.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.java.After;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.pages.Login;
import org.example.utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class StepDefinitions {
    public String BASE_URL = "";
    public Response all_emp_response;
    public JsonPath jsonPath;
    public WebDriver driver;
    WebDriverWait wait;

    /* API */
    @Given("^Employee list API \"([^\"]*)\" is provided$")
    public void employeeListAPIIsProvided(String base_url) throws Exception {
        BASE_URL = base_url;
    }

    @When("^User call Employee list \"([^\"]*)\" API$")
    public void userCallEmployeeListAPI(String endpoint) throws Exception {
        all_emp_response = Utils.get(BASE_URL+endpoint);
    }

    @Then("^Employee list \"([^\"]*)\" will be shown$")
    public void employeeListWillBeShown(String response_message) throws Exception {
        jsonPath = all_emp_response.jsonPath();
        String actual_response_message = jsonPath.get("message");
        Assert.assertEquals(response_message, actual_response_message);
        System.out.println(actual_response_message);
    }

    @Then("^Employee API returns response code (\\d+)$")
    public void employeeAPIReturnsResponseCode(int response_code) throws Exception {
        Assert.assertEquals(response_code, all_emp_response.getStatusCode());
        System.out.println(all_emp_response.getStatusCode());
    }

    /* UI */
    @Given("^User visits e-commerce website$")
    public void user_visits_e_commerce_website() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        ChromeOptions ops=new ChromeOptions();
        ops.addArguments("--headed"); //uncomment if you want to run in headless mode
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
    }

    @When("^User enters valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_valid_credentials(String username, String password) throws Exception {
        wait=new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.className("login"))); // wait until getting the login button
        Login login=new Login(driver);
        login.doLogin(username,password);
    }

    @Then("^User can logged in successfully$")
    public void user_can_logged_in_successfully() throws Exception {
        WebElement lblUserName=driver.findElement(By.xpath("//span[contains(text(),'Test User')]"));
        Assert.assertEquals(lblUserName.getText(),"Test User");
        driver.quit();
    }

}
