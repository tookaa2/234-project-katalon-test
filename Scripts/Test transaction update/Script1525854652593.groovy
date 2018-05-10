import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import org.junit.After as After
import org.openqa.selenium.By as By
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.openBrowser('')

WebUI.navigateToUrl('http://54.71.80.147:8086/')

WebUI.setText(findTestObject('Login_Page/input_username'), 'admin')

WebUI.setText(findTestObject('Login_Page/input_password'), 'admin')

WebUI.click(findTestObject('Login_Page/button_Login'))

WebUI.waitForElementNotPresent(findTestObject('Product page/Logout Button'), 15)

WebUI.click(findTestObject('Navigation bar/a_Total Transaction'))

WebUI.delay(2)

WebDriver driver = DriverFactory.getWebDriver()

WebElement TransactionsTable = driver.findElement(By.xpath('//*[@id="add-row"]/div/table'))

List<WebElement> transactions = TransactionsTable.findElements(By.tagName('tr'))

//for (int i = 1; i <= transactions.size(); i++) {
//	TestObject buttonAddProduct = new TestObject()
//
//	buttonAddProduct.addProperty('xpath', ConditionType.EQUALS, ('/html/body/app-root/app-product-list/div/div[2]/div/div[' +
//		i) + ']/a/div[2]/div[2]/button')
//
//
//
//	println(buttonAddProduct)
//
//
//}
println(transactions.size())

int transactionSizeBefore = transactions.size()

TestObject totalPrice1 = new TestObject()

totalPrice1.addProperty('xpath', ConditionType.EQUALS, '//*[@id="add-row"]/div/div[2]/div/p')

int firstprice = Integer.parseInt(WebUI.getText(totalPrice1).replace(' THB', '').replace(',', '').replace('Total price: ', 
        ''))

println(firstprice)

WebUI.delay(1)

WebUI.click(findTestObject('Product page/Logout Button'))

WebUI.verifyElementPresent(findTestObject('Login_Page/Login Header'), 15)

WebUI.setText(findTestObject('Login_Page/input_username'), username)

WebUI.setText(findTestObject('Login_Page/input_password'), password)

WebUI.click(findTestObject('Login_Page/button_Login'))

WebUI.waitForElementPresent(findTestObject('Product page/Header_Products'), 15)

WebUI.delay(2)

WebElement Products = driver.findElement(By.xpath('/html/body/app-root/app-product-list/div/div[2]/div'))

List<WebElement> farmerCards = Products.findElements(By.className('farmer-card'))

for (int i = 1; i <= farmerCards.size(); i++) {
    TestObject buttonAddProduct = new TestObject()

    buttonAddProduct.addProperty('xpath', ConditionType.EQUALS, ('/html/body/app-root/app-product-list/div/div[2]/div/div[' + 
        i) + ']/a/div[2]/div[2]/button')

    WebUI.click(buttonAddProduct)

    WebUI.delay(1)
}

WebUI.click(findTestObject('Navigation bar/Redirect to cart'))

WebUI.delay(1)

WebUI.setText(findTestObject('Cart Page/item1'), '' + item_1_amount)

WebUI.setText(findTestObject('Cart Page/item2'), '' + item_2_amount)

WebUI.setText(findTestObject('Cart Page/item3'), '' + item_3_amount)

WebUI.setText(findTestObject('Cart Page/item4'), '' + item_4_amount)

WebUI.setText(findTestObject('Cart Page/item5'), '' + item_5_amount)

int priceOfLatestTeansaction = Integer.parseInt(WebUI.getText(findTestObject('Cart Page/Total price  label')).replace(' THB', 
        '').replace(',', '').replace('Total price: ', ''))

println(priceOfLatestTeansaction)

WebUI.delay(2)

WebUI.click(findTestObject('Cart Page/Confirm purchase button'))

WebUI.acceptAlert()

WebUI.waitForElementPresent(findTestObject('Product page/Logout Button'), 15)

WebUI.click(findTestObject('Product page/Logout Button'))

WebUI.waitForElementNotPresent(findTestObject('Product page/Logout Button'), 15)

WebUI.delay(1)

WebUI.setText(findTestObject('Login_Page/input_username'), 'admin')

WebUI.setText(findTestObject('Login_Page/input_password'), 'admin')

WebUI.click(findTestObject('Login_Page/button_Login'))

WebUI.click(findTestObject('Navigation bar/a_Total Transaction'))

String verifyPrice = String.format('%,d', firstprice + priceOfLatestTeansaction)

WebUI.verifyElementText(findTestObject('Transaction page/Total Transaction Price'), ('Total price: ' + verifyPrice) + ' THB')

TransactionsTable = driver.findElement(By.xpath('//*[@id="add-row"]/div/table'))

transactions = TransactionsTable.findElements(By.tagName('tr'))

int transactionSizeAfter = transactions.size()

WebUI.delay(2)

WebUI.verifyEqual(transactionSizeBefore, transactionSizeAfter - 1)

WebUI.delay(1)

