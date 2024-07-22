package org.interview.design.singleton;

import java.util.*;


class Pair<T1, T2> extends AbstractMap.SimpleEntry<T1, T2> {
    public Pair(T1 key, T2 value) {
        super(key, value);
    }
}

class Singleton {
    private static volatile Singleton instance = null;

    List<Pair<String, Integer>> list;

    private Singleton() {
        list = new ArrayList<>();
    }

    public List<Pair<String, Integer>> getList() {
        return list;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Singleton instance = Singleton.getInstance();
        while (in.hasNext()) {
            String name = in.next();
            int num = in.nextInt();
            instance.getList().add(new Pair<>(name, num));
        }
        for (Pair<String, Integer> pair : instance.getList()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
