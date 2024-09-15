package org.interview.concurrent.printabc;

public class SynchronizedLoopPrint {

    static final Object lock = new Object();
    static boolean aDone = false;
    static boolean bDone = false;

    static boolean cDone = true;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!cDone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("A");
                    lock.notifyAll();
                    aDone = true;
                    cDone = false;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!aDone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("B");
                    lock.notifyAll();
                    bDone = true;
                    aDone = false;
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!bDone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("C");
                    lock.notifyAll();
                    cDone = true;
                    bDone = false;
                }
            }
        }).start();

    }
}
