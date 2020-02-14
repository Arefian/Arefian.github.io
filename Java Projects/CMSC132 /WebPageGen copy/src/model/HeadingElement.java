package model;

public class HeadingElement extends TagElement {

	private int level;
	static String header = "h";

	public HeadingElement(Element content, int level, String attributes) {
		super(header, endTag, content, attributes);
		this.level = level;

	}

	@Override
	public String genHTML(int indentation) {

		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}
		return y + this.getStartTag() + this.level + ">"
		+ TagElement.content.genHTML(0) + "</" + this.getEndTag() + this.level
		+ ">";

	}

}
