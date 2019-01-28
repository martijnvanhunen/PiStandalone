package threadPooledServer;

import resources.ExecutorServices;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


class ConnectionManager {
    private final Set connections = Collections.synchronizedSet(new HashSet());

    ConnectionManager(){}

    void addConnection(SocketConnection connection){
        synchronized (connections) {
            //connections.add(connection);
        }
        InputReader reader = new InputReader(connection.bufferedReader);
        //800 sockets need to receive 165 lines/minute, receiving 1 line = ca 2500 nanoseconds = 330 milliseconds/second for receiving data
        reader.setScheduledFuture(ExecutorServices.SOCKET_READER_EXECUTOR.scheduleAtFixedRate(reader, 5, 7, TimeUnit.MILLISECONDS));
    }
}
