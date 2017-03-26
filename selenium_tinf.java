package Lab2;

import java.io.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.csvreader.CsvReader;

public class selenium_tinf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver","d:\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	    capabilities.setCapability("mariontte",true);
		WebDriver driver = new FirefoxDriver(capabilities);
		
		String instr="";
		File inFile = new File("D://inputgit.csv");
		//String test=null;
		//String test=null;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			CsvReader creader = new CsvReader(reader,',');
			String str= null;
			//String line =null;
			//test="Œ∫”Ò≈Ù";
			char[] inchar;
			int count=1;
			while(creader.readRecord()){
				instr = creader.getRawRecord();
//				System.out.println(instr);
//				System.out.println(instr.length());
				for(int i =0;i<instr.length();i++){
					str = instr.replace(",", "");
					instr = str;
				}
//				System.out.println(instr);
				
				//Ã·»°◊÷∑˚¥Æ∑Ω∑®∂˛
//				String[] teststr=instr.trim().split(",");
//				System.out.println(teststr);
//				for(String s:teststr){
//					char[] ch = s.toCharArray();
//					if(ch[0]=='–’' && s.equals("–’√˚")){
//						System.out.println("–’√˚≤‚ ‘");
//						//System.out.println("right");
//					}else if(ch[0]=='g'){
//						System.out.println("git≤‚ ‘");
//					}
//					System.out.println(s);
//				}
//				inchar = instr.toCharArray();
//				System.out.println(inchar[2]);
//				System.out.println(instr);
				
				String first=instr.substring(0,2);
//				System.out.println(first);  30
				if(first.equals("30")){
					String id=instr.substring(0,10);
					//System.out.println(id);
					String pwd=id.substring(4,10);
					//System.out.println(pwd);
					driver.get("http://121.193.130.195:8080/");
					WebElement nameIn = driver.findElement(By.id("name"));
					nameIn.sendKeys(id);
					WebElement pwdIn = driver.findElement(By.id("pwd"));
					pwdIn.sendKeys(pwd);
					WebElement submit = driver.findElement(By.id("submit"));
					submit.click();
					
					String name=null;
					String url=null;
					inchar=instr.toCharArray();
					for(int i = 12;i<instr.length();i++){
						if(inchar[i]=='h'){
							name=instr.substring(10,i);
							url=instr.substring(i);
							break;
						}
					}
//					System.out.println(name);
//					System.out.println(url);
					
//					System.out.println(driver);
					WebElement readCon = driver.findElement(By.id("resultString"));
					String[] readStr = readCon.getAttribute("innerHTML").trim().split(",");
					//System.out.println("");
					
					char[] compare=null;
					for(String s:readStr){
						compare=s.toCharArray();
						if(compare[0]!='3'&&compare[0]!='h'){
							if(!name.equals(s)){
								System.out.println("Name is different "+name+"  which in file is in line "+count);
								System.out.println("");
							}
						}else if(compare[0]=='h'){
							if(!url.equals(s)){
								System.out.println("URL is different "+url+"  which in file is in line "+count);
								System.out.println("");
							}
						}
//						if(name.equals(s)){
////							System.out.println(count);
//							System.out.println("right"+"right");
//							count++;
//						}
//						else{
//							System.out.println("wrong");
//						}
//						System.out.println(s);
					}
					
					//driver.quit();
				}
				count++;
			}
			driver.quit();
			creader.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		

	}

}
