package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class OrdersProcessor {

	TreeMap<Integer, String[]> sharedMap = new TreeMap< Integer, String[]>();//CREATING A MAP

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan  = new Scanner(System.in);
		BufferedReader br = null;
		StringBuilder liner = new StringBuilder();
		String line;
		Inventory x = new Inventory();       

		System.out.println("Enter item's data file name: ");
		String dataInventory = scan.nextLine(); 
		File dataInventoryFile =  new File(dataInventory);
		br = new BufferedReader(new FileReader(dataInventoryFile));
		while((line = br.readLine())!= null) {
			liner.append(line+"\n");
		}
		String inventoryArray[]=liner.toString().split(" |\\n");
		for(int j = 0; j<inventoryArray.length; j+=2) {
			String key = inventoryArray[j];
			Double p = Double.parseDouble(inventoryArray[j+1]);
			x.inventory.put(key, p);
			
		}
		System.out.println("Enter 'y' for multiple threads, any other character otherwise:");
		String multThreads = scan.nextLine(); 
		System.out.println("how many orders");
		int orders = scan.nextInt(); 
		scan.nextLine();
		System.out.println("What file base");
		String userFile = scan.nextLine(); 
		
		System.out.println("What output file");
		String userOutFile = scan.nextLine();
		//File file =  new File(userFile);
		File outputFile =  new File(userOutFile);
		x.userOutFile = outputFile;

		
		String holder[] = new String[orders];

		if(multThreads.equals("y")) {
		
			
		
				for (int i = 0; i < orders; i++) {
					holder[i]= userFile+(i+1)+".txt";

				}
				Thread[] threads = new Thread[orders];
				long startTime = System.currentTimeMillis();
				/* TASK YOU WANT TO TIME */

				for (int i = 0; i < threads.length; i++) {
					File orderFile =  new File(holder[i]);

					threads[i] = new Threading(orderFile, outputFile, x);

					

				}
				for (int i = 0; i < threads.length; i++) {
					threads[i].start();

				}
				for (int i = 0; i < threads.length; i++) {
					try {
						threads[i].join();

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//System.out.println("This is right before generate summary " + x.finale);
				x.GenerateSummary(x.finale);
				long endTime = System.currentTimeMillis();
				System.out.println("Processing time (msec): " + (endTime - startTime));

			}
		
		else {
			for (int i = 0; i < orders; i++) {
				holder[i]= userFile+(i+1)+".txt";

			}
			long startTime = System.currentTimeMillis();
			/* TASK YOU WANT TO TIME */
			for (int i = 0; i < holder.length; i++) {
				File orderFile =  new File(holder[i]);
			
				Threading singleThread = new Threading(orderFile, outputFile, x);

				singleThread.run();
				
			}
			x.GenerateSummary(x.finale);
			long endTime = System.currentTimeMillis();
			System.out.println("Processing time (msec): " + (endTime - startTime));



		}

		scan.close();
		br.close();


	}




	/*for (File filex : file.listFiles()) {
		    Threading thread = new Threading(filex, outputFile);
		    thread.start();
		}*/
	//String yeet[]=file.list();

	/*
		for(int i =0; i<=orders; i++) {
			File orderFile =  new File(yeet[i]);

		    Threading thread = new Threading(orderFile, outputFile);
		    thread.start();

		}*/




	// String[] w = sharedMap.get(clientId);
	// System.out.println(w[1]);




}

class Threading extends Thread{
	File file;
	File userOutFile;
	Inventory x;      


	public Threading(File file, File userOutFile, Inventory x){

		this.file = file;
		this.userOutFile = userOutFile;
		this.x=x;
	}

	public void run() {
		//synchronized(this) {

		String line;
		StringBuilder liner = new StringBuilder();
		BufferedReader br = null;
		int clientId;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		try {
			while((line = br.readLine())!= null) {
				liner.append(line+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		line = liner.toString();
		String both[]=line.split(" |\n");
		clientId = Integer.parseInt(both[1]);
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(both));
		Collections.sort(arrayList);
		
		synchronized(x) {
		x.finale.put(clientId, arrayList);
		}
		System.out.println("This client is: " + clientId );
		x.userOutFile = this.userOutFile;
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


