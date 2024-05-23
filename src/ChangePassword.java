import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.*;
public class ChangePassword extends JFrame {

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
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(150, 56, 146, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(150, 128, 109, 26);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(311, 51, 224, 48);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(311, 120, 224, 48);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = username.getText();
				String pass = password.getText();
				
				PreparedStatement ps;
				ResultSet rs;
				String query = "update users SET password = ? where name = ?";
				
				try {
					ps = Database.getConnection().prepareStatement(query);
					
					ps.setString(2, name);
					ps.setString(1, pass);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Passwordi u ndryshua me sukeses");	
						HomePage hp = new HomePage();
						hp.setVisible(true);
						
						//JOptionPane.showMessageDialog(null, "Login me sukeses");						
				}
		
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSave.setBounds(337, 245, 191, 57);
		contentPane.add(btnSave);
	}
}
