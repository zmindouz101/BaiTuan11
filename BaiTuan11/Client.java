package Database;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
 
public class Client {
    public final static String SERVER_IP = "localhost";
    public final static int SERVER_PORT = 5555;
    public static void InputData(ArrayList<Student> students) {
		int n;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Ban Co Muon Them Sinh Vien Vao CSDL Khong????");
			System.out.print("\n(1)Co\n(0)Khong\nSo Ban Chon: ");
			n = sc.nextInt();
			if (n == 1) 
			{
				Student student = new Student();
				student.InputStudent();
				students.add(student);
			}
		} while (n != 0);
	}
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        ArrayList<Student> dssv = new ArrayList<Student>();
        ArrayList<Student> dskq = new ArrayList<Student>();
        InputData(dssv);
        try {
            socket = new Socket(SERVER_IP,SERVER_PORT);
            System.out.println("Connected: " + socket);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(dssv);
            dskq = (ArrayList<Student>) ois.readObject();
            for(Student i : dskq)
            	i.OutputStudent();
        } catch (IOException | ClassNotFoundException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}