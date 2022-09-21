import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.util.List;

public class saInvoice {
    ChromeDriver chromeDriver;
    private static final String link = "https://app133.easybooks.vn/#/login";
    private static final String user = "hotro133_hien@gmail.com";
    private static final String pass = "123456";
    private static final String date1 = "15/09/2022";
    @Test
    public void run(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.get(link);
        login();
        sleep(5000);
        WebElement banHang = chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/nav/eb-sidebar/ul/li[3]/a"));
        banHang.click();
        sleep(1000);
        WebElement chungTuBanHang = chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/nav/eb-sidebar/ul/li[3]/div/ul/li[3]/a"));
        chungTuBanHang.click();
        sleep(5000);
        List<WebElement> buttons  = chromeDriver.findElements(By.className("button-navigation"));
        buttons.get(0).click();
        findButtonChildByName("accountingObjectCode", "drop-down-button");
        sleep(5000);
        List<WebElement> cbbAccountingObject = chromeDriver.findElements(By.className("data-list"));
        cbbAccountingObject.get(1).click();
//        WebElement isBill = chromeDriver.findElement(By.id("/html/body/eb-main/div/div/div/div/div/eb-sa-invoice-update/div/div[1]/div/div/div[2]/div/div[2]/div/div[4]/div"));
//        isBill.click();
        List<WebElement> isBill = chromeDriver.findElements(By.className("custom-checkbox"));
//       isBill.get(1).click();
        setCheckboxStatus(isBill.get(1), true);
        setCheckboxStatus(isBill.get(0), true);
        chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/div/div/eb-sa-invoice-update/div/div[1]/div/div/div[2]/div/div[1]/div/div/ul/li[3]/a")).click();
        findButtonChildByName("invoiceTemplate", "drop-down-button");
        List<WebElement> cbbTemplate = chromeDriver.findElements(By.className("data-list"));
        cbbTemplate.get(1).click();
//        WebElement date = chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/div/div/eb-sa-invoice-update/div/div[1]/div/div/div[3]/div[2]/form/div/div/div[4]/div/eb-date-picker/div/input[1]"));
        WebElement date =  chromeDriver.findElement(By.cssSelector("input[tabindex='41']"));
        inputText(date, date1);
        chromeDriver.findElement(By.cssSelector("input#insertRow")).click();
        chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/div/div/eb-sa-invoice-update/div/div[1]/div/div/ngb-tabset/div/div/div/div/div/virtual-scroller/div[2]/table/tbody/tr[1]/td[1]/combo-box/div/span/button[2]/i")).click();
        sleep(2000);
        List<WebElement> cbbMaterialGoods = chromeDriver.findElements(By.className("data-list"));
        cbbMaterialGoods.get(0).click();
        WebElement repository0 = chromeDriver.findElement(By.id("repository0"));
        for (WebElement item: cbbMaterialGoods
             ) {
            inputNull(repository0, "repositoryCode", 0, i);
        }
//        String inputValue = repository0.getAttribute("title");
//        if (null != inputValue){
//            findButtonChildByName("repositoryCode", "drop-down-button", 0);
//            sleep(1000);
//            List<WebElement> cbbRepository = chromeDriver.findElements(By.className("data-list"));
//            cbbRepository.get(1).click();
//        }
//        inputNull(repository0,cbbMaterialGoods,0 );

//        WebElement them = chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/div/div/eb-sa-invoice/div/div/div[1]/div[2]/button[1]"));
//        them.click();
//      chromeDriver.quit();
    }

    private void findButtonChildByName(String cha, String con) { //tìm cbb tìm lớp cha rồi đến lớp con
        List<WebElement> button = chromeDriver.findElements(By.cssSelector("combo-box[displaymember='" + cha + "']"));
        if(button.size() > 0){
            List<WebElement> button2 = button.get(0).findElements(By.className(con));
            if(button2.size() > 0){
                button2.get(0).click();
                sleep(1000);
            }
        }
    }
    private void setCheckboxStatus (WebElement element, boolean isChecked){ //check trạng thái checkbox
        boolean isActualChecked = element.isSelected();
        if (isChecked != isActualChecked){
            element.click();
        }
    }
    private void inputText(WebElement element, String input){
        element.clear();
        element.sendKeys(input);
    }
   private void inputNull(WebElement input, String  cbb, int i){
       String inputValue  = input.getAttribute("title");
       if (inputValue != null){
           findButtonChildByName(cbb, "drop-down-button");
           List<WebElement> cbbRepository = chromeDriver.findElements(By.className("data-list"));
           cbbRepository.get(i).click();
       }

   }

    public void login(){

        WebElement close = chromeDriver.findElement(By.className("close-button"));
        close.click();
        WebElement userName = chromeDriver.findElement(By.id("username"));
        userName.sendKeys(user);
        WebElement password = chromeDriver.findElement(By.id("password"));
        password.sendKeys(pass);
        WebElement login = chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/div/div/eb-error/div[1]/div[2]/form/div[3]/form/div[7]/button"));
        login.click();
        sleep(1000);
        WebElement login1 = chromeDriver.findElement(By.xpath("/html/body/eb-main/div/div/div/div/div/eb-error/div[1]/div[2]/form/div[3]/form/div[3]/button"));
        login1.click();
    }
    private void sleep (int timeSleep){
        try {
            Thread.sleep(timeSleep);
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }
    }
}
