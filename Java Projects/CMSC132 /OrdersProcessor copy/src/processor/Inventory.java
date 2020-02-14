package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Inventory {

	Map<String, Double> inventory = new HashMap<String, Double>();//CREATING A MAP
	//TreeMap<Integer, TreeMap<Integer, ArrayList<String>>> summary = new TreeMap<Integer, TreeMap<Integer, ArrayList<String>> >();
	TreeMap<Integer, ArrayList<String>> finale = new TreeMap<Integer, ArrayList<String>>();

	//TreeMap< Integer, String > dataStuff = new TreeMap<Integer, String >();

	//public File file;
	public File userOutFile;
/*
	public Inventory(Integer x, ArrayList<String> y, File file, File userOutFile){
		//	this.file = file;
		this.userOutFile = userOutFile;

		finale.put(x, y);

	}*/
	public void GenerateSummary(TreeMap<Integer, ArrayList<String>> finale )throws IOException {
		DecimalFormat df = new DecimalFormat("#.00"); 

		//	BufferedReader br = null;
		BufferedWriter bw = null;
		int finalQuantity = 0;
		int finalQuantitySummary = 0;

		double finalItemPrice = 0.0;
		double finalItemPriceTotal =0.0;
		double finalTotalSpent = 0.0;
		try {
			bw = new BufferedWriter(new FileWriter(userOutFile,true));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for (Integer entry : finale.keySet()) {
			Set<String> finalSummary = new HashSet<String>();
			//System.out.println(  entry + "this is the entry\n");
			bw.write("----- Order details for client with Id: "+entry  +" -----\n");
			for(String items :finale.get(entry)) {
				if(inventory.containsKey(items)) {
					if (finalSummary.add(items)) { 
						finalItemPrice = inventory.get(items);
						finalQuantity = Collections.frequency(finale.get(entry), items);
						finalItemPriceTotal =inventory.get(items) * finalQuantity ;
						bw.write("Item's name: " + items
								+ ", Cost per item: $" + df.format(finalItemPrice) + ", Quantity: "+ finalQuantity 
								+ ", Cost: $" + df.format(finalItemPriceTotal)+"\n");
						finalTotalSpent += finalItemPriceTotal;


					}


				}
			}
			bw.write("Order Total: $"+df.format(finalTotalSpent));

			//System.out.println("Inventory total for " + entry + " is" + finalTotalSpent );
			finalTotalSpent = 0; //reset
			//	}

			try {
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Set<String> finalSummaryTotal = new HashSet<String>();
		ArrayList<String> finalSummary = new ArrayList<String>();
	//	System.out.println( "\nthis is the summary");
		bw.write("***** Summary of all orders *****\n");
		for (Integer entry : finale.keySet()) {
			finalSummary.addAll(finale.get(entry));
		}
			Collections.sort(finalSummary);
			for(String items :finalSummary) {
				if(inventory.containsKey(items)) {
					if (finalSummaryTotal.add(items)) { 
						finalItemPrice = inventory.get(items);
						finalQuantitySummary = Collections.frequency(finalSummary, items);
						finalItemPriceTotal =inventory.get(items) * finalQuantitySummary ;
						bw.write("Summary - Item's name: " + items
								+ ", Cost per item: " +  NumberFormat.getCurrencyInstance().format(finalItemPrice) + ", Number sold: "+ finalQuantitySummary 
								+ ", Item's Total: " + NumberFormat.getCurrencyInstance().format(finalItemPriceTotal)+"\n");
						finalTotalSpent += finalItemPriceTotal;


					}


				}
			}
			
			//finalTotalSpent = 0; //reset
			//	}

			
			
		
		bw.write("Summary Grand Total: "+NumberFormat.getCurrencyInstance().format(finalTotalSpent));
		
		bw.close();
	}






	/*public Map<String, Double> getInventory() {
		return inventory;
	}

	public Inventory(){
		inventory.put("Textbook", 25.00);
		inventory.put("Blu-Ray", 15.00);
		inventory.put("Lamp", 3.50);
		inventory.put("Shoes", 30.00);
		inventory.put("Song", 2.00);
		inventory.put("MovieRental", 4.00);
		inventory.put("Phone", 13.75);
	}*/



}
