package com.gittigidiyor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class BasePage {
    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 30);
    Random random = new Random();
    public BasePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    //==================PAGE FACTORY==================//
    @FindBy(xpath = "//input[@placeholder='Keşfetmeye Bak']")
    WebElement searchArea;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(css = "a[title='2. sayfa'] span")
    WebElement secondPage;

    @FindBy(xpath = "(//div[@id='sp-price-lowPrice'])[1]")
    WebElement urunFiyat;

    @FindBy(xpath = "(//tbody[1])[1]")
    WebElement urunBilgi;

    @FindBy(css = "#add-to-basket")
    WebElement sepeteEkle;

    @FindBy(xpath = "//div[@class='icon-sepet-line-wrapper']//img")
    WebElement sepeteGit;

    @FindBy(xpath = "//p[@class='new-price']")
    WebElement sepetteFiyat;

    @FindBy(xpath = "//select[@class='amount']")
    WebElement urunAdet;

    @FindBy(xpath = "//div[contains(text(),'Sepet Tutarı (2 Adet)')]")
    WebElement urunAdetKont;

    @FindBy(xpath = "(//i[@class='gg-icon gg-icon-bin-medium'])[1]")
    WebElement urunSilme;

    @FindBy(xpath = "//h2[contains(text(),'Sepetinizde ürün bulunmamaktadır.')]")
    WebElement sepetBos;

    @FindBy(xpath = "//ul[@class='sc-1yvp483-0 sc-1favwb2-1 dTGwmm brViIn']//li")
    List<WebElement> tumUrunler;

}
