package question3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import TestUtil.TestUtil;

public class CreateTestFileBatch {
	
	public void generateExpectedData(){
		RESTApi api = new RESTApi();
		List<String> searchs = new ArrayList<String>(){
			{
				add("singapore+google");
				add("singapore+food");
				add("singapore+somewhere");
				add("singapore+279832");
				add("random+279832");
				add("grab");
			}
		};
		List<String> encodeResultList = new ArrayList<String>();
		for(String search: searchs){
			Location location = api.getLocation(search);
			encodeResultList.add(encodeLocationResult(search, location));
		}
		writeStringListToFile(encodeResultList);
	}
	
	/***
	 * get the print out, and paste it into Resources File!
	 * @param list
	 */
	private void writeStringListToFile(List<String> list){
		list.forEach(System.out::println);
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource("expected.txt").getFile());
//		try {
//			PrintWriter out = new PrintWriter(file);
//			for(String s: list){
//				out.println(s);
//			}
//			out.flush();
//			out.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	private String encodeLocationResult(String search, Location location){
		List<String> list = new ArrayList<String>();
		list.add(search);
		list.add(String.valueOf(location.getLatitute()));
		list.add(String.valueOf(location.getLongitute()));
		return TestUtil.encodeStringList(list);
	}

}
