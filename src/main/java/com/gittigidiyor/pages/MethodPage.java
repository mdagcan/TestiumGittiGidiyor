package com.gittigidiyor.pages;

import com.gittigidiyor.util.Log4j;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MethodPage extends BasePage{
    public MethodPage(WebDriver driver) {
        super(driver);
    }
    public MethodPage addComp2Basket() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        searchArea.sendKeys("bilgisayar"); // Arama bölgesine 'bilgisayar' yazılır
        Log4j.info("Arama bölgesine 'bilgisayar' yazılır");
        submitButton.click(); // Arama yapmak için 'ARA' butonuna basılır


        String ctrlEnd = Keys.chord(Keys.CONTROL, Keys.END);
        driver.findElement(By.tagName("body")).sendKeys(ctrlEnd); // Sayfa sonuna gitmek için CTRL+END tuşlarına basılır
        wait.until(ExpectedConditions.elementToBeClickable(secondPage)); // Explicit wait ile tıklanabilir olana kadar beklenir
        secondPage.click(); // ikinci sayfaya geçmek için tıklanır
        Log4j.info("ikinci sayfaya gidilir");
        Assert.assertTrue(driver.getCurrentUrl().contains("sf=2")); // İkinci sayfaya gidildiğinin kontrolu için sayfa urlsinde 'sf=2' varmı bakılır


        tumUrunler.get(random.nextInt(tumUrunler.size())).click(); // Sayfadaki bütün ürünlerden biri random seçilir ve tıklanır
        Log4j.info("Sayfadaki ürünlerden biri random seçilir");
        String urunFiyatText = urunFiyat.getText(); // Ürünün fiyatı String içine alınır
        String urunBilgiText = urunBilgi.getText(); // Ürünün bilgisi String içine alınır


        //System.out.println(urunFiyatText); Thread.sleep(3000);
        //System.out.println(urunBilgiText); Thread.sleep(3000);


        Log4j.info("ürün fiyat bilgisi ve özellikleri txt içine yazdırılır");
        try {
            FileWriter fileWriter = new FileWriter("data.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(urunFiyatText); // Ürün fiyat bilgisi txt dosyası içine yazdırılır
            printWriter.println("==============================");
            printWriter.println(urunBilgiText); // Ürün bilgisi txt dosyası içine yazdırılır

            printWriter.close();

        } catch (IOException e) {
            System.out.println("Ürün Bilgisi ve Fiyatı Kaydedilirken Hata Oluştu..!!!");
        }


        driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN); // Sepete ekle sekmesi için PAGE_DOWN tuşu yardımı ile aşağı inilir
        wait.until(ExpectedConditions.elementToBeClickable(sepeteEkle)); // Explicit wait ile bekleme verilir
        sepeteEkle.click(); // Ürün sepete eklenir
        Log4j.info("ürün sepete eklenir");
        wait.until(ExpectedConditions.elementToBeClickable(sepeteGit));
        Thread.sleep(1000); // ürünün sepete eklenmesi için gerekli bekleme


        sepeteGit.click(); // Sepete gidilir
        String urunSepetFiyatText = sepetteFiyat.getText(); // Ürünün sepet fiyatı değişken içine alınır
        System.out.println(urunSepetFiyatText); Thread.sleep(1000);
        Assert.assertEquals(urunFiyatText, urunSepetFiyatText); // Ürünün fiyatı ile sepetteki fiyat aynı mı kontrol edilir
        Log4j.info("ürünün fiyatı ve sepet fiyatı karşılaştırılır");


        Select options = new Select(urunAdet); // Select ile sepetteki ürün sayısı 2 yapılır
        options.selectByIndex(1); // 2 seçeneği seçilir
        Log4j.info("sepetteki ürün adedi 2 yapılır ve doğruluğu kontrol edilir");


        String urunAdetKontText = urunAdetKont.getText(); // sepetteki urun sayısı alınır
        Assert.assertTrue(urunAdetKontText.contains("2 Adet")); // Sepette 2 adet ürün olduğu kontrol edilir


        urunSilme.click(); //Sepetteki ürün silinir
        Thread.sleep(2000); // Sepetin boş olduğunu görmek için gerekli bekleme
        Log4j.info("sepetteki ürün boşaltılır");
        String sepetBosText = sepetBos.getText(); // Sepet boşaltıldıktan sonra sepet boş uyarısı alınır
        Assert.assertTrue(sepetBosText.contains("Sepetinizde ürün bulunmamaktadır")); // Sepetin boş olduğu doğrulanır


        return new MethodPage(driver);
    }
}
