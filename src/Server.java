import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final int PORT = 3000;
	public static final String EXIT = "exit";

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket clientConnection = serverSocket.accept();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream dis = new DataInputStream(clientConnection.getInputStream());
		DataOutputStream dos = new DataOutputStream(clientConnection.getOutputStream());
		String fromClient = null;
		String fromServer = null;
		while (true) {
			fromClient = dis.readUTF();
			System.out.println("Client: " + fromClient);
			if (fromClient.equals(EXIT)) {
				break;
			}
			System.out.print("You: ");
			fromServer = reader.readLine();
			dos.writeUTF(fromServer);
			dos.flush();
		}
		dos.writeUTF(Client.EXIT);
		dos.flush();
		dos.close();
		serverSocket.close();
	}
}
