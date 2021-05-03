package J3Lesson6HW;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task2 {
private static J3Lesson6HW hw;
    @BeforeAll
    public static void init() {hw = new J3Lesson6HW();}

    public static Stream<Arguments> data(){
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(false, new int[]{1,2,5,7,8,4,22,4,5,56,8,94,1,4,12,20,34,16,4,20,18,19,8}));
        out.add(Arguments.arguments(false, new int[]{2,2,2,2,2,2,2,2,2,2,2,24}));
        out.add(Arguments.arguments(true, new int[]{1,1,4,1,1,4,1}));
        out.add(Arguments.arguments(false, new int[]{1,1,1,1,1,1}));
        out.add(Arguments.arguments(false, new int[]{4,4,4,4,4,4}));
        out.add(Arguments.arguments(true, new int[]{1,4}));
        out.add(Arguments.arguments(false, new int[]{1,1,4,2}));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("data")
    public void task2Test(boolean bool, int[] ints) {
        Assertions.assertEquals(bool, hw.task2(ints));
    }
}

