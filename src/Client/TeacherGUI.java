package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import java.awt.Label;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherGUI frame = new TeacherGUI();
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
	public TeacherGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 65, 844, 498);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Test", null, panel, null);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 28, 492, 262);
		panel.add(list);
		
		JLabel lblNewLabel_2 = new JLabel("List tests");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(156, 0, 167, 30);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("CREATE NEW TEST");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateExamGUI createExamGUI = new CreateExamGUI();
				createExamGUI.setVisible(true);
                dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(10, 312, 235, 55);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("EDIT TEST");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(10, 389, 235, 55);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("REFRESH");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1.setBounds(267, 312, 235, 55);
		panel.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("DELETE TEST");
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_1.setBounds(267, 389, 235, 55);
		panel.add(btnNewButton_1_1_1_1);
		
		JButton btnNewButton_1_1_1_1_1 = new JButton("start exam time");
		btnNewButton_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_1_1.setBounds(559, 312, 235, 55);
		panel.add(btnNewButton_1_1_1_1_1);
		
		JList list_1 = new JList();
		list_1.setBounds(559, 28, 235, 262);
		panel.add(list_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("The exams are starting");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(546, 0, 262, 30);
		panel.add(lblNewLabel_2_1);
		
		JButton btnNewButton_1_1_1_1_1_1 = new JButton("The exams are started");
		btnNewButton_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_1_1_1.setBounds(559, 389, 235, 55);
		panel.add(btnNewButton_1_1_1_1_1_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Students", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("List students");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(201, 0, 167, 30);
		panel_1.add(lblNewLabel_2_2);
		
		JList list_2 = new JList();
		list_2.setBounds(10, 42, 819, 262);
		panel_1.add(list_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Class: ");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_2_1.setBounds(396, 0, 167, 30);
		panel_1.add(lblNewLabel_2_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(518, 0, 153, 33);
		panel_1.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("view");
		btnNewButton_2.setBounds(696, 9, 85, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_1_2 = new JButton("ADD NEW STUDENT");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_2.setBounds(24, 329, 235, 55);
		panel_1.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1_2 = new JButton("REFRESH");
		btnNewButton_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_2.setBounds(281, 329, 235, 55);
		panel_1.add(btnNewButton_1_1_1_2);
		
		JButton btnNewButton_1_1_2 = new JButton("EDIT STUDENT");
		btnNewButton_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_2.setBounds(24, 406, 235, 55);
		panel_1.add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_1_1_2 = new JButton("DELETE STUDENT");
		btnNewButton_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_1_2.setBounds(281, 406, 235, 55);
		panel_1.add(btnNewButton_1_1_1_1_2);
		
		JButton btnNewButton_1_1_1_2_1 = new JButton("STATISTICAL");
		btnNewButton_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_2_1.setBounds(546, 329, 235, 55);
		panel_1.add(btnNewButton_1_1_1_2_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Meet", null, panel_2, null);
		
		JLabel lblNewLabel = new JLabel("ONLINE TEST- TEACHER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 10, 341, 67);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hello ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(335, 25, 247, 45);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(720, 25, 124, 45);
		contentPane.add(btnNewButton);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnProfile.setBounds(592, 25, 124, 45);
		contentPane.add(btnProfile);
	}
}
