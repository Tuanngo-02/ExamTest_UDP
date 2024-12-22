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
    public List<String> getSinhVienByUserName(String username) {
        String query = "SELECT * FROM sinhvien WHERE username = ?";
        List<String> sinhvienInfo = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Thiết lập giá trị cho tham số trong câu truy vấn
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Lấy tất cả các thông tin cần thiết từ bảng
                    int id = rs.getInt("id");
                    String ten = rs.getString("fullname");
                    String email = rs.getString("email");
                    String lop = rs.getString("class"); // hoặc bất kỳ cột nào khác

                    // Ghép thông tin lại thành một chuỗi
                    String info = id+"-"+ten+"-"+email+"-"+lop;
                    sinhvienInfo.add(info);
                }
            }

            System.out.println("Sinhvien information retrieved successfully.");
        } catch (SQLException e) {
            System.out.println("Error retrieving GiangVien information: " + e.getMessage());
        }

        return sinhvienInfo;
    }
    public List<String> getBaiThiByLop(String name) {
        // Kết quả lưu danh sách bài thi
        List<String> baiThiList = new ArrayList<>();

        // Truy vấn để lấy thông tin lớp từ sinh viên
        String querySinhVien = "SELECT class FROM sinhvien WHERE username = ?";
        
        // Truy vấn để lấy lop_id từ bảng lop
        String queryLop = "SELECT id FROM lop WHERE lop = ?";
        
        // Truy vấn để lấy danh sách bài thi
        String queryBaiThi = "SELECT * FROM baithi WHERE id_lop = ?";
        
        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {
            // Bước 1: Tìm tên lớp (lop) từ bảng sinhvien
            String className = null;
            try (PreparedStatement pstmt1 = conn.prepareStatement(querySinhVien)) {
                pstmt1.setString(1, name);
                try (ResultSet rs1 = pstmt1.executeQuery()) {
                    if (rs1.next()) {
                        className = rs1.getString("class");
                    }
                }
            }

            // Kiểm tra tên lớp có tồn tại không
            if (className == null) {
                System.out.println("Không tìm thấy sinh viên với tên: " + name);
                return baiThiList;
            }

            // Bước 2: Tìm lop_id từ bảng lop
            String lopId = null;
            try (PreparedStatement pstmt2 = conn.prepareStatement(queryLop)) {
                pstmt2.setString(1, className);
                try (ResultSet rs2 = pstmt2.executeQuery()) {
                    if (rs2.next()) {
                        lopId = rs2.getString("id");
                    }
                }
            }

            // Kiểm tra lop_id có tồn tại không
            if (lopId == null) {
                System.out.println("Không tìm thấy ID lớp cho lớp: " + className);
                return baiThiList;
            }

            // Bước 3: Lấy danh sách bài thi từ bảng baithi
            try (PreparedStatement pstmt3 = conn.prepareStatement(queryBaiThi)) {
                pstmt3.setString(1, lopId);
                try (ResultSet rs3 = pstmt3.executeQuery()) {
                    while (rs3.next()) {
                        String tenbaiThi = rs3.getString("tenbaithi");
                        String timeExam = rs3.getString("thoigian");
                        String subject = rs3.getString("monhoc");
                        baiThiList.add(tenbaiThi+"-"+timeExam+"-"+subject);
                    }
                }
            }

            System.out.println("Lấy danh sách bài thi thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách bài thi: " + e.getMessage());
        }

        return baiThiList;
    }
    public List<String> getCauHoiByBaiThi(String tenBaiThi) {
        // Câu truy vấn lấy id_baithi từ bảng baithi
        String queryBaiThi = "SELECT * FROM baithi WHERE tenbaithi = ?";
        // Câu truy vấn lấy danh sách câu hỏi từ bảng cauhoi
        String queryCauHoi = "SELECT * FROM cauhoi WHERE id_baithi = ?";
        
        List<String> danhSachCauHoi = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmtBaiThi = conn.prepareStatement(queryBaiThi)) {
             
            // Thiết lập giá trị cho tham số trong câu truy vấn bảng baithi
            pstmtBaiThi.setString(1, tenBaiThi);

            try (ResultSet rsBaiThi = pstmtBaiThi.executeQuery()) {
                if (rsBaiThi.next()) {
                    int idBaiThi = rsBaiThi.getInt("id");
             
                    // Thực hiện truy vấn bảng cauhoi để lấy danh sách câu hỏi
                    try (PreparedStatement pstmtCauHoi = conn.prepareStatement(queryCauHoi)) {
                        pstmtCauHoi.setInt(1, idBaiThi);

                        try (ResultSet rsCauHoi = pstmtCauHoi.executeQuery()) {
                            while (rsCauHoi.next()) {
                                String cauHoi = rsCauHoi.getString("cauhoi");
                                String cauhoiso = rsCauHoi.getString("cauhoiso");
                                String dapan1 = rsCauHoi.getString("dapan1");
                                String dapan2 = rsCauHoi.getString("dapan2");
                                String dapan3 = rsCauHoi.getString("dapan3");
                                String dapan4 = rsCauHoi.getString("dapan4");
                                danhSachCauHoi.add(cauhoiso+"-"+cauHoi+"-"+dapan1+"-"+dapan2+"-"+dapan3+"-"+dapan4);
                            }
                        }
                    }
                } else {
                    System.out.println("Không tìm thấy bài thi với tên: " + tenBaiThi);
                }
            }

            System.out.println("Danh sách câu hỏi được lấy thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách câu hỏi: " + e.getMessage());
        }

        return danhSachCauHoi;
    }
    public List<String> getThongKeByUserName(String name) {
        String querysinhvien = "SELECT * FROM sinhvien WHERE username = ?";
        
        String querythongke = "SELECT * FROM thongke WHERE id_sinhvien = ?";
        
        String queryBaiThi = "SELECT * FROM baithi WHERE id = ?";
        
        List<String> thongke = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmtsinhvien = conn.prepareStatement(querysinhvien)) {
             
            // Thiết lập giá trị cho tham số trong câu truy vấn bảng baithi
        	pstmtsinhvien.setString(1, name);

            try (ResultSet rsSinhVien = pstmtsinhvien.executeQuery()) {
                if (rsSinhVien.next()) {
                    int id_sinhvien = rsSinhVien.getInt("id");
             
                    // Thực hiện truy vấn bảng cauhoi để lấy danh sách câu hỏi
                    try (PreparedStatement pstmtThongKe = conn.prepareStatement(querythongke)) {
                    	pstmtThongKe.setInt(1, id_sinhvien);

                        try (ResultSet rsThongKe = pstmtThongKe.executeQuery()) {
                            while (rsThongKe.next()) {
                                int id_baithi   = rsThongKe.getInt("id_baithi");
                                String thoigianlambai = rsThongKe.getString("thoigianlambai");
                                String ngaylam = rsThongKe.getString("ngaylam");
                                int diem = rsThongKe.getInt("diem");
                                
                             // Lấy tên bài thi từ bảng baithi
                                String tenBaiThi = "";
                                try (PreparedStatement pstmtBaiThi = conn.prepareStatement(queryBaiThi)) {
                                    pstmtBaiThi.setInt(1, id_baithi);

                                    try (ResultSet rsBaiThi = pstmtBaiThi.executeQuery()) {
                                        if (rsBaiThi.next()) {
                                            tenBaiThi = rsBaiThi.getString("tenbaithi");
                                        }
                                    }
                                }
                                thongke.add(ngaylam+"-"+tenBaiThi+"-"+diem+"-"+thoigianlambai);
                            }
                        }
                    }
                } else {
                    System.out.println("Không tìm thấy bài thi với tên:");
                }
            }

            System.out.println("Danh sách thống kê được lấy thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách câu hỏi: " + e.getMessage());
        }

        return thongke;
    }
    public List<String> getListThongKe() {
        List<String> list = new ArrayList<>();
        String query = "SELECT ngaylam, COUNT(id_sinhvien) AS so_luong_sinh_vien FROM thongke GROUP BY ngaylam ORDER BY ngaylam";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String ngayLam = rs.getString("ngaylam");
                int soLuongHocVien = rs.getInt("so_luong_sinh_vien");
                list.add(ngayLam+"-"+soLuongHocVien);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu thống kê: " + e.getMessage());
        }
        return list;
    }
    public List<String> getListThongKeDiemThi() {
        List<String> list = new ArrayList<>();
        String query = "SELECT diem, COUNT(*) AS so_luong FROM thongke GROUP BY diem ORDER BY diem ASC";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	int diem = rs.getInt("diem");
                int soLuong = rs.getInt("so_luong");
                list.add(diem+"-"+soLuong);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu thống kê: " + e.getMessage());
        }
        return list;
    }
    
    


}
