package ra.md4project.service.OrdersService.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.md4project.model.order.OrdersItem;
import ra.md4project.repository.OrdersRepository.OrdersItemRepository;
import ra.md4project.service.OrdersService.IOrdersItemService;

@Service
@RequiredArgsConstructor
public class OrdersItemService implements IOrdersItemService {
    private final OrdersItemRepository ordersItemRepository;
    @Override
    public void save(OrdersItem ordersItem) {
        ordersItemRepository.save(ordersItem);
    }
}
