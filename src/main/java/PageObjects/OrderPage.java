package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    //Имя
    private By name = By.xpath(".//input[@placeholder='* Имя']");

    //Фамилия
    private By surname = By.xpath(".//input[@placeholder='* Фамилия']");

    //Адресс
    private By adress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Метро
    private By metro = By.xpath(".//input[@placeholder='* Станция метро']");

    //Телефон
    private By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Далее
    private By next = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String userName){
        WebElement field = driver.findElement(name);
        field.click();
        field.clear();
        field.sendKeys(userName);
    }

    public void enterSurname (String userSurname){
        WebElement field = driver.findElement(surname);
        field.click();
        field.clear();
        field.sendKeys(userSurname);
    }

    public void enterAdress(String userAdress){
        WebElement field = driver.findElement(adress);
        field.click();
        field.clear();
        field.sendKeys(userAdress);
    }

    public void enterMetro(String userMetro){
        WebElement field = driver.findElement(metro);
        field.click();
        field.clear();
        field.sendKeys(userMetro);
        driver.findElement(By.xpath("//li[contains(@class, 'select-search__row')][1]")).click();
    }

    public void enterPhone(String userPhone){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement field =wait.until(ExpectedConditions.visibilityOfElementLocated(phone));
        field.clear();
        field.sendKeys(userPhone);
    }

    public void clickNxt(){
        driver.findElement(next).click();
    }
}

