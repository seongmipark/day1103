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

//�����α׷��� gui�� ������
//��� �л��� ����� �ؽ�Ʈ����� �������
//������ ȭ�鱸���� �����Ӱ�

public class AllStudentGUI extends JFrame {
	
	JTextArea jta;
	
	public AllStudentGUI() {
		jta = new JTextArea();
		JButton btn = new JButton("���");
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
				//1.jdbc����̹��� �޸𸮷� �ε��Ѵ�.
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2.db������ �����Ѵ�.
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
			
				//3.Statement ��ü ����
				Statement stmt = conn.createStatement();
				
				//4.�����ͺ��̽� ����� �����Ѵ�.
				ResultSet rs = stmt.executeQuery(sql);
				
				String data = null;
				//5.���ڵ尡 �ִ� ��ŭ Ŀ���� �� �྿ �ű��
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
				
				//6.����ߴ� �ڿ����� �ݾ��ش�.
				rs.close();
				stmt.close();
				conn.close();			
				
			} catch (Exception ex) {
				
				System.out.println("���ܹ߻�:"+ex.getMessage());
			}
				
			}			
		});

	}
	

	public static void main(String[] args) {
		new AllStudentGUI();	
	}

}
