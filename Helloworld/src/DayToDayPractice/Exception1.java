package DayToDayPractice;

public class Exception1 {

	public static void main(String[] args) {
		int i = 99;
		try {
			System.out.println(10 / 0);
		} catch (Exception e) {
			System.out.println(i);
		} finally {
			System.out.println(i);
		}

	}
}