package com.sist.inner;

public class A {
	public String title="Hello";
	public void pro() {
		System.out.println("AŬ������ pro�Դϴ�.");
		
		//inner Ŭ���� B�� ��������� ���� �� �� ����.
		//System.out.println(year);

		//�����Ϸ��� ��ü�� �����ϰ� ��ü�� ���� ������ �� �ִ�.		
		B ob = new B();
		System.out.println(ob.year);	
		ob.test();
	}
	
	//innerŬ���� B
	class B{
		public int year=2021;
		public void test() {
			System.out.println("B�� test�Դϴ�.");
			//outter Ŭ���� A�� ������� title�� �����Ӱ� ������ �� �ִ�.
			System.out.println(title);
		}
	}
	
	public static void main(String[] args) {
		A a = new A();
		a.pro();
		
		//A.B b = new A.B();
	}
	
}
