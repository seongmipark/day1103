package com.sist.product;
//�����Ѱ�
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;

/*
��ǰ

��ǰ��ȣ
��ǰ��
����
�ܰ�
 */



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;


public class ProductTest extends JFrame {
	
	JTextField jtf_no;
	JTextField jtf_name;
	JTextField jtf_num;
	JTextField jtf_price;
	
	ProductTest(){
	jtf_no = new JTextField();
	jtf_name = new JTextField();
	jtf_num = new JTextField();
	jtf_price = new JTextField();
	
	JButton btn = new JButton("�߰�");

	JPanel p = new JPanel();
	p.setLayout(new GridLayout(4,2));	
	
	p.add(new JLabel("��ǰ��ȣ"));
	p.add(jtf_no);
	
	p.add(new JLabel("��ǰ��:"));
	p.add(jtf_name);
	
	p.add(new JLabel("����:"));
	p.add(jtf_num);
	
	p.add(new JLabel("�ܰ�:"));
	p.add(jtf_price);

	add(p,BorderLayout.CENTER);
	add(btn,BorderLayout.SOUTH);
	
	setSize(200,300);
	setVisible(true);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	btn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String no = jtf_no.getText();
			String name = jtf_name.getText();
			int num = Integer.parseInt(jtf_num.getText());
			int price = Integer.parseInt(jtf_price.getText());
			String sql = "Insert into product values("+"'"+no+"','"+name+"',"+num+","+price+")";
			System.out.println(sql);
			
			//jdbc ����
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = 
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##sist","sist");
				Statement stmt = conn.createStatement();
				int re = stmt.executeUpdate(sql);
				System.out.println("re:"+re);
				
				stmt.close();
				conn.close();
				
			} catch (Exception ex) {
				System.out.println("���ܹ߻�:"+ex.getMessage());
			}

		}
		
	});

	
	}
	
	public static void main(String[] args) {
		new ProductTest();

	}

}
