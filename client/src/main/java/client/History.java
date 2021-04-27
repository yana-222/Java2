package client;
// 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
// Для каждой учетной записи файл с историей должен называться history_[login].txt.
// (Например, history_login1.txt, history_user111.txt)
// 2.** После загрузки клиента показывать ему последние 100 строк истории чата

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History {
   // RandomAccessFile f;
    FileOutputStream out;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public History(String nick) throws IOException {
      // f = new RandomAccessFile("history_users/history_" + getWay(nick),"rw");
      // f.seek(f.length());
        out = new FileOutputStream ("history_users/history_" + getWay(nick),true);
    }

    public void write (String msg) throws IOException {
        String time = actualTime();
        String message = time + " " + msg +"\n";
       // f.writeBytes(time + " " + msg+"\n");
        out.write(message.getBytes(StandardCharsets.UTF_8));
    }

    public String getWay(String nick) {
        return nick + ".txt";
    }
    public void close(){
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String actualTime () {
        LocalDateTime date = LocalDateTime.now();
        return  (String) formatter.format(date);
    }

}
