package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	/* Use the @Test annotation for JUnit 4 
	 * [This is just an example, please erase
	 * it and write some real tests.]    */
	@Test
	public void test() {

		Deck a = new Deck();
		assertTrue(a.getNumCards() == 52);
		Deck b = a;
		assertTrue(b.equals(a));

	}
	@Test
	public void testpair() {

		Card shouldPass1[] = new Card[5];
		shouldPass1[0] = new Card(13,1);
		shouldPass1[1] = new Card(13,0);
		shouldPass1[2] = new Card(3,2);
		shouldPass1[3] = new Card(9,3);
		shouldPass1[4] = new Card(7,2);
		assertTrue(PokerHandEvaluator.hasPair(shouldPass1));
		Deck shouldPass = new Deck();
		shouldPass.shuffle();
		assertTrue(PokerHandEvaluator.hasPair(shouldPass.deal(5)));
		Deck shouldFail = new Deck();
		assertFalse(PokerHandEvaluator.hasPair(shouldFail.deal(5)));

	}

	@Test
	public void testtriple() {

		Deck shouldPass = new Deck();
		shouldPass.shuffle();
		shouldPass.shuffle();
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(shouldPass.deal(5)));
		Deck shouldFail = new Deck();
		assertFalse(PokerHandEvaluator.hasThreeOfAKind(shouldFail.deal(5)));

	
}
	@Test
	public void testFlush() {

		Deck shouldPass = new Deck();
		assertTrue(PokerHandEvaluator.hasFlush(shouldPass.deal(5)));
		Deck shouldFail = new Deck();
		shouldFail.shuffle();
		assertFalse(PokerHandEvaluator.hasFlush(shouldFail.deal(5)));

	
}

	@Test
	public void testFourOfAKind() {

		Card shouldPass[] = new Card[5];
		shouldPass[0] = new Card(13,1);
		shouldPass[1] = new Card(13,0);
		shouldPass[2] = new Card(13,2);
		shouldPass[3] = new Card(13,3);
		shouldPass[4] = new Card(7,2);
		Card shouldFail[] = new Card[5];
		shouldFail[0] = new Card(9,1);
		shouldFail[1] = new Card(7,0);
		shouldFail[2] = new Card(7,2);
		shouldFail[3] = new Card(2,3);
		shouldFail[4] = new Card(2,3);
		
		
		assertTrue(PokerHandEvaluator.hasFourOfAKind(shouldPass));
		assertFalse(PokerHandEvaluator.hasFourOfAKind(shouldFail));

	
}
	@Test
	public void testFullHouse() {

		Card shouldPass[] = new Card[5];
		shouldPass[0] = new Card(7,1);
		shouldPass[1] = new Card(7,0);
		shouldPass[2] = new Card(7,2);
		shouldPass[3] = new Card(2,3);
		shouldPass[4] = new Card(2,3);
		
		Card shouldFail[] = new Card[5];
		shouldFail[0] = new Card(9,1);
		shouldFail[1] = new Card(7,0);
		shouldFail[2] = new Card(7,2);
		shouldFail[3] = new Card(2,3);
		shouldFail[4] = new Card(2,3);
		assertTrue(PokerHandEvaluator.hasFullHouse(shouldPass));
		
		assertFalse(PokerHandEvaluator.hasFullHouse(shouldFail));

	
}

	
	@Test
	public void testtwopair() {

		Card shouldPass[] = new Card[5];
		shouldPass[0] = new Card(13,1);
		shouldPass[1] = new Card(13,0);
		shouldPass[2] = new Card(2,2);
		shouldPass[3] = new Card(2,3);
		shouldPass[4] = new Card(7,2);
		Card shouldFail[] = new Card[5];
		shouldFail[0] = new Card(9,1);
		shouldFail[1] = new Card(7,0);
		shouldFail[2] = new Card(1,2);
		shouldFail[3] = new Card(2,3);
		shouldFail[4] = new Card(2,3);
		
		assertTrue(PokerHandEvaluator.hasTwoPair(shouldPass));
		assertFalse(PokerHandEvaluator.hasTwoPair(shouldFail));
 
	
}
	@Test
	public void testStraight() {

		Card shouldPass[] = new Card[5];
		shouldPass[0] = new Card(10,1);
		shouldPass[1] = new Card(11,0);
		shouldPass[2] = new Card(13,2);
		shouldPass[3] = new Card(12,3);
		shouldPass[4] = new Card(1,2);
		Card shouldFail[] = new Card[5];
		shouldFail[0] = new Card(1,1);
		shouldFail[1] = new Card(13,0);
		shouldFail[2] = new Card(2,2);
		shouldFail[3] = new Card(12,3);
		shouldFail[4] = new Card(11,3);
		
		assertTrue(PokerHandEvaluator.hasStraight(shouldPass));
		assertFalse(PokerHandEvaluator.hasStraight(shouldFail));

	
}
	@Test
	public void testStraightFlush() {

		Card shouldPass[] = new Card[5];
		shouldPass[0] = new Card(7,1);
		shouldPass[1] = new Card(6,1);
		shouldPass[2] = new Card(8,1);
		shouldPass[3] = new Card(5,1);
		shouldPass[4] = new Card(9,1);
		Card shouldFail[] = new Card[5];
		shouldFail[0] = new Card(11,1);
		shouldFail[1] = new Card(10,0);
		shouldFail[2] = new Card(12,2);
		shouldFail[3] = new Card(1,3);
		shouldFail[4] = new Card(13,3);
		Card shouldFail1[] = new Card[5];
		shouldFail[0] = new Card(2,1);
		shouldFail[1] = new Card(3,0);
		shouldFail[2] = new Card(4,2);
		shouldFail[3] = new Card(5,3);
		shouldFail[4] = new Card(6,3);
		
		assertTrue(PokerHandEvaluator.hasStraightFlush(shouldPass));
		assertFalse(PokerHandEvaluator.hasStraightFlush(shouldFail));
	}
	
}