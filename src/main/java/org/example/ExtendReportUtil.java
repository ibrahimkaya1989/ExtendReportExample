package org.example;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendReportUtil {
    private static ExtentReports oExtentReport;
    private static ExtentTest oExtentTest;
    private static String reportPath, reportFolder, reportVideoFolder, reportScreenshotFolder;

    public ExtendReportUtil() {
        reportFolder = getReportPath();
        reportPath = System.getProperty("user.dir") + "/" + reportFolder;
        reportVideoFolder = getReportVideoFolder(reportFolder);
        reportScreenshotFolder = getReportScreenshotFolder(reportFolder);
        this.createReportFiles();
    }

    /**
     * <p>This method is used to add a new test scenario. </p>
     * @param scenarioName  Used to define test scenario name.
     */
    public void startReport(String scenarioName) {
        oExtentTest = oExtentReport.startTest(scenarioName);
    }

    /**
     * <p>This method is used to finish preparing and creating report file. </p>
     */
    public void endReport() {
        oExtentReport.endTest(oExtentTest);
        oExtentReport.flush();
    }

    public void addStep(String status, String message){
        oExtentTest.log(LogStatus.valueOf(status), message);
    }

    public void addStepWithScreenshot(String status, String message, String imagePath){
        oExtentTest.log(LogStatus.valueOf(status), message, oExtentTest.addScreenCapture(imagePath));
    }

    public void addStepWithVideoRecording(String status, String message, String videoPath){
        oExtentTest.log(LogStatus.valueOf(status), message, oExtentTest.addScreencast(videoPath));
    }

    /**
     * <p>This method is used to define report file. </p>
     * @return report's path.
     */
    private String getReportPath() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "Reports/Report_" + dateFormat.format(new Date());
    }

    /**
     * <p>This method is used to define screenshots' file. </p>
     * @param reportPath  Used to define and create test screenshots' file.
     * @return full path of screenshots' file.
     */
    private String getReportScreenshotFolder(String reportPath){
        return reportPath + "/Screenshots";
    }

    /**
     * <p>This method is used to define video recordings' file. </p>
     * @param reportPath  Used to define and create test video recordings' file.
     * @return full path of video recordings' file.
     */
    public String getReportVideoFolder(String reportPath){
        return reportPath + "/VideoRecords";
    }

    /**
     * <p>This method is used to create test report files. </p>
     */
    private void createReportFiles(){

        // Creation files
        File f = new File(reportFolder);
        File ss = new File(reportScreenshotFolder);
        File v = new File(reportVideoFolder);

        try {
            f.mkdir();
            ss.mkdir();
            v.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Report xml path
        oExtentReport = new ExtentReports(reportFolder + "/TestSuiteReport.html", true);
        oExtentReport.loadConfig(new File("config.xml"));
    }
}
