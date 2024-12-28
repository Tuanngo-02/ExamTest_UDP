package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.Common;

public class ServerDeleteData {
	String urldb = Common.DATABASECONFIG.getDbUrl(); 
    String userdb = Common.DATABASECONFIG.getDbUser(); 
    String passdb = Common.DATABASECONFIG.getDbPass(); 
    public void deleteSinhVienById(List<String> ids) {
        String query = "DELETE FROM sinhvien WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                for (String id : ids) {
                    pstmt.setString(1, id); 
                    pstmt.addBatch(); 
                }
                pstmt.executeBatch();
                conn.commit();
                System.out.println("Sinh viên đã được xóa thành công.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Lỗi khi xóa sinh viên: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }
    public void deleteTestExamById(List<String> ids) {
        String query = "DELETE FROM baithi WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {
            conn.setAutoCommit(false);
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                for (String id : ids) {
                    pstmt.setString(1, id); 
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
                System.out.println("bài thi đã được xóa thành công.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Lỗi khi xóa bài thin: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

}
