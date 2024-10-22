package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String itemCode;
    private String description;
    private Double unitPrice;
    private String size;
    private Integer qtyOnHand;
}
