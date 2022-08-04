package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.book.AddBookToCartDTO;
import bg.softuni.onlinebookstore.model.dto.book.BookAddedToCartDTO;
import bg.softuni.onlinebookstore.service.BookService;
import bg.softuni.onlinebookstore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartRestController {
    private final UserService userService;
    private final BookService bookService;

    public CartRestController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public void getCartItems(@AuthenticationPrincipal UserDetails userDetails) {

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookAddedToCartDTO> addBookToCart(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody AddBookToCartDTO bookDTO) {
        if (userService.addBookToCart(userDetails, bookDTO)) {
            return ResponseEntity.ok(bookService.getAddedBook(bookDTO));
        }

        return ResponseEntity.notFound().build();
    }
}
