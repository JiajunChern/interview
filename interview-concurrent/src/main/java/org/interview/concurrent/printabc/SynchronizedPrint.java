package org.interview.concurrent.printabc;

public class SynchronizedPrint {

    static final Object lock = new Object();
    static boolean aDone = false;
    static boolean bDone = false;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("A");
                lock.notifyAll();
                aDone = true;
            }
        }).start();

        new Thread(() -> {
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
            }
        }).start();


        new Thread(() -> {
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
            }
        }).start();

    }
}
