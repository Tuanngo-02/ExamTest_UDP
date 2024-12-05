package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerLoginRegister {
	String urldb = "jdbc:mysql://localhost:3306/test_online"; // URL cơ sở dữ liệu
    String userdb = "root"; // Tên đăng nhập cơ sở dữ liệu
    String passdb = "root"; // Mật khẩu cơ sở dữ liệu
    
    
    
    //Login
    public String CheckLogin(String username, String password, String role) {
    	String query = "";
    	if(role.equals("Student")) {
    		 query ="SELECT *FROM sinhvien WHERE username=? AND password=?";
    	}else if(role.equals("Teacher")) {
    		 query ="SELECT *FROM giangvien WHERE username=? AND password=?";
    	}else {
    		return "Please choose role!";
    	}
    	try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
               PreparedStatement pstmt = conn.prepareStatement(query)) {

               pstmt.setString(1, username);     // Thiết lập giá trị email
               pstmt.setString(2, password);  // Thiết lập giá trị password

               try (ResultSet rs = pstmt.executeQuery()) {
                   // Nếu có kết quả trả về từ cơ sở dữ liệu, nghĩa là email và password khớp
                   if (rs.next()) {
                       System.out.println("Login successful for username: " + username);
                       return "Login successful";
                   } else {
                       System.out.println("Username or password incorrect.");
                       return "Username or password incorrect.";
                   }
               }

           } catch (SQLException e) {
               System.out.println("Lỗi khi kiểm tra đăng nhập: " + e.getMessage());
               return e.getMessage();
           }
    }
    
    //Kiểm tra username
    public boolean checkUsername(String username, String role) {
    	String query = "";
    	
    	if(role.equals("Student")) {
    		 query ="SELECT username FROM sinhvien WHERE username=?";
    	}else if(role.equals("Teacher")) {
    		 query ="SELECT username FROM giangvien WHERE username=?";
    	}else {
    		
    	}
    	System.out.println("query "+ query+"\nusername "+ username);
    	try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
               PreparedStatement pstmt = conn.prepareStatement(query)) {

               pstmt.setString(1, username);     // Thiết lập giá trị email
               try (ResultSet rs = pstmt.executeQuery()) {
                   // Nếu không có hàng nào trả về, có nghĩa là username không tồn tại
                   return !rs.next(); // Nếu rs.next() trả về false, username có thể sử dụng
               }

           } catch (SQLException e) {
               System.out.println("Lỗi khi kiểm tra đăng nhập123: " + e.getMessage());
               return false;
           }
    }
    
  //Register
    public String Register(String username,String password,String fullname,String role,String email,String classs) {
    	String response="";
    	String query="";
    	String querycheckUser="";
    	if(role.equals("Student")) {
    		query ="INSERT INTO sinhvien(username,password,fullname,email,class) VALUES(?,?,?,?,?)";
    		querycheckUser="SELECT *FROM sinhvien WHERE username=?";
    	}else if(role.equals("Teacher")) {
    		query ="INSERT INTO giangvien(username,password,fullname,email) VALUES(?,?,?,?)";
    		querycheckUser="SELECT *FROM giangvien WHERE username=?";
    	}else {
    		
    	}
    	
    	
    	if(username=="" || password==""||fullname==""||email==""||classs==""||role=="") {
    		response="Please fill in all information!";
    	}
    	else if(checkUsername(username, role)==false) {
    		response="Username already exists!";
    	}
    	else {
    	
    	//Thêm vào CSDL
    	try(Connection connection= DriverManager.getConnection(urldb,userdb,passdb);
    			PreparedStatement pstmt =connection.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, fullname);
			pstmt.setString(4, email);
			if(role.equals("Student")) {
				pstmt.setString(5, classs);
			}
			int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                response = "Account registration successful, please login!";
            } else {
                response = "Account registration failed!";
            }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi khi kiểm tra đăng ký: " + e.getMessage());
		}
    	}
    	return response;
    }
    
    
    
    
    
}
