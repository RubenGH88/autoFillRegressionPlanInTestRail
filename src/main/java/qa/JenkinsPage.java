package qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class JenkinsPage {
    private static final String HEADER = "test-view";
    private WebDriver webDriver;

    public JenkinsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void sleep(int timemillis) {
        try {
            Thread.sleep(timemillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void openMainPage(String URL) {
        webDriver.navigate().to(URL);
        webDriver.manage().window().maximize();
    }
    public List<TestRailCase> getPassedCases() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HEADER)));
        sleep(1000);
        List<WebElement> cases = webDriver.findElements(By.xpath("//li[contains(@class, 'collection-item test displayed pass')]//span[@class='test-name']"));
        List<TestRailCase> temp = new ArrayList<>();
        for (WebElement element : cases) {
            String caseID = element.getText().split("_")[0];;
            temp.add(new TestRailCase(caseID));
        }
        return temp;
    }
}
