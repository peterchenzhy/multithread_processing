package CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SimpleTest2 {
    //whenCombine 和 supplyAsync 不一定哪个先哪个后，是并行执行的。
    //compose 接收上一级返回的结果，并返回一个新的 CompletableFuture
    //thenApply 在 supplyAsync 后执行
    public static void main(String[] args) {
        Random random = new Random();
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("supplyAsync start");
                    ThreadUtil.sleep(random.nextInt(30));
                    System.out.println("supplyAsync end ");
                    return 2;
                }).thenApply((a) -> {
                    System.out.println("thenApply start ");
                    ThreadUtil.sleep(random.nextInt(20));
                    System.out.println("thenApply end ");
                    return a * 3;
                })
                .thenCompose((s) -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("thenCompose start ");
                    ThreadUtil.sleep(random.nextInt(20));
                    System.out.println("thenCompose end ");
                    return s+1;
                }))
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println("thenCombineAsync start ");
                    ThreadUtil.sleep(random.nextInt(10));
                    System.out.println("thenCombineAsync end ");
                    return 10;
                }), (a, b) -> {
                    System.out.println(a);
                    System.out.println(b);
                    return a + b;
                });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
