import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class UIB08 {
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
    public void testColorAndFont() throws Exception {
        //Select 'Challenging DOM' link
        driver.findElement(By.linkText("Challenging DOM")).click();
        System.out.println("Clicked on Challenging DOM");
        assertTrue("Dropdown list title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Challenging DOM\")]")).isDisplayed());

        //Verify font size of 'foo' button'
        WebElement barButton = driver.findElement(By.xpath("//a[@class = \"button success\"]"));
        String actualFontSize = barButton.getCssValue("font-size");
        System.out.println("Get font-size of foo button");
        //Font size is 16px
        assertEquals(actualFontSize,"16px");

        //Verify background-color of 'qux' button
        WebElement quzButton = driver.findElement(By.xpath("//a[@class = \"button alert\"]"));
        String actualBgColor = quzButton.getCssValue("background-color");
        System.out.println("Get background-color of quz button");
        //verify background color is RED
        assertEquals("rgb(198, 15, 19)" ,actualBgColor);

        //Verify border-color of 'baz' button
        WebElement bazButton = driver.findElement(By.xpath("//a[@class = \"button\"]"));
        String actualBolderColor = bazButton.getCssValue("border-top-color");
        System.out.println("Get border-top-color of baz button");
        assertEquals( "rgb(34, 132, 161)",actualBolderColor);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
