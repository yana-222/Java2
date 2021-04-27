package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickName;
    private String login;

    public ClientHandler(Server server, Socket socket){
        try {
            this.server= server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try{
                    socket.setSoTimeout(5000);

                    while(true){
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            throw new RuntimeException("Client disconnected");
                        }

                        if (str.startsWith("/auth")){
                            String[] token = str.split("\\s+",3);
                            if(token.length <3) continue;
                            // split разбивает строку по пробелам на отд. строки;
                            // с плюсом сворачивается любое количество пробелов;
                            String newNick = server
                                    .getAuthService()
                                    .getNickNameByLoginAndPassword(token[1],token[2]);
                            if(newNick != null) {
                                login = token[1];
                                if (!server.isLoginAuthenticated(login)) {
                                    nickName = newNick;
                                    sendMsg("/auth_ok " + nickName);
                                    server.subscribe(this);
                                  //  System.out.println(server.getClients());
                                    System.out.println("Client authorized, nick: " + nickName + ", Address: " + socket.getRemoteSocketAddress());
                                    socket.setSoTimeout(0);
                                    break;

                                } else {
                                    sendMsg("The login is already in use");
                                }
                            } else {
                                sendMsg("Wrong pare: login or password");
                            }

                        }

                        if (str.startsWith("/reg")) {
                            String[] token2 = str.split("\\s+", 4);
                            if(token2.length <4) continue;
                            boolean b = server.getAuthService().registration(token2[1],token2[2],token2[3]);
                            if (b) {
                                sendMsg("/reg_ok");
                            } else {
                                sendMsg("/reg_no");
                            }
                        }
                        if (str.startsWith("/change")) {
                            String[] token3 = str.split("\\s+", 4);
                            if(token3.length <4) continue;
                            boolean c = server.getAuthService().changeNick(token3[1],token3[2],token3[3]);
                            if (c) {
                                sendMsg("/change_ok");
                            } else {
                                sendMsg("/change_no");
                            }
                        }

                    }

                    while(true) {
                        String str = in.readUTF();

                        if (str.startsWith("/")) {

                            if (str.equals("/end")) {
                                out.writeUTF("/end");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                String[] token = str.split("\\s+",3);
                                server.privatMsg(this,token[1],token[2]);
                            }

                      /*  if (str.startsWith("/w")){
                            String[] token = str.split("\\s+",3);
                            String stri = token[2];
                            this.sendMsg(nickName + " -> " + token[1] + ": " + stri);
                            for (ClientHandler c: server.getClients()){
                                if(c.nickName.equals(token[1])){
                                    c.sendMsg(nickName + " -> " + token[1] + ": " + stri);
                                }
                            } */ // мое решение дз 7
                        } else {
                            server.getHist().write(nickName + ": "+ str);
                            server.broadcastMsg(this, str);
                        }
                    }
                } catch (SocketTimeoutException r){
                    System.out.println("Client "+socket.getRemoteSocketAddress() + " disconnected by timeout");
                    sendMsg("/end");
                }

                catch (RuntimeException r){
                    System.out.println(r.getMessage());
                } catch (IOException | SQLException | ClassNotFoundException e){
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

    public String getLogin() {
        return login;
    }

}
