package J3Lesson5HW;
// Перенести приведенный в методичке код в новый проект, где мы организуем гонки.
// Все участники должны стартовать одновременно, несмотря на разное время подготовки.
// В тоннель не может одновременно заехать больше половины участников (условность).
// Попробуйте все это синхронизировать. Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
// Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима). Когда все завершат гонку, нужно выдать объявление об окончании.
// Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из пакета java.util.concurrent.

public class Road extends Stage {

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.Final = false;
    }


    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}