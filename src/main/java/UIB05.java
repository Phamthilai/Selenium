import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB05 {
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
    public void testEditor() throws Exception {

        //Select 'WYSIWYG Editor' link"
        driver.findElement(By.linkText("WYSIWYG Editor")).click();
        System.out.println("Clicked WYSIWYG Editor link");
        assertTrue("WYSIWYG Editor title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"An iFrame containing the TinyMCE WYSIWYG Editor\")]")).isDisplayed());

        // Switch into Frame
        WebElement editorFrame = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(editorFrame);
        System.out.println("Switched Editor frame");
        WebElement editBody = driver.findElement(By.id("tinymce"));

        // Verify the default content
        // The default content body is 'Your content goes here.'
        String expectedDefaultContent = "Your content goes here.";
        checkContent(editBody,expectedDefaultContent);
        System.out.println("Default content: " +editBody.getText());

        //Set new content "Hello, how are you?"
        String newContent = "Hello, how are you?";
        editBody.clear();
        editBody.sendKeys("Hello, how are you?");
        System.out.println("Set new content success");
        checkContent(editBody,newContent);

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
     * This function is to check the value of element
     * @param element: WebElement that you want to check content
     * @param expectedContent: Value that you expected to compare with actual value
     */
    public void checkContent(WebElement element,String expectedContent){
        String actualContent = element.getText();
        assertEquals(actualContent,expectedContent);

    }
}
