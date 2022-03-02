package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Locatable;

public class SizeAndLocation extends BaseTestElements{
    @Test
    public void getSizeAndLocationTestDrive() {
        driver.get("http://localhost/litecart/en/");
        WebElement link = driver.findElement(By.cssSelector(".required"));

        //координаты верхнего левого угла элемента в пикселях относительно верхнего левого угла страницы,
        //но не относительно окна браузера! То есть при скроллирование положение эл-та на странице не меняется
        //хотя положение относительно окна браузера меняется.
        Point location = link.getLocation();
        System.out.println("Положение элемента (в пикселях): " + location); //координаты верхнего левого угла элемента на странице
        //возвращает положение эл-та с учетом трансформации (разворот и пр.) для Chrome и FireFox

        Dimension size = link.getSize();
        System.out.println("Размер элемента (в пикселях): " + size); //ширина и высота в пикселях
        //возвращает размер без учета трансформации эл-та (разворот и пр.) в Chrome, однако для FireFox трансформации учитываются.

        // "два в одном" -- новый способ, который пока не все драйверы поддерживают
        Rectangle rect = link.getRect();
        System.out.println("И размер, и положение эл-та: " + rect);
    }

    @Test
    public void SecretLocatable() {
        driver.get("http://localhost/litecart/en/");
        WebElement link = driver.findElement(By.cssSelector("#copyright"));

    //getCoordinates().inViewPort() позволяет получить координаты эл-та не только относительно страницы, но и относительно окна браузера
    //Этот метод автоматически прокручивает страницу и/или кадры, чтобы сделать элемент видимым в окне просмотра перед вычислением его координат.
    //Удобно использовать перед снятием скриншота
    // А также для вычисления координат относительно левого верхнего угла монитора для работы с объектами, с которыми
    //Selenium работать не умеет (флеш-ролики, кэнвес), тогда можно узнать их координаты и выполнить с ними действовия при помощи
    //инструмента, умеющего делать клики по координатам.


        Point location =  ((Locatable) link).getCoordinates().inViewPort();

        System.out.println("Координаты эл-та с прокруткой страницы:  " + location);
    }
}
