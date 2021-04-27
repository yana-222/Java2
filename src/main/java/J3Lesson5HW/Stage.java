package J3Lesson5HW;
// Перенести приведенный в методичке код в новый проект, где мы организуем гонки.
// Все участники должны стартовать одновременно, несмотря на разное время подготовки.
// В тоннель не может одновременно заехать больше половины участников (условность).
// Попробуйте все это синхронизировать. Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
// Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима). Когда все завершат гонку, нужно выдать объявление об окончании.
// Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из пакета java.util.concurrent.

public abstract class Stage {
    protected boolean Final;
    protected int length;
    protected String description;
    protected Car carWinner = null;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);

    public boolean getFinal() {
        return Final;
    }
    public void setFinal(boolean Final) {
        this.Final = Final;
    }

    public Car getCarWinner() {
        return carWinner;
    }

    public void setCarWinner(Car carWinner) {
        this.carWinner = carWinner;
    }
}