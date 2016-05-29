/**
 * A singleton class containing the ArrayList of all inventories besides the master.
 * 
 * @author Julia McClellan, Luke Giacalone, Hyun Choi
 * @version 05/28/2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventories
{
	private static ArrayList<Inventory> inventories;
	private static final String INVENTORY_FILE_LOC = "inventories.ilist";
	private static GUI gui; //Keeps track of the main GUI involved in the program
	
	/**
	 * Returns the ArrayList of inventories.
	 * 
	 * @return The list of inventories.
	 */
	public static ArrayList<Inventory> getList()
	{
		return inventories;
	}
	
	/**
	 * Sets the GUI to the value.
	 * 
	 * @param g The GUI for the program.
	 */
	public static void setGUI(GUI g)
	{
		gui = g;
	}
	
	/**
	 * Adds an inventory to the list.
	 * 
	 * @param i The inventory to be added.
	 */
	public static void addInventory(Inventory i)
	{
		inventories.add(i);
		gui.addInventory(i);
	}
	
	public static void removeInventory(Inventory i)
	{
		inventories.remove(i);
		gui.removeInventory(i);
	}
	
	/**
	 * Imports the list of inventory names from last use of program
	 * 
	 * @return The list of inventory names
	 * @throws FileNotFoundException If there is a problem locating the File
	 */
	public static void importInventories() throws FileNotFoundException {
		inventories = new ArrayList<Inventory>();
		File file = new File(INVENTORY_FILE_LOC);
		
		if(!file.exists()) return;
		
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()) {
			Inventory temp = new Inventory(scan.nextLine());
			temp.importInventory();
			inventories.add(temp);
		}
		scan.close();
		MasterInventory.addInventories(inventories);
	}
	
	/**
	 * Exports the names of the inventories to a file for later use
	 * 
	 * @throws IOException If there is a problem in export
	 */
	public static void exportInventories() throws IOException {
		File file = new File(INVENTORY_FILE_LOC);
		
		if(!file.exists()) file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		for(Inventory i: inventories) writer.println(i.getName());
		writer.close();
	}
}