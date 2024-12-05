package Server;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

}
