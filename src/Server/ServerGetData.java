package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerGetData {
	String urldb = "jdbc:mysql://localhost:3306/test_online"; // URL cơ sở dữ liệu
    String userdb = "root"; // Tên đăng nhập cơ sở dữ liệu
    String passdb = "root"; // Mật khẩu cơ sở dữ liệu
    public List<String> getAllStudents() {
        String query = "SELECT * FROM lop";
        List<String> lop = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Lấy fullname và student_id từ kết quả truy vấn
                String className = rs.getString("lop");
                lop.add(className);
            }
            System.out.println("Student list retrieved successfully.");
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }

        return lop;
    }
}
