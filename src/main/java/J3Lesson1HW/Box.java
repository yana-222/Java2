package J3Lesson1HW;
// 3. Задача: a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
//// b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
////    поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//// c. Для хранения фруктов внутри коробки можно использовать ArrayList;
//// d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
////    вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
//// e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
////    которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
////    Можно сравнивать коробки с яблоками и апельсинами;
//// f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
////    Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
////    Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
//// g. Не забываем про метод добавления фрукта в коробку.

import java.util.ArrayList;

public class Box <T extends Fruit & Comparable> {
    private float weight;
    private String type;
    private int number;
    ArrayList <T> box  = new ArrayList();

    public Box(int num){
        number = num;
    }

    public boolean compare (Box b) {
       return box.size() * weight == b.box.size() * weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public ArrayList<T> getBox() {
        return box;
    }

    public void setBox(ArrayList<T> box) {
        this.box = box;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight(){
      return weight* box.size();
    }

    public void addAFruit (T fruit){
       boolean check = this.box.isEmpty();
       if (check == true) {
           this.setType(fruit.getType());
           this.setWeight(fruit.getWeight());
       }
       if (check == true || (this.getType() == fruit.getType())){
           this.box.add(fruit);
       }
       if (check == false && this.getType() != fruit.getType()){
           System.out.println("You can't put an "+fruit.getType()+ " at the box with " + this.getType()+"s!");
       }
    }

    public void pour (Box receiver){
        boolean check = receiver.box.isEmpty();
        if (check == true || (this.getType() == receiver.getType()))
            {for (Fruit t : this.box){
                receiver.addAFruit(t);
            }
            this.box.clear();
            this.type = null;
            }

        if (check == false && receiver.getType() != this.getType()){
            System.out.println("You can't put an "+this.getType()+ " at the box with " +receiver.getType()+"s!");
        }
    }

    @Override
    public String toString() {
        return "Box "+ number + ": total weight " + this.getWeight() +", filled by " + type +"s; ";
    }
}
