package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    public static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private final WebDriver driver;

    //Кнопка заказать сверху
    private final By upperOrder = By.xpath(".//button[@class='Button_Button__ra12g']");

    //Кнопка Заказать снизу
    private final By downOrder = By.xpath(".//button[@class = 'Button_Button__ra12g Button_UltraBig__UU3Lp']");

    //Вопросы о важном
    private final By importantQuestions = By.xpath(".//div[text()='Вопросы о важном']");

    //Принять куки
    private final By acceptCookie = By.xpath(".//button[@id='rcc-confirm-button']");

    //Вопросы
    private final String questions = ".//div[@id='accordion__heading-%d']";

    //Ответы
    private final String answer = ".//div[@id='accordion__panel-%d']";

    public MainPage (WebDriver driver){
        this.driver = driver;
    }

    public MainPage openMainPage(){
        driver.get(MAIN_PAGE_URL);
        return this;
    }

    public By getQuestions(int questionsIndex) {
        return By.xpath(String.format(questions, questionsIndex));
    }

    public By getAnswer(int questionsIndex) {
        return By.xpath(String.format(answer, questionsIndex));
    }

    public By getImportantQuestions() {
        return importantQuestions;
    }

    public void clickCookieesButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookie)).click();
    }

    public void clickUpperOrder() {
        driver.findElement(upperOrder).click();
    }

    public By clickDownOrder() {
        return downOrder;
    }
}
