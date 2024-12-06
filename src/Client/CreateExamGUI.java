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

public class CreateExamGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfSubject;
	private JComboBox<String> comboBox;
	private JLabel textNotification;
	private File selectedFile;

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
		lblSelectClass_1_1_1.setBounds(20, 181, 81, 24);
		contentPane.add(lblSelectClass_1_1_1);
		
		tfName = new JTextField();
		tfName.setBounds(99, 83, 150, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfSubject = new JTextField();
		tfSubject.setColumns(10);
		tfSubject.setBounds(101, 117, 150, 21);
		contentPane.add(tfSubject);
		
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
                	selectedFile = fileChooser.getSelectedFile();
                    textNotification.setText("Đã chọn file: " + selectedFile.getAbsolutePath());
                    System.out.println("Đã chọn file: " + selectedFile.getAbsolutePath());
                    
                    // Xử lý file Excel được chọn ở đây
                    // importExamFromExcel(selectedFile.getAbsolutePath());
                } else {
                    System.out.println("Không có file nào được chọn.");
                }
			}
		});
		btnNewButton.setBounds(169, 185, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendDataToDB sendDataToDB = new SendDataToDB();
                sendDataToDB.excel(tfName.getText(), tfSubject.getText() ,selectedFile.getAbsolutePath());
			}
		});
		btnConfirm.setBounds(256, 264, 85, 21);
		contentPane.add(btnConfirm);
		
		textNotification = new JLabel("New label");
		textNotification.setBounds(100, 217, 150, 13);
		contentPane.add(textNotification);
		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendDataToDB sendDataToDB = new SendDataToDB();
				String username_TF = name;
				System.out.println(username_TF);
				String classId = (String) comboBox.getSelectedItem();
				sendDataToDB.createBaiThi(username_TF, tfName.getText(), tfSubject.getText(), classId);
	            JOptionPane.showMessageDialog(null, "successful");
			}
		});
		btnNewButton_1.setBounds(163, 151, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}
