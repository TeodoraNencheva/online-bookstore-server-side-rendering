package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.model.entity.OrderEntity;
import bg.softuni.onlinebookstore.model.entity.UserEntity;
import bg.softuni.onlinebookstore.repositories.OrderRepository;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createNewOrder(UserDetails userDetails) {
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).get();
        Map<BookEntity, Integer> items = user.getCart();
        OrderEntity newOrder = new OrderEntity(user, items);
        orderRepository.save(newOrder);
        user.emptyCart();
        userRepository.save(user);
    }
}
