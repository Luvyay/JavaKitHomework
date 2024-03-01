package Homework5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    private static final int MAX_COUNT_EATING = 3;
    private static final int MIN_RANDOM_SLEEP = 1000;
    private static final int MAX_RANDOM_SLEEP = 5000;

    private static final Random random = new Random();

    private String name;
    private int leftFork;
    private int rightFork;
    private int countEating;

    private CountDownLatch cdl;
    private RoomWithTable roomWithTable;

    public Philosopher(String name, int leftFork, int rightFork, CountDownLatch cdl, RoomWithTable roomWithTable) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cdl = cdl;
        this.roomWithTable = roomWithTable;
    }

    @Override
    public void run() {
        while (countEating < MAX_COUNT_EATING) {
            try {
                thinking();
                eating();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Филосов " + name + " наелся.");
        cdl.countDown();
    }

    private void thinking() throws InterruptedException {
        System.out.println("Филосов " + name + " размышляет...");
        sleep(random.nextInt(MIN_RANDOM_SLEEP, MAX_RANDOM_SLEEP));
    }

    private void eating() throws InterruptedException {
        if (roomWithTable.CanGetForks(leftFork, rightFork)) {
            System.out.println("Филосов " + name + " начинает есть вилками: л-" + leftFork + "; п-" + rightFork);
            sleep(random.nextInt(MIN_RANDOM_SLEEP, MAX_RANDOM_SLEEP));
            roomWithTable.putForks(leftFork, rightFork);
            System.out.println("Филосов " + name + " закончил есть вилками: л-" + leftFork + "; п-" + rightFork);
            countEating++;
        }
    }
}
