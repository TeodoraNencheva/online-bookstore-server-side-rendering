package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.error.OrderNotFoundException;
import bg.softuni.onlinebookstore.service.OrderService;
import bg.softuni.onlinebookstore.user.BookstoreUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String getOrderDetails(@PathVariable("id") Long id, Model model,
                                  @AuthenticationPrincipal BookstoreUserDetails principal) {
        if (orderService.isOwner(principal.getUsername(), id) || principal.isAdmin()) {
            model.addAttribute("order", orderService.getOrder(id));
            model.addAttribute("items", orderService.getOrderItems(id));

            return "order-details";
        }

        return "error";
    }

    @PostMapping("/{id}/confirm")
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

    @GetMapping("/statistics")
    public String getOrdersStatistics(Model model) {
        model.addAttribute("count", orderService.getNewOrdersCount());
        return "orders-statistics";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({OrderNotFoundException.class})
    public ModelAndView onOrderNotFound(OrderNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("order-not-found");
        modelAndView.addObject("orderId", ex.getId());

        return modelAndView;
    }
}
