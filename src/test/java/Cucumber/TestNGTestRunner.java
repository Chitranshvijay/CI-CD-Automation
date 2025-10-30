package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Cucumber can't run itself, it requires either TestNG and JUnit runner to execute the test scenarios.

@CucumberOptions(features="src/test/java/Cucumber", glue="Main.StepDefinitions", 
monochrome=true, tags="@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
}
