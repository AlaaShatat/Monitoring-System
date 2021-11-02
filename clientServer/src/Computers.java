import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Computers {
    private static final int PORT = 9091;
    private static final String SERVER_IP = "127.0.001";
    private static final int SERVER_PORT_CENTRAL = 9090;
    public static void main ( String [] args) throws IOException
    {
        ServerSocket computer = new ServerSocket(PORT);
        Socket toServer = new Socket(SERVER_IP, SERVER_PORT_CENTRAL);
        System.out.println("[SERVER] Waiting for driver connection... ");
        Socket clientDriver = computer.accept();
        System.out.println("[SERVER] connected to driver ");

        // getting the two locations from the user
        BufferedReader fromDriver = new BufferedReader(new InputStreamReader(clientDriver.getInputStream()));
        String location = fromDriver.readLine();
        System.out.println("The Driver Is Going From " + location);

        // sensors and reading
        String sensor = "traffic";

        // time to send data to the servers to get the recommendation

           PrintWriter out = new PrintWriter(clientDriver.getOutputStream(),true);
           PrintWriter outTOServer = new PrintWriter(toServer.getOutputStream(),true); // sending info to the server
          // outTOServer.println(sensor +" "+ location);
           BufferedReader input = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
           String serverResponse = input.readLine();
           out.println(serverResponse);
           System.out.println( "The server sent these recommendations: " + serverResponse);
           System.out.println("The Computer Took The Recommendations From The Server");
           System.out.println("The Computer Sent The Recommendations To The Driver");
           computer.close();
           clientDriver.close();
           toServer.close();
           //System.exit(0);




    }
}
