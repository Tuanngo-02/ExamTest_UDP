package Client;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Config.Common;


public class SendDataToDB {
	public void createBaiThi (String username,String nameExam, String subject, String timeExam, String classID) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "BAITHI:." + username+":."+nameExam+":."+subject+":."+classID+":."+timeExam;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi tạo bài thi: " + e.getMessage());
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

	            Sheet sheet = workbook.getSheetAt(0); 
	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) continue; 
	                int questionOrder = (int) row.getCell(0).getNumericCellValue();
	                String questionText = row.getCell(1).getStringCellValue();
	                String questionType = row.getCell(2).getStringCellValue();
	                String options = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null;
	                Cell cell = row.getCell(4); 
	                String correctAnswer = "";
	                if (cell.getCellType() == CellType.NUMERIC) {
	                    correctAnswer = String.valueOf(cell.getNumericCellValue());  
	                } else if (cell.getCellType() == CellType.STRING) {
	                    correctAnswer = cell.getStringCellValue();
	                }
	                sendDataQuestionToDB(nameExam,questionOrder, questionText, options, correctAnswer);
	            }

	        } catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi đọc dữ liệu excel: " + e.getMessage());
			}
	}
	public void sendDataQuestionToDB (String nameExam, int questionOrder, String questionText, String options, String correctAnswer) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "QUESTION:." +nameExam+":."+questionOrder+":."+questionText+":."+options+":."+correctAnswer;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi gửi dữ liệu excel: " + e.getMessage());
			}
			finally {
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
	public void sendDataResultToDB (List<String> result,String time,String username, String tenbaithi, ResponseCallback callback) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "RESULT:." +result+":."+username+":."+tenbaithi+":."+time;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();

                callback.onResponseReceived(response);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi gửi kết quả: " + e.getMessage());
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
	public void updateSinhVien (String id,String username, String fullName, String email, String classID) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "UPDATE_SINHVIEN:." + id+":."+username+":."+fullName+":."+email+":."+classID;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();

				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi cập nhật: " + e.getMessage());
			}
			finally {
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
	public void deleteSinhVienById (List<String> id) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "DELETE_SINHVIEN:." + id;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi xóa: " + e.getMessage());
			}
			finally {
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
	public void deleteTestExamById (List<String> id) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "DELETE_TESTEXAM:." + id;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		      
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi xóa: " + e.getMessage());
			}
			finally {
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
	public void updateTestExam (String id,String tenbaithi, String monhoc, String time) {
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName(Common.IPCONFIG.getIpAddress());
		        String message = "UPDATE_TEST:." + id+":."+tenbaithi+":."+monhoc+":."+time;
		        byte[] sendData = message.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		        clientSocket.send(sendPacket);
		       
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientSocket.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi khi cập nhật: " + e.getMessage());
			}
			finally {
	            if (clientSocket != null && !clientSocket.isClosed()) {
	                clientSocket.close();
	            }
	        }
		});

        thread.start();
	}
}
