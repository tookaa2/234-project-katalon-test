import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.lang.reflect.Array as Array
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.openBrowser('')

WebUI.navigateToUrl('http://54.71.80.147:8086/')

WebUI.setText(findTestObject('Login_Page/input_username'), 'user')

WebUI.setText(findTestObject('Login_Page/input_password'), 'user')

WebUI.click(findTestObject('Login_Page/button_Login'))

WebUI.delay(2)

WebUI.waitForElementPresent(findTestObject('Product page/Header_Products'), 15)

WebUI.delay(5)

WebDriver driver = DriverFactory.getWebDriver()

WebElement Products = driver.findElement(By.xpath('/html/body/app-root/app-product-list/div/div[2]/div'))

List<WebElement> farmerCards = Products.findElements(By.className('farmer-card'))

for (int i = 1; i <= farmerCards.size(); i++) {
    TestObject buttonAddProduct = new TestObject()

    buttonAddProduct.addProperty('xpath', ConditionType.EQUALS, ('/html/body/app-root/app-product-list/div/div[2]/div/div[' + 
        i) + ']/a/div[2]/div[2]/button')

    WebUI.verifyElementText(buttonAddProduct, 'add to cart')

    WebUI.click(buttonAddProduct)

    WebUI.verifyElementText(buttonAddProduct, 'already added')

    println(buttonAddProduct)

    WebUI.verifyElementText(findTestObject('Product page/cart amount label'), '' + i //    WebUI.delay(1)
        )
}

WebUI.delay(2)

WebUI.click(findTestObject('Navigation bar/Redirect to cart'))

WebUI.delay(2)

WebElement items = driver.findElement(By.xpath('/html/body/app-root/app-cart-list/div/div/table'))

List<WebElement> itemInTable = items.findElements(By.tagName('tr'))

int[] prices = new int[]

for (int i = 1; i < itemInTable.size; i++) {
    TestObject product = new TestObject()

    product.addProperty('xpath', ConditionType.EQUALS, ('//*[@id="add-row"]/div/table/tbody/tr[' + i) + ']/td[2]')

    //	WebUI.verifyElementText(product, '20,000 THB')
    (prices[i]) = Integer.parseInt(WebUI.getText(product).replace(' THB', '').replace(',', ''))

    println((('No:' + i) + 'price') + (prices[i]))
}

WebUI.setText(findTestObject('Cart Page/item1'), '' + item_1_amount)

WebUI.setText(findTestObject('Cart Page/item2'), '' + item_2_amount)

WebUI.setText(findTestObject('Cart Page/item3'), '' + item_3_amount)

WebUI.setText(findTestObject('Cart Page/item4'), '' + item_4_amount)

WebUI.setText(findTestObject('Cart Page/item5'), '' + item_5_amount)

int finalPrice = finalPrice + (Integer.parseInt(item_1_amount) * (prices[1]))

finalPrice = (finalPrice + (Integer.parseInt(item_2_amount) * (prices[2])))

finalPrice = (finalPrice + (Integer.parseInt(item_3_amount) * (prices[3])))

finalPrice = (finalPrice + (Integer.parseInt(item_4_amount) * (prices[4])))

finalPrice = (finalPrice + (Integer.parseInt(item_5_amount) * (prices[5])))

String priceInString = String.format('%,d', finalPrice)

println(priceInString)

WebUI.delay(5)

WebUI.verifyElementText(findTestObject('Cart Page/Total price  label'), ('Total price: ' + priceInString) + ' THB')

WebUI.delay(5)

