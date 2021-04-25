package server;

import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ghgh {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        RandomAccessFile f;
        ArrayList<String> totalHistory = new ArrayList<>();
        String strin = "Server started at time " + (String) formatter.format(date);
        totalHistory.add(strin);

    }
}
