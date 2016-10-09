package question2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 
 * @author Xiao
 * Assumption for GetAnagram:
 * palindrome word will be returned
 * if in list there is only "aba", "aba" will be returned
 * if exists both "aba" and "aba", only one "aba" will be returned
 * duplicated word will only be added to result once
 *
 */
public class GetWords {
	
	private boolean isWord(String s){
		if(s == null){
			return false;
		}
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}
	
	public List<String> getWords(String str){
		List<String> result = new ArrayList<>();
		if(str == null){
			return result;
		}
		String[] list = str.split("\\s+");
		for(String s: list){
			if(isWord(s)){
				result.add(s);
			}
		}
		return result;
	}
	
	private String reverse(String s){
		if(s == null){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--){
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
	/***
	 * Assumption for GetAnagram:
	 * palindrome word will be returned
	 * if in list there is only "aba", "aba" will be returned
	 * if exists both "aba" and "aba", only one "aba" will be returned
     * duplicated word will only be added to result once
	 */
	public List<String> getAnagrams(List<String> list){
		List<String> result = new ArrayList<String>();
		if(list == null){
			return result;
		}
		Set<String> set = new HashSet<String>();
		Set<String> duplicateCheck = new HashSet<String>();
		for(String s: list){
			//add to set first in case of palindrome string
			set.add(s);
			String reverse = reverse(s);
			if(set.contains(reverse) && !duplicateCheck.contains(s)){
				result.add(s);
				duplicateCheck.add(s);
				//palindrome will only be added once
				if(!reverse.equals(s)){
					result.add(reverse);
					duplicateCheck.add(reverse);
				}
			}
		}
		return result;
	}
}
