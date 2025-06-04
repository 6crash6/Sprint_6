import PageObjects.MainOrderPage;
import PageObjects.MainPage;
import PageObjects.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPageParametrizeTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private OrderPage orderPage;
    private MainOrderPage mainOrderPage;


    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainOrderPage = new MainOrderPage(driver);
        mainPage.clickCookieesButton();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testOrder(
        String button,
        String name,
        String surname,
        String adrees,
        String metro,
        String phone,
        int date,
        String days,
        String comment
        ){
        if ("HEADER".equals(button)){
            mainPage.clickUpperOrder();
        } else if ("MIDDLE".equals(button)) {
            WebElement element = driver.findElement(mainPage.clickDownOrder());
            // Выводим в середину экрана
            ((JavascriptExecutor)driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    element
            );
            element.click();
        }
        //Жмём Заказать
        mainPage.clickUpperOrder();
        //Переход на нужную страницу
        String title = driver.getTitle();
        assertEquals("undefined", title);

        //Заполняем форму
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAdress(adrees);
        orderPage.enterMetro(metro);
        orderPage.enterPhone(phone);

        //Переход к другой странице
        orderPage.clickNxt();
        String Order = driver.getTitle();
        assertEquals("undefined", Order);

        //Заказать
        mainOrderPage.selectDate(date);
        mainOrderPage.selectOption(days);
        mainOrderPage.selectColour();
        mainOrderPage.comment(comment);
        mainOrderPage.order();

        //Да
        mainOrderPage.yes();
        assertTrue(mainOrderPage.success());
    }

    static Stream<Arguments> parameters(){
        return Stream.of(
                Arguments.of("HEADER","Иванов","Иван","Питер",
                        "Шаболовская","+15672348900",1,"сутки","Как можно скорее"),
                Arguments.of("MIDDLE","Иванов","Иван","Иваныч,Питер",
                        "Лубянка","+01234567890",2,"трое суток","Можно не торопиться")
        );
    }
}


