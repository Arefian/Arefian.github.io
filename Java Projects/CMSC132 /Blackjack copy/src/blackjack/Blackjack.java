package blackjack;
import java.util.*;

public class Blackjack implements BlackjackEngine {
	
    private int playerAccount;
    private int bet; 
    private Random randomGenerator;
    private int numberOfDecks;
    private ArrayList<Card> gameDeck;
    private ArrayList<Card> dealerHand;
    private ArrayList <Card> playerHand;
    private final int ACE_CHECK = 10;
    private int gameStatus;
    

	/**
	 * Constructor you must provide.  Initializes the player's account 
	 * to 200 and the initial bet to 5.  Feel free to initialize any other
	 * fields. Keep in mind that the constructor does not define the 
	 * deck(s) of cards.
	 * @param randomGenerator
	 * @param numberOfDecks
	 */
	public Blackjack(Random randomGenerator, int numberOfDecks) {
			this.playerAccount = 200;
			this.bet  = 5;
			this.randomGenerator = randomGenerator;
			this.numberOfDecks = numberOfDecks;
	}
	
	public int getNumberOfDecks() {
		return this.numberOfDecks;
		
	}
	
	public void createAndShuffleGameDeck() {
		gameDeck = new ArrayList <Card>();
		//create an array of type cards to hold the deck
		for(int i =0; i<this.numberOfDecks; i++) {
			//number of decks will decide number of loops
			for(CardSuit suit: CardSuit.values()) {
				//go through all suits
			for(CardValue value : CardValue.values()){
				//go through all values
				gameDeck.add(new Card(value, suit));
				//incrementally add each card to create deck
		
			}
			}	
		}
		Collections.shuffle(gameDeck, this.randomGenerator);
		//shuffle the deck after it is complete
		
	}
	
	public Card[] getGameDeck() {
		Card getGameDeckArray[] = new Card[gameDeck.size()];
		//create an empty array with size equal to gamedeck
		return this.gameDeck.toArray(getGameDeckArray);
		//use the toArray method to convert then return an array of the deck
	}
	
	public void deal() {	
		
		createAndShuffleGameDeck();
		this.playerHand = new ArrayList<Card>();
		this.dealerHand = new ArrayList<Card>();

		this.playerHand.add(this.gameDeck.get(0));
		this.gameDeck.remove(0);
		//51 cards
		//System.out.println(this.gameDeck.size());
		Card dealerFirstCard = this.gameDeck.get(0);
		this.gameDeck.remove(0);
		dealerFirstCard.setFaceDown();
		this.dealerHand.add(dealerFirstCard);
		
		this.playerHand.add(this.gameDeck.get(0));
		this.gameDeck.remove(0);
		
		this.dealerHand.add(this.gameDeck.get(0));
		this.gameDeck.remove(0);
		setAccountAmount(this.playerAccount -= this.bet);
		this.gameStatus = BlackjackEngine.GAME_IN_PROGRESS;
	}
		
	
		
	public Card[] getDealerCards() {
		//yeet
		
		Card[] dealerCards = new Card[this.dealerHand.size()];
		return dealerHand.toArray(dealerCards);
		
	}

	public int[] getDealerCardsTotal() {
		
	return this.CardsTotal(dealerHand);
					
	}

	public int getDealerCardsEvaluation() {
	return getCardsEvaluation(getDealerCardsTotal(), this.dealerHand);
	
	}
	public Card[] getPlayerCards() {
		Card[] playerCards = new Card[this.playerHand.size()];
		return playerHand.toArray(playerCards);
		
	}
	
	public int[] getPlayerCardsTotal() {
		return this.CardsTotal(playerHand);
	}
		
	public int getPlayerCardsEvaluation() {
		return getCardsEvaluation(getPlayerCardsTotal(), this.playerHand);
		
		}
	
	
	public void playerHit() {
	this.playerHand.add(this.gameDeck.remove(0));
		//this.playerHand.add(playerHand.size()-1, this.gameDeck(0));	
	if(this.getPlayerCardsEvaluation() == BUST) {
		this.gameStatus= DEALER_WON;
	}else {
		this.gameStatus =GAME_IN_PROGRESS;
	}
	}
	
