package J3Lesson5HW;
// Перенести приведенный в методичке код в новый проект, где мы организуем гонки.
// Все участники должны стартовать одновременно, несмотря на разное время подготовки.
// В тоннель не может одновременно заехать больше половины участников (условность).
// Попробуйте все это синхронизировать. Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
// Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима). Когда все завершат гонку, нужно выдать объявление об окончании.
// Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из пакета java.util.concurrent.

import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    public AtomicBoolean winner = new AtomicBoolean(false);

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
             if (race.getStages().get(i).getFinal() == true && race.getStages().get(i).getCarWinner() == null){
                    race.getStages().get(i).setCarWinner(this);
                    this.winner.getAndSet(true);
            }
        }
    }

    public void ready() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    public AtomicBoolean isWinner() {
        return winner;
    }

}