package com.sist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AllStudent  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String sql ="select * from student";
			//1.jdbc����̹��� �޸𸮷� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.db������ �����Ѵ�.
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
		
			//3.Statement ��ü ����
			Statement stmt = conn.createStatement();
			
			//4.�����ͺ��̽� ����� �����Ѵ�.
			ResultSet rs = stmt.executeQuery(sql);
			
			//5.���ڵ尡 �ִ� ��ŭ Ŀ���� �� �྿ �ű��
			while(rs.next()) {
				String name = rs.getString(1);
				int kor = rs.getInt(2);
				int eng = rs.getInt(3);
				int math = rs.getInt(4);
				System.out.println(name+","+kor+","+eng+","+math);
			}
			
			//6.����ߴ� �ڿ����� �ݾ��ش�.
			rs.close();
			stmt.close();
			conn.close();			
			
		} catch (Exception e) {
			
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
	}

}
