import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.FileWriter;
import java.io.IOException;

public class FurnitoretCRUD extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel sup;
	private JTextField supId;
	private JTextField supName;
	private JTextField supAddress;
	private JTextField supCity;
	private JTextField supStatus;
	
	PreparedStatement ps;
	ResultSet rs;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FurnitoretCRUD frame = new FurnitoretCRUD();
					frame.setVisible(true);
					frame.getFurnitoret();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public void getFurnitoret() {
		String query = "SELECT * from furnitoret";		
		try {
			ps = Database.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			table.setModel((DefaultTableModel)DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public FurnitoretCRUD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 548);
		sup = new JPanel();
		sup.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(sup);
		sup.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Furnitoret");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(88, 28, 201, 66);
		sup.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SupId");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(24, 115, 123, 36);
		sup.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SupName");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(24, 176, 123, 36);
		sup.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SupAddress");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(28, 233, 119, 36);
		sup.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SupCity");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(24, 304, 73, 15);
		sup.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("SupStatus");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(24, 340, 80, 18);
		sup.add(lblNewLabel_5);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = supName.getText();
				String address = supAddress.getText();
				String city = supCity.getText();
				String status = supStatus.getText();
				
				
				if (name.equals("") || name==null) {
					JOptionPane.showMessageDialog(null, "Ju lutem vendosmi vleren e emrit te furnitorit");
				}
				if (address.equals("") || address==null) {
					JOptionPane.showMessageDialog(null, "Ju lutem vendosmi vleren e emrit te furnitorit");
				}
				if (city.equals("") || city==null) {
					JOptionPane.showMessageDialog(null, "Ju lutem vendosmi vleren e emrit te furnitorit");
				}
				
String query = "INSERT INTO furnitoret(SUPNAME,SUPADDRESS,SUPCITY,SUPSTATUS) VALUES (?,?,?,?)";
				
				try {
					ps = Database.getConnection().prepareStatement(query);
					
					ps.setString(1, name);
					ps.setString(2, address);
					ps.setString(3, city);
					ps.setString(4, status);
					
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Te dhenat u insertuan me sukeses");
					getFurnitoret();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(2, 442, 145, 36);
		sup.add(btnSave);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = supName.getText();
				String address = supAddress.getText();
				String city = supCity.getText();
				String status = supStatus.getText();
				
				String ids = supId.getText();
				
				if (name.equals("") || name==null) {
					JOptionPane.showMessageDialog(null, "Ju lutem vendosmi vleren e emrit te furnitorit");
				}
				if (address.equals("") || address==null) {
					JOptionPane.showMessageDialog(null, "Ju lutem vendosmi vleren e emrit te furnitorit");
				}
				if (city.equals("") || city==null) {
					JOptionPane.showMessageDialog(null, "Ju lutem vendosmi vleren e emrit te furnitorit");
				}
				
				String query = "UPDATE furnitoret set SUPNAME=?, SUPADDRESS=?,SUPCITY=?,SUPSTATUS=? where SUPNR=?";
				
				try {
						ps = Database.getConnection().prepareStatement(query);
						
						ps.setString(1, name);
						ps.setString(2, address);
						ps.setString(3, city);
						ps.setString(4, status);
						ps.setString(5, ids);
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "Te dhenat u updatuan me sukeses");
				        getFurnitoret();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(204, 442, 161, 44);
		sup.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ids = supId.getText();
				
				String query = "DELETE FROM furnitoret WHERE SUPNR=?";
				
				try {
					ps = Database.getConnection().prepareStatement(query);
					
					ps.setString(1, ids);
					
					ps.executeUpdate();

					JOptionPane.showMessageDialog(null, "Rekordi u fshi me sukeses");
			        getFurnitoret();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(400, 442, 152, 44);
		sup.add(btnDelete);
		
		supId = new JTextField();
		supId.setBounds(114, 112, 201, 28);
		sup.add(supId);
		supId.setColumns(10);
		
		supName = new JTextField();
		supName.setBounds(114, 161, 201, 36);
		sup.add(supName);
		supName.setColumns(10);
		
		supAddress = new JTextField();
		supAddress.setBounds(114, 222, 201, 36);
		sup.add(supAddress);
		supAddress.setColumns(10);
		
		supCity = new JTextField();
		supCity.setBounds(114, 298, 201, 32);
		sup.add(supCity);
		supCity.setColumns(10);
		
		supStatus = new JTextField();
		supStatus.setBounds(114, 333, 201, 36);
		sup.add(supStatus);
		supStatus.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		table=new JTable();

		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = table.getSelectedRow();
				TableModel model =  table.getModel();
				supId.setText(model.getValueAt(index, 0).toString());
				supName.setText(model.getValueAt(index, 1).toString());
				supAddress.setText(model.getValueAt(index, 2).toString());
				supCity.setText(model.getValueAt(index, 3).toString());
				supStatus.setText(model.getValueAt(index, 4).toString());
			}
		});
		scrollPane.setBounds(493, 127, 370, 231);
		sup.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);

		scrollPane.setViewportView(table);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnPrint.setBounds(422, 10, 173, 51);
		sup.add(btnPrint);
		
		JButton btnExport = new JButton("EXPORT");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String path = "C:\\Users\\User\\Desktop\\ExportExcel.csv";
				
				try {
					FileWriter fw = new FileWriter(path);
					
					PreparedStatement ps;
					String query = "SELECT * from furnitoret";
					ps = Database.getConnection().prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						fw.append(rs.getString("SUPNAME"));
						fw.append(',');
						fw.append(rs.getString("SUPADDRESS"));
						fw.append(',');
						fw.append(rs.getString("SUPCITY"));
						fw.append(',');
						fw.append(rs.getString("SUPSTATUS"));
	
					}
					JOptionPane.showMessageDialog(null, "Te dhenat u exportuan me sukeses");
					fw.flush();
					fw.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});
		btnExport.setBounds(656, 10, 201, 44);
		sup.add(btnExport);
	}
}
