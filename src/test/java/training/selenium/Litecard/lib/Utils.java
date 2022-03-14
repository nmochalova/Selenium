package training.selenium.Litecard.lib;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {
    public static int getIntByTextOfWebElement(WebElement element) {
        String elementStr = element.getText();
        return Integer.parseInt(elementStr);
    }

    public static String relativePath(String path) {
        String localDir = System.getProperty("user.dir");
        File file = new File(localDir + path);
        String strPath = file.getPath();
        return strPath;
    }

    public static int randomNumber(int min, int max) {
        // Инициализируем генератор
        Random rnd = new Random(System.currentTimeMillis());
        // Получаем случайное число в диапазоне от min до max (включительно)
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }

    //Возвращает текущую дату в формате dd.mm.yyyy
    public static String currentDate() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String currentDate = dateFormat.format(date);
        return currentDate;
    }
}
