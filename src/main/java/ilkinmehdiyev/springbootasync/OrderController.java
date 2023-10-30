package ilkinmehdiyev.springbootasync;

import ilkinmehdiyev.springbootasync.service.Order;
import ilkinmehdiyev.springbootasync.service.OrderService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/process")
  public ResponseEntity<Void> process(@RequestBody Order order) throws InterruptedException {
    System.out.println("PROCESSING STARTED");
    orderService.saveOrderDetails(order);
    return ResponseEntity.ok(null);
  }

  @PostMapping("/process/future")
  public ResponseEntity<String> processFuture(@RequestBody Order order) throws InterruptedException, ExecutionException {
    System.out.println("PROCESSING STARTED");
    CompletableFuture<String> orderDetailsFuture = orderService.saveOrderDetailsFuture(order);
    return ResponseEntity.ok(orderDetailsFuture.get());
  }

  @PostMapping("/process/future/chain")
  public ResponseEntity<Void> processFutureChain(@RequestBody Order order) throws InterruptedException, ExecutionException {
    System.out.println("PROCESSING STARTED");
    CompletableFuture<String> computeResult = orderService.compute(order);
    computeResult.thenApply(result -> result).thenAccept(System.out::println);
    return ResponseEntity.ok(null);
  }
}
