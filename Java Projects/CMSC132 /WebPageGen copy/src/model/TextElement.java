package model;

public class TextElement implements Element {

	private String text;

	public TextElement(String text) {
		this.text = text;
	}

	@Override
	public String genHTML(int indentation) {
		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}
		return y + this.text;
	}

}
