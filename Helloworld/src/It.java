
public interface It {
	static void m1() {
		System.out.println("hello");
	}

	default void m4() {
		System.out.println("default m4");
	}

}
