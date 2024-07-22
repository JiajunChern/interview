package org.interview.concurrent.printabc;

import java.util.concurrent.Semaphore;

public class SemaphorePrint {

    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);

    static class PrintA extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    s1.acquire();
                    System.out.println("A");
                    s2.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    static class PrintB extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    s2.acquire();
                    System.out.println("B");
                    s3.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    static class PrintC extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    s3.acquire();
                    System.out.println("C");
                    s1.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void main(String[] args) {
        new PrintA().start();
        new PrintB().start();
        new PrintC().start();
    }
}
