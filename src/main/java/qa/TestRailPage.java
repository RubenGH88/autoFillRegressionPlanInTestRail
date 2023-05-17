package qa;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestRailPage {

    private static final String HEADER = "content-header";
    public static final String TESTRAIL_LINK = "https://axn-testrail01-p.axadmin.net/index.php?/runs/overview/128";

    private WebDriver webDriver;

    public TestRailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void sleep(int timemillis) {
        try {
            Thread.sleep(timemillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void openMainPage() {
        webDriver.navigate().to(TESTRAIL_LINK);
        webDriver.manage().window().maximize();
    }

    public List<TestRailCase> getPassedCases() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HEADER)));
        sleep(1000);
        List<WebElement> cases = webDriver.findElements(By.xpath("//td[@class='js-status']//a[text()='Passed']/../.."));
        List<TestRailCase> temp = new ArrayList<>();
        for (WebElement element : cases) {
            String caseID = element.findElement(By.xpath("./td[@class='id']/a")).getText();
            temp.add(new TestRailCase(caseID));
        }
        return temp;
    }


    public void setPassedCases(List<TestRailCase> cases) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HEADER)));
        sleep(1000);
        for (TestRailCase element : cases) {
           String caseId = element.getCaseID();
            webDriver.findElement(By.xpath(
                    "//td[@class='id'][a='" + caseId + "']/following-sibling::td[@class='js-status']/a[text()='Untested']"))
                    .click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("statusDropdown")));
            webDriver.findElement(By.xpath(
                            "//div[@id='statusDropdown']/ul/li/a[text()='Passed']"))
                    .click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//button[@id='addResultSubmit']")));

            webDriver.findElement(By.xpath(
                            "//button[@id='addResultSubmit']"))
                    .click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                    "//button[@id='addResultSubmit']")));
        }
    }

}
