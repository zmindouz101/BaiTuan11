

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public final static  int SERVER_PORT = 9000;
    public final static byte[] BUFFER = new byte[4096];
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws SocketException {
        DatagramSocket ds = null;
        List<Integer> dss = new ArrayList<Integer>();
        try {
            System.out.println("Binding to port "+ SERVER_PORT + ",Please wait...");
            ds = new DatagramSocket(SERVER_PORT);
            System.out.println("Server start...");
            System.out.println("Waiting for messages from Client...");
            while (true) {
                DatagramPacket dp = new DatagramPacket(BUFFER,BUFFER.length);
                ds.receive(dp);
                ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                dss= (List<Integer>) ois.readObject();
                Collections.sort(dss);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(dss);
                byte[] data = baos.toByteArray();
                dp = new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
                ds.send(dp);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (ds != null)
                ds.close();
        }
    }
}
