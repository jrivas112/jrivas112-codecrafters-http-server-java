import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    // hr You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    
    try {
      ServerSocket serverSocket = new ServerSocket(4221);
    
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      serverSocket.setReuseAddress(true);
    
      //create new socket for client if a client attempt a conenction
      Socket clientSocket = serverSocket.accept(); // Wait for connection from client.
      
      System.out.println("accepted new connection");
      //clientSocket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
      InputStreamReader streamR =  new InputStreamReader(clientSocket.getInputStream());
      BufferedReader reader = new BufferedReader(streamR);

      if(reader.readLine().equals("/index.html")){
        clientSocket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
      }else{
        clientSocket.getOutputStream().write("HTfTP/1.1 404 Not Found\r\n\r\n".getBytes());
      }
    
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
