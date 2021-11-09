package com.sist.inner;

public class A {
	public String title="Hello";
	public void pro() {
		System.out.println("A클래스의 pro입니다.");
		
		//inner 클래스 B의 멤버변수에 접근 할 수 없다.
		//System.out.println(year);

		//접근하려면 객체를 생성하고 객체를 통해 접근할 수 있다.		
		B ob = new B();
		System.out.println(ob.year);	
		ob.test();
	}
	
	//inner클래스 B
	class B{
		public int year=2021;
		public void test() {
			System.out.println("B의 test입니다.");
			//outter 클래스 A의 멤버변수 title에 자유롭게 접근할 수 있다.
			System.out.println(title);
		}
	}
	
	public static void main(String[] args) {
		A a = new A();
		a.pro();
		
		//A.B b = new A.B();
	}
	
}
