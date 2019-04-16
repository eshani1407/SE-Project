import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private static  JTextField  msg_area;
	private JTextField msg_text;

	/**
	 * Launch the application.
	 */
	

	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout; 
	
	
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		try {
			
			s = new Socket("192.168.5.31", 1201); // ip address of the computer because running client and server on same pc
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			
			while(msgin.equals("exit")) {
				
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\n Server:\t"+msgin);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_area = new JTextField();
		msg_area.setBounds(10, 39, 333, 240);
		contentPane.add(msg_area);
		msg_area.setColumns(10);
		
		msg_text = new JTextField();
		msg_text.setColumns(10);
		msg_text.setBounds(10, 310, 252, 29);
		contentPane.add(msg_text);
		
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
		msg_send.setBounds(272, 310, 67, 29);
		contentPane.add(msg_send);
		
		JLabel lblNewLabel = new JLabel("CLIENT");
		lblNewLabel.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 108, 17);
		contentPane.add(lblNewLabel);
	}
}
