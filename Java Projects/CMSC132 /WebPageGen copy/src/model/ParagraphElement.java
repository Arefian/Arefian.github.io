package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement{

	ArrayList <Element> data= new ArrayList<Element>();
	private static String tagName = "p";

	public ParagraphElement ( String attributes) {
		super(tagName, endTag, content, attributes);
	}

	public void addItem(Element item) {
		data.add(item);
	}
	@Override
	public String genHTML(int indentation) {

		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}
		for(int i=0; i<data.size(); i++) {
			y +=data.get(i).genHTML(0)+"\n";
		}

		return this.getStartTag()+">\n"+ y+"</"+this.getEndTag()+">";


	}

}
