package Lesson3HW;

//1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//   Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//   Посчитать, сколько раз встречается каждое слово.
//2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
//   В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
//   Номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
//   тогда при запросе такой фамилии должны выводиться все телефоны. Желательно не добавлять лишний функционал
//   (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль и т.д).
//   Консоль использовать только для вывода результатов проверки телефонного справочника.

import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

//  Из массива и ввода данных можно получить stream для работы с коллецией;
//  Можно использовтаь map для преобразования / можно складывать / искать среднее;
//  collect - вкинуть в коллекцию (Collectors - отд. класс);
//  метод split у строки возвращает массив строк (на вход нужно передать разделитель);
//  map.getOrDefault - не возвращает Null (преобразует к 0);

public class Main {
    public static int numberOfEntry;

    public static void main(String[] args) {
        //Task1();
        Scanner sc = new Scanner(System.in);
        HashSet<PhoneList> AdressBook = new HashSet<>();

        addNewEntry("Selezneva","8-901-766-10-22", AdressBook);
        addNewEntry("Ivanova","8-952-390-44-22", AdressBook);
        addNewEntry("Petrov","8-919-908-22-16", AdressBook);
        addNewEntry("Ivanova","8-908-111-12-12", AdressBook);

        System.out.println("Enter the surname of the person you looking for:");
        String sn = sc.next();
        getPhoneNumber(sn, AdressBook);

    }

    private static void getPhoneNumber(String surname, HashSet<PhoneList> AdressBook) {

        surname = surname.toLowerCase(Locale.ROOT);
        String rezult = "";
        for (PhoneList ph : AdressBook){
            if (ph.familyName.equals(surname))
                rezult = rezult + ph.phoneNumber + ", ";
        }
        System.out.println("We found phone numbers for surname you provided: "+ rezult);
    }

    private static void addNewEntry(String surname, String number, HashSet<PhoneList> AdressBook) {
        surname = surname.toLowerCase(Locale.ROOT);
        PhoneList u = new PhoneList(surname, number);
        AdressBook.add(u);
    }

    private static void Task1() {
        ArrayList<String> words = new ArrayList<>();
        words.add("Chess");
        words.add("Glass");
        words.add("love");
        words.add("Peace");
        words.add("Apple");
        words.add("Word");
        words.add("Chess");
        words.add("World");
        words.add("Class");
        words.add("Chess");
        words.add("love");
        words.add("Cherry");

        int[] numberOfWords = new int[words.size()];
        for (int i = 0; i < words.size(); i++) {
            int v = 0;
            for (int j = 0; j < words.size(); j++) {
                if (words.get(i).equals(words.get(j))) {
                    v++;
                }
                numberOfWords[i] = v;
            }
        }

        HashSet<String> unik = new HashSet<>();
        for (String s : words) {
            unik.add(s);
        }
        System.out.println("Array 'words' contains these units: " + unik);

        for (String s : unik) {

            System.out.println("The word '" + s + "' meets " + numberOfWords[words.indexOf(s)] + " times at the array 'words'.");
        }
    }
}
