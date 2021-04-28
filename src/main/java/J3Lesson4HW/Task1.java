package J3Lesson4HW;
//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.
//2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1 {
    private final Object monitor = new Object();
    char currentLetter = 'g';

    public static void main(String[] args) {
        Task1 tas = new Task1();
        synchronized (tas.monitor) {
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    tas.task("A");
                }).start();

                new Thread(() -> {
                    tas.task("B");
                }).start();

                new Thread(() -> {
                    tas.task("C");
                }).start();
                tas.monitor.notifyAll();
            }
        }
    }

        public void task (String letter){
             System.out.print(letter);
        }

    }


