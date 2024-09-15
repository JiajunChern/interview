package org.interview.concurrent.printabc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPrint {

    private static ReentrantLock lock = new ReentrantLock();
    private static int value = 0;

    private static class PrintA implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (value % 3 == 0) {
                        System.out.println("A");
                        value = 1;
                    }
                } finally {
                    lock.unlock();
                }

            }
        }
    }
    private static class PrintB implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (value % 3 == 1) {
                        System.out.println("B");
                        value = 2;
                    }
                } finally {
                    lock.unlock();
                }

            }
        }
    }
    private static class PrintC implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (value % 3 == 2) {
                        System.out.println("C");
                        value = 0;
                    }
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) {
        PrintA a = new PrintA();
        PrintB b = new PrintB();
        PrintC c = new PrintC();
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }

}
