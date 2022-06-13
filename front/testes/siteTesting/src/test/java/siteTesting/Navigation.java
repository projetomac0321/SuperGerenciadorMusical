package siteTesting;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Navigation {
     String list, home, create, url;           
	 WebDriver driver;
	   
	   @Before
	   public void init() {
		   list = "http://localhost:3000/listplaylists";
		   create = "http://localhost:3000/createplaylists";
		   home = "http://localhost:3000/home";
		   url = "http://localhost:3000";
		   WebDriverManager.firefoxdriver().setup();
		   driver = new FirefoxDriver();
		   driver.manage().window().maximize(); 
		   driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
	   }
	   
	   @After
	   public void finish() {
		   driver.quit();	   		   
	   }


	   @Test
	   public void consultarMusica() {
		   driver.get(list);
		   
		   driver.findElement(By.className("playlistRowText")).click();
		   assertEquals("http://localhost:3000/listplaylists/playlist_0", driver.getCurrentUrl());
		   
		   driver.findElement(By.className("songRowText")).click();
		   assertEquals("http://localhost:3000/listplaylists/playlist_0/musica_0", driver.getCurrentUrl());
	   }
	   
	   @Test
	   public void entrarEmHome() {
		   driver.get(url);
		   driver.findElement(By.id("homeLink")).click();

		   assertEquals(home, driver.getCurrentUrl());
	   }
	   
	   @Test
	   public void entrarEmListPlaylists() {
		   driver.get(url);
		   driver.findElement(By.id("listLink")).click();

		   assertEquals(list, driver.getCurrentUrl());
	   }
	   
	   @Test
	   public void entrarEmCreatePlaylists() {
		   driver.get(url);
		   driver.findElement(By.id("createLink")).click();

		   assertEquals(create, driver.getCurrentUrl());
	   }
	   
	   @Test
	   public void listToCreate() {
		   driver.get(list);
		   driver.findElement(By.className("playlistCreateText")).click();
		   assertEquals(create, driver.getCurrentUrl());
	   }
	   
}
