package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientLogin {
	 public void Login(String username, String password, String role, ResponseCallback callback) {
	        Thread thread = new Thread(() -> {
	            try {
	                DatagramSocket clientSocket = new DatagramSocket();
	                InetAddress serverAddress = InetAddress.getByName("localhost");
	                String message = "LOGIN:." + username + ":." + password + ":." + role+":.0"+":.0"+":.0";
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
	                e.printStackTrace();
	            }
	        });

	        thread.start(); // Bắt đầu thread
	    }
}