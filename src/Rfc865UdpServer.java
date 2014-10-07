import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class Rfc865UdpServer {
	public static void main(String[] args) {
		//Open UDP socket at well-known port
		DatagramSocket socket = null;
		
		try{
			socket = new DatagramSocket(17);
		}catch (SocketException e){
			System.exit(-1);
		}
		
		while(true){
			try{
				//listen for UDP request from client
				byte[] buf = new byte[512];
				DatagramPacket request = new DatagramPacket(buf, buf.length);
				socket.receive(request);
				
				//send UDP reply to client
				byte[] replyBuf = "Some quote here".getBytes("UTF-8");
				DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);
				socket.send(reply);
			}catch(IOException e){
				
			}
		}
	}
}
