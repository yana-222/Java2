package J3Lesson7HW;

public class Tests {

    @BeforeSuite
    public static void before (){
        System.out.println("To be done before");
    }
 /*   @BeforeSuite
    public void beforeTest (){
        System.out.println("To be done before - ERROR");
    }*/

    @Test(priority = 8)
    public static void test1(){
        System.out.println("Running test1 priority8");
    }
    @Test(priority = 3)
    public static void test2(){
        System.out.println("Running test2 priority3");
    }
    @Test
    public static void test3(){
        System.out.println("Running test3 without priority");
    }
    @Test(priority = 4)
    public static void test4(){
        System.out.println("Running test4 without priority4");
    }

    @AfterSuite
    public static void after (){
        System.out.println("To be done after");
    }
  /*  @AfterSuite
    public void afterTest (){
        System.out.println("To be done after - ERROR");
    }*/

}
