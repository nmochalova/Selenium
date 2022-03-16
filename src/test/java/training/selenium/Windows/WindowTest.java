package training.selenium.Windows;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.HashSet;
import java.util.Set;

public class WindowTest extends BaseWin{
   @Test
   public void WinTest() {
       //Открытие нового окна в том же браузере
       ((JavascriptExecutor) driver).executeScript("window.open()");
       //Список всех окон, которые открыты в данный момент
       //Для каждого браузера свой драйвер и свой список окон
       driver.getWindowHandles();
       //Текущее окно
       String handle = driver.getWindowHandle();
       //Чтобы переключиться в нужное окно. При открытии нового окна, фокус в него не смещается. Чтобы
       // него переключиться, нужно определить его идентификатор и потом по нему переключиться
       driver.switchTo().window(handle);
       //Закрытие окна. После закрытия окна Selenium продолжает считать активным это закрытое окно, поэтому
       //нужно будет явно переключиться на другое окно
       driver.close();

       /*Для Chrome и Firefox при открытие нового окна будет открываться новая вкладка, будут возвращаться
       идентификаторы вкладок и будет переключение между вкладками. Т.е. новые окна выглядит как вкладки
       Для IE будут открываться именно новые окна.
       * */
   }

   //Туда и обратно. Переключение в новое открытое окно и возвращение обратно.
   @Test
    public void thereAndBack() {
       //Запоминаем идентификатор текущего окна
       String originalWindow = driver.getWindowHandle();
        //запоминаем идентификаторы уже открытых окон
       Set<String> exitingWindows = driver.getWindowHandles();
        // кликаем кнопку, которая открывает окно
       driver.findElement(By.id("button")).click();

        //ждем появления нового окна, с новым идентификатором
       String newWindow = wait.until(anyWindowOtherThan(exitingWindows));
       //или ожидание появления окон в заданном количестве
       //wait.until(numberOfWindowsToBe(2));

        //переключаемся на новое окно
       driver.switchTo().window(newWindow);
        //закрываем его
       driver.close();
        //и возвращаемся в исходное окно
       driver.switchTo().window(originalWindow);
   }

    /*  Параметр - список старых открытых окон. При помощи явных ожиданий в цикле периодически
        мы получаем список новых окон. Удаляем из списка ранее открытые окна и смотрим что осталось
        если несколько выбираем первое попавшееся и возвращаем его идентификатор */
    //     Ожидание появления нового окна
    public ExpectedCondition<String> anyWindowOtherThan(Set<String>oldWindows){
        return new ExpectedCondition<String>(){
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return  handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }




    @Test
    public void test() {
        Set<String> handles = new HashSet<String>();
        handles.add("test1");
        handles.add("test2");
        if(handles.size() > 0) {
            System.out.println(handles.iterator().next());
        }
    }

    //Размер и положение окна
    @Test
    public void sizeTest() {
        //Получить положение окна (левого верхнего угла)
        driver.manage().window().getPosition();
        //Установить положение окна (левого верхнего угла)
        int x = 100;
        int y = 100;
        driver.manage().window().setPosition(new Point(x,y));
        //Получить размеры окна (именно окна, а не видимой части страницы без учета тулбара и скроллбара)
        driver.manage().window().getSize();
        //Установить размеры окна
        int w = 100;
        int h = 100;
        driver.manage().window().setSize(new Dimension(w,h));
        //Изменение размера окна до максимально возможного (проверка адаптивной верстки)
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        //Пример
        WebDriver.Window window = driver.manage().window();
        window.maximize();
        window.setSize(new Dimension(800, 600));
    }

    //close() - закрывает текущее окно, quit() - закрывает все окна, останавливает драйвер и удаляет профиль
    //close() не останавливает вспомогательный файл драйвера,
    //чтобы закрыть все полностью и освободить все ресурсы в конце нужно вызвать quit()
    @Test
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }
}
