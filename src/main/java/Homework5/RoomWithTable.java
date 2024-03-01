package Homework5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RoomWithTable {
    private static final  String[] arrayName = {"Oleg", "Vanya", "Kolya", "Sasha", "Fedya"};

    private static final int COUNT_PHILOSOPHER = 5;

    private Fork[] forks;
    private Philosopher[] philosophers;

    private CountDownLatch cdl;

    private static final Random random = new Random();

    public RoomWithTable() {
        forks = new Fork[COUNT_PHILOSOPHER];
        philosophers = new Philosopher[COUNT_PHILOSOPHER];
        cdl = new CountDownLatch(COUNT_PHILOSOPHER);

        init();
    }

    private void init() {
        for (int i = 0; i < COUNT_PHILOSOPHER; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < COUNT_PHILOSOPHER; i++) {
            philosophers[i] = new Philosopher(i + " " + randomName(), i, (i+1) % COUNT_PHILOSOPHER
            , cdl, this);
        }
    }

    private String randomName() {
        return arrayName[random.nextInt(arrayName.length)];
    }

    public synchronized boolean CanGetForks(int leftFork, int rightFork) {
        if (!forks[leftFork].isUp() && !forks[rightFork].isUp()) {
            forks[leftFork].setUp(true);
            forks[rightFork].setUp(true);
            return true;
        }

        return false;
    }

    public void putForks(int leftFork, int rightFork) {
        forks[leftFork].setUp(false);
        forks[rightFork].setUp(false);
    }

    public void startEating() {
        System.out.println("Философы начинают есть");
        System.out.println("-----------------------------------------------------------");

        for (int i = 0; i < COUNT_PHILOSOPHER; i++) {
            philosophers[i].start();
        }

        endEating();
    }

    private void endEating() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("Все философы наелись.");
    }
}
