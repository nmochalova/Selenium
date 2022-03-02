package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/***
 * Стили элемента (css) привязываются к:
 * - тегам элемента (getTagName, getAttribute("id"),getAttribute("class"))
 *      Для проверки стилей элементов в автоматизации проверяют не цвет или размер элемента, а то что он имеет
 *      правильный тег, правильный идентификатор и правильный класс.
 *      Например, для сообщения об ошибке достаточно проверить, что оно имеет, например, класс error, а все остальные
 *      хар-ки описаны в стилевом файле и их проверять не надо. То есть в тестах мы проверяем структурную
 *      правильность страницы, то что элементы там правильно размечены и не проверяем что этим элементам
 *      действительно назначаются правильные цвета или размер.
 *  - getCssValue() - получение настоящего значения стилей. Есть договоренность только для color (RGBa), остальные
 *  значения стилей каждый браузер будет возвращать по своему.
 *  - Цвет (color) - нормализация, RGBa
 *  - Комбинированные стили (font, background).  О них также нет договоренности, поэтому каждый браузер будет
 *  возвращать значения на свое усмотрение.
 *
 *  Вывод: цвета, размеры и прочие стили лучше проверять вручную, автоматизации же оставить проверку
 *  правильности разметки классов для элементов.
 */

public class CSSStyle extends BaseTestElements {

    @Test
    public void getCssValueTestDrive()  {
        driver.get("http://localhost/litecart/en/");
        WebElement link = driver.findElement(By.cssSelector(".required"));

        String color = link.getCssValue("color");

        System.out.println(color); //Цвет звездочки: #f00 = (255,0,0,1)  Калькулятор в RGBa: http://hex2rgba.devoth.com/
    }
}
