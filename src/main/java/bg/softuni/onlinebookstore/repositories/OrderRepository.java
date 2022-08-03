package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT o FROM OrderEntity o WHERE o.isProcessed = true")
    List<OrderEntity> getAllProcessed();
    @Query("SELECT o FROM OrderEntity o WHERE o.isProcessed = false")
    List<OrderEntity> getAllUnprocessed();

    List<OrderEntity> getAllByOwner_Email(String email);
}
