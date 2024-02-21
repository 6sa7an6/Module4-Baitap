package ra.md4project.model.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ra.md4project.model.user.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String addressOrders;
    private String fullName;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date orderAt;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersItem> ordersItemList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment payment;
    public enum OrderStatus{
        WAITING,
        CONFIRM,
        DELIVERY,
        SUCCESS,
        CANCEL
    }
}
