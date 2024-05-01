package web_testing_app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WebTesting {
	private static WebDriver localDriver;
	
	
	public static WebDriver getDriver() {
		return localDriver;
	}
	
	
	public static void initSystemProperties() {
		Path currentRelativePath = Paths.get("");

		String geckoPath = Paths.get(currentRelativePath.toAbsolutePath().toString(), "drivers/geckodriver.exe").toString();
		geckoPath = geckoPath.replace("\\", "/");
		System.out.println(geckoPath);
		
		String chromePath = Paths.get(currentRelativePath.toAbsolutePath().toString(), "drivers/chromedriver.exe").toString();
		chromePath = chromePath.replace("\\", "/");
		System.out.println(chromePath);
				
		
		System.setProperty("webdriver.gecko.driver", geckoPath);
		
		
		System.setProperty("webdriver.chrome.driver", chromePath);
	}
	
	
	public static WebDriver launchDriver(String siteUrl, String browser) {
		if (browser.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions()
					     	.addPreference("browser.startup.page", 1)
					     	.addPreference("browser.startup.homepage", siteUrl)
					     	.setAcceptInsecureCerts(true)
					     	.setHeadless(false);
				
			quitDriver();
			localDriver = new FirefoxDriver(options);
		} else if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions()
							.addArguments("--homepage \""+siteUrl+"\"")
							.setAcceptInsecureCerts(true)
							.setHeadless(false);
							
			quitDriver();
			localDriver = new ChromeDriver(options);
		}
		
		return localDriver;
	}
	
	public static void quitDriver() {
		if (localDriver != null) {
			localDriver.quit();
			localDriver = null;
		}
	}
}