	public void playerStand() {
		/**
		 * Flips the dealer's card that is currently face down 
		 * and assigns cards to the dealer as long as the dealer 
		 * doesn't bust and the cards have a value less than 16.  Once the dealer
		 * has a hand with a value greater than or equal to 16, and less than or equal to 21, 
		 * the hand will be compared against the player's hand and whoever has the
		 * hand with a highest value will win the game. If both have the same value 
		 * we have a draw.  The game's status will be updated to one of
		 * the following values: DEALER_WON, PLAYER_WON, or DRAW.  The player's
		 * account will be updated with a value corresponding to twice the bet amount if 
		 * the player wins.  If there is a draw the player's account will be updated
		 * with the only the bet amount. 
		 *
		 */
		this.dealerHand.get(0).setFaceUp();
		int tempdealer[] =getDealerCardsTotal();
		int tempPlayer[] = getPlayerCardsTotal();
		while(tempdealer[tempdealer.length - 1] < 16) {
			tempdealer = getDealerCardsTotal();
			if(tempdealer == null || tempdealer[tempdealer.length - 1] >= 16) {
				break;
			} else {
				this.dealerHand.add(this.gameDeck.remove(0));
			}
		}
		
		if(tempdealer == null ) {
			this.gameStatus = PLAYER_WON;
		}
		else if (tempPlayer == null) {
			this.gameStatus = DEALER_WON;
		}
		else if(tempdealer[tempdealer.length-1] > tempPlayer[tempPlayer.length-1]) {
			this.gameStatus = DEALER_WON;
		}else if(tempdealer[tempdealer.length-1] < tempPlayer[tempPlayer.length-1]) {
			this.gameStatus = PLAYER_WON;
			this.playerAccount += (this.bet * 2) ;
		}else if(tempdealer[tempdealer.length-1] == tempPlayer[tempPlayer.length-1]) {
			this.gameStatus = DRAW;
		}
	} 
	

	public int getGameStatus() {
		return this.gameStatus;
	}
		
	public void setBetAmount(int amount) {
		this.bet = amount;
	}
	
	public int getBetAmount() {
		return this.bet;
	}
	
	public void setAccountAmount(int amount) {	
		this.playerAccount = amount;
	}
	
	public int getAccountAmount() {
		return this.playerAccount;
	}
	
	/* Feel Free to add any private methods you might need */


	public int[] CardsTotal(ArrayList<Card> hand) {
		boolean aceDrawn = false;
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() == CardValue.Ace){
				aceDrawn = true;
			}
		}
		int AceIsOne = 0, AceIsEleven = 0, NoAces = 0;
		if(aceDrawn) {
			for(int index = 0; index < hand.size(); index++) {
				AceIsOne += hand.get(index).getValue().getIntValue();
			}
			AceIsEleven = AceIsOne + 10;
			if(AceIsOne > 21){
				return null;
			} else if(AceIsOne <= 21 && AceIsEleven > 21) {
				int[] total = {AceIsOne};
				return total;
			} else {
				int[] total = {AceIsOne, AceIsEleven};
				return total;
			}
		} else {
			for(int j = 0; j < hand.size(); j++) {
				NoAces += hand.get(j).getValue().getIntValue();
			}
			if(NoAces > 21) {
				return null;
			}
			int[] answer = {NoAces};
			System.out.println(NoAces);
			return answer;
			
		}
	}
	private int getCardsEvaluation(int[] handTotal, ArrayList<Card> hand) {
		if(handTotal == null) {
			return BlackjackEngine.BUST;
		} else if(handTotal.length == 2) {
			if(handTotal[1] == 21) {
				if(hand.size() > 2) {
					return BlackjackEngine.HAS_21;
				} else {
					return BlackjackEngine.BLACKJACK;
				}
			} 
		} else {
			if(handTotal[0] == 21) {
				if(hand.size() > 2) {
					return BlackjackEngine.HAS_21;
				} else {
					return BlackjackEngine.BLACKJACK;
				}
			}
		}
		return BlackjackEngine.LESS_THAN_21;
	}	
	}
	
				