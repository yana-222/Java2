package Lesson6HW;
// Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
// как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать "Привет",
// нажать Enter то сообщение должно передаться на сервер и там отпечататься в консоли.
// Если сделать то же самое на серверной стороне, сообщение соответственно передается клиенту и печатается у него в консоли.
// Есть одна особенность, которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд,
// такую ситуацию необходимо корректно обработать
//ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать второго/третьего/n-го клиентов

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static final int PORT =8189;
    private static final String IP_ADDRESS="localhost";
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       try{
        socket = new Socket(IP_ADDRESS,PORT);
        System.out.println("Client started, server socket: " + socket.getRemoteSocketAddress());
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        Thread v = new Thread(()->{
            try{
                while(true) {
                  String str = sc.nextLine();
                  out.writeUTF(str);
                   if (str.equals("/end")){
                        System.out.println("Disconnected");
                        break;
                    }

                }
            } catch (IOException | NullPointerException e){
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        v.setDaemon(true);
        v.start();
           try {
               while (true){
                   String str = in.readUTF();
                   if (str.equals("/end")) {
                       System.out.println("Server disconnected");
                       break;
                   }
                   System.out.println(formatter.format(date) + " Server: " + str);
               };
           } catch (NullPointerException e) {
               e.printStackTrace();
           }

       }catch (IOException e) {
           e.printStackTrace();
       }finally {
           try{
               socket.close();
           }catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

}

