package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Rfc865TcpClient {
    public static void main(String[] argv){
        
        //establish TCP connection with server
        Socket socket = null;
        try{
            socket = new Socket();
            InetAddress IpAddress = InetAddress.getByName("localhost");
            SocketAddress addr = new InetSocketAddress(IpAddress, 17);
            socket.connect(addr);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        
        try{
            //send TCP request to server
            OutputStream os = socket.getOutputStream();
            byte[] buf = "Andy Chong Chin Shin, TS2, 172.21.144.45".getBytes("UTF-8");
            os.write(buf);
            
            //receive TCP reply from server
            InputStream is = socket.getInputStream();
            byte[] quoteBuf = new byte[512];
            is.read(quoteBuf);
            
            String quote = new String(quoteBuf);
            System.out.println(quote);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
