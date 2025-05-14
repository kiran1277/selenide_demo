package org.example.report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class CucumberReportGenerator {

    public static void main(String[] args) {
        String jsonReportPath = args[0];
        String outputDirPath = args[1];
        String projectName = args.length > 2 ? args[2] : "selenide_demo";

        File reportOutputDirectory = new File(outputDirPath);
        List<String> jsonFiles = Collections.singletonList(jsonReportPath);

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Platform", System.getProperty("os.name"));
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
