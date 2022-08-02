package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
