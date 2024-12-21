package Client;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

public class ExamHistoryGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExamHistoryGUI frame = new ExamHistoryGUI("thanhkhaa");
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
    public ExamHistoryGUI(String name) {
        GetData getData = new GetData();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("EXAM HISTORY");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(220, 10, 164, 30);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Name : ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 50, 54, 16);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Class : ");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(10, 80, 66, 16);
        contentPane.add(lblNewLabel_1_1);

        JLabel lbName = new JLabel(name);
        lbName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbName.setBounds(70, 50, 200, 16);
        contentPane.add(lbName);

        JLabel lbClass = new JLabel("Class 1"); // Bạn có thể thay đổi thông tin lớp.
        lbClass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbClass.setBounds(70, 80, 200, 16);
        contentPane.add(lbClass);
        
        List<String> sinhvien = getData.getSinhVienByUserName(name);
        
        String classSV = "";
        String fullName = "";
        for (String sinhvienn : sinhvien) {
        	String[] parts = sinhvienn.split("-"); // Tách chuỗi bằng dấu "-"
        	fullName = parts[1];
            classSV = parts[3];
        }
        lbName.setText(fullName);
        lbClass.setText(classSV);

        // Tạo JScrollPane để hiển thị danh sách bài thi
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 130, 560, 220);
        contentPane.add(scrollPane);

        // Panel chứa các thông tin bài thi
        JPanel panelList = new JPanel();
        panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(panelList);
        
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblDate.setBounds(64, 100, 72, 30);
        contentPane.add(lblDate);
        
        JLabel lblNameExam = new JLabel("Name exam");
        lblNameExam.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNameExam.setBounds(172, 100, 105, 30);
        contentPane.add(lblNameExam);
        
        JLabel lblScore = new JLabel("Score");
        lblScore.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblScore.setBounds(330, 100, 72, 30);
        contentPane.add(lblScore);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTime.setBounds(465, 100, 72, 30);
        contentPane.add(lblTime);

        // Lấy danh sách bài thi
        List<String> baithis = getData.getThongkeByUserName(name);

        // Tạo các panel con cho từng bài thi
        for (String thongke : baithis) {
            String[] parts = thongke.split("-"); // Tách chuỗi bằng dấu "-"
            String date = parts[0];
            String nameExam = parts[1];
            String score = parts[2];
            String timeUsed = parts[3];

            // Tạo panel cho một bài thi
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 4));
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel lbDate = new JLabel(date);
            JLabel lbNameEx = new JLabel(nameExam);
            JLabel lblResult = new JLabel(score);
            JLabel lbTime = new JLabel(timeUsed);

            lbDate.setHorizontalAlignment(SwingConstants.CENTER);
            lbNameEx.setHorizontalAlignment(SwingConstants.CENTER);
            lblResult.setHorizontalAlignment(SwingConstants.CENTER);
            lbTime.setHorizontalAlignment(SwingConstants.CENTER);

            panel.add(lbDate);
            panel.add(lbNameEx);
            panel.add(lblResult);
            panel.add(lbTime);

            // Thêm panel vào danh sách
            panelList.add(panel);
        }

        // Làm mới giao diện để hiển thị các bài thi
        panelList.revalidate();
        panelList.repaint();
    }
}
