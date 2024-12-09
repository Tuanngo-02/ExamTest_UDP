package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ClientLoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton Student_JBT, Teacher_JBT,Parents_JBT;
	private JTextField username_TF;
	private JPasswordField password_TF;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLoginGUI frame = new ClientLoginGUI();
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
	public ClientLoginGUI() {
		setBounds(100, 100, 470, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lblNewLabel = new JLabel("ONLINE TEST");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(52, 10, 353, 68);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(58, 60, 303, 68);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 129, 143, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(10, 175, 143, 36);
		contentPane.add(lblNewLabel_2_1);
		
		username_TF = new JTextField();
		username_TF.setBounds(134, 134, 271, 36);
		contentPane.add(username_TF);
		username_TF.setColumns(10);
		
		Student_JBT = new JRadioButton("Student");
		Student_JBT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Student_JBT.setBounds(176, 258, 86, 21);
		contentPane.add(Student_JBT);
		
		Teacher_JBT = new JRadioButton("Teacher");
		Teacher_JBT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Teacher_JBT.setBounds(71, 258, 103, 21);
		contentPane.add(Teacher_JBT);
		
		Parents_JBT = new JRadioButton("Parents");
		Parents_JBT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Parents_JBT.setBounds(281, 258, 103, 21);
		contentPane.add(Parents_JBT);
		
		
		//Gộp 3 JRBT
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(Student_JBT);
		buttonGroup.add(Teacher_JBT);
		buttonGroup.add(Parents_JBT);
		
		JLabel lblNewLabel_3 = new JLabel("Login with");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(134, 202, 162, 68);
		contentPane.add(lblNewLabel_3);
	
		JButton Login_BT = new JButton("LOGIN");
		Login_BT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String select;
				if(Student_JBT.isSelected()) {
					select = Student_JBT.getText();
				} else if(Teacher_JBT.isSelected()) {
					select = Teacher_JBT.getText();
				}else {
					select = Parents_JBT.getText();
				}
				ClientLogin clientLogin = new ClientLogin();
				try {
				    clientLogin.Login(username_TF.getText(), password_TF.getText(), select, response -> {
				        // Xử lý response ở đây
				        String note = response;  // Cập nhật note trong callback
				       
				        if (note.equals("Login successful")) {
				            JOptionPane.showMessageDialog(null, "Login successful");
				            if (select.equals("Student")) {
				                StudentGUI studentGUI = new StudentGUI(username_TF.getText());
				                studentGUI.setVisible(true);
				                dispose();
				            } else if (select.equals("Teacher")) {
				                TeacherGUI teacherGUI = new TeacherGUI(username_TF.getText());
				                teacherGUI.setVisible(true);
				                dispose();
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, note);
				        }
				    });

				} catch (Exception e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}
			}
		});
		Login_BT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Login_BT.setBounds(34, 292, 380, 59);
		contentPane.add(Login_BT);
		
		JButton SignUp_BT = new JButton("SIGN UP");
		SignUp_BT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRegisterGUI clientRegister = new ClientRegisterGUI();
				clientRegister.setVisible(true);
				dispose();
			}
		});
		SignUp_BT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SignUp_BT.setBounds(34, 361, 380, 44);
		contentPane.add(SignUp_BT);
		
		password_TF = new JPasswordField();
		password_TF.setBounds(134, 173, 271, 38);
		contentPane.add(password_TF);
	}
}
