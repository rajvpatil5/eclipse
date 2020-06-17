import java.util.TreeSet;

public class SortArrays {

	public static void main(String[] args) {
		int arr1[] = { 6, 7, 1, 4, 5, 8, 9, 2, 3, 11 };
		int arr2[] = { 4, 22, 6, 7, 9, 55, 1, 3, 68, 11 };
		TreeSet<Integer> tm = new TreeSet<Integer>();
		for (int i = 0; i < arr2.length; i++) {
			tm.add(arr2[i]);
			tm.add(arr1[i]);
		}
		System.out.println(tm);
	}
}
