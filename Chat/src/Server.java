import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Server extends JFrame {

	private JPanel contentPane;
	private static JTextField msg_area;
	private JTextField msg_text;

	/**
	 * Launch the application.
	 */
	
	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String msgin = "";
		
			try {
				
			ss = new ServerSocket(1201);//server starts at 1201 port no.
			s = ss.accept(); // now server will accept the connections
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(!msgin.equals("exit")) {
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\n"+msgin); // display message
				
				
				
			}//while
		
		
		}catch(Exception e) {
			
			
			
		}//trycatch
		
		
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_area = new JTextField();
		msg_area.setBounds(10, 39, 333, 240);
		contentPane.add(msg_area);
		msg_area.setColumns(10);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 306, 252, 29);
		contentPane.add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_send = new JButton("Send");
		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			try {
				String msgout = "";
				msgout = msg_text.getText().trim();//sending the message from server to client
				dout.writeUTF(msgout);
			
			
			}catch(Exception e) {
				
				
			}
			
			
			
			}
		});
		msg_send.setBounds(276, 306, 67, 29);
		contentPane.add(msg_send);
		
		JLabel lblServer = new JLabel("SERVER");
		lblServer.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 17));
		lblServer.setBounds(10, 11, 108, 17);
		contentPane.add(lblServer);
	}
}
