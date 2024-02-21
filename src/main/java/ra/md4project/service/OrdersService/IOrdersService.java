package ra.md4project.service.OrdersService;

import ra.md4project.model.order.Orders;

import java.util.List;

public interface IOrdersService {
    Orders save(Orders orders);
    Orders findOrdersById(Integer id);
    List<Orders> findAll();
}
