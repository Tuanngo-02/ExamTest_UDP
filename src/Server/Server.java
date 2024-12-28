package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    
    
	private static String handleRequest(String request) throws IOException {
		 String[] requestParts = request.split(":.");
		 String command = requestParts[0];
		
		switch (command) {
		case "LOGIN":
		{
			 if (requestParts.length >= 4) {
	                String username = requestParts[1];
	                String password = requestParts[2];
	                String role = requestParts[3];
	                ServerLoginRegister serverLoginRegister = new ServerLoginRegister();
	                return serverLoginRegister.CheckLogin(username, password, role);
	            } else {
	                return "Insufficient data for LOGIN request";
	            }
		}
		case "GET_ALL_CLASS":
		{
			ServerGetData serverGetData = new ServerGetData();
			List<String> lop  =  serverGetData.getAllStudents();
			System.out.println("result "+String.join(";", lop));
		    return String.join(";", lop); 
		}
		case "GET_SINHVIEN":
		{
			String username = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> sinhvien  =  serverGetData.getSinhVienByUserName(username);
			System.out.println("result "+String.join(";", sinhvien));
		    return String.join(";", sinhvien);
		}
		case "GET_SINHVIEN_BYCLASS":
		{
			String classId = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> sinhvien  =  serverGetData.getSinhVienByClassId(classId);
			System.out.println("result "+String.join(";", sinhvien));
		    return String.join(";", sinhvien); 
		}
		case "GET_TESTINFOR":
		{
			String id_giangvien = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> test  =  serverGetData.getTestOnline(id_giangvien);
			System.out.println("result "+String.join(";", test));
		    return String.join(";", test); 
		}
		case "GET_BAITHI":
		{
			String username = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> baithi  =  serverGetData.getBaiThiByLop(username);
			System.out.println("result "+String.join(";", baithi));
		    return String.join(";", baithi); 
		}
		case "GET_CAUHOI":
		{
			String tenbaithi = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> baithi  =  serverGetData.getCauHoiByBaiThi(tenbaithi);
			System.out.println("result "+String.join(";", baithi));
		    return String.join(";", baithi); 
		}
		case "GET_THONGKE":
		{
			String username = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> thongke  =  serverGetData.getThongKeByUserName(username);
			System.out.println("result "+String.join(";", thongke));
		    return String.join(";", thongke); 
		}
		case "UPDATE_SINHVIEN":
		{
			String id = requestParts[1];
			String username = requestParts[2];
            String fullname = requestParts[3];
            String email = requestParts[4];
            String classId = requestParts[5];
            ServerSaveData saveData = new ServerSaveData();
            saveData.updateSinhVien(id, username, fullname, email, classId);
            break;
		}
		case "UPDATE_TEST":
		{
			request = request.replace(":.", ":");
			String[] parts = request.split(":");
	        String id = parts[1]; 
	        String tenbaithi = parts[2]; 
	        String monhoc = parts[3]; 
	        String thoigian = parts[4] + ":" + parts[5] + ":" + parts[6]; 
            ServerSaveData saveData = new ServerSaveData();
            saveData.updateTest(id, tenbaithi, monhoc, thoigian);
            break;
		}
		case "DELETE_SINHVIEN":
		{
			String id = requestParts[1].replaceAll("[\\[\\] ]", "");
	        String[] parts = id.split(",");  
	        List<String> listId = new ArrayList<>(Arrays.asList(parts));
            ServerDeleteData deleteData = new ServerDeleteData();
            deleteData.deleteSinhVienById(listId);
            break;
		}
		case "DELETE_TESTEXAM":
		{
			String id = requestParts[1].replaceAll("[\\[\\] ]", "");
	        String[] parts = id.split(",");  
	        List<String> listId = new ArrayList<>(Arrays.asList(parts));
            ServerDeleteData deleteData = new ServerDeleteData();
            deleteData.deleteTestExamById(listId);
            break;
		}
		case "QUESTION":
		{
			String nameExam = requestParts[1];
			String questionOrder = requestParts[2];
            String questionText = requestParts[3];
            String options = requestParts[4];
            String correctAnswer = requestParts[5];
            ServerSaveData saveData = new ServerSaveData();
            saveData.saveCauHoi(nameExam, questionOrder, questionText, options, correctAnswer);
            System.out.println(nameExam);
		}
		case "BAITHI":
		{
			String username = requestParts[1];
            String name = requestParts[2];
            String subject = requestParts[3];
            String classID = requestParts[4];
            
            String[] parts = request.split(":\\.");
            String time = parts[parts.length - 1]; 
  
            ServerSaveData saveData = new ServerSaveData();
            saveData.saveBaiThi(username,name, subject,time, classID);
            break;
		}
		case "RESULT":
		{
	        String extractedPart = request.split(":\\.")[1].trim();
	        String username = request.split(":\\.")[2].trim();
	        String nameExam = request.split(":\\.")[3].trim();
	        String[] parts = request.split(":\\.");
	        String time = parts[parts.length - 1];

	        extractedPart = extractedPart.substring(1, extractedPart.length() - 1);

	        String[] pairs = extractedPart.split(", ");
	        List<String> values = new ArrayList<>();
	        for (String pair : pairs) {
	            values.add(pair);
	        }
	        ServerSaveData serverSaveData = new ServerSaveData();
	        return serverSaveData.examCorrected(values, username, nameExam,time);
		}
		case "STATISTICAL":
		{
	        ServerGetData serverGetData = new ServerGetData();
	        List<String> thongke =  serverGetData.getListThongKe();
	        System.out.println("result "+String.join(";", thongke));
		    return String.join(";", thongke);
		}
		case "STATISTICAL_SCORE":
		{
	        ServerGetData serverGetData = new ServerGetData();
	        List<String> thongke =  serverGetData.getListThongKeDiemThi();
	        System.out.println("result "+String.join(";", thongke));
		    return String.join(";", thongke);
		}
		case "REGISTER":
			if (requestParts.length >= 7) {
                String username = requestParts[1];
                String password = requestParts[2];
                String role = requestParts[3];
                String fullname = requestParts[4];
                String email = requestParts[5];
                String classs = requestParts[6];
                ServerLoginRegister serverLoginRegister = new ServerLoginRegister();
                return serverLoginRegister.Register(username, password, fullname, role, email, classs);
            } else {
                return "Insufficient data for REGISTER request";
            }
		}
		return "";
	}
	
	 public static void main(String[] args) {
	        try (DatagramSocket socket = new DatagramSocket(9876)) {
	            byte[] receiveData = new byte[1024];
	            System.out.println("server running...");
	            
	            while (true) {
	                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	                socket.receive(receivePacket);

	                String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
	                InetAddress clientAddress = receivePacket.getAddress();
	                int clientPort = receivePacket.getPort();
	                new Thread(() -> { 
	                    try {
	                        String response = handleRequest(clientRequest);
	                        byte[] sendData = response.getBytes();
	                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
	                        socket.send(sendPacket);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }).start(); 
	            }
	        } catch (Exception e) {
	            e.printStackTrace();;
	        }
	    }
}
