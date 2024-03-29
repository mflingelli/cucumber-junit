package de.blackned.training.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/math",
        glue = "de.blackned.training.cucumber",
        plugin = {
                "json:target/cucumber-report.json",
                "junit:target/cucumber-junit-report.xml",
                "testng:target/cucumber-testng-report.xml"
        })
public class ArithmeticAcceptanceTest {
}
