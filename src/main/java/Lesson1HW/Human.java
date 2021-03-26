package Lesson1HW;
//1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
// Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//2. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
// участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль
// (успешно пробежал, не смог пробежать и т.д.).
//3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
//4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

public class Human implements Actions{
    String Name;
    int MaxRan;
    int MaxHeight;

    public Human(String name, int maxRan, int maxHeight) {
        Name = name;
        MaxRan = maxRan;
        MaxHeight = maxHeight;
    }

    @Override
    public void run(int length) {
        System.out.println(Name + " human has run " + length + " metres");
    }

    @Override
    public void jump(int height) {
        System.out.println(Name + " human has jumped on " + height + " meters height");
    }

    public String getName() {
        return Name;
    }

    public int getMaxRan() {
        return MaxRan;
    }

    public int getMaxHeight() {
        return MaxHeight;
    }
}
