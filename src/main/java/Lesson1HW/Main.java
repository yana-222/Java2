package Lesson1HW;
//1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
// Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//2. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
// участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль
// (успешно пробежал, не смог пробежать и т.д.).
//3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
//4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
//5. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
// При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//6. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
// Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
// должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
//7. В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException и
// вывести результат расчета.

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rd = new Random();
        int MaxRunn = 10000;
        int MaxHeightt = 4;
        int MaxLength = 10000;
        int MaxHighness = 4;

        Actions[] members = {
                new Human("Jak", rd.nextInt(MaxRunn), rd.nextInt(MaxHeightt)),
                new Cat("Bars",rd.nextInt(MaxRunn),rd.nextInt(MaxHeightt)),
                new Robot("Robo",rd.nextInt(MaxRunn),rd.nextInt(MaxHeightt)),
                new Human("Jane", rd.nextInt(MaxRunn), rd.nextInt(MaxHeightt)),
                new Cat("Murzik",rd.nextInt(MaxRunn),rd.nextInt(MaxHeightt)),
                new Robot("Robotik",rd.nextInt(MaxRunn),rd.nextInt(MaxHeightt)),
                new Human("Ann", rd.nextInt(MaxRunn), rd.nextInt(MaxHeightt)),
                new Cat("Tihon",rd.nextInt(MaxRunn),rd.nextInt(MaxHeightt)),
                new Robot("Urban",rd.nextInt(MaxRunn),rd.nextInt(MaxHeightt))
        };

        Object[] obstacles = {
                new Treadmill(rd.nextInt(MaxLength)),
                new Wall(rd.nextInt(MaxHighness)),
                new Treadmill(rd.nextInt(MaxLength)),
                new Wall(rd.nextInt(MaxHighness)),
        };

        for (int i = 0; i< members.length; i++) {
            for (int j = 0; j< obstacles.length; j++){
                if (obstacles[j] instanceof Treadmill){
                    if (members[i] instanceof Human ){
                        if (((Human) members[i]).getMaxRan()>=((Treadmill) obstacles[j]).getLength()) members[i].run(((Treadmill) obstacles[j]).getLength());
                        if (((Human) members[i]).getMaxRan()<((Treadmill) obstacles[j]).getLength()) {System.out.println(((Human) members[i]).getName() + " is out of distance"); break;}
                    }
                    if (members[i] instanceof Cat ){
                        if (((Cat) members[i]).getMaxRan()>=((Treadmill) obstacles[j]).getLength()) members[i].run(((Treadmill) obstacles[j]).getLength());
                        if (((Cat) members[i]).getMaxRan()<((Treadmill) obstacles[j]).getLength()) {System.out.println(((Cat) members[i]).getName() + " is out of distance"); break;}
                    }
                    if (members[i] instanceof Robot ){
                        if (((Robot) members[i]).getMaxRan()>=((Treadmill) obstacles[j]).getLength()) members[i].run(((Treadmill) obstacles[j]).getLength());
                        if (((Robot) members[i]).getMaxRan()<((Treadmill) obstacles[j]).getLength()) {System.out.println(((Robot) members[i]).getName() + " is out of distance"); break;}
                    }

                }
                if (obstacles[j] instanceof Wall){
                    if (members[i] instanceof Human ){
                        if (((Human) members[i]).getMaxHeight()>=((Wall) obstacles[j]).getHeight()) members[i].jump(((Wall) obstacles[j]).getHeight());
                        if (((Human) members[i]).getMaxHeight()<((Wall) obstacles[j]).getHeight()) {System.out.println(((Human) members[i]).getName() + " is out of distance"); break;}
                    }
                    if (members[i] instanceof Cat ){
                        if (((Cat) members[i]).getMaxHeight()>=((Wall) obstacles[j]).getHeight()) members[i].jump(((Wall) obstacles[j]).getHeight());
                        if (((Cat) members[i]).getMaxHeight()<((Wall) obstacles[j]).getHeight()) {System.out.println(((Cat) members[i]).getName() + " is out of distance"); break;}
                    }
                    if (members[i] instanceof Robot ){
                        if (((Robot) members[i]).getMaxHeight()>=((Wall) obstacles[j]).getHeight()) members[i].jump(((Wall) obstacles[j]).getHeight());
                        if (((Robot) members[i]).getMaxHeight()<((Wall) obstacles[j]).getHeight()){System.out.println(((Robot) members[i]).getName() + " is out of distance"); break;}
                    }

                }

            }

        }
    }

}