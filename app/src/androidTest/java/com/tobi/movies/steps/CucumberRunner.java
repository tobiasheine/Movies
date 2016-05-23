package com.tobi.movies.steps;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        plugin = {
//                "pretty",
//                "html:" + CucumberRunner.REPORT_PATH + "cucumber-html-report",
//                "pretty:" + CucumberRunner.REPORT_PATH + "cucumber-report.json",
//                "junit:" + CucumberRunner.REPORT_PATH + "cucumber.xml"
        },
        features = "features")
public class CucumberRunner {
    public static final String REPORT_PATH = "data/data/" + BuildConfig.APPLICATION_ID + "/cucumber-reports/";
}
