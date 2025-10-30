package Main.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "//reports//index.html";

		// ExtentSparkReporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("WebPage Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		// ExtentReports
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Chitransh Vijay");
		return extent;
		
	}

}
