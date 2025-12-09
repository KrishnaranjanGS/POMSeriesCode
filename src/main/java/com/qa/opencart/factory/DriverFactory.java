package com.qa.opencart.factory;
//driver initialization is very important. The driver instance has to be the same throughout the flow; throughout the execution.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;



public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is initializing the driver based on the given browser name
	 * 
	 * @param browserName
	 * @return method returns driver
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		System.out.println("Browser name is: " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} 
		else if (browserName.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} 
		else if (browserName.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		} 
		else if (browserName.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} 
		else {
				System.out.println("Invalid browser name. Please pass the right browser.");
			}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/**
	 * get the local thread copy of the driver
	 */
	public synchronized static WebDriver getDriver() {			//synchronized keyword ensures every thread gets its own individual copy
		return tlDriver.get();
	}

	/**
	 * This method is reading the properties from properties file
	 * 
	 * @return the object reference of properties class
	 */
	public Properties initProp() {
//		mvn clean install -Denv="qa"
//		mvn clean install
		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("Running test cases on env: " + envName);
			try {
				if(envName==null) {
					System.out.println("No environment is passed. Running test on QA environment");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				}
				else {
					switch (envName.toLowerCase().trim()) {
					case "prod":
						ip = new FileInputStream("./src/test/resources/config/config.properties");
						break;
					case "qa":
						ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;
	
					default:
						System.out.println("Invalid environment passed: " + envName + "\n" + "Valid values are qa, prod.");
						throw new FrameworkException("Wrong environment");
					}
				}
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return prop;
	}
	
	/**
	 * take a screenshot
	 */
	public static String getScreenshot() {
		File screenshotFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/reports/screenshots/screenshot" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
			try {
				FileUtil.copyFile(screenshotFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return path;
	}
}
