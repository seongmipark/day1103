package com.sist.chat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClient extends JFrame implements ActionListener{
	
	//대화내용을 출력할 텍스트에리어를 멤버변수로 만든다
	JTextArea jta;
	
	//내가 대화내용을 입력할 텍스트필드를 멤버변수로
	JTextField jtf;
	
	//입출력스트림을 멤버변수로 만든다
	InputStream is;
	OutputStream os;
	
	public ChatClient() {
				
		//멤버변수 텍스트에리어와 텍스트필드를 생성
		jta = new JTextArea();
		jtf = new JTextField();
		
		//전송을 위한 버튼생성
		JButton btn = new JButton("전송");
		
		//버튼에 이벤트를 등록한다
		btn.addActionListener(this);
		
		
		//텍스트필드와 버튼을 담기위한 패널을 생성
		JPanel p = new JPanel();
		
		//패널의 레이아웃방식을 BorderLayout으로 변경한다.
		p.setLayout(new BorderLayout());
		
		p.add(jtf,BorderLayout.CENTER);
		p.add(btn,BorderLayout.EAST);
		
		//텍스트에리어를 스크롤팬으로 감싼다.
		JScrollPane jsp = new JScrollPane(jta);
		
		//프레임의 가운데에 텍스트에리어를 감싸고있는 스크롤팬을 담는다.
		add(jsp,BorderLayout.CENTER);
		
		//텍스트필드와 버튼을 담고있는 패널을 프레임의 아래에 담는다.
		add(p,BorderLayout.SOUTH);
		
		//프레임의 크기를 설정한다.
		setSize(600,400);
		setVisible(true);

		try {
			//통신을 위하여 서버에 접속을 요청한다.
			Socket socket = new Socket("222.120.183.156", 9003);
			
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//서버가 보내오는 데이터를 계속하여 받기 위한 쓰레드 클래스를 만들기
		//클래스 안에 있는 클래스를 inner클래스라고 하고
		//바깥에 있는 클래스는 outter클래스라고 한다.
		//inner클래스는 마치 outter클래스의 멤버처럼 동작한다.
		//outter클래스의 멤버에 자유롭게 접근 할 수 있다.
		class ClientThread implements Runnable{
			
			byte []data = new byte[100];
			
			public void run() {
				while(true) {
					try {
						//서버가 보내온 데이터를 수신한다.
						is.read(data);
						
						//수신한 데이터를 문자열로 만든다.
						String msg = new String(data);
						
						//수신한 문자열을 텍스트에리얼에 추가한다.
						jta.append(msg.trim()+"\n");
						
					} catch (Exception e) {
						System.out.println("예외발생:"+e.getMessage());
					}
				}
			}
		}//end innerClass
		
		//서버로부터 계속하여 수신된 메세지를 받기 위한 쓰레드객체를 생성하고 가동한다.
		ClientThread ct = new ClientThread();
		new Thread(ct).start();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatClient();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			//사용자가 입력한 텍스트필드의 내용을 서버로 보내도록 한다.
			//OutputStream의 void write(byte[] b) 메소드를 이용한다.
			byte []data = jtf.getText().getBytes();
			os.write(data);
			
			//메세지를 전송하고 다음 메세지입력을 위하여 텍스트 필드를 깨끗이 지운다.
			jtf.setText("");
			
		} catch (Exception ex) {
		System.out.println("예외발생:"+ex.getMessage());
		}
	}

}



