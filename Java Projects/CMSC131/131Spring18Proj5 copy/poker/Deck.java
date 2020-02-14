package poker;


public class Deck {

	private Card[] cards;

	// make an array of type cards
	// two-dimensional array with four rows and thirteen columns
	public Deck() {

		this.cards = new Card[52];
		int counter = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 1; j <= 13; j++){
				cards[counter] = new Card(j, i);
				counter++;
			}
		}
	}
	//standard copy constructor
	// reference copy works because cards class is immutable
	public Deck(Deck other) {
		this.cards = other.cards;

	}
	//card getter
	public Card getCardAt(int position) {

		return this.cards[position];

	}
	//returns length of the array
	public int getNumCards() {
		return this.cards.length;

	}
	//split deck into two parts
	// return a card from each packet one at a time into a new array


	public void shuffle() {
		Card[] firstPart = new Card[(cards.length / 2)];
		Card[] secondPart = new Card[(cards.length / 2)];
		//Check if incoming array has an even or odd number of cards.
		if(firstPart.length + secondPart.length != cards.length){
			firstPart = new Card[firstPart.length + 1];
		}
		//make a shallow copy of the first packet
		for(int i = 0; i < firstPart.length; i++){
			firstPart[i] = cards[i];
		}
		//make a shallow copy for the second packet
		for(int i = 0; i < secondPart.length; i++){
			secondPart[i] = cards[i + firstPart.length];
		}

		int counter = 0;
		//from 0 to x, every other card will be from the first packet
		for(int i = 0; i < cards.length; i=i+2){
			this.cards[i] = firstPart[counter];
			counter++;
		}
		//reset counter after first packet is done.
		counter=0;
		//from 1 to x, every other card will be from the second packet
		for(int i = 1; i < cards.length; i =i+2){
			this.cards[i] = secondPart[counter];
			counter++;

		}
	}
	//take "position" number of cards and place them at the end of the deck
	public void cut(int position) {
		//create a large array to store leftover cards
		final int BIG_NUMBER = 1000;
		Card[] cutDeck = new Card[BIG_NUMBER];
		//get "cut" cards from original array and copy into the end of new array
		for(int i =0; i<position; i++) {
			cutDeck[i+cards.length] = cards[i];
		}
		//copy leftover cards into the beginning of the new array
		for(int i =position; i<cards.length; i++) {
			cutDeck[i-position] = cards[i];
		}
		//Bring back the cut cards and copy them onto end of leftover cards
		for(int i = cards.length - position; i < cards.length; i++){
			cutDeck[i] = cutDeck[i + position];
		}
		// reassign original arrays reference to new array reference
		for(int i=0; i<cards.length; i++) {
			cards[i] = cutDeck[i];
		}
	}


	public Card[] deal(int numCards) {
		//create an array for cards that are being dealt
		Card[] hand = new Card[numCards];
		//create an array for the deck - cards that have dealt
		Card[] smaller = new Card[cards.length - numCards];

		//copy not dealt cards onto "smaller" array
		for(int i = 0; i < smaller.length; i++){
			smaller[i] = this.cards[i + numCards];
		}
		//copy dealt cards into "hand" array
		for(int i = 0; i < numCards; i++){
			hand[i] = this.cards[i];
		}
		//assign the original array to refer to a new array with the updated
		//number of cards left in the deck
		this.cards = new Card[smaller.length];
		for(int i = 0; i < smaller.length; i++){
			cards[i] = smaller[i];
		}
		return hand;
	}
}
