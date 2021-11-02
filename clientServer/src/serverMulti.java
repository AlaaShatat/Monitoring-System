import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class computer_handler implements Runnable
{
    Socket client ;
    public computer_handler(Socket client)
    {
        this.client = client;
    }
    @Override
    public void run()
    {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                String recommendedRoute = "Turn Right After 100 m, then turn left and Stop after 50 m.";
                System.out.println("These Recommendations Will Be Sent To The Computers: " + recommendedRoute);
                out.println(recommendedRoute);
                System.out.println("[SERVER] sent recommendations to computer, closing");

                // close the connection

                client.close();


        }
            catch (Exception e)
            {
                System.out.println("Exception ERROR");
            }




    }

}
public class serverMulti {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[SERVER] Waiting for CLIENT computer connection... ");
        while (true) {
            Socket client = listener.accept();
            System.out.println("[SERVER] connected to Computer client ");

            // System.out.println("[SERVER] Your Best Route Is:  " + getRandomRecommendation());
            //BufferedReader fromComputer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //String info = fromComputer.readLine();
            computer_handler ch = new computer_handler(client);

            // handle computers in parallel
            Thread t = new Thread(ch);
            t.start(); // create new computer client.
        }



        //listener.close();

    }
}
