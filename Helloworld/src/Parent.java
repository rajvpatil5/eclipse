
public class Parent {
	Parent() {
		this(20);
		System.out.println("0 arg parent");
	}

	Parent(int q) {
		System.out.println("1 arg parent" + q);
	}

	String parent1() {
		return "0";
	}

}
