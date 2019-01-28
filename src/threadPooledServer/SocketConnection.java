package threadPooledServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * initiates the inputstream which the data comes from.
 */
class SocketConnection {
    BufferedReader bufferedReader = null;

    SocketConnection(Socket socket){
        //7789
        try {
            this.bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Exception caught when trying to load input stream ");
            System.out.println(e.getMessage());
        }
    }
}
