import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB02 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\laitpham\\Type Driver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testDragAndDrop() throws Exception {

        //Select 'Drag and Drop' link
        driver.findElement(By.linkText("Drag and Drop")).click();
        System.out.println("Clicked in Drag and Drop link");
        assertTrue("Checkboxes page is displayed", driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Drag and Drop\")]")).isDisplayed());

        WebElement dragColumnA = driver.findElement(By.id("column-a"));
        WebElement dropColumnB = driver.findElement(By.id("column-b"));
        System.out.println("Defined drag and drop elements");

        //Drag and Drop column A to column B
        dragDrop("#column-a","#column-b");
        System.out.println("Drag column A to column B success");
        assertEquals("B",driver.findElement(By.xpath("//div[@id= \"column-a\"]//header")).getText());
        assertEquals("A",driver.findElement(By.xpath("//div[@id= \"column-b\"]//header")).getText());

    }

    /**
     * This function is to handle Drag and Drop element in website
     * This use Javascript to handle, because Actions.draganddrop in selenium don't support for HTML5
     * @param source : drag from
     * @param target: drop to
     */

    public void dragDrop( String source, String target) {

        String script = "(function( $ ) {" +
                "    $.fn.simulateDragDrop = function(options) {" +
                "        return this.each(function() {" +
                "            new $.simulateDragDrop(this, options);" +
                "        });" +
                "    };" +
                "    $.simulateDragDrop = function(elem, options) {" +
                "        this.options = options;" +
                "        this.simulateEvent(elem, options);" +
                "    };" +
                "    $.extend($.simulateDragDrop.prototype, {" +
                "        simulateEvent: function(elem, options) {" +
                "            /*Simulating drag start*/" +
                "            var type = 'dragstart';" +
                "            var event = this.createEvent(type);" +
                "            this.dispatchEvent(elem, type, event);" +
                "            /*Simulating drop*/" +
                "            type = 'drop';" +
                "            var dropEvent = this.createEvent(type, {});" +
                "            dropEvent.dataTransfer = event.dataTransfer;" +
                "            this.dispatchEvent($(options.dropTarget)[0], type, dropEvent);" +
                "            /*Simulating drag end*/" +
                "            type = 'dragend';" +
                "            var dragEndEvent = this.createEvent(type, {});" +
                "            dragEndEvent.dataTransfer = event.dataTransfer;" +
                "            this.dispatchEvent(elem, type, dragEndEvent);" +
                "        }," +
                "        createEvent: function(type) {" +
                "            var event = document.createEvent(\"CustomEvent\");" +
                "            event.initCustomEvent(type, true, true, null);" +
                "            event.dataTransfer = {" +
                "                data: {" +
                "                }," +
                "                setData: function(type, val){" +
                "                    this.data[type] = val;" +
                "                }," +
                "                getData: function(type){" +
                "                    return this.data[type];" +
                "                }" +
                "            };" +
                "            return event;" +
                "        }," +
                "        dispatchEvent: function(elem, type, event) {" +
                "            if(elem.dispatchEvent) {" +
                "                elem.dispatchEvent(event);" +
                "            }else if( elem.fireEvent ) {" +
                "                elem.fireEvent(\"on\"+type, event);" +
                "            }" +
                "        }" +
                "    });" +
                "})(jQuery);";
        ((JavascriptExecutor)driver).executeScript(script+"$('"+source+"').simulateDragDrop({ dropTarget: '"+target+"'});" );
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
