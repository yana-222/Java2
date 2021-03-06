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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

public class Server {
    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;
    private TotalHistory hist;
    ExecutorService service = Executors.newCachedThreadPool();
    private static final Logger logger = java.util.logging.Logger.getLogger(Server.class.getName());
    private final Handler handler = new ConsoleHandler();

    public Server() {
        clients = new CopyOnWriteArrayList <>();
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);
       // authService = new SimpleAuthService();
        authService = new AuthServiceBD();
        try{
            server = new ServerSocket(PORT);
            logger.info("Server started");
            hist = new TotalHistory();
            while (true){
                socket = server.accept();
                logger.info("Client connected: " + socket.getRemoteSocketAddress()+socket.getLocalAddress());
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
                hist.close();
                service.shutdown();
            } catch (NullPointerException | IOException b) {
                b.printStackTrace();
            }
        }
    }

    public void broadcastMsg (ClientHandler clientHandler, String msg) { // ??????????????????????????????????
        for (ClientHandler c: clients){
            service.execute(()-> { c.sendMsg(clientHandler.getNickName() +": " + msg);
          //  System.out.println(clientHandler.getSocket() + " "  +c.getNickName());
            });
        }
    }

    public void privatMsg (ClientHandler sender, String receiver, String msg) { // ??????????????????????????????????
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

    public Logger getLogger() {
        return logger;
    }

    public Handler getHandler() {
        return handler;
    }
}
