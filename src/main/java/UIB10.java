import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB10 {
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
    public void testMenu() throws Exception {

        //Select 'JQuery UI Menus' link
        driver.findElement(By.linkText("JQuery UI Menus")).click();
        System.out.println("Click on JQuery UI Menus");
        //JQueryUI - Menu header title is displayed
        assertTrue("JQueryUI - Menu title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"JQueryUI - Menu\")]")).isDisplayed());

        //Select Enabled -> JQuery UI menu
        WebElement mainMenu = driver.findElement(By.xpath("//li[@id =\"ui-id-3\"]//a[contains(text(),\"Enabled\")]"));
        Actions action = new Actions(driver);
        action.moveToElement(mainMenu).perform();
        System.out.println("Move to main Menu ");

        //Click on 'JQuery UI menu' link
        WebElement subMenu = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[@id =\"ui-id-8\"]//a[contains(text(),\"Back to JQuery UI\")]"))));
        action.moveToElement(subMenu).click().build().perform();
        System.out.println("Move to JQuery UI menu");
        //JQueryUI - Menu header title is displayed
        assertTrue("JQuery UI title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"JQuery UI\")]")).isDisplayed());

        //Click on 'Menu' link
        driver.findElement(By.linkText("Menu")).click();
        System.out.println("Click on Menu link");
        //JQueryUI - Menu header title is displayed
        assertTrue("JQueryUI - Menu title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"JQueryUI - Menu\")]")).isDisplayed());


    }
    @After
    public void tearDown() throws Exception {
//        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
