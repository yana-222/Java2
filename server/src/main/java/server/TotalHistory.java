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
    String time = (String) formatter.format(date);

    public TotalHistory() throws FileNotFoundException {
        f = new RandomAccessFile("total_history.txt","rw");
    }

    public void write (String msg) throws IOException {
        f.writeBytes(time + " " + msg+"\n");
    }

    public void close(){
        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
