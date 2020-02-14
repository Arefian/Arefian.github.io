package model;

public class ImageElement extends TagElement implements Element {

	private String imageURL;
	private int width;
	private int height;
	private String alt;
	private static String image = "img";

	public ImageElement(String imageURL, int width, int height, String alt,
			String attributes) {
		super(image, endTag, content, attributes);
		this.imageURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;
		// TODO Auto-generated constructor stub
	}

	public String getImageURL() {
		return this.imageURL;
	}

	@Override
	public String genHTML(int indentation) {
		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}

		return y + this.getStartTag() + (" src=" + "\"" + this.imageURL + "\"")
				+ " width=" + "\"" + this.width
				+ ("\"" + " height=" + "\"" + this.height + "\"") + " alt="
				+ "\"" + this.alt + "\"" + ">";
	}
}
