package question3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import TestUtil.TestUtil;

public class CreateTestFileBatch {
	
	public void generateExpectedData(){
		RESTApi api = new RESTApi();
		List<String> searchs = new ArrayList<String>(){
			{
				add("singapore+google");
				add("singapore+food");
				add("singapore+somewhere");
			}
		};
		List<String> encodeResultList = new ArrayList<String>();
		for(String search: searchs){
			Location location = api.getLocation(search);
			System.out.println(search + " is " + location.toString());
			encodeResultList.add(encodeLocationResult(search, location));
		}
		writeStringListToFile(encodeResultList);
	}
	
	private void writeStringListToFile(List<String> list){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("expected.txt").getFile());
		try {
			PrintWriter out = new PrintWriter(file);
			for(String s: list){
				out.println(s);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String encodeLocationResult(String search, Location location){
		List<String> list = new ArrayList<String>();
		list.add(search);
		list.add(String.valueOf(location.getLatitute()));
		list.add(String.valueOf(location.getLongitute()));
		return TestUtil.encodeStringList(list);
	}

}
