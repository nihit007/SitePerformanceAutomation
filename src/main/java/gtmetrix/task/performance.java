package gtmetrix.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class performance {
	// CONVERT PERCENTAGE TO GRADE
	private static String percentageToGrade(String val) {
		String newchar = val.replaceAll("%", "");
		int per = Integer.parseInt(newchar);
		if(per>89){
		return ("Grade A");
		}
		else if(per<90 && per>79) {
			return("Grade B");
		}
		else if(per>69 && per<80) {
			return ("Grade C");
		}
		else if(per>59 && per<70) {
			return ("Grade D");
		}
		else if(per>49 && per<60) {
			return ("Grade E");
		}
		else {
			return("Worst site");
		}
	}
	//CONVERT GRADE TO PERCENTAGE
	private static String GradeToPercentage(char val) {
		if(val =='A') {
			return ("90% - 100%");
		}
		else if(val == 'B') {
			return ("80% - 90%");
		}
		else if(val == 'C') {
			return ("70% - 80%");
		}
		else if(val == 'D') {
			return ("60% - 70%");
		}
		else if(val == 'E') {
			return ("50% - 60%");
		}
		else {
			return ("Worst Site");
		}
	}


	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver","C:\\\\geckodriver\\\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://gtmetrix.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.findElement(new By.ByXPath("//input[@type='url']")).sendKeys("https://www.tothenew.com/",Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement Grade = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("(//span[@class='report-score-percent'])[1]")));
		String grade1 = Grade.getText();
		String grade2 = driver.findElement(new By.ByXPath("(//span[@class='report-score-percent'])[2]")).getText();
		WebElement Grade0 = driver.findElement(new By.ByXPath("//div[@class='report-score report-score-grade-gtmetrix']/i"));
		String classname = Grade0.getAttribute("class");
		String[] arr  =classname.split("-");
		String charracter = arr[2];
		char value = charracter.charAt(0);
		String Performance = percentageToGrade(grade1);
		String Structure= percentageToGrade(grade2);
		String  Overall = GradeToPercentage(value);
		
		System.out.println("Overall Percentage Range is Between : "+Overall);
		System.out.println("PERFORMANCE GRADE IS : "+Performance);
		System.out.println("STRUCTURE GRADE IS : "+Structure);
	
	}

}
