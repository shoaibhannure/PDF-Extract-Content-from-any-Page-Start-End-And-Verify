package com.shoaib.selenium;
/*
 * 
 * 
 * @Author:- SHOAIB HANNURE
 * 
 * 
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadContentOfPdf {
	WebDriver driver = null;

	@BeforeTest
	public void SetUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	//Method to get Page Count
	public static int getPageCount(PDDocument doc) {
		//get the total number of pages in the pdf document
		int pageCount = doc.getNumberOfPages();
		return pageCount;
		
	}
	
	//Method to Read PDF Start Page & End Page
public static  String readPdfContent(String url) throws IOException {
		
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		PDFTextStripper pdfStrip = new PDFTextStripper();
		pdfStrip.setStartPage(2); //Start Reading From Page No 2 
		pdfStrip.setEndPage(4); //End Reading To Page No 4
		String content = pdfStrip.getText(doc);
		doc.close();
	
	return content;
}
	
	@Test
	public void verifyContentInPDf() throws IOException {
		//specify the url of the pdf file
		String url ="http://www.pdf995.com/samples/pdf.pdf";
		driver.get(url);
		
			String pdfContent = readPdfContent(url);
			System.out.println(pdfContent);
			Assert.assertTrue(pdfContent.contains("Introduction"));
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
