package restassured.util;


import java.text.SimpleDateFormat;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    static ExtentReports extent;
    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
   // extent = new ExtentReports(System.getProperty("user.dir") + "/CredifytechReport/Report" + formater.format(calendar.getTime()) + ".html", false);
    final static String filePath = "./ExecutionReport/Report.html";
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }
        
        return extent;
    }
}