package Lesson2HW;
//1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
// При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
// Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
// должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
//3. В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException и
// вывести результат расчета.

public class Main {

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        String[][] arr = {
                {"8", "yut", "11", "jkg"},
                {"6", "4", "rpu", "10"},
                {"2", "6", "1", "ras"},
                {"8", "63", "4", "mas"}
        };
        try {
            Sum(arr);
        } catch (MyArraySizeException mw1) { //catch (MyArraySizeException | RuntimeException) можно писать как или
            // если исключения не наследуюд друг друга
            System.out.println("Array's size is wrong (either length or height of it isn't 4)");
        } catch (MyArrayDataException me2) {
            me2.printStackTrace();
        } finally {
            int Sum = 0;
            for (int i=0; i<arr.length;i++){
                for (int j=0; j< arr[i].length; j++){
                    if (check (arr[i][j]) == false) {arr[i][j] = "0";}
                    Sum = Sum + Integer.parseInt(arr[i][j]);
                }
            }
            System.out.println("The sum of chars, which could be converted to a number, is " + Sum);
        }

    }
    public static boolean check (String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {return false;}
    }

    public static int Sum (String [][] arr) throws MyArraySizeException,MyArrayDataException {
        int Sum = 0;

        if (arr.length != 4 || arr[0].length != 4)
            throw new MyArraySizeException("Array's size is wrong (either length or height of it isn't 4)");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (check (arr[i][j]) == true) Sum = Sum + Integer.parseInt(arr[i][j]);
                if (check (arr[i][j]) == false)
                    throw new MyArrayDataException("There should be only the numbers at array, error on [" +i+"],["+j+"]");
            }
        }
        return Sum;
    }

}