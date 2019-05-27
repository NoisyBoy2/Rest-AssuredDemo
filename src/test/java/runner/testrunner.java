package runner;



import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/"},
		glue= {"com.test.stepdefs"},
		tags	= {"@test"}
                )

public class testrunner {
	@BeforeClass
	  public static void test() {
		System.out.println("************* Test Starts Here *************");
	    
	    }
	 @AfterClass
	    public static void writeExtentReport() {
	      System.out.println("************* Test Ends here *************");
	    
	    }
}
