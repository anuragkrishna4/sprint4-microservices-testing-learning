package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue      = "stepdefinitions",
        plugin    = {"pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"},
        monochrome = true
)
public class TestRunners {

}
