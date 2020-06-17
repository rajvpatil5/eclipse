package DayToDayPractice;

public class DemoString {

	public static void main(String[] args) {
		String s1 = "hi";
		String s2 = "hi";
		String s5 = "RajatPatil";
		System.out.println(s1 == s2);
		String s3 = new String("hi");
		String s4 = new String("hi");
		System.out.println(s3 == s4);
		System.out.println(s5.substring(2,5));
		System.out.println(s1.equals(s2));
		System.out.println(s3.equals(s4));
		System.out.println("================");
		StringBuffer sb1 = new StringBuffer("hi");
		StringBuffer sb2 = new StringBuffer("hi");
		System.out.println(sb1.toString().equals(sb2.toString()));
		System.out.println(sb1 == sb2);
		System.out.println(sb1.equals(sb2));
	}

}
