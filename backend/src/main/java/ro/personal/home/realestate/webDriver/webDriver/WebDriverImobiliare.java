package ro.personal.home.realestate.webDriver.webDriver;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Component
public class WebDriverImobiliare {
    /**
     * Aici am pathul local (de pe PC) catre chromedriver.exe
     */
    private static final String CHROMEDRIVER_EXECUTABLE = "C:\\Users\\vancer\\Desktop\\Libraryes & Sources\\chromedriver_win32\\chromedriver.exe";
    private static WebDriver webDriver;

    private WebDriverImobiliare() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_EXECUTABLE);
            webDriver = new ChromeDriver();
            webDriver.manage().window().setPosition(new Point(-2000, 0));
        }
        return webDriver;
    }

    public static void closeWebDriver() {
        webDriver.close();
        webDriver = null;
    }

    public static void refreshPage() {
        webDriver.navigate().refresh();
    }
}



