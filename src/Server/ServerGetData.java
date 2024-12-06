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
    public List<String> getAllGiangVienByUsername(String username) {
        String query = "SELECT * FROM giangvien WHERE username = ?";
        List<String> giangVienInfo = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Thiết lập giá trị cho tham số trong câu truy vấn
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Lấy tất cả các thông tin cần thiết từ bảng
                    int id = rs.getInt("id");
                    String ten = rs.getString("ten");
                    String email = rs.getString("email");
                    String lop = rs.getString("lop"); // hoặc bất kỳ cột nào khác

                    // Ghép thông tin lại thành một chuỗi
                    String info = "ID: " + id + ", Tên: " + ten + ", Email: " + email + ", Lớp: " + lop;
                    giangVienInfo.add(info);
                }
            }

            System.out.println("GiangVien information retrieved successfully.");
        } catch (SQLException e) {
            System.out.println("Error retrieving GiangVien information: " + e.getMessage());
        }

        return giangVienInfo;
    }

}
