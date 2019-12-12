package Database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {
	private final String ClassName = "com.mysql.jdbc.Driver";
	private final String Url = "jdbc:mysql://localhost:3306/qlsv";
	private final String User = "root";
	private final String pass = "nguyenlinh1998";
	private Connection connection;
	public final static int SERVER_PORT = 9999;

	private void connect() {
		try {
			Class.forName(ClassName);
			connection = DriverManager.getConnection(Url, User, pass);
			System.out.println("Connect success..!");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
		} catch (SQLException e) {
			System.out.println("Error Connection");
		}
	}

	@SuppressWarnings("unused")
	private ResultSet getData() {
		ResultSet rs = null;
		String sqlCommand = "select * from dssv";
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			System.out.println("Error " + e.toString());
		}
		return rs;
	}

	@SuppressWarnings("unused")
	private void showData(ResultSet rs, ArrayList<Student> st) {
		try {
			while (rs.next()) {
				System.out.printf(
						rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getFloat(4) + "\n");
				Student student = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
				st.add(student);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private void insert(Student s) {
		String sqlCommand = "insert into dssv value(?, ?, ?, ?)";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sqlCommand);
			pst.setInt(1, s.getId());
			pst.setString(2, s.getTen());
			pst.setInt(3, s.getTuoi());
			pst.setDouble(4, s.getMark());
			if (pst.executeUpdate() > 0)
				System.out.println("Insert Success..!");
			else
				System.out.println("Insert Error..!");
		} catch (SQLException e) {
			System.out.println("Insert Error " + e.toString());
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException {
		Server myconnect = new Server();
		ArrayList<Student> dssv = new ArrayList<Student>();
		ArrayList<Student> dskq = new ArrayList<Student>();
		myconnect.connect();
		ServerSocket serverSocket = null;
		try {
			System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started: " + serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					System.out.println("Client accepted: " + socket);
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					dssv = (ArrayList<Student>) ois.readObject();
					for (Student i : dssv)
						myconnect.insert(i);
					myconnect.showData(myconnect.getData(), dskq);
					oos.writeObject(dskq);
					socket.close();
				} catch (IOException e) {
					System.err.println(" Connection Error: " + e);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}
