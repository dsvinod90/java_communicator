import java.io.*;
import java.net.Socket;

public class Client {
	public static final String HOST = "localhost";
	public static final int PORT = 3000;
	public static final String EXIT = "bye";

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(HOST, PORT);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		String fromClient = null;
		String fromServer = null;
		while (true) {
			System.out.print("You: ");
			fromClient = reader.readLine();
			dos.writeUTF(fromClient);
			dos.flush();
			fromServer = dis.readUTF();
			if (fromServer.equals(EXIT)) {
				break;
			}
			System.out.println("Server: " + fromServer);
		}
		dos.close();
		socket.close();
	}
}
