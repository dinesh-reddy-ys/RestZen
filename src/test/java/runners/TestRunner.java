package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", //path to your .feature files
    glue = {"stepDefs", "hooks"},                // Steps defs + hooks package locations
    plugin = {
            "pretty",                         // Neat console output
            "html:target/cucumber-reports.html",  // HTML report
            "json:target/cucumber.json"      // JSON report (useful for integrations)
    },
        monochrome = true,                   // Cleaner console output (no weird characters)
    tags = ""                          // true = checks mapping but doesn't execute tests
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
