import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class computerMulti {
    private static final int PORT = 9091;
    private static final String SERVER_IP = "127.0.001";
    private static final int SERVER_PORT_CENTRAL = 9090;
    static class client_handler implements Runnable
    {
        Socket clientDriver ;
        Socket toServer ;
        public client_handler(Socket clientDriver, Socket toServer)
        {
            this.clientDriver = clientDriver ;
            this.toServer = toServer ;
        }
        @Override
        public void run()
        {

            try {
                // getting the two locations from the user
                BufferedReader fromDriver = new BufferedReader(new InputStreamReader(clientDriver.getInputStream()));
                String location = fromDriver.readLine();
                System.out.println("The Driver Is Going From " + location);

                // sensors and reading
                String sensor = "traffic";

                // time to send data to the servers to get the recommendation

                PrintWriter out = new PrintWriter(clientDriver.getOutputStream(), true);
                out.println("Turn Right After 100 m, then turn left and Stop after 50 m.");
                System.out.println("The Computer Took The Recommendations From The Server");
                System.out.println("The Computer Sent The Recommendations To The Driver");
                /////////
                PrintWriter outTOServer = new PrintWriter(toServer.getOutputStream(), true); // sending info to the server
                BufferedReader input = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
                String serverResponse = input.readLine();
                outTOServer.println(serverResponse);
                //outTOServer.println(serverResponse);
                System.out.println("The server sent these recommendations: " + serverResponse);
                // close connection
                clientDriver.close();


            }
            catch (Exception e)
            {
                System.out.println("Exception ERROR");
            }



        }

    }
    public static void main ( String [] args) throws IOException
    {
        ServerSocket computer = new ServerSocket(PORT);
        Socket toServer = new Socket(SERVER_IP, SERVER_PORT_CENTRAL);
        System.out.println("[SERVER] Waiting for driver connection... ");
        while(true) {
            try {
                Socket clientDriver = computer.accept();
                System.out.println("[SERVER] connected to driver ");

                // start threading
                client_handler var = new client_handler(clientDriver, toServer);

                // handle computers in parallel
                Thread t = new Thread(var);
                t.start(); // create new computer client.

            }
            catch (Exception e)
            {
                System.out.println(" ERROR");
            }

        }

        // close connection
        //toServer.close();
        //computer.close();

        //System.exit(0);




    }
}
