import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {
    private static final String SERVER_IP = "127.0.001";
    private static final int SERVER_PORT = 9091;
    public static void main( String[] args) throws IOException
    {
        Socket toComputer= new Socket(SERVER_IP,SERVER_PORT);
        // output and input stream of the server
        PrintWriter out = new PrintWriter(toComputer.getOutputStream(),true);
        BufferedReader input = new BufferedReader(new InputStreamReader(toComputer.getInputStream()));
        // end of stream declaration
        System.out.println("Connected To The Computer");

        //get the inputs from the user
        System.out.println(" Where Are You ? ");
        Scanner scanCurrent = new Scanner(System.in);
        String current = scanCurrent.nextLine();
        // destination
        Scanner scanDestination = new Scanner(System.in);
        System.out.println(" Where Are You Going ? ");
        String destination = scanDestination.nextLine();
        System.out.println("You Will Get The Recommendations ASAP, PLEASE WAIT ! ");
        out.println(current + " To "+destination);
        String serverResponse = input.readLine();
        System.out.println( "The Best Route Is: " + serverResponse);
        System.out.println("End Of Connection");

        toComputer.close();
        System.exit(0);
    }
}
