package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import java.awt.Label;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TeacherGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable studentTable;
	private GetData getData;
	private boolean isDetailWindowOpen = false;
	private JTable testTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherGUI frame = new TeacherGUI(null);
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
	public TeacherGUI(String name) {
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
		
		JLabel lblNewLabel_2 = new JLabel("List tests");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(328, 0, 167, 30);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("CREATE NEW TEST");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateExamGUI createExamGUI = new CreateExamGUI(name);
				createExamGUI.setVisible(true);
            
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(88, 312, 235, 55);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("EDIT TEST");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int rowCount = testTable.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    Boolean isSelected = (Boolean) testTable.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        String id = (String) testTable.getValueAt(i, 1);
                        String tenbaithi = (String) testTable.getValueAt(i, 2);
                        String monhoc = (String) testTable.getValueAt(i, 3);
                        String thoigian = (String) testTable.getValueAt(i, 4);

                        showTestDetailsWindow(id,tenbaithi,monhoc,thoigian);
                    }
                }
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(88, 389, 235, 55);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("REFRESH");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String classId = (String) name;
				List<String> tests = getData.getTestOnline(classId);
				    DefaultTableModel model = new DefaultTableModel(new Object[]{"Select", "ID", "Tên bài thi", "Môn học", "Thời gian"}, 0) {
			            @Override
			            public Class<?> getColumnClass(int column) {
			                return column == 0 ? Boolean.class : String.class; 
			            }
			        };
		            for (String test :tests ) {
		            	String[] parts = test.split("-"); 
		    	        String id = parts[0]; 
		    	        String tenbaithi = parts[1];
		    	        String monhoc = parts[2];
		    	        String thoigian = parts[3];
		                model.addRow(new Object[]{Boolean.FALSE,id, tenbaithi, monhoc,thoigian});
		            }
		            testTable.setModel(model);
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1.setBounds(472, 312, 235, 55);
		panel.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("DELETE TEST");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> idTest = new ArrayList<>();
                int rowCount = testTable.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    Boolean isSelected = (Boolean) testTable.getValueAt(i, 0); 
                    if (isSelected != null && isSelected) {
                        String id = (String) testTable.getValueAt(i, 1);
                        String tenbaithi = (String) testTable.getValueAt(i, 2);
                        String monhoc = (String) testTable.getValueAt(i, 3);
                        String thoigian = (String) testTable.getValueAt(i, 4);

                        idTest.add(id);
                        
                    }
                }
                SendDataToDB sendDataToDB = new SendDataToDB();
                sendDataToDB.deleteTestExamById(idTest);
                JOptionPane.showMessageDialog(null, "XÓA THÀNH CÔNG!!!",
    	                "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_1.setBounds(472, 389, 235, 55);
		panel.add(btnNewButton_1_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 33, 819, 264);
		panel.add(scrollPane_1);
		
		testTable = new JTable();
		scrollPane_1.setViewportView(testTable);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Students", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("List students");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(201, 0, 167, 30);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Class: ");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_2_1.setBounds(396, 0, 167, 30);
		panel_1.add(lblNewLabel_2_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(518, 0, 153, 33);
		getData = new GetData();
        List<String> classes = getData.getAllClass();
        for (String className : classes) {
        	comboBox.addItem(className);
        }
		panel_1.add(comboBox);
		
		
		JButton btnNewButton_2 = new JButton("view");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String classId = (String) comboBox.getSelectedItem();
			    List<String> sinhviens = getData.getSinhVienByLop(classId);
			    DefaultTableModel model = new DefaultTableModel(new Object[]{"Select", "ID", "Username", "Fullname", "Email"}, 0) {
		            @Override
		            public Class<?> getColumnClass(int column) {
		                return column == 0 ? Boolean.class : String.class;
		            }
		        };
	            for (String sinhvien :sinhviens ) {
	            	String[] parts = sinhvien.split("-"); 
	    	        String id = parts[0]; 
	    	        String username = parts[1];
	    	        String fullname = parts[2];
	    	        String email = parts[3];
	                model.addRow(new Object[]{Boolean.FALSE,id, username, fullname,email});
	            }
	            studentTable.setModel(model);
	            studentTable.addMouseListener(new MouseAdapter() {
	                public void mouseClicked(MouseEvent e) {
	                	 int selectedRow = studentTable.getSelectedRow(); 
	                	    int selectedColumn = studentTable.getSelectedColumn(); 
	                	    if (selectedColumn != 0 && selectedRow != -1) {
	                	        if (!isDetailWindowOpen) {
	                	            String id = (String) studentTable.getValueAt(selectedRow, 1);
	                	            String username = (String) studentTable.getValueAt(selectedRow, 2);
	                	            String fullname = (String) studentTable.getValueAt(selectedRow, 3);
	                	            String email = (String) studentTable.getValueAt(selectedRow, 4);
	                	            showDetailsWindow(id, username, fullname, email, classId);
	                	            isDetailWindowOpen = true;
	                	        }
	                	    }
	                }
	            });
	            isDetailWindowOpen = false;
			}
		});
		btnNewButton_2.setBounds(696, 9, 85, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_1_2 = new JButton("ADD NEW STUDENT");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRegisterGUI clientRegisterGUI = new ClientRegisterGUI();
				clientRegisterGUI.setVisible(true);
			}
		});
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
		btnNewButton_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> idSinhVien = new ArrayList<>();
                int rowCount = studentTable.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    Boolean isSelected = (Boolean) studentTable.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        String id = (String) studentTable.getValueAt(i, 1);
                        String username = (String) studentTable.getValueAt(i, 2);
                        String fullname = (String) studentTable.getValueAt(i, 3);
                        String email = (String) studentTable.getValueAt(i, 4);

                        idSinhVien.add(id);
                    }
                }
                SendDataToDB sendDataToDB = new SendDataToDB();
                sendDataToDB.deleteSinhVienById(idSinhVien);
                JOptionPane.showMessageDialog(null, "XÓA THÀNH CÔNG!!!",
    	                "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_1_2.setBounds(281, 406, 235, 55);
		panel_1.add(btnNewButton_1_1_1_1_2);
		
		JButton btnNewButton_1_1_1_2_1 = new JButton("STATISTICAL");
		btnNewButton_1_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticalGUI statisticalGUI = new StatisticalGUI();
				statisticalGUI.setVisible(true);
                
			}
		});
		btnNewButton_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1_2_1.setBounds(546, 329, 235, 55);
		panel_1.add(btnNewButton_1_1_1_2_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 52, 819, 267);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 819, 267);

        studentTable = new JTable();
        scrollPane.setViewportView(studentTable);
		panel_3.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Meet", null, panel_2, null);
		
		JLabel lblNewLabel = new JLabel("ONLINE TEST- TEACHER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 10, 341, 67);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hello "+name);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(335, 25, 247, 45);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientLoginGUI clientLoginGUI = new ClientLoginGUI();
				clientLoginGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(720, 25, 124, 45);
		contentPane.add(btnNewButton);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnProfile.setBounds(592, 25, 124, 45);
		contentPane.add(btnProfile);
	}
	public static void showDetailsWindow(String id, String username, String fullname, String email, String classId) {
	    JFrame detailFrame = new JFrame("Student Details");
	    detailFrame.setSize(400, 300);
	    detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JLabel idLabel = new JLabel("ID:");
	    JLabel idField = new JLabel(id);

	    JLabel usernameLabel = new JLabel("Username:");
	    JTextField usernameField = new JTextField(username, 20);

	    JLabel fullnameLabel = new JLabel("Fullname:");
	    JTextField fullnameField = new JTextField(fullname, 20);

	    JLabel emailLabel = new JLabel("Email:");
	    JTextField emailField = new JTextField(email, 20);

	    JLabel classLabel = new JLabel("Class:");
	    JComboBox<String> classComboBox = new JComboBox<>();
	    GetData getData = new GetData();
	    List<String> classes = getData.getAllClass();
	    for (String className : classes) {
	        classComboBox.addItem(className);
	    }
	    JButton getInfoButton = new JButton("Confirm");
	    JPanel panel = new JPanel();
	    GroupLayout layout = new GroupLayout(panel);
	    panel.setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    layout.setHorizontalGroup(
	        layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(idLabel)
	                .addComponent(usernameLabel)
	                .addComponent(fullnameLabel)
	                .addComponent(emailLabel))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(idField)
	                .addComponent(usernameField)
	                .addComponent(fullnameField)
	                .addComponent(emailField)
	                .addComponent(getInfoButton))
	    );
	    layout.setVerticalGroup(
	        layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(idLabel)
	                .addComponent(idField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(usernameLabel)
	                .addComponent(usernameField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(fullnameLabel)
	                .addComponent(fullnameField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(emailLabel)
	                .addComponent(emailField))
	            .addComponent(getInfoButton)
	    );
	    getInfoButton.addActionListener(e -> {
	        String newId = idField.getText();
	        String newUsername = usernameField.getText();
	        String newFullname = fullnameField.getText();
	        String newEmail = emailField.getText();
	        
	        SendDataToDB sendDataToDB = new SendDataToDB();
	        sendDataToDB.updateSinhVien(newId, newUsername, newFullname, newEmail, classId);
	        JOptionPane.showMessageDialog(detailFrame,"LƯU THÀNH CÔNG!!!",
	                "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
	    });

	    detailFrame.getContentPane().add(panel);
	    detailFrame.setLocationRelativeTo(null);
	    detailFrame.setVisible(true);
	}
	public static void showTestDetailsWindow(String id, String tenbaithi, String monhoc, String thoigian) {
	    JFrame detailFrame = new JFrame("Test Exam Details");
	    detailFrame.setSize(400, 300);
	    detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JLabel idLabel = new JLabel("ID:");
	    JLabel idField = new JLabel(id);

	    JLabel nameExamLabel = new JLabel("Name Exam:");
	    JTextField nameExamField = new JTextField(tenbaithi, 20);

	    JLabel subjectLabel = new JLabel("Subject:");
	    JTextField subjectField = new JTextField(monhoc, 20);

	    JLabel timeLabel = new JLabel("Time:");
	    JTextField timeField = new JTextField(thoigian, 20);

	    JButton getInfoButton = new JButton("Confirm");

	    JPanel panel = new JPanel();
	    GroupLayout layout = new GroupLayout(panel);
	    panel.setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    layout.setHorizontalGroup(
	        layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(idLabel)
	                .addComponent(nameExamLabel)
	                .addComponent(subjectLabel)
	                .addComponent(timeLabel))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(idField)
	                .addComponent(nameExamField)
	                .addComponent(subjectField)
	                .addComponent(timeField)
	                .addComponent(getInfoButton))
	    );
	    layout.setVerticalGroup(
	        layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(idLabel)
	                .addComponent(idField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(nameExamLabel)
	                .addComponent(nameExamField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(subjectLabel)
	                .addComponent(subjectField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(timeLabel)
	                .addComponent(timeField))
	            .addComponent(getInfoButton)
	    );
	    getInfoButton.addActionListener(e -> {
	        String newId = idField.getText();
	        String newNameExam = nameExamField.getText();
	        String newSubject = subjectField.getText();
	        String newTime = timeField.getText();
	        
	        SendDataToDB sendDataToDB = new SendDataToDB();
	        sendDataToDB.updateTestExam(newId, newNameExam, newSubject, newTime);
	        JOptionPane.showMessageDialog(detailFrame,"LƯU THÀNH CÔNG!!!",
	                "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
	    });

	    detailFrame.getContentPane().add(panel);
	    detailFrame.setLocationRelativeTo(null);
	    detailFrame.setVisible(true);
	}
}
