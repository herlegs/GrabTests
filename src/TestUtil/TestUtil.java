package TestUtil;

import java.util.List;

public class TestUtil {
	public static <E> boolean isSameListIgnoreOrder(List<E> a, List<E> b){
		return a.size() == b.size()
				&& a.containsAll(b) && b.containsAll(a);
	}
}
