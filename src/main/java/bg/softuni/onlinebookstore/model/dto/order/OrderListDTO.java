package bg.softuni.onlinebookstore.model.dto.order;

import java.util.UUID;

public class OrderListDTO {
    private UUID id;

    public OrderListDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
