package server;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TotalHistory {
    private LocalDateTime date = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    RandomAccessFile f;
    FileOutputStream out;

    public TotalHistory() throws IOException {
       // f = new RandomAccessFile("total_history.txt","rw");
       // f.seek(f.length()-1);
        out = new FileOutputStream ("total_history.txt",true);
    }

    public void write (String msg) throws IOException {
        String time = actualTime();
        String message = time + " " + msg +"\n";
       // f.writeBytes(time + " " + msg+"\n");
        out.write(message.getBytes(StandardCharsets.UTF_8));
    }

    public String actualTime () {
        date = LocalDateTime.now();
        return  (String) formatter.format(date);
    }
    public void close(){
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
