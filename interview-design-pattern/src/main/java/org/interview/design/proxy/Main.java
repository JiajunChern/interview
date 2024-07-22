package org.interview.design.proxy;

import java.util.*;


/*
代理模式 Proxy Pattern 是⼀种结构型设计模式，用于控制对其他对象的访问。
在代理模式中，允许⼀个对象（代理）充当另⼀个对象（真实对象）的接口，以控制对这个对象的访问。通常用于
在访问某个对象时引⼊⼀些间接层(中介的作用)，这样可以在访问对象时添加额外的控制逻辑，⽐如限制访问权
限，延迟加载。
⽐如说有⼀个文件加载的场景，为了避免直接访问“件”对象，我们可以新增⼀个代理对象，代理对象中有⼀个对
“文件对象”的引用，在代理对象的 load 方法中，可以在访问真实的文件对象之前进⾏⼀些操作，⽐如权限检查，
然后调用真实文件对象的 load 方法，最后在访问真实对象后进⾏其他操作，⽐如记录访问⽇志。
*/


/*
代理模式在许多⼯具和库中也有应⽤：
Spring 框架的 AOP 模块使⽤了代理模式来实现切⾯编程。通过代理，Spring 能够在⽬标对象的⽅法执⾏
前、执⾏后或抛出异常时插⼊切⾯逻辑，⽽不需要修改原始代码。
Java 提供了动态代理机制，允许在运⾏时⽣成代理类。
*/

// 1. 定义抽象主题
interface Subject {
    void buy(int area);
}

// 2. 定义真实主题
class RealSubject implements Subject {
    @Override
    public void buy(int area) {
        System.out.println("YES");
    }
}


// 3. 定义代理
class Proxy implements Subject {
    // 包含⼀个引⽤
    private RealSubject realSubject;

    @Override
    public void buy(int area) {
        // 在访问真实主题之前可以添加额外的逻辑
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        // 调⽤真实主题的⽅法

        // 在访问真实主题之后可以添加额外的逻辑
        if (area > 100) {
            realSubject.buy(area);
            return;
        }
        System.out.println("NO");
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Proxy proxy = new Proxy();
        int N = in.nextInt();
        while (N-- > 0) {
            int area = in.nextInt();
            proxy.buy(area);
        }
    }
}
