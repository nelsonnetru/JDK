package ru.personacode.philosoph;

/*
Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
На столе между каждой парой ближайших философов лежит по одной вилке.

Каждый философ может либо есть, либо размышлять. Приём пищи длится 500 мс. Каждый философ должен пообедать три раза.
Тем не менее, философ может есть только тогда, когда держит две вилки — взятую справа и слева

Каждый философ может взять ближайшую вилку (если она доступна) или положить — если он уже держит её.
Взятие каждой вилки и возвращение её на стол являются раздельными действиями, которые должны выполняться одно за другим.
 */
public class Philosoph implements InterfacePhilosoph {
    private final Object leftFork;
    private final Object rightFork;
    private int eatCount;
    private final int maxEatCount;

    public Philosoph(Object leftFork, Object rightFork, int maxEatCount) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatCount = 0;
        this.maxEatCount = maxEatCount;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                think();
                if (eatCount < maxEatCount)
                {
                    synchronized (leftFork) {
                        get_left_fork();
                        synchronized (rightFork) {
                            get_right_fork();
                            eat();
                            put_right_fork();
                        }
                        put_left_fork();
                    }
                }
                else {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void eat() throws InterruptedException {
        System.out.print(Thread.currentThread().getName() + "("+System.currentTimeMillis()+") ");
        System.out.print(" - кушает ("+(eatCount + 1)+"-й раз)\n");
        eatCount++;
        Thread.sleep(500);
    }

    @Override
    public void think() {
        System.out.print(Thread.currentThread().getName() + "("+System.currentTimeMillis()+") ");
        System.out.print(" думает...\n");

    }

    @Override
    public void get_left_fork() {
        System.out.print(Thread.currentThread().getName() + "("+System.currentTimeMillis()+") ");
        System.out.print(" взял левую вилку\n");
    }

    @Override
    public void get_right_fork() {
        System.out.print(Thread.currentThread().getName() + "("+System.currentTimeMillis()+") ");
        System.out.print(" взял правую вилку\n");
    }

    @Override
    public void put_left_fork() {
        System.out.print(Thread.currentThread().getName() + "("+System.currentTimeMillis()+") ");
        System.out.print(" положил левую вилку\n");
    }

    @Override
    public void put_right_fork() {
        System.out.print(Thread.currentThread().getName() + "("+System.currentTimeMillis()+") ");
        System.out.print(" положил правую вилку\n");
    }
}
