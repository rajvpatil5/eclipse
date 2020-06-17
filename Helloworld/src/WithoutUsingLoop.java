import java.util.Scanner;

public class WithoutUsingLoop {
	static int i = 1;

	public static void printNumbers(int number) {
		if (i <= number) {
			System.out.println(i);
			i++;
			printNumbers(number);
		} else {
			return;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		printNumbers(n);
	}
}
