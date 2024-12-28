package Client;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientRegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username_TF;
	private JTextField fullname_TF;
	private JTextField email_TF;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	public JComboBox role_JCB;
	private JComboBox classs_TF;
	private JLabel lblNewLabel_2_1_2_1_1;
	private JTextField code_TF;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ClientRegisterGUI() {
		
		setBounds(100, 100, 470, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ONLINE TEST");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(52, 10, 353, 68);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SIGN UP");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(58, 60, 303, 68);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 129, 143, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(10, 175, 143, 36);
		contentPane.add(lblNewLabel_2_1);
		
		username_TF = new JTextField();
		username_TF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username_TF.setBounds(134, 134, 271, 36);
		contentPane.add(username_TF);
		username_TF.setColumns(10);
		
		JButton Signup_BT = new JButton("SIGN UP");
		Signup_BT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passwordField.getText().equals(passwordField_1.getText())){
					 if(code_TF.getText().equals("8386")) {
						
					
				ClientRegister clientRegister = new ClientRegister();
				try {
					clientRegister.Register(username_TF.getText(), passwordField.getText(),fullname_TF.getText(), role_JCB.getSelectedItem().toString(), email_TF.getText(),classs_TF.getSelectedItem().toString(),respone ->{
						if(respone.equals("Account registration successful, please login!")) {
						JOptionPane.showMessageDialog(null, respone);
						ClientLoginGUI clientLoginGUI = new ClientLoginGUI();
						clientLoginGUI.setVisible(true);
						dispose();
						} else {
							JOptionPane.showMessageDialog(null, respone);
						}
					});
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}else {
					JOptionPane.showMessageDialog(null, "Wrong code!");
				}
			}
				else {
				JOptionPane.showMessageDialog(null, "Passwords do not match!");
			}
				}
		});
		Signup_BT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Signup_BT.setBounds(37, 454, 380, 59);
		contentPane.add(Signup_BT);
		
		JButton Login_BT = new JButton("You already have an account?");
		Login_BT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientLoginGUI clientLogin = new ClientLoginGUI();
				clientLogin.setVisible(true);
				dispose();
			}
		});
		Login_BT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Login_BT.setBounds(37, 523, 380, 44);
		contentPane.add(Login_BT);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Fullname");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(10, 267, 143, 36);
		contentPane.add(lblNewLabel_2_1_1);
		
		fullname_TF = new JTextField();
		fullname_TF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fullname_TF.setColumns(10);
		fullname_TF.setBounds(134, 267, 271, 36);
		contentPane.add(fullname_TF);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Email");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_2.setBounds(10, 313, 143, 36);
		contentPane.add(lblNewLabel_2_1_2);
		
		email_TF = new JTextField();
		email_TF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email_TF.setColumns(10);
		email_TF.setBounds(134, 313, 271, 36);
		contentPane.add(email_TF);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("Role");
		lblNewLabel_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_2_1.setBounds(10, 359, 143, 36);
		contentPane.add(lblNewLabel_2_1_2_1);
		
		role_JCB = new JComboBox();
		role_JCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		role_JCB.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher"}));
		role_JCB.setToolTipText("");
		role_JCB.setBounds(134, 359, 271, 36);
		contentPane.add(role_JCB);
		role_JCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) role_JCB.getSelectedItem();
                
                if ("Student".equals(selectedRole)) {
                	lblNewLabel_2_1_2_1_1.setText("Class:");
                	classs_TF.setVisible(true);
                	code_TF.setVisible(false);
                } else if ("Teacher".equals(selectedRole)) {
                	lblNewLabel_2_1_2_1_1.setText("Code:");
                	code_TF.setVisible(true);
                	classs_TF.setVisible(false);
                }
            }
        });
		
		lblNewLabel_2_1_2_1_1 = new JLabel("Class");
		lblNewLabel_2_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_2_1_1.setBounds(10, 408, 143, 36);
		contentPane.add(lblNewLabel_2_1_2_1_1);
		
		classs_TF = new JComboBox();
		classs_TF.setModel(new DefaultComboBoxModel(new String[] {"12A", "12B"}));
		classs_TF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		classs_TF.setToolTipText("");
		classs_TF.setBounds(134, 405, 271, 36);
		contentPane.add(classs_TF);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(134, 177, 271, 34);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Password");
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1_3.setBounds(10, 221, 143, 36);
		contentPane.add(lblNewLabel_2_1_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField_1.setBounds(134, 223, 271, 34);
		contentPane.add(passwordField_1);
		
		code_TF = new JTextField();
		code_TF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		code_TF.setColumns(10);
		code_TF.setBounds(134, 405, 271, 36);
		code_TF.setVisible(false);
		contentPane.add(code_TF);
	}
}
