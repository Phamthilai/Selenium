
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB06 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
//        System.setProperty("webdriver.gecko.driver","C:\\Users\\laitpham\\Type Driver\\geckodriver-v0.24.0-win64\\geckodriver.exe");
//////        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testAlert() throws Exception {

        //JavaScript Alerts header title is displayed
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        System.out.println("Clicked JavaScript Alerts link");
        assertTrue("JavaScript Alerts title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"JavaScript Alerts\")]")).isDisplayed());

        // Click 'Click for JS Alert' button
        driver.findElement(By.xpath("//button[@onclick = \"jsAlert()\"]")).click();
        System.out.println("Clicked on JsAlert alert");

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println("Switched to jsAlert");

        //I am a JS Alert' message is displayed
        String alertMessage = alert.getText();
        checkMessage(alertMessage,"I am a JS Alert");

        // Click on OK button in alert
        // Result message: You successfully clicked an alert
        alert.accept();
        System.out.println("Clicked on OK button JsAlert alert");
        WebElement resultMessage =  driver.findElement(By.id("result"));
        System.out.println("Message: " +resultMessage.getText());
        checkMessage(resultMessage.getText(),"You successfuly clicked an alert");

        // "Click 'I am a JS Confirm' button
        driver.findElement(By.xpath("//button[@onclick =\"jsConfirm()\"]")).click();
        System.out.println("Clicked on JsConfirm button");

        // Click 'Cancel' button to close Alert"
        // Result message: You clicked: Cancel
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        alert.dismiss();
        System.out.println("Click on Canceled button");
        checkMessage(resultMessage.getText(),"You clicked: Cancel");

        //"Click 'Click for JS Prompt' button
        driver.findElement(By.xpath("//button[@onclick = \"jsPrompt()\"]")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        System.out.println("Hello");
        //Set text ""Hello'
        alert.sendKeys("Hello");
        System.out.println("Hello1");
        String actualResult = alert.getText();
        System.out.println(actualResult);
        //Click 'OK' button to close Alert"
//       alert.accept();
       //checkMessage(actualResult,"You entered: Hello");


    }
    @After
    public void tearDown() throws Exception {
//        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    /**
     * This funtion is to check message of alert
     * @param actualMessage: Value of element
     * @param expectedMessage: Value that you need to verify
     */

    public void checkMessage(String actualMessage,String expectedMessage){
        assertEquals(actualMessage,expectedMessage);

    }
}
