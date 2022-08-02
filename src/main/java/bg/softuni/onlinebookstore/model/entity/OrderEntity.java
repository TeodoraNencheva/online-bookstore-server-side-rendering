package bg.softuni.onlinebookstore.model.entity;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @ManyToOne(optional = false)
    private UserEntity owner;

    @ElementCollection
    @CollectionTable(name = "orders_items",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "book_id")
    @Column(name = "quantity")
    private Map<BookEntity, Integer> items;

    private boolean isProcessed;

    public OrderEntity() {
    }

    public OrderEntity(UserEntity owner, Map<BookEntity, Integer> items) {
        this.owner = owner;
        setItems(items);
        this.isProcessed = false;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public Map<BookEntity, Integer> getItems() {
        return items;
    }

    public void setItems(Map<BookEntity, Integer> items) {
        this.items = new LinkedHashMap<>();
        this.items.putAll(items);
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}
