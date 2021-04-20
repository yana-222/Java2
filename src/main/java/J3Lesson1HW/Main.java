package J3Lesson1HW;

import java.util.ArrayList;
import java.util.Arrays;
// 1. Задача:
// a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
// b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
//    поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
// c. Для хранения фруктов внутри коробки можно использовать ArrayList;
// d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
//    вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
// e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
//    которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
//    Можно сравнивать коробки с яблоками и апельсинами;
// f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
//    Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
//    Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
// g. Не забываем про метод добавления фрукта в коробку.
public class Main {

    public static void main(String[] args) {
        Fruit apple1 = new Apple(1);
        Fruit apple2 = new Apple(2);
        Apple apple3 = new Apple(3);
        Apple apple4 = new Apple(4);
        Apple apple5 = new Apple(5);
        Apple apple6 = new Apple(6);
        Apple apple7 = new Apple(7);
        Apple apple8 = new Apple(8);
        Orange orange1 = new Orange(1);
        Orange orange2 = new Orange(2);
        Orange orange3 = new Orange(3);
        Orange orange4 = new Orange(4);
        Orange orange5 = new Orange(5);
        Orange orange6 = new Orange(6);
        Orange orange7 = new Orange(7);
        Orange orange8 = new Orange(8);
        Box box1 = new Box(1);
        Box box2 = new Box(2);
        Box box3 = new Box(3);
        Box box4 = new Box(4);

        box1.addAFruit(apple1);
        box1.addAFruit(orange1);
        box1.addAFruit(orange2);
        box1.addAFruit(apple2);
        box2.addAFruit(orange1);
        box2.addAFruit(orange2);
        box2.addAFruit(apple3);
        box2.addAFruit(orange3);

        System.out.println(box1 + ", " + box2 + ", " + box3 + ", " + box4);
        System.out.println("Checking if the box1 has the same weight as the box2 is: " + box1.compare(box2));
        System.out.println("Checking if the box3 has the same weight as the box4 is: " + box3.compare(box4));

        box1.pour(box2);
        box1.pour(box3);
        box2.pour(box4);

        System.out.println(box1 + ", " + box2 + ", " + box3 + ", " + box4);
        System.out.println("Checking if the box1 has the same weight as the box2 is: " + box1.compare(box2));
        System.out.println("Checking if the box3 has the same weight as the box4 is: " + box3.compare(box4));

    }

}
