import java.util.ArrayList;
import java.util.List;

public class DummyList {
	static List<Object> column1 = new ArrayList<Object>();

	public static void main(String args[]) {
		String data = "1970 Physics                   Hannes Alfven                                 Sweden                 Scientist";
		String update = data.toString().trim().replaceAll(" + ", " ");
		System.out.println(update);
		String[] arr = update.split(" ");
		for (int k = 0; k < 1; k++) {
			column1.add(arr[0]);
			System.out.println(column1.get(k));
		}
	}

}
