import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField lastName;
	private JTextField username;
	private JTextField email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(320, 10, 277, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(93, 88, 159, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(89, 161, 175, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(93, 225, 175, 27);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(93, 262, 175, 42);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(90, 325, 96, 19);
		contentPane.add(lblNewLabel_5);
		
		name = new JTextField();
		name.setBounds(320, 88, 277, 42);
		contentPane.add(name);
		name.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(320, 158, 277, 33);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		username = new JTextField();
		username.setBounds(320, 223, 277, 33);
		contentPane.add(username);
		username.setColumns(10);
		
		email = new JTextField();
		email.setBounds(320, 271, 290, 27);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(320, 319, 290, 33);
		contentPane.add(password);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = name.getText();
				String lName = lastName.getText();
				String user = username.getText();
				String mail = email.getText();
				String pass = password.getText();
				
				if(firstName == null) {
					JOptionPane.showMessageDialog(null, "Vendosni nje vlere per emrin",
							"", JOptionPane.ERROR_MESSAGE);
				}
				if(lastName == null) {
					JOptionPane.showMessageDialog(null, "Vendosni nje vlere per mbiemrin",
							"", JOptionPane.ERROR_MESSAGE);
				}
				
				if(user == null) {
					JOptionPane.showMessageDialog(null, "Vendosni nje vlere per username",
							"", JOptionPane.ERROR_MESSAGE);
				}
				
				PreparedStatement ps;				
				//ResultSet rs = null;
				
				String qyery = "INSERT INTO users(firstName,lastName,userName,password,email,gender) values(?,?,?,?,?,?)";
				
				try {
					ps = Database.getConnection().prepareStatement(qyery);
					
					ps.setString(1, firstName);
					ps.setString(2, lName);
					ps.setString(3, user);
					ps.setString(4, pass);
					ps.setString(5, mail);
					
					ps.executeUpdate();
					ps.close();
					JOptionPane.showMessageDialog(null, "Te dhenat u insertuan me sukeses",
							"", JOptionPane.INFORMATION_MESSAGE);
			
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "NUk u insertuan te dhenat",
							"", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}


			}
		});
		btnRegister.setBounds(144, 417, 201, 42);
		contentPane.add(btnRegister);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				name.setText("");
				lastName.setText("");
				email.setText("");
				password.setText("");
				username.setText("");

			}
		});
		btnClear.setBounds(473, 417, 229, 42);
		contentPane.add(btnClear);
	}
}
