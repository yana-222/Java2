package Lesson1HW;
// Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
// С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца недели по заданному текущему дню.
// Возвращает кол-во оставшихся рабочих часов до конца недели по заданному текущему дню.
// Считается, что текущий день ещё не начался, и рабочие часы за него должны учитываться.
// Если заданный день выходной то вывести "Сегодня выходной".

import java.time.DayOfWeek;
import java.util.Scanner;

public class Task5 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите порядковый день недели от 1 до 7");
            int number = sc.nextInt();
            DayOfWeek day = getDayOfWeek(number);
            System.out.println(getWorkingHours(day));
        }

        public static String getWorkingHours (DayOfWeek day) {
            int hours = 0;
            String text = "";

            if (day.getDayNumber() == 1||day.getDayNumber() == 2||day.getDayNumber() == 3||day.getDayNumber() == 4||day.getDayNumber() == 5){
                text = "До конца текущей рабочей недели осталось " + day.getHoursToTheEnd() + " часов.";
            }
            if (day.getDayNumber() == 6||day.getDayNumber() == 7){
                text = "Вы ввели выходной день. До конца недели рабочих часов не осталось.";
            }
            return "Вы ввели порядковый номер " + day.getDayNumber() + ".\r\n " +
                    "Введенному номеру соответствует день недели " + day.getRussianname() +
                    ". \r\n " + text;
        }
    public static DayOfWeek getDayOfWeek(int number){
        DayOfWeek day = null;
        if (number == 1) day = DayOfWeek.MONDAY;
        if (number == 2) day = DayOfWeek.TUESDAY;
        if (number == 3) day = DayOfWeek.WEDNESDAY;
        if (number == 4) day = DayOfWeek.THURSDAY;
        if (number == 5) day = DayOfWeek.FRIDAY;
        if (number == 6) day = DayOfWeek.SATURDAY;
        if (number == 7) day = DayOfWeek.SUNDAY;
        return day;
    }
    public enum DayOfWeek {

        MONDAY(1, "Понедельник",40 ),
        TUESDAY(2, "Вторник", 32),
        WEDNESDAY(3, "Среда", 24),
        THURSDAY(4, "Четверг", 16),
        FRIDAY(5, "Пятница",8 ),
        SATURDAY(6, "Суббота", 0),
        SUNDAY(7, "Восресенье",0 );

        private int dayNumber;
        private String russianname;
        private int hoursToTheEnd;

        DayOfWeek(int dayNumber, String russianName, int hoursToTheEnd) {
            this.dayNumber = dayNumber;
            this.russianname = russianName;
            this.hoursToTheEnd = hoursToTheEnd;
        }

        public int getDayNumber() {
            return dayNumber;
        }
        public int getHoursToTheEnd() {
            return hoursToTheEnd;
        }
        public String getRussianname() {
            return russianname;
        }

    }
}


