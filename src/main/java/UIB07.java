import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB07 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\laitpham\\Type Driver\\geckodriver-v0.24.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testKeyPress() throws Exception {

        //Select 'Key Presses' link
        driver.findElement(By.linkText("Key Presses")).click();
        System.out.println("Click on Key Press link");
        assertTrue("Dropdown list title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Key Presses\")]")).isDisplayed());

        Robot rb = new Robot();
        //Presses 'TAB' key
        rb.keyPress(KeyEvent.VK_TAB);
        System.out.println("Press TAB");
        checkResult("TAB");

        //Presses 'ENTER' key
        rb.keyPress(KeyEvent.VK_ENTER);
        System.out.println("Press ENTER");
        checkResult("ENTER");

        // Press G keyboard key
        rb.keyPress(KeyEvent.VK_G);
        System.out.println("Press G");
        checkResult("G");

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
     * Check result after press Key
     * @param keyName: Key that press
     */

    public void checkResult(String keyName){
        String expectedResult = "You entered: " + keyName ;
        String actualResult = driver.findElement(By.id("result")).getText();
        System.out.println("Actual result: " +actualResult+"  and expected result is: "+expectedResult);
        assertEquals(expectedResult,actualResult);

    }
}
