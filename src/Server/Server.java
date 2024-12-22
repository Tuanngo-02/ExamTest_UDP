package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Server {
    
    
	private static String handleRequest(String request) throws IOException {
		System.out.println(request);
		/*
		 * [0]: yêu cầu
		 * [1]: username
		 * [2]: password
		 * [3]: role
		 * [4]: fullname
		 * [5]: email
		 * [6]: classs
		*/
		 String[] requestParts = request.split(":.");
		 String command = requestParts[0];
//		 String username = requestParts[1];
//		 String password = requestParts[2];
//		 String role = requestParts[3];
//		 String fullname = requestParts[4];
//		 String email = requestParts[5];
//		 String classs = requestParts[6];
//		 
//		 String className = requestParts[7];
		
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
			System.out.println(String.join(";", lop));
		    return String.join(";", lop); // Trả về dữ liệu dưới dạng chuỗi
		}
		case "GET_SINHVIEN":
		{
			String username = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> sinhvien  =  serverGetData.getSinhVienByUserName(username);
			System.out.println("chuoi"+String.join(";", sinhvien));
		    return String.join(";", sinhvien); // Trả về dữ liệu dưới dạng chuỗi
		}
		case "GET_BAITHI":
		{
			String username = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> baithi  =  serverGetData.getBaiThiByLop(username);
			System.out.println("chuoi"+String.join(";", baithi));
		    return String.join(";", baithi); // Trả về dữ liệu dưới dạng chuỗi
		}
		case "GET_CAUHOI":
		{
			String tenbaithi = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> baithi  =  serverGetData.getCauHoiByBaiThi(tenbaithi);
			System.out.println("ssdsdfsdf"+String.join(";", baithi));
		    return String.join(";", baithi); // Trả về dữ liệu dưới dạng chuỗi
		}
		case "GET_THONGKE":
		{
			String username = requestParts[1];
			ServerGetData serverGetData = new ServerGetData();
			List<String> thongke  =  serverGetData.getThongKeByUserName(username);
			System.out.println("ssdsdfsdf"+String.join(";", thongke));
		    return String.join(";", thongke); // Trả về dữ liệu dưới dạng chuỗi
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
//			if (requestParts.length >= 7) {
//                
//            } else {
//                return "Insufficient data for QUESTION request";
//            }
		}
		case "BAITHI":
		{
			String username = requestParts[1];
            String name = requestParts[2];
            String subject = requestParts[3];
            String classID = requestParts[4];
            
            String[] parts = request.split(":\\.");
            String time = parts[parts.length - 1]; 
            System.out.println("Thời gian: " + time);
  
            ServerSaveData saveData = new ServerSaveData();
            saveData.saveBaiThi(username,name, subject,time, classID);
//			if (requestParts.length >= 2) {
//                
////                System.out.println(username);
//            } else {
//                return "Insufficient data for QUESTION request";
//            
            break;
		}
		case "RESULT":
		{
	        String extractedPart = request.split(":\\.")[1].trim();
	        String username = request.split(":\\.")[2].trim();
	        String nameExam = request.split(":\\.")[3].trim();
	        String[] parts = request.split(":\\.");
	        String time = parts[parts.length - 1];

	        System.out.println(time);
	        // Loại bỏ dấu ngoặc vuông đầu và cuối
	        extractedPart = extractedPart.substring(1, extractedPart.length() - 1);

	        // Tách thành từng phần tử dựa trên dấu ","
	        String[] pairs = extractedPart.split(", ");

	        // Tạo danh sách lưu kết quả
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
	        System.out.println("ssdsdfsdf"+String.join(";", thongke));
		    return String.join(";", thongke);
		}
		case "STATISTICAL_SCORE":
		{
	        ServerGetData serverGetData = new ServerGetData();
	        List<String> thongke =  serverGetData.getListThongKeDiemThi();
	        System.out.println("ssdsdfsdf"+String.join(";", thongke));
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
	                // Nhận một gói tin
	                socket.receive(receivePacket);

	                String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
	                InetAddress clientAddress = receivePacket.getAddress();
	                int clientPort = receivePacket.getPort();

	                // Tạo một thread mới để xử lý yêu cầu
	                new Thread(() -> { 
	                    try {
	                        // Gửi phản hồi về Client
	                        String response = handleRequest(clientRequest);
	                        byte[] sendData = response.getBytes();
	                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
	                        socket.send(sendPacket);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }).start(); // Khởi động thread mới
	            }
	        } catch (Exception e) {
	            e.printStackTrace();;
	        }
	    }
}
