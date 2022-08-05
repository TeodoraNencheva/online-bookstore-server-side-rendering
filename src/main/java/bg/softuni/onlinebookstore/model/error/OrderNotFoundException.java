package bg.softuni.onlinebookstore.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFoundException extends RuntimeException {
    private Long id;

    public OrderNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
