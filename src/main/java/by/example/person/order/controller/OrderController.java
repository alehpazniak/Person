package by.example.person.order.controller;

import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/client/{id}")
    public List<OrderResponse> getClientOrders(@PathVariable(value = "id") int id) {
        return orderService.getClientOrders(id);
    }

    @PostMapping("/client/{id}")
    public List<OrderResponse> createOrder(@PathVariable(value = "id") int id,
                                           @RequestBody OrderRequest orderRequest) {
        return orderService.addOrderToClient(id, orderRequest);
    }
}
