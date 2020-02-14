package foodManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	/*
	 * STUDENTS:  You may NOT add any other instance variables to this class!
	*/
	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	public SortedListOfImmutables() {
		this.items =new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		//these lists are immutable, so we can make reference copies
		this.items = new Listable[other.getSize()];
		for(int i =0; i<other.getSize(); i++) {
		 this.items[i] = other.get(i);
		}
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {
		return this.items.length;
	}
	
	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		//item thats at the specified index
	return this.items[i];
	}
	
	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 * 
	 * In order to accomodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
		//create a new array that holds one more value
		Listable placeHolder[] = new Listable[this.items.length+1];
		boolean checker = false;
		//copy all the items so we can manipulate values
		for(int i =0; i <items.length; i++) {
			placeHolder[i] = items[i];
		}
		if(items.length ==0) {
			//if the list is empty, place the item in the first index
			placeHolder[0] = itemToAdd;
		}else {
			for(int i =0; i<items.length; i++) {

				if(checker == false) {
					if(itemToAdd.getName().compareTo(items[i].getName())<0){
						//check that item being added goes into right index
						placeHolder[i] = itemToAdd;
						for(int j = i+1; j<placeHolder.length; j++ ) {
							//shift the rest of the items by one
							placeHolder[j] = items[j-1];
						}
						checker=true;
					}

				}
			}
			if(checker == false) {
				//if after checking all placeHolder index and itemToAdd
				//is bigger than everything else, add it to the end of list
				placeHolder[placeHolder.length-1] = itemToAdd;
			}

		}
		//refer back to the original array
		items=placeHolder;		
		

	}

	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
	
		//do add method for each item of incoming list
		for(int i = 0; i <listToAdd.items.length; i ++) {
		this.add(listToAdd.items[i]);
			
		}
	}
	
	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
	
		Listable placeHolder[] = new Listable[items.length];
		//create copy of current items list
		for(int i = 0; i < placeHolder.length; i++) {
			placeHolder[i]=items[i];
			}
		
		
		boolean checker = false;
		for(int i=0; i<items.length; i++) {
			if(checker==false && items[i].getName().equals(itemToRemove.getName())) {
				//check if item thats needs to be removed is in the current list
				checker = true;	
				 placeHolder[i] = null;
				 //if item is found, set value of the index to null
				
			}
		}
		
		if(checker == true) {
			this.items = new Listable[placeHolder.length-1];
			//if checker is true create new list with smaller size
			int index = 0;	
			for(int i = 0; i < placeHolder.length; i++) {	
				if(placeHolder[i] != null) {
					//check each item in list, if the item value is null
					//skip the item, if item is not null, put in smaller
					//list.
					this.items[index++] = placeHolder[i];	
				}
			}
		}
			
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		
		//use remove method for each item in incoming list
		for(int i = 0; i <listToRemove.items.length; i ++) {
			this.remove(listToRemove.items[i]);
				
			}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
	int holder = 0;
		
		//add all items in list one by one
		for(int i =0; i<this.items.length; i++) {
		int wholeSale = items[i].getWholesaleCost() + holder;
			holder = wholeSale;
			//wholesale variable gets updated each index
		}
		
		return holder;
	
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {

		int holder = 0;
		//add all items in list one by one
			for(int i =0; i<this.items.length; i++) {
			int wholeSale = items[i].getRetailValue() + holder;
				holder = wholeSale;
				//wholesale variable gets updated each index

			}
			
			return holder;
		
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		
		
		for(int i = 0; i < this.items.length; i ++) {
			if( this.items[i].equals(itemToFind)){
				//check if value at any index equals incoming item
				return true;
			}
		}
		return false;
		
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		
		SortedListOfImmutables placeHolder=new SortedListOfImmutables(this);
		//create a copy of the data
		boolean checker=false;
		for(int i=0;i<listToCheck.items.length;i++){
			if(placeHolder.checkAvailability(listToCheck.items[i])==false){
				//check if all the items are there, if there not return false
				checker=false;
				break;
				
			}else{
				checker=true;
				//if all items are there, check that there are no
				//copies in the data set
				for(int j=0;j<placeHolder.items.length;j++){
					if(placeHolder.items[j].equals(listToCheck.items[i])){
						placeHolder.remove(placeHolder.items[j]);
						//if duplicate is found, remove it from items
						break;
					}
				}
			}
		}
		return checker;
	}

	/*
	 * We'll give you this one for free.  Do not modify this method or you
	 * will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
