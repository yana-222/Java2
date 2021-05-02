package J3Lesson7HW;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//1. Создать класс, который может выполнять «тесты», в качестве тестов выступают классы
// с наборами методов с аннотациями @Test. Для этого у него должен быть статический метод start(),
// которому в качестве параметра передается или объект типа Class, или имя класса. Из «класса-теста»
// вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется, далее запущены методы
// с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite.
// К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет
// выбираться порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения.
// Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
// иначе необходимо бросить RuntimeException при запуске «тестирования».
public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        start(Tests.class);
        start("J3Lesson7HW.Tests");
    }

    public static void start(Class cl) throws InvocationTargetException, IllegalAccessException {
        int bef = 0;
        int aft =0;

        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods){
        if(m.isAnnotationPresent(BeforeSuite.class)){
            bef++;
        }
            if(m.isAnnotationPresent(AfterSuite.class)){
                aft++;
            }
        }
        if(bef>1 || aft >1){
            throw new RuntimeException("There might be no more than one @BeforeSuite / @AfterSuite method at test");
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
              m.invoke(null);
            }
        }

        for (int i = 0;i<10;i++){
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    if(m.getAnnotation(Test.class).priority()==i+1)
                    m.invoke(null);
                }
            }
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                if(m.getAnnotation(Test.class).priority()<1 || m.getAnnotation(Test.class).priority()>10){
                    m.invoke(null);
            }
        }
    }
        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(null);
            }
        }

   }

    public static void start(String className) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        int bef = 0;
        int aft =0;

        Class cl = Class.forName(className);

        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods){
            if(m.isAnnotationPresent(BeforeSuite.class)){
                bef++;
            }
            if(m.isAnnotationPresent(AfterSuite.class)){
                aft++;
            }
        }
        if(bef>1 || aft >1){
            throw new RuntimeException("There might be no more than one @BeforeSuite / @AfterSuite method at test");
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                m.invoke(null);
            }
        }

        for (int i = 0;i<10;i++){
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    if(m.getAnnotation(Test.class).priority()==i+1)
                        m.invoke(null);
                }
            }
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                if(m.getAnnotation(Test.class).priority()<1 || m.getAnnotation(Test.class).priority()>10){
                    m.invoke(null);
                }
            }
        }
        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(null);
            }
        }

    }
}
