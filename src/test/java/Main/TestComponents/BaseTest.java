package Main.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Main.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		
		//Converting Properties file to FileInputStream
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Main//Resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		// ? :- Terminary Operator
		// System.getProperty("browser")- Value from Command Prompt
		// prop.getProperty("browser")- Value from Global Data file.
		//String browserName= prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
			//Chrome Driver
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "C:/Users/vijayc2/Downloads/chromedriver-win64/chromedriver.exe");
			
			//To run the Chrome in headless mode.
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); // to run in full screen(optional)
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			//Firefox Driver
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			
			//Edge Driver
			System.setProperty("webdriver.edge.driver", "C:/Users/vijayc2/Downloads/edgedriver_win64/msedgedriver.exe");
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			}
		
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	//To get data from JSON file
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		// Read JSON to String
		String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "//src//test//java//Main//Data//PurchaseOrder.json"), StandardCharsets.UTF_8);
		
		//String to HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String file = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		FileUtils.copyFile(src, new File(file));
		return file;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchWebPage() throws IOException {
		
		driver= initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToURL();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		driver.quit();
	}

}
