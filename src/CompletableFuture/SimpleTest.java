package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * TestMain
 *
 * @author PeterChen
 * @summary TestMain
 * @Description TestMain
 * @since 2020-02-15 19:14
 */
public class SimpleTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SimpleTest simpleTest = new SimpleTest();

        System.out.println("===main start====");

        System.out.println(simpleTest.dosth(5, simpleTest));

        simpleTest.sleep(10);
        System.out.println("====main end====");
    }

    private Integer dosth(int i, SimpleTest simpleTest) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> simpleTest.sleep(5))
                //执行完成
                .whenComplete((v, e) -> System.out.println(v + "---" + e))
                //补获异常
                .exceptionally((ex) -> {
                    System.out.println( ex.getMessage());
                    return 111;
                }).thenApply((v)->{
                    System.out.println(v);
                    return v ;
                }).get();
    }

    private Integer sleep(int i) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " sleep start " + i);
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " sleep end " + i);
        if (i == 5) {
            throw new RuntimeException(threadName + " my exception");
        }

        return i;
    }

}
