package ru.personacode.philosoph;

public interface InterfacePhilosoph extends Runnable {
    void eat() throws InterruptedException;
    void think();
    void get_left_fork();
    void get_right_fork();
    void put_left_fork();
    void put_right_fork();
}
