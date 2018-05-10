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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By

WebUI.delay(1)

WebUI.setText(findTestObject('Login_Page/input_username'), username)

WebUI.setText(findTestObject('Login_Page/input_password'), password)

WebUI.click(findTestObject('Login_Page/button_Login'))

WebUI.waitForElementPresent(findTestObject('Product page/Header_Products'), 0)

WebUI.delay(2)

WebDriver driver = DriverFactory.getWebDriver()

WebElement Products = driver.findElement(By.xpath('/html/body/app-root/app-product-list/div/div[2]/div'))

List<WebElement> farmerCards = Products.findElements(By.className('farmer-card'))

for (int i = 1; i <= farmerCards.size(); i++) {
    TestObject buttonAddProduct = new TestObject()

    buttonAddProduct.addProperty('xpath', ConditionType.EQUALS, ('/html/body/app-root/app-product-list/div/div[2]/div/div[' + 
        i) + ']/a/div[2]/div[2]/button')

    WebUI.click(buttonAddProduct)

    WebUI.click(findTestObject('Navigation bar/Redirect to cart'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Navigation bar/Redirect to product'))
}

WebUI.delay(2)

WebUI.click(findTestObject('Product page/Logout Button'))

WebUI.verifyElementPresent(findTestObject('Login_Page/Login Header'), 15)

