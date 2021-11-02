import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ServerClass {
    private static final int PORT = 9090;
    private static String[] recommendations = {"Please Turn Left, after 100 metres turn left Again after 200 metres Stop!","Please Turn Right, after 50 metres turn left, after 50 metres Stop!",};
    public static void main(String[] args) throws IOException
    {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[SERVER] Waiting for CLIENT computer connection... ");
        Socket client = listener.accept();
        System.out.println("[SERVER] connected to Computer client ");

      // System.out.println("[SERVER] Your Best Route Is:  " + getRandomRecommendation());
        //BufferedReader fromComputer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //String info = fromComputer.readLine();
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        String recommendedRoute = getRandomRecommendation();
        System.out.println("These Recommendations Will Be Sent To The Computers: " + recommendedRoute );
        out.println(recommendedRoute);
        System.out.println("[SERVER] sent recommendations to computer, closing");

        // close the connection
        listener.close();
        client.close();

    }
    public static String getRandomRecommendation()
    {
        String name = recommendations[(int)(Math.random() * recommendations.length)];
        return name;
    }
}
