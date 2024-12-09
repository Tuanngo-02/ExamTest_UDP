package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExamOnlineGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<ButtonGroup> buttonGroups; // Lưu các ButtonGroup cho mỗi câu hỏi

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamOnlineGUI frame = new ExamOnlineGUI("Đề thi mẫu", null);
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
	public ExamOnlineGUI(String tenbaithi, String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("BÀI THI: " + tenbaithi);
		lblNewLabel.setBounds(5, 5, 676, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		// Panel chứa các câu hỏi
		JPanel questionsPanel = new JPanel();
		questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

		// Thanh cuộn cho panel
		JScrollPane scrollPane = new JScrollPane(questionsPanel);
		scrollPane.setBounds(5, 27, 676, 475);
		contentPane.add(scrollPane);

		// Nút Submit
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSubmit(username, tenbaithi);
			}
		});
		btnNewButton.setBounds(591, 512, 85, 21);
		contentPane.add(btnNewButton);

		// Khởi tạo danh sách ButtonGroup
		buttonGroups = new ArrayList<>();

		// Lấy dữ liệu câu hỏi
		GetData getData = new GetData();
		List<String> cauhois = getData.getCauHoiByBaiThi(tenbaithi);

		// Xử lý và hiển thị câu hỏi
		for (int i = 0; i < cauhois.size(); i++) {
			String rawData = cauhois.get(i);

			// Tách và xử lý dữ liệu câu hỏi
			String[] parts = rawData.split("-");
			if (parts.length < 6) {
				System.out.println("Dữ liệu câu hỏi không hợp lệ: " + rawData);
				continue;
			}

			// Phân tích dữ liệu
			String questionNumber = parts[0];
			String questionContent = parts[1];
			String answer1 = parts[2];
			String answer2 = parts[3];
			String answer3 = parts[4];
			String answer4 = parts[5];

			// Tạo panel cho từng câu hỏi
			JPanel questionPanel = new JPanel();
			questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
			questionPanel.setBorder(BorderFactory.createTitledBorder("Câu " + questionNumber));

			// Thêm câu hỏi
			JLabel lblQuestion = new JLabel(questionContent);
			questionPanel.add(lblQuestion);

			// Thêm các đáp án
			JRadioButton option1 = new JRadioButton(answer1);
			JRadioButton option2 = new JRadioButton(answer2);
			JRadioButton option3 = new JRadioButton(answer3);
			JRadioButton option4 = new JRadioButton(answer4);

			// Tạo nhóm các lựa chọn
			ButtonGroup group = new ButtonGroup();
			group.add(option1);
			group.add(option2);
			group.add(option3);
			group.add(option4);

			// Lưu nhóm vào danh sách
			buttonGroups.add(group);

			// Thêm các lựa chọn vào panel câu hỏi
			questionPanel.add(option1);
			questionPanel.add(option2);
			questionPanel.add(option3);
			questionPanel.add(option4);

			questionsPanel.add(questionPanel);
		}
	}

	// Hàm xử lý khi nhấn nút Submit
	private void handleSubmit(String username, String tenbaithi) {
		List<String> results = new ArrayList<>();

		for (int i = 0; i < buttonGroups.size(); i++) {
			ButtonGroup group = buttonGroups.get(i);
			for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); ) {
				AbstractButton button = buttons.nextElement();
				if (button.isSelected()) {
					results.add("Câu " + (i + 1) + ": " + button.getText());
				}
			}
		}
		
		SendDataToDB sendDataToDB = new SendDataToDB();
		sendDataToDB.sendDataResultToDB(results,username, tenbaithi,response -> {
			if (results.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn đáp án nào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, response, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
			}
		});

//		// Hiển thị kết quả
//		if (results.isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Bạn chưa chọn đáp án nào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//		} else {
//			JOptionPane.showMessageDialog(this, String.join("\n", results), "Kết quả", JOptionPane.INFORMATION_MESSAGE);
//		}
	}
}
