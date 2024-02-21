package ra.md4project.service.OrdersService.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.md4project.model.order.Payment;
import ra.md4project.repository.OrdersRepository.PaymentRepository;
import ra.md4project.service.OrdersService.IPaymentService;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
