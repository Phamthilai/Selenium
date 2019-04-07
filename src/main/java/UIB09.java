import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB09 {
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

        //Select 'Horizontal Slider' link
        driver.findElement(By.linkText("Horizontal Slider")).click();
        System.out.println("Click on Horizontal slider");
        //JavaScript Alerts header title is displayed
        assertTrue("Horizontal Slider title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Horizontal Slider\")]")).isDisplayed());

        WebElement slider = driver.findElement(By.xpath("//div[@class =\"sliderContainer\"]//input[@type =\"range\"]"));
        WebElement valueOfSlide = driver.findElement(By.id("range"));
        System.out.println("Switch to slider");
        //Set Slider to 1
        moveSlider(slider,2);
        System.out.println("Set slider to 1");
        checkSliderValue(valueOfSlide,"1");

        //Set Slider to 2.5
        moveSlider(slider,3);
        System.out.println("Set slider to 2.5");
        checkSliderValue(valueOfSlide,"2.5");

        //Set Slider to 4.5
        moveSlider(slider,4);
        System.out.println("Set slider to 2.5");
        checkSliderValue(valueOfSlide,"4.5");

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
     * Set slider by press ARROW_RIGHT key
     * @param element: slider
     * @param numberOfStep: move slide to in how many steps
     */
    public void moveSlider(WebElement element,Integer numberOfStep){

        for (int i = 1; i <= numberOfStep ; i++) {
            element.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    /**
     * Check value of slide
     * @param element: slider
     * @param expectedValue: value to compare with actual value
     */

    public void checkSliderValue(WebElement element,String expectedValue){

        String actualValue = element.getText();
        assertEquals(expectedValue,actualValue);

    }

}
