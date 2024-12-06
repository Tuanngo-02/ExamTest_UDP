package Server;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerSaveData {
	String urldb = "jdbc:mysql://localhost:3306/test_online"; // URL cơ sở dữ liệu
    String userdb = "root"; // Tên đăng nhập cơ sở dữ liệu
    String passdb = "root"; // Mật khẩu cơ sở dữ liệu
    // Phương thức để đọc dữ liệu từ file Excel và lưu vào cơ sở dữ liệu
    public void importStudentsFromExcel(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis);
             Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            String insertQuery = "INSERT INTO lop (student_id, fullname, class_name) VALUES (?, ?, ?)";

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Bỏ qua dòng tiêu đề

                int studentId = (int) row.getCell(0).getNumericCellValue();
                String fullname = row.getCell(1).getStringCellValue();
                String className = row.getCell(2).getStringCellValue();

                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                    pstmt.setInt(1, studentId);
                    pstmt.setString(2, fullname);
                    pstmt.setString(3, className);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error inserting student with ID " + studentId + ": " + e.getMessage());
                }
            }
            System.out.println("Data imported successfully from Excel file.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveBaiThi(String username, String nameExam, String subject, String classID) {
        String selectQuery = "SELECT id FROM giangvien WHERE username = ?";
        String selectQuery_2 = "SELECT id FROM lop WHERE lop = ?";
        String insertQuery = "INSERT INTO baithi (id_giangvien, tenbaithi, monhoc, id_lop) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {
            // Tìm id trong bảng giangvien
            int giangVienId = -1;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, username);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        giangVienId = rs.getInt("id");
                    }
                }
            }

            // Tìm id trong bảng lop
            int classsssId = -1;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery_2)) {
                selectStmt.setString(1, classID); // Sử dụng classID thay vì username
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        classsssId = rs.getInt("id");
                    }
                }
            }

            // Kiểm tra id_giangvien và id_lop trước khi lưu
            if (giangVienId == -1) {
                System.out.println("Không tìm thấy giảng viên với username: " + username);
                return;
            }
            if (classsssId == -1) {
                System.out.println("Không tìm thấy lớp với ID: " + classID);
                return;
            }

            // Lưu thông tin vào bảng baithi
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, giangVienId);
                insertStmt.setString(2, nameExam);
                insertStmt.setString(3, subject);
                insertStmt.setInt(4, classsssId);
                insertStmt.executeUpdate();
                System.out.println("Bài thi được lưu thành công!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu bài thi: " + e.getMessage());
        }
    }

    public void saveCauHoi(String nameExam, String questionOrder, String questionText, String options, String correctAnswer) {
        System.out.println("sdfsf:" + options);
        String[] answers = options.split(",");
    	String selectQuery = "SELECT id FROM baithi WHERE tenbaithi = ?";
        String insertQuery = "INSERT INTO cauhoi (id_baithi, cauhoiso, cauhoi, dapan1, dapan2, dapan3, dapan4, dapandung) VALUES (?, ?, ?, ?, ?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {

            // Tìm id của bài thi dựa vào nameExam
            int examId = -1;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, nameExam);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        examId = rs.getInt("id"); // Lấy id của bài thi
                    }
                }
            }

            // Nếu tìm thấy id của bài thi, lưu thông tin câu hỏi vào bảng cauhoi
            if (examId != -1) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, examId); // Gán id bài thi
                    insertStmt.setString(2, questionOrder); // Thứ tự câu hỏi
                    insertStmt.setString(3, questionText); // Nội dung câu hỏi
                    insertStmt.setString(4, answers[0]); // Lựa chọn câu trả lời
                    insertStmt.setString(5, answers[1]); // Lựa chọn câu trả lời
                    insertStmt.setString(6, answers[2]); // Lựa chọn câu trả lời
                    insertStmt.setString(7, answers[3]); // Lựa chọn câu trả lời

                    insertStmt.setString(8, correctAnswer); // Đáp án đúng
                    insertStmt.executeUpdate();
                    System.out.println("Câu hỏi được lưu thành công!");
                }
            } else {
                System.out.println("Không tìm thấy bài thi với tên: " + nameExam);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu câu hỏi: " + e.getMessage());
        }
    }


}
