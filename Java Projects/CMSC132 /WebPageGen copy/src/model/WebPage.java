package model;

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {

	private String title;
	ArrayList<Element> data;

	public WebPage(String title) {
		this.title = title;
		data = new ArrayList<Element>();

	}

	public int addElement(Element element) {
		if (!(element instanceof TagElement)) {
			data.add(element);
			return -1;
		} else {
			data.add(element);
			return ((TagElement) element).getId();
		}
	}

	public void writeToFile(String filename, int indentation) {
		for (int i = 0; i < data.size(); i++) {
			Utilities.writeToFile(filename, data.get(i).genHTML(indentation));
		}
	}

	public Element findElem(int id) {
		for (Element element : data) {
			if (element instanceof TagElement) {
				if (((TagElement) element).getId() == id) {
					return element;
				}
			}
		}
		return null;
	}

	public String stats() {

		int list = 0;
		int para = 0;
		int table = 0;
		int count = 0;
		double tableUtil = 0.0;


		for (Element element : data) {

			if (element instanceof TagElement) {
				if (((TagElement) element).getTag().equals("p")) {
					para++;
				}

				else if (((TagElement) element).getTag().equals("table")) {
					table++;

				} else if (((TagElement) element).getTag().equals("ul")
						|| ((TagElement) element).getTag().equals("ol")) {
					list++;

				}
				if (element instanceof TableElement) {
					count++;
					TableElement t = (TableElement) element;
					tableUtil += (double) t.getTableUtilization();

				}
			}
		}
		double fraction = tableUtil / count;

		return "List Count: " + list + "\n Paragraph Count: " + para
				+ "\n Table Count: " + table + "\nTableElement Utilization:"
				+ fraction;

	}


	public String getWebPageHTML(int indentation) {
		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;

		}
		for (Element element : data) {
			y += element.genHTML(0) + "\n";
		}
		return "<!doctype html>\n<html>\n<head>\n<meta charset=\"utf-8\"/>"
		+ "\n<title>" + this.title + "</title>\n" + "</head>\n"
		+ "<body>\n" + y + "</body>\n</html>";

	}

	@Override
	public int compareTo(WebPage o) {

		return this.title.compareTo(o.title);
	}

	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}

}
