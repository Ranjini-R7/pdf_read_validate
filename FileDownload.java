import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class FileDownloadExample {
    public static void main(String[] args) {
        // Set up the Chrome driver location
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Define the download folder path
        String downloadFilepath = "path_to_download_directory";

        // Create a map to set preferences for Chrome
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadFilepath); // Set download path
        prefs.put("download.prompt_for_download", false); // Disable the download prompt
        prefs.put("safebrowsing.enabled", true); // Disable the Safe Browsing prompt

        // Create ChromeOptions instance
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Navigate to a page with a download link
        driver.get("https://example.com/file-download-page");

        // Perform actions to click the download link
        driver.findElement(By.linkText("Download file")).click();

        // Add logic to handle wait or confirmation of the download completion
        // (Optional: Implement additional logic to ensure file has been downloaded)

        // Quit driver
        driver.quit();
    }
}
