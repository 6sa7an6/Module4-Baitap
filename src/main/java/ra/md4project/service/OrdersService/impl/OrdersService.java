package ra.md4project.service.OrdersService.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.md4project.model.order.Orders;
import ra.md4project.repository.OrdersRepository.OrdersRepository;
import ra.md4project.service.OrdersService.IOrdersService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService implements IOrdersService {
    private final OrdersRepository ordersRepository;
    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders findOrdersById(Integer id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }
}
