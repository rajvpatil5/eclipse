import io.restassured.path.json.JsonPath;

public class Exercise {

	public static void main(String[] args) {
		String response = "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n" + "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "},\r\n" + "\r\n" + "\"courses\": [\r\n"
				+ "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Selenium Python\",\r\n" + "\r\n" + "\"price\": 50,\r\n"
				+ "\r\n" + "\"copies\": 6\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"Cypress\",\r\n" + "\r\n" + "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 45,\r\n"
				+ "\r\n" + "\"copies\": 10\r\n" + "\r\n" + "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}\r\n" + "\r\n" + "";
		JsonPath js = new JsonPath(response);

		// print number of courses return by api
		int courseSize = js.getInt("courses.size()");
		System.out.println(courseSize);

		System.out.println("====================");
		// print purchase amount
		int purchaseamt = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamt);

		System.out.println("====================");
		// title of first course
		String firstTitle = js.get("courses[0].title");
		System.out.println(firstTitle);

		System.out.println("====================");
		// print all course title and their respective prices
		for (int i = 0; i < courseSize; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			System.out.print(courseTitle + " - ");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println(price);
		}

		System.out.println("====================");
		// print number of copies sold by RPA course
		for (int i = 0; i < courseSize; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			if (courseTitle.equals("RPA")) {
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println(copies);
			}
		}

		System.out.println("====================");
		// verify all course price matches with purchase amount
		int priceOfAllCourses = 0;
		for (int i = 0; i < courseSize; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			int allCoursesSum = price * copies;
			priceOfAllCourses = priceOfAllCourses + allCoursesSum;
		}
		if (purchaseamt == priceOfAllCourses) {
			System.out.println("Course price amount is verified");
		} else {
			System.out.println("Unsuccessfull - Course orice missmatch");
		}

	}

}
