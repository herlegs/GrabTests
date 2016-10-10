package TestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtil {
	public static <E> boolean isSameListIgnoreOrder(List<E> a, List<E> b){
		return a.size() == b.size()
				&& a.containsAll(b) && b.containsAll(a);
	}
	
	public static String encodeStringList(String...strings){
		return encodeStringList(Arrays.asList(strings));
	}
	
	public static String encodeStringList(List<String> strings){
		StringBuilder sb = new StringBuilder();
		for(String s: strings){
			if(s != null){
				int len = s.length();
				sb.append(len + "/" + s);
			}
		}
		return sb.toString();
	}
	
	public static List<String> decodeStringList(String s){
		List<String> result = new ArrayList<String>();
		if(s == null){
			return result;
		}
		int begin = 0;
		int separatorPos = 0;
		while((separatorPos = s.indexOf("/", begin)) != -1){
			String lenString = s.substring(begin, separatorPos);
			int len = Integer.parseInt(lenString);
			String cur = s.substring(separatorPos + 1, separatorPos + 1 + len);
			result.add(cur);
			begin = separatorPos + 1;
		}
		return result;
	}
}
