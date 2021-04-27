package client;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
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
        int rz;
        int hist_length = 20;
        if(list.size() >hist_length){
            rz = list.size()-hist_length;
        } else {
            rz = 0;
        }
        for (int i = rz;i<list.size();i++){
            str = str + list.get(i) + "\n";
        }
        return "History: \n" + str + "\n";
    }

}
