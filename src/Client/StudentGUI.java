package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class StudentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI frame = new StudentGUI();
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
	public StudentGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ONLINE TEST");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 154, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lbNameUser = new JLabel("");
		lbNameUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbNameUser.setBounds(465, 10, 154, 41);
		contentPane.add(lbNameUser);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(571, 13, 122, 41);
		contentPane.add(btnLogout);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 91, 672, 327);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 197, 186);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên môn học");
		lblNewLabel_1.setBounds(52, 20, 87, 39);
		panel_1.add(lblNewLabel_1);
	}
}
