package DayToDayPractice;

public class DemoException {

	public static void main(String[] args) throws Exception {
		int a[] = { 1, 2, 3 };
		int b = 5;
		if (b == 5) {
			throw new UserDefineException("b is 5");
		}
		try {
			System.out.println(10 / 0);
		} catch (Exception e) {
			System.out.println("10");
			System.out.println();
			e.printStackTrace();
		}

	}

}
