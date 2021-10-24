package com.gittigidiyor.tests;

import org.junit.Assert;
import com.gittigidiyor.pages.HomePage;
import com.gittigidiyor.util.Browser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase extends Browser {
    WebDriver driver;
    HomePage homePage;

    @Before
    public void setUp() throws IOException {
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream("TestConfig.properties"));
        driver = Browser.createDriver(testConfig.getProperty("browser"));
        driver.get(testConfig.getProperty("baseUrl"));
        homePage = new HomePage(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
