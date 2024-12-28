package Client;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class StudentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel className;
	private JLabel nameExam;
	private JLabel username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI frame = new StudentGUI(null);
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
	public StudentGUI(String name) {
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

	    JButton btnLogout = new JButton("Logout");
	    btnLogout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ClientLoginGUI clientLoginGUI = new ClientLoginGUI();
	    		clientLoginGUI.setVisible(true);
	    		dispose();
	    	}
	    });
	    btnLogout.setBounds(571, 13, 122, 41);
	    contentPane.add(btnLogout);

	    JPanel gridPanel = new JPanel();
	    gridPanel.setLayout(new GridLayout(0, 4, 10, 10)); 
	    gridPanel.setBounds(10, 91, 672, 327);
	    GetData getData = new GetData();
	    List<String> baithis = getData.getBaiThiByLop(name);
	    for (String exam : baithis) {
	    	String[] parts = exam.split("-"); 
	        String examName = parts[0]; 
	        String timeExam = parts[1];
	        String subject = parts[2];
	        JButton examButton = new JButton("<html>Name Exam: " + examName + "<br>Subject: " + subject +"<br>Time: " + timeExam+ "</html>");
	        examButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        examButton.setPreferredSize(new Dimension(100, 100)); 
	        examButton.addActionListener(e -> {        
	            int option = JOptionPane.showConfirmDialog(
	                null, 
	                "Are you sure you want to take the test?", 
	                "Confirm", 
	                JOptionPane.YES_NO_OPTION
	            );
	            if (option == JOptionPane.YES_OPTION) {
	                System.out.println("Bấm vào bài thi: " + examName);
	                ExamOnlineGUI examOnlineGUI = new ExamOnlineGUI(examName, name, timeExam);
	                examOnlineGUI.setVisible(true);
	            }	           
	            else {
	                System.out.println("Không tham gia bài thi.");
	            }
	        });
	        gridPanel.add(examButton);
	    }
	    contentPane.add(gridPanel);
	    
	    username = new JLabel("New label");
	    username.setText(name);
	    username.setBounds(489, 27, 72, 13);
	    contentPane.add(username);
	    
	    JButton btnNewButton = new JButton("Exam history");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ExamHistoryGUI examHistoryGUI = new ExamHistoryGUI(name);
	    		examHistoryGUI.setVisible(true);
	    	}
	    });
	    btnNewButton.setBounds(140, 23, 122, 21);
	    contentPane.add(btnNewButton);

	    gridPanel.revalidate();
	    gridPanel.repaint();
	}
}
