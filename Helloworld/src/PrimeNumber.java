import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		List<Integer> prime = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter No should be > 3:");
		int n = scan.nextInt();
		for (int i = 1; i <= n + 1; i++) {
			int counter = 0;
			for (int num = i; num >= 1; num--) {
				if (i % num == 0) {
					counter = counter + 1;
				}
			}
			if (counter == 2) {
				prime.add(i);
			}
		}
		for (int i = 0; i < prime.size(); i++) {
			for (int j = i; j < prime.size(); j++) {
				if (prime.get(i) + prime.get(j) == n) {
					System.out.println("N1 - " + prime.get(i) + ", " + "N2 - " + prime.get(j));
				}
			}
		}
		scan.close();
	}
}
