package com.framework.runners;

import org.testng.annotations.Listeners;

import com.framework.cucumber.AbstractTestNGCucumberTest;

import io.cucumber.testng.CucumberOptions;

@Listeners(com.framework.listeners.TestListener.class)
@CucumberOptions(features = "src/test/resources/features/",

        plugin = {"pretty", "json:target/cucumber-report/report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.framework.stepdefinition"},
        tags = "@Interview",
        dryRun = false

)

public class Runner extends AbstractTestNGCucumberTest {

}
