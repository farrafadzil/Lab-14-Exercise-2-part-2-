import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerSideApp {

	public static void main(String[] args) {
		System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side Application.");

		// Permissible port for this application
		int portNo = 8083;

		try {
			// 1. Bind a DatagramSocket object to a port number
			DatagramSocket datagramSocket = new DatagramSocket(portNo);

			// Continually listen for requests
			while (true) {
				// 2. Variable to receive data from the port
				byte[] receivedData = new byte[65535];

				// 3. Object represents a packet from the client
				DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

				// 4. Receive packet
				datagramSocket.receive(receivedPacket);

				// 5. Extract data from packet
				String sentence = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

				// 6. Perform analysis on the sentence
				int vowelCount = countVowels(sentence);
				int consonantCount = countConsonants(sentence);
				int punctuationCount = countPunctuation(sentence);

				// 7. Prepare response data
				String responseData = "Vowels: " + vowelCount + ", Consonants: " + consonantCount + ", Punctuation: " + punctuationCount;
				byte[] responseDataBytes = responseData.getBytes();

				// 8. Get the client information
				InetAddress clientAddress = receivedPacket.getAddress();
				int clientPort = receivedPacket.getPort();

				// 9. Wrap response data into a datagram packet
				DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length, clientAddress, clientPort);

				// 10. Send response to the client
				datagramSocket.send(responsePacket);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("UDPServerSideApp: End of program.");
	}

	private static int countVowels(String sentence) {
		// Count the number of vowels in the sentence and return the count
		int count = 0;
		sentence = sentence.toLowerCase();
		for (int i = 0; i < sentence.length(); i++) {
			char c = sentence.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				count++;
			}
		}
		return count;
	}

	private static int countConsonants(String sentence) {
		// Count the number of consonants in the sentence and return the count
		int count = 0;
		sentence = sentence.toLowerCase();
		for (int i = 0; i < sentence.length(); i++) {
			char c = sentence.charAt(i);
			if (c >= 'a' && c <= 'z' && !isVowel(c)) {
				count++;
			}
		}
		return count;
	}

	private static int countPunctuation(String sentence) {
		// Count the number of punctuation marks in the sentence and return the count
		int count = 0;
		for (int i = 0; i < sentence.length(); i++) {
			char c = sentence.charAt(i);
			if (isPunctuation(c)) {
				count++;
			}
		}
		return count;
	}

	private static boolean isVowel(char c) {
		// Check if a character is a vowel
		c = Character.toLowerCase(c);
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	private static boolean isPunctuation(char c) {
		// Check if a character is a punctuation mark
		return !Character.isLetterOrDigit(c) && !Character.isWhitespace(c);
	}
}
