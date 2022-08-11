package task.hw7;


import java.util.*;
import java.util.stream.IntStream;


/*С использованием нескольких потоков выполнения (Thread) реализовать поиск простых чисел внутри заданного диапазона.
Ввести с клавиатуры интервал поиска чисел (например, от 1 до 10000) и число потоков.
Проверку на простоту реализовать самым простым способом: циклом от 2 до числа/2.
Подумать, как разделять интервал поиска по потокам.
Найденные числа должны быть записаны в один общий массив в таком виде, что найденные
простые числа одного потока должны быть расположены подряд.
Написать Unit тесты.*/

public class Demo {





//    List<Integer> list1 = Collections.synchronizedList(new ArrayList());
    static List<Integer> list1 = new ArrayList();
    static List<Integer> list2 = new ArrayList();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            start();
            System.out.println();
            start2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void start2() throws InterruptedException {
        long start = System.currentTimeMillis();

        int startFrom = 0;
        for (int i = 0; i < 3; i++) {
            run(startFrom, startFrom + 250);
            startFrom += 250;
        }
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("Total time: " + totalTime + " ms");
        System.out.println(list2);
    }

    public static void start() throws InterruptedException {
        long start = System.currentTimeMillis();



        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 250; i++) {
                if (isPrime(i)) {
                    list1.add(i);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 250; i < 500; i++) {
                if (isPrime(i)) {
                    list1.add(i);
                }
            }
        });

        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 500; i < 750; i++) {
                if (isPrime(i)) {
                    list1.add(i);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("Total time: " + totalTime + " ms");

        System.out.println(list1);
    }

    public static boolean isPrime(int number) {
        return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
    }

    public static void run(int start, int end) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = start; i < end; i++) {
                if (isPrime(i)) {
                    list2.add(i);
                }
            }
        });
        t.start();
        t.join();
    }
}