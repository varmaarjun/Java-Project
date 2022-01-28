package com.SeleniumProject.Assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class AssignmentMain {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver\\chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("headless");
		
		String url="https://www.house.gov/representatives";
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);

		int t= driver.findElements(By.xpath("/html/body/div[2]/div/div[2]/section/div/div[2]/div/div/div/section[1]/div/div/div[2]/table")).size();
		int c = driver.findElements(By.xpath("/html/body/div[2]/div/div[2]/section/div/div[2]/div/div/div/section[1]/div/div/div[2]/table[1]/tbody/tr[1]/td")).size();
		ArrayList al=new ArrayList();

		Gson gson=new GsonBuilder().setPrettyPrinting().create();

		String District=null,Name=null,Party=null,OfficeRoom=null,Phone=null,CommitteeAssignment=null,FName=null,LName=null;
		String nm[] = null;
		String Country="United State Of America";
		WebElement ele = null;

		for(int k=1;k<=t;k++) {
			int row = driver.findElements(By.xpath("/html/body/div[2]/div/div[2]/section/div/div[2]/div/div/div/section[1]/div/div/div[2]/table["+k+"]/tbody/tr")).size();
			String State = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/div/div[2]/div/div/div/section[1]/div/div/div[2]/table["+k+"]/caption")).getText();

			for(int i=1;i<=row;i++) {

				for(int j=1;j<=c;j++) {
					if(row==1) {
						ele = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/div/div[2]/div/div/div/section[1]/div/div/div[2]/table["+k+"]/tbody/tr/td["+j+"]"));

					}
					else {

						ele = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/div/div[2]/div/div/div/section[1]/div/div/div[2]/table["+k+"]/tbody/tr["+i+"]/td["+j+"]"));

					}
					if(j==1)
						District=State+" "+ele.getText();
					if(j==2) {
						Name=ele.getText().replaceAll("\\(link is external\\)", "");
						nm=Name.split(",");
						FName=nm[0];
						LName=nm[1];
					}
					if(j==3)
						Party=ele.getText();
					if(j==4)
						OfficeRoom=ele.getText();
					if(j==5)
						Phone=ele.getText();
					if(j==6)
						CommitteeAssignment=ele.getText();

				}

				al.add(new Directory(FName,LName.trim(),District,Party,OfficeRoom,Phone,CommitteeAssignment,Country,url));

			}
		}

		try {
			FileWriter writer=new FileWriter("D:\\sample.json");
			gson.toJson(al, writer);
			writer.close();

		} 
		catch (IOException e) {

			e.printStackTrace();
		}

		driver.close();
		System.out.println("Success");

	}

}
