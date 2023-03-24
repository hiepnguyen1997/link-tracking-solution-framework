package reportConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import common.GlobalConstant;

public class ExtentManager {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstant.PROJECT_PATH + "/extentReportV5/ExtentReport.html");
		reporter.config().setReportName("LINK - Tracking Solution Report");
		reporter.config().setDocumentTitle("LINK - Tracking Solution Report");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.STANDARD);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Bollore");
		extentReports.setSystemInfo("Project", "LINK");
		extentReports.setSystemInfo("Team", "LINK - CESA VN");
		extentReports.setSystemInfo("JDK version", GlobalConstant.JAVA_VERSION);
		return extentReports;
	}
}