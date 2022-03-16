package training.selenium.Windows;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FrameTest extends BaseWin{
    //Фрейм - это вложенное окно в окне. Фрейм изолирует содержимое от основного окна и
    //имеет свою модель DOM
    @Test
    public void test() {
        WebElement element = driver.findElement(By.cssSelector("#index iframe"));
        //Переключиться во фрейм (можно передавать ID, name или element(предпочтительно)
        driver.switchTo().frame(element);
        //Вернуться из frame обратно
        driver.switchTo().defaultContent(); //на самый верх страницы
        driver.switchTo().parentFrame();    //на 1 уровень вверх (для случая большой вложенности iframe)
    }

}
