package J3Lesson5HW;
// Перенести приведенный в методичке код в новый проект, где мы организуем гонки.
// Все участники должны стартовать одновременно, несмотря на разное время подготовки.
// В тоннель не может одновременно заехать больше половины участников (условность).
// Попробуйте все это синхронизировать. Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
// Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима). Когда все завершат гонку, нужно выдать объявление об окончании.
// Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из пакета java.util.concurrent.

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws IllegalArgumentException, InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            final int w = i;
            Thread t = new Thread(()->{
                 try {
                    cars[w].ready();
                    cyclicBarrier.await();
                    if (w == cars.length - 1) System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                    cyclicBarrier.await();
                    cars[w].run();
                    cyclicBarrier.await();
                    if (w == cars.length - 1) System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                     cyclicBarrier.await();
                    if (w == cars.length - 1) System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>>  Победил " + race.getStages().get(race.getStages().size()-1).getCarWinner().getName());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                 }
            });
            t.start();
        }
    }
}


