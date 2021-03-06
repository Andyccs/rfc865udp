import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Name: Andy Chong Chin Shin
 * Group: TS2
 * IP Address: <YourClientIPAddressHere>
 */
public class Rfc865UdpClient {
	public static void main(String[] args) {
		//Open UDP Socket
		DatagramSocket socket = null;
		try{
			socket = new DatagramSocket();
			InetAddress IpAddress = InetAddress.getByName("localhost");
			socket.connect(IpAddress, 17);
			
		}catch(SocketException e){
			e.printStackTrace();
			System.exit(-1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		try {
			//send UDP request to server
			byte[] buf = "Andy Chong Chin Shin, TS2, <YourClientIPAddressHere>".getBytes("UTF-8");
			DatagramPacket request = new DatagramPacket(buf, buf.length);       
			socket.send(request);
			
			//receive UDP reply from server
			byte[] replyBuf = new byte[512];
			DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);
			socket.receive(reply);
			
			String quote = new String(replyBuf);
			System.out.println(quote);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			socket.close();
		}
	}
}
