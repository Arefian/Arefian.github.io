package model;

public class TagElement implements Element {

	private String tagName;
	protected static boolean endTag = true;
	protected static Element content;
	private String attributes;
	private static int staticId = 0;
	private int id = staticId + 1;
	private static boolean idFlag = true;

	public TagElement(String tagName, boolean endTag, Element z,
			String attributes) {
		this.attributes = attributes;
		this.tagName = tagName;
		TagElement.endTag = endTag;
		TagElement.content = z;
		staticId++;
	}

	public int getId() {
		return this.id;
	}
	public String getTag() {
		return this.tagName;
	}

	public String getStringId() {
		return (this.tagName + this.getId());
	}

	public String getStartTag() {
		if (idFlag == true && this.attributes != null) {
			return ("<" + this.tagName+ " id= " +"\"" + this.tagName+ this.id + "\" "+ this.attributes );
		} else if (idFlag == true && this.attributes == null) {
			return ("<" + this.tagName+ " id= " +"\"" + this.tagName+ this.id + "\"");
		} else if (idFlag == false && this.attributes == null) {
			return ("<" + this.tagName);
		} else {
			return ("<" + this.tagName + this.attributes);
		}
	}

	public String getEndTag() {
		if (TagElement.endTag == false) {
			return ("");
		} else {
			return (this.tagName);
		}
	}

	public static void enableId(boolean choice) {
		idFlag = choice;
	}

	public String genHTML(int indentation) {

		String x = this.getStartTag() + TagElement.content.genHTML(indentation)
		+ this.getEndTag();
		String y = "";
		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}
		System.out.println(y + x);
		return (y + x);
	}

	public static void resetIds() {
		staticId = 0;

	}



}
