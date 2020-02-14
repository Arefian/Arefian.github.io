import static org.junit.Assert.*;
import org.junit.Test;
import foodManagement.*;

public class PublicTests {

	private static final Food BACON = Food.FOOD_OBJECTS[0];
	private static final Food WAFFLE = Food.FOOD_OBJECTS[1];
	private static final Food EGG = Food.FOOD_OBJECTS[2];
	private static final Food OJ = Food.FOOD_OBJECTS[3];
	private static final Food MILK = Food.FOOD_OBJECTS[4];
	
	@Test
	public void testDefaultConstructorAndGetSize() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		assertTrue(list.getSize() == 0);
		assertEquals("[  ]", list.toString());
	}
	
	@Test
	public void testListSimpleAdd() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		assertEquals(Food.FOOD_OBJECTS.length, list.getSize());
		assertEquals("[ Bacon, Cereal, Coffee, Croissant, Donut, Egg, Hashbrowns, Melon, Milk, Orange Juice, Pancakes, Pie, Toast, Waffle ]",
				list.toString());
		
		list.add(BACON);
		list.add(WAFFLE);
		list.add(EGG);
		list.add(MILK);
		list.add(EGG);
		list.add(OJ);
		assertEquals(20, list.getSize());
		assertEquals("[ Bacon, Bacon, Cereal, Coffee, Croissant, Donut, Egg, Egg, Egg, Hashbrowns, Melon, Milk, Milk, " +
				"Orange Juice, Orange Juice, Pancakes, Pie, Toast, Waffle, Waffle ]" , list.toString());
	}	
	
	@Test
	public void testListSimpleAddList() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		assertEquals(Food.FOOD_OBJECTS.length, list.getSize());
		assertEquals("[ Bacon, Cereal, Coffee, Croissant, Donut, Egg, Hashbrowns, Melon, Milk, Orange Juice, Pancakes, Pie, Toast, Waffle ]",
				list.toString());
		SortedListOfImmutables secondlist = new SortedListOfImmutables();
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		list.add(secondlist);
		
		//Expected results is a life saver.
		assertEquals(28, list.getSize());
		assertEquals("[ Bacon, Bacon, Cereal, Cereal, Coffee, Coffee, Croissant, Croissant, Donut, Donut, Egg, Egg, Hashbrowns, Hashbrowns, Melon, Melon, Milk, Milk,"
				+ " Orange Juice, Orange Juice, Pancakes, Pancakes, Pie, Pie, Toast, Toast, Waffle, Waffle ]"  , list.toString());
	}	
	
	@Test
	public void testListSimpleRemove() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		
		list.remove(BACON);
		list.remove(WAFFLE);
		list.remove(EGG);
		assertEquals(11, list.getSize());
		assertEquals("[ Cereal, Coffee, Croissant, Donut, Hashbrowns, Melon, Milk, Orange Juice, Pancakes, Pie, Toast ]" , list.toString());
	}	
	@Test
	public void testCheckAvailibility() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		assertTrue(list.checkAvailability(BACON));
		
		
	}	
	@Test
	public void testListCheckAvailibility() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		SortedListOfImmutables listToCheck = new SortedListOfImmutables();

		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			listToCheck.add(Food.FOOD_OBJECTS[i]);
		}
		assertTrue(list.checkAvailability(listToCheck));
		
		
	}	
	@Test
	public void testListSimpleRemoveList() {
		SortedListOfImmutables list = new SortedListOfImmutables();
		SortedListOfImmutables listToremove = new SortedListOfImmutables();

		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			listToremove.add(Food.FOOD_OBJECTS[i]);
		}
		
		list.remove(listToremove);
		assertEquals(0, list.getSize());
		//assertEquals("[ Cereal, Coffee, Croissant, Donut, Hashbrowns, Melon, Milk, Orange Juice, Pancakes, Pie, Toast ]" , list.toString());
	}	
	@Test
	public void testgetWholeSale() {
		SortedListOfImmutables list = new SortedListOfImmutables();

		for (int i = Food.FOOD_OBJECTS.length - 1; i >= 0; i--) {
			list.add(Food.FOOD_OBJECTS[i]);
		}
		
		
		System.out.println(list.getWholesaleCost());
		System.out.println(list.getRetailValue());

		//assertEquals(0, list.getSize());
		//assertEquals("[ Cereal, Coffee, Croissant, Donut, Hashbrowns, Melon, Milk, Orange Juice, Pancakes, Pie, Toast ]" , list.toString());
	}	
}
