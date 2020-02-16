package com.lianjia.sh.salary.taxfriend.test;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * TestMain
 *
 * @author PeterChen
 * @summary TestMain
 * @Copyright (c) 2020, Lianjia Group All Rights Reserved.
 * @Description TestMain
 * @since 2020-02-15 19:14
 */
public class TestMain {
    @SneakyThrows
    public static void main(String[] args) {

        TestMain testMain = new TestMain();

        System.out.println("===main start====");

        System.out.println(testMain.dosth(5, testMain));

        testMain.sleep(10);
        System.out.println("====main end====");
    }

    private Integer dosth(int i, TestMain testMain) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> testMain.sleep(5))
                .whenComplete((v, e) -> System.out.println(v + "---" + e))
                .exceptionally((ex) -> {
                    System.out.println( ex.getMessage());
                    return 111;
                }).get();
    }

    @SneakyThrows
    private Integer sleep(int i) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " sleep start " + i);
        Thread.sleep(i * 1000L);
        System.out.println(threadName + " sleep end " + i);
        if (i == 5) {
            throw new RuntimeException(threadName + " my exception");
        }

        return i;
    }

}
