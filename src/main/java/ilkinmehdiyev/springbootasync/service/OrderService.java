package ilkinmehdiyev.springbootasync.service;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Async
  public void saveOrderDetails(Order order) throws InterruptedException {
    Thread.sleep(2000);
    System.out.println(order.name());
  }

  @Async
  public CompletableFuture<String> saveOrderDetailsFuture(Order order) throws InterruptedException {
    System.out.println("Execute method with return type + " + Thread.currentThread().getName());
    String result = "Hello From CompletableFuture. Order: ".concat(order.name());
    Thread.sleep(5000);
    return CompletableFuture.completedFuture(result);
  }

  @Async
  public CompletableFuture<String> compute(Order order) throws InterruptedException {
    String result = "Hello From CompletableFuture CHAIN. Order: ".concat(order.name());
    Thread.sleep(5000);
    return CompletableFuture.completedFuture(result);
  }
}
