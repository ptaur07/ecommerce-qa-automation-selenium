package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(
                            "reports/extent-report.html");

            reporter.config()
                    .setDocumentTitle(
                            "E-Commerce Automation Report");

            reporter.config()
                    .setReportName(
                            "QA Automation Execution");

            extent =
                    new ExtentReports();

            extent.attachReporter(reporter);

            extent.setSystemInfo(
                    "Application",
                    "E-Commerce");

            extent.setSystemInfo(
                    "Framework",
                    "Selenium + Java + TestNG");

            extent.setSystemInfo(
                    "Environment",
                    "QA");
        }

        return extent;
    }
}