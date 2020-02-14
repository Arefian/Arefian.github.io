package foodManagement;

/**
 *  The Restaurant has a name (String), a menu (list of Entrees), an inventory
 *  (list of Food), and an amount of cash on hand, measured in pennies (int)
 * 
 *  This class facilitates orders being placed, deliveries being made to the
 *  inventory, and entrees being added to the menu.
 */
public class Restaurant {

	/*
	 * STUDENTS:  YOU MAY NOT ADD ANY OTHER INSTANCE VARIABLES TO THIS CLASS!
	 */
	private String name;
	private SortedListOfImmutables menu;       // A list of Entree objects	
	private SortedListOfImmutables inventory;  // A list of Food objects
	private int cash;

	/**
	 * Standard constructor.  The menu and the inventory are both initialized as 
	 * empty lists.  The name and cash amount are set to match the paramters.
	 * 
	 * @param nameIn name of the restaurant
	 * @param startingCash cash amount that the restaurant will have, measured
	 * in pennies
	 */
	public Restaurant(String nameIn, int startingCash) {
		this.name=nameIn;
		this.cash = startingCash;
		inventory= new SortedListOfImmutables();  
		menu = new SortedListOfImmutables();       
	}

	/**
	 * Getter for the name of the restaurant.
	 * 
	 * @return reference to the name of the restaurant
	 */
	public String getName() {
	return this.name;
		
	}

	/**
	 * Getter for the menu.
	 * 
	 * @return a reference to a copy of the menu
	 */
	public SortedListOfImmutables getMenu() {
		return new SortedListOfImmutables(this.menu);

}

	/**
	 * Adds an entree to the menu.
	 * 
	 * @param entreeToAdd reference to the entree to be added to the menu
	 */
	public void addEntree(Entree entreeToAdd) {
		this.menu.add(entreeToAdd);
	}
	
	/**
	 * Getter for the inventory.
	 * 
	 * @return a reference to a copy of the inventory
	 */
	public SortedListOfImmutables getInventory() {
		
		return new SortedListOfImmutables(this.inventory);
	}

	/**
	 * Getter for the current amount of cash on hand
	 * 
	 * @return the current amount of cash, measured in pennies
	 */
	public int getCash() {
		return this.cash;
	}

	/**
	 * Checks if the Food items contained in the specified Entree are 
	 * actually contained in the restaurant's inventory.
	 * 
	 * @param entree Entree that we are checking against the inventory
	 * @return true if the list of Food items contained in the Entree are
	 * all present in the inventory, false otherwise.
	 */
	public boolean checkIfInInventory(Entree entree) {
		if(this.inventory.checkAvailability(entree.getFoodList()) == true) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * Adds the specified list of food items to the inventory.  If the 
	 * total wholesale cost of all of the food items combined exceeds 
	 * the amount of cash on hand, then NONE of the food items are added 
	 * to the inventory.  If the amount of cash on hand is sufficient to
	 * pay for the shipment, then the amount of cash on hand is reduced by 
	 * the wholesale cost of the shipment.
	 * 
	 * @param list food items to be added to the inventory
	 * @return true if the food items are added; false if the food items are
	 * not added because their wholesale cost exceeds the current cash
	 * on hand
	 */
	public boolean addShipmentToInventory(SortedListOfImmutables list) {
	//check if wholesale cost is less than cash
		if(list.getWholesaleCost()>this.cash) {
			return false;
		}else {
			this.inventory.add(list); //add the list of items to the inventory
			this.cash-= list.getWholesaleCost();//subtract the cost from cash
			return true;
		}
	
	}

	/**
	 * Removes the food items contained in the specified Entree from the inventory.
	 * If the inventory does not contain all of the items required for this
	 * Entree, then NOTHING is removed from the inventory.  If the inventory contains
	 * all of the required items, then the amount of cash on hand is INCREASED by
	 * the retail value of the Entree.
	 *  
	 * @param entree Entree that has been ordered
	 * @return true if the food items are removed from the inventory; false
	 * if the food items were not removed because one or more required items
	 * were not contained in the inventory
	 */
	public boolean placeOrder(Entree entree) {
		//check if each item specified is there
		if(checkIfInInventory(entree)==true) {
			//remove items from inventory
			this.inventory.remove(entree.getFoodList());
			//get paid
			this.cash+=entree.getRetailValue();
			return true;
		}else {
			return false;
		}
	
	}

}
