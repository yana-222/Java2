package J3Lesson6HW;

public class J3Lesson6HW {
//    1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
//    Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
//    идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
//    иначе в методе необходимо выбросить RuntimeException.
//    Написать набор тестов для этого метода (по 3-4 варианта входных данных).
//    Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
//    Вх: [ 1 2 4 3 4 ] -> вых: [ ].
//    Вх: [ 1 2 44 24 3 7 ] -> вых: RuntimeException

//  2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
//  то метод вернет false; Если содержит что то кроме 1 и 4, то метод вернет false; Написать набор тестов для этого метода
//  (по 3-4 варианта входных данных).
//            [ 1 1 1 4 4 1 4 4 ] -> true
//            [ 1 1 1 1 1 1 ] -> false
//            [ 4 4 4 4 ] -> false
//            [ 1 4 4 1 1 4 3 ] -> false

//   3. * Добавить на серверную сторону сетевого чата логирование событий (сервер запущен, произошла ошибка,
//   клиент подключился, клиент прислал сообщение/команду).

    public static int lookingForNumber = 4;
    public static int numberOne = 1;
    public static int numberTwo = 4;
    private static int maxIndex = 0;
    private static int chekZero = -1;

    public static void main(String[] args) {
        int arr1 [] = {1,2,5,7,8,4,22,4,5,56,8,94,1,4,12,20,34,16,4,20,18,19,8};
        int arr2 [] = {2,2,2,2,2,2,2,2,2,2,2,24};
        int arr3 [] = {1,1,4,1,1,4,1};
        int arr4 [] = {1,1,1,1,1,1};
        int arr5 [] = {4,4,4,4,4,4};
        int arr6 [] = {1,4};
        int arr7 [] = {1,1,4,2};
       //  printArr(task1(arr1));
        // printArr(task1(arr1));
        // printArr(task1(arr2));
        // printArr(task1(arr3));
        // printArr(task1(arr4));
//        printArr(task1(arr5));
//        printArr(task1(arr6));
    //    printArr(task1(arr7));
        System.out.println(task2(arr1));
        System.out.println(task2(arr2));
        System.out.println(task2(arr3));
        System.out.println(task2(arr4));
        System.out.println(task2(arr5));
        System.out.println(task2(arr6));
        System.out.println(task2(arr7));
    }

    public static int[] task1 (int [] ints)  {

        for (int i = 0; i<ints.length;i++){
            if (ints[i]==lookingForNumber) {maxIndex = i;}
            if (ints[i]==0) {chekZero = 0 + ints[i];}
        }

        checkExeption(ints);

        int ints2[] = new int[ints.length-maxIndex-1];

        System.arraycopy(ints,maxIndex+1,ints2,0,ints2.length);
        return ints2;
    }

    private static void printArr(int[] ints){
        for (int i = 0; i < ints.length; i++){
            System.out.print(ints[i]+" ");
        }
        System.out.println();
    }

    private static void checkExeption(int [] ints) {
      if ((ints[0]!=0 && maxIndex ==0 )||chekZero==0) {
            throw new NewException("There should be at least one figure '" + lookingForNumber + "' at the array\n" +
              "Try another array, which contains " + lookingForNumber);
      };
    }

    public static boolean task2(int[] ints){
        boolean result = false;
        int one =0 ;
        double sum = SUM(ints);
        for (int i = 0; i< ints.length; i++){
            if ((ints[i] == numberOne | ints[i] == numberTwo) && ! ((sum/ints.length == numberOne) | (sum/ints.length == numberTwo))) {
                one = one+1;
            }
        }
        if (one == ints.length) result = true;
        return result;
    }
    public static double SUM (int[] ints){
        double SUM = 0;
        for (int i = 0; i< ints.length; i++){
            SUM = SUM + (double) ints[i];
        }
        return SUM;
    }

}
