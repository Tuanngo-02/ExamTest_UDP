package Server;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Config.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServerSaveData {
	String urldb = Common.DATABASECONFIG.getDbUrl(); 
    String userdb = Common.DATABASECONFIG.getDbUser(); 
    String passdb = Common.DATABASECONFIG.getDbPass(); 
    public void importStudentsFromExcel(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis);
             Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {

            Sheet sheet = workbook.getSheetAt(0); 
            String insertQuery = "INSERT INTO lop (student_id, fullname, class_name) VALUES (?, ?, ?)";

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; 

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
    public void saveBaiThi(String username, String nameExam, String subject, String timeExam, String classID) {
        String selectQuery = "SELECT id FROM giangvien WHERE username = ?";
        String selectQuery_2 = "SELECT id FROM lop WHERE lop = ?";
        String insertQuery = "INSERT INTO baithi (id_giangvien, tenbaithi, monhoc, thoigian, id_lop) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {
            int giangVienId = -1;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, username);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        giangVienId = rs.getInt("id");
                    }
                }
            }
            int classsssId = -1;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery_2)) {
                selectStmt.setString(1, classID); 
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        classsssId = rs.getInt("id");
                    }
                }
            }
            if (giangVienId == -1) {
                System.out.println("Không tìm thấy giảng viên với username: " + username);
                return;
            }
            if (classsssId == -1) {
                System.out.println("Không tìm thấy lớp với ID: " + classID);
                return;
            }
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, giangVienId);
                insertStmt.setString(2, nameExam);
                insertStmt.setString(3, subject);
                insertStmt.setString(4, timeExam);
                insertStmt.setInt(5, classsssId);
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu bài thi: " + e.getMessage());
        }
    }

    public void saveCauHoi(String nameExam, String questionOrder, String questionText, String options, String correctAnswer) {
        String[] answers = options.split(",");
    	String selectQuery = "SELECT id FROM baithi WHERE tenbaithi = ?";
        String insertQuery = "INSERT INTO cauhoi (id_baithi, cauhoiso, cauhoi, dapan1, dapan2, dapan3, dapan4, dapandung) VALUES (?, ?, ?, ?, ?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb)) {
            int examId = -1;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, nameExam);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        examId = rs.getInt("id"); 
                    }
                }
            }
            if (examId != -1) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, examId); 
                    insertStmt.setString(2, questionOrder); 
                    insertStmt.setString(3, questionText); 
                    insertStmt.setString(4, answers[0]); 
                    insertStmt.setString(5, answers[1]); 
                    insertStmt.setString(6, answers[2]); 
                    insertStmt.setString(7, answers[3]); 

                    insertStmt.setString(8, correctAnswer); 
                    insertStmt.executeUpdate();
                }
            } else {
                System.out.println("Không tìm thấy bài thi với tên: " + nameExam);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lưu câu hỏi: " + e.getMessage());
        }
    }
    public String examCorrected (List<String> results, String username, String nameExam, String time) {
       List<String[]> cauhoiList = new ArrayList<>();
       String query = "SELECT cauhoiso, dapandung FROM cauhoi WHERE id_baithi = ?";
       String queryGetUserId = "SELECT id FROM sinhvien WHERE username = ?";
       String queryGetExamId = "SELECT id FROM baithi WHERE tenbaithi = ?";
       String queryInsertThongKe = "INSERT INTO thongke (id_sinhvien, id_baithi, diem, thoigianlambai, ngaylam) VALUES (?, ?, ?, ?, ?)";

       int userId = -1; 
       int examId = -1; 
       try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
            PreparedStatement pstmtUser = conn.prepareStatement(queryGetUserId)) {

           pstmtUser.setString(1, username);
           try (ResultSet rsUser = pstmtUser.executeQuery()) {
               if (rsUser.next()) {
                   userId = rsUser.getInt("id");
               }
           }

       } catch (SQLException e) {
           System.out.println("Error retrieving user ID: " + e.getMessage());
       }
       try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
            PreparedStatement pstmtExam = conn.prepareStatement(queryGetExamId)) {

           pstmtExam.setString(1, nameExam);
           try (ResultSet rsExam = pstmtExam.executeQuery()) {
               if (rsExam.next()) {
                   examId = rsExam.getInt("id");
               }
           }

       } catch (SQLException e) {
           System.out.println("Error retrieving exam ID: " + e.getMessage());
       }
       
       try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
    		     PreparedStatement pstmt = conn.prepareStatement(query)) {
    		    pstmt.setInt(1, examId);
    		    try (ResultSet rs = pstmt.executeQuery()) {
    		        while (rs.next()) {
    		            String cauhoiso = rs.getString("cauhoiso").trim();
    		            String dapandung = rs.getString("dapandung").trim();
    		            cauhoiList.add(new String[]{cauhoiso, dapandung});
    		        }
    		    }

    		} catch (SQLException e) {
    		    System.out.println("Error retrieving questions: " + e.getMessage());
    		}
       int correctCount = 0;
       for (String result : results) {
           String[] parts = result.split(": ");
           if (parts.length != 2) continue;

           String questionNumber = parts[0].replace("Câu ", "").trim();
           String selectedAnswer = parts[1].trim();
           for (String[] cauhoi : cauhoiList) {
           	 if (cauhoi[1].equalsIgnoreCase(selectedAnswer)) { 
                    correctCount++;
                }
           }
       }

       // Tính điểm
       int totalQuestions = cauhoiList.size();
       int score = (int) (((double) correctCount / totalQuestions) * 100);
       LocalDate currentDate = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       String dateAsString = currentDate.format(formatter);
       
       try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
            PreparedStatement pstmtInsert = conn.prepareStatement(queryInsertThongKe)) {

           pstmtInsert.setInt(1, userId);
           pstmtInsert.setInt(2, examId);
           pstmtInsert.setInt(3, score);
           pstmtInsert.setString(4, time);
           pstmtInsert.setString(5, dateAsString);
           pstmtInsert.executeUpdate();

       } catch (SQLException e) {
           System.out.println("Error inserting result into thongke: " + e.getMessage());
       }
       return "Bạn đã trả lời đúng " + correctCount + " câu trên tổng số " + totalQuestions + " câu.\n" + "Điểm của bạn: " + score ;
    }
    public void updateSinhVien(String id, String username, String fullname, String email, String classId) {
        String updateQuery = "UPDATE sinhvien SET username = ?, fullname = ?, email = ?, class = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            updateStmt.setString(1, username); 
            updateStmt.setString(2, fullname); 
            updateStmt.setString(3, email);    
            updateStmt.setString(4, classId); 
            updateStmt.setInt(5, Integer.valueOf(id));      
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật sinh viên thành công!");
            } else {
                System.out.println("Không tìm thấy sinh viên với ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin sinh viên: " + e.getMessage());
        }
    }
    public void updateTest(String id, String tenbaithi, String monhoc, String thoigian) {
        String updateQuery = "UPDATE baithi SET tenbaithi = ?, monhoc = ?, thoigian = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, tenbaithi); 
            updateStmt.setString(2, monhoc); 
            updateStmt.setString(3, thoigian);   
            updateStmt.setInt(4, Integer.valueOf(id));    
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật bài thi thành công!");
            } else {
                System.out.println("Không tìm thấy bài thi với ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin bài thi: " + e.getMessage());
        }
    }




}
