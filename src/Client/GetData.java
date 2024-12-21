package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GetData {
	public List<String> getAllClass() {
        List<String> lop = new ArrayList<>();
		  try {
              DatagramSocket clientSocket = new DatagramSocket();
              InetAddress serverAddress = InetAddress.getByName("localhost");
              String message = "GET_ALL_CLASS:." + ":.";
              byte[] sendData = message.getBytes();
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
              clientSocket.send(sendPacket);

              // Nhận phản hồi từ server
              byte[] receiveData = new byte[1024];
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
              clientSocket.close();

              String[] classes = response.split(";");
              for (String className : classes) {
                  lop.add(className.trim());  // Thêm từng lớp vào danh sách và loại bỏ khoảng trắng
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return lop;
    }
	public List<String> getSinhVienByUserName(String name) {
        List<String> sinhvien = new ArrayList<>();
		  try {
              DatagramSocket clientSocket = new DatagramSocket();
              InetAddress serverAddress = InetAddress.getByName("localhost");
              String message = "GET_SINHVIEN:." +name+ ":.";
              byte[] sendData = message.getBytes();
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
              clientSocket.send(sendPacket);

              // Nhận phản hồi từ server
              byte[] receiveData = new byte[1024];
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
              clientSocket.close();
              
              sinhvien.add(response);
              
          } catch (Exception e) {
              e.printStackTrace();
          }
          return sinhvien;
    }
	public List<String> getBaiThiByLop(String name) {
        List<String> baithi = new ArrayList<>();
		  try {
              DatagramSocket clientSocket = new DatagramSocket();
              InetAddress serverAddress = InetAddress.getByName("localhost");
              String message = "GET_BAITHI:." +name+ ":.";
              byte[] sendData = message.getBytes();
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
              clientSocket.send(sendPacket);

              // Nhận phản hồi từ server
              byte[] receiveData = new byte[1024];
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
              clientSocket.close();
              
              System.out.println(response);
              String[] classes = response.split(";");
              for (String className : classes) {
            	  baithi.add(className.trim());  // Thêm từng lớp vào danh sách và loại bỏ khoảng trắng
            	  System.out.println(className.trim());
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return baithi;
    }
	public List<String> getCauHoiByBaiThi(String tenbaithi) {
        List<String> cauhoi = new ArrayList<>();
		  try {
              DatagramSocket clientSocket = new DatagramSocket();
              InetAddress serverAddress = InetAddress.getByName("localhost");
              String message = "GET_CAUHOI:." +tenbaithi+ ":.";
              byte[] sendData = message.getBytes();
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
              clientSocket.send(sendPacket);

              // Nhận phản hồi từ server
              byte[] receiveData = new byte[1024];
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
              clientSocket.close();

              System.out.println("sdfsdf" + response);
              String[] classes = response.split(";");
              for (String className : classes) {
            	  cauhoi.add(className.trim());  // Thêm từng lớp vào danh sách và loại bỏ khoảng trắng
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return cauhoi;
    }
	public List<String> getThongkeByUserName(String name) {
        List<String> thongke = new ArrayList<>();
		  try {
              DatagramSocket clientSocket = new DatagramSocket();
              InetAddress serverAddress = InetAddress.getByName("localhost");
              String message = "GET_THONGKE:." +name+ ":.";
              byte[] sendData = message.getBytes();
              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
              clientSocket.send(sendPacket);

              // Nhận phản hồi từ server
              byte[] receiveData = new byte[1024];
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              clientSocket.receive(receivePacket);
              String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
              clientSocket.close();
              
              String[] classes = response.split(";");
              for (String thongkee : classes) {
            	  thongke.add(thongkee.trim());  // Thêm từng lớp vào danh sách và loại bỏ khoảng trắng
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return thongke;
    }
}
