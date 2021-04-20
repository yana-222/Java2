package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {
        private Server server;
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String nickName;
        private ArrayList <ClientHandler> recipients;

    public ClientHandler(Server server, Socket socket){
          try {
              this.server= server;
              this.socket = socket;
              in = new DataInputStream(socket.getInputStream());
              out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try{
                    while(true){
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            break;
                        }
                        if (str.startsWith("/auth")){
                           String[] token = str.split("\\s+",3);
                           // split разбивает строку по пробелам на отд. строки;
                           // с плюсом сворачивается любое количество пробелов;
                            String newNick = server
                                    .getAuthService()
                                    .getNickNameByLoginAndPassword(token[1],token[2]);
                            if(newNick != null){
                                nickName = newNick;
                                sendMsg("/auth_ok " + nickName);
                                server.subscribe(this);
                                System.out.println(server.getClients());
                                System.out.println("Client authorized, nick: " +nickName + ", Address: " + socket.getRemoteSocketAddress());
                                break;
                            } else {
                                sendMsg("Wrong pare: login or password");
                            }

                        }
                    }


                    while(true){
                        String str = in.readUTF();
                        if (str.equals("/end")){
                            out.writeUTF("/end");
                            break;
                        }
                        if (str.startsWith("/w")){
                            String[] token = str.split("\\s+",3);
                            String stri = token[2];
                            this.sendMsg(nickName + " -> " + token[1] + ": " + stri);
                            for (ClientHandler c: server.getClients()){
                                if(c.nickName.equals(token[1])){
                                    c.sendMsg(nickName + " -> " + token[1] + ": " + stri);
                                }
                            }

                        } else {
                            server.broadcastMsg(this, str);
                        }
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    server.unsubscribe(this);
                    System.out.println("Client " + socket.getRemoteSocketAddress()+" disconnected");
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

        public String getNickName() {
            return nickName;
        }

        public Socket getSocket() {
            return socket;
        }
    }
