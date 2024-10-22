package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "item")
@Table(name = "item")
public class ItemEntity {
    @Id
    private String itemCode;
    private String description;
    private Double unitPrice;
    private String size;
    private Integer qtyOnHand;
}
