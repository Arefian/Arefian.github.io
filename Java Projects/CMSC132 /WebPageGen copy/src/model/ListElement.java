package model;

import java.util.ArrayList;

public class ListElement extends TagElement{

	private static String listType;
	ArrayList<Element> data= new ArrayList<Element>() ;
	private boolean ordered;

	public ListElement(boolean ordered, String attributes) {
		super(listType = (ordered ? "ol":"ul"), endTag, content, attributes);

	}
	public void addItem(Element x) {
		data.add(x);

	}

	@Override
	public String genHTML(int indentation) {
		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;


		}for(int i=0; i<data.size(); i++) {
			y += "<li>\n"+data.get(i).genHTML(0)+"\n</li>"+"\n";
		}
		return this.getStartTag()+">\n"+ y+"</"+this.getEndTag()+">";


	}

}
