package com.itheima;

import org.junit.jupiter.api.Test;

/**
 * @author Birdy
 * @date 2025/1/24 18:00
 * @description ThreadLocalTest
 */
public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        ThreadLocal tl = new ThreadLocal();

        new Thread(()->{
            tl.set("abc");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "蓝色").start();

        new Thread(()->{
            tl.set("fff");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "绿色").start();
    }
}
