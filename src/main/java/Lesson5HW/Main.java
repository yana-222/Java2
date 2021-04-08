package Lesson5HW;
// Необходимо написать два метода, которые делают следующее:
// 1. Создают одномерный длинный массив, например:
// static final int SIZE = 10 000 000;
// static final int HALF = size / 2;
// float[] arr = new float[size].
// 1. Заполняют этот массив единицами.
// 2. Засекают время выполнения: long a = System.currentTimeMillis().
// 3. Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
//      arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//      1. Проверяется время окончания метода System.currentTimeMillis().
//      2. консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).

import java.sql.Array;
import java.util.Arrays;

public class Main {
    static final int size = 1_000_001;
    static boolean check = false;
    static long time1;
    static long time2;
    static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        float arr1 [] = createArray(size);
        float arr2 [] = twoArrays(size);

        check = Arrays.equals(arr2,arr1);
        System.out.println("Is array1 is equal to array2? - " + check + "\n");

        //printArr(arr1);
        //printArr(arr2);
    }

    public static float twoArrays (int size)[]throws InterruptedException {
        float second [] =  new float [size];
        int half1 =0;
        int half2 =0;
        if (size % 2 ==0){ half1 = size/2; half2 = size/2; };
        if (size % 2 !=0){ half1 = size/2; half2 = size-half1;};
        final int cou = half1;
        long b;

        long a = System.currentTimeMillis();
        for (int i = 0; i <second.length; i++){
            second[i] =1;
        }
        System.out.println("Second array was filled by '1's in " + (System.currentTimeMillis()-a) + " miliseconds");
        a = System.currentTimeMillis();
        float arr1 [] = new float [half1];
        float arr2 [] = new float[half2];

        System.arraycopy(second,0,arr1,0, half1);
        System.arraycopy(second,half1,arr2,0,half2);

        System.out.println("Sepatation of array has been done in " + (System.currentTimeMillis()-a) + " miliseconds\n");

        a = System.currentTimeMillis();
        Thread t1 = new Thread(()->{
            count(arr1,0,1);
        });

        b = System.currentTimeMillis();
        Thread t2 = new Thread(()->{
            count(arr2,cou,2);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Array half 1 has filled by the formula in " + (time1-a) +
                " miliseconds");
        System.out.println("Array half 2 has filled by the formula in " + (time2-b) +
                " miliseconds");

        a = System.currentTimeMillis();
        System.arraycopy(arr1,0,second,0, half1);
        System.arraycopy(arr2,0,second,half1,half2);
        System.out.println("\nGluing together of array has been done in " + (System.currentTimeMillis()-a) + " miliseconds\n");

        return second;
    }

    public static float createArray(int size)[]{
        float first [] =  new float[size];
        long a = System.currentTimeMillis();
        for (int i = 0; i <first.length; i++){
            first[i] =1;
        }
        System.out.println("First array was filled by '1's in " + (System.currentTimeMillis()-a) + " miliseconds");

        a = System.currentTimeMillis();
        for (int i = 0; i < first.length; i++) {
            first[i] =(float) (first[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        };

        System.out.println("First array was filled by the formula in " + (System.currentTimeMillis()-a) + " miliseconds\n");
        return first;
    }

    public static void count (float arr[], int count, int num) {
        for (int i = 0, cou = count; i < arr.length && cou < size ; i++, cou++) {
            arr[i] =(float) (arr[i]  * Math.sin(0.2f + cou / 5) * Math.cos(0.2f + cou / 5) * Math.cos(0.4f + cou / 2));
        }
        if (num==1) time1 = System.currentTimeMillis();
        if (num==2) time2 = System.currentTimeMillis();
    }

    public static void printArr(float arr[]){
        for (int i =0;i< arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("\r\n");
    }
}