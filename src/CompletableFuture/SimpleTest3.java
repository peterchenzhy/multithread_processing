package CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SimpleTest3 {
    //以上代码有时输出A，有时输出B，哪个Future先执行完就会根据它的结果计算。
    //acceptEither --null 没有返回值，applyToEither -- A/B 有返回值
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        System.out.println("main---"+ CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(random.nextInt(10));
            return "A";
        })
                //acceptEither
//                .acceptEither(CompletableFuture.supplyAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(10));
//                    return "B";
//                }), System.out::println)
//                .get()

                //applyToEither
                .applyToEither(CompletableFuture.supplyAsync(() -> {
                    ThreadUtil.sleep(random.nextInt(10));
                    return "B";
                }), (e)->{
                    System.out.println(e);
                    return e;
                }).get()
        );
    }
}
