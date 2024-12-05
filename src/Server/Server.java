package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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