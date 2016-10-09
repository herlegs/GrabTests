package question2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestUtil.TestUtil;

public class GetWordsTest {
	private GetWords getWords;

	@Before
	public void setUp() throws Exception {
		getWords = new GetWords();
	}
	
	private List<String> formList(String...strings){
		List<String> list = new ArrayList<String>();
		for(String string : strings){
			list.add(string);
		}
		return list;
	}

	@Test
	public void testGetWords() {
		List<String> result = getWords.getWords(" a b dde ade ");
		List<String> expected = formList("a", "b", "dde", "ade");
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
	}
	
	@Test
	public void testGetWordsEdgeCases() {
		List<String> result = getWords.getWords("");
		List<String> expected = formList();
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
		
		result = getWords.getWords("   ");
		expected = formList();
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
		
		result = getWords.getWords(null);
		expected = formList();
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
		
		result = getWords.getWords("#d word !notword isWord ....null ");
		expected = formList("word", "isWord");
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
	}
	
	@Test
	public void testGetAnagram(){
		List<String> test = formList("a", "bac", "cab", "grab", "barg", "test");
		List<String> result = getWords.getAnagrams(test);
		List<String> expected = formList("a", "cab", "bac", "grab", "barg");
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
	}
	
	@Test
	public void testGetAnagramEdgeCases(){
		List<String> test = null;
		List<String> result = null;
		try{
			result = getWords.getAnagrams(test);
		}
		catch (Exception e) {
			fail("should handle null input");
		}
		List<String> expected = formList();
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
	}
	
	@Test
	public void testGetAnagramDuplicate(){
		List<String> test = formList("a", "bac", "cab", "grab", "barg", "test", "grab", "barg");
		List<String> result = getWords.getAnagrams(test);
		List<String> expected = formList("a", "cab", "bac", "grab", "barg");
		assertTrue(TestUtil.isSameListIgnoreOrder(result, expected));
	}

}
