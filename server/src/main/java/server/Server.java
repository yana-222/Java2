package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;
    private TotalHistory hist;

    public Server() {
        clients = new CopyOnWriteArrayList <>();
       // authService = new SimpleAuthService();
        authService = new AuthServiceBD();
        try{
            server = new ServerSocket(PORT);
            System.out.println("Server started");
            hist = new TotalHistory();
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
          //  System.out.println(clientHandler.getSocket() + " "  +c.getNickName());

        }
    }
    public void privatMsg (ClientHandler sender, String receiver, String msg) { // широковещательный
        String message = String.format("%s -> %s : %s", sender.getNickName(),receiver,msg);
        for (ClientHandler c: clients){
            if (c.getNickName().equals(receiver)) {
                c.sendMsg(message);
                if(sender.equals(c)) return;
                sender.sendMsg(message);
                return;
            }
        }
        sender.sendMsg("User " + receiver + " not found");
    }
    public void subscribe (ClientHandler clientHandler){
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void unsubscribe (ClientHandler clientHandler){
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public List<ClientHandler> getClients() {
        return clients;
    }
    public boolean isLoginAuthenticated (String login){
        for (ClientHandler c : clients) {
            if (c.getLogin().equals(login)){
                return true;

            }
        }
        return false;
    }
    public void broadcastClientList (){
        StringBuilder sb = new StringBuilder("/clientslist");
        for (ClientHandler c : clients){
            sb.append(" ").append(c.getNickName());
        }
        String msg = sb.toString();

        for (ClientHandler c : clients){
            c.sendMsg(msg);
        }
        System.out.println(msg);
    }

    public TotalHistory getHist() {
        return hist;
    }
}
