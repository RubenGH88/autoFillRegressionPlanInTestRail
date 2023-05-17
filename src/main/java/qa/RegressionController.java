package qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import java.util.List;
import java.util.logging.Logger;

public class RegressionController {

    // ATTRIBUTES    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private final Logger LOG = Logger.getLogger("qa.ExcelController");
    private String regressionTestRunUrl;
    private String regressionTestPlanUrl;

    private WebDriver webDriver;
    private List<TestRailCase> passedTestCases;
    private TestRailPage testRailPage;
    private JenkinsPage jenkinsPage;

    // PRINCIPAL FUNCTIONS   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    public void sigIn(String browser) {
        setWebDriverBrowser(browser);
        this.testRailPage = new TestRailPage(webDriver);
        testRailPage.openMainPage();
    }

    public void JenkinsSignIn(String browser, String URL) {
        setWebDriverBrowser(browser);
        this.jenkinsPage = new JenkinsPage(webDriver);
        jenkinsPage.openMainPage(URL);
    }

    public void updateFromTestRail(String browser, String regressionTestRunUrl, String regressionTestPlanUrl) {
        sigIn(browser);
        webDriver.navigate().to(regressionTestRunUrl);
        List<TestRailCase> cases = testRailPage.getPassedCases();
        webDriver.navigate().to(regressionTestPlanUrl);
        testRailPage.setPassedCases(cases);
        webDriver.close();
    }

    public void updateFromJenkins(String browser, String regressionTestRunUrl, String regressionTestPlanUrl) {
        JenkinsSignIn(browser, regressionTestRunUrl);
        List<TestRailCase> cases = jenkinsPage.getPassedCases();
        webDriver.close();
        sigIn(browser);
        webDriver.navigate().to(regressionTestPlanUrl);
        testRailPage.setPassedCases(cases);
        webDriver.close();
    }


    // BROWSER   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private void setWebDriverBrowser(String browser) {
        switch (browser) {
            case "Firefox":
                ProfilesIni profile = new ProfilesIni();
                FirefoxProfile myprofile = profile.getProfile("profileToolsQA");
                FirefoxOptions options = new FirefoxOptions().setProfile(myprofile);
                webDriver = new FirefoxDriver(options);
                break;
            case "Edge":
                webDriver = new EdgeDriver();
                break;
            default:
                ChromeOptions co = new ChromeOptions().addArguments("--remote-allow-origins=*");
                co.setBrowserVersion("91");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(co);
        }
    }

}
