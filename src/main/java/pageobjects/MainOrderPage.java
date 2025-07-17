package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainOrderPage {
    private final WebDriver driver;

    //Когда привезти самокат
    private final By delivery = By.xpath(".//input[contains(@placeholder,'Когда привезти')]");

    //Срок аренды
    private final By rentPeriod = By.xpath(".//div[@class='Dropdown-control']");

    //Выпадашка
    private final By dropDown = By.xpath(".//div[@class='Dropdown-menu']/div[@class='Dropdown-option']");

    //Цвет самоката
    private final By black = By.xpath(".//input[@id='black']");
    private final By grey = By.xpath(".//input[@id='grey']");

    //Комментарий курьеру
    private final By comment = By.xpath(".//input[contains(@placeholder,'Комментарий')]");

    //Подтверждение заказа
    private final By yes = By.xpath(".//button[contains(@class,'Button_Middle') and text()='Да']");
    private final By no = By.xpath(".//button[contains(@class,'Button_Middle') and text()='Нет']");

    //Заказать
    private final By order = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");

    //Заказ оформлен
    private final By success = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public MainOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //Когда привезти?
    public void deliveryDate(String date) {
        WebElement delivDate = driver.findElement(delivery);
        delivDate.click();
        delivDate.clear();
        delivDate.sendKeys(date);
        driver.findElement(By.xpath(".//div[contains(@class, 'datepicker__day--selected')]")).click();
    }
public void selectDate (int addDays){
    LocalDate delDate = LocalDate.now().plusDays(addDays);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formatDate = delDate.format(formatter);
    deliveryDate(formatDate);
}
    //Срок аренды Заполнение
    public void enterRentPeriod(){
        WebElement rentPer = driver.findElement(rentPeriod);
        rentPer.click();
    }

    public void selectOption(String text){
        enterRentPeriod();

        List<WebElement> options = driver.findElements(dropDown);
        for (WebElement option : options){
            if (option.getText().equalsIgnoreCase(text)){
                option.click();
                return;
            }
        }
    }
    // Выбрать цвет
    public void selectColour(){
        WebElement colour = driver.findElement(black);
        colour.click();
    }
    //Комментарии
    public void comment (String commentText){
        WebElement coment = driver.findElement(comment);
        coment.click();
        coment.clear();
        coment.sendKeys(commentText);
    }
    //Заказать
    public void order(){
        driver.findElement(order).click();
    }
    //Да
    public void yes(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(yes)).click();
    }

    //Заказ оформлен
    public boolean success() {
        String text = driver.findElement(success).getText();
        return text.contains("Заказ оформлен");
    }
}
