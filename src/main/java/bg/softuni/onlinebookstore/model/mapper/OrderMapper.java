package bg.softuni.onlinebookstore.model.mapper;

import bg.softuni.onlinebookstore.model.dto.order.OrderListDTO;
import bg.softuni.onlinebookstore.model.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderListDTO orderEntityToOrderListDTO(OrderEntity order);
}
