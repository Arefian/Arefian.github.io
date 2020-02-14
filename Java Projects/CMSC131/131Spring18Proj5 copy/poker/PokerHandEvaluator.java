package poker;
public class PokerHandEvaluator {

	public static boolean hasPair(Card[] cards) {
		int Deck[] = new int[13];
		for(int i = 0; i<cards.length; i++) {
			//for each card, increment the deck array index[value-1]
			Deck[cards[i].getValue()-1] = Deck[cards[i].getValue()-1] + 1;
		}
		// if any one index of Deck[] is bigger than one its a pair
		for( int i = 0; i < Deck.length; i++) {
			if (Deck[i]>1) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasTwoPair(Card[] cards) {
		int Deck[] = new int[13];
		for(int i = 0; i<cards.length; i++) {
			//for each card, increment the deck array index[value-1]
			Deck[cards[i].getValue()-1] = Deck[cards[i].getValue()-1]+ 1;
		}
		//here, the for loop checks the Deck[] array for at least two values
		// of [i] that are bigger than one, for each value of [i] that is >1
		// counter is incremented, then counter is checked.
		int checker = 0;
		for( int i = 0; i < Deck.length; i++) {
			if (Deck[i]>1) {
				checker++;
			}
			if (checker==2) {
				return true;
			}
		}

		return false;
	}	



	public static boolean hasThreeOfAKind(Card[] cards) {
		int Deck[] = new int[13];
		for(int i = 0; i<cards.length; i++) {
			//for each card, increment the deck array index[value-1]
			Deck[cards[i].getValue()-1] = Deck[cards[i].getValue()-1] + 1;
		}
		// if any one index of Deck[] is bigger than two its three of a kind
		for( int i = 0; i < Deck.length; i++) {
			if (Deck[i]>2) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasStraight(Card [] cards) {
		
		// Sort the incoming array from least valued cards to highest valued
	
		Card temp;				
		for(int i = 0; i < cards.length; i++){					
			for (int j = 0; j < (cards.length - 1); j++){
				if (cards[j + 1].getValue() < cards[j].getValue()){
					temp = cards[j];	
					cards[j] = cards[j + 1];
					cards[j + 1] = temp;
							
				}
			}
		} 
		// create deck with 13 usable indexes for an extra ace
		
		int Deck[] = new int[14];

		if(cards[0].getValue() == 1 && cards[4].getValue()==13) {
			Deck[0] = 0;
			Deck[13] = 1;
			//if both the ace and king are present, ignore Deck[0]=1;
			for(int i = 1; i<cards.length; i++) {
				//for each card, increment the deck array index[value-1]
				Deck[cards[i].getValue()-1] = Deck[cards[i].getValue()-1] + 1;

			}
		}
		//if ace and king are not present simultaneously 
		else {
			for(int i = 0; i<cards.length; i++) {
				//for each card, increment the deck array index[value-1]
				Deck[cards[i].getValue()-1] = Deck[cards[i].getValue()-1] + 1;

			}

		}
		//check to see if there are any five consecutive indexes with a 1
		int counter =0;
		for(int i=0; i<Deck.length-1; i++) {
		//arrays are auto-filled with (0)'s, so avoid qualifying a pair of (0) 
			if(Deck[i] == Deck[i+1] && Deck[i] != 0) {
				counter++;
			}

		}
		if(counter>=4) {

			return true;

		}
		return false;
	}


	public static boolean hasFlush(Card[] cards) {
		int Deck[] = new int[4];
		for(int i = 0; i<cards.length; i++) {
			//for each card, increment the deck array index[suit]
			Deck[cards[i].getSuit()] = Deck[cards[i].getSuit()]+ 1;
		}
		//if in the array Deck[i], if any [i] >4, then there is five of the
		// same suits.
		for( int i = 0; i < Deck.length; i++) {
			if (Deck[i]>4) {
				return true;
			}
		}
		return false;
	}



	public static boolean hasFullHouse(Card[] cards) {
		//we need at least two distinct pairs

		if(hasTwoPair(cards) == true && hasThreeOfAKind(cards) == true) {
			return true;
		}
		return false;
	}

	public static boolean hasFourOfAKind(Card[] cards) {
		int Deck[] = new int[13];
		for(int i = 0; i<cards.length; i++) {
			//for each card, increment the deck array index[value-1]
			Deck[cards[i].getValue()-1] = Deck[cards[i].getValue()-1]+ 1;
		}
		// if any one index of Deck[] is bigger than three its four of a kind
		for( int i = 0; i < Deck.length; i++) {
			if (Deck[i]>3) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasStraightFlush(Card[] cards) {

		if(hasStraight(cards) == true && hasFlush(cards)==true) {
			return true;
		}
		return false;
	}
	
	
}




