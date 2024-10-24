package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTm {
    private String itemCode;
    private String description;
    private String size;
    private Double unitPrice;
    private Integer quantity;
    private Double amount;
}
