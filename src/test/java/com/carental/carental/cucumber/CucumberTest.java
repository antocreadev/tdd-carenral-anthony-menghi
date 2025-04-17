package com.carental.carental.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/com/carental/carental/cucumber/features/carental.feature",
    glue = "com.carental.carental.cucumber",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/report.json"
    }
)
public class CucumberTest {
}