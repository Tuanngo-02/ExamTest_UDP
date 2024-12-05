package Client;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class CreateExamGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox;
	private JLabel textNotification;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateExamGUI frame = new CreateExamGUI();
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
	public CreateExamGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create new exam");
		lblNewLabel.setBounds(20, 10, 143, 24);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(101, 44, 148, 21);
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
		
		JLabel lblSelectClass_1_1_1 = new JLabel("fileTest");
		lblSelectClass_1_1_1.setBounds(20, 160, 81, 24);
		contentPane.add(lblSelectClass_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(99, 83, 150, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 117, 150, 21);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn file Excel");

                // Chỉ cho phép chọn file có đuôi .xlsx hoặc .xls
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
                fileChooser.setFileFilter(filter);

                // Mở hộp thoại chọn file
                int result = fileChooser.showOpenDialog(null);
                
                // Kiểm tra nếu người dùng chọn file
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textNotification.setText("Đã chọn file: " + selectedFile.getAbsolutePath());
                    System.out.println("Đã chọn file: " + selectedFile.getAbsolutePath());
                    SendDataToDB sendDataToDB = new SendDataToDB();
                    sendDataToDB.excel(selectedFile.getAbsolutePath());
                    // Xử lý file Excel được chọn ở đây
                    // importExamFromExcel(selectedFile.getAbsolutePath());
                } else {
                    System.out.println("Không có file nào được chọn.");
                }
			}
		});
		btnNewButton.setBounds(167, 162, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(256, 264, 85, 21);
		contentPane.add(btnConfirm);
		
		textNotification = new JLabel("New label");
		textNotification.setBounds(99, 197, 150, 13);
		contentPane.add(textNotification);
	}
}
