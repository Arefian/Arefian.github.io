package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AnchorElement;
import model.HeadingElement;
import model.ImageElement;
import model.ListElement;
import model.ParagraphElement;
import model.TableElement;
import model.TagElement;
import model.TextElement;
import model.WebPage;

public class StudentTests {
	
	
	
	
	/*@Test
	public void pubImageElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null, alt = "Testudo Image";
		int width = 84, height = 111;
		
		TagElement.resetIds();
		TagElement.enableId(true);
		ImageElement element = new ImageElement("testudo.jpg", width, height, alt, attributes);
		answer += element.genHTML(indentation);
		
		System.out.println(answer);

		
	}*/
	
	/*@Test
	public void pubParagraphElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null;
		
		TagElement.resetIds();
		TagElement.enableId(true);
		ParagraphElement element = new ParagraphElement(attributes);
		element.addItem(new TextElement("Fear the turtle"));
		element.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", attributes));
		element.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";
		System.out.println(answer);
}*/
	/*@Test
	public void pubListElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null;
		
		boolean orderedList = false;
		TagElement.resetIds();
		TagElement.enableId(true);
		ListElement element = new ListElement(orderedList, attributes);
		element.addItem(new TextElement("Superman"));
		element.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		answer += element.genHTML(indentation);
		System.out.println(answer);
		answer += "\nSecondElement\n";
}*/
	/*@Test
	public void pubTableTest1() {zoiiiideqacx vb
		int indentation = 3;
		String attributes = "border=\"1\"", answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		TableElement tableElement = new TableElement(2, 2, attributes);
		tableElement.addItem(0, 0, new TextElement("John"));
	//	System.out.print(tableElement.);
		//answer += tableElement.genHTML(indentation);
}*//*
	@Test
	public void pubWebPageTest1() {
		int indentation = 3;
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		WebPage webPage = new WebPage("Example1");
		answer = webPage.getWebPageHTML(indentation);
		//answer += TESTS_TAG;
		System.out.print(answer);
		
		assertTrue(TestsSupport.isCorrect("pubWebPageTest1.txt", answer));
	}*/
	/*@Test
	public void pubWebPageTest2() {
		WebPage webPage = new WebPage("Example1");
		int indentation = 3;
		String headingAttributes = null, paragraphAttributes = null, answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		webPage.addElement(new HeadingElement(new TextElement("Introduction"), 1, headingAttributes));
		ParagraphElement paragraph = new ParagraphElement(paragraphAttributes);
		paragraph.addItem(new TextElement("Fear the turtle"));
		paragraph.addItem(new ImageElement("testudo.jpg", 200, 300, "Testudo Image", ""));
		webPage.addElement(paragraph);

		answer += webPage.getWebPageHTML(indentation);
		System.out.println(answer);
		
			
		assertTrue(TestsSupport.isCorrect("pubWebPageTest2.txt", answer));
	}*/
	public void pubWebPageTest3() {
		WebPage webPage = new WebPage("Example2");
		int indentation = 3;
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		TableElement tableElement = new TableElement(2, 2, null);
		tableElement.addItem(0, 0, new TextElement("Dog"));
		tableElement.addItem(1, 1, new TextElement("Cat"));
		webPage.addElement(tableElement);

		tableElement = new TableElement(2, 2, null);
		tableElement.addItem(0, 0, new TextElement("Red"));
		tableElement.addItem(0, 1, new TextElement("Blue"));
		tableElement.addItem(1, 0, new TextElement("Green"));
		tableElement.addItem(1, 1, new TextElement("Yellow"));
		webPage.addElement(tableElement);
		
		webPage.addElement(new ListElement(true, null));

		answer += webPage.getWebPageHTML(indentation);
		answer += "\n" + webPage.stats();
		System.out.println(answer);
	}
}