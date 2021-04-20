package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public Server() {
        clients = new CopyOnWriteArrayList <>();
        authService = new SimpleAuthService();
        try{
            server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true){
                socket = server.accept();
                System.out.println("Client connected: " + socket.getRemoteSocketAddress()+socket.getLocalAddress());
                new ClientHandler(this,socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (NullPointerException | IOException b) {
                b.printStackTrace();
            }
            try {
                server.close();
            } catch (NullPointerException | IOException b) {
                b.printStackTrace();
            }

        }
    }

    public void broadcastMsg (ClientHandler clientHandler, String msg) { // широковещательный
        for (ClientHandler c: clients){
            c.sendMsg(clientHandler.getNickName() +": " + msg);
            System.out.println(clientHandler.getSocket() + " "  +c.getNickName());

        }
    }
    public void subscribe (ClientHandler clientHandler){
    clients.add(clientHandler);
    }

    public void unsubscribe (ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public List<ClientHandler> getClients() {
        return clients;
    }
}
