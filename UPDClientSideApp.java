import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientSideApp {

	public static void main(String[] args) {
		System.out.println("UDPClientSideApp: Demonstration of UDP Client-Side Application.");

		try {
			// 1. Define server port number and address
			int portNo = 8083;
			InetAddress ip = InetAddress.getLocalHost();

			// 2. Prepare and transform data into bytes
			String sentence = "Hello, how are you?";
			byte[] requestData = sentence.getBytes();

			// 3. Wrap data in a datagram packet
			DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, ip, portNo);

			// 4. Create the socket object to transmit the data
			DatagramSocket datagramSocket = new DatagramSocket();

			// 5. Send the datagram packet
			datagramSocket.send(requestPacket);
			System.out.println("Sending sentence: " + sentence);

			// 6. Create a buffer to receive the response
			byte[] responseData = new byte[65535];

			// 7. Packet to receive data
			DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);

			// 8. Receive data
			datagramSocket.receive(responsePacket);

			// 9. Extract data
			String response = new String(responsePacket.getData(), 0, responsePacket.getLength());

			// 10. Display the response
			System.out.println("Received analysis: " + response);

			datagramSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("UDPClientSideApp: End of program.");
	}
}
