package training.selenium.Windows;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class AlertClass extends BaseWin{
    @Test
    public void AlertTest() {
        //Класс для работы с всплывающими окнами (alert). Создание объекта класса
        Alert alert = driver.switchTo().alert();
        //Явные ожидания диалогового окна, в случае, если оно всплывает не мгновенно и его нужно подождать
        Alert alertWait = wait.until(alertIsPresent()); //запрос на наличие окна каждые полсекунды в течении таймаута
        //Получение текста диалогового окна
        String textAlert = alert.getText();
        //Ввод текста в диалоговое окно (для типа прумпт)
        alert.sendKeys("test");
        //закрытие диалогового окна (кнопка Ок)
        alert.accept();
        //закрытие диалогового окна кнопкой Отмена, если есть. Если нет Отмены, то dismiss() соотв.Х или кнопке Esc.
        alert.dismiss();

        //Итого, если ожидается появление окна:
        Alert alertX = wait.until(alertIsPresent());
        alertX.accept();

        //Если alert появляется неожиданно, то можно настроить разблокировку.
        //Настройка unexpectedAlertBehaviour может принимать значения: accept, dismiss, ignore
        //Если в момент выполнение команды появляется диалоговое окно, то selenium действует
        //в соответствии с настройкой (accept, dismiss), потом выбрасывает исключение UnhandledAlertException и останавливает сценарий.
        //Если нужно чтобы команда все же выполнилась нужно реализовать механизм повторения команд.
        //ignore - запрещает разблокировать и alert не закрывается. Тогда нужно самим перехватить исключение UnhandledAlertException
        //и в перехватчике самим закрыть alert. Это удобно, чтобы можно было сделать, например, скриншот неожиданного диалога.
        ChromeOptions options = new ChromeOptions();
        options.setCapability("unexpectedAlertBehaviour","dismiss");
        driver = new ChromeDriver(options);
    }
}
