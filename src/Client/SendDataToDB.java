package Client;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class SendDataToDB {
	public void createBaiThi (String username,String nameExam, String subject, String classID) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				//Gửi yêu cầu đến server
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName("localhost");
		        String message = "BAITHI:." + username+":."+nameExam+":."+subject+":."+classID;
		        System.out.println(message);
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
		        // Nhận phản hồi từ server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();

                // Gọi callback để gửi response
//                callback.onResponseReceived(response);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi kiểm tra đăng ký123: " + e.getMessage());
			}
			finally {
	            // Đảm bảo đóng socket trong finally block
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
	public void excel(String nameExam, String subject, String url) {
		try (FileInputStream fis = new FileInputStream(new File(url));
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) continue; // Bỏ qua dòng tiêu đề

	                // Đọc dữ liệu từ từng cột trong Excel
	                int questionOrder = (int) row.getCell(0).getNumericCellValue();
	                String questionText = row.getCell(1).getStringCellValue();
	                String questionType = row.getCell(2).getStringCellValue();
	                String options = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null;
	                Cell cell = row.getCell(4); 
	                String correctAnswer = "";
	                if (cell.getCellType() == CellType.NUMERIC) {
	                    correctAnswer = String.valueOf(cell.getNumericCellValue());  // Chuyển giá trị số thành chuỗi
	                } else if (cell.getCellType() == CellType.STRING) {
	                    correctAnswer = cell.getStringCellValue();
	                }
	                sendDataQuestionToDB(nameExam,questionOrder, questionText, options, correctAnswer);
		            System.out.println(questionOrder+ questionText + options + correctAnswer);
	            }

	        } catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi kiểm tra đăng ký123: " + e.getMessage());
			}
	}
	public void sendDataQuestionToDB (String nameExam, int questionOrder, String questionText, String options, String correctAnswer) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				//Gửi yêu cầu đến server
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName("localhost");
		        String message = "QUESTION:." +nameExam+":."+questionOrder+":."+questionText+":."+options+":."+correctAnswer;
		        System.out.println(message);
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
		        // Nhận phản hồi từ server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();

                // Gọi callback để gửi response
//                callback.onResponseReceived(response);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi kiểm tra đăng ký123: " + e.getMessage());
			}
			finally {
	            // Đảm bảo đóng socket trong finally block
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
}
