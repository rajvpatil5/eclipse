import java.util.Scanner;

public class AllDigitSum {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Number: ");
		int n = scan.nextInt();
		int sum = 0;
		while (n != 0) {
			int digits = n % 10;
			sum = digits + sum;
			n = n / 10;
		}
		System.out.println("Sum of all digits is: " + sum);
		scan.close();
	}

}
