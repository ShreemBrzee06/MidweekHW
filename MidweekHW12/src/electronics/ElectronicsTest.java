package electronics;

import homepage.Utility;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ElectronicsTest extends Utility {
    String basUrl="https://demo.nopcommerce.com/";
    @Before
    public void setUP(){
        openBrowser(basUrl);
    }
    @Test
    public void textVerified(){
        //1.1 Mouse Hover on “Electronics” Tab
        mouseHooverMethod(By.xpath("//ul[@class='top-menu notmobile']//li[2]//a[@ href=\"/electronics\"]"));
        //1.2 Mouse Hover on “Cell phones” and click
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[2]//ul[1]//li[2]")).click();

        //1.3 Verify the text “Cell phones”
        String actualCellPhoneTxt =driver.findElement(By.xpath("//div[@class='page-title']//h1[1]")).getText();
        System.out.println(actualCellPhoneTxt);
        String expectedCellPhoneTxt = "Cell phones";
        Assert.assertEquals("Verifying Txt Msg",expectedCellPhoneTxt,actualCellPhoneTxt);
    }



    @Test
     public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully(){
        //2.1 Mouse Hover on “Electronics” Tab
        //2.2 Mouse Hover on “Cell phones” and click
        //2.3 Verify the text “Cell phones”
        textVerified();

        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[@class='viewmode-icon list']"));

        //2.5 Click on product name “Nokia Lumia 1020” link
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div/h2/a[contains(text(),'Nokia Lumia 1020')]")).click();
        //2.6 Verify the text “Nokia Lumia 1020”
        driver.navigate().refresh();
        String actualNokiaTextMsg = driver.findElement(By.xpath("//div[@class='product-name']//h1[contains(text(),'Nokia Lumia 1020')]")).getText();
        System.out.println(actualNokiaTextMsg);
        String expectedNokiaTextMsg = "Nokia Lumia 1020";
        Assert.assertEquals("Verify Nokia Text MSG",expectedNokiaTextMsg,actualNokiaTextMsg);
        //2.7 Verify the price “$349.00”
        String displayMsgPrice =driver.findElement(By.xpath("//span[@id='price-value-20']")).getText();
        System.out.println(displayMsgPrice);
        String expectedPriceMsg = "$349.00";
        Assert.assertEquals("Verify the price message",expectedPriceMsg,displayMsgPrice);

        //2.8 Change quantity to 2
        driver.navigate().refresh();
        clearTextFromField(By.id("product_enteredQuantity_20"));
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).sendKeys("2");
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        //2.9 Click on “ADD TO CART” tab
        //driver.navigate().refresh();
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top
        //green Bar
        //After that close the bar clicking on the cross button.
        String actualDisplayMsgForCart = driver.findElement(By.xpath("//p[@class='content']")).getText();
        System.out.println(actualDisplayMsgForCart);
        String expectedMsgForCart ="The product has been added to your shopping cart";
        Assert.assertEquals("Verify the display Msg",expectedMsgForCart,actualDisplayMsgForCart);
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHooverMethod(By.xpath("//a[@class='ico-cart']//span[1]"));
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));

        //2.12 Verify the message "Shopping cart"
        String actualShoppingCartMSG = driver.findElement(By.xpath("//div[@class='page-title']//h1[1]")).getText();
        System.out.println(actualShoppingCartMSG);
        String expectedShoppingCartMsg = "Shopping cart";
        Assert.assertEquals("Verify ShoppingCart Msg",expectedShoppingCartMsg,actualShoppingCartMSG);

        //2.13 Verify the quantity is 2
        driver.navigate().refresh();
        String actualQtyMsg = driver.findElement(By.xpath("//tr//td[5]//input[1]")).getText();
        System.out.println(actualQtyMsg);

        //2.14 Verify the Total $698.00
        String actualTotalAmtMSG = driver.findElement(By.xpath("//tr//td[6]//span[1]")).getText();
        System.out.println(actualTotalAmtMSG);

        //update cart
        driver.findElement(By.cssSelector("div>#updatecart")).click();

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//div[@class='totals']//div[3]//input[1]"));
        //2.16 Click on checkout
        clickOnElement(By.id("checkout"));

        //“2.17 Verify the Text “Welcome, Please Sign In!”
        String actualWelcomeMsg = driver.findElement(By.xpath("//div[@class='page-title']//h1[1]")).getText();
        System.out.println(actualWelcomeMsg);

        //Click on “CHECKOUT AS GUEST” Tab
        driver.findElement(By.cssSelector("button.button-1.checkout-as-guest-button")).click();

        //2.22 Fill the all mandatory field
        billingInformation();

        //2.29 Click on "2nd day Air($0.00)
        clickOnElement(By.cssSelector("input#shippingoption_2"));

        //2.30 click on Continue Button
        clickOnElement(By.cssSelector("button[onclick='ShippingMethod.save()']"));

        //2.31 Select Radio Button “Credit Card”
        driver.findElement(By.cssSelector("#paymentmethod_1")).click();

        // click on Continue
        driver.findElement(By.cssSelector("button[onclick='PaymentMethod.save()']")).click();

        //2.32 Select “Visa” From Select credit card dropdown
        WebElement dropDownCreditType = driver.findElement(By.cssSelector("select.dropdownlists"));
        dropDownCreditType.click();
        Select dropDown = new Select(dropDownCreditType);
        dropDown.selectByVisibleText("Visa");

        // 2.33 Fill all the details
        cardInfo();

        //2.34 Click on “CONTINUE”
        driver.findElement(By.cssSelector("button[onclick='PaymentInfo.save()']")).click();

        //2.35 Verify “Payment Method” is “Credit Card”
        String actualPaymentMsg = getTextFromElement(By.cssSelector("ul>li.payment-method>span:last-of-type"));
        String expectedPaymentMsg="Credit Card";
        Assert.assertEquals("Verify Payment Msg",expectedPaymentMsg,actualPaymentMsg);

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        String shippingMethod = driver.findElement(By.cssSelector("ul>li.shipping-method>span:last-of-type")).getText();
        String expectedShippingMethod = "2nd Day Air";
        Assert.assertEquals("Verify the shipping msg",expectedShippingMethod,shippingMethod);

        //2.37 Verify Total is “$698.00”
        String total =getTextFromElement(By.cssSelector("tr>td.subtotal>span"));
        String expectedTotal = "$698.00";
        Assert.assertEquals("verify Total amt msg",expectedTotal,total);

        //2.38 Click on “CONFIRM”
        driver.findElement(By.cssSelector("button[onclick='ConfirmOrder.save()']")).click();

        //2.39 Verify the Text “Thank You”
        String thankYouMSG =getTextFromElement(By.cssSelector("div.page-title>h1"));
        String expectedThankYouMsg = "Thank you";
        //Assert.assertEquals("Verify Thank you msg",expectedThankYouMsg,thankYouMSG);

        //2.40 Verify the message “Your order has been successfully processed!”
        String successfulMsg = getTextFromElement(By.cssSelector("div.section.order-completed>div>strong"));
        String expectedSuccessfulMSG = "Your order has been successfully processed!";
        Assert.assertEquals("Verify successfulMsg",expectedSuccessfulMSG,successfulMsg);

        //2.41 Click on “CONTINUE”
        clickOnElement(By.cssSelector("button[onclick='setLocation(\"/\")']"));

        //2.42 Verify the text “Welcome to our store”
        String WelcomeStoreMSG = getTextFromElement(By.cssSelector("div.topic-block-title>h2"));
        String expectedWelcomeStoreMsg = "Welcome to our store";
        Assert.assertEquals("Verify the Welcome Store message",expectedWelcomeStoreMsg,WelcomeStoreMSG);

        //2.43 Click on “Logout” link : no logout option available


       // 2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String actualUrl= driver.getCurrentUrl();
        System.out.println(actualUrl);
        String expectedUrl = "https://demo.nopcommerce.com/";
        Assert.assertEquals("Verifying the Url ",expectedUrl,actualUrl);


        //

       /* //2.18 Click on “REGISTER” tab
        driver.findElement(By.linkText("Register")).click();

        //2.19 Verify the text “Register”
       String actualRegisterPage= driver.findElement(By.xpath("//div[@class='page-title']//h1[1]")).getText();
       System.out.println(actualRegisterPage);
       String expectedRegisterPage = "Register";
       Assert.assertEquals("Verify Register Page",expectedRegisterPage,actualRegisterPage);

       //2.20 Fill the mandatory fields
        registrationPage();

        //2.22 Verify the message “Your registration completed”
        String actualRegistrationCompMsg = driver.findElement(By.cssSelector("div>.result")).getText();
        System.out.println(actualRegistrationCompMsg);
        String expectedRegistrationCompMsg = "Your registration completed";
        Assert.assertEquals("Verify Registration complete",expectedRegistrationCompMsg,actualRegistrationCompMsg);

        //2.23 Click on “CONTINUE” tab
        driver.findElement(By.xpath("//div[@class='page-body']//div[2]//a[1]")).click();

        //2.24 Verify the text “Shopping cart”
        String actualShopCartMSG = getTextFromElement(By.cssSelector( "div.page-title>h1"));
        System.out.println(actualShopCartMSG);
        String expectedShopCartMsg = "Shopping cart";
       // Assert.assertEquals("Verify that ShoppingCart Msg",expectedShopCartMsg,actualShopCartMSG);

        //2.25 click on checkbox “I agree with the terms of service”
        mouseHooverMethod(By.xpath("//a[@class='ico-cart']//span[1]"));
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        clickOnElement(By.xpath("//div[@class='totals']//div[3]//input[1]"));
        clickOnElement(By.id("checkout"));

       //2.26 click on Returning customer
        driver.findElement(By.cssSelector("input[fdprocessedid='iuq2t7']")).sendKeys("ganesha1@gmail.com");
        driver.findElement(By.cssSelector("input[fdprocessedid='q1e4nm']")).sendKeys("test_12345");
        clickOnElement(By.cssSelector(".login-button"));

        // 2.27 Fill the Mandatory fields
        fillUpMandatoryInfo();

        //2.28 Click on “CONTINUE”
        //driver.navigate().refresh();
        driver.findElement(By.cssSelector("div>.register-continue-button")).click(); */




    }

    public void registrationPage(){
        driver.findElement(By.xpath("//div[@class='form-fields']//div[1]//div[1]//span[1]")).click();//Gender
        driver.findElement(By.id("FirstName")).sendKeys("Ganesha");//Firstname
        driver.findElement(By.id("LastName")).sendKeys("Shiv");//Lastname

        //select day of D.O.B
        WebElement day = driver.findElement(By.xpath("//div[@class='inputs date-of-birth']//div[1]//select[1]"));
        Select select= new Select(day);
        select.selectByVisibleText("11");

        //Select Month of D.O.B
        WebElement month = driver.findElement(By.xpath("//div[@class='inputs date-of-birth']//div[1]//select[2]"));
        Select select1= new Select(month);
        select1.selectByVisibleText("May");

        //Select Year of D.O.B
        WebElement year = driver.findElement(By.xpath("//div[@class='inputs date-of-birth']//div[1]//select[3]"));
        Select select2= new Select(year);
        select2.selectByVisibleText("1987");

        //input Email id
        driver.findElement(By.id("Email")).sendKeys("ga@gmail.com");

        //input password
        driver.findElement(By.id("Password")).sendKeys("test_12345");

        //input confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("test_12345");

        //click on register button
        driver.findElement(By.id("register-button")).click();


    }

    public void billingInformation(){
        driver.findElement(By.cssSelector("#BillingNewAddress_FirstName")).sendKeys("Ganesha");
        driver.findElement(By.cssSelector("#BillingNewAddress_LastName")).sendKeys("Shiv");
        driver.findElement(By.cssSelector("#BillingNewAddress_Email")).sendKeys("ganesha@gmail.com");
        WebElement option= driver.findElement(By.cssSelector("#BillingNewAddress_CountryId"));
        Select opt= new Select(option);
        opt.selectByVisibleText("United Kingdom");
        WebElement state= driver.findElement(By.cssSelector("select#BillingNewAddress_StateProvinceId"));
        Select se =new Select(state);
        se.selectByVisibleText("Other");
        driver.findElement(By.cssSelector("input#BillingNewAddress_City")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#BillingNewAddress_Address1")).sendKeys("Test Address1");
        driver.findElement(By.cssSelector("input#BillingNewAddress_ZipPostalCode")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#BillingNewAddress_PhoneNumber")).sendKeys("123456789");
        driver.findElement(By.cssSelector("button[onclick='Billing.save()']")).click();


    }

    public void fillUpMandatoryInfo(){
        //fill up mandatory field
        selectByVisibleTextFromDropDown(By.xpath("//div[@class='edit-address']//div[5]//select[1]"), "United Kingdom");
        driver.findElement(By.xpath("BillingNewAddress_City")).sendKeys("Test City");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Test1");
        driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("Test2");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("Test345");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("12345678");
    }
    public void cardInfo(){
        driver.findElement(By.cssSelector("#CardholderName")).sendKeys("Ganesha Shiv");
        driver.findElement(By.cssSelector("#CardNumber")).sendKeys("1254 2555 55");
        WebElement expireMonth = driver.findElement(By.cssSelector("#ExpireMonth"));
        Select month = new Select(expireMonth);
        month.selectByVisibleText("11");

        WebElement expireYear = driver.findElement(By.cssSelector("#ExpireYear"));
        Select year = new Select(expireYear);
        year.selectByVisibleText("2027");

        driver.findElement(By.cssSelector("#CardCode")).sendKeys("555");
    }

    @After
    public void tearDown(){
      // closeDownBrowser();
    }

}
