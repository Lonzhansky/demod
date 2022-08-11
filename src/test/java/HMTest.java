import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import task.hw1.Main;
import task.hw5.model.PassengerCar;
import task.hw5.process.VehicleProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HMTest {

    @Test
    void hm1_test1() {
        int[] arrayN = {0, 2, 4, 6, 3, -1, 1};
        int[] arrayK = {4, 6, 3};
        assertTrue(Main.check(arrayN, arrayK));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 55 })
    void demo1(int num) {
        assertTrue(num >= 0 && num <= 10);
    }


    @ParameterizedTest
    @MethodSource("methodSourceDemo1")
    void demo2(String argument) {
        assertTrue(argument.length() == 6);
    }

    static Stream<String> methodSourceDemo1() {
        return Stream.of("qwerty", "ytrewq", "sdfsdfsd");
    }


    @ParameterizedTest
    @MethodSource("methodSourceWithArguments")
    void demo3(String str, int num, List<String> list) {
        assertEquals(6, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> methodSourceWithArguments() {
        return Stream.of(
                arguments("qwerty", 1, Arrays.asList("a1", "b1")),
                arguments("ytrewq", 2, Arrays.asList("x2", "y2"))
        );
    }

    @ParameterizedTest
    @MethodSource("demoHM")
    void demoHM(PassengerCar car) {
        assertTrue(VehicleProcessor.check(car, 70, 120));
//        assertTrue((car.getSpeed() >= 90 && car.getSpeed() <= 110));
//        assertTrue((car.getSpeed() >= 70 && car.getSpeed() <= 120));
    }


    static Stream<PassengerCar> demoHM() {
        return Stream.of(
                new PassengerCar(1, 10, 3000, 120, 120, 4,
                        66),
                new PassengerCar(2, 6, 1500, 80, 70, 4,
                        40)
        );
    }


    @Test
    void testHM2() {
        int[] array = {4, 777, 111, 42, 88888888, 4321, 1111, 1};

        int max = task.hw2.Main.getMax(array);
        assertEquals(1, task.hw2.Main.getMin(array));
        assertEquals(88888888, max);
        assertEquals(10, String.valueOf(max).length());

    }


    @Test
    void testHM3() {
        int[] array = {118888, 887, 41, 186, 9571, 12345678, 333, 10000, 77788};
        ArrayList<Integer> unique = task.hw3.Main.getUnique(array);

        for (Integer integer : unique) {

        }
    }

    @Test
    void testHM4() {
        int k = 8;
        int n = 1;
        int num = 1;

        String string = task.hw4.Main.getString(k, n, num);
        assertEquals("1\t2\t3\t\n4\t5\t6\t\n7\t8\t\n", string);
    }


}
