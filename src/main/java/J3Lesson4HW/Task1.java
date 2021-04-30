package J3Lesson4HW;
//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.
//2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private static final Object monitor = new Object();
    static volatile  char letter ='A';
    static char letterA = 'A';
    static char letterB = 'B';
    static char letterC = 'C';
    public static void main(String[] args) throws InterruptedException {
        Task1 tas = new Task1();
          //  tas.task(letterA,letterB);
          //  tas.task(letterB, letterC);
          //  tas.task(letterC, letterA);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor){
                    while (letter != 'A') {
                        try {
                            tas.monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print('A');
                    letter = 'B';
                    monitor.notifyAll();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor){
                    while (letter != 'B') {
                        try {
                            tas.monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print('B');
                    letter = 'C';
                    monitor.notifyAll();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor){
                    while (letter != 'C') {
                        try {
                            tas.monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print('C');
                    letter = 'A';
                    monitor.notifyAll();
                }
            }
        }).start();
    }

    public void  task(char let, char next){
            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                   synchronized (monitor){
                        while (let != letter) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(let);
                        letter = next;
                        monitor.notifyAll();
                    }
                }
            }).start();
        }

 }


