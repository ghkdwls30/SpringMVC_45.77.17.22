package com.sample.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestJava {

	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.chrome.driver", "c:\\APP\\chromedriver.exe");
   	 
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
     
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://naver.com");
        //driver.quit();

        driver.findElement(By.cssSelector("#query")).sendKeys("사과");
        driver.findElement(By.cssSelector("#search_btn")).click();
        
        List<WebElement> elements  = driver.findElements(By.cssSelector("._related_keyword_ul a"));
        
        for (int j = 0; j < elements.size(); j++)
        {
        	WebElement element = elements.get(j);
        	System.out.println(element.getText());
        }

	}
}
