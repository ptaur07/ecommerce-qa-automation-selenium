package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver,
                                        String testName) {

        try {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String path =
                    "reports/screenshots/"
                    + testName
                    + "_"
                    + timestamp
                    + ".png";

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE);

            File destination =
                    new File(path);

            FileUtils.copyFile(
                    source,
                    destination);

            return destination.getAbsolutePath();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}