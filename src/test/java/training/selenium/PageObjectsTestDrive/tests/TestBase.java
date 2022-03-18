package training.selenium.PageObjectsTestDrive.tests;

import org.junit.jupiter.api.BeforeEach;
import training.selenium.PageObjectsTestDrive.app.Application;

public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @BeforeEach
    public void start() {
        //если драйвер уже есть, то выходим
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        //иначе создаем новый
        app = new Application();
        tlApp.set(app);
        //останов драйвера в конце теста
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }
}
