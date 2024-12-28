package Client;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreateExamGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfSecond;
	private JComboBox<String> comboBox;
	private JLabel textNotification;
	private File selectedFile;
	private JTextField tfSubject;
	private JTextField tfMinute;
	private JTextField tfHour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateExamGUI frame = new CreateExamGUI(null);
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
	public CreateExamGUI(String name) {
		setBounds(100, 100, 361, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREATE NEW EXAM");
		lblNewLabel.setBounds(20, 10, 186, 24);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(101, 44, 213, 21);
		GetData getData = new GetData();
        List<String> classes = getData.getAllClass();
        
        // Đổ dữ liệu vào JComboBox
        for (String className : classes) {
        	comboBox.addItem(className);
        }
		contentPane.add(comboBox);
		
		JLabel lblSelectClass = new JLabel("Select Class");
		lblSelectClass.setBounds(20, 44, 81, 24);
		contentPane.add(lblSelectClass);
		
		JLabel lblTnBiThi = new JLabel("Name exam");
		lblTnBiThi.setBounds(20, 80, 81, 24);
		contentPane.add(lblTnBiThi);
		
		JLabel lblSelectClass_1_1 = new JLabel("");
		lblSelectClass_1_1.setBounds(20, 114, 81, 24);
		contentPane.add(lblSelectClass_1_1);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(20, 114, 81, 24);
		contentPane.add(lblSubject);
		
		JLabel lblSelectClass_1_1_1 = new JLabel("File Exam (Excel)");
		lblSelectClass_1_1_1.setBounds(20, 207, 156, 24);
		contentPane.add(lblSelectClass_1_1_1);
		
		tfName = new JTextField();
		tfName.setBounds(101, 83, 213, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfSecond = new JTextField();
		tfSecond.setText("00");
		tfSecond.setColumns(10);
		tfSecond.setBounds(245, 147, 65, 21);
		contentPane.add(tfSecond);
		
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn file Excel");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                	selectedFile = fileChooser.getSelectedFile();
                    textNotification.setText("Đã chọn file: " + selectedFile.getAbsolutePath());
                } else {
                    System.out.println("Không có file nào được chọn.");
                }
			}
		});
		btnNewButton.setBounds(229, 209, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendDataToDB sendDataToDB = new SendDataToDB();
                sendDataToDB.excel(tfName.getText(), tfSubject.getText() ,selectedFile.getAbsolutePath());
                JOptionPane.showMessageDialog(null,"LƯU THÀNH CÔNG!!!",
    	                "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnConfirm.setBounds(229, 264, 85, 21);
		contentPane.add(btnConfirm);
		
		textNotification = new JLabel("New label");
		textNotification.setBounds(164, 241, 150, 13);
		contentPane.add(textNotification);
		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendDataToDB sendDataToDB = new SendDataToDB();
				String username_TF = name;
				String classId = (String) comboBox.getSelectedItem();
				String timeExam = tfHour.getText() + ":" + tfMinute.getText() + ":" + tfSecond.getText();
				sendDataToDB.createBaiThi(username_TF, tfName.getText(), tfSubject.getText(), timeExam, classId);
	            JOptionPane.showMessageDialog(null, "successful");
			}
		});
		btnNewButton_1.setBounds(229, 178, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblSelectClass_1_1_2 = new JLabel("");
		lblSelectClass_1_1_2.setBounds(20, 114, 81, 24);
		contentPane.add(lblSelectClass_1_1_2);
		
		tfSubject = new JTextField();
		tfSubject.setColumns(10);
		tfSubject.setBounds(101, 117, 213, 21);
		contentPane.add(tfSubject);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(20, 144, 81, 24);
		contentPane.add(lblTime);
		
		tfMinute = new JTextField();
		tfMinute.setText("00");
		tfMinute.setColumns(10);
		tfMinute.setBounds(158, 147, 65, 21);
		contentPane.add(tfMinute);
		
		tfHour = new JTextField();
		tfHour.setText("00");
		tfHour.setColumns(10);
		tfHour.setBounds(111, 147, 24, 21);
		contentPane.add(tfHour);
		
		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(145, 147, 12, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(":");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(229, 147, 12, 17);
		contentPane.add(lblNewLabel_1_1);
	}
}
