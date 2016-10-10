package question1;

import static org.junit.Assert.*;

import java.util.List;import javax.naming.spi.DirStateFactory.Result;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import TestUtil.TestUtil;

public class ListIntersectionTest {
	private ListIntersection intersection;

	@Before
	public void setUp() throws Exception {
		intersection = new ListIntersection();
	}
	
	@Test
	public void testInputNullCase(){
		List<String> a = null;
		List<String> b = null;
		try{
			intersection.bruteForceSearch(a, b);
		}
		catch (Exception e) {
			fail("should have handled input is null case");
		}
	}
	
	@Test
	public void testStringNullCase(){
		List<String> aList = new ArrayList<>();
		List<String> bList = new ArrayList<>();
		aList.add(null);
		aList.add(null);
		aList.add("a");
		bList.add(null);
		bList.add(null);
		bList.add("b");
		List<String> expected = new ArrayList<>();
		expected.add(null);
		try{
			List<String> bruteForceResult = intersection.bruteForceSearch(aList, bList);
			List<String> hashSearchResult = intersection.hashSetSearch(aList, bList);
			//test contains same elements with expected ignore order
			assertTrue(TestUtil.isSameListIgnoreOrder(expected, bruteForceResult));
			assertTrue(TestUtil.isSameListIgnoreOrder(expected, hashSearchResult));
		}
		catch (Exception e) {
			fail("should have handled string is null case");
		}
	}
	
	@Test
	public void testFunctionality(){
		List<String> aList = new ArrayList<>();
		List<String> bList = new ArrayList<>();
		for(int i = 0; i <= 100; i++){
			aList.add("test"+i);
		}
		for(int i = 150; i >= 50; i--){
			bList.add("test"+i);
		}
		List<String> expected = new ArrayList<>();
		for(int i = 50; i <= 100; i++){
			expected.add("test"+i);
		}
		List<String> bruteForceResult = intersection.bruteForceSearch(aList, bList);
		List<String> hashSearchResult = intersection.hashSetSearch(aList, bList);
		//test contains same elements with expected ignore order
		assertTrue(TestUtil.isSameListIgnoreOrder(expected, bruteForceResult));
		assertTrue(TestUtil.isSameListIgnoreOrder(expected, hashSearchResult));
	}

	/***
	 * this test may take around 20 seconds
	 */
	@Test
	public void testPerformance() {
		List<String> aList = new ArrayList<>();
		List<String> bList = new ArrayList<>();
		for(int i = 0; i <= 30000; i++){
			aList.add("test"+i);
		}
		for(int i = 50000; i >= 50; i--){
			bList.add("test"+i);
		}

		long timestamp = System.currentTimeMillis();
		intersection.bruteForceSearch(aList, bList);
		long bruteForceTime = System.currentTimeMillis() - timestamp;
		
		timestamp = System.currentTimeMillis();
		intersection.hashSetSearch(aList, bList);
		long hashSearchTime = System.currentTimeMillis() - timestamp;
		
		System.out.println("brute force uses time: " + (double)bruteForceTime/1000 + "s");
		System.out.println("hash search uses time: " + (double)hashSearchTime/1000 + "s");
	}

}
