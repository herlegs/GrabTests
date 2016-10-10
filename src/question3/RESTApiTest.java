package question3;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.html.InlineView;

import org.junit.Before;
import org.junit.Test;

import TestUtil.TestUtil;

public class RESTApiTest {
	private RESTApi api;
	private Map<String, Location> expected;
	
	@Before
	public void setUp(){
		api = new RESTApi();
		loadExpectedFromFile();
		generateExpectedData();
	}
	
	private void generateExpectedData(){
		CreateTestFileBatch batch = new CreateTestFileBatch();
		batch.generateExpectedData();
	}
	
	private void loadExpectedFromFile(){
		expected = new HashMap<String, Location>();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("expected.txt").getFile());
		try {
			Scanner in = new Scanner(file);
			while(in.hasNext()){
				String line = in.nextLine();
				List<String> list = TestUtil.decodeStringList(line);
				if(list.size() == 3){
					String search = list.get(0);
					double lat = Double.parseDouble(list.get(1));
					double lng = Double.parseDouble(list.get(2));
					Location location = new Location(lat, lng);
					expected.put(search, location);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***
	 * check search word is in expected list and search result is different from expected
	 * @return
	 */
	private boolean isWrongResult(String search, Location location){
		if(expected.containsKey(search)){
			Location expectedLocation = expected.get(search);
			if(!expectedLocation.equals(location)){
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void TestSimpleAddress() {
		String address = "singapore+279832";
		Location result = api.getLocation(address);
		if(isWrongResult(address, result)){
			fail("different from expected");
		}
	}

}
