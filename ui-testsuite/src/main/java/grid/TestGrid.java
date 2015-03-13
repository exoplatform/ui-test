package grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestGrid {
	WebDriver driver;
	String baseUrl, nodeUrl;
	
	@BeforeTest
	public void SetUp() throws MalformedURLException {
		baseUrl = "http://localhost:8080/portal/intranet/";
		nodeUrl = "http://127.0.0.1:5555/wd/hub";
		
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeUrl), capability);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	@Test
	public void test() {
		driver.get(baseUrl);
		Assert.assertEquals("Login", driver.getTitle());
		
		Utils.pause(5000);;
	}
}
