package ru.stqa.training.selenium.ParallelTestsFactory;

import org.junit.jupiter.api.Test;

public class FirstTestFactory extends WebDriverFactorySamples{
    @Test
    public void test1() {
        doSomething();
    }

    @Test
    public void test2() {
        doSomething();
    }

    @Test
    public void test3() {
        doSomething();
    }

}
