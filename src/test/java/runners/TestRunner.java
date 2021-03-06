package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utilities.ServerManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"step_definitions"},
        plugin = {"pretty", "summary", "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class TestRunner {
    static ServerManager serverManager = new ServerManager();

    @BeforeClass
    public static void serverInitialize() {
        serverManager.startAppiumServer();
    }

    @AfterClass
    public static void serverCleanup() {
        serverManager.stopAppiumServer();
    }
}
