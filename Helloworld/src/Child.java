
public abstract class Child extends Parent {
	Child() {
		this(30);
		System.out.println("0 arg child");
	}

	Child(int a) {
		System.out.println("1 arg child" + a);
	}

	abstract void m2();

	void m1() {
		System.out.println("m1");
	}

	public static void main(String[] args) {
		
	}

}
