import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer implements TCPConnectionListener {

    private final List<TCPConnection> connections = new ArrayList<>();


    private ChatServer() {
        System.out.println("Server running...");

        try(ServerSocket serverSocket = new ServerSocket(8189)) {

            while (true) {
                try {
                    // serverSocket.accept() - объект сокета в приходящем соединении
                    // метод accept - Ждет новое соединение
                    new TCPConnection(this, serverSocket.accept());
                } catch (Exception e) {
                    System.out.println("TCPConnection exception : " + e);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        new ChatServer();
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnection("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnection(value);
    }

    @Override
    public synchronized void onDisconect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnection("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onExceptioon(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendToAllConnection(String value) {
        System.out.println(value);

        System.out.println("Conncetion N = " + connections.size());

        for (TCPConnection connection : connections) {
            connection.sendString(value);
        }
    }
}
