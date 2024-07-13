package selenium.autotests;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    @Test
    public void positiveTest() {
        // Arrange
        // Создаем web-драйвер
        WebDriver driver = new ChromeDriver();
        try     {
            // Задаем данные для теста
            String userName = "Иванов Иван";
            String userEmail = "ivanov@email.com";
            String currentAddress = "г. Курск, пр-т Вячеслава Клыкова, д. 7, кв. 5";
            String permanentAddress = "г. Москва, ул. Ленина, д. 14, кв. 10";

            // Act

            // Переходим на сайт https://demoqa.com/
            driver.get("https://demoqa.com/");
            // Находим по xpath кнопку Elements и кликаем на нее
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]")).click();
            // Находим вкладку TextBox и кликаем на нее
            driver.findElement(By.id("item-0")).click();

            // Находим поля для ввода по id и заполняем их тестовыми данными
            driver.findElement(By.id("userName")).sendKeys(userName);
            driver.findElement(By.id("userEmail")).sendKeys(userEmail);
            driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
            driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress);
            // Находим кнопку Submit и кликаем на нее (получилось кликнуть на нее только через js
            WebElement submitButton = driver.findElement(By.id("submit"));
            try {
                submitButton.click();
            } catch (Exception e) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", submitButton);
            }


            // Находим по классу все выведенные данные и записываем в соответствующие переменные,
            var results = driver.findElements(By.className("mb-1"));

            var nameActual = results.get(0).getText().split(":")[1];
            var emailActual = results.get(1).getText().split(":")[1];
            var currentActual  = results.get(2).getText().split(":")[1];
            var permanentActual  = results.get(3).getText().split(":")[1];


            // Assert
            // Сравнение полученных и введенных данных
            assertEquals(userName, nameActual);
            assertEquals(userEmail, emailActual);
            assertEquals(currentAddress, currentActual);
            assertEquals(permanentAddress, permanentActual);
        } catch (Exception e) {
            fail();
        }

        driver.quit();
    }
}