
public class Child1 extends Child implements It {

	public static void main(String[] args) {

		Child1 c = new Child1();
		c.m1();
		It.m1();
		c.m5();
	}

	void m5() {
		It.super.m4();
	}

	@Override
	public void m2() {
		// TODO Auto-generated method stub

	}
}
