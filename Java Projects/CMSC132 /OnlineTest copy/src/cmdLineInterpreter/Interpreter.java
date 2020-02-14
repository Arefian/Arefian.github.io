/*package cmdLineInterpreter;

import java.io.Serializable;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import objectStreams.Phonebook;
import onlineTest.SystemManager;

*//**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 *	Add a student
	Add an exam
	Add a true/false question
	Answer a true/false question
	Get the exam score for a student
 *//*
public class Interpreter implements Serializable {
 
		public static void main(String[] args) throws Exception {

			SystemManager phonebook = (SystemManager) phonebook.restoreManager("lol");

			int choice;

			do {
				String menu = "Enter 1 to look up a phone\n";
				menu += "Enter 2 to add/update a phone\n";
				menu += "Enter 3 to display phone book\n";
				menu += "Enter 4 to clear phone book\n";
				menu += "Enter 5 to quit";

				choice = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
				String name, number;

				switch (choice) {
				case 1:
					name = JOptionPane.showInputDialog(null, "Enter name");
					JOptionPane.showMessageDialog(null, phonebook.get(name));
					break;
				case 2:
					name = JOptionPane.showInputDialog(null, "Enter name");
					number = JOptionPane.showInputDialog(null, "Enter number");
					phonebook.addUpdate(name, number);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, phonebook);
					break;
				case 4:
					phonebook.clear();
					break;
				case 5:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid choice.");
				}
			} while (choice != 5);

			savePhonebook(phonebook);
		}
}*/