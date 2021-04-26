package client;
// 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
// Для каждой учетной записи файл с историей должен называться history_[login].txt.
// (Например, history_login1.txt, history_user111.txt)
// 2.** После загрузки клиента показывать ему последние 100 строк истории чата

import java.io.*;
import java.nio.charset.StandardCharsets;

public class History {
    private String way;
    RandomAccessFile f;

    public History(String nick) throws IOException {
       f = new RandomAccessFile("history_users/history_" + getWay(nick),"rw");
    }

    public void write (String msg) throws IOException {
        f.writeBytes(msg+"\n");
    }

    public String getWay(String nick) {
        return way = nick + ".txt";
    }
    public void close(){
        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
