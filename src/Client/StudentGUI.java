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
	    btnLogout.setBounds(571, 13, 122, 41);
	    contentPane.add(btnLogout);

	    // Panel chính chứa các nút
	    JPanel gridPanel = new JPanel();
	    gridPanel.setLayout(new GridLayout(0, 4, 10, 10)); // Lưới: 4 cột, các hàng tự động, khoảng cách 10px
	    gridPanel.setBounds(10, 91, 672, 327);

	    // Lấy danh sách bài thi
	    GetData getData = new GetData();
	    List<String> baithis = getData.getBaiThiByLop(name);

	    // Tạo các nút động cho từng bài thi
	    for (String examName : baithis) {
	        JButton examButton = new JButton(examName);
	        examButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        examButton.setPreferredSize(new Dimension(100, 100)); // Kích thước mỗi ô hình vuông

	        // Thêm sự kiện khi nhấn vào nút
	        examButton.addActionListener(e -> {
	            System.out.println("Bấm vào bài thi: " + examName);
	            ExamOnlineGUI examOnlineGUI = new ExamOnlineGUI(examName, name);
	            examOnlineGUI.setVisible(true);
	        });

	        // Thêm nút vào gridPanel
	        gridPanel.add(examButton);
	    }

	    // Thêm gridPanel vào contentPane
	    contentPane.add(gridPanel);
	    
	    username = new JLabel("New label");
	    username.setText(name);
	    username.setBounds(489, 27, 72, 13);
	    contentPane.add(username);

	    // Cập nhật giao diện
	    gridPanel.revalidate();
	    gridPanel.repaint();
	}


}
