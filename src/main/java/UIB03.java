import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB03 {
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
    public void testDropdown() throws Exception {

        //Select 'Dropdown' link
        driver.findElement(By.linkText("Dropdown")).click();
        System.out.println("Clicked in Dropdown link");
        assertTrue("Dropdown list title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Dropdown List\")]")).isDisplayed());

        Select dropdownList = new Select(driver.findElement(By.id("dropdown")));
        System.out.println("Selected dropdownList");

        // Select item by label ''Option 2'
        dropdownList.selectByVisibleText("Option 2");
        System.out.println("Selected Option2");
        //The current item is 'Option 2'
        assertEquals("Option 2", getActualResult(dropdownList));

        //Select item by index 1
        dropdownList.selectByIndex(1);
        System.out.println("Selected index1");
        assertEquals("Option 1", getActualResult(dropdownList));

        //Select item by value 2
        dropdownList.selectByIndex(2);
        System.out.println("Selected index2");
        assertEquals("Option 2", getActualResult(dropdownList));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    /**
     * This function is to get the selected value of dropdown list
     * @param dropdown: get dropdown list
     * @return: selected value
     */
    public String getActualResult(Select dropdown){
        String actualResult = dropdown. getFirstSelectedOption().getText();
        return actualResult;
    }
}
