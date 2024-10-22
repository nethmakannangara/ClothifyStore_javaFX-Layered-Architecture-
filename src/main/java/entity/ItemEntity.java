package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemEntity {
    private String itemCode;
    private String description;
    private Double UnitPrice;
    private String size;
    private Integer qtyOnHand;
}
