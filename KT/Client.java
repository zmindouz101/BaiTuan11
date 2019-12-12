

import java.io.*;
import  java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public final  static  int SERVER_PORT = 9000;
    public  final static String hostName = "localhost";
    public final static byte[] BUFFER = new byte[4096];
    @SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) throws IOException {
        DatagramSocket ds = null;
        List<Integer> dss = new ArrayList<Integer>();
        List<Integer> dskq = new ArrayList<Integer>();
        System.out.println("Vui Long Nhap Day So");
        int kt = 1;
        while (kt!=0)
        {
            System.out.print("Moi Nhap: ");
            @SuppressWarnings("resource")
			int n = new Scanner(System.in).nextInt();
            dss.add(n);
            System.out.println("(1)Nhap\n(0)Stop ");
            kt = new Scanner(System.in).nextInt();
        }
        try{
            ds = new DatagramSocket();
            System.out.println("Client bat dau khoi tao...");
            InetAddress SERVER_IP = InetAddress.getByName(hostName);
            System.out.println("Send list Integer...");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(dss);
            byte[] data = baos.toByteArray();
            DatagramPacket dp = new DatagramPacket(data,data.length,SERVER_IP,SERVER_PORT);
            ds.send(dp);
            dp = new DatagramPacket(BUFFER, BUFFER.length);
            ds.receive(dp);
            ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            dskq = (List<Integer>) ois.readObject();
            System.out.println("Danh sach tra ve cho Client");
            for(int i : dskq)
                System.out.print(i+" ");
        } catch (SocketException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}
