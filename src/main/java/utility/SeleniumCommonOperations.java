package utility;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SeleniumCommonOperations {
	public static void enterText(WebElement element, String data) {
		element.sendKeys(data);
	}
	
	public static void enter(WebElement element) {
		element.click();
	}
	 public static void enterKeys(WebElement element, Keys key) {
		 element.sendKeys(key);
	 }

	public static void enterForList(List<WebElement> elements) {
		for(int i=0;i<=elements.size()-1;i++) {
		elements.get(i).click();
			}
	}

	public static String getTextData(WebElement element) {
		return element.getText();
		
	}
}
