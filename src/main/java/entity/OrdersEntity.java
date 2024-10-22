package entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersEntity {
    private String orderId;
    private Date orderDate;
    private String employeeId;
    private String paymentType;
    List<OrderDetailsEntity> orderDetailsList;
}
