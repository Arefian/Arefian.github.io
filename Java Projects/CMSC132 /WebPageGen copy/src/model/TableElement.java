package model;

public class TableElement extends TagElement {

	private static String tagName = "table";
	private int row;
	private int col;
	final private int PERCENT = 100;

	private int counter;

	Element data[][];
	public TableElement (int x, int y, String attributes) {
		super(tagName, endTag, content, attributes);
		this.row=x;
		this.col=y;
		data= new Element[row][col];

	}
	public void addItem(int x, int y, Element item) {
		counter++;
		data[x][y]=item;

		//your value

	}

	public double getTableUtilization () {
		for (int r=0; r<data.length; r++) {
			for (int c=0; c<data[r].length; c++) {



			}
		}


		return (double)counter/(this.row * this.col) * PERCENT;//*100
	}

	@Override
	public String genHTML(int indentation) {

		String y = "";

		for (int i = 0; i < indentation; i++) {
			y = (" ") + y;
		}
		for (int r=0; r<data.length; r++) {
			y+= "<tr>";
			for (int c=0; c<data[r].length; c++) {
				y+= "<td>";
				if(data[r][c] != null) {
					y+= data[r][c].genHTML(0);

				}y+="</td>";
			}y+="</tr>\n";
		}

		return this.getStartTag()+">\n"+ y+"</"+this.getEndTag()+">";
	}
}