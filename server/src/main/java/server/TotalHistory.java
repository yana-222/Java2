package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TotalHistory {
    private LocalDateTime date = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    RandomAccessFile f;
    String time=  (String) formatter.format(date);

    public TotalHistory() throws FileNotFoundException {
        f = new RandomAccessFile("total_history.txt","rw");
    }

    public void write (String msg) throws IOException {
        f.writeBytes(time + " " + msg+"\n");
    }
}
