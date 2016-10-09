package question1;

/**
 * Created by Xiao on 9/10/2016.
        */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * give two list of string, find their intersection
 * Assumption:
 * duplicate item should be added into result once only
 */
public class ListIntersection {

    /***
     * using brute force
     * time complexity O(n^2)
     * space complexity O(1)
     * @param a
     * @param b
     * @return intersection of a and b
     */
    public List<String> bruteForceSearch(List<String> a, List<String> b){
        List<String> result = new ArrayList<String>();
        if(a == null || b == null){
            return result;
        }
        a.forEach(s -> {
            if(b.contains(s) && !result.contains(s)){
                result.add(s);
            }
        });
        return result;
    }

    /***
     * using hash set
     * time complexity O(n + m)
     * space complexity O(min(n,m))
     * @param a
     * @param b
     * @return intersection of a and b
     */
    public List<String> hashSetSearch(List<String> a, List<String> b){
        List<String> result = new ArrayList<String>();
        if(a == null || b == null){
            return result;
        }
        Set<String> set = new HashSet<String>();
        //save smaller list into set to save space
        List<String> biggerList = a.size() > b.size() ? a : b;
        List<String> smallerList = a.size() > b.size() ? b : a;
        smallerList.forEach(s -> set.add(s));
        Set<String> duplicateCheck = new HashSet<>();
        biggerList.forEach(s -> {
            if(set.contains(s) && !duplicateCheck.contains(s)){
                result.add(s);
                duplicateCheck.add(s);
            }
        });
        return result;
    }
}
