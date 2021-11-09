package com.sist.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Color;

public class NumberGuess extends JFrame implements ActionListener {
	
	//����ڰ� ���ڸ� �Է��ϱ� ���� �ؽ�Ʈ �ʵ带 ��������� ����
	JTextField jtf;
	
	//����ڰ� �Է��� ���ڰ� ��ǻ�Ͱ� ������ ���ڿ� ���߾����� ����� ����� �ؽ�Ʈ�ʵ带 ��������� ����
	JTextField result;
	
	//��ǻ�Ͱ� ������ ������ �����ϱ� ���� ������ ��������� �����Ѵ�.
	int num;
	
	
	public NumberGuess() {
		
		//���� �ÿ� ������ �ϳ� �����Ͽ� num�� �����Ѵ�
		Random r = new Random();
		num = r.nextInt(100)+1; //1~100������ �����߻� (+1�� �ؾ� 1���� �����߻�)
		System.out.println(num);
		
		
		//������� �ؽ�Ʈ�ʵ�� ���� ����
		jtf = new JTextField(10);
		result = new JTextField("              ���⿡ ����� ��Ÿ���ϴ�.              ");
		
		//�������� ��ġ����� �÷ο췹�̾ƿ����� ����
		setLayout(new FlowLayout());
		
		//���̾ƿ��� ���
		add(new JLabel("���ڸ� �����Ͻÿ�"));
		add(jtf);
		add(result);
		
		JButton btn01 = new JButton("�ٽ� �ѹ�");
		JButton btn02 = new JButton("����");
		add(btn01);
		add(btn02);
		
		//�������� ũ��� ȭ�鿡 ���̵��� ����
		setSize(300,300);
		setVisible(true);
		
		//â�� ������ ���α׷��� �����ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//â�� ũ�⸦ �������� ���ϵ���
		setResizable(false);
		
		//�ΰ��� ��ư�� �̺�Ʈ�� ���
		btn01.addActionListener(this);
		btn02.addActionListener(this);
		
	}
	
	
	public static void main(String[] args) {
		new NumberGuess();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//'�ٽ��ѹ�','����' ��ư�� ������ �� �޼ҵ尡 �����Ѵ�.
		//�Ű����� ActionEvent�� getActionCommand�޼ҵ带 ���� ��ư�� ���ڸ� �о����
		String cmd = e.getActionCommand();
		
		if(cmd.equals("�ٽ� �ѹ�")) {
		//��ǻ�Ͱ� �����ϰ� �ִ� ������ ����ڰ��Է��� ���ڰ� �������� �Ǻ�
		
		//����ڰ� �Է��� ���ڸ� ���� �´�.
		int user = Integer.parseInt(jtf.getText());
		
		//������,��ū��,������ �Ǻ��Ͽ� ��� ���
		if(user == num) {
			result.setText("                    �����Դϴ�.                    ");	
			result.setBackground(Color.BLUE);
		}else if(user > num ) {
			result.setText("              �� ū ���ڸ� �Է��߾��!              ");
			jtf.setText("");
			result.setBackground(Color.RED);
		}else {
			result.setText("              �� ���� ���ڸ� �Է��߾��!              ");
			jtf.setText("");
			result.setBackground(Color.RED);			
		}
			
		}else if(cmd.equals("����")) {
			System.exit(0);
		}
		
	}

}
