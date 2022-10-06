package org.example;

public class Main {

    static ExtendReportUtil extendReportUtil = new ExtendReportUtil();

    public static void main(String[] args) {

        extendReportUtil.startReport("First Step");

        extendReportUtil.addStep("PASS", "Test is passed successfully");
        extendReportUtil.addStep("FAIL", "Test is failed");
        extendReportUtil.addStep("INFO", "Test is skipped");
        extendReportUtil.addStep("PASS", "Test is done");

        extendReportUtil.addStepWithScreenshot("PASS", "Screenshot has been taken!",
                "C:\\Example\\Screenshots\\first.png");

        extendReportUtil.addStepWithVideoRecording("PASS", "Video has been recorded!",
                "C:\\Example\\Videos\\first.mp4");

        extendReportUtil.endReport();
    }
}