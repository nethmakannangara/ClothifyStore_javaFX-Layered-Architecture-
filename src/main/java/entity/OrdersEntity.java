package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "orders")
@Table(name = "orders")
public class OrdersEntity {
    @Id
    private String orderId;
    private LocalDate orderDate;
    private String employeeId;
    private String paymentType;
    private double amount;
}
