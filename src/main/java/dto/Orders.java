package dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {
    private String orderId;
    private LocalDate orderDate;
    private String employeeId;
    private String paymentType;
    private double amount;
    List<OrderDetails> orderDetailsList;
}
