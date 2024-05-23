import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\ikonaprojektit2.jpeg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 383);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(212, 0, 198, 64);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(157, 74, 253, 47);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(157, 171, 253, 47);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				String passw = password.getText();
				
				PreparedStatement ps;
				ResultSet rs;
				
				String query = "SELECT name, password from users where name = ? and password = ?";
				
				try {
					ps = Database.getConnection().prepareStatement(query);
					
					ps.setString(1, name);
					ps.setString(2, passw);
					
					
					rs = ps.executeQuery();
					
					while(rs.next()) {
						dispose();
						HomePage hp = new HomePage();
						hp.setVisible(true);
						
						JOptionPane.showMessageDialog(null, "Login me sukeses");						
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(46, 261, 232, 47);
		contentPane.add(btnLogin);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChangePassword.setBounds(331, 250, 191, 64);
		contentPane.add(btnChangePassword);
	}
}
