package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.service.OrderService;
import bg.softuni.onlinebookstore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final UserService userService;
    private final OrderService orderService;

    public CartController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Map<BookEntity, Integer> userCart = userService.getUserCart(userDetails);
        model.addAttribute("cart", userCart);

        return "cart";
    }

    @GetMapping("/{id}/remove")
    public String removeItem(@PathVariable("id") Long bookId,
                             @AuthenticationPrincipal UserDetails userDetails) {
        userService.removeItemFromCart(bookId, userDetails);

        return "redirect:/cart";
    }

    @GetMapping("/removeAll")
    public String removeAllItems(@AuthenticationPrincipal UserDetails userDetails) {
        userService.removeAllItemsFromCart(userDetails);

        return "redirect:/cart";
    }

    @GetMapping("/confirm")
    public String confirmOrder(@AuthenticationPrincipal UserDetails userDetails) {
        orderService.createNewOrder(userDetails);

        return "redirect:/cart";
    }
}
