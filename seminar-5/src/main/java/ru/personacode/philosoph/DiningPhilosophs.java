package ru.personacode.philosoph;

public class DiningPhilosophs {
    private static final int COUNT_PHILOSOPHS = 5;
    private static final int COUNT_MAX_EATING = 3;

    public static void main(String[] args) {
        // создадим массив философов
        Philosoph[] philosophs = new Philosoph[COUNT_PHILOSOPHS];
        // и массив вилок, равный кол-ву философов
        Object[] forks = new Object[philosophs.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophs.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == 0) {
                /*
                 первый философ должен сначала поднять правую вилку, а не левую, как другие, иначе
                 каждый философ поднимет свою левую вилку и процесс зависнет по кругу
                 */
                philosophs[i] = new Philosoph(rightFork, leftFork, COUNT_MAX_EATING);
            } else {
                philosophs[i] = new Philosoph(leftFork, rightFork, COUNT_MAX_EATING);
            }

            Thread thread = new Thread(philosophs[i], "Философ-" + (i + 1));
            thread.start();
        }

    }
}
