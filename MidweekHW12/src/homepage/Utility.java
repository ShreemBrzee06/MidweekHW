package homepage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class Utility extends BaseClass {
    public void clickOnElement (By by){
        driver.findElement(by).click();
    }

   public void mouseHooverMethod(By by){
       Actions actions= new Actions(driver);
       WebElement mouseHoover = driver.findElement(by);
       actions.moveToElement(mouseHoover).build().perform();
    }
    public void clearTextFromField(By by) {
        driver.findElement(by).sendKeys(Keys.CONTROL + "a");
        driver.findElement(by).sendKeys(Keys.DELETE);
    }
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }
    public String getTextFromElement(By by) {
        String getTxt= driver.findElement(by).getText();// logout
        System.out.println(getTxt);
        return getTxt;


    }
    public void verifyTextMsg(String expectedMsg,By by, String displayMSG){
        String actualMsg = getTextFromElement(by);
        Assert.assertEquals(displayMSG,expectedMsg,actualMsg);
    }
    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        WebElement mouseHoover = driver.findElement(by);
        //   Thread.sleep(3000);
        //  waitUnitVisibilityOfElementLocated(by,3000);
        actions.moveToElement(mouseHoover).click().build().perform();

    }

}
