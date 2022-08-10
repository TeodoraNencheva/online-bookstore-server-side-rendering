package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.dto.order.OrderListDTO;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.model.entity.OrderEntity;
import bg.softuni.onlinebookstore.model.entity.UserEntity;
import bg.softuni.onlinebookstore.model.error.EmptyCartException;
import bg.softuni.onlinebookstore.model.error.OrderNotFoundException;
import bg.softuni.onlinebookstore.model.mapper.OrderMapper;
import bg.softuni.onlinebookstore.repositories.OrderRepository;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private int newOrdersCount = 0;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }

    public int getNewOrdersCount() {
        return newOrdersCount;
    }

    @Transactional
    public void createNewOrder(UserDetails userDetails) {
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).get();
        if (user.getCart() == null || user.getCart().isEmpty()) {
            throw new EmptyCartException("Cannot create order on empty cart!");
        }

        Map<BookEntity, Integer> items = user.getCart();
        OrderEntity newOrder = new OrderEntity(user, items);
        orderRepository.save(newOrder);
        user.emptyCart();
        userRepository.save(user);
        newOrdersCount++;
    }

    public List<OrderListDTO> getUnprocessedOrders() {
        return orderRepository.getAllUnprocessed().stream()
                .map(orderMapper::orderEntityToOrderListDTO)
                .collect(Collectors.toList());
    }

    public List<OrderListDTO> getProcessedOrders() {
        return orderRepository.getAllProcessed().stream()
                .map(orderMapper::orderEntityToOrderListDTO)
                .collect(Collectors.toList());
    }

    public OrderEntity getOrder(Long id) {
        Optional<OrderEntity> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException(id);
        }

        return orderOpt.get();
    }

    @Transactional
    public Map<BookEntity, Integer> getOrderItems(Long id) {
        return getOrder(id).getItems();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void confirmOrder(Long id) {
        OrderEntity order = getOrder(id);
        order.setProcessed(true);
        orderRepository.save(order);
    }


    public List<OrderListDTO> getLoggedUserOrders(UserDetails userDetails) {
        return orderRepository.getAllByOwner_Email(userDetails.getUsername())
                .stream().map(orderMapper::orderEntityToOrderListDTO)
                .collect(Collectors.toList());
    }

    public void cleanNewOrdersCount() {
        newOrdersCount = 0;
    }
}
