package com.sist.db;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//이프로그램을 gui로 만들어보기
//모든 학생의 목록을 텍스트에리어에 출력하자
//나머지 화면구성은 자유롭게

public class AllStudentGUI extends JFrame {
	
	JTextArea jta;
	
	public AllStudentGUI() {
		jta = new JTextArea();
		JButton btn = new JButton("출력");
		add(btn, BorderLayout.SOUTH);
		add(jta);
		
		setSize(400,300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
				String sql ="select * from student";
				//1.jdbc드라이버를 메모리로 로드한다.
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2.db서버에 연결한다.
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
			
				//3.Statement 객체 생성
				Statement stmt = conn.createStatement();
				
				//4.데이터베이스 명령을 실행한다.
				ResultSet rs = stmt.executeQuery(sql);
				
				String data = null;
				//5.레코드가 있는 만큼 커서를 한 행씩 옮긴다
				while(rs.next()) {
					String name = rs.getString(1);
					int kor = rs.getInt(2);
					int eng = rs.getInt(3);
					int math = rs.getInt(4);
					data = name+","+kor+","+eng+","+math+"\n";
					System.out.println(data);
					//System.out.println(name+","+kor+","+eng+","+math);
					jta.append(data.trim() + "\n");
				}
				jta.setText(data);
				
				//6.사용했던 자원들을 닫아준다.
				rs.close();
				stmt.close();
				conn.close();			
				
			} catch (Exception ex) {
				
				System.out.println("예외발생:"+ex.getMessage());
			}
				
			}			
		});

	}
	

	public static void main(String[] args) {
		new AllStudentGUI();	
	}

}
