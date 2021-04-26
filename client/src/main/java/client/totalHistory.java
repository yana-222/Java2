package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.file.*;
import java.util.*;

public class totalHistory {
    Path path = Paths.get("total_history.txt");
    List<String> listHistory;
    ArrayList <String> list = new ArrayList<>();

    public totalHistory() throws IOException {
        listHistory = Files.readAllLines(path);
        list.addAll(listHistory);
    }
    public String history_100(){
        String str = "";
        for (int i = 99;i>=0;i--){
            str = str + list.get(i) + "\n";
        }
        return "History: \n" + str + "\n";
    }

}
