package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRegister {
	public void Register(String username,String password,String fullname,String role,String email,String classs,ResponseCallback callback) throws Exception {
		
		Thread thread = new Thread(() -> {
			DatagramSocket clientSocket=null;
			try {
				
				//Gửi yêu cầu đến server
				clientSocket = new DatagramSocket();
		        InetAddress serverAddress = InetAddress.getByName("localhost");
		        String message = "REGISTER:." + username+":."+password+":."+role+":."+fullname+":."+email+":."+classs;
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
                callback.onResponseReceived(response);
				
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
