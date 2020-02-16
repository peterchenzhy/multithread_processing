package CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SimpleTest4 {

    static Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    dosth();
                    return "A";
                }),
                CompletableFuture.supplyAsync(() -> {
                    dosth();
                    return "B";
                })
        ).get());
    }


    private static void dosth() {
        System.out.println(Thread.currentThread().getName() + " start ");
        ThreadUtil.sleep(random.nextInt(10));
        System.out.println(Thread.currentThread().getName() + " end  ");
    }
}

