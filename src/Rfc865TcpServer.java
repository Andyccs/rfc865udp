package lab2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Rfc865TcpServer {
    public static void main(String[] argv){
        //open TCP socket at well-known port
        ServerSocket parentSocket = null;
        try{
            parentSocket = new ServerSocket(17);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        while(true){
            try{
                //listen to establish TCP connection with clnt
                Socket childSocket = parentSocket.accept();
                
                //create new thread to handle client connection
                ClientHandler client = new ClientHandler(childSocket);
                Thread thread = new Thread(client);
                thread.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    static class ClientHandler implements Runnable{

        private Socket socket;
        
        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try{
                //receive TCP request from client
                InputStream is = socket.getInputStream();

                byte[] request = new byte[512];
                is.read(request);
                
                String requestString = new String(request);
                System.out.println(requestString);
                
                //send TCP reply to client
                
                OutputStream os = socket.getOutputStream();
                byte[] quoteByte = "Some quote here".getBytes();
                os.write(quoteByte);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
    }
}
