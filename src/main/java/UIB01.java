import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB01 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\laitpham\\Type Driver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckboxes() throws Exception {
        //Select ' Checkboxes' link
        driver.findElement(By.linkText("Checkboxes")).click();
        assertTrue("Checkboxes page is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Checkboxes\")]")).isDisplayed());
        System.out.println("Checkboxes page is displayed");

        // Check 'checkbox 1'
        // Uncheck 'checkbox 2'
        driver.findElement(By.xpath("(//form[@id = \"checkboxes\"]/input)[1]")).click();
        System.out.println("Checkbox 1 is clicked");
        driver.findElement(By.xpath("(//form[@id = \"checkboxes\"]/input)[last()]")).click();
        System.out.println("Checkbox 2 is clicked");

        assertTrue("Checkboxes 1 is selected",driver.findElement(By.xpath("(//form[@id = \"checkboxes\"]/input)[1]")).isSelected());
        assertFalse("Checkboxes 2 is not selected", driver.findElement(By.xpath("(//form[@id = \"checkboxes\"]/input)[last()]")).isSelected());

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