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
        System.out.println(list);
    }
    public String history_100(){
        String str = "";
        int sz = 0;
        if (list.size() > 99) {
            sz = list.size() - 99;
        }
        for (int i = sz;i<list.size();i++){
            str = str + list.get(i) + "\n";
        }
        return "History: \n" + str + "\n";
    }

}
