import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class DownloadAndVerifyPDF {
    public static void main(String[] args) {
        // Define the path where the PDF should be downloaded
        String downloadFilePath = "C:\\path\\to\\download\\directory"; // Replace with your desired download directory

        // Set up EdgeOptions to configure browser preferences
        EdgeOptions options = new EdgeOptions();
        
        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("download.default_directory", downloadFilePath);
        edgePrefs.put("download.prompt_for_download", false); // Bypass the 'Save As' dialog
        edgePrefs.put("plugins.always_open_pdf_externally", true); // Automatically download PDF instead of opening

        options.setExperimentalOption("prefs", edgePrefs);

        // Instantiate the Edge driver with the specified options
        WebDriver driver = new EdgeDriver(options);

        try {
            // Navigate to the webpage that contains the PDF link
            driver.get("https://example.com/sample.pdf"); // Replace with the actual URL of the PDF

            // Wait for the file to download (you can add explicit waits here if needed)
            String downloadedFilePath = Paths.get(downloadFilePath, "sample.pdf").toString(); // Adjust the file name accordingly

            // Verify if the file is downloaded successfully
            File file = new File(downloadedFilePath);
            if (file.exists()) {
                System.out.println("PDF downloaded successfully.");

                // Read and verify the content of the PDF using PDFBox
                try (PDDocument document = PDDocument.load(file)) {
                    PDFTextStripper pdfStripper = new PDFTextStripper();
                    String pdfText = pdfStripper.getText(document);

                    // Check if the PDF contains the expected text
                    if (pdfText.contains("Expected text")) { // Replace "Expected text" with the actual text to verify
                        System.out.println("Text verification passed.");
                    } else {
                        System.out.println("Text verification failed.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Code to upload the file if necessary
                // e.g., WebDriver code to upload the file to a website

            } else {
                System.out.println("PDF download failed.");
            }
        } finally {
            // Clean up and close the browser
            driver.quit();
        }
    }
}
