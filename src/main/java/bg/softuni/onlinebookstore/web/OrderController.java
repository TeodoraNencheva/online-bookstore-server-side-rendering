package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/processed")
    public String getProcessedOrders(Model model) {
        model.addAttribute("type", "Processed");
        model.addAttribute("orders", orderService.getProcessedOrders());
        return "orders";
    }

    @GetMapping("/unprocessed")
    public String getUnprocessedOrders(Model model) {
        model.addAttribute("type", "Unprocessed");
        model.addAttribute("orders", orderService.getUnprocessedOrders());

        return "orders";
    }

    @GetMapping("/{id}/details")
    public String getOrderDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrder(id));
        model.addAttribute("items", orderService.getOrderItems(id));

        return "order-details";
    }

    @GetMapping("/{id}/confirm")
    public String confirmOrder(@PathVariable("id") Long id) {
        orderService.confirmOrder(id);
        return "redirect:/orders/unprocessed";
    }

    @GetMapping("/mine")
    public String getMyOrders(@AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        model.addAttribute("type", "My");
        model.addAttribute("orders", orderService.getLoggedUserOrders(userDetails));
        return "orders";
    }
}
