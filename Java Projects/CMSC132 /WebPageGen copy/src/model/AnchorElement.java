package model;

public class AnchorElement extends TagElement {

	private String url;
	private String linkText;
	private static String anchor = "a";

	public AnchorElement(String url, String linkText, String attributes) {
		super(anchor, endTag, content, attributes);
		this.url = url;
		this.linkText = linkText;

	}

	public String getLinkText() {
		return this.linkText;

	}

	public String getUrlText() {
		return this.url;

	}

	@Override
	public String genHTML(int indentation) {

		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}
		return y + this.getStartTag()
		+ " href="+("\"" + (this.url) + "\"" + ">" + this.linkText) + "</a>";
	}
}
