import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class EvenOddExecutorservice {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);

        IntStream.rangeClosed(1,15)
                .forEach(num -> {
                    CompletableFuture<Integer> oddcompletableFuture = CompletableFuture.completedFuture(num)
                            .thenApplyAsync(x->
                                    {
                                        if(x % 2 != 0){
                                            System.out.println("Thread name : " + Thread.currentThread().getName()+ "value : " +x);
                                        }
                                        return num;
                                    }, es);
                    oddcompletableFuture.join();

                    CompletableFuture<Integer> evencompletableFuture = CompletableFuture.completedFuture(num)
                            .thenApplyAsync(x ->
                                    {
                                        if(x % 2 == 0) {
                                            System.out.println("Thread Name : " + Thread.currentThread().getName() + "value : " + x);
                                        }
                                        return num;
                                    }, es);
                    evencompletableFuture.join();
                        });
               es.shutdown();

    }
}

